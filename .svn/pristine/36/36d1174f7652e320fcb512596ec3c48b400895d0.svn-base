/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Conexion.GestionSQL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jorge.correa
 */
public class ComunesRegistro {
    
    public String obtenerStringFechaActual(){
        //Obtener la fecha actual.

        Calendar c = Calendar.getInstance();
        String strDia, strMes, strAnio, strFechaActual;

        strDia = Integer.toString(c.get(Calendar.DATE));
        strMes = Integer.toString(c.get(Calendar.MONTH)+1);
        strAnio = Integer.toString(c.get(Calendar.YEAR));                

        if (Integer.parseInt(strMes) < 10){
                strMes = "0" + strMes;
        }                    

        strFechaActual = strAnio + "-" + strMes + "-" + strDia;                        
       
        return strFechaActual;        
    }
    
    public Date obtenerDateFechaActual(){
        //Obtener la fecha actual.

        Calendar c = Calendar.getInstance();
        String strDia, strMes, strAnio, strFechaActual;

        strDia = Integer.toString(c.get(Calendar.DATE));
        strMes = Integer.toString(c.get(Calendar.MONTH)+1);
        strAnio = Integer.toString(c.get(Calendar.YEAR));                

        if (Integer.parseInt(strMes) < 10){
                strMes = "0" + strMes;
        }                    

        strFechaActual = strAnio + "-" + strMes + "-" + strDia;                        
        SimpleDateFormat sdfFechaActual = new SimpleDateFormat("yyyy-MM-dd");
        Date dtFechaActual = null;

        try{
                dtFechaActual = sdfFechaActual.parse(strFechaActual);
        }catch(ParseException pe){
                pe.printStackTrace();
        }

        return dtFechaActual;        
    }
    
    public void agregarInterventor(String strIdInterventor){
        String strSQL, strMensaje=null;
        String[] strTemp=null;
        
        strSQL = "select ixp.txtIdInterventor from nomina.tbl_interventores_x_proyecto ixp where ixp.txtIdInterventor = '" + strIdInterventor + "'";
        strTemp = GestionSQL.getFila(strSQL, "Nomina");
        
        if (strTemp == null){
            strSQL = "INSERT INTO nomina.tbl_interventores_x_proyecto VALUES('" + strIdInterventor + "','-')";
            strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");
            
            if (strMensaje != null){
                Log.registroTraza("AgregarInterventor: " + strMensaje);
            }        
        }
    }
    
