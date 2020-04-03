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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jorge.correa
 */
public class Pagos extends HttpServlet {

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
            PagosContrato pc = new PagosContrato();
            Notificacion notificacion = new Notificacion();
            String strTipoUsuario = "";
            if (session.getAttribute("txtTipoUsuario") != null){
                strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
            }else{
                request.getRequestDispatcher("cerrar.jsp").forward(request, response);
            }
            
            String strAccion = request.getParameter("txtAccion");
            String strSQL = "", strMensaje = null;;
            
            if (strAccion.equals("ELIMINARPAGOSSGTES")){
                String strConsecutivo = request.getParameter("txtConsecutivo");
                String strNumPago = request.getParameter("txtNumPago");
                String strPorcNuevo = request.getParameter("txtPorcNuevo");
                String strEvento = request.getParameter("txtEvento");
                
                // Actualizar el porcentaje a pagar del pago actual.
                
                strSQL = "update nomina.tbl_plan_pagos set txtPorcPago = '" + strPorcNuevo + "' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strNumPago + "'";
                strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");
                
                // Eliminar los pagos siguientes, ya que se llegó al 100%.
                
                if (strMensaje == null){
                    strSQL = "delete from nomina.tbl_plan_pagos where txtIdContrato = '" + strConsecutivo + "' and CAST(txtNumPago as SIGNED) > '" + strNumPago + "'";
                    strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");
                    
                    if (strMensaje == null){
                        if(strEvento.equals("DD")){
                            out.println("<html>");
                            out.println("<head>");
                            out.println("</head>");
                            out.println("<body>");                           
                            out.println("<script type='text/javascript'>"); 
                            out.println("alert('El nuevo porcentaje fue modificado correctamente');"); 
                            out.println("setTimeout(function(){opener.frmDetallePago.btnSalir.click();window.close();},1500);");
                            out.println("</script>");                 
                            out.println("</body>");
                            out.println("</html>");
                        }else{
                            out.println("<html>");
                            out.println("<head>");
                            out.println("</head>");
                            out.println("<body>");                                                          
                            out.println("<script type='text/javascript'>"); 
                            out.println("alert('El nuevo porcentaje fue modificado correctamente');");                                    
                            out.println("window.opener.document.getElementById('btnRefrescar').click();");
                            out.println("window.close();");   
                            out.println("</script>");                 
                            out.println("</body>");
                            out.println("</html>");
                        }                        
                    }else{
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class='TEXTOFALLO'");                                                     
                        out.println("Se produjo un error al eliminar los pagos para el contrato #" + strConsecutivo + " y pago #" + strNumPago);
                        out.println("</div>");                   
                        out.println("</body>");
                        out.println("</html>");
                    }                    
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='TEXTOFALLO'");                                                     
                    out.println("Se produjo un error al actualizar el nuevo porcentaje para el contrato #" + strConsecutivo + " y pago #" + strNumPago);
                    out.println("</div>");                   
                    out.println("</body>");
                    out.println("</html>");
                }                
            }
            
