/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Conexion.GestionSQL;
import Negocio.Comunes;
import Negocio.ComunesRegistro;
import Negocio.PagosContrato;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorge.correa
 */
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           String strForm = request.getParameter("txtForm");  
           String strAccion = request.getParameter("txtAccion"); 
           ComunesRegistro comunesRegistro = new ComunesRegistro();
           
           String strSQL = "";
           String strMensaje = "";
           String strResult = null;
           String strConsecutivo="";
           
           if (strForm.equals("frmRol")){

                String strCodigo = request.getParameter("txtCodigo");
                String strNombre = request.getParameter("txtNombre");               

                //Creación

                if (strAccion.equals("C")){   
                    //Validar duplicidad.
                    strResult = comunesRegistro.validarRegistro(strCodigo,strForm);           

                    if (strResult != null){
                        out.println(strResult);
                    }else{                
                        strSQL = "INSERT INTO nomina.tbl_roles (txtCodigo,txtNombre) VALUES('" + strCodigo + "','" + strNombre + "');";
                    }
                }
                
                if (strAccion.equals("V")){                             
                        strSQL = "update nomina.tbl_roles set txtNombre = '" + strNombre + "' where txtCodigo = '" +  strCodigo + "'";                                         
                }
           }
           
           if (strForm.equals("frmEstado")){

                String strCodigo = request.getParameter("txtCodigo");
                String strNombre = request.getParameter("txtNombre");               

                //Creación

                if (strAccion.equals("C")){   
                    //Validar duplicidad.
                    strResult = comunesRegistro.validarRegistro(strCodigo,strForm);           

                    if (strResult != null){
                        out.println(strResult);
                    }else{                
                        strSQL = "INSERT INTO nomina.tbl_estados_contrato (txtCodigo,txtNombre) VALUES('" + strCodigo + "','" + strNombre + "');";
                    }
                }
                
                if (strAccion.equals("V")){                             
                        strSQL = "update nomina.tbl_estados_contrato set txtNombre = '" + strNombre + "' where txtCodigo = '" +  strCodigo + "'";                                         
                }
           }
           
           if (strForm.equals("frmTipoC")){

                String strCodigo = request.getParameter("txtCodigo");
                String strNombre = request.getParameter("txtNombre");               

                //Creación

                if (strAccion.equals("C")){   
                    //Validar duplicidad.
                    strResult = comunesRegistro.validarRegistro(strCodigo,strForm);           

                    if (strResult != null){
                        out.println(strResult);
                    }else{                
                        strSQL = "INSERT INTO nomina.tbl_tipos_contrato (txtCodigo,txtNombre) VALUES('" + strCodigo + "','" + strNombre + "');";
                    }
                }
                
                if (strAccion.equals("V")){                             
                        strSQL = "update nomina.tbl_tipos_contrato set txtNombre = '" + strNombre + "' where txtCodigo = '" +  strCodigo + "'";                                         
                }
           }
           
           if (strForm.equals("frmRolXPersona")){

                String strIdRol = request.getParameter("txtIdRol");
                String strIdPersona = request.getParameter("txtIdPersona");               

                //Creación

                if (strAccion.equals("C")){   
                    //Validar duplicidad.
                    strResult = comunesRegistro.validarRegistro(strIdRol + ">" + strIdPersona,strForm);           

                    if (strResult != null){
                        out.println(strResult);
                    }else{                
                        strSQL = "INSERT INTO nomina.tbl_roles_x_persona (txtIdRol,txtIdPersona) VALUES('" + strIdRol + "','" + strIdPersona + "');";  
                    }
                }              
           }
           
          if (strForm.equals("frmGeneral")){
               String strNroPaginas = request.getParameter("txtNumRegPag");
               String strRutaCargaArchivo = request.getParameter("txtRutaArchivoCarga");
               String strNumDiasAlertaVencerse = request.getParameter("txtNumDiasAlertaVencerse");
               String strNombreServidor = request.getParameter("txtNombreServidor");
               String strNumeroPuerto = request.getParameter("txtNumeroPuerto");
               String strUsuario = request.getParameter("txtUsuario");
               String strPassword = request.getParameter("txtPassword");
               
               if (strAccion.equals("C")){                                                                         
                    strSQL = "INSERT INTO tbl_generales values('" + strForm + "','" + strNroPaginas + "','" + strRutaCargaArchivo + "','" + strNumDiasAlertaVencerse + "','" + strNombreServidor + "','" + strNumeroPuerto + "','" + strUsuario + "','" + strPassword + "');";
               } 
               
               if (strAccion.equals("V")){    
                    strSQL = "update tbl_generales set txtNumRegPag='" + strNroPaginas + "', txtRutaCargaArchivo = '" + strRutaCargaArchivo + "', txtNumDiasAlertaAVencerse = '" + strNumDiasAlertaVencerse + "', txtNomServidor = '" + strNombreServidor + "', txtNumPuerto = '" + strNumeroPuerto + "', txtUsuario = '" + strUsuario + "', txtPassword = '" + strPassword + "' where txtForm='" + strForm + "'";                    
                }
           }
          
           if (strForm.equals("frmContratista")){
               String strTipoId = request.getParameter("txtTipoId");
               String strNumId = request.getParameter("txtNumId");
               String strNombres = request.getParameter("txtNombres");
               String strApellidos = request.getParameter("txtApellidos");
               String strDireccion = request.getParameter("txtDireccion");
               String strTelefono = request.getParameter("txtTelefono");
               String strEmail = request.getParameter("txtEmail");
               String strEstado = request.getParameter("txtEstado");
               String strTipoRegistro = request.getParameter("txtTipoRegistro");
                              
               if (strTipoRegistro.equals("C")){
                   String strJubilado = request.getParameter("txtJubilado");
                   
                    if (strAccion.equals("C")){                    
                         strResult = comunesRegistro.validarRegistro(strNumId,strForm);           

                         if (strResult != null){
                             out.println(strResult);
                         }else{ 
                             strSQL = "INSERT INTO tbl_contratistas values('" + strTipoId + "','" + strNumId + "','" + strNombres + "','" + strApellidos + "','" + strDireccion + "','" + strTelefono + "','" + strEmail + "','" + strEstado + "','" + strJubilado + "');";
                         }
                    } 

                    if (strAccion.equals("V")){    
                         strSQL = "update tbl_contratistas set txtNombres='" + strNombres + "', txtApellidos='" + strApellidos + "', txtDireccion='" + strDireccion + "', txtTelefono='" + strTelefono + "', "
                                         + "txtCorreoElectronico='" + strEmail + "', txtEstado='" + strEstado + "', txtJubilado= '" + strJubilado + "' where txtNumId ='" + strNumId + "'";                    
                     }
               }
               
               if (strTipoRegistro.equals("I")){
                   if (strAccion.equals("C")){                    
                         strResult = comunesRegistro.validarRegistro(strNumId,"frmInterventor");           

                         if (strResult != null){
                             out.println(strResult);
                         }else{ 
                             strSQL = "INSERT INTO users.users_personas(txtTipoId,txtIdentificacion,txtNombreCompleto,txtDireccion, txtTelOficina, txtEmailC, txtEstadoActual) values('" + strTipoId + "','" + strNumId + "','" + strNombres + " " + strApellidos + "','" + strDireccion + "','" + strTelefono + "','" + strEmail + "','" + strEstado + "');";
                         }
                    }
               }
           }
                      
           if (strForm.equals("frmInterventorXProyecto")){
               String strInterventor = request.getParameter("txtInterventor");
               String strCodProy = request.getParameter("txtCodProy");                        
               
                if (strAccion.equals("C")){                                                                         
                    strSQL = "INSERT INTO tbl_interventores_x_proyecto values('" + strInterventor + "','" + strCodProy + "');";
               }    
          }
                
            if (strForm.equals("frmContrato")){
                strConsecutivo = request.getParameter("txtConsecutivo");
                String strIdContratista = request.getParameter("txtContratista");
                String strTipoContrato = request.getParameter("txtTipoContrato");
                String strIdInterventor = request.getParameter("txtInterventor");
                String strGrupo= request.getParameter("txtGrupo");
                String strFechaIni = request.getParameter("txtFechaIni");
                String strFechaFin = request.getParameter("txtFechaFin");
                String strDuracion = request.getParameter("txtDuracion");
                String strValor = request.getParameter("txtValor");
                String strCentroC = request.getParameter("txtCentroC");
                String strConsecutivoL = request.getParameter("txtConsecutivoL");   
                String strNumCDP = request.getParameter("txtNumCDP");
                String strObjeto = request.getParameter("txtObjeto");
                String strIdEstado = request.getParameter("txtEstado");
                String strFechaCreacion = request.getParameter("txtFechaCreacion");

                if (strAccion.equals("C")){      
                        strResult = comunesRegistro.validarRegistro(strConsecutivo,strForm);           

                        if (strResult != null){
                            out.println(strResult);
                        }else{ 
                                strSQL = "INSERT INTO tbl_contratos values('" + strConsecutivo + "','" + strIdContratista + "','" + strIdInterventor + "','" + strTipoContrato + "','" + strGrupo + "','" + strFechaIni + "','" + strFechaFin + "','" + strDuracion + "',"
                                            + "'" + strValor + "','" + strCentroC + "','" + strConsecutivoL + "','" + strNumCDP + "','" + strObjeto + "','" + strIdEstado + "','" + strFechaCreacion + "');";    
                                
                                comunesRegistro.agregarInterventor(strIdInterventor);
                        }
                } 

                if (strAccion.equals("V")){    
                        strSQL = "update tbl_contratos set txtIdInterventor='" + strIdInterventor + "', txtIdGrupo='" + strGrupo + "', dtFechaInicio='" + strFechaIni + "', dtFechaFin='" + strFechaFin + "', txtDuracion='" + strDuracion + "', " 
                                        + "txtValor='" + strValor + "', txtIdCentroCostos='" + strCentroC + "', txtConsecutivoL='" + strConsecutivoL + "', txtCDP='" + strNumCDP + "', txtObjeto='" + strObjeto + "' "
                                        + "where txtConsecutivo ='" + strConsecutivo + "'";                    
                 }
            }            
           
            if (!strSQL.equals("")){
                strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                
                if (strMensaje == null){
                    //Inserción exitosa                  
                    
                    if (strAccion.equals("C")){ 
                        if (strForm.equals("frmContrato")){                         
                            PagosContrato pc = new PagosContrato();
                            pc.crearPlanPagos(strConsecutivo);          
                        }                        
                    }
                    
                    if (!(strForm.equals("frmGeneral"))){ 
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<script type='text/javascript'>"); 
                        out.println("setTimeout(function(){$(\"#btnLimpiar\").click();},2500);");
                        out.println("opener.frmRefresh.btnRefresh.click();");
                        out.println("</script>");                 
                        out.println("</body>");
                        out.println("</html>");
                    }
                    
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='TEXTOEXITO'>");                                                     
                    out.println("El registro fue ingresado correctamente!.");                  
                    out.println("</div>");                   
                    out.println("</body>");
                    out.println("</html>");                                    
                }else{
                    //Inserción fallida                   
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='TEXTOFALLO'>");
                    out.println("Se produjo el siguiente error al insertar el registro: " + strMensaje);
                    out.println("</div>");                  
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
        } finally {            
            out.close();
        }
    }                              
        
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
