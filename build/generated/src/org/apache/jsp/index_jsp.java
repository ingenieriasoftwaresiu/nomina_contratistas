package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");

    String strUsuario=null;
    String strPreload=null;
    strUsuario = request.getParameter("txtCedula");  
    strPreload = request.getParameter("preload");  

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/comunes.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/login.js\"></script>\n");
      out.write("        <title>Sistema de Gestión de Pagos a Contratistas - Inicio de sesión</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("       </header>\n");
      out.write("        <br>\n");
      out.write("        <div style=\"padding-top: 50px;\"></div>\n");
      out.write("        ");
if (strUsuario == null){
      out.write("\n");
      out.write("                <div align=\"center\">      \n");
      out.write("                    <br>\n");
      out.write("                    <form id=\"frmLogin\" name=\"frmLogin\" method=\"POST\" action=\"Ingreso\">\n");
      out.write("                            <table cellspacing=\"0\" cellpadding=\"0\" class=\"TABLAMAESTRO\" border=\"0\" style=\"width:320px;\">                        \n");
      out.write("                                <tr>\n");
      out.write("                                        <td colspan=\"3\" style=\"text-align: center;\" class=\"TITULOFORM\">INICIO DE SESIÓN</td>\t\t\t\t\n");
      out.write("                                </tr>                   \n");
      out.write("                                <tr>\n");
      out.write("                                    <td class=\"LABELFORM\" style=\"width: 90px;padding-left: 10px;\">* Tipo de usuario:</td>\n");
      out.write("                                    <td class=\"CELDARADIOFORM\" style=\"vertical-align: middle;text-align: center;\">\n");
      out.write("                                        <select name=\"txtTipoUsuario\" id=\"txtTipoUsuario\" class=\"CAMPOSELECT\" style=\"width:145px;\">\n");
      out.write("                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                            <option value=\"A\">Administrador</option>\n");
      out.write("                                            <option value=\"C\">Contratista</option>\n");
      out.write("                                            <option value=\"I\">Interventor</option>\n");
      out.write("                                        </select>                                    \n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <img src=\"Images/error.png\" id=\"imgTipoUsuario\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                        <td class=\"LABELFORM\" style=\"width: 90px;padding-left: 10px;\">* Usuario:</td>\n");
      out.write("                                        <td class=\"CELDACAMPOFORM\" style=\"width: 150px;text-align: center;\">\n");
      out.write("                                            <input type=\"text\" name=\"txtUsuario\" id=\"txtUsuario\" class=\"CAMPOFORM\" style=\"width: 142px;\">\n");
      out.write("                                        </td>\n");
      out.write("                                        <td style=\"width: 30px;text-align: left;\"><img src=\"Images/error.png\" id=\"imgUsuario\" alt=\"Campo obligatorio\" class=\"IMGERROR\"></td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                        <td class=\"LABELFORM\" style=\"width: 90px;padding-left: 10px;\">* Contraseña:</td>\n");
      out.write("                                        <td class=\"CELDACAMPOFORM\" style=\"width: 150px;text-align: center;\">\n");
      out.write("                                            <input type=\"password\" name=\"txtPwd\" id=\"txtPwd\" class=\"CAMPOFORM\" style=\"width: 142px;\">\n");
      out.write("                                        </td>\n");
      out.write("                                        <td style=\"width: 30px;text-align: left;\"><img src=\"Images/error.png\" id=\"imgPwd\" alt=\"Campo obligatorio\" class=\"IMGERROR\"></td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                        <td colspan=\"3\" class=\"CELDABOTONFORM\">\n");
      out.write("                                            <input type=\"button\" value=\"Ingresar\" id=\"btnIngresar\" class=\"BOTONFORM\">&nbsp;&nbsp;\n");
      out.write("                                            <input type=\"button\" value=\"Limpiar\" id=\"btnLimpiar\" class=\"BOTONFORM\">                                                \n");
      out.write("                                        </td>\t\t\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td class=\"MSGAVISOOBLG\" colspan=\"3\" style=\"padding-left: 3px;padding-bottom: 3px;\">(*) Campo obligatorio.</td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\t\t\t\t\n");
      out.write("                    </form>\n");
      out.write("                    <br>\n");
      out.write("                    <div id=\"dMensaje\" class=\"TEXTOFALLO\">                \n");
      out.write("                    </div>\n");
      out.write("                </div>  \n");
      out.write("          ");
}else{
      out.write("\n");
      out.write("                <script type=\"text/javascript\">\n");
      out.write("                    var dataString = \"txtUsuario=\"+ ");
      out.print(strUsuario);
      out.write(" + \"&preload=\" + ");
      out.print(strPreload);
      out.write(";                             \n");
      out.write("                    setTimeout(function(){AJAX_REDIRECT(\"POST\",\"Ingreso\",dataString,\"dMensaje\",\"principal.jsp\");},3000);\n");
      out.write("                </script>\n");
      out.write("                <div align=\"center\">              \n");
      out.write("                    <div style=\"padding-top: 80px;\"></div>            \n");
      out.write("                    <table cellspacing=\"0\" cellpadding=\"0\" width=\"600px\"  border=\"0\">                        \n");
      out.write("                        <tr>\n");
      out.write("                                <td class=\"TEXTOHOMEBIG\">BIENVENIDO!</td>                                \n");
      out.write("                        </tr>\n");
      out.write("                        <tr><td height=\"10px\"></td></tr>\n");
      out.write("                        <tr>\n");
      out.write("                                <td class=\"TEXTOHOMESMALL\">Un momento por favor, la aplicación se está cargando para su acceso.</td>                                \n");
      out.write("                        </tr>\n");
      out.write("                        <tr><td height=\"15px\"></td></tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td style=\"text-align: center;\"><img src=\"Images/loader.gif\" width=\"40\" height=\"40\"></td>\t\t\n");
      out.write("                        </tr>\n");
      out.write("                    </table>      \n");
      out.write("                </div>\n");
      out.write("          ");
}
      out.write("\n");
      out.write("          <br><br>\n");
      out.write("        <div align=\"center\" class=\"MSGBROWSER\">\n");
      out.write("            <<< Para una mejor visualización, se recomienda utilizar <b>Mozilla Firefox</b> ó <b>Google Chrome</b> en una resolución superior 1024 x 768. >>>\n");
      out.write("        </div> \n");
      out.write("        <footer>\n");
      out.write("            <div id=\"dFooter\" class=\"FOOTER\">\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("          \n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