            if (strAccion.equals("RECALCULARPAGOS")){
                String strConsecutivo = request.getParameter("txtConsecutivo");
                String strNumPago = request.getParameter("txtNumPago");
                String strPorcNuevo = request.getParameter("txtPorcNuevo");
                String strEvento = request.getParameter("txtEvento");
       
                double dblPorcMax=0, dblNumPagos=0, dblPorcBase=0, dblPorcAPagar=0, dblAcumulado=0;
                int intNumPagos=0;
                String strAnio="", strMes="", strFechaPrimera="", strFechaUltima="", strPorcAcum="";
                long lgDiferencia=0;
                String[] strDatos = null;
                boolean error = false, borrar=false;
                                                      
                strSQL = "select c.txtDuracion from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
                strDatos = GestionSQL.getFila(strSQL, "Nomina");
                
                dblNumPagos = Math.floor(Double.parseDouble(strDatos[0])/30.0);
                                              
                strSQL = "select p.txtNumPago from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' ORDER BY CAST(p.txtNumPago AS SIGNED) DESC limit 1";
                strDatos = GestionSQL.getFila(strSQL, "Nomina");
                
                //dblPorcMax = Math.rint(100 - (Double.parseDouble(strPorcAcum) + Double.parseDouble(strPorcNuevo)));
                dblPorcMax = 100.00;
                //dblNumPagos = (Double.parseDouble(strDatos[0]) - Double.parseDouble(strNumPago));                                    
                //dblPorcBase = Math.rint((dblPorcMax/dblNumPagos)*100)/100;
                
                dblPorcBase = Math.rint((dblPorcMax/dblNumPagos)*100)/100;
                //dblNumPagos = (Double.parseDouble(strDatos[0]) - Double.parseDouble(strNumPago));   
                dblNumPagos = Double.parseDouble(strDatos[0]);
                intNumPagos = (int) Math.ceil(dblNumPagos); 
                Date dtFechaInicio = null;
                Date dtFechaFin = null;
                Comunes comun = new Comunes();
                  
                strSQL = "select p.dtFechaPago from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo +"' and p.txtNumPago = '" + strNumPago + "'";
                strDatos = GestionSQL.getFila(strSQL, "Nomina");
                strFechaPrimera = comun.aumentarDiasFecha(strDatos[0], 1);
                                               
                strSQL = "update nomina.tbl_plan_pagos set txtPorcPago = '" + strPorcNuevo + "' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strNumPago + "'";
                strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");
                
                strSQL = "select sum(p.txtPorcPago) from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo +"' and CAST(p.txtNumPago AS SIGNED) <= " + strNumPago + "";
                strDatos = GestionSQL.getFila(strSQL, "Nomina");
                strPorcAcum = strDatos[0];
                  
                dblAcumulado = Double.parseDouble(strPorcAcum);
                int i;
                
                if (strMensaje == null){                                
                    for(i=Integer.parseInt(strNumPago)+1;i<=intNumPagos && borrar==false ;i++){
                        
                        if((dblAcumulado + dblPorcBase) > 100){
                            dblPorcBase = Math.rint((100.00 - dblAcumulado)*100)/100;
                            borrar = true;                            
                        }else{
            
                            // Actualización de pagos
                            strSQL = "update nomina.tbl_plan_pagos set txtPorcPago = '" + String.valueOf(dblPorcBase) + "' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + String.valueOf(i) + "'";
                            strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");

                            if (strMensaje != null){          
                                System.out.println("Se generó un error al actualizar el pago " + i + " para el contrato con consecutivo " + strConsecutivo + ".");
                                error = true;
                            }

                            //strFechaPrimera = comun.aumentarDiasFecha(strFechaUltima, 1);
                            dblAcumulado = dblAcumulado + dblPorcBase;     
                        }
                    }
                                                            
                    if (borrar==true){                        
                        strMensaje = null;                       
                                                                     
                        dblPorcBase = Math.rint((dblPorcBase/(intNumPagos - (i-2)))*100)/100;                        
                                                
                        for(int a=i-1;a<=intNumPagos;a++){
                            strSQL = "update nomina.tbl_plan_pagos set txtPorcPago = '" + String.valueOf(dblPorcBase) + "' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + String.valueOf(a) + "'";
                            strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");                            
                        }
                        
                        /*strSQL = "delete from nomina.tbl_plan_pagos where txtIdContrato = '" + strConsecutivo + "' and CAST(txtNumPago AS SIGNED) >= " + (Integer.parseInt(strNumPago) + i);
                        strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");*/
                        
                        if (strMensaje != null){          
                            System.out.println("Se generó un error al borrar los pagos para el contrato con consecutivo " + strConsecutivo + ".");
                            error = true;
                        }
                    }
                    
                    if (error == false){
                        if(strEvento.equals("DD")){
                            out.println("<html>");
                            out.println("<head>");
                            out.println("</head>");
                            out.println("<body>");                       ;                   
                            out.println("<script type='text/javascript'>"); 
                            out.println("alert('El nuevo porcentaje fue modificado correctamente');");
                            out.println("setTimeout(function(){window.opener.document.getElementById('btnSalir').click();window.close();},1500);");
                            out.println("</script>");                 
                            out.println("</body>");
                            out.println("</html>");
                        }else{
                            out.println("<html>");
                            out.println("<head>");
                            out.println("</head>");
                            out.println("<body>");                                                          
                            out.println("<script type='text/javascript'>"); 
                            out.println("alert('El nuevo porcentaje fue modificado correctamente');");        
                            out.println("window.opener.document.getElementById('btnRefrescar').click();");
                            out.println("window.close();");                               
                            out.println("</script>");                 
                            out.println("</body>");
                            out.println("</html>");
                        }
                    }
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='TEXTOFALLO'");                                                     
                    out.println("Se produjo un error al actualizar el nuevo porcentaje para el contrato #" + strConsecutivo + " y pago #" + strNumPago);
                    out.println("</div>");                   
                    out.println("</body>");
                    out.println("</html>");
                }
            }            
            
