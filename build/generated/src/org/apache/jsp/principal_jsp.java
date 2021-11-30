package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;

public final class principal_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "comprobarSesion.jsp", out, false);
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

    String strUsuario = null;
    String strPreload = null;
    String strTipoUsuario = null;
    String[] strTemp = null;
    String[] strDatosDllo = null;
    String[] strNombreUsuario = null;
    String strSQL = "";
    strUsuario = (String) request.getParameter("txtCedula");
    strPreload = (String) session.getAttribute("preload");
    strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
         
    if ((strUsuario == null) || session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);    
    }else{             
        if ((strTipoUsuario.equals("A")) || (strTipoUsuario.equals("I"))){       
           strSQL = "select p.txtNombreCompleto from users.users_personas p where p.txtIdentificacion = '" + strUsuario + "'";
           strNombreUsuario = GestionSQL.getFila(strSQL,"Users");

           strSQL = "select rxp.txtIdPersona from nomina.tbl_roles_x_persona rxp where rxp.txtIdPersona = '" + strUsuario + "' and rxp.txtIdRol = 'DES'";
           strDatosDllo = GestionSQL.getFila(strSQL,"Nomina");

        }else{
           strSQL = "select CONCAT(c.txtNombres,' ',c.txtApellidos) from nomina.tbl_contratistas c where c.txtNumId = '" + strUsuario + "'";
           strNombreUsuario = GestionSQL.getFila(strSQL,"Nomina");
        }
   }

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/menu.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/visualizacion.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/principal.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/visualizacion.js\"></script>\n");
      out.write("        <title>Sistema de Gestión de Pagos a Contratistas: Menú principal</title>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function disableKeyPress(evt){               \n");
      out.write("                var evt = (evt) ? evt : ((event) ? event : null); \n");
      out.write("                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); \n");
      out.write("                if ((evt.keyCode == 13) && (node.type==\"text\"))  {return false;} \n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body onKeyPress=\"disableKeyPress(event);\">        \n");
      out.write("        <input type=\"hidden\" name=\"txtUsuario\" id=\"txtUsuario\" value=\"");
      out.print(strUsuario);
      out.write("\">\n");
      out.write("        <input type=\"hidden\" name=\"txtTipoUsuario\" id=\"txtTipoUsuario\" value=\"");
      out.print(strTipoUsuario);
      out.write("\">\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("        <div align=\"center\">            \t   \n");
      out.write("            <br>\n");
      out.write("            <table cellspacing=\"0\" cellpadding=\"0\" width=\"99%\" border=\"0\">                \n");
      out.write("                    <tr>\n");
      out.write("                        ");
 if(strNombreUsuario != null){ 
      out.write("\n");
      out.write("                            ");
if (strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                <td class=\"USERLOGED\" style=\"text-align: left;\"><b>Bienvenido(a),</b>&nbsp;<span style=\"font-weight: normal;\">");
      out.print(strNombreUsuario[0]);
      out.write("&nbsp;[Administrador]</span></td>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                            ");
if (strTipoUsuario.equals("I")){
      out.write("\n");
      out.write("                                <td class=\"USERLOGED\" style=\"text-align: left;\"><b>Bienvenido(a),</b>&nbsp;<span style=\"font-weight: normal;\">");
      out.print(strNombreUsuario[0]);
      out.write("&nbsp;[Interventor]</span></td>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                            ");
if (strTipoUsuario.equals("C")){
      out.write("\n");
      out.write("                                <td class=\"USERLOGED\" style=\"text-align: left;\"><b>Bienvenido(a),</b>&nbsp;<span style=\"font-weight: normal;\">");
      out.print(strNombreUsuario[0]);
      out.write("&nbsp;[Contratista]</span></td>             \n");
      out.write("                            ");
}
      out.write("                        \n");
      out.write("                        ");
}else{
      out.write("\n");
      out.write("                            <td class=\"USERLOGED\" style=\"text-align: left;\"><b>Bienvenido(a)!</b></td> \n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                        ");
if (strPreload.equals("N")){
      out.write("     \n");
      out.write("                            <td class=\"CELDAACCION\" style=\"border-left: 1px solid #116043;width: 120px;text-align: right;vertical-align: middle;\"><input type=\"button\" value=\"Salir\" class=\"BOTONACCION\" onclick=\"location.href='cerrar.jsp';\" /></td>\n");
      out.write("                        ");
}else{
      out.write("\n");
      out.write("                            <td class=\"CELDAACCION\" style=\"border-left: 1px solid #116043;width: 120px;text-align: right;vertical-align: middle;\"><input type=\"button\" value=\"Salir\" class=\"BOTONACCION\" onclick=\"javascript:window.close();\" /></td>\n");
      out.write("                        ");
}
      out.write("                                            \n");
      out.write("                    </tr>                                \n");
      out.write("            </table>\n");
      out.write("            <br>\n");
      out.write("             <table cellspacing=\"0\" cellpadding=\"0\" width=\"99%\" border=\"0\">\n");
      out.write("                <tr>\n");
      out.write("                    <td width=\"18%\" style=\"vertical-align: text-top;\">   \n");
      out.write("                        ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                            <table cellspacing=\"0\" cellpadding=\"5\" width=\"100%\" border=\"0\" class=\"TABLAMENU\">\n");
      out.write("                                <tr><td class=\"TITULOMENU\" style=\"font-size: 15px;\">MENÚ PRINCIPAL</td></tr>                         \n");
      out.write("                                <tr><td class=\"ITEMMENU\" id=\"itPersonas\"><a href=\"#\">Contratistas</a></td></tr>                                    \n");
      out.write("                                <tr><td class=\"ITEMMENU\" id=\"itContratos\"><a href=\"#\">Contratos activos</a></td></tr>                                                  \n");
      out.write("                                <tr><td class=\"ITEMMENU\" id=\"itContratosH\"><a href=\"#\">Contratos históricos</a></td></tr>\n");
      out.write("                                <tr><td class=\"SUBTITULOMENU\">Informes</td></tr>\n");
      out.write("                                <tr><td class=\"ITEMMENU\"><a href=\"#\" onclick=\"javascript:window.open('Informes/PagosXEstado.jsp','Pagos_X_Estado','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',scrollbars=yes')\">Pagos por estado</a></td></tr>                                                                                         \n");
      out.write("                                <tr><td class=\"ITEMMENU\"><a href=\"#\" onclick=\"javascript:window.open('Informes/PagosXPeriodo.jsp','Pagos_X_Periodo','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',scrollbars=yes')\">Pagos por periodo</a></td></tr>                                                                                         \n");
      out.write("                                <tr><td class=\"ITEMMENU\"><a href=\"#\" onclick=\"generarArchivoPlanoContrato()\">Archivo plano de Contratos</a></td></tr>                                                                                         \n");
      out.write("                                <tr><td class=\"SUBTITULOMENU\">Administración</td></tr>\n");
      out.write("                                <tr><td class=\"ITEMMENU\"><a href=\"#\" onclick=\"javascript:window.open('admin.jsp','Administracion','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',scrollbars=yes')\">Parámetros del sistema</a></td></tr>\n");
      out.write("                               ");
if(strDatosDllo != null){
      out.write("\n");
      out.write("                                    <tr><td class=\"SUBTITULOMENU\">Desarrollador</td></tr>\n");
      out.write("                                    <tr><td class=\"ITEMMENU\" id=\"itCambioEstadoContrato\"><a href=\"#\">Iniciar Job: Cambio de estado Contratos</a></td></tr>                                                      \n");
      out.write("                                    <tr><td class=\"ITEMMENU\" id=\"itCambioEstadoPago\"><a href=\"#\">Iniciar Job: Cambio de estado Pagos</a></td></tr>\n");
      out.write("                                    <tr><td class=\"ITEMMENU\" id=\"itDetenerJobs\"><a href=\"#\">Detener Jobs</a></td></tr>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                            </table>     \n");
      out.write("                        ");
}else{
      out.write("                            \n");
      out.write("                                <table cellspacing=\"0\" cellpadding=\"5\" width=\"100%\" border=\"0\" class=\"TABLAMENU\">\n");
      out.write("                                    <tr><td class=\"TITULOMENU\" style=\"font-size: 15px;\">MENÚ PRINCIPAL</td></tr>       \n");
      out.write("                                    <tr><td class=\"ITEMMENU\" id=\"itContratosP\"><a href=\"#\">Contratos activos</a></td></tr>                                     \n");
      out.write("                                    <tr><td class=\"ITEMMENU\" id=\"itContratosPH\"><a href=\"#\">Contratos históricos</a></td></tr>\n");
      out.write("                                </table>                                                      \n");
      out.write("                         ");
}
      out.write("\n");
      out.write("                    </td>\n");
      out.write("                    <td width=\"1%\"></td>\n");
      out.write("                    <td style=\"width:80%;text-align: center;vertical-align: top;\">\n");
      out.write("                        <div id=\"dMostrar\">                            \n");
      out.write("                        </div>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("        <br>\t\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("     </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
