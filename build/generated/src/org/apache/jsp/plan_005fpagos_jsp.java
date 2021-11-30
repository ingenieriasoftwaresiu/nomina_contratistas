package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Negocio.Comunes;
import java.util.Vector;
import Conexion.GestionSQL;

public final class plan_005fpagos_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "comprobarSesion.jsp", out, false);
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

    String strTipoUsuario = null;
    String strConsecutivo = request.getParameter("txtConsecutivo");
    String[] strTemp=null;
    String strSQL;
    Vector arrNumPagos = new Vector();
    Vector arrFechasPagos = new Vector();
    Vector arrPocPagos = new Vector();
    Vector arrValorSalud = new Vector();
    Vector arrValorPension = new Vector();
    Vector arrValorARL = new Vector();
    Vector arrEstados= new Vector();
    Vector arrSoportes = new Vector();    
    Vector arrFormatos = new Vector(); 
    Comunes comun = new Comunes();
    
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
    }
    
    strSQL = "select c.txtIdEstado from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
    String[] strDatosContrato = GestionSQL.getFila(strSQL, "Nomina");
    
    strSQL = "select p.txtNumPago, p.dtFechaPago, p.txtPorcPago, txtValorSalud, p.txtValorPension, p.txtValorARL, p.txtIdEstadoPago, p.txtRutaArchivo, p.txtRutaFormato from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' order by p.dtFechaPago";
    Vector arrPagos =GestionSQL.consultaSQL(strSQL, "Nomina", "9");
    
    if (arrPagos != null){    
        for(int i=0;i<arrPagos.size();i++){
            strTemp = arrPagos.get(i).toString().split(">");
            arrNumPagos.add(strTemp[0]);
            arrFechasPagos.add(strTemp[1]);
            arrPocPagos.add(strTemp[2]);
            arrValorSalud.add(strTemp[3]);
            arrValorPension.add(strTemp[4]);
            arrValorARL.add(strTemp[5]);
            arrEstados.add(strTemp[6]);
            arrSoportes.add(strTemp[7]);
            arrFormatos.add(strTemp[8]);
        }
    }    

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/plan_pagos.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/print.css\" media=\"print\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/plan_pagos.js\"></script>\n");
      out.write("        <title>Plan de pagos del contrato #");
      out.print(strConsecutivo);
      out.write("</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"dNPrint\">\n");
      out.write("            <header>\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("           </header>\n");
      out.write("           <section>\n");
      out.write("                <div align=\"center\">\n");
      out.write("                    <br />                \n");
      out.write("                    ");
if(arrPagos != null){
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\" style=\"width: 90%;\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">PLAN DE PAGOS DEL CONTRATO #");
      out.print(strConsecutivo);
      out.write("</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmPlanPagos\" name=\"frmPlanPagos\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmPlanPagos\" />                                              \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\" style=\"width: 100%;\">         \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Nro. pago</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Fecha de pago<br/>(aaaa-mm-dd)</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">% a pagar</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Valor salud ($)</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Valor pensión ($)</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Valor ARL ($)</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">Estado del pago</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">¿Tiene soporte?</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;\">¿Tiene formato?</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\">Acciones</td>                                        \n");
      out.write("                                            </tr>                \n");
      out.write("                                            ");

                                                double sumaPorc = 0;
                                                int sumaSalud = 0;
                                                int sumaPension = 0;
                                                int sumaARL = 0;
                                                double ftValorPorc = 0;
                                                int ftValorSalud = 0;
                                                int ftValorPension = 0;
                                                int ftValorARL = 0;
                                            
      out.write("\n");
      out.write("                                            ");
for(int i=0;i<arrNumPagos.size();i++){
      out.write("\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(arrNumPagos.get(i));
      out.write("</td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(arrFechasPagos.get(i));
      out.write("</td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(arrPocPagos.get(i));
      out.write("</td>\n");
      out.write("                                                    ");
if(arrValorSalud.get(i).toString().equals("0")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">-</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(comun.marcarMiles(arrValorSalud.get(i).toString()));
      out.write("</td>\n");
      out.write("                                                    ");
}
      out.write("                      \n");
      out.write("                                                    ");
if(arrValorPension.get(i).toString().equals("0")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">-</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(comun.marcarMiles(arrValorPension.get(i).toString()));
      out.write("</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    ");
if(arrValorARL.get(i).toString().equals("0")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">-</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(comun.marcarMiles(arrValorARL.get(i).toString()));
      out.write("</td>\n");
      out.write("                                                     ");
}
      out.write("                                                \n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(comun.validarEstado(arrEstados.get(i).toString()));
      out.write("</td>\n");
      out.write("                                                    ");
if(arrSoportes.get(i).toString().equals("-")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">No</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">Si</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    ");
if(arrFormatos.get(i).toString().equals("-")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">No</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">Si</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;\">\n");
      out.write("                                                        <a href=\"#\" onclick=\"verDetalle('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(arrNumPagos.get(i).toString());
      out.write("');\" style=\"text-decoration: underline;\"><img src=\"Images/Ver_Detalle.png\" alt=\"Ver detalle\" title=\"Ver detalle\" class=\"IMGACCION\" /></a>&nbsp;&nbsp;&nbsp;\n");
      out.write("                                                                                                                \n");
      out.write("                                                        ");
if(!(strDatosContrato[0].equals("FN")) && !(strDatosContrato[0].equals("CA"))){
      out.write("\n");
      out.write("                                                            ");
      out.write("\n");
      out.write("                                                            ");
if(arrEstados.get(i).toString().equals("P")){
      out.write("\n");
      out.write("                                                                \n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("\n");
      out.write("                                                            ");
      out.write("\n");
      out.write("                                                            ");
if(arrEstados.get(i).toString().equals("PA")){
      out.write("\n");
      out.write("                                                                ");
if(strTipoUsuario.equals("I")){
      out.write("\n");
      out.write("                                                                    <a href=\"#\" onclick=\"preaprobarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(arrNumPagos.get(i).toString());
      out.write("','PP');\" style=\"text-decoration: underline;\"><img src=\"Images/Aprobar.png\" alt=\"Aprobar\" title=\"Aprobar\" class=\"IMGACCION\" /></a>&nbsp;&nbsp;&nbsp;                                                                    \n");
      out.write("                                                                 ");
}
      out.write("                                                                        \n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                                    \n");
      out.write("                                                           ");
      out.write("\n");
      out.write("                                                            ");
if(arrEstados.get(i).toString().equals("PRA")){
      out.write("\n");
      out.write("                                                                ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                                                    <a href=\"#\" onclick=\"aprobarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(arrNumPagos.get(i).toString());
      out.write("','PP');\" style=\"text-decoration: underline;\"><img src=\"Images/Aprobar.png\" alt=\"Aprobar\" title=\"Aprobar\" class=\"IMGACCION\" /></a>&nbsp;&nbsp;&nbsp;                                                                    \n");
      out.write("                                                                ");
}
      out.write("                                                     \n");
      out.write("                                                            ");
}
      out.write("         \n");
      out.write("                                                                                                                            \n");
      out.write("                                                            ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                                                    ");
if(!(arrEstados.get(i).toString().equals("E"))){
      out.write("                \n");
      out.write("                                                                    <a href=\"#\" onclick=\"cambiarPorcentaje('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(arrNumPagos.get(i).toString());
      out.write("','");
      out.print(arrPocPagos.get(i).toString());
      out.write("','PP');\" style=\"text-decoration: underline;\"><img src=\"Images/Cambiar_Porc.png\" alt=\"Cambiar %\" title=\"Cambiar %\" class=\"IMGACCION\" /></a>&nbsp;&nbsp;&nbsp;                                                                    \n");
      out.write("                                                                    <a href=\"#\" onclick=\"ejecutarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(arrNumPagos.get(i).toString());
      out.write("','PP');\" style=\"text-decoration: underline;\"><img src=\"Images/Ejecutar.png\" alt=\"Poner ejecutado\" title=\"Poner ejecutado\" class=\"IMGACCION\" /></a>&nbsp;&nbsp;&nbsp;\n");
      out.write("                                                                    <a href=\"#\" onclick=\"eliminarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(arrNumPagos.get(i).toString());
      out.write("');\" style=\"text-decoration: underline;\"><img src=\"Images/Eliminar.png\" alt=\"Eliminar\" title=\"Eliminar\" class=\"IMGACCION\" /></a>&nbsp;&nbsp;&nbsp;                                                                    \n");
      out.write("                                                                ");
}
      out.write("                                                                \n");
      out.write("                                                            ");
}
      out.write("                                                           \n");
      out.write("                                                       ");
}
      out.write("         \n");
      out.write("                                                                    \n");
      out.write("                                                        <a href=\"#\" onclick=\"verHistorico('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(arrNumPagos.get(i).toString());
      out.write("');\" style=\"text-decoration: underline;\"><img src=\"Images/historico.png\" alt=\"Ver Histórico\" title=\"Ver Histórico\" class=\"IMGACCION\" /></a>&nbsp;&nbsp;&nbsp;\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                ");
 if((arrEstados.get(i).toString().equals("E"))){
                                                            ftValorPorc = Math.rint(Double.parseDouble(arrPocPagos.get(i).toString())*100)/100;
                                                            sumaPorc=sumaPorc+ftValorPorc;

                                                            ftValorSalud = Integer.parseInt(arrValorSalud.get(i).toString());
                                                            sumaSalud = sumaSalud + ftValorSalud;

                                                            ftValorPension = Integer.parseInt(arrValorPension.get(i).toString());
                                                            sumaPension = sumaPension + ftValorPension;

                                                            ftValorARL = Integer.parseInt(arrValorARL.get(i).toString());
                                                            sumaARL = sumaARL + ftValorARL;
                                                }
      out.write("\n");
      out.write("                                            ");
}
      out.write("\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"2\" class=\"SUBTITULOFORM\" style=\"border-bottom: 1px solid #116043;border-right:  1px solid #116043;\">Total acumulado</td>\n");
      out.write("                                                <td class=\"TEXTONORMAL\" style=\"font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;\">");
      out.print(Math.rint(sumaPorc*10)/10);
      out.write("%</td>\n");
      out.write("                                                <td class=\"TEXTONORMAL\" style=\"font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;\">$");
      out.print(comun.marcarMiles(String.valueOf(sumaSalud)));
      out.write("</td>\n");
      out.write("                                                <td class=\"TEXTONORMAL\" style=\"font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;\">$");
      out.print(comun.marcarMiles(String.valueOf(sumaPension)));
      out.write("</td>\n");
      out.write("                                                <td class=\"TEXTONORMAL\" style=\"font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;\">$");
      out.print(comun.marcarMiles(String.valueOf(sumaARL)));
      out.write("</td>\n");
      out.write("                                                <td colspan=\"2\"></td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                    <td colspan=\"10\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                        <input type=\"button\" name=\"btnRefrescar\" id =\"btnRefrescar\" value=\"Actualizar datos\" style=\"width: 100px;\" onclick=\"refrescar('");
      out.print(strConsecutivo);
      out.write("');\" class=\"BOTONFORM\" />&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"button\" id=\"btnImprimir\" value=\"Imprimir\" class=\"BOTONFORM\" />&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\" />\n");
      out.write("                                                    </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                        </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>   \n");
      out.write("                        </table>   \n");
      out.write("                        <br />\n");
      out.write("                        <div id=\"dLoader\" class=\"TEXTOEXITO\" style=\"display: none;\">\n");
      out.write("                            <img src=\"Images/loader.gif\" style=\"vertical-align: middle;width: 30px;height: 30px;\"/>&nbsp;&nbsp;Procesando...\n");
      out.write("                        </div>\n");
      out.write("                    ");
}else{
      out.write("\n");
      out.write("                    <br />\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class='TEXTOFALLO'>NO SE ENCONTRÓ UN PLAN DE PAGOS ASOCIADO AL CONTRATO #");
      out.print(strConsecutivo);
      out.write(".</td>\n");
      out.write("                            </tr>\n");
      out.write("                            <tr><td style=\"height: 0px;\"></td></tr>\n");
      out.write("                            <tr>\n");
      out.write("                                    <td class=\"CELDABOTONFORM\">\n");
      out.write("                                        <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\" />\n");
      out.write("                                    </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <div id=\"dMensaje\">\n");
      out.write("                    </div>\n");
      out.write("                </section>\n");
      out.write("               <footer>        \n");
      out.write("                <div id=\"dFooter\" class=\"FOOTER\">\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("          \n");
      out.write("                </div>\n");
      out.write("            </footer>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"dSPrint\">\n");
      out.write("            <header>\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header_print.jsp", out, false);
      out.write("  \n");
      out.write("           </header>\n");
      out.write("           <section>\n");
      out.write("                <div align=\"center\">\n");
      out.write("                    <br /><br />                \n");
      out.write("                    ");
if(arrPagos != null){
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\" style=\"border-right: 1px solid #116043;border-left: 1px solid #116043;border-top: 1px solid #116043;\">PLAN DE PAGOS DEL CONTRATO #");
      out.print(strConsecutivo);
      out.write("</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmPlanPagos\" name=\"frmPlanPagos\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmPlanPagos\">                                   \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">         \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;border-bottom: 1px solid #116043;\">Nro. pago</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;border-bottom: 1px solid #116043;\">Fecha de pago<br/>(aaaa-mm-dd)</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;border-bottom: 1px solid #116043;\">% a pagar</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;border-bottom: 1px solid #116043;\">Valor salud ($)</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;border-bottom: 1px solid #116043;\">Valor pensión ($)</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;border-bottom: 1px solid #116043;\">Valor ARL ($)</td>\n");
      out.write("                                                <td class=\"SUBTITULOFORM\" style=\"border-right: 1px solid #116043;border-bottom: 1px solid #116043;\">Estado del pago</td>                                                           \n");
      out.write("                                            </tr>                \n");
      out.write("                                            ");

                                                double sumaPorc = 0;
                                                int sumaSalud = 0;
                                                int sumaPension = 0;
                                                int sumaARL = 0;
                                                double ftValorPorc = 0;
                                                int ftValorSalud = 0;
                                                int ftValorPension = 0;
                                                int ftValorARL = 0;
                                            
      out.write("\n");
      out.write("                                            ");
for(int i=0;i<arrNumPagos.size();i++){
      out.write("\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(arrNumPagos.get(i));
      out.write("</td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(arrFechasPagos.get(i));
      out.write("</td>\n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(arrPocPagos.get(i));
      out.write("</td>\n");
      out.write("                                                    ");
if(arrValorSalud.get(i).toString().equals("0")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">-</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(comun.marcarMiles(arrValorSalud.get(i).toString()));
      out.write("</td>\n");
      out.write("                                                    ");
}
      out.write("                      \n");
      out.write("                                                    ");
if(arrValorPension.get(i).toString().equals("0")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">-</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(comun.marcarMiles(arrValorPension.get(i).toString()));
      out.write("</td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    ");
if(arrValorARL.get(i).toString().equals("0")){
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">-</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(comun.marcarMiles(arrValorARL.get(i).toString()));
      out.write("</td>\n");
      out.write("                                                     ");
}
      out.write("                                                \n");
      out.write("                                                    <td class=\"DATOREPORTE\" style=\"text-align: center;border-right: 1px solid #116043\">");
      out.print(comun.validarEstado(arrEstados.get(i).toString()));
      out.write("</td>                                                    \n");
      out.write("                                                </tr>\n");
      out.write("                                                ");
 if((arrEstados.get(i).toString().equals("E"))){
                                                            ftValorPorc = comun.redondear(Double.parseDouble(arrPocPagos.get(i).toString()),0);
                                                            sumaPorc=sumaPorc+ftValorPorc;

                                                            ftValorSalud = Integer.parseInt(arrValorSalud.get(i).toString());
                                                            sumaSalud = sumaSalud + ftValorSalud;

                                                            ftValorPension = Integer.parseInt(arrValorPension.get(i).toString());
                                                            sumaPension = sumaPension + ftValorPension;

                                                            ftValorARL = Integer.parseInt(arrValorARL.get(i).toString());
                                                            sumaARL = sumaARL + ftValorARL;
                                                }
      out.write("\n");
      out.write("                                            ");
}
      out.write("\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"2\" class=\"SUBTITULOFORM\" style=\"border-bottom: 1px solid #116043;border-right:  1px solid #116043;\">Total ejecutado</td>\n");
      out.write("                                                <td class=\"TEXTONORMAL\" style=\"font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;\">");
      out.print(sumaPorc);
      out.write("%</td>\n");
      out.write("                                                <td class=\"TEXTONORMAL\" style=\"font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;\">$");
      out.print(comun.marcarMiles(String.valueOf(sumaSalud)));
      out.write("</td>\n");
      out.write("                                                <td class=\"TEXTONORMAL\" style=\"font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;\">$");
      out.print(comun.marcarMiles(String.valueOf(sumaPension)));
      out.write("</td>\n");
      out.write("                                                <td class=\"TEXTONORMAL\" style=\"font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;\">$");
      out.print(comun.marcarMiles(String.valueOf(sumaARL)));
      out.write("</td>\n");
      out.write("                                                <td colspan=\"2\"></td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                    <td colspan=\"8\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                        <div class=\"noPrint\">\n");
      out.write("                                                            <input type=\"button\" value=\"Volver\" id=\"btnVolver\" class=\"BOTONFORM\" />\n");
      out.write("                                                       </div>\n");
      out.write("                                                    </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                        </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>   \n");
      out.write("                        </table>   \n");
      out.write("                    ");
}else{
      out.write("\n");
      out.write("                    <br />\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class='TEXTOFALLO'>NO SE ENCONTRÓ UN PLAN DE PAGOS ASOCIADO AL CONTRATO #");
      out.print(strConsecutivo);
      out.write(".</td>\n");
      out.write("                            </tr>\n");
      out.write("                            <tr><td style=\"height: 0px;\"></td></tr>\n");
      out.write("                            <tr>\n");
      out.write("                                    <td class=\"CELDABOTONFORM\">\n");
      out.write("                                        <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\" />\n");
      out.write("                                    </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <div id=\"dMensaje\">\n");
      out.write("                    </div>\n");
      out.write("                </section>\n");
      out.write("               <footer>        \n");
      out.write("                <div id=\"dFooter\" class=\"FOOTER\">\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer_print.jsp", out, false);
      out.write("          \n");
      out.write("                </div>\n");
      out.write("            </footer>            \n");
      out.write("        </div>            \n");
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
