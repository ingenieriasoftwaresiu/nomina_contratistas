/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Conexion.GestionSQL;
import Jobs.ProgramacionCambiarEstadoContrato;
import Jobs.ProgramacionCambiarEstadoPago;
import Negocio.Comunes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author jorge.correa
 */
public class Acciones extends HttpServlet {

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
           
            String strAccion = request.getParameter("txtAccion");
            String strSQL = null,strTareaActiva="";             
            
            if (strAccion.equals("buscar_interventor")){
                String strNumId = request.getParameter("txtNumId");
                String strNombre = request.getParameter("txtNombre");
                String[] strTemp=null;
                Vector arrInterventores = null;
                Vector arrNumIds = new Vector();
                Vector arrNombres = new Vector();
                Comunes comun = new Comunes();
                
                if ((strNumId != "") && (strNombre != "")){
                    strSQL = "SELECT p.txtIdentificacion, p.txtNombreCompleto FROM users.users_personas p WHERE p.txtIdentificacion like '%" + strNumId +"%' and p.txtNombreCompleto like '%" + strNombre + "%' ORDER BY p.txtIdentificacion";
                }else{
                    if (strNumId != ""){
                        strSQL = "SELECT p.txtIdentificacion, p.txtNombreCompleto FROM users.users_personas p WHERE p.txtIdentificacion like '%" + strNumId +"%' ORDER BY p.txtIdentificacion";
                    }
                    
                    if (strNombre != ""){
                        strSQL = "SELECT p.txtIdentificacion, p.txtNombreCompleto FROM users.users_personas p WHERE p.txtNombreCompleto like '%" + strNombre + "%' ORDER BY p.txtIdentificacion";
                    }
                }
                                
                arrInterventores = GestionSQL.consultaSQL(strSQL, "Users", "2");
                
                if (arrInterventores != null){
                    out.println("<table cellspacing='0' cellpadding='5' border='0' class='TABLAFORM' width='99%'>");
                    out.println("<tr>");
                    out.println("<td class='TITULOFORM' colspan='3'>Resultados de la búsqueda</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td class='SUBTITULOFORM'>Número de identificación</td>");
                    out.println("<td class='SUBTITULOFORM'>Nombre del interventor</td>");
                    out.println("<td class='SUBTITULOFORM'>Seleccionar</td>");
                    out.println("</tr>");

                    for (int i=0;i<arrInterventores.size();i++){
                        strTemp = arrInterventores.get(i).toString().split(">");

                        out.println("<tr>");
                        out.println("<td class='TEXTONORMAL'>" + comun.marcarMiles(strTemp[0]) + "</td>");
                        out.println("<td class='TEXTONORMAL' style='text-align: left;'>" + strTemp[1]  + "</td>");
                        out.println("<td class='CELDAIMGERROR'><img src='Images/Aprobar.png' class='IMGERROR' onclick=\"seleccionarInterventor('" + strTemp[0]  +"')\" /></td>");
                        out.println("</tr>");                                                        
                    }                                     

                    out.println("</table>");
                }else{
                    out.println("<div class='TEXTOFALLO'>");
                    out.println("No se encontró ningún registro de Interventor que coincida con los criterios ingresados.");
                    out.println("</div>");
                }
            }
            
            if (strAccion.equals("buscar_contratista")){
                String strNumId = request.getParameter("txtNumId");
                String strNombre = request.getParameter("txtNombre");
                String[] strTemp=null;
                Vector arrContratistas = null;
                Vector arrNumIds = new Vector();
                Vector arrNombres = new Vector();
                Comunes comun = new Comunes();
                
                if ((strNumId != "") && (strNombre != "")){
                    strSQL = "SELECT c.txtNumId, CONCAT(c.txtApellidos,' ',c.txtNombres) FROM nomina.tbl_contratistas c WHERE c.txtNumId like '%" + strNumId + "%' and CONCAT(c.txtApellidos,' ',c.txtNombres) like '%" + strNombre + "%' ORDER BY c.txtNumId";
                }else{
                    if (strNumId != ""){
                        strSQL = "SELECT c.txtNumId, CONCAT(c.txtApellidos,' ',c.txtNombres) FROM nomina.tbl_contratistas c WHERE c.txtNumId like '%" + strNumId + "%' ORDER BY c.txtNumId";
                    }
                    
                    if (strNombre != ""){
                        strSQL = "SELECT c.txtNumId, CONCAT(c.txtApellidos,' ',c.txtNombres) FROM nomina.tbl_contratistas c WHERE CONCAT(c.txtApellidos,' ',c.txtNombres) like '%" + strNombre + "%' ORDER BY c.txtNumId";
                    }
                }
                                
                arrContratistas = GestionSQL.consultaSQL(strSQL, "Nomina", "2");
                
                if (arrContratistas != null){
                    out.println("<table cellspacing='0' cellpadding='5' border='0' class='TABLAFORM' width='99%'>");
                    out.println("<tr>");
                    out.println("<td class='TITULOFORM' colspan='3'>Resultados de la búsqueda</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td class='SUBTITULOFORM'>Número de identificación</td>");
                    out.println("<td class='SUBTITULOFORM'>Nombre del contratista</td>");
                    out.println("<td class='SUBTITULOFORM'>Seleccionar</td>");
                    out.println("</tr>");

                    for (int i=0;i<arrContratistas.size();i++){
                        strTemp = arrContratistas.get(i).toString().split(">");

                        out.println("<tr>");
                        out.println("<td class='TEXTONORMAL'>" + comun.marcarMiles(strTemp[0]) + "</td>");
                        out.println("<td class='TEXTONORMAL' style='text-align: left;'>" + strTemp[1]  + "</td>");
                        out.println("<td class='CELDAIMGERROR'><img src='Images/Aprobar.png' class='IMGERROR' onclick=\"seleccionarContratista('" + strTemp[0]  +"')\" /></td>");
                        out.println("</tr>");                                                        
                    }                                     

                    out.println("</table>");
                }else{
                    out.println("<div class='TEXTOFALLO'>");
                    out.println("No se encontró ningún registro de Contratista que coincida con los criterios ingresados.");
                    out.println("</div>");
                }
            }
            
