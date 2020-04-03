/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import Conexion.GestionSQL;
import Negocio.Comunes;
import Negocio.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author jorge.correa
 */
public class CambiarEstadoContrato implements Job {
    
    public void execute(JobExecutionContext jec) throws JobExecutionException {
      
        // Aca pueden poner la tarea o el job que desean automatizar.
        // Por ejemplo enviar correo, revisar ciertos datos, etc.
        
        Log.registroTraza("Job: Inicia CambiarEstadoContrato");
      
        String strSQL, strFechaHoy="", strConsecutivo="", strFechaInicio="", strFechaFin="", strIdEstado="", strIdNuevoEstado="",strMensaje=null;
        String[] strTemp = null;        
        Date dtFechaHoy = null, dtFechaInicio=null, dtFechaFin=null;
        Comunes comun = new Comunes();
        int intTotal=0;
        
        // Obtener la fecha actual.
        
        strFechaHoy = comun.getFechaActual();
        dtFechaHoy = comun.getDateFromString(strFechaHoy);
        
        strSQL = "select c.txtConsecutivo, c.dtFechaInicio, c.dtFechaFin, c.txtIdEstado from nomina.tbl_contratos c where (c.txtIdEstado = 'CR' or c.txtIdEstado = 'EJ') ORDER BY c.txtConsecutivo";
        Vector arrContratos = GestionSQL.consultaSQL(strSQL, "Nomina", "4");
        
        if (arrContratos != null){
            for (int i=0;i<arrContratos.size();i++){
                strTemp = arrContratos.get(i).toString().split(">");
                strConsecutivo = strTemp[0];
                strFechaInicio =  strTemp[1];      
                strFechaFin = strTemp[2];     
                strIdEstado = strTemp[3];     
                strIdNuevoEstado = "";
                strMensaje = null;

                if (strIdEstado.equals("CR")){
                    dtFechaInicio = comun.getDateFromString(strFechaInicio);

                    if (dtFechaHoy.compareTo(dtFechaInicio) == 0){           
                        strIdNuevoEstado = "EJ";
                    }        
                }            

                if (strIdEstado.equals("EJ")){
                    dtFechaFin = comun.getDateFromString(strFechaFin);

                    if (dtFechaHoy.compareTo(dtFechaFin) == 0){
                        strIdNuevoEstado = "FN";
                    }
                }

                if (!(strIdNuevoEstado.equals(""))){
                    strSQL = "update nomina.tbl_contratos set txtIdEstado = '" + strIdNuevoEstado + "' where txtConsecutivo = '" + strConsecutivo + "'";
                    strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");

                    if (strMensaje != null){
                        Log.registroTraza("Job: CambiarEstadoContrato. Error al cambiar el estado del contrato con consecutivo " + strConsecutivo);
                    }else{
                        Log.registroTraza("Job: CambiarEstadoContrato. Contrato #" + strConsecutivo + ". Cambio de estado: " + strIdEstado + " -> " + strIdNuevoEstado);
                        intTotal = intTotal +1;
                    }
                }            
            } 
        }
        Log.registroTraza("Job: Finaliza CambiarEstadoContrato. Contratos modificados: " + intTotal);    
    }
}
