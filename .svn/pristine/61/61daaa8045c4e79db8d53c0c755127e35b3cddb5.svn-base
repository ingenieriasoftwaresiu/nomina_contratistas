/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Conexion.GestionSQL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author jorge.correa
 */
public class ValoresContrato {
    
    private String strSQL;
    private String[] strTemp;
    
    public ValoresContrato(){
        this.strSQL = null;
        this.strTemp = null;
    }
    
    public String obtenerContratista(String strIdContratista){
        String strContratista = null;
        
        this.strSQL = "SELECT CONCAT(txtApellidos,' ',txtNombres) from nomina.tbl_contratistas where txtNumId = '" + strIdContratista + "'";
        this.strTemp = GestionSQL.getFila(this.strSQL, "Nomina");
        
        if (this.strTemp != null){
            strContratista = this.strTemp[0];
        }
        
        return strContratista;
    }
    
     public String obtenerCorreoContratista(String strIdContratista){
        String strCorreo = "";
        
        this.strSQL = "SELECT txtCorreoElectronico from nomina.tbl_contratistas where txtNumId = '" + strIdContratista + "'";
        this.strTemp = GestionSQL.getFila(this.strSQL, "Nomina");
        
        if (this.strTemp != null){
            strCorreo = this.strTemp[0];
        }
        
        return strCorreo;
    }
    
    public String obtenerInterventor(String strIdInterventor){
        String strInterventor = null;
        
        this.strSQL = "SELECT txtNombreCompleto from users.users_personas where txtIdentificacion = '" + strIdInterventor + "'";
        this.strTemp = GestionSQL.getFila(this.strSQL, "Nomina");
        
        if (this.strTemp != null){
            strInterventor = this.strTemp[0];
        }
        
        return strInterventor;
    }
    
    public String obtenerTipoContrato(String strIdTipoContrato){
        String strTipoContrato = null;
        
        this.strSQL = "SELECT txtNombre from nomina.tbl_tipos_contrato WHERE txtCodigo = '" + strIdTipoContrato + "'";
        this.strTemp = GestionSQL.getFila(this.strSQL, "Nomina");
        
        if (this.strTemp != null){
            strTipoContrato = this.strTemp[0];
        }
        
        return strTipoContrato;
    }
    
    public String obtenerGrupo(String strIdGrupo){
        String strGrupo = null;
        
        this.strSQL = "SELECT txtNombre FROM users.users_grupos_siu WHERE txtCodigo = '" + strIdGrupo + "'";
        this.strTemp = GestionSQL.getFila(this.strSQL, "Nomina");
        
        if (this.strTemp != null){
            strGrupo = this.strTemp[0];
        }
        
        return strGrupo;
    }
    
    public String obtenerEstado(String strIdEstado){
        String strEstado = null;        
                
        this.strSQL = "SELECT txtNombre FROM nomina.tbl_estados_contrato WHERE txtCodigo = '" + strIdEstado + "'";
        this.strTemp = GestionSQL.getFila(this.strSQL, "Nomina");
        
        if (this.strTemp != null){
            strEstado = this.strTemp[0];
        }
        
        return strEstado;
    }
    
    public Vector obtenerAdministradores(){
        Vector arrAdministradores = null;
        
        strSQL = "SELECT p.txtNombreCompleto, p.txtEmailC FROM nomina.tbl_roles_x_persona rxp, users.users_personas p WHERE (p.txtIdentificacion = rxp.txtIdPersona) and rxp.txtIdRol = 'ADM' ORDER BY p.txtNombreCompleto";
        arrAdministradores = GestionSQL.consultaSQL(strSQL, "Nomina", "2");          
        
        return arrAdministradores;
    }
    
    public String obtenerVacio(String strValor){
        String strSalida = "";
        
        if ((strValor.equals("null")) || (strValor.trim().equals(""))){
            strSalida = "-";
        }else{
            strSalida = strValor.trim();
        }
        
        return strSalida;
    }
    
    public List<String[]> obtenerDatosNotificacion(String strIdContrato, String strTipoUsuario){
        String[] strDatos = null;
        List<String[]> datos = new ArrayList<String[]>();
        String strSQL = null;
              
        if (strTipoUsuario.equals("A")){
            Vector arrDatos = obtenerAdministradores();
            
            for (int i=0; i<arrDatos.size();i++){
                strDatos = arrDatos.get(i).toString().split(">");
                datos.add(strDatos);
                strDatos = null;
            }
        }
        
        if (strTipoUsuario.equals("I")){
            strSQL = "SELECT p.txtNombreCompleto, p.txtEmailC FROM users.users_personas p WHERE p.txtIdentificacion = (SELECT c.txtIdInterventor FROM nomina.tbl_contratos c WHERE c.txtConsecutivo = '" + strIdContrato + "')";
            strDatos = GestionSQL.getFila(strSQL,"Nomina");
            datos.add(strDatos);
        }
        
        if (strTipoUsuario.equals("C")){
            strSQL = "SELECT CONCAT(c.txtNombres,' ',c.txtApellidos), c.txtCorreoElectronico FROM nomina.tbl_contratistas c WHERE c.txtNumId = (SELECT c.txtIdContratista FROM nomina.tbl_contratos c WHERE c.txtConsecutivo = '" + strIdContrato + "')";
            strDatos = GestionSQL.getFila(strSQL,"Nomina");
            datos.add(strDatos);
        }
        
        return datos;
    }
    
}
