/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Conexion.GestionSQL;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge.correa
 */
public class PagosContrato {
    
    private String strSQL;
    private Comunes comun;
    public PagosContrato(){
        strSQL= "";
        comun = new Comunes();
    }
    
    public void crearPlanPagos(String strConsecutivo){
        
        String strAnio, strMes, strDia, strMensaje;
        int intNumPagos=0;
        double dblDuracionDias, dblPorcBaseDias, dblPorcBaseMes, dblNumPagos=0, dblPorcAPagar=0, dblAcumulado=0;
        String strFechaInicio = "", strFechaBase="", strFechaPrimera="", strFechaUltima="";
        final double dblPorcMax = 100.00;
        String strIdEstado = "P";
        final String strValores = "0";
        final String strVacio = "-";
        long lgDiferencia=0; 
        Date dtFechaPago = null;
        double redondeador = 100;    
                
        strSQL = "select c.dtFechaInicio, c.txtDuracion, c.dtFechaFin from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
        String[] strDatos = GestionSQL.getFila(strSQL, "Nomina");
        
        strFechaInicio = strDatos[0];
        dblDuracionDias = Double.parseDouble(strDatos[1]);
        dblNumPagos = Math.floor(dblDuracionDias/30.00);        
        //dblPorcBase = Math.rint((dblPorcMax/dblNumPagos)*100)/100;
        dblPorcBaseMes = (double)(Math.round((dblPorcMax/dblNumPagos)*redondeador)/redondeador);
        //dblPorcBaseDias = dblPorcMax/dblDuracionDias;      
        dblPorcBaseDias = dblPorcBaseMes/30.00;    
        dblNumPagos = dblDuracionDias/30.00; 
        intNumPagos = (int) Math.ceil(dblNumPagos);
        Date dtFechaInicio = null;
        Date dtFechaFin = null;                      
                
        if (dblDuracionDias % 3 == 0){
            if (comun.getDiaFromFecha(strFechaInicio) > 1){
                intNumPagos = intNumPagos +1;
            }            
        }
                       
        for(int i=1;i<=intNumPagos ;i++){
            if (i == 1){     
                strFechaPrimera = strFechaInicio;
            }
                                               
            // Cálculo de la fecha de pago.            
      
            strAnio = strFechaPrimera.substring(0, 4);
            strMes = strFechaPrimera.substring(5, 7);
            strFechaUltima = comun.getUltimaDiaFecha(strAnio, strMes);                  
            
            // Cálculo de porcentaje a pagar en cada mes.
            
            dtFechaInicio = comun.getDateFromString(strFechaPrimera);            
            
            if(i<intNumPagos){
                dtFechaFin = comun.getDateFromString(strFechaUltima);                       
                lgDiferencia = comun.getDiasDiferenciaFechasEspecial(dtFechaInicio, dtFechaFin);
        
            }else{
                dtFechaFin = comun.getDateFromString(strDatos[2]);                      
                lgDiferencia = comun.getDiasDiferenciaFechasEspecial(dtFechaInicio, dtFechaFin);
                          
                if(lgDiferencia >=28){
                    lgDiferencia = 30;
                }
            }                        
                        
            if (lgDiferencia >= 30){                
                dblPorcAPagar = (double)(Math.round(dblPorcBaseMes*redondeador)/redondeador);                          
            }else{
                
                if (i==intNumPagos){
                    dblPorcAPagar = Math.round((100.00-dblAcumulado)*redondeador)/redondeador;
                }else{
                    //dblPorcAPagar = (double)((dblPorcBase/30.00)*(lgDiferencia + 1.00));           
                    dblPorcAPagar = (double)((dblPorcBaseDias)*(lgDiferencia));         
                    dblPorcAPagar = (Math.round(dblPorcAPagar*redondeador)/redondeador); 
                 }                 
            }            
           
            if ((lgDiferencia >= 0) && (dblPorcAPagar > 0)){
                // Creación del registro de pago.
                                
                if (dblAcumulado + dblPorcAPagar > 99){
                    strIdEstado = "PA";
                }
                                             
                strSQL = "insert into nomina.tbl_plan_pagos values('" + strConsecutivo + "','" + i + "','" + strFechaUltima + "','" +String.valueOf(dblPorcAPagar) + "','" + strValores + "','" + strValores + "','" + strValores + "','" +strIdEstado + "','" +strVacio +"','" + strVacio + "')";
                strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");  

                if (strMensaje != null){          
                    Log.registroTraza("Se generó un error al crear el pago " + i + " para el contrato con consecutivo " + strConsecutivo + ".");
                }
            }            
            
            strFechaPrimera = comun.aumentarDiasFecha(strFechaUltima, 1);
            dblAcumulado = dblAcumulado + dblPorcAPagar;            
        }
    }    
        
