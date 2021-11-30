package org.apache.jsp.Informes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class PagosXPeriodo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../Styles/informes.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../Styles/calendar-system.css\" />       \n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../Styles/print.css\" media=\"print\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"../Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"../Scripts/JSCalendar.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"../Scripts/JSCalendar-es.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"../Scripts/JSCalendar-setup.js\"></script>        \n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"../Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"../Scripts/informes.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"../Scripts/comunes.js\"></script>\n");
      out.write("        <title>Informe: Pagos por periodo</title>            \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "headerInformes.jsp", out, false);
      out.write("\n");
      out.write("        <div align=\"center\">            \n");
      out.write("            <br><br>        \n");
      out.write("            <div id=\"dParametros\">\n");
      out.write("                <form method=\"POST\" action=\"\" id=\"frmParametros\" name=\"frmParametros\">\n");
      out.write("                    <table cellspacing=\"0\" cellpadding=\"0\" width=\"980px\" border=\"0\" class=\"TABLAMAESTRO\">                     \n");
      out.write("                        <tr>\n");
      out.write("                            <td colspan=\"6\" class=\"TITULOFORM\">PARÁMETROS PARA LA GENERACIÓN DEL INFORME</td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr><td colspan=\"6\" style=\"height: 10px;\"></td></tr>\n");
      out.write("                        <tr>                       \n");
      out.write("                            <td class=\"LABELFORM\"><label for=\"txtFechaIni\" id=\"lblFechaIni\">* Fecha de inicio:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                            <td class=\"CELDARADIOFORM\">\n");
      out.write("                                <input type=\"text\" name=\"txtFechaIni\" id=\"txtFechaIni\" class=\"CAMPOFORM\" style=\"width: 150px;\" readOnly>&nbsp;<img src=\"../Images/Calendario.JPG\" class=\"IMGCALENDAR\" id=\"imgFechaIni\">\n");
      out.write("                            </td>                               \n");
      out.write("                            <td class=\"CELDAIMGERROR\" style=\"text-align: center;width: 70px;\">\n");
      out.write("                                <img src=\"../Images/error.png\" id=\"imgFechaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                            </td>\n");
      out.write("                            <td class=\"LABELFORM\"><label for=\"txtFechaFin\" id=\"lblFechaFin\">* Fecha de fin:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                            <td class=\"CELDARADIOFORM\">\n");
      out.write("                                <input type=\"text\" name=\"txtFechaFin\" id=\"txtFechaFin\" class=\"CAMPOFORM\" style=\"width: 150px;\" readOnly>&nbsp;<img src=\"../Images/Calendario.JPG\" class=\"IMGCALENDAR\" id=\"imgFechaFin\">\n");
      out.write("                            </td>                               \n");
      out.write("                            <td class=\"CELDAIMGERROR\" style=\"text-align: center;width: 70px;\">\n");
      out.write("                                <img src=\"../Images/error.png\" id=\"imgFechaFinal\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr><td colspan=\"6\"  style=\"height: 10px;\"></td></tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td colspan=\"6\" class=\"CELDABOTONFORM\">\n");
      out.write("                                <input type=\"button\" name=\"btnGenerarPP\" id=\"btnGenerarPP\" value=\"Generar\" class=\"BOTONFORM\">&nbsp;\n");
      out.write("                                <input type=\"button\" name=\"btnLimpiarPP\" id=\"btnLimpiarPP\" value=\"Limpiar\" class=\"BOTONFORM\">&nbsp;\n");
      out.write("                                <input type=\"button\" name=\"btnImprimir\" id=\"btnImprimir\" value=\"Imprimir\" class=\"BOTONFORM\" style=\"display: none;\">&nbsp;\n");
      out.write("                                <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                            </td>                        \n");
      out.write("                        </tr>                 \n");
      out.write("                    </table>\n");
      out.write("                    <script language=\"javascript\" type=\"text/javascript\">\n");
      out.write("                        cargarCalendarios();\n");
      out.write("                    </script>\n");
      out.write("                </form>    \n");
      out.write("            </div>\n");
      out.write("            <div id=\"dVolver\">\n");
      out.write("                <div class=\"noPrint\">             \n");
      out.write("                    <input type=\"button\" name=\"btnVolver\" id=\"btnVolver\" value=\"Volver\" class=\"BOTONFORM\"><br /><br /> \n");
      out.write("                    <span class=\"TEXTOFALLO\">                        \n");
      out.write("                        RECOMENDACIÓN: Para una mejor visualización, imprima la hoja en sentido horizontal desde las propiedades de la impresora.\n");
      out.write("                    </span><br />\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <br />          \n");
      out.write("            <div id=\"dMostrarInforme\">                \n");
      out.write("            </div>\n");
      out.write("            <br />\n");
      out.write("        </div>        \n");
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
