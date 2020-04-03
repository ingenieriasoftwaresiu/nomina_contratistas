/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Conexion.GestionSQL;
import DTO.ContratosAVencerse;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author jorge.correa
 */
public class Notificacion {
    
    public EnvioEmail mail=null;
    public ValoresContrato valores = null;
    
    public Notificacion(){               
        this.mail = new EnvioEmail();            
        this.valores = new ValoresContrato();
    }
    
    public void notificarTransaccion(String strIdContrato, String strNumPago, String strAccion, String strObs){
        
        final String strMsgGuardarI = "El pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato + " ha sido actualizado por el Contratista. Para verificarlo ingrese al sistema con las credenciales asignadas.\n";
        final String strMsgPreAprobarA = "El pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato + " ha sido preaprobado por el Interventor. Para verificarlo ingrese al sistema con las credenciales asignadas..\n";
        final String strMsgPreAprobarC = "El pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato + " ha sido preaprobado por el Interventor.\n";
        final String strMsgReprocesarC = "El pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato + " ha sido reprocesado por el Interventor. Para verificarlo ingrese al sistema con las credenciales asignadas.\n";
        final String strMsgReprocesarI = "El pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato + " ha sido reprocesado por el Administrador del Sistema. Para verificarlo ingrese al sistema con las credenciales asignadas.\n";
        final String strMsgAprobarC = "El pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato + " ha sido aprobado por el Administrador del Sistema.\n";
        final String strMsgAprobarI = "El pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato + " ha sido aprobado por el Administrador del Sistema.\n";
        String strMsg = "", strAsunto = "", strDestino=null, strPie="";
        List<String[]> datosPersona =null;
        
        strPie += "Atentamente,\n\n";
        strPie += "Sistema de Gestión de Pagos a Contratistas\n";
        strPie += "Administración SIU";
                                 
        if (strAccion.equals("GUARDAR")){            
            strAsunto = "Actualización del pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato;
            datosPersona = this.valores.obtenerDatosNotificacion(strIdContrato, "I");
            
            if (datosPersona != null){
                for(String[] datos : datosPersona){
                    strMsg += "Cordial saludo Sr(a). " + datos[0].toString() + ".\n\n"; 
                    strMsg += strMsgGuardarI + "\n";

                    if ((!(strObs.equals(""))) && (!(strObs.equals("-")))){
                        strMsg += "La observación agregada por el Contratista fue: '" + strObs + "'.\n\n";
                    }

                    strMsg += strPie + "\n";
                    strDestino = datos[1].toString();
                    
                    mail.sendMail(strDestino, strAsunto, strMsg);

                    strMsg = "";
                    strDestino = null;
                    datos = null;
                }
            }            
        }
        
        if (strAccion.equals("APROBAR")){
            strAsunto = "Aprobación del pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato;
            datosPersona = this.valores.obtenerDatosNotificacion(strIdContrato, "I");
                        
            if (datosPersona != null){
                for(String[] datos : datosPersona){
                    strMsg += "Cordial saludo Sr(a). " + datos[0].toString() + ".\n\n"; 
                    strMsg += strMsgAprobarI + "\n";

                    if ((!(strObs.equals(""))) && (!(strObs.equals("-")))){
                        strMsg += "La observación agregada por el Administrador fue: '" + strObs + "'.\n\n";
                    }

                    strMsg += strPie + "\n";
                    strDestino = datos[1].toString();

                    mail.sendMail(strDestino, strAsunto, strMsg);

                    strMsg = "";
                    strDestino = null;
                    datos = null;
                }
            }
            
            datosPersona = null;
            datosPersona = this.valores.obtenerDatosNotificacion(strIdContrato, "C");
                        
            if (datosPersona != null){
                for(String[] datos : datosPersona){
                    strMsg += "Cordial saludo Sr(a). " + datos[0].toString() + ".\n\n"; 
                    strMsg += strMsgAprobarC + "\n";

                    if ((!(strObs.equals(""))) && (!(strObs.equals("-")))){
                        strMsg += "La observación agregada por el Administrador fue: '" + strObs + "'.\n\n";
                    }

                    strMsg += strPie + "\n";
                    strDestino = datos[1].toString();

                    mail.sendMail(strDestino, strAsunto, strMsg);

                    strMsg = "";
                    strDestino = null;
                    datos = null;
                }
            }                        
        }
        
        if (strAccion.equals("PREAPROBAR")){            
            strAsunto = "Preaprobación del pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato;
            datosPersona = this.valores.obtenerDatosNotificacion(strIdContrato, "A");
            
            if (datosPersona != null){
                for(String[] datos : datosPersona){
                    strMsg += "Cordial saludo Sr(a). " + datos[0].toString() + ".\n\n"; 
                    strMsg += strMsgPreAprobarA+ "\n";

                    if ((!(strObs.equals(""))) && (!(strObs.equals("-")))){
                        strMsg += "La observación agregada por el Interventor fue: '" + strObs + "'.\n\n";
                    }

                    strMsg += strPie + "\n";
                    strDestino = datos[1].toString();

                    mail.sendMail(strDestino, strAsunto, strMsg);

                    strMsg = "";
                    strDestino = null;
                    datos = null;
                }
            }
            
            datosPersona = null;
            datosPersona = this.valores.obtenerDatosNotificacion(strIdContrato, "C");
            
            if (datosPersona != null){
                for(String[] datos : datosPersona){
                    strMsg += "Cordial saludo Sr(a). " + datos[0].toString() + ".\n\n"; 
                    strMsg += strMsgPreAprobarC+ "\n";

                    if ((!(strObs.equals(""))) && (!(strObs.equals("-")))){
                        strMsg += "La observación agregada por el Interventor fue: '" + strObs + "'.\n\n";
                    }

                    strMsg += strPie + "\n";
                    strDestino = datos[1].toString();

                    mail.sendMail(strDestino, strAsunto, strMsg);

                    strMsg = "";
                    strDestino = null;
                    datos = null;
                }
            }            
            
        }
        
        if (strAccion.equals("REPROCESARI")){
            strAsunto = "Reproceso del pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato;
            datosPersona = this.valores.obtenerDatosNotificacion(strIdContrato, "C");
            
            if (datosPersona != null){
                for(String[] datos : datosPersona){
                    strMsg += "Cordial saludo Sr(a). " + datos[0].toString() + ".\n\n"; 
                    strMsg += strMsgReprocesarC + "\n";

                    if ((!(strObs.equals(""))) && (!(strObs.equals("-")))){
                        strMsg += "La observación agregada por el Interventor fue: '" + strObs + "'.\n\n";
                    }

                    strMsg += strPie + "\n";
                    strDestino = datos[1].toString();

                    mail.sendMail(strDestino, strAsunto, strMsg);

                    strMsg = "";
                    strDestino = null;
                    datos = null;
                }
            }            
        }
        
        if (strAccion.equals("REPROCESARC")){
            strAsunto = "Reproceso del pago #" + strNumPago + " del contrato con consecutivo " + strIdContrato;
            datosPersona = this.valores.obtenerDatosNotificacion(strIdContrato, "I");
            
            if (datosPersona != null){
                for(String[] datos : datosPersona){
                    strMsg += "Cordial saludo Sr(a). " + datos[0].toString() + ".\n\n"; 
                    strMsg += strMsgReprocesarI + "\n";

                    if ((!(strObs.equals(""))) && (!(strObs.equals("-")))){
                        strMsg += "La observación agregada por el Administrador fue: '" + strObs + "'.\n\n";
                    }

                    strMsg += strPie + "\n";
                    strDestino = datos[1].toString();

                    mail.sendMail(strDestino, strAsunto, strMsg);

                    strMsg = "";
                    strDestino = null;
                    datos = null;
                }
            }
        }
        
        if (strAccion.equals("EJECUTAR")){
            // Pendiente por verificar si se impementa.
        }
        
    }
    
