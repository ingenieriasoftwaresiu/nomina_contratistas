package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Conexion.GestionSQL;
import java.util.Vector;

public final class contrato_jsp extends org.apache.jasper.runtime.HttpJspBase
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
 
    String strAccion = (String) request.getParameter("txtAccion");      
    String strCodigo = (String) request.getParameter("txtCodigo");  
    String strTipoUsuario = null;
        
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);    
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
    }
   
    String[] strDatosContrato = null;
    String[] strTemp=null;
    String[] strNomInt = null;
    String strIdEstado = "";
    String[] strNomEstado = null;
    String[] strPlanPagos = null;
    String[] strOTROSIs = null;
    String strSQL = "";
    Vector arrIdsC = new Vector();
    Vector arrNombresC = new Vector();
    Vector arrIdsI = new Vector();
    Vector arrNombresI = new Vector();
    Vector arrIdsG = new Vector();
    Vector arrNombresG = new Vector();
    Vector arrIdsCC = new Vector();
    Vector arrNombresCC = new Vector();    
    Vector arrIdsTC= new Vector();
    Vector arrNombresTC = new Vector();    
    
     if (strAccion == null){
            response.sendRedirect("cerrar.jsp");
     }else{
        
        if (strAccion.equals("C")){
            strIdEstado = "CR";
            
            strSQL = "SELECT e.txtNombre from nomina.tbl_estados_contrato e where e.txtCodigo = '" + strIdEstado + "'";
            strNomEstado = GestionSQL.getFila(strSQL,"Nomina");
            
            strSQL="select c.txtNumId, CONCAT(c.txtApellidos,' ',c.txtNombres) as Nombre from nomina.tbl_contratistas c where c.txtEstado = 'A' ORDER BY Nombre";
        }else{
            strSQL="select c.txtNumId, CONCAT(c.txtApellidos,' ',c.txtNombres) as Nombre from nomina.tbl_contratistas c ORDER BY Nombre";
        }
        
        Vector arrContratistas = GestionSQL.consultaSQL(strSQL, "Nomina","2");
        
        if (arrContratistas != null){
            for(int i=0;i<arrContratistas.size();i++){
                strTemp = arrContratistas.get(i).toString().split(">");
                arrIdsC.add(strTemp[0]);
                arrNombresC.add(strTemp[1]);
            }
        }else{
            arrIdsC.add("");
            arrNombresC.add("");
        }
        
        if (strAccion.equals("C")){
            strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl where tbl.txtEstadoActual = 'A' ORDER BY tbl.txtNombreCompleto";
        }else{
            strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl ORDER BY tbl.txtNombreCompleto";
        }
        
        //strSQL="select ixp.txtIdInterventor, ixp.txtCodProyecto from nomina.tbl_interventores_x_proyecto ixp ORDER BY ixp.txtIdInterventor";
        Vector arrInterventores = GestionSQL.consultaSQL(strSQL,"Users","2");
        
        if (arrInterventores != null){
            for(int i=0;i<arrInterventores.size();i++){
                strTemp = arrInterventores.get(i).toString().split(">");
                arrIdsI.add(strTemp[0]);         
                arrNombresI.add(strTemp[1]);                
            }
        }else{
            arrIdsI.add("");         
            arrNombresI.add("");
        }
        
        strSQL = "select tc.txtCodigo, tc.txtNombre from nomina.tbl_tipos_contrato tc ORDER BY tc.txtNombre";
        Vector arrTiposContrato = GestionSQL.consultaSQL(strSQL, "Nomina", "2");
        
        for(int i=0;i<arrTiposContrato.size();i++){
            strTemp = arrTiposContrato.get(i).toString().split(">");
            arrIdsTC.add(strTemp[0]);
            arrNombresTC.add(strTemp[1]);
        }
        
        strSQL = "select g.txtCodigo, g.txtNombre from users.users_grupos_siu g ORDER BY g.txtNombre";
        Vector arrGrupos = GestionSQL.consultaSQL(strSQL, "Users", "2");
        
        for(int i=0;i<arrGrupos.size();i++){
            strTemp = arrGrupos.get(i).toString().split(">");
            arrIdsG.add(strTemp[0]);
            arrNombresG.add(strTemp[1]);
        }
        
        strSQL = "select c.txtCodigo, c.txtNombre from users.users_centros_costos c ORDER BY c.txtNombre";
        Vector arrCentrosCostos = GestionSQL.consultaSQL(strSQL, "Users", "2");
        
        for(int i=0;i<arrCentrosCostos.size();i++){
            strTemp = arrCentrosCostos.get(i).toString().split(">");
            arrIdsCC.add(strTemp[0]);
            arrNombresCC.add(strTemp[1]);
        }                
        
                       
        if (strAccion.equals("V")){
            strSQL = "select * from nomina.tbl_contratos c where c.txtConsecutivo = '" +  strCodigo + "'";
            strDatosContrato = GestionSQL.getFila(strSQL,"Nomina");                              
                        
            strSQL = "SELECT e.txtNombre from nomina.tbl_estados_contrato e where e.txtCodigo = '" + strDatosContrato[13] + "'";
            strNomEstado = GestionSQL.getFila(strSQL,"Nomina");
            
            strSQL = "select count(*) from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strCodigo + "'";
            strPlanPagos = GestionSQL.getFila(strSQL,"Nomina");
            
            strSQL = "select count(*) from nomina.tbl_otrosi_contratos o where o.txtIdContrato = '" + strCodigo + "'";
            strOTROSIs = GestionSQL.getFila(strSQL,"Nomina");
        }
     }    

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"SHORTCUT ICON\" href=\"Images/App.ico\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/forms.css\" />\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"Styles/calendar-system.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery-1.7.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/ajax.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/JSCalendar.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/JSCalendar-es.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/JSCalendar-setup.js\"></script>        \n");
      out.write("        <script type=\"text/javascript\" src=\"Scripts/jquery.filestyle.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/comunes.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" charset=\"UTF-8\" src=\"Scripts/contrato.js\"></script>\n");
      out.write("        <title>Sistema de Gestión de Pagos a Contratistas: Contratista</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("  \n");
      out.write("       </header>\n");
      out.write("       <section>\n");
      out.write("            <div align=\"center\">\n");
      out.write("                <br>\n");
      out.write("                    ");
if (strAccion.equals("C")){
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">NUEVO REGISTRO DE CONTRATO</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmContrato\" name=\"frmContrato\" enctype=\"multipart/form-data\" action=\"RegistroContrato\" onsubmit=\"return validarContrato();\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmContrato\" />\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"C\" />\n");
      out.write("                                        <input type=\"hidden\" name=\"txtIdEstado\" id=\"txtIdEstado\" value=\"");
      out.print(strIdEstado);
      out.write("\" />\n");
      out.write("                                        <input type=\"hidden\" name=\"txtRutaActaInicio\" id=\"txtRutaActaInicio\" value=\"-\" />\n");
      out.write("                                        <input type=\"hidden\" name=\"txtRutaActaFinalizacion\" id=\"txtRutaActaFinalizacion\" value=\"-\" />\n");
      out.write("                                        <input type=\"hidden\" name=\"txtRutaMinutaContrato\" id=\"txtRutaMinutaContrato\" value=\"-\" />                                        \n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">         \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos generales</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtFechaCreacion\" id=\"lblFechaCreacion\">Fecha de creación:</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtFechaCreacion\" id=\"txtFechaCreacion\" class=\"CAMPOFORM\" readOnly>\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgFechaCreacion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                    \n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtEstado\" id=\"lblEstado\">Estado actual:</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtEstado\" id=\"txtEstado\" value=\"");
      out.print(strNomEstado[0]);
      out.write("\" class=\"CAMPOFORM\" readonly>\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgEstado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                            \n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos básicos</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtConsecutivo\" id=\"lblConsecutivo\">* Consecutivo:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtConsecutivo\" id=\"txtConsecutivo\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgConsecutivo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtContratista\" id=\"lblContratista\">* Nombre del contratista:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                   <select id=\"txtContratista\" name=\"txtContratista\" class=\"CAMPOSELECT\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsC.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresC.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </select>      \n");
      out.write("                                                    <br />\n");
      out.write("                                                    <img src=\"Images/Agregar.png\" alt=\"Agregar Contratista\" title=\"Agregar Contratista\" id=\"btnAgregarContratista\" style=\"width: 15px;height: 15px;padding-top: 5px;\" />&nbsp;\n");
      out.write("                                                    <img src=\"Images/Ejecutar.png\" alt=\"Refrescar\" title=\"Refrescar\" class=\"IMGREFRESH\" style=\"width: 15px;height: 15px;padding-top: 5px;\" />&nbsp;\n");
      out.write("                                                    <img src=\"Images/lupa.gif\" alt=\"Buscar Contratista\" title=\"Buscar Contratista\" id=\"btnBuscarContratista\" style=\"width: 20px;height: 20px;padding-top: 5px;\" />\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgContratista\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtTipoContrato\" id=\"lblTipoContrato\">* Tipo de contrato:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <select id=\"txtTipoContrato\" name=\"txtTipoContrato\" class=\"CAMPOSELECT\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsTC.size();i++){
      out.write("\n");
      out.write("                                                        ");
if(arrIdsTC.get(i).equals("PS")){
      out.write("\n");
      out.write("                                                                 <option value=\"");
      out.print(arrIdsTC.get(i));
      out.write("\" selected>");
      out.print(arrNombresTC.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}else{
      out.write("\n");
      out.write("                                                                 <option value=\"");
      out.print(arrIdsTC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresTC.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("                                                           \n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>                                       \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgTipoContrato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtInterventor\" id=\"lblInterventor\">* Nombre del interventor:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                   <select id=\"txtInterventor\" name=\"txtInterventor\" class=\"CAMPOSELECT\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsI.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsI.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresI.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </select>             \n");
      out.write("                                                    <br />                                      \n");
      out.write("                                                    <img src=\"Images/Agregar.png\" alt=\"Agregar Interventor\" title=\"Agregar Interventor\" id=\"btnAgregarInterventor\" style=\"width: 15px;height: 15px;padding-top: 5px;\" />&nbsp;\n");
      out.write("                                                    <img src=\"Images/Ejecutar.png\" alt=\"Refrescar\" title=\"Refrescar\" class=\"IMGREFRESH\" style=\"width: 15px;height: 15px;padding-top: 5px;\" />&nbsp;\n");
      out.write("                                                    <img src=\"Images/lupa.gif\" alt=\"Buscar Interventor\" title=\"Buscar Interventor\" id=\"btnBuscarInterventor\" style=\"width: 20px;height: 20px;padding-top: 5px;\" />\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgInterventor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr> \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtGrupo\" id=\"lblGrupo\">* Grupo asociado:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <select id=\"txtGrupo\" name=\"txtGrupo\" class=\"CAMPOSELECT\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsG.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsG.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresG.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>                                       \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgGrupo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                                                                \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos de tiempos</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtFechaIni\" id=\"lblFechaIni\">* Fecha de inicio:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtFechaIni\" id=\"txtFechaIni\" class=\"CAMPOFORM\" style=\"width: 180px;\" readOnly>&nbsp;<img src=\"Images/Calendario.JPG\" class=\"IMGCALENDAR\" id=\"imgFechaIni\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgFechaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtDuracion\" id=\"lblDuracion\">* Duración (Días):</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtDuracion\" id=\"txtDuracion\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgDuracion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                           \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtFechaFin\" id=\"lblFechaFin\">Fecha de fin:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtFechaFin\" id=\"txtFechaFin\" class=\"CAMPOFORM\" style=\"width: 180px;\" readonly>&nbsp;<img src=\"Images/Calendario.JPG\" class=\"IMGCALENDAR\" id=\"imgFechaFin\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/Refresh.png\" id=\"imgRefresh\" alt=\"Campo obligatorio\" style=\"width: 30px;height: 30px;vertical-align: middle;text-align: left;\" onclick=\"refreshFechaFin();\">                                                            \n");
      out.write("                                                </td>                                                 \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos financieros</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtValor\" id=\"lblValor\">* Valor ($):</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtValor\" id=\"txtValor\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgValor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtCentroC\" id=\"lblCentroC\">* Centro de costos:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                   <select id=\"txtCentroC\" name=\"txtCentroC\" class=\"CAMPOSELECT\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsCC.size();i++){
      out.write("\n");
      out.write("                                                        <option value=\"");
      out.print(arrIdsCC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrIdsCC.get(i));
      out.write("&nbsp;-&nbsp;");
      out.print(arrNombresCC.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>                         \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgCentroC\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Otros datos</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtConsecutivoL\" id=\"lblConsecutivoL\">Consecutivo laborales:</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtConsecutivoL\" id=\"txtConsecutivoL\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgConsecutivoL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtNumCDP\" id=\"lblNumCDP\">Nro. de CDP:</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtNumCDP\" id=\"txtNumCDP\" class=\"CAMPOFORM\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgNumCDP\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr>                                    \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtObjeto\" id=\"lblObjeto\">* Objeto:</label></td>                                        \n");
      out.write("                                                <td colspan=\"4\" class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"hidden\" name=\"txtObjetoC\" id=\"txtObjetoC\" />\n");
      out.write("                                                    <div id=\"txtObjeto\" name=\"txtObjeto\" class=\"DIVEDITABLE\" contenteditable=\"true\" style=\"width: 650px;height: 100px;\">                                                        \n");
      out.write("                                                    </div>                                        \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgObjeto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos adjuntos</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>                                                                    \n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtActaInicio\" id=\"lblActaInicio\">Acta de inicio:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"file\" name=\"txtActaInicio\" id=\"txtActaInicio\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                    <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>              \n");
      out.write("                                                </td>                                                      \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgActaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtActaFin\" id=\"lblActaInicio\">Acta de finalización:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"file\" name=\"txtActaFin\" id=\"txtActaFin\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                    <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>              \n");
      out.write("                                                </td>                                                      \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgActaFin\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>                                                                    \n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtMinutaContrato\" id=\"lblActaInicio\">Minuta del contrato:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"file\" name=\"txtMinutaContrato\" id=\"txtMinutaContrato\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                    <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>              \n");
      out.write("                                                </td>                                                      \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgMinutaContrato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                    <input type=\"submit\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;\n");
      out.write("                                                    <input type=\"button\" value=\"Limpiar\" id=\"btnLimpiar\" class=\"BOTONFORM\"> &nbsp;&nbsp;\n");
      out.write("                                                    <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                        </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table>\n");
      out.write("                    ");
}else{
      out.write("\n");
      out.write("                    <input type=\"hidden\" name=\"txtTipoUsuario\" id=\"txtTipoUsuario\" value=\"");
      out.print(strTipoUsuario);
      out.write("\">\n");
      out.write("                    ");
if(strTipoUsuario.equals("A")){
      out.write("\n");
      out.write("                        ");
if(strDatosContrato[13].equals("CR")){
      out.write("\n");
      out.write("                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                                <tr>\n");
      out.write("                                    <td class=\"TITULOFORM\">REGISTRO DE CONTRATO</td>\n");
      out.write("                                </tr>                \n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <form method=\"POST\" id=\"frmContrato\" name=\"frmContrato\" enctype=\"multipart/form-data\" action=\"RegistroContrato\" onsubmit=\"return validarContrato();\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmContrato\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"V\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtIdEstado\" id=\"txtIdEstado\" value=\"");
      out.print(strDatosContrato[13]);
      out.write("\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtRutaActaInicio\" id=\"txtRutaActaInicio\" value=\"");
      out.print(strDatosContrato[15]);
      out.write("\" />\n");
      out.write("                                            <input type=\"hidden\" name=\"txtRutaActaFinalizacion\" id=\"txtRutaActaFinalizacion\" value=\"");
      out.print(strDatosContrato[16]);
      out.write("\" />\n");
      out.write("                                            <input type=\"hidden\" name=\"txtRutaMinutaContrato\" id=\"txtRutaMinutaContrato\" value=\"");
      out.print(strDatosContrato[17]);
      out.write("\" />\n");
      out.write("                                            <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">         \n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos generales</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtFechaCreacion\" id=\"lblFechaCreacion\">Fecha de creación:</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtFechaCreacion\" id=\"txtFechaCreacion\" value=\"");
      out.print(strDatosContrato[14]);
      out.write("\" class=\"CAMPOFORM\" readonly>\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFechaCreacion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                    \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtEstado\" id=\"lblEstado\">Estado actual:</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtEstado\" id=\"txtEstado\" value=\"");
      out.print(strNomEstado[0]);
      out.write("\" class=\"CAMPOFORM\" readonly>\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgEstado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                            \n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos básicos</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtConsecutivo\" id=\"lblConsecutivo\">* Consecutivo:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtConsecutivo\" id=\"txtConsecutivo\" value=\"");
      out.print(strDatosContrato[0]);
      out.write("\" class=\"CAMPOFORM\" readonly>                                           \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgConsecutivo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtContratista\" id=\"lblContratista\">* Nombre del contratista:</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                       <select id=\"txtContratista\" name=\"txtContratista\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsC.size();i++){
      out.write("\n");
      out.write("                                                                <option value=\"");
      out.print(arrIdsC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresC.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>     \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtContratista option[value='");
      out.print(strDatosContrato[1]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgContratista\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtTipoContrato\" id=\"lblTipoContrato\">* Tipo de contrato:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <select id=\"txtTipoContrato\" name=\"txtTipoContrato\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsTC.size();i++){
      out.write("\n");
      out.write("                                                            ");
if(arrIdsTC.get(i).equals("PS")){
      out.write("\n");
      out.write("                                                                     <option value=\"");
      out.print(arrIdsTC.get(i));
      out.write("\" selected>");
      out.print(arrNombresTC.get(i));
      out.write("</option>\n");
      out.write("                                                                ");
}else{
      out.write("\n");
      out.write("                                                                     <option value=\"");
      out.print(arrIdsTC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresTC.get(i));
      out.write("</option>\n");
      out.write("                                                                ");
}
      out.write("                                                           \n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>          \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtTipoContrato option[value='");
      out.print(strDatosContrato[3]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgTipoContrato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtInterventor\" id=\"lblInterventor\">* Nombre del interventor:</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                       <select id=\"txtInterventor\" name=\"txtInterventor\" class=\"CAMPOSELECT\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsI.size();i++){
      out.write("\n");
      out.write("                                                                <option value=\"");
      out.print(arrIdsI.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresI.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>        \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtInterventor option[value='");
      out.print(strDatosContrato[2]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgInterventor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr> \n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtGrupo\" id=\"lblGrupo\">* Grupo asociado:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <select id=\"txtGrupo\" name=\"txtGrupo\" class=\"CAMPOSELECT\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsG.size();i++){
      out.write("\n");
      out.write("                                                                <option value=\"");
      out.print(arrIdsG.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresG.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>           \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtGrupo option[value='");
      out.print(strDatosContrato[4]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgGrupo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                                                                \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos de tiempos</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtFechaIni\" id=\"lblFechaIni\">* Fecha de inicio:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtFechaIni\" id=\"txtFechaIni\" value=\"");
      out.print(strDatosContrato[5]);
      out.write("\" class=\"CAMPOFORM\" style=\"width: 180px;\" readOnly disabled=\"disabled\">\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFechaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtDuracion\" id=\"lblDuracion\">* Duración (Días):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtDuracion\" id=\"txtDuracion\" value=\"");
      out.print(strDatosContrato[7]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                           \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgDuracion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtFechaFin\" id=\"lblFechaFin\">Fecha de fin:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtFechaFin\" id=\"txtFechaFin\" value=\"");
      out.print(strDatosContrato[6]);
      out.write("\" class=\"CAMPOFORM\" style=\"width: 180px;\" readOnly disabled=\"disabled\">\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">                                                                                         \n");
      out.write("                                                    </td>                                                 \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos financieros</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValor\" id=\"lblValor\">* Valor ($):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValor\" id=\"txtValor\" value=\"");
      out.print(strDatosContrato[8]);
      out.write("\" class=\"CAMPOFORM\">                                           \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtCentroC\" id=\"lblCentroC\">* Centro de costos:</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                       <select id=\"txtCentroC\" name=\"txtCentroC\" class=\"CAMPOSELECT\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsCC.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsCC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrIdsCC.get(i));
      out.write("&nbsp;-&nbsp;");
      out.print(arrNombresCC.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>     \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtCentroC option[value='");
      out.print(strDatosContrato[9]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgCentroC\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Otros datos</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtConsecutivoL\" id=\"lblConsecutivoL\">Consecutivo laborales:</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtConsecutivoL\" id=\"txtConsecutivoL\" value=\"");
      out.print(strDatosContrato[10]);
      out.write("\" class=\"CAMPOFORM\">\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgConsecutivoL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtNumCDP\" id=\"lblNumCDP\">Nro. de CDP:</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtNumCDP\" id=\"txtNumCDP\" value=\"");
      out.print(strDatosContrato[11]);
      out.write("\" class=\"CAMPOFORM\">\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgNumCDP\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr>                                      \n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtObjeto\" id=\"lblObjeto\">* Objeto:</label></td>                                        \n");
      out.write("                                                    <td colspan=\"4\" class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"hidden\" name=\"txtObjetoC\" id=\"txtObjetoC\" />\n");
      out.write("                                                        <div id=\"txtObjeto\" class=\"DIVEDITABLE\" contenteditable=\"true\" style=\"width: 650px;height: 100px;\">                    \n");
      out.write("                                                            ");
      out.print(strDatosContrato[12]);
      out.write("\n");
      out.write("                                                        </div>                                                                                   \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgObjeto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos adjuntos</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>            \n");
      out.write("                                                    <td class=\"LABELFORM\" style=\"border-bottom: 1px solid #116043;\"><label for=\"txtActaInicio\" id=\"lblActaInicio\">Acta de inicio:</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\" style=\"border-bottom: 1px solid #116043;\">\n");
      out.write("                                                       <input type=\"file\" name=\"txtActaInicio\" id=\"txtActaInicio\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                       <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>            \n");
      out.write("                                                       ");
if(!strDatosContrato[15].equals("-")){
      out.write("   \n");
      out.write("                                                        <br />\n");
      out.write("                                                        <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[15]);
      out.write("');\" />\n");
      out.write("                                                        <br />\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>                                                        \n");
      out.write("                                                       ");
}
      out.write("\n");
      out.write("                                                   </td>                                                    \n");
      out.write("                                                     <td class=\"CELDAIMGERROR\" style=\"border-bottom: 1px solid #116043;\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgActaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\" style=\"border-bottom: 1px solid #116043;\"><label for=\"txtActaFin\" id=\"lblActaInicio\">Acta de finalización:</label></td>         \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\" style=\"border-bottom: 1px solid #116043;\">\n");
      out.write("                                                        <input type=\"file\" name=\"txtActaFin\" id=\"txtActaFin\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>              \n");
      out.write("                                                        ");
if(!strDatosContrato[16].equals("-")){
      out.write(" \n");
      out.write("                                                            <br />\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[16]);
      out.write("');\" />\n");
      out.write("                                                            <br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>                                                    \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\" style=\"border-bottom: 1px solid #116043;\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgActaFin\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>                                                                    \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtMinutaContrato\" id=\"lblActaInicio\">Minuta del contrato:</label></td>                       \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"file\" name=\"txtMinutaContrato\" id=\"txtMinutaContrato\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>              \n");
      out.write("                                                        ");
if(!strDatosContrato[17].equals("-")){
      out.write("\n");
      out.write("                                                            <br />\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[17]);
      out.write("');\" />\n");
      out.write("                                                            <br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>                                                     \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgMinutaContrato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                        <input type=\"submit\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;                                                                                                            \n");
      out.write("                                                        ");
if((Integer.parseInt(strPlanPagos[0])) > 0){
      out.write("\n");
      out.write("                                                            &nbsp;&nbsp;<input type=\"button\" value=\"Plan de pagos\" id=\"btnPlanPagos\" class=\"BOTONFORM\" style=\"width: 90px;\">\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        ");
if((Integer.parseInt(strOTROSIs[0])) > 0){
      out.write("\n");
      out.write("                                                            &nbsp;&nbsp;<input type=\"button\" value=\"Ver OTROSIs\" id=\"btnOtrosi\" class=\"BOTONFORM\" style=\"width: 90px;\">\n");
      out.write("                                                        ");
}
      out.write("                                                        \n");
      out.write("                                                        &nbsp;&nbsp;<input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td></tr>\n");
      out.write("                                            </table>\n");
      out.write("                                        </form>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\n");
      out.write("                        ");
}else{
      out.write("\n");
      out.write("                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                                <tr>\n");
      out.write("                                    <td class=\"TITULOFORM\">REGISTRO DE CONTRATO</td>\n");
      out.write("                                </tr>                \n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <form method=\"POST\" id=\"frmContrato\" name=\"frmContrato\" enctype=\"multipart/form-data\" action=\"RegistroContrato\" onsubmit=\"return validarContrato();\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmContrato\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"V\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtIdEstado\" id=\"txtIdEstado\" value=\"");
      out.print(strDatosContrato[13]);
      out.write("\">\n");
      out.write("                                            <input type=\"hidden\" name=\"txtRutaActaInicio\" id=\"txtRutaActaInicio\" value=\"");
      out.print(strDatosContrato[15]);
      out.write("\" />\n");
      out.write("                                            <input type=\"hidden\" name=\"txtRutaActaFinalizacion\" id=\"txtRutaActaFinalizacion\" value=\"");
      out.print(strDatosContrato[16]);
      out.write("\" />\n");
      out.write("                                            <input type=\"hidden\" name=\"txtRutaMinutaContrato\" id=\"txtRutaMinutaContrato\" value=\"");
      out.print(strDatosContrato[17]);
      out.write("\" />\n");
      out.write("                                            <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">         \n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos generales</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtFechaCreacion\" id=\"lblFechaCreacion\">Fecha de creación:</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtFechaCreacion\" id=\"txtFechaCreacion\" value=\"");
      out.print(strDatosContrato[14]);
      out.write("\" class=\"CAMPOFORM\" readonly>\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFechaCreacion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                    \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtEstado\" id=\"lblEstado\">Estado actual:</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtEstado\" id=\"txtEstado\" value=\"");
      out.print(strNomEstado[0]);
      out.write("\" class=\"CAMPOFORM\" readonly>\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgEstado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                            \n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos básicos</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtConsecutivo\" id=\"lblConsecutivo\">Consecutivo:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtConsecutivo\" id=\"txtConsecutivo\" value=\"");
      out.print(strDatosContrato[0]);
      out.write("\" class=\"CAMPOFORM\" readonly>                                           \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgConsecutivo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtContratista\" id=\"lblContratista\">Nombre del contratista:</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                       <select id=\"txtContratista\" name=\"txtContratista\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsC.size();i++){
      out.write("\n");
      out.write("                                                                <option value=\"");
      out.print(arrIdsC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresC.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>     \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtContratista option[value='");
      out.print(strDatosContrato[1]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgContratista\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtTipoContrato\" id=\"lblTipoContrato\">Tipo de contrato:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <select id=\"txtTipoContrato\" name=\"txtTipoContrato\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsTC.size();i++){
      out.write("\n");
      out.write("                                                            ");
if(arrIdsTC.get(i).equals("PS")){
      out.write("\n");
      out.write("                                                                     <option value=\"");
      out.print(arrIdsTC.get(i));
      out.write("\" selected>");
      out.print(arrNombresTC.get(i));
      out.write("</option>\n");
      out.write("                                                                ");
}else{
      out.write("\n");
      out.write("                                                                     <option value=\"");
      out.print(arrIdsTC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresTC.get(i));
      out.write("</option>\n");
      out.write("                                                                ");
}
      out.write("                                                           \n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>          \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtTipoContrato option[value='");
      out.print(strDatosContrato[3]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgTipoContrato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtInterventor\" id=\"lblInterventor\">Nombre del interventor:</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                       <select id=\"txtInterventor\" name=\"txtInterventor\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsI.size();i++){
      out.write("\n");
      out.write("                                                                <option value=\"");
      out.print(arrIdsI.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresI.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>        \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtInterventor option[value='");
      out.print(strDatosContrato[2]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgInterventor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr> \n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtGrupo\" id=\"lblGrupo\">Grupo asociado:</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <select id=\"txtGrupo\" name=\"txtGrupo\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsG.size();i++){
      out.write("\n");
      out.write("                                                                <option value=\"");
      out.print(arrIdsG.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresG.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>           \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtGrupo option[value='");
      out.print(strDatosContrato[4]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgGrupo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                                                                \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos de tiempos</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtFechaIni\" id=\"lblFechaIni\">Fecha de inicio:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtFechaIni\" id=\"txtFechaIni\" value=\"");
      out.print(strDatosContrato[5]);
      out.write("\" class=\"CAMPOFORM\" style=\"width: 180px;\" readOnly disabled=\"disabled\">\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgFechaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtDuracion\" id=\"lblDuracion\">Duración (Días):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtDuracion\" id=\"txtDuracion\" value=\"");
      out.print(strDatosContrato[7]);
      out.write("\" class=\"CAMPOFORM\" readOnly disabled=\"disabled\">                                           \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgDuracion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                           \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtFechaFin\" id=\"lblFechaFin\">Fecha de fin:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                                    <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtFechaFin\" id=\"txtFechaFin\" value=\"");
      out.print(strDatosContrato[6]);
      out.write("\" class=\"CAMPOFORM\" style=\"width: 180px;\" readonly disabled=\"disabled\">\n");
      out.write("                                                    </td>                               \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">                                                                                                                   \n");
      out.write("                                                    </td>                                                 \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos financieros</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtValor\" id=\"lblValor\">Valor ($):</label></td>                                        \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"text\" name=\"txtValor\" id=\"txtValor\" value=\"");
      out.print(strDatosContrato[8]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">                                           \n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgValor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtCentroC\" id=\"lblCentroC\">Centro de costos:</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                       <select id=\"txtCentroC\" name=\"txtCentroC\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                            <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                            ");
for (int i=0;i<arrIdsCC.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsCC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrIdsCC.get(i));
      out.write("&nbsp;-&nbsp;");
      out.print(arrNombresCC.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("\n");
      out.write("                                                    </select>     \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtCentroC option[value='");
      out.print(strDatosContrato[9]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgCentroC\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>                                                \n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Otros datos</td>\n");
      out.write("                                                </tr>                                                \n");
      out.write("                                                ");
if(strDatosContrato[13].equals("EJ")){
      out.write("\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtConsecutivoL\" id=\"lblConsecutivoL\">Consecutivo laborales:</label></td>\n");
      out.write("                                                        <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtConsecutivoL\" id=\"txtConsecutivoL\" value=\"");
      out.print(strDatosContrato[10]);
      out.write("\" class=\"CAMPOFORM\">\n");
      out.write("                                                        </td>                               \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgConsecutivoL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtNumCDP\" id=\"lblNumCDP\">Nro. de CDP:</label></td>\n");
      out.write("                                                        <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtNumCDP\" id=\"txtNumCDP\" value=\"");
      out.print(strDatosContrato[11]);
      out.write("\" class=\"CAMPOFORM\">\n");
      out.write("                                                        </td>                               \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgNumCDP\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>                                                \n");
      out.write("                                                    </tr>                                      \n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtObjeto\" id=\"lblObjeto\">* Objeto:</label></td>                                        \n");
      out.write("                                                        <td colspan=\"4\" class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"hidden\" name=\"txtObjetoC\" id=\"txtObjetoC\" />\n");
      out.write("                                                            <div id=\"txtObjeto\" class=\"DIVEDITABLE\" contenteditable=\"true\" style=\"width: 650px;height: 100px;\">                                                        \n");
      out.write("                                                                    ");
      out.print(strDatosContrato[12]);
      out.write("\n");
      out.write("                                                            </div>                                                                         \n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgObjeto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                ");
}else{
      out.write("\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtConsecutivoL\" id=\"lblConsecutivoL\">Consecutivo laborales:</label></td>\n");
      out.write("                                                        <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtConsecutivoL\" id=\"txtConsecutivoL\" value=\"");
      out.print(strDatosContrato[10]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">\n");
      out.write("                                                        </td>                               \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgConsecutivoL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                         <td class=\"LABELFORM\"><label for=\"txtNumCDP\" id=\"lblNumCDP\">Nro. de CDP:</label></td>\n");
      out.write("                                                        <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                            <input type=\"text\" name=\"txtNumCDP\" id=\"txtNumCDP\" value=\"");
      out.print(strDatosContrato[11]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">\n");
      out.write("                                                        </td>                               \n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgNumCDP\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td class=\"LABELFORM\"><label for=\"txtObjeto\" id=\"lblObjeto\">Objeto:</label></td>                                        \n");
      out.write("                                                        <td colspan=\"4\" class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"hidden\" name=\"txtObjetoC\" id=\"txtObjetoC\" />\n");
      out.write("                                                            <div id=\"txtObjeto\" class=\"DIVEDITABLE\" contenteditable=\"false\" style=\"width: 650px;height: 100px;\">                                                        \n");
      out.write("                                                                ");
      out.print(strDatosContrato[12]);
      out.write("\n");
      out.write("                                                            </div>                                                   \n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                            <img src=\"Images/error.png\" id=\"imgObjeto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                ");
}
      out.write("                              \n");
      out.write("                                                <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                                <tr>\n");
      out.write("                                                    <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos adjuntos</td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>            \n");
      out.write("                                                    <td class=\"LABELFORM\" style=\"border-bottom: 1px solid #116043;\"><label for=\"txtActaInicio\" id=\"lblActaInicio\">Acta de inicio:</label></td>\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\" style=\"border-bottom: 1px solid #116043;\">\n");
      out.write("                                                       <input type=\"file\" name=\"txtActaInicio\" id=\"txtActaInicio\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                       <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>            \n");
      out.write("                                                       ");
if(!strDatosContrato[15].equals("-")){
      out.write("   \n");
      out.write("                                                        <br />\n");
      out.write("                                                        <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[15]);
      out.write("');\" />\n");
      out.write("                                                        <br />\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>                                                        \n");
      out.write("                                                       ");
}
      out.write("\n");
      out.write("                                                   </td>                                                    \n");
      out.write("                                                     <td class=\"CELDAIMGERROR\" style=\"border-bottom: 1px solid #116043;\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgActaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                    <td class=\"LABELFORM\" style=\"border-bottom: 1px solid #116043;\"><label for=\"txtActaFin\" id=\"lblActaInicio\">Acta de finalización:</label></td>         \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\" style=\"border-bottom: 1px solid #116043;\">\n");
      out.write("                                                        <input type=\"file\" name=\"txtActaFin\" id=\"txtActaFin\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>              \n");
      out.write("                                                        ");
if(!strDatosContrato[16].equals("-")){
      out.write(" \n");
      out.write("                                                            <br />\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[16]);
      out.write("');\" />\n");
      out.write("                                                            <br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>                                                    \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\" style=\"border-bottom: 1px solid #116043;\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgActaFin\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr>                                                                    \n");
      out.write("                                                    <td class=\"LABELFORM\"><label for=\"txtMinutaContrato\" id=\"lblActaInicio\">Minuta del contrato:</label></td>                       \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"file\" name=\"txtMinutaContrato\" id=\"txtMinutaContrato\" class=\"CAMPOFORM\"><br />\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">[El archivo debe adjuntarse en formato PDF]</span>              \n");
      out.write("                                                        ");
if(!strDatosContrato[17].equals("-")){
      out.write("\n");
      out.write("                                                            <br />\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[17]);
      out.write("');\" />\n");
      out.write("                                                            <br />\n");
      out.write("                                                            <span class=\"MSGAVISOOBLG\" style=\"font-size: 9px;\">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </td>                                                     \n");
      out.write("                                                    <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                        <img src=\"Images/error.png\" id=\"imgMinutaContrato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr> \n");
      out.write("                                                    <td colspan=\"6\" class=\"CELDABOTONFORM\">                                                                  \n");
      out.write("                                                        <input type=\"submit\" value=\"Guardar\" id=\"btnGuardar\" class=\"BOTONFORM\">&nbsp;&nbsp;                                                 \n");
      out.write("                                                        ");
if((Integer.parseInt(strPlanPagos[0])) > 0){
      out.write("\n");
      out.write("                                                            &nbsp;&nbsp;<input type=\"button\" value=\"Plan de pagos\" id=\"btnPlanPagos\" class=\"BOTONFORM\" style=\"width: 90px;\">\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        ");
if((Integer.parseInt(strOTROSIs[0])) > 0){
      out.write("\n");
      out.write("                                                            &nbsp;&nbsp;<input type=\"button\" value=\"Ver OTROSIs\" id=\"btnOtrosi\" class=\"BOTONFORM\" style=\"width: 90px;\">\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                        &nbsp;&nbsp;<input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\">\n");
      out.write("                                                    </td>\n");
      out.write("                                                </tr>\n");
      out.write("                                                <tr><td colspan=\"6\" class=\"MSGAVISOOBLG\">Los campos marcados con (*) son obligatorios.</td></tr>\n");
      out.write("                                            </table>\n");
      out.write("                                        </form>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\n");
      out.write("                        ");
}
      out.write("                            \n");
      out.write("                    ");
}else{
      out.write("\n");
      out.write("                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLACONTENEDORA\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td class=\"TITULOFORM\">REGISTRO DE CONTRATO</td>\n");
      out.write("                            </tr>                \n");
      out.write("                            <tr>\n");
      out.write("                                <td>\n");
      out.write("                                    <form method=\"POST\" id=\"frmContrato\" name=\"frmContrato\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtForm\" id=\"txtForm\" value=\"frmContrato\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtAccion\" id=\"txtAccion\" value=\"V\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtIdEstado\" id=\"txtIdEstado\" value=\"");
      out.print(strDatosContrato[13]);
      out.write("\">\n");
      out.write("                                        <input type=\"hidden\" name=\"txtRutaActaInicio\" id=\"txtRutaActaInicio\" value=\"");
      out.print(strDatosContrato[15]);
      out.write("\" />\n");
      out.write("                                        <input type=\"hidden\" name=\"txtRutaActaFinalizacion\" id=\"txtRutaActaFinalizacion\" value=\"");
      out.print(strDatosContrato[16]);
      out.write("\" />\n");
      out.write("                                        <input type=\"hidden\" name=\"txtRutaMinutaContrato\" id=\"txtRutaMinutaContrato\" value=\"");
      out.print(strDatosContrato[17]);
      out.write("\" />\n");
      out.write("                                        <table cellspacing=\"0\" cellpadding=\"5\" border=\"0\" class=\"TABLAMAESTRO\">         \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos generales</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtFechaCreacion\" id=\"lblFechaCreacion\">Fecha de creación:</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtFechaCreacion\" id=\"txtFechaCreacion\" value=\"");
      out.print(strDatosContrato[14]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgFechaCreacion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                    \n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtEstado\" id=\"lblEstado\">Estado actual:</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtEstado\" id=\"txtEstado\" value=\"");
      out.print(strNomEstado[0]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgEstado\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                            \n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos básicos</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtConsecutivo\" id=\"lblConsecutivo\">Consecutivo:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtConsecutivo\" id=\"txtConsecutivo\" value=\"");
      out.print(strDatosContrato[0]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgConsecutivo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtContratista\" id=\"lblContratista\">Nombre del contratista:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                   <select id=\"txtContratista\" name=\"txtContratista\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsC.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresC.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>     \n");
      out.write("                                                <script type=\"text/javascript\">\n");
      out.write("                                                     $(\"#txtContratista option[value='");
      out.print(strDatosContrato[1]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                </script>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgContratista\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtTipoContrato\" id=\"lblTipoContrato\">Tipo de contrato:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <select id=\"txtTipoContrato\" name=\"txtTipoContrato\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsTC.size();i++){
      out.write("\n");
      out.write("                                                        ");
if(arrIdsTC.get(i).equals("PS")){
      out.write("\n");
      out.write("                                                                 <option value=\"");
      out.print(arrIdsTC.get(i));
      out.write("\" selected>");
      out.print(arrNombresTC.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}else{
      out.write("\n");
      out.write("                                                                 <option value=\"");
      out.print(arrIdsTC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresTC.get(i));
      out.write("</option>\n");
      out.write("                                                            ");
}
      out.write("                                                           \n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>          \n");
      out.write("                                                <script type=\"text/javascript\">\n");
      out.write("                                                     $(\"#txtTipoContrato option[value='");
      out.print(strDatosContrato[3]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                </script>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgTipoContrato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtInterventor\" id=\"lblInterventor\">Nombre del interventor:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                   <select id=\"txtInterventor\" name=\"txtInterventor\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsI.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsI.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresI.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </select>        \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtInterventor option[value='");
      out.print(strDatosContrato[2]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgInterventor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr> \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtGrupo\" id=\"lblGrupo\">Grupo asociado:</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <select id=\"txtGrupo\" name=\"txtGrupo\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsG.size();i++){
      out.write("\n");
      out.write("                                                            <option value=\"");
      out.print(arrIdsG.get(i));
      out.write('"');
      out.write('>');
      out.print(arrNombresG.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                </select>           \n");
      out.write("                                                <script type=\"text/javascript\">\n");
      out.write("                                                     $(\"#txtGrupo option[value='");
      out.print(strDatosContrato[4]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                </script>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgGrupo\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                                                                \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos de tiempos</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtFechaIni\" id=\"lblFechaIni\">Fecha de inicio:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtFechaIni\" id=\"txtFechaIni\" value=\"");
      out.print(strDatosContrato[5]);
      out.write("\" class=\"CAMPOFORM\" style=\"width: 180px;\" readonly disabled=\"disabled\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgFechaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtDuracion\" id=\"lblDuracion\">Duración (Días):</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtDuracion\" id=\"txtDuracion\" value=\"");
      out.print(strDatosContrato[7]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgDuracion\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                           \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtFechaFin\" id=\"lblFechaFin\">Fecha de fin:<br />(aaaa-mm-dd)</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtFechaFin\" id=\"txtFechaFin\" value=\"");
      out.print(strDatosContrato[6]);
      out.write("\" class=\"CAMPOFORM\" style=\"width: 180px;\" readonly disabled=\"disabled\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgFechaFin\" alt=\"Campo obligatorio\" class=\"IMGERROR\">                                                            \n");
      out.write("                                                </td>                                                 \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos financieros</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtValor\" id=\"lblValor\">Valor ($):</label></td>                                        \n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtValor\" id=\"txtValor\" value=\"");
      out.print(strDatosContrato[8]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">                                           \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgValor\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtCentroC\" id=\"lblCentroC\">Centro de costos:</label></td>\n");
      out.write("                                                <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                   <select id=\"txtCentroC\" name=\"txtCentroC\" class=\"CAMPOSELECT\" disabled=\"disabled\">\n");
      out.write("                                                        <option value=\"-1\">Seleccione una opción</option>\n");
      out.write("                                                        ");
for (int i=0;i<arrIdsCC.size();i++){
      out.write("\n");
      out.write("                                                        <option value=\"");
      out.print(arrIdsCC.get(i));
      out.write('"');
      out.write('>');
      out.print(arrIdsCC.get(i));
      out.write("&nbsp;-&nbsp;");
      out.print(arrNombresCC.get(i));
      out.write("</option>\n");
      out.write("                                                        ");
}
      out.write("\n");
      out.write("                                                    </select>     \n");
      out.write("                                                    <script type=\"text/javascript\">\n");
      out.write("                                                         $(\"#txtCentroC option[value='");
      out.print(strDatosContrato[9]);
      out.write("']\").attr('selected', 'selected');\n");
      out.write("                                                    </script>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgCentroC\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                                \n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Otros datos</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtConsecutivoL\" id=\"lblConsecutivoL\">Consecutivo laborales:</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtConsecutivoL\" id=\"txtConsecutivoL\" value=\"");
      out.print(strDatosContrato[10]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgConsecutivoL\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                 <td class=\"LABELFORM\"><label for=\"txtNumCDP\" id=\"lblNumCDP\">Nro. de CDP:</label></td>\n");
      out.write("                                                <td class=\"CELDARADIOFORM\">\n");
      out.write("                                                    <input type=\"text\" name=\"txtNumCDP\" id=\"txtNumCDP\" value=\"");
      out.print(strDatosContrato[11]);
      out.write("\" class=\"CAMPOFORM\" readonly disabled=\"disabled\">\n");
      out.write("                                                </td>                               \n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgNumCDP\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>                                               \n");
      out.write("                                            </tr>                                            \n");
      out.write("                                            <tr>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtObjeto\" id=\"lblObjeto\">Objeto:</label></td>                                        \n");
      out.write("                                                <td colspan=\"4\" class=\"CELDACAMPOFORM\">                                               \n");
      out.write("                                                    <div id=\"txtObjeto\" class=\"DIVEDITABLE\" contenteditable=\"false\" style=\"width: 650px;height: 100px;\">                                                        \n");
      out.write("                                                        ");
      out.print(strDatosContrato[12]);
      out.write("\n");
      out.write("                                                    </div>                                                                \n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgObjeto\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td colspan=\"6\" class=\"SUBTITULOFORM\">Datos adjuntos</td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>            \n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtActaInicio\" id=\"lblActaInicio\">Acta de inicio:</label></td>\n");
      out.write("                                                ");
if(strDatosContrato[15].equals("-")){
      out.write("                                                                                            \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\">NOTA: No se tiene Acta de Inicio adjunta .</td>           \n");
      out.write("                                                    </td>                                                     \n");
      out.write("                                                 ");
}else{
      out.write("\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[15]);
      out.write("');\" />                                                            \n");
      out.write("                                                    </td>\n");
      out.write("                                                 ");
}
      out.write("\n");
      out.write("                                                 <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgActaInicio\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtActaFin\" id=\"lblActaInicio\">Acta de finalización:</label></td>            \n");
      out.write("                                                ");
if(strDatosContrato[16].equals("-")){
      out.write(" \n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\">NOTA: No se tiene Acta de Finalización adjunta .</td>        \n");
      out.write("                                                    </td>                          \n");
      out.write("                                                ");
}else{
      out.write("\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[16]);
      out.write("');\" />                                                            \n");
      out.write("                                                    </td>\n");
      out.write("                                                 ");
}
      out.write("\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgActaFin\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr>                                                                    \n");
      out.write("                                                <td class=\"LABELFORM\"><label for=\"txtMinutaContrato\" id=\"lblActaInicio\">Minuta del contrato:</label></td>                       \n");
      out.write("                                                ");
if(strDatosContrato[17].equals("-")){
      out.write("\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                        <span class=\"MSGAVISOOBLG\">NOTA: No se tiene Minuta del Contrato adjunta .</td>   \n");
      out.write("                                                    </td>                         \n");
      out.write("                                                ");
}else{
      out.write("\n");
      out.write("                                                    <td class=\"CELDACAMPOFORM\">\n");
      out.write("                                                            <input type=\"button\" value=\"Descargar\" id=\"btnDescargar\" class=\"BOTONFORM\" onclick=\"descargarArchivo('");
      out.print(strDatosContrato[17]);
      out.write("');\" />                                                            \n");
      out.write("                                                    </td>\n");
      out.write("                                                ");
}
      out.write("\n");
      out.write("                                                <td class=\"CELDAIMGERROR\">\n");
      out.write("                                                    <img src=\"Images/error.png\" id=\"imgMinutaContrato\" alt=\"Campo obligatorio\" class=\"IMGERROR\">\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" class=\"CELDABOTONFORM\">\n");
      out.write("                                                ");
if((Integer.parseInt(strPlanPagos[0])) > 0){
      out.write("\n");
      out.write("                                                    <input type=\"button\" value=\"Plan de pagos\" id=\"btnPlanPagos\" class=\"BOTONFORM\" style=\"width: 90px;\">&nbsp;&nbsp;\n");
      out.write("                                                ");
}
      out.write("\n");
      out.write("                                                ");
if((Integer.parseInt(strOTROSIs[0])) > 0){
      out.write("\n");
      out.write("                                                     <input type=\"button\" value=\"Ver OTROSIs\" id=\"btnOtrosi\" class=\"BOTONFORM\" style=\"width: 90px;\">&nbsp;&nbsp;\n");
      out.write("                                                ");
}
      out.write("\n");
      out.write("                                                <input type=\"button\" value=\"Salir\" class=\"BOTONFORM\" onclick=\"javascript:window.close();\"></td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            <tr><td colspan=\"6\" style=\"height: 0px;\"></td></tr>\n");
      out.write("                                        </table>\n");
      out.write("                                    </form>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                        </table\n");
      out.write("                    ");
}
      out.write("                        \n");
      out.write("                ");
 }
      out.write("      \n");
      out.write("                <div id=\"dMensaje\">            \n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <script language=\"javascript\" type=\"text/javascript\">\n");
      out.write("                cargarCalendarios();\n");
      out.write("            </script>\n");
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
