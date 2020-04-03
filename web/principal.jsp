<%-- 
    Document   : principal
    Created on : 19-nov-2013, 9:13:49
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="comprobarSesion.jsp"></jsp:include>
<!DOCTYPE html>
<%
    String strUsuario = null;
    String strPreload = null;
    String strTipoUsuario = null;
    String[] strTemp = null;
    String[] strDatosDllo = null;
    String[] strNombreUsuario = null;
    String strSQL = "";
    strUsuario = (String) request.getParameter("txtCedula");
    strPreload = (String) session.getAttribute("preload");
    strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
         
    if ((strUsuario == null) || session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);    
    }else{             
        if ((strTipoUsuario.equals("A")) || (strTipoUsuario.equals("I"))){       
           strSQL = "select p.txtNombreCompleto from users.users_personas p where p.txtIdentificacion = '" + strUsuario + "'";
           strNombreUsuario = GestionSQL.getFila(strSQL,"Users");

           strSQL = "select rxp.txtIdPersona from nomina.tbl_roles_x_persona rxp where rxp.txtIdPersona = '" + strUsuario + "' and rxp.txtIdRol = 'DES'";
           strDatosDllo = GestionSQL.getFila(strSQL,"Nomina");

        }else{
           strSQL = "select CONCAT(c.txtNombres,' ',c.txtApellidos) from nomina.tbl_contratistas c where c.txtNumId = '" + strUsuario + "'";
           strNombreUsuario = GestionSQL.getFila(strSQL,"Nomina");
        }
   }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <link rel="stylesheet" type="text/css" href="Styles/menu.css" />
        <link rel="stylesheet" type="text/css" href="Styles/visualizacion.css" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/principal.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/visualizacion.js"></script>
        <title>Sistema de Gestión de Pagos a Contratistas: Menú principal</title>
        <script type="text/javascript">
            function disableKeyPress(evt){               
                var evt = (evt) ? evt : ((event) ? event : null); 
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
                if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
            }
        </script>
    </head>
    <body onKeyPress="disableKeyPress(event);">        
        <input type="hidden" name="txtUsuario" id="txtUsuario" value="<%=strUsuario%>">
        <input type="hidden" name="txtTipoUsuario" id="txtTipoUsuario" value="<%=strTipoUsuario%>">
        <jsp:include page="header.jsp" />
        <div align="center">            	   
            <br>
            <table cellspacing="0" cellpadding="0" width="99%" border="0">                
                    <tr>
                        <% if(strNombreUsuario != null){ %>
                            <%if (strTipoUsuario.equals("A")){%>
                                <td class="USERLOGED" style="text-align: left;"><b>Bienvenido(a),</b>&nbsp;<span style="font-weight: normal;"><%=strNombreUsuario[0]%>&nbsp;[Administrador]</span></td>
                            <%}%>
                            <%if (strTipoUsuario.equals("I")){%>
                                <td class="USERLOGED" style="text-align: left;"><b>Bienvenido(a),</b>&nbsp;<span style="font-weight: normal;"><%=strNombreUsuario[0]%>&nbsp;[Interventor]</span></td>
                            <%}%>
                            <%if (strTipoUsuario.equals("C")){%>
                                <td class="USERLOGED" style="text-align: left;"><b>Bienvenido(a),</b>&nbsp;<span style="font-weight: normal;"><%=strNombreUsuario[0]%>&nbsp;[Contratista]</span></td>             
                            <%}%>                        
                        <%}else{%>
                            <td class="USERLOGED" style="text-align: left;"><b>Bienvenido(a)!</b></td> 
                        <%}%>
                        <%if (strPreload.equals("N")){%>     
                            <td class="CELDAACCION" style="border-left: 1px solid #116043;width: 120px;text-align: right;vertical-align: middle;"><input type="button" value="Salir" class="BOTONACCION" onclick="location.href='cerrar.jsp';" /></td>
                        <%}else{%>
                            <td class="CELDAACCION" style="border-left: 1px solid #116043;width: 120px;text-align: right;vertical-align: middle;"><input type="button" value="Salir" class="BOTONACCION" onclick="javascript:window.close();" /></td>
                        <%}%>                                            
                    </tr>                                
            </table>
            <br>
             <table cellspacing="0" cellpadding="0" width="99%" border="0">
                <tr>
                    <td width="18%" style="vertical-align: text-top;">   
                        <%if(strTipoUsuario.equals("A")){%>
                            <table cellspacing="0" cellpadding="5" width="100%" border="0" class="TABLAMENU">
                                <tr><td class="TITULOMENU" style="font-size: 15px;">MENÚ PRINCIPAL</td></tr>                         
                                <tr><td class="ITEMMENU" id="itPersonas"><a href="#">Contratistas</a></td></tr>                                    
                                <tr><td class="ITEMMENU" id="itContratos"><a href="#">Contratos activos</a></td></tr>                                                  
                                <tr><td class="ITEMMENU" id="itContratosH"><a href="#">Contratos históricos</a></td></tr>
                                <tr><td class="SUBTITULOMENU">Informes</td></tr>
                                <tr><td class="ITEMMENU"><a href="#" onclick="javascript:window.open('Informes/PagosXEstado.jsp','Pagos_X_Estado','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',scrollbars=yes')">Pagos por estado</a></td></tr>                                                                                         
                                <tr><td class="ITEMMENU"><a href="#" onclick="javascript:window.open('Informes/PagosXPeriodo.jsp','Pagos_X_Periodo','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',scrollbars=yes')">Pagos por periodo</a></td></tr>                                                                                         
                                <tr><td class="ITEMMENU"><a href="#" onclick="generarArchivoPlanoContrato()">Archivo plano de Contratos</a></td></tr>                                                                                         
                                <tr><td class="SUBTITULOMENU">Administración</td></tr>
                                <tr><td class="ITEMMENU"><a href="#" onclick="javascript:window.open('admin.jsp','Administracion','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',scrollbars=yes')">Parámetros del sistema</a></td></tr>
                               <%if(strDatosDllo != null){%>
                                    <tr><td class="SUBTITULOMENU">Desarrollador</td></tr>
                                    <tr><td class="ITEMMENU" id="itCambioEstadoContrato"><a href="#">Iniciar Job: Cambio de estado Contratos</a></td></tr>                                                      
                                    <tr><td class="ITEMMENU" id="itCambioEstadoPago"><a href="#">Iniciar Job: Cambio de estado Pagos</a></td></tr>
                                    <tr><td class="ITEMMENU" id="itDetenerJobs"><a href="#">Detener Jobs</a></td></tr>
                                <%}%>
                            </table>     
                        <%}else{%>                            
                                <table cellspacing="0" cellpadding="5" width="100%" border="0" class="TABLAMENU">
                                    <tr><td class="TITULOMENU" style="font-size: 15px;">MENÚ PRINCIPAL</td></tr>       
                                    <tr><td class="ITEMMENU" id="itContratosP"><a href="#">Contratos activos</a></td></tr>                                     
                                    <tr><td class="ITEMMENU" id="itContratosPH"><a href="#">Contratos históricos</a></td></tr>
                                </table>                                                      
                         <%}%>
                    </td>
                    <td width="1%"></td>
                    <td style="width:80%;text-align: center;vertical-align: top;">
                        <div id="dMostrar">                            
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <br>	
        <jsp:include page="footer.jsp" />
     </body>
</html>
