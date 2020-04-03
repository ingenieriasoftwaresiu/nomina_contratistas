/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Conexion.GestionSQL;
import Negocio.Comunes;
import Negocio.ComunesRegistro;
import Negocio.Log;
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
 * @author jorge.correa
 */
public class RegistroOTROSI extends HttpServlet {

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
                                              
            String strFechaActual = null, strIdAutor=null, strConsecutivo=null, strNombreArchivo=null, strJustificacion=null, strAccion=null, strRutaActa="", strFechaCreacion=null, strFechaFinActual=null, strFechaFinNueva=null; 
            String strAccionRegistro = "", strMsgAccion=null, strMensaje = null, strResult=null, strForm= null, strCodigo=null;
            String[] strCodigoOTROSI =null, strRuta=null;
            Long lgSizeActa=null;
            Integer intCodigoOTROSI=0;
            
            String strSQL = "select g.txtRutaCargaArchivo from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";
            strRuta = GestionSQL.getFila(strSQL, "Nomina");
                                                            
            String strRutaArchivo = strRuta[0].replace("/", "\\\\");     
            Comunes comun = new Comunes();            
            ComunesRegistro comunesRegistro = new ComunesRegistro();
            strFechaActual = comun.getFechaActual();                         
             
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
                                                                
                                   if (strItem.equals("txtCodigo")){
                                       strCodigo = item.getString();
                                   }                                    
                                
                                   if (strItem.equals("txtForm")){
                                       strForm = item.getString();
                                   }

                                   if (strItem.equals("txtIdAutor")){
                                       strIdAutor = item.getString();
                                   }

                                   if (strItem.equals("txtConsecutivo")){
                                       strConsecutivo = item.getString();
                                   }

                                   if (strItem.equals("txtAccion")){
                                       strAccion = item.getString();
                                   }

                                   if (strItem.equals("txtRutaActa")){
                                       strRutaActa = item.getString();
                                   }

                                   if (strItem.equals("txtFechaCreacion")){
                                       strFechaCreacion = item.getString();
                                   }

                                   if (strItem.equals("txtFechaFinActual")){
                                       strFechaFinActual = item.getString();
                                   }

                                   if (strItem.equals("txtFechaFinNueva")){
                                       strFechaFinNueva = item.getString();
                                   }                                                                                                                                                                                                              

                                   if (strItem.equals("txtJustificacion")){                       
                                       strJustificacion = item.getString();
                                       strJustificacion = new String(strJustificacion.getBytes("iso-8859-1"),"UTF-8");                       
                                       strJustificacion = strJustificacion.trim();
                                   }                                                                               

                            }else{                                                                                       
                                                              
                                
                                strFechaActual = strFechaActual.replace("-", "").replace("-", "");                                     

                                if(strRutaActa.equals("-")){                                    
                                   if(item.getFieldName().equals("txtActa")){                                       
                                       lgSizeActa = item.getSize();                                       
                                       if (lgSizeActa > 0){                                                 
                                           strNombreArchivo= strConsecutivo + "_" + strFechaActual + "_" + "ActaOTROSI.pdf";                                           
                                           item.write(new File(destino,strNombreArchivo));                                           
                                           strRutaActa = strRutaArchivo + strNombreArchivo;                                  
                                           strRutaActa= strRutaActa.replace("\\\\", "/");        
                                       }else{
                                           strRutaActa = "-";
                                       }                                       
                                   }    
                                }                                                                        
                            }                                  
                     }
                                                                                                 
                   if (strAccion.equals("C")){          
                       
                        strAccionRegistro = strAccionRegistro + "<script type='text/javascript'>\n";                   
                        strAccionRegistro = strAccionRegistro + "location.href='notificacion.jsp';\n";
                        strAccionRegistro = strAccionRegistro + "opener.frmRefresh.btnRefresh.click();\n";
                        strAccionRegistro = strAccionRegistro + "</script>\n";
                        strMsgAccion = "El OTROSI fue registrado correctamente en el sistema!."; 

                        strResult = comunesRegistro.validarRegistro(strConsecutivo + ">" +strFechaFinNueva ,strForm);                                                 

                        if (strResult != null){                                
                            out.println("<script type='text/javascript'>");
                            out.println("alert('Ya existe un registro de OTROSI para la fecha de finalización nueva seleccionada. Por favor seleccione una fecha diferente.');");
                            out.println("history.back();");
                            out.println("</script>");      
                            return;

                        }else{                           

                            strSQL = "select txtCodigo from nomina.tbl_otrosi_contratos where txtIdContrato = '" + strConsecutivo + "' ORDER BY CAST(txtCodigo AS SIGNED) DESC limit 1";
                            strCodigoOTROSI = GestionSQL.getFila(strSQL,"Nomina");                           

                            if (strCodigoOTROSI == null){
                                intCodigoOTROSI = 1;
                            }else{
                                intCodigoOTROSI = Integer.parseInt(strCodigoOTROSI[0]) + 1;
                            }                           

                            strSQL = "INSERT INTO tbl_otrosi_contratos values(" + intCodigoOTROSI + ",'" + strConsecutivo + "','" + strIdAutor + "','" + strFechaCreacion + "','" + strFechaFinActual + "','" + strFechaFinNueva + "','" + strJustificacion + "','" + strRutaActa + "');";    
                            
                        }                            
                   }

                   if (strAccion.equals("V")){      
                                               
                        strAccionRegistro = strAccionRegistro + "<script type='text/javascript'>\n";                   
                        strAccionRegistro = strAccionRegistro + "location.href='notificacion.jsp';\n";
                        strAccionRegistro = strAccionRegistro + "opener.frmListadoOTROSI.btnRefresh.click();\n";
                        strAccionRegistro = strAccionRegistro + "</script>\n";
                        strMsgAccion = "El OTROSI fue actualizado correctamente en el sistema!."; 

                        strSQL = "UPDATE tbl_otrosi_contratos set txtJustificacion='" + strJustificacion + "' where txtCodigo = '" + strCodigo + "' and txtIdContrato = '" + strConsecutivo + "'";    

                   }

                   if (!strSQL.equals("")){

                       strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");

                       if (strMensaje == null){
                           //Inserción exitosa             
                           
                           if (strAccion.equals("C")){
                               strSQL = "UPDATE nomina.tbl_contratos set dtFechaFin = '" + strFechaFinNueva + "' where txtConsecutivo = '" + strConsecutivo + "'";
                               GestionSQL.ejecuta(strSQL,"Nomina");
                           }                           
                           
                           request.getSession().setAttribute("mensaje", strMsgAccion);      
                           out.println(strAccionRegistro);

                       }else{                                
                           out.println("<script type='text/javascript'>\n");
                           out.println("alert('Se produjo un error al registrar la información del OTROSI en el sistema. Por favor intente de nuevo en un momento o contacte al Administrador del Sistema.');\n");
                           out.println("history.back();\n");
                           out.println("</script>\n");
                           return;                        
                       }
                   }

               }catch(FileUploadException  fue){                         
                   Log.registroTraza("Se generó un error al crear el archivo adjunto: " + fue.getMessage());
               }catch(Exception e){                         
                   Log.registroTraza("Se generó un error al crear el archivo adjunto: " + e.getMessage());
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
