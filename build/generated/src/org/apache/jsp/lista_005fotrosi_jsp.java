package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;
import java.util.Vector;

public final class lista_005fotrosi_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "comprobarSesion.jsp", out, false);
      out.write('\n');

    String strConsecutivo = (String) request.getParameter("txtConsecutivo");
    String strTipoUsuario = null;
    
    String strSQL="";
    String[] strTemp = null, strDatosContrato=null;
    Vector arrCodigos = new Vector();
    Vector arrAutores = new Vector();
    Vector arrFechasCreacion = new Vector();
    Vector arrFechasIniciales = new Vector();
    Vector arrFechasFinales = new Vector();
    Vector arrJustificaciones = new Vector();
    Vector arrRutas = new Vector();
    
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }
    
    strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
        
    strSQL = "SELECT txtIdEstado from nomina.tbl_contratos WHERE txtConsecutivo = '" + strConsecutivo + "'";
    strDatosContrato = GestionSQL.getFila(strSQL,"Nomina");

    strSQL = "select c.txtCodigo, p.txtNombreCompleto, c.dtFechaCreacion, c.dtFechaInicial, c.dtFechaFinal, c.txtJustificacion, c.txtRutaActa from nomina.tbl_otrosi_contratos c, users.users_personas p where (c.txtAutor = p.txtIdentificacion) and c.txtIdContrato = '" + strConsecutivo + "' ORDER BY c.txtCodigo DESC";
    Vector arrListado = GestionSQL.consultaSQL(strSQL, "Nomina", "7");
    
    if (arrListado != null){    
        for(int i=0;i<arrListado.size();i++){
            strTemp = arrListado.get(i).toString().split(">");
            arrCodigos.add(strTemp[0]);
            arrAutores.add(strTemp[1]);
            arrFechasCreacion.add(strTemp[2]);
            arrFechasIniciales.add(strTemp[3]);
            arrFechasFinales.add(strTemp[4]);
            arrJustificaciones.add(strTemp[5]);
            arrRutas.add(strTemp[6]);
        }
    }

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>  \n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/otrosi.js\"></script>\n");
      out.write("        <title>Lista de OTROSI del contrato ");
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
if(arrListado != null){
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\" style=\"width: 99%;\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">LISTADO DE OTROSI DEL CONTRATO ");
      out.print(strConsecutivo);
      out.write("</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmListadoOTROSI\" name=\"frmListadoOTROSI\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmListadoOTROSI\" />                                              \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\" style=\"width: 100%;\">         \n");
      out.write("                                            <tr>                                   \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Nro.</td> \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Autor</td>                                                                                               \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Fecha de creación<br/>(aaaa-mm-dd)</td>                 \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Fecha de inicial<br/>(aaaa-mm-dd)</td> \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Fecha de final<br/>(aaaa-mm-dd)</td> \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Acta</td> \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Justificación</td>\n");
      out.write("                                                ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                                    ");
if((!(strDatosContrato[0].equals("FN"))) && (!(strDatosContrato[0].equals("CA")))){
      out.write("\n");
      out.write("                                                        <td class=\"SUBTITULOFORM\">Editar</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                               ");
}
      out.write("\n");
      out.write("                                            </tr>                                                     \n");
      out.write("                                            ");
for(int i=0;i<arrCodigos.size();i++){
      out.write("\n");
      out.write("                                                <tr>  \n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center; vertical-align: middle;border-right: 1px solid #116043;\">\n");
      out.write("                                                        ");
      out.print(arrCodigos.get(i).toString());
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center; vertical-align: middle;border-right: 1px solid #116043;\">\n");
      out.write("                                                        ");
      out.print(arrAutores.get(i).toString());
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center; vertical-align: middle;;border-right: 1px solid #116043;\">\n");
      out.write("                                                        ");
      out.print(arrFechasCreacion.get(i).toString());
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center; vertical-align: middle;border-right: 1px solid #116043;\">\n");
      out.write("                                                        ");
      out.print(arrFechasIniciales.get(i).toString());
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center; vertical-align: middle;border-right: 1px solid #116043;\">\n");
      out.write("                                                        ");
      out.print(arrFechasFinales.get(i).toString());
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center; vertical-align: middle;border-right: 1px solid #116043;\">\n");
      out.write("                                                        <input type=\"button\" name=\"btnDescargar\" id=\"btnDescargar\" class=\"BOTONFORM\" value=\"Descargar\" onclick=\"descargarArchivo('");
      out.print(arrRutas.get(i).toString());
      out.write("');\" />\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: justify; vertical-align: middle;border-right: 1px solid #116043;\">\n");
      out.write("                                                        ");
      out.print(arrJustificaciones.get(i).toString());
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                                        ");
if((!(strDatosContrato[0].equals("FN"))) && (!(strDatosContrato[0].equals("CA")))){
      out.write("\n");
      out.write("                                                            <td class=\"DATOREPORTE\" style=\"text-align: center; vertical-align: middle;\">\n");
      out.write("                                                                <input type=\"button\" name=\"btnEditar\" id=\"btnEditar\" class=\"BOTONFORM\" value=\"\" style=\"width: 15px; height: 15px;\" onclick=\"verOTROSI('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(arrCodigos.get(i).toString());
      out.write("')\" />\n");
      out.write("                                                            </td>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                   ");
}
      out.write("\n");
      out.write("                                                </tr> \n");
      out.write("                                            ");
}
      out.write("\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"10\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                    <input type=\"button\" name=\"btnRefresh\" id=\"btnRefresh\" value=\"Actualizar\" class=\"BOTONFORM\" onclick=\"actualizar()\" />&nbsp;&nbsp;\n");
      out.write("                                                    <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\" />\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                        </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>   \n");
      out.write("                        </table>\n");
      out.write("                     ");
}else{
      out.write("       \n");
      out.write("                     <br />\n");
      out.write("                     <div class=\"TEXTOFALLO\">\n");
      out.write("                         ESTE CONTRATO AÚN NO CUENTA CON OTROSI CREADOS.\n");
      out.write("                     </div>\n");
      out.write("                     <br />\n");
      out.write("                     <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\" />\n");
      out.write("                     <br /><br />\n");
      out.write("                     ");
}
      out.write("\n");
      out.write("             </div>\n");
      out.write("       </section>\n");
      out.write("        <footer>                    \n");
      out.write("            <br />\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("                     \n");
      out.write("        </footer>\n");
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
