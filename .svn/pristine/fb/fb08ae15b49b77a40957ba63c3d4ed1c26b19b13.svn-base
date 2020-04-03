/*
 * To change this template, choose Tools | Templates
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author jorge.correa
 */
public class Ingreso extends HttpServlet {

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
           String strUsuario = request.getParameter("txtUsuario");
           String strPwd = request.getParameter("txtPwd");
           String strTipoUsuario = request.getParameter("txtTipoUsuario");
           String strPreload = request.getParameter("preload");
           String strSQL="";
           String[] strDatos = null, strDatosAdmin=null, strDatosInterventor=null;           
           boolean continuar=false;
                      
           if(request.getParameter("preload") == null){
            if ((strTipoUsuario.equals("A")) || (strTipoUsuario.equals("I"))){ 
                strSQL = "select p.txtIdentificacion, p.txtNombreCompleto, p.txtAdministrador from users.users_personas p where p.txtUsuario = '" + strUsuario + "' and p.txtPassword = '" + strPwd +"'";
                strDatos = GestionSQL.getFila(strSQL,"Users");               
            }else{
                strSQL = "select c.txtNumId from nomina.tbl_contratistas c where c.txtNumId = '" + strUsuario + "' and c.txtNumId ='" +  strPwd + "'";
                strDatos = GestionSQL.getFila(strSQL,"Nomina");
            }      

            if (strDatos == null){    
                out.println("<html>");
                out.println("<head>");              
                out.println("</head>");
                out.println("<body>");
                out.println("El usuario y/o la contraseña ingresado(s) es(son) incorrecto(s).");
                out.println("</body>");
                out.println("</html>");
            }else{ 
                String strCedula = strDatos[0];

                if(strTipoUsuario.equals("A")){
                    strSQL = "SELECT rxp.txtIdPersona from nomina.tbl_roles_x_persona rxp where rxp.txtIdPersona = '" + strCedula + "' and rxp.txtIdRol = 'ADM'";
                    strDatosAdmin =  GestionSQL.getFila(strSQL,"Nomina");

                    if(strDatosAdmin != null){
                         continuar = true;
                    }
                }

                if(strTipoUsuario.equals("I")){
                    strSQL = "SELECT ixp.txtIdInterventor from nomina.tbl_interventores_x_proyecto ixp where ixp.txtIdInterventor = '" + strCedula + "' limit 1";
                    strDatosInterventor =  GestionSQL.getFila(strSQL,"Nomina");

                    if(strDatosInterventor != null){
                         continuar = true;
                    }
                }

                if(strTipoUsuario.equals("C")){
                    continuar = true;
                }

                if(continuar==true){
                     HttpSession session = request.getSession(true);                                 
                     out.println("txtCedula="+strCedula);         
                     session.setAttribute("txtCedula", strCedula);
                     session.setAttribute("txtTipoUsuario", strTipoUsuario);    
                     session.setAttribute("preload", "N");
                }else{
                    out.println("<html>");
                    out.println("<head>");              
                    out.println("</head>");
                    out.println("<body>");
                    out.println("No cuenta con el nivel de acceso seleccionado para ingresar al sistema. Por favor contacte al Administrador.");
                    out.println("</body>");
                    out.println("</html>");
                }
            }         
           }else{
               
            String[] strTemp=null;   
            strSQL = "select rxp.txtIdPersona from nomina.tbl_roles_x_persona rxp where rxp.txtIdRol = 'ADM' and rxp.txtIdPersona = '" + strUsuario + "'";
            strTemp = GestionSQL.getFila(strSQL,"Nomina");
            
            if (strTemp != null){
                strTipoUsuario = "A";
            }else{
                strTipoUsuario = "I";
            }
            
            HttpSession session = request.getSession(true);                                 
            out.println("txtCedula="+strUsuario);         
            session.setAttribute("txtCedula", strUsuario);
            session.setAttribute("txtTipoUsuario", strTipoUsuario);
            session.setAttribute("preload", "S");
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
