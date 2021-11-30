package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class buscarPersona_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    String strAccion = request.getParameter("txtAccion");
    

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>       \n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/buscar_persona.js\"></script>    \n");
      out.write("        ");
if(strAccion.equals("C")){
      out.write("\n");
      out.write("            <title>Búsqueda de contratista</title>\n");
      out.write("        ");
}else{
      out.write("\n");
      out.write("            <title>Búsqueda de interventor</title>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>        \n");
      out.write("        <div align=\"center\">\n");
      out.write("            <form id=\"frmBuscarContratista\" name=\"frmBuscarContratista\" method=\"POST\">\n");
      out.write("                <input type=\"hidden\" id=\"txtAccion\" name=\"txtAccion\" value=\"");
      out.print(strAccion);
      out.write("\" />\n");
      out.write("                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLAFORM\" width=\"99%\">                    \n");
      out.write("                    <tr>\n");
      out.write("                        ");
if(strAccion.equals("C")){
      out.write("\n");
      out.write("                            <td colspan=\"6\" class=\"TITULOFORM\">BÚSQUEDA DE CONTRATISTA</td>\n");
      out.write("                        ");
}else{
      out.write("\n");
      out.write("                            <td colspan=\"6\" class=\"TITULOFORM\">BÚSQUEDA DE INTERVENTOR</td>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td class=\"LABELFORM\"><label for=\"txtNumId\" id=\"lblNumId\">Nro. de identificación:</label></td>\n");
      out.write("                        <td class=\"CELDARADIOFORM\">\n");
      out.write("                            <input type=\"text\" name=\"txtNumId\" id=\"txtNumId\" class=\"CAMPOFORM\" />\n");
      out.write("                        </td>                               \n");
      out.write("                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                            <img src=\"Images/error.png\" id=\"imgNumId\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                        </td>                    \n");
      out.write("                        <td class=\"LABELFORM\"><label for=\"txtNombre\" id=\"lblNombre\">Nombre:</label></td>\n");
      out.write("                        <td class=\"CELDARADIOFORM\">\n");
      out.write("                            <input type=\"text\" name=\"txtNombre\" id=\"txtNombre\" class=\"CAMPOFORM\" />\n");
      out.write("                        </td>                               \n");
      out.write("                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                            <img src=\"Images/error.png\" id=\"imgNombre\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                            \n");
      out.write("                        </td>  \n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td colspan=\"6\" class=\"CELDABOTONFORM\">\n");
      out.write("                            <input type=\"button\" value=\"Buscar\" id=\"btnBuscar\" class=\"BOTONFORM\">&nbsp;&nbsp;\n");
      out.write("                            <input type=\"button\" value=\"Limpiar\" id=\"btnLimpiar\" class=\"BOTONFORM\"> &nbsp;&nbsp;\n");
      out.write("                            <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("            </form>\n");
      out.write("            <br />\n");
      out.write("            <div id=\"dResultados\">                             \n");
      out.write("            </div>\n");
      out.write("        </div>        \n");
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