    public void notificarContratosAVencerseAdministrador(List<ContratosAVencerse> contratosAVencerse){
        // Buscar información de los Administradores.
        
        String strSQL = null, strMensaje="", strDestino="", strAsunto="", strNombre=null, strContratos="";
        String[] strTemp = null;
        Integer intContador = null;
        Vector arrDestinatarios = null;                
        
        arrDestinatarios = this.valores.obtenerAdministradores();
                
        if (arrDestinatarios != null){
            
            strAsunto = "ALERTA: Contratos próximos a finalizar";
            
            intContador = 1;
            for (ContratosAVencerse contratoAVencerse : contratosAVencerse){
                    strContratos = strContratos + "CONTRATO #" + intContador + ".\n";
                    strContratos = strContratos + "- Nro. de consecutivo: " + contratoAVencerse.getConsecutivo() + ".\n";
                    strContratos = strContratos + "- Nombre del contratista: " + contratoAVencerse.getContratista() + ".\n";
                    strContratos = strContratos + "- Grupo: " + contratoAVencerse.getGrupo() + ".\n";
                    strContratos = strContratos + "- Fecha de finalización (aaaa-mm-dd): " + contratoAVencerse.getFechaFin() + ".\n";
                    strContratos = strContratos + "- Tiempo restante para finalización: " + contratoAVencerse.getTiempoRestante() + " días.\n\n";
                    intContador++;
            }
                                    
            for (int i=0; i<arrDestinatarios.size();i++){
                strTemp = arrDestinatarios.get(i).toString().split(">");
                strNombre = strTemp[0];
                strDestino = strTemp[1];
                
                strMensaje = strMensaje + "Cordial saludo Sr(a). " + strNombre + ".\n\n";
                strMensaje = strMensaje + "Los siguientes contratos se encuentran próximos a cumplir su fecha de finalización:\n\n";
                
                if (strContratos.equals("")){
                    strMensaje = strMensaje +  "No se tienen contratos próximos a finalizar.\n\n";
                }else{
                    strMensaje = strMensaje +  strContratos;
                }
                
                strMensaje = strMensaje + "Atentamente,\n\n";
                strMensaje = strMensaje + "Sistema de Gestión de Pagos a Contratistas\n";
                strMensaje = strMensaje + "Administración de la SIU";
                                               
                if (!(strDestino.equals(""))){
                    mail.sendMail(strDestino, strAsunto, strMensaje); 
                }else{                   
                    Log.registroTraza("El Administrador " + strNombre + " no tiene un correo electrónico configurado.");                                   
                }
                                
                strTemp = null;
                strNombre = null;
                strDestino = null;
                strMensaje = "";
            }                                
        }                
    }
    
