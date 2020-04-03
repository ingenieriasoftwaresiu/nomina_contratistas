<%-- 
    Document   : contrato
    Created on : 27-nov-2013, 14:24:34
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="comprobarSesion.jsp"></jsp:include>
<!DOCTYPE html>
<% 
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
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <link rel="stylesheet" type="text/css" href="Styles/calendar-system.css" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" src="Scripts/JSCalendar.js"></script>
        <script type="text/javascript" src="Scripts/JSCalendar-es.js"></script>
        <script type="text/javascript" src="Scripts/JSCalendar-setup.js"></script>        
        <script type="text/javascript" src="Scripts/jquery.filestyle.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/contrato.js"></script>
        <title>Sistema de Gestión de Pagos a Contratistas: Contratista</title>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />  
       </header>
       <section>
            <div align="center">
                <br>
                    <%if (strAccion.equals("C")){%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class="TITULOFORM">NUEVO REGISTRO DE CONTRATO</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmContrato" name="frmContrato" enctype="multipart/form-data" action="RegistroContrato" onsubmit="return validarContrato();">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmContrato" />
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="C" />
                                        <input type="hidden" name="txtIdEstado" id="txtIdEstado" value="<%=strIdEstado%>" />
                                        <input type="hidden" name="txtRutaActaInicio" id="txtRutaActaInicio" value="-" />
                                        <input type="hidden" name="txtRutaActaFinalizacion" id="txtRutaActaFinalizacion" value="-" />
                                        <input type="hidden" name="txtRutaMinutaContrato" id="txtRutaMinutaContrato" value="-" />                                        
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">         
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos generales</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtFechaCreacion" id="lblFechaCreacion">Fecha de creación:</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtFechaCreacion" id="txtFechaCreacion" class="CAMPOFORM" readOnly>
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgFechaCreacion" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                    
                                                <td class="LABELFORM"><label for="txtEstado" id="lblEstado">Estado actual:</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtEstado" id="txtEstado" value="<%=strNomEstado[0]%>" class="CAMPOFORM" readonly>
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgEstado" alt="Campo obligatorio" class="IMGERROR">                                                            
                                                </td>                                                
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos básicos</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtConsecutivo" id="lblConsecutivo">* Consecutivo:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtConsecutivo" id="txtConsecutivo" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgConsecutivo" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtContratista" id="lblContratista">* Nombre del contratista:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                   <select id="txtContratista" name="txtContratista" class="CAMPOSELECT">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsC.size();i++){%>
                                                            <option value="<%=arrIdsC.get(i)%>"><%=arrNombresC.get(i)%></option>
                                                        <%}%>
                                                    </select>      
                                                    <br />
                                                    <img src="Images/Agregar.png" alt="Agregar Contratista" title="Agregar Contratista" id="btnAgregarContratista" style="width: 15px;height: 15px;padding-top: 5px;" />&nbsp;
                                                    <img src="Images/Ejecutar.png" alt="Refrescar" title="Refrescar" class="IMGREFRESH" style="width: 15px;height: 15px;padding-top: 5px;" />&nbsp;
                                                    <img src="Images/lupa.gif" alt="Buscar Contratista" title="Buscar Contratista" id="btnBuscarContratista" style="width: 20px;height: 20px;padding-top: 5px;" />
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgContratista" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtTipoContrato" id="lblTipoContrato">* Tipo de contrato:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <select id="txtTipoContrato" name="txtTipoContrato" class="CAMPOSELECT">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsTC.size();i++){%>
                                                        <%if(arrIdsTC.get(i).equals("PS")){%>
                                                                 <option value="<%=arrIdsTC.get(i)%>" selected><%=arrNombresTC.get(i)%></option>
                                                            <%}else{%>
                                                                 <option value="<%=arrIdsTC.get(i)%>"><%=arrNombresTC.get(i)%></option>
                                                            <%}%>                                                           
                                                        <%}%>
                                                </select>                                       
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgTipoContrato" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtInterventor" id="lblInterventor">* Nombre del interventor:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                   <select id="txtInterventor" name="txtInterventor" class="CAMPOSELECT">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsI.size();i++){%>
                                                            <option value="<%=arrIdsI.get(i)%>"><%=arrNombresI.get(i)%></option>
                                                        <%}%>
                                                    </select>             
                                                    <br />                                      
                                                    <img src="Images/Agregar.png" alt="Agregar Interventor" title="Agregar Interventor" id="btnAgregarInterventor" style="width: 15px;height: 15px;padding-top: 5px;" />&nbsp;
                                                    <img src="Images/Ejecutar.png" alt="Refrescar" title="Refrescar" class="IMGREFRESH" style="width: 15px;height: 15px;padding-top: 5px;" />&nbsp;
                                                    <img src="Images/lupa.gif" alt="Buscar Interventor" title="Buscar Interventor" id="btnBuscarInterventor" style="width: 20px;height: 20px;padding-top: 5px;" />
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgInterventor" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                
                                            </tr> 
                                            <tr>
                                                <td class="LABELFORM"><label for="txtGrupo" id="lblGrupo">* Grupo asociado:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <select id="txtGrupo" name="txtGrupo" class="CAMPOSELECT">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsG.size();i++){%>
                                                            <option value="<%=arrIdsG.get(i)%>"><%=arrNombresG.get(i)%></option>
                                                        <%}%>
                                                </select>                                       
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgGrupo" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                                                                
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos de tiempos</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtFechaIni" id="lblFechaIni">* Fecha de inicio:<br />(aaaa-mm-dd)</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtFechaIni" id="txtFechaIni" class="CAMPOFORM" style="width: 180px;" readOnly>&nbsp;<img src="Images/Calendario.JPG" class="IMGCALENDAR" id="imgFechaIni">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgFechaInicio" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtDuracion" id="lblDuracion">* Duración (Días):</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtDuracion" id="txtDuracion" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgDuracion" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                           
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtFechaFin" id="lblFechaFin">Fecha de fin:<br />(aaaa-mm-dd)</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtFechaFin" id="txtFechaFin" class="CAMPOFORM" style="width: 180px;" readonly>&nbsp;<img src="Images/Calendario.JPG" class="IMGCALENDAR" id="imgFechaFin">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/Refresh.png" id="imgRefresh" alt="Campo obligatorio" style="width: 30px;height: 30px;vertical-align: middle;text-align: left;" onclick="refreshFechaFin();">                                                            
                                                </td>                                                 
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos financieros</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtValor" id="lblValor">* Valor ($):</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtValor" id="txtValor" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgValor" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtCentroC" id="lblCentroC">* Centro de costos:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                   <select id="txtCentroC" name="txtCentroC" class="CAMPOSELECT">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsCC.size();i++){%>
                                                        <option value="<%=arrIdsCC.get(i)%>"><%=arrIdsCC.get(i)%>&nbsp;-&nbsp;<%=arrNombresCC.get(i)%></option>
                                                        <%}%>
                                                </select>                         
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgCentroC" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Otros datos</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtConsecutivoL" id="lblConsecutivoL">Consecutivo laborales:</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtConsecutivoL" id="txtConsecutivoL" class="CAMPOFORM">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgConsecutivoL" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtNumCDP" id="lblNumCDP">Nro. de CDP:</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtNumCDP" id="txtNumCDP" class="CAMPOFORM">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumCDP" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                
                                            </tr>                                    
                                            <tr>
                                                <td class="LABELFORM"><label for="txtObjeto" id="lblObjeto">* Objeto:</label></td>                                        
                                                <td colspan="4" class="CELDACAMPOFORM">
                                                    <input type="hidden" name="txtObjetoC" id="txtObjetoC" />
                                                    <div id="txtObjeto" name="txtObjeto" class="DIVEDITABLE" contenteditable="true" style="width: 650px;height: 100px;">                                                        
                                                    </div>                                        
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgObjeto" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos adjuntos</td>
                                            </tr>
                                            <tr>                                                                    
                                                <td class="LABELFORM"><label for="txtActaInicio" id="lblActaInicio">Acta de inicio:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="file" name="txtActaInicio" id="txtActaInicio" class="CAMPOFORM"><br />
                                                    <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>              
                                                </td>                                                      
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgActaInicio" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtActaFin" id="lblActaInicio">Acta de finalización:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="file" name="txtActaFin" id="txtActaFin" class="CAMPOFORM"><br />
                                                    <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>              
                                                </td>                                                      
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgActaFin" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>                                                                    
                                                <td class="LABELFORM"><label for="txtMinutaContrato" id="lblActaInicio">Minuta del contrato:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="file" name="txtMinutaContrato" id="txtMinutaContrato" class="CAMPOFORM"><br />
                                                    <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>              
                                                </td>                                                      
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgMinutaContrato" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="6" class="CELDABOTONFORM">
                                                    <input type="submit" value="Guardar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;
                                                    <input type="button" value="Limpiar" id="btnLimpiar" class="BOTONFORM"> &nbsp;&nbsp;
                                                    <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="6" class="MSGAVISOOBLG">Los campos marcados con (*) son obligatorios.</td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    <%}else{%>
                    <input type="hidden" name="txtTipoUsuario" id="txtTipoUsuario" value="<%=strTipoUsuario%>">
                    <%if(strTipoUsuario.equals("A")){%>
                        <%if(strDatosContrato[13].equals("CR")){%>
                            <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                                <tr>
                                    <td class="TITULOFORM">REGISTRO DE CONTRATO</td>
                                </tr>                
                                <tr>
                                    <td>
                                        <form method="POST" id="frmContrato" name="frmContrato" enctype="multipart/form-data" action="RegistroContrato" onsubmit="return validarContrato();">
                                            <input type="hidden" name="txtForm" id="txtForm" value="frmContrato">
                                            <input type="hidden" name="txtAccion" id="txtAccion" value="V">
                                            <input type="hidden" name="txtIdEstado" id="txtIdEstado" value="<%=strDatosContrato[13]%>">
                                            <input type="hidden" name="txtRutaActaInicio" id="txtRutaActaInicio" value="<%=strDatosContrato[15]%>" />
                                            <input type="hidden" name="txtRutaActaFinalizacion" id="txtRutaActaFinalizacion" value="<%=strDatosContrato[16]%>" />
                                            <input type="hidden" name="txtRutaMinutaContrato" id="txtRutaMinutaContrato" value="<%=strDatosContrato[17]%>" />
                                            <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">         
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos generales</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtFechaCreacion" id="lblFechaCreacion">Fecha de creación:</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtFechaCreacion" id="txtFechaCreacion" value="<%=strDatosContrato[14]%>" class="CAMPOFORM" readonly>
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFechaCreacion" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                    
                                                    <td class="LABELFORM"><label for="txtEstado" id="lblEstado">Estado actual:</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtEstado" id="txtEstado" value="<%=strNomEstado[0]%>" class="CAMPOFORM" readonly>
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgEstado" alt="Campo obligatorio" class="IMGERROR">                                                            
                                                    </td>                                                
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos básicos</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtConsecutivo" id="lblConsecutivo">* Consecutivo:</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtConsecutivo" id="txtConsecutivo" value="<%=strDatosContrato[0]%>" class="CAMPOFORM" readonly>                                           
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgConsecutivo" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtContratista" id="lblContratista">* Nombre del contratista:</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                       <select id="txtContratista" name="txtContratista" class="CAMPOSELECT" disabled="disabled">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsC.size();i++){%>
                                                                <option value="<%=arrIdsC.get(i)%>"><%=arrNombresC.get(i)%></option>
                                                            <%}%>
                                                    </select>     
                                                    <script type="text/javascript">
                                                         $("#txtContratista option[value='<%=strDatosContrato[1]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgContratista" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtTipoContrato" id="lblTipoContrato">* Tipo de contrato:</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <select id="txtTipoContrato" name="txtTipoContrato" class="CAMPOSELECT" disabled="disabled">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsTC.size();i++){%>
                                                            <%if(arrIdsTC.get(i).equals("PS")){%>
                                                                     <option value="<%=arrIdsTC.get(i)%>" selected><%=arrNombresTC.get(i)%></option>
                                                                <%}else{%>
                                                                     <option value="<%=arrIdsTC.get(i)%>"><%=arrNombresTC.get(i)%></option>
                                                                <%}%>                                                           
                                                            <%}%>
                                                    </select>          
                                                    <script type="text/javascript">
                                                         $("#txtTipoContrato option[value='<%=strDatosContrato[3]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgTipoContrato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtInterventor" id="lblInterventor">* Nombre del interventor:</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                       <select id="txtInterventor" name="txtInterventor" class="CAMPOSELECT">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsI.size();i++){%>
                                                                <option value="<%=arrIdsI.get(i)%>"><%=arrNombresI.get(i)%></option>
                                                            <%}%>
                                                    </select>        
                                                    <script type="text/javascript">
                                                         $("#txtInterventor option[value='<%=strDatosContrato[2]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgInterventor" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                
                                                </tr> 
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtGrupo" id="lblGrupo">* Grupo asociado:</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <select id="txtGrupo" name="txtGrupo" class="CAMPOSELECT">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsG.size();i++){%>
                                                                <option value="<%=arrIdsG.get(i)%>"><%=arrNombresG.get(i)%></option>
                                                            <%}%>
                                                    </select>           
                                                    <script type="text/javascript">
                                                         $("#txtGrupo option[value='<%=strDatosContrato[4]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgGrupo" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                                                                
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos de tiempos</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtFechaIni" id="lblFechaIni">* Fecha de inicio:<br />(aaaa-mm-dd)</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtFechaIni" id="txtFechaIni" value="<%=strDatosContrato[5]%>" class="CAMPOFORM" style="width: 180px;" readOnly disabled="disabled">
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFechaInicio" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtDuracion" id="lblDuracion">* Duración (Días):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtDuracion" id="txtDuracion" value="<%=strDatosContrato[7]%>" class="CAMPOFORM" readOnly disabled="disabled">                                           
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgDuracion" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtFechaFin" id="lblFechaFin">Fecha de fin:<br />(aaaa-mm-dd)</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtFechaFin" id="txtFechaFin" value="<%=strDatosContrato[6]%>" class="CAMPOFORM" style="width: 180px;" readOnly disabled="disabled">
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">                                                                                         
                                                    </td>                                                 
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos financieros</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtValor" id="lblValor">* Valor ($):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValor" id="txtValor" value="<%=strDatosContrato[8]%>" class="CAMPOFORM">                                           
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValor" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtCentroC" id="lblCentroC">* Centro de costos:</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                       <select id="txtCentroC" name="txtCentroC" class="CAMPOSELECT">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsCC.size();i++){%>
                                                            <option value="<%=arrIdsCC.get(i)%>"><%=arrIdsCC.get(i)%>&nbsp;-&nbsp;<%=arrNombresCC.get(i)%></option>
                                                            <%}%>
                                                    </select>     
                                                    <script type="text/javascript">
                                                         $("#txtCentroC option[value='<%=strDatosContrato[9]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgCentroC" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Otros datos</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtConsecutivoL" id="lblConsecutivoL">Consecutivo laborales:</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtConsecutivoL" id="txtConsecutivoL" value="<%=strDatosContrato[10]%>" class="CAMPOFORM">
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgConsecutivoL" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtNumCDP" id="lblNumCDP">Nro. de CDP:</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtNumCDP" id="txtNumCDP" value="<%=strDatosContrato[11]%>" class="CAMPOFORM">
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgNumCDP" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                
                                                </tr>                                      
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtObjeto" id="lblObjeto">* Objeto:</label></td>                                        
                                                    <td colspan="4" class="CELDACAMPOFORM">
                                                        <input type="hidden" name="txtObjetoC" id="txtObjetoC" />
                                                        <div id="txtObjeto" class="DIVEDITABLE" contenteditable="true" style="width: 650px;height: 100px;">                    
                                                            <%=strDatosContrato[12]%>
                                                        </div>                                                                                   
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgObjeto" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos adjuntos</td>
                                                </tr>
                                                <tr>            
                                                    <td class="LABELFORM" style="border-bottom: 1px solid #116043;"><label for="txtActaInicio" id="lblActaInicio">Acta de inicio:</label></td>
                                                    <td class="CELDACAMPOFORM" style="border-bottom: 1px solid #116043;">
                                                       <input type="file" name="txtActaInicio" id="txtActaInicio" class="CAMPOFORM"><br />
                                                       <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>            
                                                       <%if(!strDatosContrato[15].equals("-")){%>   
                                                        <br />
                                                        <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[15]%>');" />
                                                        <br />
                                                        <span class="MSGAVISOOBLG" style="font-size: 9px;">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>                                                        
                                                       <%}%>
                                                   </td>                                                    
                                                     <td class="CELDAIMGERROR" style="border-bottom: 1px solid #116043;">
                                                        <img src="Images/error.png" id="imgActaInicio" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM" style="border-bottom: 1px solid #116043;"><label for="txtActaFin" id="lblActaInicio">Acta de finalización:</label></td>         
                                                    <td class="CELDACAMPOFORM" style="border-bottom: 1px solid #116043;">
                                                        <input type="file" name="txtActaFin" id="txtActaFin" class="CAMPOFORM"><br />
                                                        <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>              
                                                        <%if(!strDatosContrato[16].equals("-")){%> 
                                                            <br />
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[16]%>');" />
                                                            <br />
                                                            <span class="MSGAVISOOBLG" style="font-size: 9px;">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>
                                                        <%}%>
                                                    </td>                                                    
                                                    <td class="CELDAIMGERROR" style="border-bottom: 1px solid #116043;">
                                                        <img src="Images/error.png" id="imgActaFin" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>
                                                <tr>                                                                    
                                                    <td class="LABELFORM"><label for="txtMinutaContrato" id="lblActaInicio">Minuta del contrato:</label></td>                       
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="file" name="txtMinutaContrato" id="txtMinutaContrato" class="CAMPOFORM"><br />
                                                        <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>              
                                                        <%if(!strDatosContrato[17].equals("-")){%>
                                                            <br />
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[17]%>');" />
                                                            <br />
                                                            <span class="MSGAVISOOBLG" style="font-size: 9px;">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>
                                                        <%}%>
                                                    </td>                                                     
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgMinutaContrato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="6" class="CELDABOTONFORM">
                                                        <input type="submit" value="Guardar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;                                                                                                            
                                                        <%if((Integer.parseInt(strPlanPagos[0])) > 0){%>
                                                            &nbsp;&nbsp;<input type="button" value="Plan de pagos" id="btnPlanPagos" class="BOTONFORM" style="width: 90px;">
                                                        <%}%>
                                                        <%if((Integer.parseInt(strOTROSIs[0])) > 0){%>
                                                            &nbsp;&nbsp;<input type="button" value="Ver OTROSIs" id="btnOtrosi" class="BOTONFORM" style="width: 90px;">
                                                        <%}%>                                                        
                                                        &nbsp;&nbsp;<input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();">
                                                    </td>
                                                </tr>
                                                <tr><td colspan="6" class="MSGAVISOOBLG">Los campos marcados con (*) son obligatorios.</td></tr>
                                            </table>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        <%}else{%>
                            <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                                <tr>
                                    <td class="TITULOFORM">REGISTRO DE CONTRATO</td>
                                </tr>                
                                <tr>
                                    <td>
                                        <form method="POST" id="frmContrato" name="frmContrato" enctype="multipart/form-data" action="RegistroContrato" onsubmit="return validarContrato();">
                                            <input type="hidden" name="txtForm" id="txtForm" value="frmContrato">
                                            <input type="hidden" name="txtAccion" id="txtAccion" value="V">
                                            <input type="hidden" name="txtIdEstado" id="txtIdEstado" value="<%=strDatosContrato[13]%>">
                                            <input type="hidden" name="txtRutaActaInicio" id="txtRutaActaInicio" value="<%=strDatosContrato[15]%>" />
                                            <input type="hidden" name="txtRutaActaFinalizacion" id="txtRutaActaFinalizacion" value="<%=strDatosContrato[16]%>" />
                                            <input type="hidden" name="txtRutaMinutaContrato" id="txtRutaMinutaContrato" value="<%=strDatosContrato[17]%>" />
                                            <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">         
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos generales</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtFechaCreacion" id="lblFechaCreacion">Fecha de creación:</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtFechaCreacion" id="txtFechaCreacion" value="<%=strDatosContrato[14]%>" class="CAMPOFORM" readonly>
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFechaCreacion" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                    
                                                    <td class="LABELFORM"><label for="txtEstado" id="lblEstado">Estado actual:</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtEstado" id="txtEstado" value="<%=strNomEstado[0]%>" class="CAMPOFORM" readonly>
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgEstado" alt="Campo obligatorio" class="IMGERROR">                                                            
                                                    </td>                                                
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos básicos</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtConsecutivo" id="lblConsecutivo">Consecutivo:</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtConsecutivo" id="txtConsecutivo" value="<%=strDatosContrato[0]%>" class="CAMPOFORM" readonly>                                           
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgConsecutivo" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtContratista" id="lblContratista">Nombre del contratista:</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                       <select id="txtContratista" name="txtContratista" class="CAMPOSELECT" disabled="disabled">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsC.size();i++){%>
                                                                <option value="<%=arrIdsC.get(i)%>"><%=arrNombresC.get(i)%></option>
                                                            <%}%>
                                                    </select>     
                                                    <script type="text/javascript">
                                                         $("#txtContratista option[value='<%=strDatosContrato[1]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgContratista" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtTipoContrato" id="lblTipoContrato">Tipo de contrato:</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <select id="txtTipoContrato" name="txtTipoContrato" class="CAMPOSELECT" disabled="disabled">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsTC.size();i++){%>
                                                            <%if(arrIdsTC.get(i).equals("PS")){%>
                                                                     <option value="<%=arrIdsTC.get(i)%>" selected><%=arrNombresTC.get(i)%></option>
                                                                <%}else{%>
                                                                     <option value="<%=arrIdsTC.get(i)%>"><%=arrNombresTC.get(i)%></option>
                                                                <%}%>                                                           
                                                            <%}%>
                                                    </select>          
                                                    <script type="text/javascript">
                                                         $("#txtTipoContrato option[value='<%=strDatosContrato[3]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgTipoContrato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtInterventor" id="lblInterventor">Nombre del interventor:</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                       <select id="txtInterventor" name="txtInterventor" class="CAMPOSELECT" disabled="disabled">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsI.size();i++){%>
                                                                <option value="<%=arrIdsI.get(i)%>"><%=arrNombresI.get(i)%></option>
                                                            <%}%>
                                                    </select>        
                                                    <script type="text/javascript">
                                                         $("#txtInterventor option[value='<%=strDatosContrato[2]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgInterventor" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                
                                                </tr> 
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtGrupo" id="lblGrupo">Grupo asociado:</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <select id="txtGrupo" name="txtGrupo" class="CAMPOSELECT" disabled="disabled">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsG.size();i++){%>
                                                                <option value="<%=arrIdsG.get(i)%>"><%=arrNombresG.get(i)%></option>
                                                            <%}%>
                                                    </select>           
                                                    <script type="text/javascript">
                                                         $("#txtGrupo option[value='<%=strDatosContrato[4]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgGrupo" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                                                                
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos de tiempos</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtFechaIni" id="lblFechaIni">Fecha de inicio:<br />(aaaa-mm-dd)</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtFechaIni" id="txtFechaIni" value="<%=strDatosContrato[5]%>" class="CAMPOFORM" style="width: 180px;" readOnly disabled="disabled">
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFechaInicio" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtDuracion" id="lblDuracion">Duración (Días):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtDuracion" id="txtDuracion" value="<%=strDatosContrato[7]%>" class="CAMPOFORM" readOnly disabled="disabled">                                           
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgDuracion" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtFechaFin" id="lblFechaFin">Fecha de fin:<br />(aaaa-mm-dd)</label></td>
                                                    <td class="CELDARADIOFORM">
                                                        <input type="text" name="txtFechaFin" id="txtFechaFin" value="<%=strDatosContrato[6]%>" class="CAMPOFORM" style="width: 180px;" readonly disabled="disabled">
                                                    </td>                               
                                                    <td class="CELDAIMGERROR">                                                                                                                   
                                                    </td>                                                 
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos financieros</td>
                                                </tr>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtValor" id="lblValor">Valor ($):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValor" id="txtValor" value="<%=strDatosContrato[8]%>" class="CAMPOFORM" readonly disabled="disabled">                                           
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValor" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtCentroC" id="lblCentroC">Centro de costos:</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                       <select id="txtCentroC" name="txtCentroC" class="CAMPOSELECT" disabled="disabled">
                                                            <option value="-1">Seleccione una opción</option>
                                                            <%for (int i=0;i<arrIdsCC.size();i++){%>
                                                            <option value="<%=arrIdsCC.get(i)%>"><%=arrIdsCC.get(i)%>&nbsp;-&nbsp;<%=arrNombresCC.get(i)%></option>
                                                            <%}%>
                                                    </select>     
                                                    <script type="text/javascript">
                                                         $("#txtCentroC option[value='<%=strDatosContrato[9]%>']").attr('selected', 'selected');
                                                    </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgCentroC" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                                
                                                </tr>
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Otros datos</td>
                                                </tr>                                                
                                                <%if(strDatosContrato[13].equals("EJ")){%>
                                                    <tr>
                                                        <td class="LABELFORM"><label for="txtConsecutivoL" id="lblConsecutivoL">Consecutivo laborales:</label></td>
                                                        <td class="CELDARADIOFORM">
                                                            <input type="text" name="txtConsecutivoL" id="txtConsecutivoL" value="<%=strDatosContrato[10]%>" class="CAMPOFORM">
                                                        </td>                               
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgConsecutivoL" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                        <td class="LABELFORM"><label for="txtNumCDP" id="lblNumCDP">Nro. de CDP:</label></td>
                                                        <td class="CELDARADIOFORM">
                                                            <input type="text" name="txtNumCDP" id="txtNumCDP" value="<%=strDatosContrato[11]%>" class="CAMPOFORM">
                                                        </td>                               
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgNumCDP" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>                                                
                                                    </tr>                                      
                                                    <tr>
                                                        <td class="LABELFORM"><label for="txtObjeto" id="lblObjeto">* Objeto:</label></td>                                        
                                                        <td colspan="4" class="CELDACAMPOFORM">
                                                            <input type="hidden" name="txtObjetoC" id="txtObjetoC" />
                                                            <div id="txtObjeto" class="DIVEDITABLE" contenteditable="true" style="width: 650px;height: 100px;">                                                        
                                                                    <%=strDatosContrato[12]%>
                                                            </div>                                                                         
                                                        </td>
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgObjeto" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                    </tr>
                                                <%}else{%>
                                                    <tr>
                                                        <td class="LABELFORM"><label for="txtConsecutivoL" id="lblConsecutivoL">Consecutivo laborales:</label></td>
                                                        <td class="CELDARADIOFORM">
                                                            <input type="text" name="txtConsecutivoL" id="txtConsecutivoL" value="<%=strDatosContrato[10]%>" class="CAMPOFORM" readonly disabled="disabled">
                                                        </td>                               
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgConsecutivoL" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                         <td class="LABELFORM"><label for="txtNumCDP" id="lblNumCDP">Nro. de CDP:</label></td>
                                                        <td class="CELDARADIOFORM">
                                                            <input type="text" name="txtNumCDP" id="txtNumCDP" value="<%=strDatosContrato[11]%>" class="CAMPOFORM" readonly disabled="disabled">
                                                        </td>                               
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgNumCDP" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="LABELFORM"><label for="txtObjeto" id="lblObjeto">Objeto:</label></td>                                        
                                                        <td colspan="4" class="CELDACAMPOFORM">
                                                            <input type="hidden" name="txtObjetoC" id="txtObjetoC" />
                                                            <div id="txtObjeto" class="DIVEDITABLE" contenteditable="false" style="width: 650px;height: 100px;">                                                        
                                                                <%=strDatosContrato[12]%>
                                                            </div>                                                   
                                                        </td>
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgObjeto" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                    </tr>
                                                <%}%>                              
                                                <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                <tr>
                                                    <td colspan="6" class="SUBTITULOFORM">Datos adjuntos</td>
                                                </tr>
                                                <tr>            
                                                    <td class="LABELFORM" style="border-bottom: 1px solid #116043;"><label for="txtActaInicio" id="lblActaInicio">Acta de inicio:</label></td>
                                                    <td class="CELDACAMPOFORM" style="border-bottom: 1px solid #116043;">
                                                       <input type="file" name="txtActaInicio" id="txtActaInicio" class="CAMPOFORM"><br />
                                                       <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>            
                                                       <%if(!strDatosContrato[15].equals("-")){%>   
                                                        <br />
                                                        <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[15]%>');" />
                                                        <br />
                                                        <span class="MSGAVISOOBLG" style="font-size: 9px;">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>                                                        
                                                       <%}%>
                                                   </td>                                                    
                                                     <td class="CELDAIMGERROR" style="border-bottom: 1px solid #116043;">
                                                        <img src="Images/error.png" id="imgActaInicio" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM" style="border-bottom: 1px solid #116043;"><label for="txtActaFin" id="lblActaInicio">Acta de finalización:</label></td>         
                                                    <td class="CELDACAMPOFORM" style="border-bottom: 1px solid #116043;">
                                                        <input type="file" name="txtActaFin" id="txtActaFin" class="CAMPOFORM"><br />
                                                        <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>              
                                                        <%if(!strDatosContrato[16].equals("-")){%> 
                                                            <br />
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[16]%>');" />
                                                            <br />
                                                            <span class="MSGAVISOOBLG" style="font-size: 9px;">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>
                                                        <%}%>
                                                    </td>                                                    
                                                    <td class="CELDAIMGERROR" style="border-bottom: 1px solid #116043;">
                                                        <img src="Images/error.png" id="imgActaFin" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>
                                                <tr>                                                                    
                                                    <td class="LABELFORM"><label for="txtMinutaContrato" id="lblActaInicio">Minuta del contrato:</label></td>                       
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="file" name="txtMinutaContrato" id="txtMinutaContrato" class="CAMPOFORM"><br />
                                                        <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>              
                                                        <%if(!strDatosContrato[17].equals("-")){%>
                                                            <br />
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[17]%>');" />
                                                            <br />
                                                            <span class="MSGAVISOOBLG" style="font-size: 9px;">Nota: Para reemplazar el archivo, adjunte uno nuevo.</span>
                                                        <%}%>
                                                    </td>                                                     
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgMinutaContrato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>
                                                <tr> 
                                                    <td colspan="6" class="CELDABOTONFORM">                                                                  
                                                        <input type="submit" value="Guardar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;                                                 
                                                        <%if((Integer.parseInt(strPlanPagos[0])) > 0){%>
                                                            &nbsp;&nbsp;<input type="button" value="Plan de pagos" id="btnPlanPagos" class="BOTONFORM" style="width: 90px;">
                                                        <%}%>
                                                        <%if((Integer.parseInt(strOTROSIs[0])) > 0){%>
                                                            &nbsp;&nbsp;<input type="button" value="Ver OTROSIs" id="btnOtrosi" class="BOTONFORM" style="width: 90px;">
                                                        <%}%>
                                                        &nbsp;&nbsp;<input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();">
                                                    </td>
                                                </tr>
                                                <tr><td colspan="6" class="MSGAVISOOBLG">Los campos marcados con (*) son obligatorios.</td></tr>
                                            </table>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        <%}%>                            
                    <%}else{%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class="TITULOFORM">REGISTRO DE CONTRATO</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmContrato" name="frmContrato">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmContrato">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="V">
                                        <input type="hidden" name="txtIdEstado" id="txtIdEstado" value="<%=strDatosContrato[13]%>">
                                        <input type="hidden" name="txtRutaActaInicio" id="txtRutaActaInicio" value="<%=strDatosContrato[15]%>" />
                                        <input type="hidden" name="txtRutaActaFinalizacion" id="txtRutaActaFinalizacion" value="<%=strDatosContrato[16]%>" />
                                        <input type="hidden" name="txtRutaMinutaContrato" id="txtRutaMinutaContrato" value="<%=strDatosContrato[17]%>" />
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">         
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos generales</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtFechaCreacion" id="lblFechaCreacion">Fecha de creación:</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtFechaCreacion" id="txtFechaCreacion" value="<%=strDatosContrato[14]%>" class="CAMPOFORM" readonly disabled="disabled">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgFechaCreacion" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                    
                                                <td class="LABELFORM"><label for="txtEstado" id="lblEstado">Estado actual:</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtEstado" id="txtEstado" value="<%=strNomEstado[0]%>" class="CAMPOFORM" readonly disabled="disabled">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgEstado" alt="Campo obligatorio" class="IMGERROR">                                                            
                                                </td>                                                
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos básicos</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtConsecutivo" id="lblConsecutivo">Consecutivo:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtConsecutivo" id="txtConsecutivo" value="<%=strDatosContrato[0]%>" class="CAMPOFORM" readonly disabled="disabled">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgConsecutivo" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtContratista" id="lblContratista">Nombre del contratista:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                   <select id="txtContratista" name="txtContratista" class="CAMPOSELECT" disabled="disabled">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsC.size();i++){%>
                                                            <option value="<%=arrIdsC.get(i)%>"><%=arrNombresC.get(i)%></option>
                                                        <%}%>
                                                </select>     
                                                <script type="text/javascript">
                                                     $("#txtContratista option[value='<%=strDatosContrato[1]%>']").attr('selected', 'selected');
                                                </script>
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgContratista" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtTipoContrato" id="lblTipoContrato">Tipo de contrato:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <select id="txtTipoContrato" name="txtTipoContrato" class="CAMPOSELECT" disabled="disabled">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsTC.size();i++){%>
                                                        <%if(arrIdsTC.get(i).equals("PS")){%>
                                                                 <option value="<%=arrIdsTC.get(i)%>" selected><%=arrNombresTC.get(i)%></option>
                                                            <%}else{%>
                                                                 <option value="<%=arrIdsTC.get(i)%>"><%=arrNombresTC.get(i)%></option>
                                                            <%}%>                                                           
                                                        <%}%>
                                                </select>          
                                                <script type="text/javascript">
                                                     $("#txtTipoContrato option[value='<%=strDatosContrato[3]%>']").attr('selected', 'selected');
                                                </script>
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgTipoContrato" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtInterventor" id="lblInterventor">Nombre del interventor:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                   <select id="txtInterventor" name="txtInterventor" class="CAMPOSELECT" disabled="disabled">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsI.size();i++){%>
                                                            <option value="<%=arrIdsI.get(i)%>"><%=arrNombresI.get(i)%></option>
                                                        <%}%>
                                                    </select>        
                                                    <script type="text/javascript">
                                                         $("#txtInterventor option[value='<%=strDatosContrato[2]%>']").attr('selected', 'selected');
                                                    </script>
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgInterventor" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                
                                            </tr> 
                                            <tr>
                                                <td class="LABELFORM"><label for="txtGrupo" id="lblGrupo">Grupo asociado:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <select id="txtGrupo" name="txtGrupo" class="CAMPOSELECT" disabled="disabled">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsG.size();i++){%>
                                                            <option value="<%=arrIdsG.get(i)%>"><%=arrNombresG.get(i)%></option>
                                                        <%}%>
                                                </select>           
                                                <script type="text/javascript">
                                                     $("#txtGrupo option[value='<%=strDatosContrato[4]%>']").attr('selected', 'selected');
                                                </script>
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgGrupo" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                                                                
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos de tiempos</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtFechaIni" id="lblFechaIni">Fecha de inicio:<br />(aaaa-mm-dd)</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtFechaIni" id="txtFechaIni" value="<%=strDatosContrato[5]%>" class="CAMPOFORM" style="width: 180px;" readonly disabled="disabled">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgFechaInicio" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtDuracion" id="lblDuracion">Duración (Días):</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtDuracion" id="txtDuracion" value="<%=strDatosContrato[7]%>" class="CAMPOFORM" readonly disabled="disabled">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgDuracion" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                           
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtFechaFin" id="lblFechaFin">Fecha de fin:<br />(aaaa-mm-dd)</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtFechaFin" id="txtFechaFin" value="<%=strDatosContrato[6]%>" class="CAMPOFORM" style="width: 180px;" readonly disabled="disabled">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgFechaFin" alt="Campo obligatorio" class="IMGERROR">                                                            
                                                </td>                                                 
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos financieros</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtValor" id="lblValor">Valor ($):</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtValor" id="txtValor" value="<%=strDatosContrato[8]%>" class="CAMPOFORM" readonly disabled="disabled">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgValor" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtCentroC" id="lblCentroC">Centro de costos:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                   <select id="txtCentroC" name="txtCentroC" class="CAMPOSELECT" disabled="disabled">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsCC.size();i++){%>
                                                        <option value="<%=arrIdsCC.get(i)%>"><%=arrIdsCC.get(i)%>&nbsp;-&nbsp;<%=arrNombresCC.get(i)%></option>
                                                        <%}%>
                                                    </select>     
                                                    <script type="text/javascript">
                                                         $("#txtCentroC option[value='<%=strDatosContrato[9]%>']").attr('selected', 'selected');
                                                    </script>
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgCentroC" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                                
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Otros datos</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtConsecutivoL" id="lblConsecutivoL">Consecutivo laborales:</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtConsecutivoL" id="txtConsecutivoL" value="<%=strDatosContrato[10]%>" class="CAMPOFORM" readonly disabled="disabled">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgConsecutivoL" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                 <td class="LABELFORM"><label for="txtNumCDP" id="lblNumCDP">Nro. de CDP:</label></td>
                                                <td class="CELDARADIOFORM">
                                                    <input type="text" name="txtNumCDP" id="txtNumCDP" value="<%=strDatosContrato[11]%>" class="CAMPOFORM" readonly disabled="disabled">
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumCDP" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                               
                                            </tr>                                            
                                            <tr>
                                                <td class="LABELFORM"><label for="txtObjeto" id="lblObjeto">Objeto:</label></td>                                        
                                                <td colspan="4" class="CELDACAMPOFORM">                                               
                                                    <div id="txtObjeto" class="DIVEDITABLE" contenteditable="false" style="width: 650px;height: 100px;">                                                        
                                                        <%=strDatosContrato[12]%>
                                                    </div>                                                                
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgObjeto" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td colspan="6" class="SUBTITULOFORM">Datos adjuntos</td>
                                            </tr>
                                            <tr>            
                                                <td class="LABELFORM"><label for="txtActaInicio" id="lblActaInicio">Acta de inicio:</label></td>
                                                <%if(strDatosContrato[15].equals("-")){%>                                                                                            
                                                    <td class="CELDACAMPOFORM">
                                                        <span class="MSGAVISOOBLG">NOTA: No se tiene Acta de Inicio adjunta .</td>           
                                                    </td>                                                     
                                                 <%}else{%>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[15]%>');" />                                                            
                                                    </td>
                                                 <%}%>
                                                 <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgActaInicio" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtActaFin" id="lblActaInicio">Acta de finalización:</label></td>            
                                                <%if(strDatosContrato[16].equals("-")){%> 
                                                    <td class="CELDACAMPOFORM">
                                                        <span class="MSGAVISOOBLG">NOTA: No se tiene Acta de Finalización adjunta .</td>        
                                                    </td>                          
                                                <%}else{%>
                                                    <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[16]%>');" />                                                            
                                                    </td>
                                                 <%}%>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgActaFin" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>                                                                    
                                                <td class="LABELFORM"><label for="txtMinutaContrato" id="lblActaInicio">Minuta del contrato:</label></td>                       
                                                <%if(strDatosContrato[17].equals("-")){%>
                                                    <td class="CELDACAMPOFORM">
                                                        <span class="MSGAVISOOBLG">NOTA: No se tiene Minuta del Contrato adjunta .</td>   
                                                    </td>                         
                                                <%}else{%>
                                                    <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosContrato[17]%>');" />                                                            
                                                    </td>
                                                <%}%>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgMinutaContrato" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr><td colspan="6" class="CELDABOTONFORM">
                                                <%if((Integer.parseInt(strPlanPagos[0])) > 0){%>
                                                    <input type="button" value="Plan de pagos" id="btnPlanPagos" class="BOTONFORM" style="width: 90px;">&nbsp;&nbsp;
                                                <%}%>
                                                <%if((Integer.parseInt(strOTROSIs[0])) > 0){%>
                                                     <input type="button" value="Ver OTROSIs" id="btnOtrosi" class="BOTONFORM" style="width: 90px;">&nbsp;&nbsp;
                                                <%}%>
                                                <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();"></td>
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table
                    <%}%>                        
                <% }%>      
                <div id="dMensaje">            
                </div>
            </div>
            <script language="javascript" type="text/javascript">
                cargarCalendarios();
            </script>
        </section>
       <footer>        
            <div id="dFooter" class="FOOTER">
                <jsp:include page="footer.jsp" />          
            </div>
       </footer>
   </body>
</html>