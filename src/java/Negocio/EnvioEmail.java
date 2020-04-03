/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Conexion.GestionSQL;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jorge.correa
 */

public class EnvioEmail {
    private String mailSMTPServer;
    private String mailSMTPServerPort;
    private String mailSenha;
    
    
    public void sendMail(String to, String subject, String message){

        Properties props = new Properties();      
        String from = null;     
        String strSQL = "";
        String[] strDatosGenerales = null;                
        
        strSQL = "select txtNomServidor, txtNumPuerto, txtUsuario, txtPassword from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";    
        strDatosGenerales = GestionSQL.getFila(strSQL,"Nomina");
                
        if (strDatosGenerales == null){
            Log.registroTraza("Error: Recuperando los parámetros, no se encuentran diligenciados en el documento de configuración general.");
        }else{
                        
            mailSMTPServer = strDatosGenerales[0].trim();
            mailSMTPServerPort = strDatosGenerales[1].trim();
            mailSenha = strDatosGenerales[3].trim();
            from = strDatosGenerales[2].trim();           
                       
            props.put("mail.transport.protocol","smtp");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host",mailSMTPServer);        
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.user",from);
            props.put("mail.smtp.debug","true");       
            props.put("mail.smtp.port",mailSMTPServerPort);                       
            props.put("mail.smtp.socketFactory.port",mailSMTPServerPort);            
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback","false");            
                        
            SimpleAuth auth = new SimpleAuth(from,mailSenha);           
                                                            
            Session session = Session.getDefaultInstance(props,auth);
            session.setDebug(false);
                        
            Message msg = new MimeMessage(session);

            try{
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                msg.setFrom(new InternetAddress(from));
                msg.setSubject(subject);            
                msg.setText(message);

            }catch(Exception e){
                Log.registroTraza("Error: Completar Mensaje" + e.getMessage());
            }

            Transport tr;
            try{
                tr = session.getTransport("smtp");
                tr.connect(mailSMTPServer,from,mailSenha);
                msg.saveChanges();
                tr.sendMessage(msg, msg.getAllRecipients());
                tr.close();
            }catch(Exception e){
                Log.registroTraza("Error: Enviando Mensaje. " + e.getMessage());
            }
        }               
    }
}

