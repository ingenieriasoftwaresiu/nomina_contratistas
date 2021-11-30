package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;

public final class cambiarPorcentaje_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    String strConsecutivo = request.getParameter("txtConsecutivo");
    String strCodigoPago = request.getParameter("txtCodigoPago");
    String strPorcAPagar = request.getParameter("txtPorcAPagar");    
    String strEvento = request.getParameter("txtEvento");
    String strTipoUsuario = null;
    String strSQL = "";
    double dblPorcAcum = 0;
    
     if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
    }     
    
     if (Integer.parseInt(strCodigoPago) > 1){
         strSQL = "select SUM(p.txtPorcPago) from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' and CAST(p.txtNumPago AS SIGNED) < " + strCodigoPago + "";
         String[] strDatosPago = GestionSQL.getFila(strSQL, "Nomina");
         dblPorcAcum = Math.rint(Double.parseDouble(strDatosPago[0]) * 100)/100;
     }else{
         dblPorcAcum = 0.00;
     }
         
 
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/plan_pagos.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>\n");
      out.write("        <title>Sistema de Gestión de Pagos a Contratistas: Cambio de porcentaje</title>\n");
      out.write("    </head>\n");
      out.write("    <body onLoad=\"onLoad();\">       \n");
      out.write("        <div align=\"center\">\n");
      out.write("            <br />\n");
      out.write("            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\" style=\"width: 450px;\">\n");
      out.write("                <tr>\n");
      out.write("                    <td class=\"TITULOFORM\">CAMBIO DE PORCENTAJE A PAGAR</td>\n");
      out.write("                </tr>                \n");
      out.write("                <tr>\n");
      out.write("                    <td>\n");
      out.write("                        <form method=\"POST\" id=\"frmCambPorc\" name=\"frmCambPorc\">\n");
      out.write("                            <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmCambPorc\">     \n");
      out.write("                            <input type=\"hidden\" name=\"txtConsecutivo\" id=\"txtConsecutivo\" value=\"");
      out.print(strConsecutivo);
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" name=\"txtNumPago\" id=\"txtNumPago\" value=\"");
      out.print(strCodigoPago);
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" name=\"txtEvento\" id=\"txtEvento\" value=\"");
      out.print(strEvento);
      out.write("\">\n");
      out.write("                            <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\" style=\"width: 450px;\">\n");
      out.write("                                <tr>                                    \n");
      out.write("                                    <td class=\"LABELFORM\"><label for=\"txtPorcActual\" id=\"lblPorcActual\">% de pago actual:</label></td>\n");
      out.write("                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                        <input type=\"text\" name=\"txtPorcActual\" id=\"txtPorcActual\" class=\"CAMPOFORM\" value=\"");
      out.print(strPorcAPagar);
      out.write("\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                    </td>       \n");
      out.write("                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                        <img src=\"Images/error.png\" id=\"imgPorcActual\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>                                    \n");
      out.write("                                    <td class=\"LABELFORM\"><label for=\"txtPorcAcum\" id=\"lblPorcAcum\">% de pago acumulado:</label></td>\n");
      out.write("                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                        <input type=\"text\" name=\"txtPorcAcum\" id=\"txtPorcAcum\" class=\"CAMPOFORM\" value=\"");
      out.print(dblPorcAcum);
      out.write("\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                    </td>       \n");
      out.write("                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                        <img src=\"Images/error.png\" id=\"imgPorcAcum\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>                                    \n");
      out.write("                                    <td class=\"LABELFORM\"><label for=\"txtPorcNuevo\" id=\"lblPorcNuevo\">* % de pago nuevo:</label></td>\n");
      out.write("                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                        <input type=\"text\" name=\"txtPorcNuevo\" id=\"txtPorcNuevo\" class=\"CAMPOFORM\">                                     \n");
      out.write("                                    </td>       \n");
      out.write("                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                        <img src=\"Images/error.png\" id=\"imgPorcNuevo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td colspan=\"3\" class=\"CELDABOTONFORM\">\n");
      out.write("                                        <input type=\"button\" value=\"Cambiar\" id=\"btnCambiar\" class=\"BOTONFORM\">&nbsp;&nbsp;                                                    \n");
      out.write("                                        <input type=\"button\" value=\"Salir\" id=\"btnGuardar\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\n");
      out.write("                        </form>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("            <br />\n");
      out.write("            <div id=\"dMensaje\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
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