            if (strAccion.equals("APROBAR")){
                String strConsecutivo = request.getParameter("txtConsecutivo");
                String strCodigoPago = request.getParameter("txtCodigoPago");
                String strEvento = request.getParameter("txtEvento");
                String strObs = request.getParameter("txtObs");                                    
                                
                pc.crearHistoricoPago(strConsecutivo, strCodigoPago, strTipoUsuario, strAccion,strObs);
                notificacion.notificarTransaccion(strConsecutivo, strCodigoPago, strAccion, strObs);
                                
                strSQL = "update nomina.tbl_plan_pagos set txtIdEstadoPago = 'A' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strCodigoPago + "'";
                strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                
                if (strMensaje == null){                    
                                        
                    if (strEvento.equals("PP")){
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<script type='text/javascript'>");                                                     
                        out.println("location.href = 'plan_pagos.jsp?txtConsecutivo=" + strConsecutivo + "';");                  
                        out.println("</script>");                   
                        out.println("</body>");
                        out.println("</html>");
                    }else{
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");                                                          
                        out.println("<script type='text/javascript'>"); 
                        out.println("window.opener.document.getElementById('btnRefrescar').click();");
                        out.println("window.close();");                           
                        out.println("</script>");                 
                        out.println("</body>");
                        out.println("</html>");
                    }
                                          
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>"); 
                    out.println("alert('Se produjo el siguiente error al actualizar el responsable: " + strMensaje + "'");
                    out.println("</script>");                 
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            if (strAccion.equals("PREAPROBAR")){
                String strConsecutivo = request.getParameter("txtConsecutivo");
                String strCodigoPago = request.getParameter("txtCodigoPago");
                String strEvento = request.getParameter("txtEvento");
                String strObs = request.getParameter("txtObs");               
                             
                pc.crearHistoricoPago(strConsecutivo, strCodigoPago, strTipoUsuario, strAccion,strObs);
                notificacion.notificarTransaccion(strConsecutivo, strCodigoPago, strAccion, strObs);
                                              
                strSQL = "update nomina.tbl_plan_pagos set txtIdEstadoPago = 'PRA' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strCodigoPago + "'";
                strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                
                if (strMensaje == null){
                                                            
                    if (strEvento.equals("PP")){
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<script type='text/javascript'>");                                                     
                        out.println("location.href = 'plan_pagos.jsp?txtConsecutivo=" + strConsecutivo + "';");                  
                        out.println("</script>");                   
                        out.println("</body>");
                        out.println("</html>");
                    }else{
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");                                                          
                        out.println("<script type='text/javascript'>");                          
                        out.println("window.opener.document.getElementById('btnRefrescar').click();");
                        out.println("window.close();");  
                        out.println("</script>");                 
                        out.println("</body>");
                        out.println("</html>");
                    }
                                          
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>"); 
                    out.println("alert('Se produjo el siguiente error al actualizar el responsable: " + strMensaje + "'");
                    out.println("</script>");                 
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            if (strAccion.equals("REPROCESARC")){
                String strConsecutivo = request.getParameter("txtConsecutivo");
                String strCodigoPago = request.getParameter("txtCodigoPago");
                String strEvento = request.getParameter("txtEvento");
                String strObs = request.getParameter("txtObs");
                                
                pc.crearHistoricoPago(strConsecutivo, strCodigoPago, strTipoUsuario, strAccion,strObs);
                notificacion.notificarTransaccion(strConsecutivo, strCodigoPago, strAccion, strObs);
                                                                                            
                strSQL = "update nomina.tbl_plan_pagos set txtIdEstadoPago = 'PA' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strCodigoPago + "'";
                strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                                
                if (strMensaje == null){                    
                                        
                    if (strEvento.equals("PP")){
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<script type='text/javascript'>");                                                     
                        out.println("location.href = 'plan_pagos.jsp?txtConsecutivo=" + strConsecutivo + "';");                  
                        out.println("</script>");                   
                        out.println("</body>");
                        out.println("</html>");         
                    }else{
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");                                                          
                        out.println("<script type='text/javascript'>");                         
                        out.println("window.opener.document.getElementById('btnRefrescar').click();");
                        out.println("window.close();");   
                        out.println("</script>");                 
                        out.println("</body>");
                        out.println("</html>");
                    }
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>"); 
                    out.println("alert('Se produjo el siguiente error al actualizar el responsable: " + strMensaje + "'");
                    out.println("</script>");                 
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            if (strAccion.equals("REPROCESARI")){
                String strConsecutivo = request.getParameter("txtConsecutivo");
                String strCodigoPago = request.getParameter("txtCodigoPago");
                String strEvento = request.getParameter("txtEvento");
                String strObs = request.getParameter("txtObs");                                  
                                
                pc.crearHistoricoPago(strConsecutivo, strCodigoPago, strTipoUsuario, strAccion,strObs);
                notificacion.notificarTransaccion(strConsecutivo, strCodigoPago, strAccion, strObs);
                            
                strSQL = "update nomina.tbl_plan_pagos set txtIdEstadoPago = 'P' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strCodigoPago + "'";
                strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                
                if (strMensaje == null){                    
                                        
                    if (strEvento.equals("PP")){
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<script type='text/javascript'>");                                                     
                        out.println("location.href = 'plan_pagos.jsp?txtConsecutivo=" + strConsecutivo + "';");                  
                        out.println("</script>");                   
                        out.println("</body>");
                        out.println("</html>");         
                    }else{
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");                                                          
                        out.println("<script type='text/javascript'>");                         
                        out.println("window.opener.document.getElementById('btnRefrescar').click();");
                        out.println("window.close();");   
                        out.println("</script>");                 
                        out.println("</body>");
                        out.println("</html>");
                    }
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>"); 
                    out.println("alert('Se produjo el siguiente error al actualizar el responsable: " + strMensaje + "'");
                    out.println("</script>");                 
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            if (strAccion.equals("EJECUTAR")){
                String strConsecutivo = request.getParameter("txtConsecutivo");
                String strCodigoPago = request.getParameter("txtCodigoPago");
                String strEvento = request.getParameter("txtEvento");
                String strObs = request.getParameter("txtObs");                          
                               
                pc.crearHistoricoPago(strConsecutivo, strCodigoPago, strTipoUsuario, strAccion,strObs);
                                  
                strSQL = "update nomina.tbl_plan_pagos set txtIdEstadoPago = 'E' where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strCodigoPago + "'";
                strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                
                if (strMensaje == null){                    
                                        
                    if (strEvento.equals("PP")){
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<script type='text/javascript'>");                                                     
                        out.println("location.href = 'plan_pagos.jsp?txtConsecutivo=" + strConsecutivo + "';");                  
                        out.println("</script>");                   
                        out.println("</body>");
                        out.println("</html>");         
                    }else{
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");                                                          
                        out.println("<script type='text/javascript'>");                         
                        out.println("window.opener.document.getElementById('btnRefrescar').click();");
                        out.println("window.close();");   
                        out.println("</script>");                 
                        out.println("</body>");
                        out.println("</html>");
                    }                      
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>"); 
                    out.println("alert('Se produjo el siguiente error al actualizar el responsable: " + strMensaje + "'");
                    out.println("</script>");                 
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            if (strAccion.equals("ELIMINAR")){
                String strConsecutivo = request.getParameter("txtConsecutivo");
                String strCodigoPago = request.getParameter("txtCodigoPago");
                String strObs = "-";

                pc.crearHistoricoPago(strConsecutivo, strCodigoPago, strTipoUsuario, strAccion,strObs);
                
                strSQL = "delete from nomina.tbl_plan_pagos where txtIdContrato = '" + strConsecutivo + "' and txtNumPago = '" + strCodigoPago + "'";
                strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");
                
                if (strMensaje == null){                                        
                                        
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>");                                                     
                    out.println("location.href = 'plan_pagos.jsp?txtConsecutivo=" + strConsecutivo + "';");                  
                    out.println("</script>");                   
                    out.println("</body>");
                    out.println("</html>");                      
                }else{
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>"); 
                    out.println("alert('Se produjo el siguiente error al actualizar el responsable: " + strMensaje + "'");
                    out.println("</script>");                 
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
