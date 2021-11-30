package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Vector;
import Conexion.GestionSQL;

public final class rolXpersona_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
 
    String strAccion = (String) request.getParameter("txtAccion");        
    String strCodigo = (String) request.getParameter("txtCodigo");   
    
    String strSQL = "";
    String[] strDatosRegistro = null;    
    String[] strTemp = null;
    Vector arrIdsP = new Vector();
    Vector arrNombresP = new Vector();
    Vector arrIdsR = new Vector();
    Vector arrNombresR = new Vector();
    
     if (strAccion == null) {
        response.sendRedirect("cerrar.jsp");
     }else{      
        
        strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl where tbl.txtEstadoActual = 'A' ORDER BY tbl.txtNombreCompleto";
        Vector arrPersonas = GestionSQL.consultaSQL(strSQL,"Users","2");
  
        for (int i=0;i<arrPersonas.size();i++){
            strTemp = arrPersonas.get(i).toString().split(">");
            arrIdsP.add(strTemp[0]);
            arrNombresP.add(strTemp[1]);
        }
        
        strSQL = "select tbl.txtCodigo, tbl.txtNombre from nomina.tbl_roles tbl ORDER BY tbl.txtNombre";
        Vector arrRoles = GestionSQL.consultaSQL(strSQL,"Nomina","2");
  
        for (int i=0;i<arrRoles.size();i++){
            strTemp = arrRoles.get(i).toString().split(">");
            arrIdsR.add(strTemp[0]);
            arrNombresR.add(strTemp[1]);
        }
        
         if (strAccion.equals("V")){           
            strTemp = strCodigo.split(">");
            strSQL = "select tbl.txtIdPersona, tbl.txtIdRol from nomina.tbl_roles_x_persona tbl where tbl.txtIdPersona = '" + strTemp[0] + "' and tbl.txtIdRol = '" + strTemp[1] + "'";
             strDatosRegistro = GestionSQL.getFila(strSQL,"Nomina");
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
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/rolXpersona.js\"></script>\n");
      out.write("        <title>Administración: Rol por persona</title>\n");
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
      out.write("                                <td class=\"TITULOFORM\">NUEVO REGISTRO DE ROL POR PERSONA</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmRolXPersona\" name=\"frmRolXPersona\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmRolXPersona\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"C\">                       \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">\n");
      out.write("                                            <tr><td style=\"height: 10px;\"></td></tr>                                                           \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtPersona\" id=\"lblPersona\">* Nombre de la persona:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <select id=\"txtPersona\" name=\"txtPersona\" class=\"CAMPOSELECT\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsP.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsP.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresP.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>                                  \n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgPersona\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtRol\" id=\"lblRol\">* Rol a asignar:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <select id=\"txtRol\" name=\"txtRol\" class=\"CAMPOSELECT\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsR.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsR.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresR.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>                                  \n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgRol\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>                                                    \n");
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
      out.write("                                <td class=\"TITULOFORM\">REGISTRO DE ROL POR PERSONA</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmRolXPersona\" name=\"frmRolXPersona\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmRolXPersona\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"V\">          \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">\n");
      out.write("                                            <tr><td style=\"height: 10px;\"></td></tr>                                                           \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtPersona\" id=\"lblPersona\">Nombre de la persona:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <select id=\"txtPersona\" name=\"txtPersona\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsP.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsP.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresP.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>                   \n");
      out.write("                                                <script type=\"text/javascript\">\n");
      out.write("                                                     $(\"#txtPersona option[value='");
      out.print(strDatosRegistro[0]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                </script>\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgPersona\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtRol\" id=\"lblRol\">Rol a asignar:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <select id=\"txtRol\" name=\"txtRol\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsR.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsR.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresR.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>    \n");
      out.write("                                                <script type=\"text/javascript\">\n");
      out.write("                                                     $(\"#txtRol option[value='");
      out.print(strDatosRegistro[1]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                </script>\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgRol\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>                                            \n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 10px;\"></td></tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"CELDABOTONFORM\"><input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\"></td></tr>\n");
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
