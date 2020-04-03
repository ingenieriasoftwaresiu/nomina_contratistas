/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Conexion.GestionSQL;
import Negocio.Comunes;
import Negocio.Notificacion;
import Negocio.PagosContrato;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author jorge.correa
 */
public class DetallePago extends HttpServlet {

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
                        
            HttpSession session = request.getSession(true); 
            String strTipoUsuario = "", strAccionRegistro="", strMsgAccion="";
            if (session.getAttribute("txtTipoUsuario") != null){
                strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
            }else{
                request.getRequestDispatcher("cerrar.jsp").forward(request, response);
            }
            String strSQL = "select g.txtRutaCargaArchivo from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";
            String[] strRuta = GestionSQL.getFila(strSQL, "Nomina");
            
            String strRutaArchivo = strRuta[0].replace("/", "\\\\");
            String strRutaFormato = strRuta[0].replace("/", "\\\\");
            String strConsecutivo="",strNumPago="",strValorSalud="",strValorPension="",strValorARL="", strObs="", strMensaje="", strNombreArchivo="", strIdEstado="", strAdjunto="", strFormato="";            
            long lgSizeAdjunto=0;
            long lgSizeFormato=0;
            Comunes comun = new Comunes();
            PagosContrato pc = new PagosContrato();
            Notificacion notificacion = new Notificacion();
            String strFechaActual = comun.getFechaActual(); 
            strSQL="";
   
             File destino = new File(strRutaArchivo);
             ServletRequestContext src = new ServletRequestContext(request);

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
                                            strConsecutivo = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtNumPago")){
                                            strNumPago = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtValorSalud")){
                                            strValorSalud = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtValorPension")){
                                            strValorPension = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtValorARL")){
                                            strValorARL = item.getString();
                                        }                                        
                                        
                                        if (strItem.equals("txtObs")){
                                            strObs = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtIdEstado")){
                                            strIdEstado = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtRutaArchivo")){
                                            strAdjunto = item.getString();
                                        }
                                        
                                        if (strItem.equals("txtRutaFormato")){
                                            strFormato = item.getString();
                                        }
                                        
                                 }else{                                                             
                                                                             
                                        if(item.getFieldName().equals("txtAdjunto")){
                                            lgSizeAdjunto = item.getSize();
                                            if (lgSizeAdjunto > 0){
                                                strFechaActual = strFechaActual.replace("-", "").replace("-", "");
                                                strNombreArchivo= strConsecutivo + "_" + strNumPago + "_" + strFechaActual + "_" + "soporte.pdf";
                                                item.write(new File(destino,strNombreArchivo));
                                                strRutaArchivo = strRutaArchivo + strNombreArchivo;                                  
                                                strRutaArchivo = strRutaArchivo.replace("\\\\", "/");        
                                            }else{
                                                strRutaArchivo = "-";
                                            }
                                        }    
                                        
                                        if(item.getFieldName().equals("txtFormato")){          
                                            lgSizeFormato = item.getSize();
                                            if (lgSizeFormato > 0){                                            
                                                strFechaActual = strFechaActual.replace("-", "").replace("-", "");
                                                strNombreArchivo= strConsecutivo + "_" + strNumPago + "_" + strFechaActual + "_" + "formato.pdf";
                                                item.write(new File(destino,strNombreArchivo));
                                                strRutaFormato = strRutaFormato + strNombreArchivo;                                  
                                                strRutaFormato = strRutaFormato.replace("\\\\", "/");                                               
                                            }else{
                                                strRutaFormato = "-";
                                            }
                                        }
                                        
                                 } 
                         }                         
                     }catch(FileUploadException  fue){
                         fue.getMessage();
                     }catch(Exception e){
                         e.getMessage();
                     }
                     
                    strAccionRegistro = strAccionRegistro + "<script type='text/javascript'>\n";                                 
                    strAccionRegistro = strAccionRegistro + "location.href='notificacion.jsp';\n";;  
                    strAccionRegistro = strAccionRegistro + "window.opener.document.getElementById('btnRefrescar').click();\n"; 
                    strAccionRegistro = strAccionRegistro + "</script>\n";
                    strMsgAccion = "Los datos del pago fueron registrados correctamente!.";
                     
                     if(strIdEstado.equals("P")){
                         if(((strAdjunto.equals("-") && lgSizeAdjunto>0) || (!(strAdjunto.equals("-")) && lgSizeAdjunto>0)) && ((strFormato.equals("-") && lgSizeFormato>0) || (!(strFormato.equals("-")) && lgSizeFormato>0))){
                             strSQL = "update nomina.tbl_plan_pagos set txtValorSalud='" + strValorSalud + "', txtValorPension='" + strValorPension + "', txtValorARL='" + strValorARL + "', txtRutaArchivo='" + strRutaArchivo + "', txtRutaFormato='" + strRutaFormato + "', txtIdEstadoPago='PA'"
                                    + " where txtIdContrato ='" + strConsecutivo + "' and txtNumPago='" + strNumPago +"'";
                         }else{
                             strSQL = "update nomina.tbl_plan_pagos set txtValorSalud='" + strValorSalud + "', txtValorPension='" + strValorPension + "', txtValorARL='" + strValorARL + "', txtIdEstadoPago='PA'"
                                    + " where txtIdContrato ='" + strConsecutivo + "' and txtNumPago='" + strNumPago +"'";
                         }
                    }                                
                     
                    if((!(strObs.trim().equals(""))) && (!(strObs.trim().equals("-")))){                                                
                        strObs = new String(strObs.getBytes("iso-8859-1"),"UTF-8");                        
                    }else{
                        strObs = "-";
                    }
                    
                    pc.crearHistoricoPago(strConsecutivo, strNumPago, strTipoUsuario, "GUARDAR",strObs);
                    notificacion.notificarTransaccion(strConsecutivo, strNumPago, "GUARDAR", strObs);
                                                            
                    strMensaje = null;
                    strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                
                    if (strMensaje == null){                                                                                            
                                                
                        request.getSession().setAttribute("mensaje", strMsgAccion);      
                        out.println(strAccionRegistro);
                    }else{
                        out.println("<script type='text/javascript'>");                        
                        out.println("alert('Se produjo el siguiente error al insertar el registro: " + System.err.toString() + "');");                 
                        out.println("window.history.back();");
                        out.println("</script>");                          
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