    public void crearHistoricoPago(String strIdContrato, String strNumPago, String strTipoUsuario, String strAccion, String strObs){
        String strMensaje=null, strFechaHora="", strSQL="";
        String[] strEstadoPago=null;
        
        strSQL = "SELECT txtIdEstadoPago FROM nomina.tbl_plan_pagos WHERE txtIdContrato = '" + strIdContrato + "' AND txtNumPago = '" + strNumPago + "'";
        strEstadoPago = GestionSQL.getFila(strSQL, "Nomina");
        
        if ((strObs.equals("")) || (strObs.equals("null")) || (strObs == null)){
            strObs = "-";
        }
         
        strFechaHora = comun.getFechaHoraActual();             
        strSQL = "insert into nomina.tbl_historico_pagos(txtIdContrato,txtNumPago,txtIdAutor,dtFecha,txtAccion,txtIdEstadoActual,txtObs) values('" + strIdContrato + "','" + strNumPago + "','" + strTipoUsuario + "','" + strFechaHora + "','" + strAccion + "','" + strEstadoPago[0] + "','" + strObs + "')";
        strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");
               
        if(strMensaje != null){
            Log.registroTraza("Se generó un error al crear el historico del pago " + strNumPago + " para el contrato #" + strIdContrato + ": " + System.err);
        }                                    
    }
            
    /*public void crearPlanPagosOld(String strConsecutivo){
        
        String strSQL= "", strAnio, strMes, strDia, strMensaje;
        int intNumPagos=0;
        double dblDuracionDias, dblPorcBase, dblNumPagos=0, dblPorcAPagar=0, dblAcumulado=0;
        String strFechaInicio = "", strFechaBase="", strFechaPrimera="";
        final double dblPorcMax = 100;
        final String strIdEstado = "P";
        final String strValores = "0";
        final String strVacio = "-";
        long lgDiferencia=0;
        Date dtFechaPago = null;
        Comunes comun = new Comunes();
        
        strSQL = "select c.dtFechaInicio, c.txtDuracion, c.dtFechaFin from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
        String[] strDatos = GestionSQL.getFila(strSQL, "Nomina");
        
        strFechaInicio = strDatos[0];
        dblDuracionDias = Double.parseDouble(strDatos[1]);
        dblNumPagos = dblDuracionDias/30;        
        dblPorcBase = comun.redondear(dblPorcMax/dblNumPagos,2);
        intNumPagos = (int) Math.ceil(dblNumPagos);
                
        for(int i=1;i<=intNumPagos || dblAcumulado < 99;i++){
            if (i == 1){
                strFechaBase = strFechaInicio;
                strFechaPrimera = strFechaInicio;
            }else{         
                strFechaPrimera = strFechaBase;
            }
            
            // Cálculo de la fecha de pago.            
      
            strAnio = strFechaBase.substring(0, 4);
            strMes = strFechaBase.substring(5, 7);
            strFechaBase = comun.getUltimaDiaFecha(strAnio, strMes);                  
            
            // Cálculo de porcentaje a pagar en cada mes.
            
            Date dtFechaInicio = comun.getDateFromString(strFechaPrimera);
            Date dtFechaFin = comun.getDateFromString(strFechaBase);                 
            
            lgDiferencia = comun.getDiasDiferenciaFechasEspecial(dtFechaInicio, dtFechaFin);
            
            if (lgDiferencia >= 30){                
                dblPorcAPagar = Math.rint(dblPorcBase*100)/100;
            }else{
                dblPorcAPagar = (double)((dblPorcBase/30)*(lgDiferencia + 1));
                dblPorcAPagar = Math.rint(dblPorcAPagar*100)/100; 
            }
            
            System.out.println("Diferencia " + i + ": " + comun.getDiasDiferenciaFechasEspecial(dtFechaInicio, dtFechaFin));
                                    
            // Creación del registro de pago.
            strSQL = "insert into nomina.tbl_plan_pagos values('" + strConsecutivo + "','" + i + "','" + strFechaBase + "','" +String.valueOf(dblPorcAPagar) + "','" + strValores + "','" + strValores + "','" + strValores + "','" +strIdEstado + "','" +strVacio +"','" + strVacio +"')";
            strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                
            if (strMensaje != null){          
                System.out.println("Se generó un error al crear el pago " + i + " para el contrato con consecutivo " + strConsecutivo + ".");
            }
            
            strFechaBase = comun.aumentarDiasFecha(strFechaBase, 1);
            dblAcumulado = dblAcumulado + dblPorcAPagar;             
        }
    }*/
}