    public String validarRegistro(String strClave,String strForm){
        
        String[] strResult = null;
        String strSQL = null;
        String strMensaje = null;
        
        // OTROSI.
        
        if (strForm.equals("frmOTROSI")){
                
                String strConsecutivo=null, strFechaFinNueva=null;
                
                String[] strTemp = strClave.split(">");
                strConsecutivo = strTemp[0];
                strFechaFinNueva = strTemp[1];
            
                strSQL = "SELECT txtCodigo FROM  nomina.tbl_otrosi_contratos WHERE txtIdContrato = '" + strConsecutivo + "' and dtFechaFinal = '" + strFechaFinNueva + "'";
                strMensaje = "<html>\n";
                strMensaje = strMensaje + "<head>\n";
                strMensaje = strMensaje + "<body>\n";
                strMensaje = strMensaje + "<div class='TEXTOFALLO'>\n";
                strMensaje = strMensaje + "Ya existe un registro de OTROSI para la fecha " + strFechaFinNueva + ". Por favor ingrese una fecha diferente.\n";
                strMensaje = strMensaje + "</div>\n";
                strMensaje = strMensaje + "</body>\n";
                strMensaje = strMensaje + "</html>";
       }
        
        // Estados.
        
       if (strForm.equals("frmEstado")){
                strSQL = "SELECT tbl.txtCodigo from nomina.tbl_estados_contrato tbl where tbl.txtCodigo = '" + strClave + "'";
                strMensaje = "<html>\n";
                strMensaje = strMensaje + "<head>\n";
                strMensaje = strMensaje + "<body>\n";
                strMensaje = strMensaje + "<div class='TEXTOFALLO'>\n";
                strMensaje = strMensaje + "Ya existe un registro de estado con el código " + strClave + ". Por favor ingrese un código diferente.\n";
                strMensaje = strMensaje + "</div>\n";
                strMensaje = strMensaje + "</body>\n";
                strMensaje = strMensaje + "</html>";
       }    
        
        // Roles.
        
        if (strForm.equals("frmRol")){
                strSQL = "SELECT tbl.txtCodigo from nomina.tbl_roles tbl where tbl.txtCodigo = '" + strClave + "'";
                strMensaje = "<html>\n";
                strMensaje = strMensaje + "<head>\n";
                strMensaje = strMensaje + "<body>\n";
                strMensaje = strMensaje + "<div class='TEXTOFALLO'>\n";
                strMensaje = strMensaje + "Ya existe un registro de rol con el código " + strClave + ". Por favor ingrese un código diferente.\n";
                strMensaje = strMensaje + "</div>\n";
                strMensaje = strMensaje + "</body>\n";
                strMensaje = strMensaje + "</html>";  
        }       
               
         // Rol por persona.
                
         if (strForm.equals("frmRolXPersona")){
                String[] strDatos = strClave.split(">");
             
                strSQL = "SELECT tbl.txtIdPersona from nomina.tbl_roles_x_persona tbl where (tbl.txtIdPersona = '" + strDatos[1] + "' and tbl.txtIdRol = '" + strDatos[0] + "')";
                strMensaje = "<html>\n";
                strMensaje = strMensaje + "<head>\n";
                strMensaje = strMensaje + "<body>\n";
                strMensaje = strMensaje + "<div class='TEXTOFALLO'>\n";
                strMensaje = strMensaje + "Ya existe un registro de rol por persona con los valores seleccionados. Por favor seleccione valores diferentes.\n";
                strMensaje = strMensaje + "</div>\n";
                strMensaje = strMensaje + "</body>\n";
                strMensaje = strMensaje + "</html>";  
        } 
         
         // Tipos de contrato.
        
        if (strForm.equals("frmTipoC")){
                strSQL = "SELECT tbl.txtCodigo from nomina.tbl_tipos_contrato tbl where tbl.txtCodigo = '" + strClave + "'";
                strMensaje = "<html>\n";
                strMensaje = strMensaje + "<head>\n";
                strMensaje = strMensaje + "<body>\n";
                strMensaje = strMensaje + "<div class='TEXTOFALLO'>\n";
                strMensaje = strMensaje + "Ya existe un registro de tipo de contrato con el código " + strClave + ". Por favor ingrese un código diferente.\n";
                strMensaje = strMensaje + "</div>\n";
                strMensaje = strMensaje + "</body>\n";
                strMensaje = strMensaje + "</html>";  
        } 
        
        // Contratistas.
        
        if (strForm.equals("frmContratista")){
                strSQL = "select tbl.txtNumId from nomina.tbl_contratistas tbl where tbl.txtNumId = '" + strClave + "'";
                strMensaje = "<html>\n";
                strMensaje = strMensaje + "<head>\n";
                strMensaje = strMensaje + "<body>\n";
                strMensaje = strMensaje + "<div class='TEXTOFALLO'>\n";
                strMensaje = strMensaje + "Ya existe un registro de contratista con el código " + strClave + ". Por favor ingrese un código diferente.\n";
                strMensaje = strMensaje + "</div>\n";
                strMensaje = strMensaje + "</body>\n";
                strMensaje = strMensaje + "</html>";  
        }
                        
                
        // Interventores.
        
        if (strForm.equals("frmInterventor")){
                strSQL = "select p.txtIdentificacion from users.users_personas p where p.txtIdentificacion = '" + strClave + "'";
                strMensaje = "<html>\n";
                strMensaje = strMensaje + "<head>\n";
                strMensaje = strMensaje + "<body>\n";
                strMensaje = strMensaje + "<div class='TEXTOFALLO'>\n";
                strMensaje = strMensaje + "Ya existe un registro de interventor con el código " + strClave + ". Por favor ingrese un código diferente.\n";
                strMensaje = strMensaje + "</div>\n";
                strMensaje = strMensaje + "</body>\n";
                strMensaje = strMensaje + "</html>";  
        }
        
        // Contratos.
        
        if (strForm.equals("frmContrato")){
                strSQL = "select tbl.txtConsecutivo from nomina.tbl_contratos tbl where tbl.txtConsecutivo = '" + strClave + "'";
                strMensaje = "<html>\n";
                strMensaje = strMensaje + "<head>\n";
                strMensaje = strMensaje + "<body>\n";
                strMensaje = strMensaje + "<div class='TEXTOFALLO'>\n";
                strMensaje = strMensaje + "Ya existe un registro de contrato con el código " + strClave + ". Por favor ingrese un código diferente.\n";
                strMensaje = strMensaje + "</div>\n";
                strMensaje = strMensaje + "</body>\n";
                strMensaje = strMensaje + "</html>";  
        }
         
        if (strSQL != null){
            strResult = GestionSQL.getFila(strSQL,"Nomina");
            
            if (strResult != null){
                return strMensaje;
            }else{
                 return null;
            }
        }else{
            return null;
        }                
    }
}
