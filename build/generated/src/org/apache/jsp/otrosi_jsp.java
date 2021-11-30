package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;

public final class otrosi_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write(' ');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "comprobarSesion.jsp", out, false);
      out.write('\n');

    String strConsecutivo = (String) request.getParameter("txtConsecutivo");
    String strAccion = (String)request.getParameter("txtAccion");
    String strTipoUsuario = null, strCedula=null, strSQL = "";
    String[] strDatosUsuario = null, strDatosContrato = null, strDatosOTROSI=null;
    
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
        strCedula = (String) session.getAttribute("txtCedula");
        
        strSQL = "SELECT txtNombreCompleto FROM users.users_personas where txtIdentificacion = '" + strCedula + "'";
        strDatosUsuario = GestionSQL.getFila(strSQL, "Nomina");        
        
        strSQL = "SELECT dtFechaFin FROM nomina.tbl_contratos WHERE txtConsecutivo = '" + strConsecutivo + "'";
        strDatosContrato = GestionSQL.getFila(strSQL, "Nomina");
        
        if (strAccion.equals("V")){
            
            String strCodigoOTROSI = request.getParameter("txtCodigo");
            
            strSQL = "SELECT * from nomina.tbl_otrosi_contratos where txtCodigo = '" + strCodigoOTROSI + "' and txtIdContrato = '" + strConsecutivo + "'";
            strDatosOTROSI = GestionSQL.getFila(strSQL,"Nomina");
        }
    }


      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/calendar-system.css\" />        \n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/JSCalendar.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/JSCalendar-es.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/JSCalendar-setup.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery.filestyle.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>  \n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/otrosi.js\"></script>\n");
      out.write("        <title>OTROSI del contrato ");
      out.print(strConsecutivo);
      out.write("</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("       </header>\n");
      out.write("       <section>            \n");
      out.write("            <div align=\"center\">\n");
      out.write("                <br /><br />\n");
      out.write("                ");
 if (strAccion.equals("C")){
      out.write("                    \n");
      out.write("                    <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"TITULOFORM\">NUEVO OTROSI DEL CONTRATO ");
      out.print(strConsecutivo);
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td>\n");
      out.write("                                <form method=\"POST\" id=\"frmOTROSI\" name=\"frmOTROSI\" enctype=\"multipart/form-data\" action=\"RegistroOTROSI\" onsubmit=\"return validarOTROSI();\">\n");
      out.write("                                    <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmOTROSI\" />                   \n");
      out.write("                                    <input type=\"hidden\" name=\"txtIdAutor\" id=\"txtIdAutor\" value=\"");
      out.print(strCedula);
      out.write("\" />             \n");
      out.write("                                    <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"");
      out.print(strAccion);
      out.write("\">\n");
      out.write("                                    <input type=\"hidden\" name=\"txtConsecutivo\" id=\"txtConsecutivo\" value=\"");
      out.print(strConsecutivo);
      out.write("\">\n");
      out.write("                                    <input type=\"hidden\" name=\"txtRutaActa\" id=\"txtRutaActa\" value=\"-\" />\n");
      out.write("                                    <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td class=\"LABELFORM\">\n");
      out.write("                                                <label for=\"txtFechaCreacion\" id=\"lblFechaCreacion\">Fecha de creación:<br />(aaaa-mm-dd)</label>\n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                <input type=\"text\" name=\"txtFechaCreacion\" id=\"txtFechaCreacion\" class=\"CAMPOFORM\" readOnly>\n");
      out.write("                                            </td>                               \n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgFechaCreacion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                            </td>                    \n");
      out.write("                                            <td class=\"LABELFORM\">\n");
      out.write("                                                <label for=\"txtAutor\" id=\"lblAutor\">Nombre del autor(a):</label>\n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                ");

                                                    strSQL = "SELECT txtNombreCompleto FROM users.users_personas where txtIdentificacion = '" + strCedula + "'";
                                                    strDatosUsuario = GestionSQL.getFila(strSQL, "Nomina");
                                                
      out.write("\n");
      out.write("                                                <input type=\"text\" name=\"txtAutor\" id=\"txtAutor\" value=\"");
      out.print(strDatosUsuario[0]);
      out.write("\" class=\"CAMPOFORM\" readonly>\n");
      out.write("                                            </td>                               \n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgAutor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                            \n");
      out.write("                                            </td>                                                \n");
      out.write("                                        </tr>\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td class=\"LABELFORM\">\n");
      out.write("                                                <label for=\"txtFechaFinActual\" id=\"lblFechaFinActual\">Fecha de finalización actual:<br />(aaaa-mm-dd)</label>\n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                <input type=\"text\" name=\"txtFechaFinActual\" id=\"txtFechaFinActual\" value=\"");
      out.print(strDatosContrato[0].toString());
      out.write("\" class=\"CAMPOFORM\" readonly>                                                    \n");
      out.write("                                            </td>                               \n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgFechaFinActual\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                     \n");
      out.write("                                            </td>                                                \n");
      out.write("                                            <td class=\"LABELFORM\">\n");
      out.write("                                                <label for=\"txtFechaFinNueva\" id=\"lblFechaFinNueva\">* Fecha de finalización nueva:<br />(aaaa-mm-dd)</label>\n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                <input type=\"text\" name=\"txtFechaFinNueva\" id=\"txtFechaFinNueva\" class=\"CAMPOFORM\" style=\"width: 180px;\" readonly>\n");
      out.write("                                                &nbsp;<img src=\"Images/Calendario.JPG\" class=\"IMGCALENDAR\" id=\"imgFechaFinNueva\">\n");
      out.write("                                            </td>                               \n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgFechaFinNuevaC\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                     \n");
      out.write("                                            </td>                                                 \n");
      out.write("                                        </tr>\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td class=\"LABELFORM\">\n");
      out.write("                                                <label for=\"txtJustificacion\" id=\"lblJustificacion\">* Justificación:</label>\n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\" colspan=\"4\">\n");
      out.write("                                                <textarea name=\"txtJustificacion\" id=\"txtJustificacion\" rows=\"4\" cols=\"50\" wrap=\"soft\" class=\"TEXTAREA\">                                                        \n");
      out.write("                                                </textarea>                                                                                                       \n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgJustificacion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                     \n");
      out.write("                                            </td>\n");
      out.write("                                        </tr>\n");
      out.write("                                        <tr>                                                                    \n");
      out.write("                                            <td class=\"LABELFORM\"><label for=\"txtActa\" id=\"lblActa\">* Acta de oficialización:</label></td>                                        \n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                <input type=\"file\" name=\"txtActa\" id=\"txtActa\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>              \n");
      out.write("                                            </td>                                                      \n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgActa\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                            </td>\n");
      out.write("                                        </tr>\n");
      out.write("                                        <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td colspan=\"6\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                <input type=\"submit\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;\n");
      out.write("                                                <input type=\"button\" value=\"Limpiar\" id=\"btnLimpiar\" class=\"BOTONFORM\"> &nbsp;&nbsp;\n");
      out.write("                                                <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                            </td>\n");
      out.write("                                        </tr>\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td>\n");
      out.write("                                        </tr>\n");
      out.write("                                        <script type=\"text/javascript\">\n");
      out.write("                                            cargarCalendarios();\n");
      out.write("                                        </script>  \n");
      out.write("                                    </table>\n");
      out.write("                                </form>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                    </table>                    \n");
      out.write("             ");
}else{
      out.write("\n");
      out.write("                ");
if(strDatosOTROSI != null){
      out.write("\n");
      out.write("                    <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">OTROSI ");
      out.print(strDatosOTROSI[0]);
      out.write(" DEL CONTRATO ");
      out.print(strConsecutivo);
      out.write("</td>\n");
      out.write("                            </tr>\n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmOTROSI\" name=\"frmOTROSI\" enctype=\"multipart/form-data\" action=\"RegistroOTROSI\" onsubmit=\"return validarOTROSI();\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmOTROSI\" />                   \n");
      out.write("                                            <input type=\"hidden\" name=\"txtIdAutor\" id=\"txtIdAutor\" value=\"");
      out.print(strCedula);
      out.write("\" />             \n");
      out.write("                                            <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"");
      out.print(strAccion);
      out.write("\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtConsecutivo\" id=\"txtConsecutivo\" value=\"");
      out.print(strConsecutivo);
      out.write("\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtCodigo\" id=\"txtCodigo\" value=\"");
      out.print(strDatosOTROSI[0]);
      out.write("\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtRutaActa\" id=\"txtRutaActa\" value=\"-\" />\n");
      out.write("                                            <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">\n");
      out.write("                                                <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\">\n");
      out.write("                                                            <label for=\"txtFechaCreacion\" id=\"lblFechaCreacion\">Fecha de creación:<br />(aaaa-mm-dd)</label>\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtFechaCreacion\" id=\"txtFechaCreacion\" value=\"");
      out.print(strDatosOTROSI[3]);
      out.write("\" class=\"CAMPOFORM\" readOnly>\n");
      out.write("                                                        </td>                               \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgFechaCreacion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>                    \n");
      out.write("                                                        <td class=\"LABELFORM\">\n");
      out.write("                                                            <label for=\"txtAutor\" id=\"lblAutor\">Nombre del autor(a):</label>\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            ");

                                                                strSQL = "SELECT txtNombreCompleto FROM users.users_personas where txtIdentificacion = '" + strDatosOTROSI[2] + "'";
                                                                strDatosUsuario = GestionSQL.getFila(strSQL, "Nomina");
                                                            
      out.write("\n");
      out.write("                                                            <input type=\"text\" name=\"txtAutor\" id=\"txtAutor\" value=\"");
      out.print(strDatosUsuario[0]);
      out.write("\" class=\"CAMPOFORM\" readonly>\n");
      out.write("                                                        </td>                               \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgAutor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                            \n");
      out.write("                                                        </td>                                                \n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\">\n");
      out.write("                                                            <label for=\"txtFechaFinActual\" id=\"lblFechaFinActual\">Fecha de finalización actual:<br />(aaaa-mm-dd)</label>\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtFechaFinActual\" id=\"txtFechaFinActual\" value=\"");
      out.print(strDatosOTROSI[4]);
      out.write("\" class=\"CAMPOFORM\" readonly>                                                    \n");
      out.write("                                                        </td>                               \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgFechaFinActual\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                     \n");
      out.write("                                                        </td>                                                \n");
      out.write("                                                        <td class=\"LABELFORM\">\n");
      out.write("                                                            <label for=\"txtFechaFinNueva\" id=\"lblFechaFinNueva\">Fecha de finalización nueva:<br />(aaaa-mm-dd)</label>\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtFechaFinNueva\" id=\"txtFechaFinNueva\" value=\"");
      out.print(strDatosOTROSI[5]);
      out.write("\" class=\"CAMPOFORM\" style=\"width: 180px;\" readonly>                                                            \n");
      out.write("                                                        </td>                               \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgFechaFinNuevaC\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                     \n");
      out.write("                                                        </td>                                                 \n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\">\n");
      out.write("                                                            <label for=\"txtJustificacion\" id=\"lblJustificacion\">* Justificación:</label>\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\" colspan=\"4\">\n");
      out.write("                                                            <textarea name=\"txtJustificacion\" id=\"txtJustificacion\" rows=\"4\" cols=\"50\" wrap=\"soft\" class=\"TEXTAREA\">        \n");
      out.write("                                                                ");
      out.print(strDatosOTROSI[6]);
      out.write("\n");
      out.write("                                                            </textarea>                                                                                                       \n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgJustificacion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                     \n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>                                                                    \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtActa\" id=\"lblActa\">Acta de oficialización:</label></td>                                                               \n");
      out.write("                                                        ");
if(strDatosOTROSI[7].equals("-")){
      out.write("\n");
      out.write("                                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                                <span class=\"MSGAVISOOBLG\">NOTA: No se tiene acta de oficialización adjunta .</td>   \n");
      out.write("                                                            </td>                         \n");
      out.write("                                                        ");
}else{
      out.write("\n");
      out.write("                                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                                    <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosOTROSI[7]);
      out.write("');\" />                                                            \n");
      out.write("                                                            </td>\n");
      out.write("                                                        ");
}
      out.write("                                                                                                              \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgActa\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td colspan=\"6\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                            <input type=\"submit\" value=\"Actualizar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;                                                         \n");
      out.write("                                                            <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td>\n");
      out.write("                                                    </tr>                                                    \n");
      out.write("                                            </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table>\n");
      out.write("                    ");
}else{
      out.write("\n");
      out.write("                        <div class=\"TEXTOFALLO\">\n");
      out.write("                            No se recuperaron los datos del OTROSI seleccionado. Por favor contacte al Administrador del Sistema.                            \n");
      out.write("                        </div>\n");
      out.write("                        <br />\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("             ");
}
      out.write("\n");
      out.write("            </div>\n");
      out.write("       </section>\n");
      out.write("        <footer>                    \n");
      out.write("            <br />\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("                     \n");
      out.write("        </footer> \n");
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
