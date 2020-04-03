<%-- 
    Document   : tipo_contrato
    Created on : 19-nov-2013, 16:20:16
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String strAccion = (String) request.getParameter("txtAccion");        
    String strCodigo = (String) request.getParameter("txtCodigo");   
    
    String strSQL = "";
    String[] strDatosTipoC = null;    
    
     if (strAccion == null) {
        response.sendRedirect("cerrar.jsp");
     }else{      
        
         if (strAccion.equals("V")){           
             strSQL = "SELECT r.txtCodigo, r.txtNombre from nomina.tbl_tipos_contrato r where r.txtCodigo = '" + strCodigo + "'";        
             strDatosTipoC = GestionSQL.getFila(strSQL,"Nomina");
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
        <script type="text/javascript" charset="UTF-8" src="Scripts/tipo_contrato.js"></script>
        <title>Administración: Tipos de contrato</title>
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
                                <td class="TITULOFORM">NUEVO REGISTRO DE TIPO DE CONTRATO</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmTipoC" name="frmTipoC">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmTipoC">
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
                                <td class="TITULOFORM">REGISTRO DE TIPO DE CONTRATO</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmTipoC" name="frmTipoC">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmTipoC">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="V">          
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">
                                            <tr><td style="height: 10px;"></td></tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtCodigo" id="lblCodigo">Código:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtCodigo" id="txtCodigo" value="<%=strDatosTipoC[0]%>" class="CAMPOFORM" readOnly>                                            
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgCodigo" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtNombre" id="lblNombre">* Nombre:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNombre" id="txtNombre" value="<%=strDatosTipoC[1]%>" class="CAMPOFORM">
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