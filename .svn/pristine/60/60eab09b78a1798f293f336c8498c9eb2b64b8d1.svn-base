/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Jorge.correa
 */
public class RegistroContrato extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
           
            String strConsecutivo="", strIdContratista="", strTipoContrato="", strIdInterventor="", strGrupo="", strFechaIni="", strFechaFin="", strDuracion="", strValor="", strCentroC="", strConsecutivoL="";
            String strNumCDP="", strObjeto="", strIdEstado="", strFechaCreacion="", strAccion="", strFechaActual="", strNombreArchivo="", strResult=null, strForm="",strMensaje=null, strAccionRegistro="",strMsgAccion="";
            
            String strSQL = "select g.txtRutaCargaArchivo from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";
            String[] strRuta = GestionSQL.getFila(strSQL, "Nomina");
            long lgSizeActaInicio=0, lgSizeActaFinalizacion=0, lgSizeMinutaContrato=0;
            Comunes comun = new Comunes();
            ComunesRegistro comunesRegistro = new ComunesRegistro();
            strFechaActual = comun.getFechaActual(); 
                        
            String strRutaArchivo = strRuta[0].replace("/", "\\\\");
            String strRutaActaInicio = "";
            String strRutaActaFinalizacion = "";
            String strRutaMinutaContrato = "";
            
            File destino = new File(strRutaArchivo);
            ServletRequestContext src = new ServletRequestContext(request);
            strSQL = "";

             if(ServletFileUpload.isMultipartContent(src)){
                DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
                ServletFileUpload upload = new  ServletFileUpload(factory);
                
                try{
                         java.util.List lista = upload.parseRequest(src);                     
                         java.util.Iterator it = lista.iterator();

                         while(it.hasNext()){
                                 FileItem item=(FileItem)it.next();
                                  String strItem = item.getFieldName();
                                  
                                 if(item.isFormField()){       
                                                                                                                  
                                        if (strItem.equals("txtConsecutivo")){
                                            strConsecutivo = item.getString().trim();
                                        }
                                                                             
                                        if (strItem.equals("txtContratista")){
                                            strIdContratista = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtTipoContrato")){
                                            strTipoContrato = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtInterventor")){
                                            strIdInterventor = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtGrupo")){
                                            strGrupo = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtFechaIni")){
                                            strFechaIni = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtFechaFin")){
                                            strFechaFin = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtDuracion")){
                                            strDuracion = item.getString().trim();
                                        }
                                        
                                        if (strItem.equals("txtValor")){
                                            strValor = item.getString().trim();
                                        }
                                        
                                        if (strItem.equals("txtCentroC")){
                                            strCentroC = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtConsecutivoL")){
                                            strConsecutivoL = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtNumCDP")){
                                            strNumCDP = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtObjetoC")){                       
                                            strObjeto = item.getString();
                                            strObjeto = new String(strObjeto.getBytes("iso-8859-1"),"UTF-8");             
                                            strObjeto = strObjeto.replaceAll("\r\n", " ");
                                        }
                                        
                                        if (strItem.equals("txtIdEstado")){
                                            strIdEstado = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtFechaCreacion")){
                                            strFechaCreacion = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtAccion")){
                                            strAccion = item.getString();
                                        }                                        
                                        
                                        if (strItem.equals("txtForm")){
                                            strForm = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtRutaActaInicio")){
                                            strRutaActaInicio = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtRutaActaFinalizacion")){
                                            strRutaActaFinalizacion = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtRutaMinutaContrato")){
                                            strRutaMinutaContrato = item.getString();
                                        }
                                        
                                 }else{                                                                                          
                                     
                                     strFechaActual = strFechaActual.replace("-", "").replace("-", "");                                     
                                     
                                     //if(strRutaActaInicio.equals("-")){                                         
                                        if(item.getFieldName().equals("txtActaInicio")){                                            
                                            lgSizeActaInicio = item.getSize();
                                            if (lgSizeActaInicio > 0){                                                                       
                                                strNombreArchivo= strConsecutivo + "_" + strFechaActual + "_" + "ActaInicio.pdf";
                                                item.write(new File(destino,strNombreArchivo));
                                                strRutaActaInicio = strRutaArchivo + strNombreArchivo;                                  
                                                strRutaActaInicio = strRutaActaInicio.replace("\\\\", "/");        
                                            }else{
                                                strRutaActaInicio = "-";
                                            }
                                        }    
                                     //}
                                                                          
                                     //if(strRutaActaFinalizacion.equals("-")){                                         
                                        if(item.getFieldName().equals("txtActaFin")){                                            
                                            lgSizeActaFinalizacion = item.getSize();
                                            if (lgSizeActaFinalizacion > 0){                                                                     
                                                strNombreArchivo= strConsecutivo + "_" + strFechaActual + "_" + "ActaFinalizacion.pdf";
                                                item.write(new File(destino,strNombreArchivo));
                                                strRutaActaFinalizacion = strRutaArchivo + strNombreArchivo;                                  
                                                strRutaActaFinalizacion = strRutaActaFinalizacion.replace("\\\\", "/");        
                                            }else{
                                                strRutaActaFinalizacion = "-";
                                            }
                                        }
                                     //}                                     
                                     
                                     //if(strRutaMinutaContrato.equals("-")){                                                    
                                        if(item.getFieldName().equals("txtMinutaContrato")){                                            
                                            lgSizeMinutaContrato = item.getSize();
                                            if (lgSizeMinutaContrato > 0){                                                                        
                                                strNombreArchivo= strConsecutivo + "_" + strFechaActual + "_" + "MinutaContrato.pdf";
                                                item.write(new File(destino,strNombreArchivo));
                                                strRutaMinutaContrato = strRutaArchivo + strNombreArchivo;                                  
                                                strRutaMinutaContrato = strRutaMinutaContrato.replace("\\\\", "/");        
                                            }else{
                                                strRutaMinutaContrato = "-";
                                            }
                                        }
                                     //}
                                 } 
                         }                                                  
                                                  
                        strAccionRegistro = strAccionRegistro + "<script type='text/javascript'>\n";
                        strAccionRegistro = strAccionRegistro + "opener.frmRefresh.btnRefresh.click();\n";
                        strAccionRegistro = strAccionRegistro + "location.href='notificacion.jsp';\n";
                        strAccionRegistro = strAccionRegistro + "</script>\n";
                         
                        if (strAccion.equals("C")){                               
                            strMsgAccion = "El contrato fue registrado correctamente en el sistema!.";                                                      
                            
                            strResult = comunesRegistro.validarRegistro(strConsecutivo,strForm);          
                                                                                    
                            if (strResult != null){                                
                                out.println("<script type='text/javascript'>");
                                out.println("alert('Ya existe un contrato creado con el consecutivo ingresado. Para continuar, ingrese un consecutivo diferente.');");
                                out.println("history.back();");
                                out.println("</script>");      
                                return;
                                
                            }else{                                 
                                strSQL = "INSERT INTO tbl_contratos values('" + strConsecutivo + "','" + strIdContratista + "','" + strIdInterventor + "','" + strTipoContrato + "','" + strGrupo + "','" + strFechaIni + "','" + strFechaFin + "','" + strDuracion + "',"
                                                + "'" + strValor + "','" + strCentroC + "','" + strConsecutivoL + "','" + strNumCDP + "','" + strObjeto + "','" + strIdEstado + "','" + strFechaCreacion + "','" + strRutaActaInicio + "','" + strRutaActaFinalizacion + "','" + strRutaMinutaContrato + "');";    
                                                                                                            
                                comunesRegistro.agregarInterventor(strIdInterventor);                                                                        
                            }                                                        
                        }
                        
                        if (strAccion.equals("V")){
                            
                            strMsgAccion = "El contrato fue actualizado correctamente en el sistema!.";
                            
                            if (strIdEstado.equals("CR")){
                                strSQL = "update tbl_contratos set txtIdInterventor='" + strIdInterventor + "', txtIdGrupo='" + strGrupo + "', " 
                                        + "txtValor='" + strValor + "', txtIdCentroCostos='" + strCentroC + "', txtConsecutivoL='" + strConsecutivoL + "', txtCDP='" + strNumCDP + "', txtObjeto='" + strObjeto + "',  txtRutaActaInicio='" + strRutaActaInicio + "', txtRutaActaFinalizacion='" + strRutaActaFinalizacion + "', txtRutaMinutaContrato='" + strRutaMinutaContrato + "' "
                                        + "where txtConsecutivo ='" + strConsecutivo + "'";
                            }
                            
                             if (strIdEstado.equals("EJ")){
                                 strSQL = "update tbl_contratos set txtConsecutivoL='" + strConsecutivoL + "', txtCDP='" + strNumCDP + "', txtObjeto='" + strObjeto + "',  txtRutaActaInicio='" + strRutaActaInicio + "', txtRutaActaFinalizacion='" + strRutaActaFinalizacion + "', txtRutaMinutaContrato='" + strRutaMinutaContrato + "' "
                                        + "where txtConsecutivo ='" + strConsecutivo + "'";
                             }
                             
                             if (strIdEstado.equals("FN")){
                                 strSQL = "update tbl_contratos set txtRutaActaInicio='" + strRutaActaInicio + "', txtRutaActaFinalizacion='" + strRutaActaFinalizacion + "', txtRutaMinutaContrato='" + strRutaMinutaContrato + "' "
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
                                                                                                 
                                request.getSession().setAttribute("mensaje", strMsgAccion);      
                                out.println(strAccionRegistro);

                            }else{                                
                                out.println("<script type='text/javascript'>\n");
                                out.println("alert('Se produjo un error al registrar la información del contrato en el sistema. Por favor intente de nuevo en un momento o contacte al Administrador del Sistema.');\n");
                                out.println("history.back();\n");
                                out.println("</script>\n");
                                return;                        
                            }
                        }
                         
                     }catch(FileUploadException  fue){                         
                         fue.getMessage();
                     }catch(Exception e){                         
                         e.getMessage();
                     }                                
             }
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
