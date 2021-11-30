package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    String[] strDatos= null; 
    String[] strDatosDllo = null;
    String strSQL = "", strUsuario=null, strDllo="N";
    
    strUsuario = (String) session.getAttribute("txtCedula");
    
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);    
    }
    
    strSQL = "select rxp.txtIdPersona from nomina.tbl_roles_x_persona rxp where rxp.txtIdPersona = '" + strUsuario + "' and rxp.txtIdRol = 'DES'";
    strDatosDllo = GestionSQL.getFila(strSQL,"Nomina");
    
    if(strDatosDllo != null){
        strDllo="S";
    }
    
    strSQL = "select g.txtForm from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";                  
    strDatos = GestionSQL.getFila(strSQL,"Nomina");


      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/menu.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/visualizacion.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/admin.js\"></script>          \n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/visualizacion.js\"></script>  \n");
      out.write("        <title>Sistema de Gestión de Pagos a Contratistas - Administración</title>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function disableKeyPress(evt){               \n");
      out.write("                var evt = (evt) ? evt : ((event) ? event : null); \n");
      out.write("                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); \n");
      out.write("                if ((evt.keyCode == 13) && (node.type==\"text\"))  {return false;} \n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("       </header>\n");
      out.write("       <div align=\"center\">\n");
      out.write("            <br>\n");
      out.write("            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" >\n");
      out.write("                <tr>\n");
      out.write("                    <td style=\"width: 1080px;\"></td>      \n");
      out.write("                    <td class=\"CELDAACCION\" style=\"border-left: 1px solid #116043; text-align: right;width: 120px;\"><input type=\"button\" value=\"Salir\" class=\"BOTONACCION\" onclick=\"javascript:window.close();\" /></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>         \n");
      out.write("            <br>\n");
      out.write("           <nav>\n");
      out.write("                   <input type=\"hidden\" name=\"txtTipoUsuario\" id=\"txtTipoUsuario\" value=\"");
      out.print(strDllo);
      out.write("\"/>\n");
      out.write("                   <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" >\n");
      out.write("                       <tr>\n");
      out.write("                           <td class=\"CELDAMENU\" width=\"18%\" >\n");
      out.write("                               <div id=\"dMenu\" align=\"right\">\n");
      out.write("                                   <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMENU\" width=\"100%\" >\n");
      out.write("                                       <tr><td class=\"TITULOMENU\">MENÚ ADMINISTRATIVO</td></tr>               \n");
      out.write("                                       ");
 if (strDllo.equals("N")){ 
      out.write("\n");
      out.write("                                           <tr><td class=\"ITEMMENU\" id=\"itRolesXPersona\" style=\"border-bottom:  1px solid #116043;\"><a href=\"#\">Roles por persona</a></td></tr>\n");
      out.write("                                           <tr><td class=\"ITEMMENU\" id=\"itTiposC\"><a href=\"#\">Tipos de contrato</a></td></tr>\n");
      out.write("                                       ");
}else{
      out.write("\n");
      out.write("                                           <tr><td class=\"ITEMMENU\" id=\"itRoles\" style=\"border-bottom:  1px solid #116043;\"><a href=\"#\">Roles</a></td></tr>           \n");
      out.write("                                           <tr><td class=\"ITEMMENU\" id=\"itEstados\" style=\"border-bottom:  1px solid #116043;\"><a href=\"#\">Estados del contrato</a></td></tr>               \n");
      out.write("                                           <tr><td class=\"ITEMMENU\" id=\"itRolesXPersona\" style=\"border-bottom:  1px solid #116043;\"><a href=\"#\">Roles por persona</a></td></tr>\n");
      out.write("                                           <tr><td class=\"ITEMMENU\" id=\"itTiposC\" style=\"border-bottom:  1px solid #116043;\"><a href=\"#\">Tipos de contrato</a></td></tr>                                                                        \n");
      out.write("                                           ");
if (strDatos == null){
      out.write("\n");
      out.write("                                               <tr><td class=\"ITEMMENU\"><a href=\"#\"  onclick=\"window.open('general.jsp?txtAccion=C','Admin','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');\">Crear configuración general</a></td></tr>\n");
      out.write("                                           ");
}else{
      out.write("\n");
      out.write("                                               <tr><td class=\"ITEMMENU\"><a href=\"#\"  onclick=\"window.open('general.jsp?txtAccion=V','Admin','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');\">Consultar configuración general</a></td></tr>\n");
      out.write("                                           ");
}
      out.write("\n");
      out.write("                                         ");
}
      out.write("\n");
      out.write("                                   </table>\n");
      out.write("                               </div>\n");
      out.write("                           </td>\n");
      out.write("                           <td class=\"CELDAESPACIO\" width=\"1%\"></td>\n");
      out.write("                           <td class=\"CELDAMOSTRAR\" style=\"width:80%;text-align: center;vertical-align: top;\">\n");
      out.write("                               <div id=\"dMostrar\">\n");
      out.write("                               </div>\n");
      out.write("                           </td>\n");
      out.write("                       </tr>\n");
      out.write("                   </table>  \n");
      out.write("           </nav>\n");
      out.write("        </div>\n");
      out.write("        <br>\n");
      out.write("       <footer>\n");
      out.write("            <div id=\"dFooter\" class=\"FOOTER\">\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("          \n");
      out.write("            </div>\n");
      out.write("       </footer>\n");
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
