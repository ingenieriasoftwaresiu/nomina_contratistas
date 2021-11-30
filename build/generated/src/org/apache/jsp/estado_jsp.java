package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;

public final class estado_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
 
    String strAccion = (String) request.getParameter("txtAccion");        
    String strCodigo = (String) request.getParameter("txtCodigo");   
    
    String strSQL = "";
    String[] strDatosEstado = null;    
    
     if (strAccion == null) {
        response.sendRedirect("cerrar.jsp");
     }else{      
        
         if (strAccion.equals("V")){           
             strSQL = "SELECT e.txtCodigo, e.txtNombre from nomina.tbl_estados_contrato e where e.txtCodigo = '" + strCodigo + "'";        
             strDatosEstado = GestionSQL.getFila(strSQL,"Nomina");
        }
     }           

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/estado.js\"></script>\n");
      out.write("        <title>Administración: Estado</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("       </header>\n");
      out.write("       <section>\n");
      out.write("            <div align=\"center\">\n");
      out.write("                    <br><br>\n");
      out.write("                    ");
 if (strAccion.equals("C")){
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">NUEVO REGISTRO DE ESTADO</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmEstado\" name=\"frmEstado\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmEstado\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"C\">                       \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">\n");
      out.write("                                            <tr><td style=\"height: 10px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtCodigo\" id=\"lblCodigo\">* Código:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtCodigo\" id=\"txtCodigo\" class=\"CAMPOFORM\">                                     \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgCodigo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtNombre\" id=\"lblNombre\">* Nombre:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtNombre\" id=\"txtNombre\" class=\"CAMPOFORM\">                                               \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgNombre\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>                                    \n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 10px;\"></td></tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"CELDABOTONFORM\"><input type=\"button\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;<input type=\"button\" value=\"Limpiar\" id=\"btnLimpiar\" class=\"BOTONFORM\"> &nbsp;&nbsp;<input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\"></td></tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td></tr>\n");
      out.write("                                        </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table>            \n");
      out.write("                    ");
}else{
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">REGISTRO DE ESTADO</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmEstado\" name=\"frmEstado\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmEstado\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"V\">          \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">\n");
      out.write("                                            <tr><td style=\"height: 10px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtCodigo\" id=\"lblCodigo\">Código:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtCodigo\" id=\"txtCodigo\" value=\"");
      out.print(strDatosEstado[0]);
      out.write("\" class=\"CAMPOFORM\" readOnly>                                            \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgCodigo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtNombre\" id=\"lblNombre\">* Nombre:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtNombre\" id=\"txtNombre\" value=\"");
      out.print(strDatosEstado[1]);
      out.write("\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgNombre\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>                                     \n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 10px;\"></td></tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"CELDABOTONFORM\"><input type=\"button\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;<input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\"></td></tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td></tr>\n");
      out.write("                                        </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    <br>\n");
      out.write("                    <div id=\"dMensaje\">                                                 \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("        </section>\n");
      out.write("       <footer>        \n");
      out.write("        <div id=\"dFooter\" class=\"FOOTER\">\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("          \n");
      out.write("        </div>\n");
      out.write("       </footer>\n");
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
