/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import Conexion.GestionSQL;
import DTO.ContratosAVencerse;
import Negocio.Comunes;
import Negocio.Log;
import Negocio.Notificacion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author George
 */
public class NotificarContratosAVencerseAdministrador implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        Log.registroTraza("Job: Inicia NotificarContratosAVencerseAdministrador");                 
         
        String strSQL = "", strFechaFin=null, strConsecutivo=null, strContratista=null, strGrupo=null;
        String[] strGenerales = null, strTemp = null;
        Integer intNumDiasAlerta = null, intRestante=null;
        Vector arrContratos = null;
        Vector arrConsecutivos = new Vector();
        Vector arrFechasFin = new Vector();
        Vector arrContratistas = new Vector();
        Vector arrGrupos = new Vector();
        Calendar fechaActual = null;
        Calendar fechaFin = null;
        List<ContratosAVencerse> contratosAVencerse = new ArrayList<ContratosAVencerse>();
        ContratosAVencerse contratoAVencerse = null;                         
        
        Comunes comunes = new Comunes();          
        Notificacion notificacion = new Notificacion();                      
        
        strSQL = "select g.txtNumDiasAlertaAVencerse from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";
        strGenerales = GestionSQL.getFila(strSQL,"Nomina");
        intNumDiasAlerta = Integer.parseInt(strGenerales[0]);
        
        strSQL = "SELECT c.txtConsecutivo, c.dtFechaFin, CONCAT(con.txtNombres,' ',con.txtApellidos), g.txtNombre from nomina.tbl_contratos c, users.users_grupos_siu g, nomina.tbl_contratistas con WHERE (c.txtIdContratista = con.txtNumId) and (c.txtIdGrupo = g.txtCodigo) and (c.txtIdEstado = 'CR' or c.txtIdEstado = 'EJ') ORDER BY c.txtConsecutivo";
        arrContratos = GestionSQL.consultaSQL(strSQL,"Nomina","4");
        
        if (arrContratos != null){            
           
            for (int i=0;i<arrContratos.size();i++){
                strTemp = arrContratos.get(i).toString().split(">");
                arrConsecutivos.add(strTemp[0]);
                arrFechasFin.add(strTemp[1]);                
                arrContratistas.add(strTemp[2]);
                arrGrupos.add(strTemp[3]);
            }                        
            
            //Obtener los feriados.

            Vector arrFechas = new Vector();
            strSQL = "SELECT d.dtFecha from users.users_dias_no_habiles d order by d.dtFecha";
            arrFechas = GestionSQL.consultaSQL(strSQL, "Users", "1");                        
            
            for(int i=0;i<arrConsecutivos.size();i++){
                strConsecutivo = arrConsecutivos.get(i).toString();
                strContratista = arrContratistas.get(i).toString();
                strGrupo = arrGrupos.get(i).toString();                
                
                fechaActual = comunes.calcularFechaActual();                                
                fechaFin = Calendar.getInstance();                             

                strFechaFin = arrFechasFin.get(i).toString();            
                strTemp = strFechaFin.split("-");                           
                fechaFin.set(Integer.parseInt(strTemp[0]),(Integer.parseInt(strTemp[1])-1),Integer.parseInt(strTemp[2]));
                fechaFin.set(Calendar.SECOND, 0);
                fechaFin.set(Calendar.MILLISECOND, 0);
                
                intRestante = (comunes.getDiasHabiles(fechaActual, fechaFin, arrFechas)-1);              

                if ((intRestante <= intNumDiasAlerta) && (intRestante > 0)){                                
                    contratoAVencerse = new ContratosAVencerse();
                    contratoAVencerse.setConsecutivo(strConsecutivo);                 
                    contratoAVencerse.setFechaFin(strFechaFin);                 
                    contratoAVencerse.setContratista(strContratista);
                    contratoAVencerse.setGrupo(strGrupo);
                    contratoAVencerse.setTiempoRestante(intRestante);                    
                    contratosAVencerse.add(contratoAVencerse);                                            
                }                                
                
                fechaActual = null;
                fechaFin = null;
                intRestante = 0;
                strFechaFin = null;
                strTemp = null;
                strConsecutivo = null;
                contratoAVencerse = null;
                strContratista = null;
                strGrupo = null;
            }                       
            
            if (contratosAVencerse.size() > 0){                
                notificacion.notificarContratosAVencerseAdministrador(contratosAVencerse);                
            }                       
        }                
               
        Log.registroTraza("Job: Finaliza NotificarContratosAVencerseAdministrador. Contratos notificados: " + contratosAVencerse.size());
    }    
}