             if (strAccion.equals("interventor")){
                 String strClave = request.getParameter("txtClave");
                 String[] strTemp=null;
                 Vector arrInterventores;
                 Vector arrIdsI = new Vector();
                 Vector arrNombresI = new Vector();
                 
                 if (strClave.equals("S")){
                     strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl where tbl.txtEstadoActual = 'A' ORDER BY tbl.txtNombreCompleto";
                    arrInterventores = GestionSQL.consultaSQL(strSQL,"Users","2");                    
                 }else{
                    strSQL = "select i.txtNumId, CONCAT(i.txtNombres,' ',i.txtApellidos) as NombreCompleto from nomina.tbl_interventores i ORDER BY NombreCompleto";
                    arrInterventores = GestionSQL.consultaSQL(strSQL,"Nomina","2");                   
                 }
                 
                for (int i=0;i<arrInterventores.size();i++){
                    strTemp = arrInterventores.get(i).toString().split(">");
                    arrIdsI.add(strTemp[0]);
                    arrNombresI.add(strTemp[1]);
                }
                
                out.println("<script type='text/javascript'>");
                out.println("$('.IMGERROR').hide();");
                out.println("</script>");
                out.println("<table cellspacing='0' cellpadding='5' border='0' class='TABLACONTENEDORA'>");
                out.println("<tr>");
                out.println("<td class='LABELFORM'><label for='txtInterventor' id='lblInterventor'>* Nombre del interventor:</label></td>");
                out.println("<td class='CELDACAMPOFORM'>");
                out.println("<select id='txtInterventor' name='txtInterventor' class='CAMPOSELECT'>");
                out.println("<option value='-1'>Seleccione una opción</option>");
                for (int i=0;i<arrIdsI.size();i++){
                    out.println("<option value='" + arrIdsI.get(i) + "'>" + arrNombresI.get(i) + "</option>");
                }
                out.println("</select>");                     
                out.println("</td>");
                out.println("<td class='CELDAIMGERROR'>");
                out.println("<img src='Images/error.png' id='imgInterventor' alt='Campo obligatorio' class='IMGERROR'>");
                out.println("</td>");
                out.println("<td class='LABELFORM'><label for='txtCodProy' id='lblCodProy'>* Código del proyecto:</label></td>");                                        
                out.println("<td class='CELDACAMPOFORM'>");
                out.println("<input type='text' name='txtCodProy' id='txtCodProy' class='CAMPOFORM'>");                                           
                out.println("</td>");
                out.println("<td class='CELDAIMGERROR'>");
                out.println("<img src='Images/error.png' id='imgCodProy' alt='Campo obligatorio' class='IMGERROR'>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");                
                 
             }
             
             HttpSession session = request.getSession(true);
             
             if ((strAccion.equals("CambioEstadoContrato")) || (strAccion.equals("CambioEstadoPago"))){
                 strTareaActiva = "N";
                 
                 if (strAccion.equals("CambioEstadoContrato")){
                     ProgramacionCambiarEstadoContrato pcec = new ProgramacionCambiarEstadoContrato();
                     pcec.crearProgramacion();
                     strTareaActiva = "S";
                     out.println("<div class='TEXTOEXITO'>");
                     out.println("Job: CambiarEstadoContrato ha sido iniciado correctamente.");
                     out.println("</div>");
                 }
                 
                 if (strAccion.equals("CambioEstadoPago")){
                     ProgramacionCambiarEstadoPago pcep = new ProgramacionCambiarEstadoPago();
                     pcep.crearProgramacion();
                     strTareaActiva = "S";
                     out.println("<div class='TEXTOEXITO'>");
                     out.println("Job: CambiarEstadoPago ha sido iniciado correctamente.");
                     out.println("</div>");
                 }
                 session.setAttribute("txtTareaActiva", strTareaActiva);
             }
             
             if (strAccion.equals("DetenerJobs")){
                 if (session.getAttribute("txtTareaActiva") != null){
                    strTareaActiva = session.getAttribute("txtTareaActiva").toString();
                }else{
                    strTareaActiva = "N";
                }
                
                try{
                    if (strTareaActiva.equals("S")){
                        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                        scheduler.shutdown(false);
                        strTareaActiva = "N";
                        session.setAttribute("txtTareaActiva", strTareaActiva);
                        out.println("<div class='TEXTOEXITO'>");
                        out.println("Los jobs activos fueron detenidos correctamente.");
                        out.println("</div>");                
                    }                    
                }catch(SchedulerException se){
                      out.println(se.getMessage());
                }catch(Exception e){
                     out.println(e.getMessage());
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
