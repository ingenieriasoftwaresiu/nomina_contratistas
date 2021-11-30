package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Negocio.Comunes;
import Conexion.GestionSQL;

public final class detalle_005fpago_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

    String strConsecutivo = request.getParameter("txtConsecutivo");
    String strCodigoPago = request.getParameter("txtCodigoPago");
    String strTipoUsuario = null, strJubilado="N", strIdContratista="";
    String strSQL = "", strCedula = null;
    Comunes comun = new Comunes();
    
     if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
        strCedula = (String) session.getAttribute("txtCedula");
    }     
     
    strSQL = "select p.txtNumPago, p.dtFechaPago, p.txtPorcPago, p.txtValorSalud, p.txtValorPension, p.txtValorARL, p.txtIdEstadoPago, p.txtRutaArchivo, p.txtRutaFormato from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' and p.txtNumPago = '" + strCodigoPago + "'";
    String[] strDatosPago = GestionSQL.getFila(strSQL, "Nomina");
      
    strSQL = "select c.txtDuracion, c.txtIdEstado, c.txtIdContratista from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
    String[] strDatosContrato = GestionSQL.getFila(strSQL, "Nomina");
    
    strSQL = "select p.txtNumPago from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' ORDER BY CAST(p.txtNumPago AS SIGNED) DESC limit 1";
    String[] strDatos = GestionSQL.getFila(strSQL, "Nomina");
    
    String[] strDatosContratista = null;
    strIdContratista = strDatosContrato[2];
    
    strSQL = "SELECT txtJubilado FROM nomina.tbl_contratistas WHERE txtNumId = '" + strIdContratista + "'";
    strDatosContratista = GestionSQL.getFila(strSQL,"Nomina");
    strJubilado = strDatosContratista[0];
    
 
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery.filestyle.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/plan_pagos.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>\n");
      out.write("        <title>Detalle de pago</title>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            window.onbeforeunload = cerrar;\n");
      out.write("            function cerrar(){\n");
      out.write("                opener.frmPlanPagos.btnRefrescar.click();\n");
      out.write("            }                   \n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("       </header>\n");
      out.write("       <section>\n");
      out.write("            <div align=\"center\">\n");
      out.write("                <br />\n");
      out.write("                 <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"TITULOFORM\">DETALLE DE PAGO</td>\n");
      out.write("                        </tr>                \n");
      out.write("                        <tr>\n");
      out.write("                            <td>\n");
      out.write("                                <form method=\"POST\" id=\"frmDetallePago\" name=\"frmDetallePago\" enctype=\"multipart/form-data\" action=\"DetallePago\" onsubmit=\"return validar();\">\n");
      out.write("                                    <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmDetallePago\">                                          \n");
      out.write("                                    <input type=\"hidden\" name=\"txtConsecutivo\" id=\"txtConsecutivo\" value=\"");
      out.print(strConsecutivo);
      out.write("\" />\n");
      out.write("                                    <input type=\"hidden\" name=\"txtRutaArchivo\" id=\"txtRutaArchivo\" value=\"");
      out.print(strDatosPago[7]);
      out.write("\" />\n");
      out.write("                                    <input type=\"hidden\" name=\"txtRutaFormato\" id=\"txtRutaFormato\" value=\"");
      out.print(strDatosPago[8]);
      out.write("\" />\n");
      out.write("                                    <input type=\"hidden\" name=\"txtIdEstado\" id=\"txtIdEstado\" value=\"");
      out.print(strDatosPago[6]);
      out.write("\" />\n");
      out.write("                                    <input type=\"hidden\" name=\"txtDuracionContrato\" id=\"txtDuracionContrato\" value=\"");
      out.print(strDatosContrato[0]);
      out.write("\" />\n");
      out.write("                                    <input type=\"hidden\" name=\"txtUltimoPago\" id=\"txtUltimoPago\" value=\"");
      out.print(strDatos[0]);
      out.write("\" />\n");
      out.write("                                    <input type=\"hidden\" name=\"txtJubilado\" id=\"txtJubilado\" value=\"");
      out.print(strJubilado);
      out.write("\" />\n");
      out.write("                                    <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">         \n");
      out.write("                                        <tr>                                    \n");
      out.write("                                            <td class=\"LABELFORM\"><label for=\"txtNumPago\" id=\"lblNumPago\">Nro. de pago:</label></td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                <input type=\"text\" name=\"txtNumPago\" id=\"txtNumPago\" class=\"CAMPOFORM\" value=\"");
      out.print(strDatosPago[0]);
      out.write("\" readOnly>                                     \n");
      out.write("                                            </td>       \n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgNumPago\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"LABELFORM\"><label for=\"txtFechaPago\" id=\"lblFechaPago\">Fecha de pago:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                <input type=\"text\" name=\"txtFechaPago\" id=\"txtFechaPago\" class=\"CAMPOFORM\" value=\"");
      out.print(strDatosPago[1]);
      out.write("\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                            </td>                  \n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgFechaPago\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                            </td>\n");
      out.write("                                        </tr>\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td class=\"LABELFORM\"><label for=\"txtEstado\" id=\"lblEstado\">Estado:</label></td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                <input type=\"text\" name=\"txtEstado\" id=\"txtEstado\" class=\"CAMPOFORM\" value=\"");
      out.print(comun.validarEstado(strDatosPago[6]));
      out.write("\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                            </td> \n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgEstado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"LABELFORM\"><label for=\"txtPorcPagar\" id=\"lblPorcPagar\">% a pagar:</label></td>\n");
      out.write("                                            <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                <input type=\"text\" name=\"txtPorcPagar\" id=\"txtPorcPagar\" class=\"CAMPOFORM\" value=\"");
      out.print(strDatosPago[2]);
      out.write("\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                            </td>\n");
      out.write("                                            <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                <img src=\"Images/error.png\" id=\"imgPorcPagar\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                            </td>\n");
      out.write("                                        </tr>                \n");
      out.write("                                        \n");
      out.write("                                        ");
      out.write("\n");
      out.write("                                        \n");
      out.write("                                        ");
if(strDatosPago[6].equals("P")){
      out.write("\n");
      out.write("                                            ");
if(strTipoUsuario.equals("C")){
      out.write("\n");
      out.write("                                                <tr>                                                          \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorSalud\" id=\"lblValorSalud\">* Valor salud ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorSalud\" id=\"txtValorSalud\" value=\"");
      out.print(strDatosPago[3]);
      out.write("\" class=\"CAMPOFORM\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorSalud\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    ");
if(strJubilado.equals("N")){
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtValorPension\" id=\"lblValorPension\">* Valor pensión ($):</label></td>                                        \n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtValorPension\" id=\"lblValorPension\">Valor pensión ($):</label></td>                                                        \n");
      out.write("                                                    ");
}
      out.write("                                                    \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorPension\" id=\"txtValorPension\" value=\"");
      out.print(strDatosPago[4]);
      out.write("\" class=\"CAMPOFORM\">      \n");
      out.write("                                                        ");
if(strJubilado.equals("S")){
      out.write("\n");
      out.write("                                                            <br />    \n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El contratista es Jubilado]</span>\n");
      out.write("                                                        ");
}
      out.write(" \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorPension\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorARL\" id=\"lblValorARL\">* Valor ARL ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorARL\" id=\"txtValorARL\" value=\"");
      out.print(strDatosPago[5]);
      out.write("\" class=\"CAMPOFORM\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorARL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    ");
if(strDatosPago[7].equals("-")){
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">* Soporte de pago:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"file\" name=\"txtAdjunto\" id=\"txtAdjunto\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El soporte debe adjuntarse en archivo PDF]</span>              \n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">* Soporte de pago:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"file\" name=\"txtAdjunto\" id=\"txtAdjunto\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El soporte debe adjuntarse en archivo PDF]</span><br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">NOTA: Este pago ya tiene un soporte adjunto. Si desea sustituirlo, adjunte un nuevo soporte.</span>\n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgAdjunto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>       \n");
      out.write("                                                <tr>\n");
      out.write("                                                    ");
if(strDatosPago[8].equals("-")){
      out.write("                              \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">* Formato de interventoría:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"file\" name=\"txtFormato\" id=\"txtFormato\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El formato debe adjuntarse en archivo PDF]</span>              \n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">* Formato de interventoría:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"file\" name=\"txtFormato\" id=\"txtFormato\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El formato debe adjuntarse en archivo PDF]</span><br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">NOTA: Este pago ya tiene un formato adjunto. Si desea sustituirlo, adjunte un nuevo formato.</span>\n");
      out.write("                                                        </td>                                                \n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFormato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtObs\" id=\"lblObs\">Observación:</label></td>\n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\" colspan=\"4\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtObs\" id=\"txtObs\" class=\"CAMPOFORM\" style=\"width: 650px;\" />\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgObs\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                ");
}
      out.write("\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                        ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("\n");
      out.write("                                                            <input type=\"submit\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;                               \n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        <input type=\"button\" value=\"Ver Histórico\" id=\"btnVerHistorico\" class=\"BOTONFORM\" onclick=\"verHistorico('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("');\" style=\"width: 100px;\">&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"button\" value=\"Salir\" id=\"btnSalir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>                                                \n");
      out.write("                                            ");
}else{
      out.write("\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorSalud\" id=\"lblValorSalud\">Valor salud ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorSalud\" id=\"txtValorSalud\" value=\"");
      out.print(strDatosPago[3]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorSalud\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorPension\" id=\"lblValorPension\">Valor pensión ($):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorPension\" id=\"txtValorPension\" value=\"");
      out.print(strDatosPago[4]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">      \n");
      out.write("                                                        ");
if(strJubilado.equals("S")){
      out.write("\n");
      out.write("                                                            <br />    \n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El contratista es Jubilado]</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorPension\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorARL\" id=\"lblValorARL\">Valor ARL ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorARL\" id=\"txtValorARL\" value=\"");
      out.print(strDatosPago[5]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorARL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    ");
if(strDatosPago[7].equals("-")){
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene soporte adjunto por parte del Contratista.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[7]);
      out.write("');\" />                                                            \n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgAdjunto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr> \n");
      out.write("                                                <tr>\n");
      out.write("                                                    ");
if(strDatosPago[8].equals("-")){
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"txtFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene formato adjunto por parte del Contratista.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"txtFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[8]);
      out.write("');\" />                                                            \n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgAdjunto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td> \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"CELDABOTONFORM\">   \n");
      out.write("                                                        ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                                            ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("                                                            \n");
      out.write("                                                                <input type=\"button\" value=\"Cambiar % a pagar\" id=\"btnCambiarPorc\" class=\"BOTONFORM\" onclick=\"cambiarPorcentaje('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','");
      out.print(strDatosPago[2]);
      out.write("','DD');\" style=\"width: 115px;\">&nbsp;&nbsp;\n");
      out.write("                                                                <input type=\"button\" value=\"Poner ejecutado\" id=\"btnEjecutar\" class=\"BOTONFORM\" onclick=\"ejecutarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','DD');\" style=\"width: 100px;\">&nbsp;&nbsp;                                                                \n");
      out.write("                                                            ");
}
      out.write("                                                            \n");
      out.write("                                                        ");
}
      out.write("                                                        \n");
      out.write("                                                        <input type=\"button\" value=\"Ver Histórico\" id=\"btnVerHistorico\" class=\"BOTONFORM\" onclick=\"verHistorico('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("');\" style=\"width: 100px;\">&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"button\" value=\"Salir\" id=\"btnSalir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                            ");
}
      out.write(" \n");
      out.write("                                        ");
}
      out.write("\n");
      out.write("                                        \n");
      out.write("                                        ");
      out.write("\n");
      out.write("                                        \n");
      out.write("                                        ");
if(strDatosPago[6].equals("PA")){
      out.write("\n");
      out.write("                                            ");
if(strTipoUsuario.equals("I")){
      out.write("\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorSalud\" id=\"lblValorSalud\">Valor salud ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorSalud\" id=\"txtValorSalud\" value=\"");
      out.print(strDatosPago[3]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorSalud\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorPension\" id=\"lblValorPension\">Valor pensión ($):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorPension\" id=\"txtValorPension\" value=\"");
      out.print(strDatosPago[4]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">             \n");
      out.write("                                                        ");
if(strJubilado.equals("S")){
      out.write("\n");
      out.write("                                                            <br />    \n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El contratista es Jubilado]</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorPension\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorARL\" id=\"lblValorARL\">Valor ARL ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorARL\" id=\"txtValorARL\" value=\"");
      out.print(strDatosPago[5]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorARL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    ");
if(strDatosPago[7].equals("-")){
      out.write("     \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>\n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene archivo adjunto, ya que se trata del último pago del contrato.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[7]);
      out.write("');\" />                                                            \n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgAdjunto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    ");
if(strDatosPago[8].equals("-")){
      out.write("                              \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene formato adjunto, ya que se trata del último pago del contrato.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[8]);
      out.write("');\" />\n");
      out.write("                                                        </td>                                                \n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFormato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>   \n");
      out.write("                                                ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtObs\" id=\"lblObs\">Observación:</label></td>\n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\" colspan=\"4\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtObs\" id=\"txtObs\" class=\"CAMPOFORM\" style=\"width: 650px;\" />\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgObs\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                ");
}
      out.write("\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"CELDABOTONFORM\">              \n");
      out.write("                                                        ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("\n");
      out.write("                                                            <input type=\"button\" value=\"Aprobar\" id=\"btnGuardar\" class=\"BOTONFORM\" onclick=\"preaprobarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','DD')\">&nbsp;&nbsp;                                                         \n");
      out.write("                                                            <input type=\"button\" value=\"Reprocesar\" id=\"btnReprocesar\" class=\"BOTONFORM\" onclick=\"reprocesarPagoI('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','DD')\">&nbsp;&nbsp;\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        <input type=\"button\" value=\"Ver Histórico\" id=\"btnVerHistorico\" class=\"BOTONFORM\" onclick=\"verHistorico('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("');\" style=\"width: 100px;\">&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"button\" value=\"Salir\" id=\"btnSalir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                            ");
}else{
      out.write("\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorSalud\" id=\"lblValorSalud\">Valor salud ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorSalud\" id=\"txtValorSalud\" value=\"");
      out.print(strDatosPago[3]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorSalud\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorPension\" id=\"lblValorPension\">Valor pensión ($):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorPension\" id=\"txtValorPension\" value=\"");
      out.print(strDatosPago[4]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">       \n");
      out.write("                                                        ");
if(strJubilado.equals("S")){
      out.write("\n");
      out.write("                                                            <br />    \n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El contratista es Jubilado]</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorPension\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorARL\" id=\"lblValorARL\">Valor ARL ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorARL\" id=\"txtValorARL\" value=\"");
      out.print(strDatosPago[5]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorARL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    ");
if(strDatosPago[7].equals("-")){
      out.write("     \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>\n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene archivo adjunto, ya que se trata del último pago del contrato.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[7]);
      out.write("');\" />                                                            \n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgAdjunto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    ");
if(strDatosPago[8].equals("-")){
      out.write("                              \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene archivo formato, ya que se trata del último pago del contrato.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[8]);
      out.write("');\" />\n");
      out.write("                                                        </td>                                                \n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFormato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>                                                \n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"CELDABOTONFORM\"> \n");
      out.write("                                                        ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                                            ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("                                                            \n");
      out.write("                                                                <input type=\"button\" value=\"Cambiar % a pagar\" id=\"btnCambiarPorc\" class=\"BOTONFORM\" onclick=\"cambiarPorcentaje('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','");
      out.print(strDatosPago[2]);
      out.write("','DD');\" style=\"width: 115px;\">&nbsp;&nbsp;\n");
      out.write("                                                                <input type=\"button\" value=\"Poner ejecutado\" id=\"btnEjecutar\" class=\"BOTONFORM\" onclick=\"ejecutarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','DD');\" style=\"width: 100px;\">&nbsp;&nbsp;                                                                \n");
      out.write("                                                            ");
}
      out.write("                                                            \n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        <input type=\"button\" value=\"Ver Histórico\" id=\"btnVerHistorico\" class=\"BOTONFORM\" onclick=\"verHistorico('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("');\" style=\"width: 100px;\">&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"button\" value=\"Salir\" id=\"btnSalir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>                                            \n");
      out.write("                                            ");
}
      out.write("\n");
      out.write("                                        ");
}
      out.write("                                                         \n");
      out.write("                                        \n");
      out.write("                                        ");
      out.write("\n");
      out.write("                                        \n");
      out.write("                                        ");
if(strDatosPago[6].equals("PRA")){
      out.write("\n");
      out.write("                                            ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorSalud\" id=\"lblValorSalud\">Valor salud ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorSalud\" id=\"txtValorSalud\" value=\"");
      out.print(strDatosPago[3]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorSalud\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorPension\" id=\"lblValorPension\">Valor pensión ($):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorPension\" id=\"txtValorPension\" value=\"");
      out.print(strDatosPago[4]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">              \n");
      out.write("                                                        ");
if(strJubilado.equals("S")){
      out.write("\n");
      out.write("                                                            <br />    \n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El contratista es Jubilado]</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorPension\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorARL\" id=\"lblValorARL\">Valor ARL ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorARL\" id=\"txtValorARL\" value=\"");
      out.print(strDatosPago[5]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorARL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    ");
if(strDatosPago[7].equals("-")){
      out.write("     \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>\n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene archivo adjunto, ya que se trata del último pago del contrato.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[7]);
      out.write("');\" />                                                            \n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgAdjunto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    ");
if(strDatosPago[8].equals("-")){
      out.write("                              \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene formato adjunto, ya que se trata del último pago del contrato.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[8]);
      out.write("');\" />\n");
      out.write("                                                        </td>                                                \n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFormato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>   \n");
      out.write("                                                ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtObs\" id=\"lblObs\">Observación:</label></td>\n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\" colspan=\"4\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtObs\" id=\"txtObs\" class=\"CAMPOFORM\" style=\"width: 650px;\" />\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgObs\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                ");
}
      out.write("\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"CELDABOTONFORM\">              \n");
      out.write("                                                        ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("\n");
      out.write("                                                            <input type=\"button\" value=\"Aprobar\" id=\"btnGuardar\" class=\"BOTONFORM\" onclick=\"aprobarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','DD')\">&nbsp;&nbsp;                                                         \n");
      out.write("                                                            <input type=\"button\" value=\"Reprocesar\" id=\"btnReprocesar\" class=\"BOTONFORM\" onclick=\"reprocesarPagoC('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','DD')\">&nbsp;&nbsp;\n");
      out.write("                                                            <input type=\"button\" value=\"Cambiar % a pagar\" id=\"btnCambiarPorc\" class=\"BOTONFORM\" onclick=\"cambiarPorcentaje('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','");
      out.print(strDatosPago[2]);
      out.write("','DD');\" style=\"width: 115px;\">&nbsp;&nbsp;\n");
      out.write("                                                            <input type=\"button\" value=\"Poner ejecutado\" id=\"btnEjecutar\" class=\"BOTONFORM\" onclick=\"ejecutarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','DD');\" style=\"width: 100px;\">&nbsp;&nbsp;\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        <input type=\"button\" value=\"Ver Histórico\" id=\"btnVerHistorico\" class=\"BOTONFORM\" onclick=\"verHistorico('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("');\" style=\"width: 100px;\">&nbsp;&nbsp;                                                        \n");
      out.write("                                                        <input type=\"button\" value=\"Salir\" id=\"btnSalir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                            ");
}else{
      out.write("\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorSalud\" id=\"lblValorSalud\">Valor salud ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorSalud\" id=\"txtValorSalud\" value=\"");
      out.print(strDatosPago[3]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorSalud\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorPension\" id=\"lblValorPension\">Valor pensión ($):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorPension\" id=\"txtValorPension\" value=\"");
      out.print(strDatosPago[4]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">   \n");
      out.write("                                                        ");
if(strJubilado.equals("S")){
      out.write("\n");
      out.write("                                                            <br />    \n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\">[El contratista es Jubilado]</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorPension\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>                                       \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValorARL\" id=\"lblValorARL\">Valor ARL ($):</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValorARL\" id=\"txtValorARL\" value=\"");
      out.print(strDatosPago[5]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValorARL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    ");
if(strDatosPago[7].equals("-")){
      out.write("     \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>\n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene archivo adjunto, ya que se trata del último pago del contrato.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[7]);
      out.write("');\" />                                                            \n");
      out.write("                                                        </td>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgAdjunto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    ");
if(strDatosPago[8].equals("-")){
      out.write("                              \n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"MSGAVISOOBLG\">NOTA: No se tiene archivo formato, ya que se trata del último pago del contrato.</td>\n");
      out.write("                                                    ");
}else{
      out.write("\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                        <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[8]);
      out.write("');\" />\n");
      out.write("                                                        </td>                                                \n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFormato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>                                                \n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"CELDABOTONFORM\">                                                         \n");
      out.write("                                                        <input type=\"button\" value=\"Ver Histórico\" id=\"btnVerHistorico\" class=\"BOTONFORM\" onclick=\"verHistorico('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("');\" style=\"width: 100px;\">&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"button\" value=\"Salir\" id=\"btnSalir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>                                            \n");
      out.write("                                            ");
}
      out.write("\n");
      out.write("                                        ");
}
      out.write("\n");
      out.write("                                        \n");
      out.write("                                        ");
      out.write("\n");
      out.write("                                       \n");
      out.write("                                        ");
if((strDatosPago[6].equals("A")) || (strDatosPago[6].equals("E"))){
      out.write("\n");
      out.write("                                            <tr>                                       \n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtValorSalud\" id=\"lblValorSalud\">Valor salud ($):</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtValorSalud\" id=\"txtValorSalud\" value=\"");
      out.print(strDatosPago[3]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgValorSalud\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtValorPension\" id=\"lblValorPension\">Valor pensión ($):</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtValorPension\" id=\"txtValorPension\" value=\"");
      out.print(strDatosPago[4]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">   \n");
      out.write("                                                    ");
if(strJubilado.equals("S")){
      out.write("\n");
      out.write("                                                        <br />    \n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\">[El contratista es Jubilado]</span>\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgValorPension\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                           \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>                                       \n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtValorARL\" id=\"lblValorARL\">Valor ARL ($):</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtValorARL\" id=\"txtValorARL\" value=\"");
      out.print(strDatosPago[5]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                     \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgValorARL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                ");
if(strDatosPago[7].equals("-")){
      out.write("     \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>\n");
      out.write("                                                    <td class=\"MSGAVISOOBLG\">NOTA: No se tiene archivo adjunto por parte del Contratista.</td>\n");
      out.write("                                                ");
}else{
      out.write("\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtAdjunto\" id=\"lblAdjunto\">Soporte adjunto:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[7]);
      out.write("');\" />                                                            \n");
      out.write("                                                    </td>\n");
      out.write("                                                ");
}
      out.write("\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgAdjunto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                           \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                ");
if(strDatosPago[8].equals("-")){
      out.write("                              \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                    <td class=\"MSGAVISOOBLG\">NOTA: No se tiene formato adjunto por parte del Contratista.</td>\n");
      out.write("                                                ");
}else{
      out.write("\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtFormato\" id=\"lblFormato\">Formato adjunto:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosPago[8]);
      out.write("');\" />\n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                ");
}
      out.write("\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgFormato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>                        \n");
      out.write("                                            ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                                                ");
if(strDatosPago[6].equals("A")){
      out.write("\n");
      out.write("                                                        ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("\n");
      out.write("                                                            <tr>\n");
      out.write("                                                                <td class=\"LABELFORM\"><label for=\"txtObs\" id=\"lblObs\">Observación:</label></td>\n");
      out.write("                                                                <td class=\"CELDACAMPOFORM\" colspan=\"4\">\n");
      out.write("                                                                    <input type=\"text\" name=\"txtObs\" id=\"txtObs\" class=\"CAMPOFORM\" style=\"width: 650px;\" />\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                                    <img src=\"Images/error.png\" id=\"imgObs\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                                </td>\n");
      out.write("                                                            </tr>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    ");
}
      out.write("\n");
      out.write("                                            ");
}
      out.write("\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"CELDABOTONFORM\">  \n");
      out.write("                                                    ");
if(strTipoUsuario.equals("A")){
      out.write(" \n");
      out.write("                                                        ");
if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){
      out.write("\n");
      out.write("                                                            ");
if(!(strDatosPago[6].equals("E"))){
      out.write("                                                                                                              \n");
      out.write("                                                                    <input type=\"button\" value=\"Poner ejecutado\" id=\"btnEjecutar\" class=\"BOTONFORM\" onclick=\"ejecutarPago('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("','DD');\" style=\"width: 100px;\">&nbsp;&nbsp;                                                                    \n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                        ");
}
      out.write("                                                        \n");
      out.write("                                                     ");
}
      out.write("\n");
      out.write("                                                     <input type=\"button\" value=\"Ver Histórico\" id=\"btnVerHistorico\" class=\"BOTONFORM\" onclick=\"verHistorico('");
      out.print(strConsecutivo);
      out.write("','");
      out.print(strCodigoPago);
      out.write("');\" style=\"width: 100px;\">&nbsp;&nbsp;\n");
      out.write("                                                    <input type=\"button\" value=\"Salir\" id=\"btnSalir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>                                       \n");
      out.write("                                        ");
}
      out.write("                                           \n");
      out.write("                                        <tr><td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td></tr>\n");
      out.write("                                    </table>\n");
      out.write("                                </form>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                 </table>\n");
      out.write("            </div>\n");
      out.write("            <br />\n");
      out.write("            <div id=\"dLoader\" class=\"TEXTOEXITO\" style=\"display: none;\">\n");
      out.write("                <img src=\"Images/loader.gif\" style=\"vertical-align: middle;width: 30px;height: 30px;\"/>&nbsp;&nbsp;Procesando...\n");
      out.write("            </div>\n");
      out.write("            <div id=\"dMensaje\">\n");
      out.write("            </div>                        \n");
      out.write("        </section>\n");
      out.write("       <footer>        \n");
      out.write("        <div id=\"dFooter\" class=\"FOOTER\">\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("          \n");
      out.write("        </div>\n");
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