    public void notificarContratosAVencerseInterventor(List<ContratosAVencerse> contratosAVencerse, String strIdInterventor){
        // Buscar información del Interventor.
        
        String strSQL = null, strMensaje="", strDestino="", strAsunto="", strNombre=null, strContratos="";
        String[] strDestinatario=null;
        Integer intContador = null;
                        
        strSQL = "select txtNombreCompleto, txtEmailC from users.users_personas where txtIdentificacion = '" + strIdInterventor + "'";
        strDestinatario = GestionSQL.getFila(strSQL, "Nomina");             
                
        if (strDestinatario != null){
            
            strAsunto = "ALERTA: Contratos próximos a finalizar";
            
            intContador = 1;
            for (ContratosAVencerse contratoAVencerse : contratosAVencerse){
                    strContratos = strContratos + "CONTRATO #" + intContador + ".\n";
                    strContratos = strContratos + "- Nro. de consecutivo: " + contratoAVencerse.getConsecutivo() + ".\n";
                    strContratos = strContratos + "- Nombre del contratista: " + contratoAVencerse.getContratista() + ".\n";
                    strContratos = strContratos + "- Grupo: " + contratoAVencerse.getGrupo() + ".\n";
                    strContratos = strContratos + "- Fecha de finalización (aaaa-mm-dd): " + contratoAVencerse.getFechaFin() + ".\n";
                    strContratos = strContratos + "- Tiempo restante para finalización: " + contratoAVencerse.getTiempoRestante() + " días.\n\n";
                    intContador++;
            }                        
                                                
            strNombre = strDestinatario[0];
            strDestino = strDestinatario[1];

            strMensaje = strMensaje + "Cordial saludo Sr(a). " + strNombre + ".\n\n";
            strMensaje = strMensaje + "Los siguientes contratos se encuentran próximos a cumplir su fecha de finalización:\n\n";

            if (strContratos.equals("")){
                strMensaje = strMensaje +  "No se tienen contratos próximos a finalizar.\n\n";
            }else{
                strMensaje = strMensaje +  strContratos;
            }

            strMensaje = strMensaje + "Atentamente,\n\n";
            strMensaje = strMensaje + "Sistema de Gestión de Pagos a Contratistas\n";
            strMensaje = strMensaje + "Administración de la SIU";

            if (!(strDestino.equals(""))){
                mail.sendMail(strDestino, strAsunto, strMensaje); 
            }else{                   
                Log.registroTraza("El Interventor " + strNombre + " no tiene un correo electrónico configurado.");                                   
            }                                                                     
        }                
    }
}
