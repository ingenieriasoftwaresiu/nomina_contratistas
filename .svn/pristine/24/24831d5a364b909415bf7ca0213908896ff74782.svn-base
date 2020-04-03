/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import Negocio.Log;
import Conexion.GestionSQL;
import Negocio.Comunes;
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
public class CambiarEstadoPago implements Job {
    public void execute(JobExecutionContext jec) throws JobExecutionException {
      
        // Aca pueden poner la tarea o el job que desean automatizar.
        // Por ejemplo enviar correo, revisar ciertos datos, etc.
            
        Log.registroTraza("Job: Inicia CambiarEstadoPago");
        
        String strSQL, strFechaHoy="", strConsecutivo="", strNumPago="", strFechaPago="", strIdEstado="", strIdNuevoEstado="",strMensaje=null;
        String[] strTemp = null;        
        Date dtFechaHoy = null, dtFechaPago=null;
        Comunes comun = new Comunes();
        int intTotal=0;
        
        // Obtener la fecha actual.
        
        strFechaHoy = comun.getFechaActual();
        dtFechaHoy = comun.getDateFromString(strFechaHoy);
        
        strSQL = "select p.txtIdContrato, p.txtNumPago, p.dtFechaPago, p.txtIdEstadoPago from nomina.tbl_plan_pagos p where p.txtIdEstadoPago = 'A' ORDER BY p.txtIdContrato, p.txtNumPago";
        Vector arrPagos = GestionSQL.consultaSQL(strSQL, "Nomina", "4");
                
        if (arrPagos != null){
            for (int i=0;i<arrPagos.size();i++){
                strTemp = arrPagos.get(i).toString().split(">");
                strConsecutivo = strTemp[0];
                strNumPago =  strTemp[1];      
                strFechaPago = strTemp[2];     
                strIdEstado = strTemp[3];     
                strIdNuevoEstado = "";
                strMensaje = null;

                if (strIdEstado.equals("A")){
                    dtFechaPago = comun.getDateFromString(strFechaPago);

                    if (dtFechaHoy.compareTo(dtFechaPago) == 0){           
                        strIdNuevoEstado = "E";
                    }        
                }            

                if (!(strIdNuevoEstado.equals(""))){
                    strSQL = "update nomina.tbl_plan_pagos set txtIdEstadoPago = '" + strIdNuevoEstado + "' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strNumPago +"'";              
                    strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");

                    if (strMensaje != null){
                        Log.registroTraza("Job: CambiarEstadoPago. Error al cambiar el estado del pago " + strNumPago + " para el contrato con consecutivo " + strConsecutivo);
                    }else{
                        Log.registroTraza("Job: CambiarEstadoPago. Contrato #" + strConsecutivo + " y pago #" + strNumPago + ". Cambio de estado: " + comun.validarEstado(strIdEstado) + " -> " + comun.validarEstado(strIdNuevoEstado));
                        intTotal = intTotal +1;
                    }
                }            
            }   
        }
        Log.registroTraza("Job: Finaliza CambiarEstadoPago. Pagos modificados: " + intTotal);    
    }
}
