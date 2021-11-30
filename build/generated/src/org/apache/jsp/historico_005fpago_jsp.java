package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;
import java.util.Vector;

public final class historico_005fpago_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    String strConsecutivo = request.getParameter("txtConsecutivo");
    String strCodigoPago = request.getParameter("txtCodigoPago");
    String strSQL="";
    String[] strTemp = null;
    Vector arrIdsAutores = new Vector();
    Vector arrFechas = new Vector();
    Vector arrAcciones = new Vector();
    Vector arrEstados = new Vector();
    Vector arrObs = new Vector();
    
    strSQL = "select h.txtIdAutor, h.dtFecha, h.txtAccion, h.txtIdEstadoActual, h.txtObs from nomina.tbl_historico_pagos h where h.txtIdContrato = '" + strConsecutivo + "' and h.txtNumPago = '" + strCodigoPago + "' ORDER BY h.dtFecha DESC";
    Vector arrHistorico = GestionSQL.consultaSQL(strSQL, "Nomina", "5");
    
    if (arrHistorico != null){    
        for(int i=0;i<arrHistorico.size();i++){
            strTemp = arrHistorico.get(i).toString().split(">");
            arrIdsAutores.add(strTemp[0]);            
            arrFechas.add(strTemp[1].replace(".0", ""));
            arrAcciones.add(strTemp[2]);                   
            arrEstados.add(strTemp[3]);
            arrObs.add(strTemp[4]);
        }
    }
    

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <title>Histórico de transacciones del pago ");
      out.print(strCodigoPago);
      out.write("</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("       </header>\n");
      out.write("       <section>\n");
      out.write("                <div align=\"center\">\n");
      out.write("                    <br /><br />                \n");
      out.write("                    ");
if(arrHistorico != null){
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\" style=\"width: 95%;\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">HISTÓRICO DE TRANSACCIONES DEL PAGO # ");
      out.print(strCodigoPago);
      out.write("</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmHistoricoPago\" name=\"frmHistoricoPago\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmHistoricoPago\" />                                              \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\" style=\"width: 100%;\">         \n");
      out.write("                                            <tr>                                   \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Fecha de registro<br/>(aaaa-mm-dd hh:mm:ss)</td> \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Autor</td>                                                                                               \n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Transacción</td>                 \n");
      out.write("                                                <td class=\"SUBTITULOFORM\">Observación</td>\n");
      out.write("                                            </tr>                                                     \n");
      out.write("                                            ");
for(int i=0;i<arrIdsAutores.size();i++){
      out.write("\n");
      out.write("                                                <tr>                    \n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043;width: 15%;\">");
      out.print(arrFechas.get(i).toString());
      out.write("</td> \n");
      out.write("                                                    ");
if(arrIdsAutores.get(i).equals("A")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043;width: 10%;\">Administrador</td>                                                                                                                \n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    ");
if(arrIdsAutores.get(i).equals("I")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043;width: 10%;\">Interventor</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    ");
if(arrIdsAutores.get(i).equals("C")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043;width: 10%;\">Contratista</td>\n");
      out.write("                                                    ");
}
      out.write("                                                    \n");
      out.write("                                                    \n");
      out.write("                                                    ");
if(arrAcciones.get(i).equals("GUARDAR")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: Pendiente --> <b>Estado final</b>: Pendiente Aprobación</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    \n");
      out.write("                                                    ");
if(arrAcciones.get(i).equals("APROBAR")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: PreAprobado --> <b>Estado final</b>: Aprobado</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    \n");
      out.write("                                                    ");
if(arrAcciones.get(i).equals("PREAPROBAR")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: Pendiente Aprobación --> <b>Estado final</b>: PreAprobado</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    \n");
      out.write("                                                    ");
if(arrAcciones.get(i).equals("REPROCESARC")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: PreAprobado --> <b>Estado final</b>: Pendiente Aprobación</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    \n");
      out.write("                                                    ");
if(arrAcciones.get(i).equals("REPROCESARI")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: Pendiente Aprobación --> <b>Estado final</b>: Pendiente</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    \n");
      out.write("                                                    ");
if(arrAcciones.get(i).equals("EJECUTAR")){
      out.write("\n");
      out.write("                                                        ");
if(arrEstados.get(i).equals("P")){
      out.write("\n");
      out.write("                                                            <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: Pendiente --> <b>Estado final</b>: Ejecutado</td>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        \n");
      out.write("                                                        ");
if(arrEstados.get(i).equals("PA")){
      out.write("\n");
      out.write("                                                            <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: Pendiente Aprobación --> <b>Estado final</b>: Ejecutado</td>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        \n");
      out.write("                                                        ");
if(arrEstados.get(i).equals("PRA")){
      out.write("\n");
      out.write("                                                            <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: PreAprobado --> <b>Estado final</b>: Ejecutado</td>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        \n");
      out.write("                                                        ");
if(arrEstados.get(i).equals("A")){
      out.write("\n");
      out.write("                                                            <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;\"><b>Estado inicial</b>: Aprobado --> <b>Estado final</b>: Ejecutado</td>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    ");
}
      out.write("                                  \n");
      out.write("                                                    \n");
      out.write("                                                    ");
if(arrObs.get(i).equals("-")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 45%;\">(Ninguna)</td> \n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: justify;text-justify: auto;width: 45%;\">");
      out.print(arrObs.get(i).toString());
      out.write("</td> \n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                </tr>\n");
      out.write("                                            ");
}
      out.write("\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"10\" class=\"CELDABOTONFORM\">\n");
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
      out.write("                         ESTE PAGO AÚN NO CUENTA CON HISTÓRICO DE TRANSACCIONES.\n");
      out.write("                     </div>\n");
      out.write("                     <br />\n");
      out.write("                     <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\" />\n");
      out.write("                     ");
}
      out.write("\n");
      out.write("                </div> \n");
      out.write("            </section>\n");
      out.write("           <footer>        \n");
      out.write("            <div id=\"dFooter\" class=\"FOOTER\">\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("          \n");
      out.write("            </div>\n");
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
