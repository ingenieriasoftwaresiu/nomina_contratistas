<%-- 
    Document   : index
    Created on : 18-nov-2013, 16:16:08
    Author     : jorge.correa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String strUsuario=null;
    String strPreload=null;
    strUsuario = request.getParameter("txtCedula");  
    strPreload = request.getParameter("preload");  
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <link rel="stylesheet" type="text/css" href="Styles/comunes.css" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/login.js"></script>
        <title>Sistema de Gestión de Pagos a Contratistas - Inicio de sesión</title>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />  
       </header>
        <br>
        <div style="padding-top: 50px;"></div>
        <%if (strUsuario == null){%>
                <div align="center">      
                    <br>
                    <form id="frmLogin" name="frmLogin" method="POST" action="Ingreso">
                            <table cellspacing="0" cellpadding="0" class="TABLAMAESTRO" border="0" style="width:320px;">                        
                                <tr>
                                        <td colspan="3" style="text-align: center;" class="TITULOFORM">INICIO DE SESIÓN</td>				
                                </tr>                   
                                <tr>
                                    <td class="LABELFORM" style="width: 90px;padding-left: 10px;">* Tipo de usuario:</td>
                                    <td class="CELDARADIOFORM" style="vertical-align: middle;text-align: center;">
                                        <select name="txtTipoUsuario" id="txtTipoUsuario" class="CAMPOSELECT" style="width:145px;">
                                            <option value="-1">Seleccione una opción</option>
                                            <option value="A">Administrador</option>
                                            <option value="C">Contratista</option>
                                            <option value="I">Interventor</option>
                                        </select>                                    
                                    </td>
                                    <td>
                                        <img src="Images/error.png" id="imgTipoUsuario" alt="Campo obligatorio" class="IMGERROR">
                                    </td>
                                </tr>
                                <tr>
                                        <td class="LABELFORM" style="width: 90px;padding-left: 10px;">* Usuario:</td>
                                        <td class="CELDACAMPOFORM" style="width: 150px;text-align: center;">
                                            <input type="text" name="txtUsuario" id="txtUsuario" class="CAMPOFORM" style="width: 142px;">
                                        </td>
                                        <td style="width: 30px;text-align: left;"><img src="Images/error.png" id="imgUsuario" alt="Campo obligatorio" class="IMGERROR"></td>
                                </tr>
                                <tr>
                                        <td class="LABELFORM" style="width: 90px;padding-left: 10px;">* Contraseña:</td>
                                        <td class="CELDACAMPOFORM" style="width: 150px;text-align: center;">
                                            <input type="password" name="txtPwd" id="txtPwd" class="CAMPOFORM" style="width: 142px;">
                                        </td>
                                        <td style="width: 30px;text-align: left;"><img src="Images/error.png" id="imgPwd" alt="Campo obligatorio" class="IMGERROR"></td>
                                </tr>
                                <tr>
                                        <td colspan="3" class="CELDABOTONFORM">
                                            <input type="button" value="Ingresar" id="btnIngresar" class="BOTONFORM">&nbsp;&nbsp;
                                            <input type="button" value="Limpiar" id="btnLimpiar" class="BOTONFORM">                                                
                                        </td>		
                                </tr>
                                <tr>
                                    <td class="MSGAVISOOBLG" colspan="3" style="padding-left: 3px;padding-bottom: 3px;">(*) Campo obligatorio.</td>
                                </tr>
                            </table>				
                    </form>
                    <br>
                    <div id="dMensaje" class="TEXTOFALLO">                
                    </div>
                </div>  
          <%}else{%>
                <script type="text/javascript">
                    var dataString = "txtUsuario="+ <%=strUsuario%> + "&preload=" + <%=strPreload%>;                             
                    setTimeout(function(){AJAX_REDIRECT("POST","Ingreso",dataString,"dMensaje","principal.jsp");},3000);
                </script>
                <div align="center">              
                    <div style="padding-top: 80px;"></div>            
                    <table cellspacing="0" cellpadding="0" width="600px"  border="0">                        
                        <tr>
                                <td class="TEXTOHOMEBIG">BIENVENIDO!</td>                                
                        </tr>
                        <tr><td height="10px"></td></tr>
                        <tr>
                                <td class="TEXTOHOMESMALL">Un momento por favor, la aplicación se está cargando para su acceso.</td>                                
                        </tr>
                        <tr><td height="15px"></td></tr>
                        <tr>
                            <td style="text-align: center;"><img src="Images/loader.gif" width="40" height="40"></td>		
                        </tr>
                    </table>      
                </div>
          <%}%>
          <br><br>
        <div align="center" class="MSGBROWSER">
            <<< Para una mejor visualización, se recomienda utilizar <b>Mozilla Firefox</b> ó <b>Google Chrome</b> en una resolución superior 1024 x 768. >>>
        </div> 
        <footer>
            <div id="dFooter" class="FOOTER">
                <jsp:include page="footer.jsp" />          
            </div>
        </footer>
    </body>
</html>