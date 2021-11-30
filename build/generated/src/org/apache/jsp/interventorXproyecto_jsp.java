package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;
import java.util.Vector;

public final class interventorXproyecto_jsp extends org.apache.jasper.runtime.HttpJspBase
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
 
    String strAccion = (String) request.getParameter("txtAccion");      
    String strCodigo = (String) request.getParameter("txtCodigo");  
   
    String[] strDatos = null;
    String[] strTemp = null;
    String strSQL = "";   
    Vector arrInterventores;
    Vector arrIdsI = new Vector();
    Vector arrNombresI = new Vector();
    
     if (strAccion == null){
            response.sendRedirect("cerrar.jsp");
     }else{
        
        if (strAccion.equals("V")){
            strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl where tbl.txtEstadoActual = 'A' ORDER BY tbl.txtNombreCompleto";
        }else{
            strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl ORDER BY tbl.txtNombreCompleto";
        }
        
        arrInterventores = GestionSQL.consultaSQL(strSQL,"Users","2"); 

        for (int i=0;i<arrInterventores.size();i++){
                strTemp = arrInterventores.get(i).toString().split(">");
                arrIdsI.add(strTemp[0]);
                arrNombresI.add(strTemp[1]);
        }
         
        if (strAccion.equals("V")){
            strTemp = strCodigo.split(">");
            strSQL = "select ixp.txtIdInterventor, ixp.txtCodProyecto from nomina.tbl_interventores_x_proyecto ixp where ixp.txtIdInterventor = '" + strTemp[0] + "' and ixp.txtCodProyecto = '" + strTemp[1]  + "'";
            strDatos = GestionSQL.getFila(strSQL,"Nomina");             
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
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/interventorXproyecto.js\"></script>\n");
      out.write("        <title>Sistema de Gestión de Pagos a Contratistas: Interventor</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("       </header>\n");
      out.write("       <section>\n");
      out.write("            <div align=\"center\">\n");
      out.write("                <br><br>\n");
      out.write("                    ");
 if (strAccion.equals("C")){
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">NUEVO REGISTRO DE INTERVENTOR POR PROYECTO</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmInterventorXProyecto\" name=\"frmInterventorXProyecto\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmInterventorXProyecto\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"C\">                       \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">                \n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class='LABELFORM'><label for='txtInterventor' id='lblInterventor'>* Nombre del interventor:</label></td>\n");
      out.write("                                                <td class='CELDACAMPOFORM'>\n");
      out.write("                                                    <select id='txtInterventor' name='txtInterventor' class='CAMPOSELECT'>\n");
      out.write("                                                        <option value='-1'>Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsI.size();i++){
      out.write("\n");
      out.write("                                                        <option value=\"");
      out.print(arrIdsI.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresI.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </select>      \n");
      out.write("                                                    <br />\n");
      out.write("                                                    <img src=\"Images/Agregar.png\" alt=\"Agregar Interventor\" title=\"Agregar Interventor\" id=\"btnAgregarInterventor\" style=\"width: 15px;height: 15px;padding-top: 5px;\" />&nbsp;\n");
      out.write("                                                    <img src=\"Images/Ejecutar.png\" alt=\"Refrescar\" title=\"Refrescar\" class=\"IMGREFRESH\" style=\"width: 15px;height: 15px;padding-top: 5px;\" />\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class='CELDAIMGERROR'>\n");
      out.write("                                                    <img src='Images/error.png' id='imgInterventor' alt='Campo obligatorio' class='IMGERROR'>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class='LABELFORM'><label for='txtCodProy' id='lblCodProy'>Código del proyecto:</label></td>                                   \n");
      out.write("                                                <td class='CELDACAMPOFORM'>\n");
      out.write("                                                    <input type='text' name='txtCodProy' id='txtCodProy' class='CAMPOFORM'>                                        \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class='CELDAIMGERROR'>\n");
      out.write("                                                    <img src='Images/error.png' id='imgCodProy' alt='Campo obligatorio' class='IMGERROR'>\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"CELDABOTONFORM\"><input type=\"button\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;<input type=\"button\" value=\"Limpiar\" id=\"btnLimpiar\" class=\"BOTONFORM\"> &nbsp;&nbsp;<input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\"></td></tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td></tr>\n");
      out.write("                                        </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table>\n");
      out.write("                    ");
 }else{
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">REGISTRO DE INTERVENTOR POR PROYECTO</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmInterventorXProyecto\" name=\"frmInterventorXProyecto\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmInterventorXProyecto\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"V\">                                         \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">            \n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class='LABELFORM'><label for='txtInterventor' id='lblInterventor'>Nombre del interventor:</label></td>\n");
      out.write("                                                <td class='CELDACAMPOFORM'>\n");
      out.write("                                                    <select id='txtInterventor' name='txtInterventor' class='CAMPOSELECT' disabled=\"disabled\">\n");
      out.write("                                                        <option value='-1'>Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsI.size();i++){
      out.write("\n");
      out.write("                                                        <option value=\"");
      out.print(arrIdsI.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresI.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </select>              \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                     $(\"#txtInterventor option[value='");
      out.print(strDatos[0]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                </script>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class='CELDAIMGERROR'>\n");
      out.write("                                                    <img src='Images/error.png' id='imgInterventor' alt='Campo obligatorio' class='IMGERROR'>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class='LABELFORM'><label for='txtCodProy' id='lblCodProy'>Código del proyecto:</label></td>                                   \n");
      out.write("                                                <td class='CELDACAMPOFORM'>\n");
      out.write("                                                    <input type='text' name='txtCodProy' id='txtCodProy' value=\"");
      out.print(strDatos[1]);
      out.write("\" class='CAMPOFORM' disabled=\"disabled\">                                        \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class='CELDAIMGERROR'>\n");
      out.write("                                                    <img src='Images/error.png' id='imgCodProy' alt='Campo obligatorio' class='IMGERROR'>\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
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
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("       <footer>        \n");
      out.write("            <div id=\"dFooter\" class=\"FOOTER\">\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("          \n");
      out.write("            </div>\n");
      out.write("       </footer>\n");
      out.write("   </body>\n");
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
