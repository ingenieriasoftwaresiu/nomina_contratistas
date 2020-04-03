/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Conexion.GestionSQL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorge.correa
 */
public class Contratos extends HttpServlet {

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
           
            String strConsecutivo = request.getParameter("txtConsecutivo");
            String strAccion = request.getParameter("txtAccion");            
            String strSQL = "", strMensaje=null;
            
            //Accion: Cancelar contrato.
            
            if (strAccion.equals("CANCELAR")){
                                               
                
                strSQL = "update nomina.tbl_contratos set txtIdEstado = 'CA' WHERE txtConsecutivo = '" + strConsecutivo +"'";
                strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");
                
                if (strMensaje == null){
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("El contrato fue cancelado correctamente");
                    out.println("<script type='text/javascript'>");                                                     
                    out.println("alert('El contrato fue cancelado correctamente.');");     
                    out.println("opener.frmRefresh.btnRefresh.click();");
                    out.println("window.close();");    
                    out.println("</script>");                   
                    out.println("</body>");
                    out.println("</html>");
                }else{
                out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>");                                                     
                    out.println("alert('El contrato no pudo ser cancelado debido a un error de datos');");                 
                    out.println("</script>");                   
                    out.println("</body>");
                    out.println("</html>");
                }
                
            }
            
            //Accion: Poner en ejecución.
            
            if (strAccion.equals("PEE")){
                strSQL = "update nomina.tbl_contratos set txtIdEstado = 'EJ' WHERE txtConsecutivo = '" + strConsecutivo +"'";
                strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");
                
                if (strMensaje != null){    
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>");                                                     
                    out.println("alert('El contrato no pudo ser puesto en ejecución debido a un error de datos');");                 
                    out.println("</script>");                   
                    out.println("</body>");
                    out.println("</html>");
                }
                
                
            }
            
            //Accion: Finalizar contrato.
            
            if (strAccion.equals("FC")){
                String strFechaFin = request.getParameter("txtFechaFin");
                
                strSQL = "update nomina.tbl_contratos set txtIdEstado = 'FN', dtFechaFin = '" + strFechaFin + "' WHERE txtConsecutivo = '" + strConsecutivo +"'";        
                strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");
                
                if (strMensaje != null){    
                    out.println("<html>");
                    out.println("<head>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>");                                                     
                    out.println("alert('El contrato no pudo ser finalizado debido a un error de datos');");                 
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
