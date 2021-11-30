package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Vector;
import Conexion.GestionSQL;

public final class contratista_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    String strTipoRegistro = "";
    
    if(request.getParameter("txtTipoRegistro") != null){
        strTipoRegistro = (String) request.getParameter("txtTipoRegistro");
    }else{
        strTipoRegistro = "C";
    }
   
    String[] strDatosContratista = null;
    String[] strTemp=null;
    String strSQL = "";
    Vector arrIdsTI = new Vector();
    Vector arrNombresTI = new Vector();
    
     if (strAccion == null){
            response.sendRedirect("cerrar.jsp");
     }else{
        
        strSQL="select ti.txtIdentificador, ti.txtNombre from users.users_tipos_id ti ORDER BY ti.txtNombre";
        Vector arrTiposIds = GestionSQL.consultaSQL(strSQL, "Users","2");
        
        for(int i=0;i<arrTiposIds.size();i++){
            strTemp = arrTiposIds.get(i).toString().split(">");
            arrIdsTI.add(strTemp[0]);
            arrNombresTI.add(strTemp[1]);
        }
               
        if (strAccion.equals("V")){
            strSQL = "select * from nomina.tbl_contratistas c where c.txtNumId = '" +  strCodigo + "'";
            strDatosContratista = GestionSQL.getFila(strSQL,"Nomina");                               
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
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>  \n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/contratista.js\"></script>   \n");
      out.write("        ");
if(strTipoRegistro.equals("I")){
      out.write("\n");
      out.write("            <title>Sistema de Gestión de Pagos a Contratistas: Interventor</title>\n");
      out.write("        ");
}else{
      out.write("\n");
      out.write("            <title>Sistema de Gestión de Pagos a Contratistas: Contratista</title>\n");
      out.write("        ");
}
      out.write("         \n");
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
      out.write("                                ");
if(strTipoRegistro.equals("I")){
      out.write("\n");
      out.write("                                    <td class=\"TITULOFORM\">NUEVO REGISTRO DE INTERVENTOR</td>\n");
      out.write("                                ");
}else{
      out.write("\n");
      out.write("                                    <td class=\"TITULOFORM\">NUEVO REGISTRO DE CONTRATISTA</td>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmContratista\" name=\"frmContratista\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmContratista\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"C\">   \n");
      out.write("                                        <input type=\"hidden\" name=\"txtTipoRegistro\" id=\"txtTipoRegistro\" value=\"");
      out.print(strTipoRegistro);
      out.write("\">\n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">                                                       \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtTipoId\" id=\"lblTipoId\">* Tipo de identificación:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                   <select id=\"txtTipoId\" name=\"txtTipoId\" class=\"CAMPOSELECT\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsTI.size();i++){
      out.write("\n");
      out.write("                                                        ");
if(arrIdsTI.get(i).equals("CC")){
      out.write("\n");
      out.write("                                                                <option value=\"");
      out.print(arrIdsTI.get(i));
      out.write("\" selected>");
      out.print(arrNombresTI.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}else{
      out.write("\n");
      out.write("                                                                <option value=\"");
      out.print(arrIdsTI.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresTI.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>                         \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgTipoId\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtNumId\" id=\"lblNumId\">* Nro. de identificación:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtNumId\" id=\"txtNumId\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgNumId\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtNombres\" id=\"lblNombres\">* Nombres:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtNombres\" id=\"txtNombres\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgNombres\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtApellidos\" id=\"lblApellidos\">* Apellidos:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtApellidos\" id=\"txtApellidos\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgApellidos\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtDireccion\" id=\"lblDireccion\">Dirección:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtDireccion\" id=\"txtDireccion\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgDireccion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtTelefono\" id=\"lblTelefono\">Teléfono:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtTelefono\" id=\"txtTelefono\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgTelefono\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\">                                           \n");
      out.write("                                                    <label for=\"txtEmail\" id=\"lblEmail\">* Correo electrónico:</label>                                                                       \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtEmail\" id=\"txtEmail\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgEmail\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtEstado\" id=\"lblEstado\">* Estado:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDARADIOFORM\" style=\"text-align: left;\">\n");
      out.write("                                                    <input type=\"radio\" name=\"txtEstado\" id=\"rdActivo\" value=\"A\" checked>Activo&nbsp;&nbsp;\n");
      out.write("                                                    <input type=\"radio\" name=\"txtEstado\" id=\"rdInactivo\" value=\"I\">Inactivo \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgEstado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            ");
if(strTipoRegistro.equals("C")){
      out.write("\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtJubilado\" id=\"lblJubilado\">* ¿Es jubilado?:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDARADIOFORM\" style=\"text-align: left;\">\n");
      out.write("                                                        <input type=\"radio\" name=\"txtJubilado\" id=\"rdJubiladoSi\" value=\"S\">Si&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"radio\" name=\"txtJubilado\" id=\"rdJubiladoNo\" value=\"N\">No \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgJubilado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                             ");
}
      out.write("\n");
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
      out.write("                                <td class=\"TITULOFORM\">REGISTRO DE CONTRATISTA</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmContratista\" name=\"frmContratista\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmContratista\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"V\">       \n");
      out.write("                                        <input type=\"hidden\" name=\"txtTipoRegistro\" id=\"txtTipoRegistro\" value=\"");
      out.print(strTipoRegistro);
      out.write("\">\n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">                                                       \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtTipoId\" id=\"lblTipoId\">* Tipo de identificación:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                   <select id=\"txtTipoId\" name=\"txtTipoId\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsTI.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsTI.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresTI.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>                         \n");
      out.write("                                                <script type=\"text/javascript\">\n");
      out.write("                                                     $(\"#txtTipoId option[value='");
      out.print(strDatosContratista[0]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                </script>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgTipoId\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtNumId\" id=\"lblNumId\">* Nro. de identificación:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtNumId\" id=\"txtNumId\" value=\"");
      out.print(strDatosContratista[1]);
      out.write("\" class=\"CAMPOFORM\" disabled=\"disabled\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgNumId\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtNombres\" id=\"lblNombres\">* Nombres:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtNombres\" id=\"txtNombres\" value=\"");
      out.print(strDatosContratista[2]);
      out.write("\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgNombres\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtApellidos\" id=\"lblApellidos\">* Apellidos:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtApellidos\" id=\"txtApellidos\" value=\"");
      out.print(strDatosContratista[3]);
      out.write("\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgApellidos\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtDireccion\" id=\"lblDireccion\">Dirección:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtDireccion\" id=\"txtDireccion\" value=\"");
      out.print(strDatosContratista[4]);
      out.write("\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgDireccion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtTelefono\" id=\"lblTelefono\">Teléfono:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtTelefono\" id=\"txtTelefono\" value=\"");
      out.print(strDatosContratista[5]);
      out.write("\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgTelefono\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtEmail\" id=\"lblEmail\">Correo electrónico:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtEmail\" id=\"txtEmail\" value=\"");
      out.print(strDatosContratista[6]);
      out.write("\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgEmail\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtEstado\" id=\"lblEstado\">* Estado:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDARADIOFORM\" style=\"text-align: left;\">\n");
      out.write("                                                    <input type=\"radio\" name=\"txtEstado\" id=\"rdActivo\" value=\"A\">Activo&nbsp;&nbsp;\n");
      out.write("                                                    <input type=\"radio\" name=\"txtEstado\" id=\"rdInactivo\" value=\"I\">Inactivo \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                            $(\"[name=txtEstado]\").filter(\"[value='");
      out.print(strDatosContratista[7]);
      out.write("']\").prop(\"checked\",true);\n");
      out.write("                                                    </script>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgEstado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            ");
if(strTipoRegistro.equals("C")){
      out.write("\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtJubilado\" id=\"lblJubilado\">* ¿Es jubilado?:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDARADIOFORM\" style=\"text-align: left;\">\n");
      out.write("                                                        <input type=\"radio\" name=\"txtJubilado\" id=\"rdJubiladoSi\" value=\"S\">Si&nbsp;&nbsp;\n");
      out.write("                                                        <input type=\"radio\" name=\"txtJubilado\" id=\"rdJubiladoNo\" value=\"N\">No \n");
      out.write("                                                        <script type=\"text/javascript\">\n");
      out.write("                                                                $(\"[name=txtJubilado]\").filter(\"[value='");
      out.print(strDatosContratista[8]);
      out.write("']\").prop(\"checked\",true);\n");
      out.write("                                                        </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgJubilado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                             ");
}
      out.write("\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"CELDABOTONFORM\"><input type=\"button\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\"> &nbsp;&nbsp;<input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\"></td></tr>\n");
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
