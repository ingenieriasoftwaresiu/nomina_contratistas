package org.apache.jsp.Informes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class PagosXEstado_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../Styles/forms.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../Styles/informes.css\" />    \n");
      out.write("        <script type=\"text/javascript\" src=\"../Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"../Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"../Scripts/informes.js\"></script>\n");
      out.write("        <title>Informe: Pagos por estado</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "headerInformes.jsp", out, false);
      out.write("\n");
      out.write("        <div align=\"center\">            \n");
      out.write("            <br><br>              \n");
      out.write("            <form method=\"POST\" action=\"\" id=\"frmParametros\" name=\"frmParametros\">\n");
      out.write("                <table cellspacing=\"0\" cellpadding=\"0\" width=\"980px\" border=\"0\" class=\"TABLAMAESTRO\">                     \n");
      out.write("                    <tr>\n");
      out.write("                        <td colspan=\"3\" class=\"TITULOFORM\">PARÁMETROS PARA LA GENERACIÓN DEL INFORME</td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr><td style=\"height: 10px;\"></td></tr>\n");
      out.write("                    <tr>                       \n");
      out.write("                        <td style=\"vertical-align: middle;text-align: left;width: 150px;\">                  \n");
      out.write("                                <label for=\"txtIdEstado\" class=\"LABELFORM\">* Estado del pago:</label>&nbsp;&nbsp;  \n");
      out.write("                        </td>\n");
      out.write("                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                <select name=\"txtIdEstado\" id=\"txtIdEstado\" class=\"CAMPOFORM\" style=\"width: 200px;\">\n");
      out.write("                                    <option value=\"-1\">Seleccione una opción</option>     \n");
      out.write("                                    <option value=\"*\">--Todos los estados--</option>\n");
      out.write("                                    <option value=\"A\">Aprobado</option>\n");
      out.write("                                    <option value=\"E\">Ejecutado</option>\n");
      out.write("                                    <option value=\"P\">Pendiente</option>\n");
      out.write("                                    <option value=\"PA\">Pendiente Aprobación</option>    \n");
      out.write("                                    <option value=\"PRA\">PreAprobado</option> \n");
      out.write("                                </select>                                                              \n");
      out.write("                        </td>\n");
      out.write("                        <td style=\"vertical-align: middle;text-align: left;\">\n");
      out.write("                            &nbsp;&nbsp;<img src=\"../Images/error.png\" id=\"imgIdEstado\" alt=\"Campo obligatorio\" class=\"IMGERROR\" style=\"vertical-align: bottom;\">\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr><td style=\"height: 10px;\"></td></tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td colspan=\"3\" class=\"CELDABOTONFORM\">\n");
      out.write("                            <input type=\"button\" name=\"btnGenerar\" id=\"btnGenerar\" value=\"Generar\" class=\"BOTONFORM\">&nbsp;\n");
      out.write("                            <input type=\"button\" name=\"btnLimpiar\" id=\"btnLimpiar\" value=\"Limpiar\" class=\"BOTONFORM\">&nbsp;\n");
      out.write("                            <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                        </td>                        \n");
      out.write("                    </tr>                 \n");
      out.write("                </table>\n");
      out.write("            </form>                 \n");
      out.write("            <br>          \n");
      out.write("            <div id=\"dMostrarInforme\">                \n");
      out.write("            </div>\n");
      out.write("            <br>\n");
      out.write("        </div>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footerInformes.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
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
