<%-- 
    Document   : buscarContratista
    Created on : 17/01/2015, 04:54:06 PM
    Author     : Jorge.correa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String strAccion = request.getParameter("txtAccion");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>       
        <script type="text/javascript" charset="UTF-8" src="Scripts/buscar_persona.js"></script>    
        <%if(strAccion.equals("C")){%>
            <title>Búsqueda de contratista</title>
        <%}else{%>
            <title>Búsqueda de interventor</title>
        <%}%>
    </head>
    <body>        
        <div align="center">
            <form id="frmBuscarContratista" name="frmBuscarContratista" method="POST">
                <input type="hidden" id="txtAccion" name="txtAccion" value="<%=strAccion%>" />
                <table cellspacing="0" cellpadding="0" border="0" class="TABLAFORM" width="99%">                    
                    <tr>
                        <%if(strAccion.equals("C")){%>
                            <td colspan="6" class="TITULOFORM">BÚSQUEDA DE CONTRATISTA</td>
                        <%}else{%>
                            <td colspan="6" class="TITULOFORM">BÚSQUEDA DE INTERVENTOR</td>
                        <%}%>
                    </tr>
                    <tr>
                        <td class="LABELFORM"><label for="txtNumId" id="lblNumId">Nro. de identificación:</label></td>
                        <td class="CELDARADIOFORM">
                            <input type="text" name="txtNumId" id="txtNumId" class="CAMPOFORM" />
                        </td>                               
                        <td class="CELDAIMGERROR">
                            <img src="Images/error.png" id="imgNumId" alt="Campo obligatorio" class="IMGERROR">
                        </td>                    
                        <td class="LABELFORM"><label for="txtNombre" id="lblNombre">Nombre:</label></td>
                        <td class="CELDARADIOFORM">
                            <input type="text" name="txtNombre" id="txtNombre" class="CAMPOFORM" />
                        </td>                               
                        <td class="CELDAIMGERROR">
                            <img src="Images/error.png" id="imgNombre" alt="Campo obligatorio" class="IMGERROR">                                                            
                        </td>  
                    </tr>
                    <tr>
                        <td colspan="6" class="CELDABOTONFORM">
                            <input type="button" value="Buscar" id="btnBuscar" class="BOTONFORM">&nbsp;&nbsp;
                            <input type="button" value="Limpiar" id="btnLimpiar" class="BOTONFORM"> &nbsp;&nbsp;
                            <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();">
                        </td>
                    </tr>
                </table>
            </form>
            <br />
            <div id="dResultados">                             
            </div>
        </div>        
    </body>
</html>
