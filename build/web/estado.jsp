<%-- 
    Document   : estado
    Created on : 26-nov-2013, 16:22:59
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String strAccion = (String) request.getParameter("txtAccion");        
    String strCodigo = (String) request.getParameter("txtCodigo");   
    
    String strSQL = "";
    String[] strDatosEstado = null;    
    
     if (strAccion == null) {
        response.sendRedirect("cerrar.jsp");
     }else{      
        
         if (strAccion.equals("V")){           
             strSQL = "SELECT e.txtCodigo, e.txtNombre from nomina.tbl_estados_contrato e where e.txtCodigo = '" + strCodigo + "'";        
             strDatosEstado = GestionSQL.getFila(strSQL,"Nomina");
        }
     }           
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/estado.js"></script>
        <title>Administración: Estado</title>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />  
       </header>
       <section>
            <div align="center">
                    <br><br>
                    <% if (strAccion.equals("C")){%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class="TITULOFORM">NUEVO REGISTRO DE ESTADO</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmEstado" name="frmEstado">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmEstado">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="C">                       
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">
                                            <tr><td style="height: 10px;"></td></tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtCodigo" id="lblCodigo">* Código:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtCodigo" id="txtCodigo" class="CAMPOFORM">                                     
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgCodigo" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtNombre" id="lblNombre">* Nombre:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNombre" id="txtNombre" class="CAMPOFORM">                                               
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNombre" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>                                    
                                            <tr><td colspan="6" style="height: 10px;"></td></tr>
                                            <tr><td colspan="6" class="CELDABOTONFORM"><input type="button" value="Guardar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;<input type="button" value="Limpiar" id="btnLimpiar" class="BOTONFORM"> &nbsp;&nbsp;<input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();"></td></tr>
                                            <tr><td colspan="6" class="MSGAVISOOBLG">Los campos marcados con (*) son obligatorios.</td></tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>            
                    <%}else{%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class="TITULOFORM">REGISTRO DE ESTADO</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmEstado" name="frmEstado">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmEstado">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="V">          
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">
                                            <tr><td style="height: 10px;"></td></tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtCodigo" id="lblCodigo">Código:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtCodigo" id="txtCodigo" value="<%=strDatosEstado[0]%>" class="CAMPOFORM" readOnly>                                            
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgCodigo" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtNombre" id="lblNombre">* Nombre:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNombre" id="txtNombre" value="<%=strDatosEstado[1]%>" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNombre" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>                                     
                                            <tr><td colspan="6" style="height: 10px;"></td></tr>
                                            <tr><td colspan="6" class="CELDABOTONFORM"><input type="button" value="Guardar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;<input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();"></td></tr>
                                            <tr><td colspan="6" class="MSGAVISOOBLG">Los campos marcados con (*) son obligatorios.</td></tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    <%}%>
                    <br>
                    <div id="dMensaje">                                                 
                    </div>
                </div>
        </section>
       <footer>        
        <div id="dFooter" class="FOOTER">
            <jsp:include page="footer.jsp" />          
        </div>
       </footer>
    </body>
</html>