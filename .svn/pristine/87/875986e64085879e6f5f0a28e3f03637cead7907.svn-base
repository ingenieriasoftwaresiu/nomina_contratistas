<%-- 
    Document   : general
    Created on : 22-nov-2013, 11:20:01
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String strAccion = (String) request.getParameter("txtAccion");      
   
    String[] strDatosGenerales = null;
    String[] strTemp=null;
    String strSQL = "";
       
     if (strAccion == null){
            response.sendRedirect("cerrar.jsp");
     }else{              
               
        if (strAccion.equals("V")){
            strDatosGenerales = GestionSQL.getFila("SELECT * FROM nomina.tbl_generales g where g.txtForm = 'frmGeneral'","Nomina");                               
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
        <script type="text/javascript" charset="UTF-8" src="Scripts/general.js"></script>
        <title>Administración: Configuración general</title>
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
                                <td class="TITULOFORM">CREACIÓN DE CONFIGURACIÓN GENERAL</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmGeneral" name="frmGeneral">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmGeneral">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="C">                       
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO" style="width: 1050px;">                
                                            <tr>                                                
                                                <td class="LABELFORM"><label for="txtNumRegPag" id="lblNumRegPag">* Nro. registros por página:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNumRegPag" id="txtNumRegPag" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumRegPag" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtRutaCargaArchivo" id="lblRutaCargaArchivo">* Ruta de carga de archivos:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtRutaCargaArchivo" id="txtRutaCargaArchivo" class="CAMPOFORM" style="width: 250px;">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgRutaCargaArchivo" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>        
                                            <tr>                                                
                                                <td class="LABELFORM">
                                                    <label for="txtNumDiasAlertaVencerse" id="lblNumDiasAlertaVencerse">* Nro. días alerta vencimiento:</label>
                                                </td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNumDiasAlertaVencerse" id="txtNumDiasAlertaVencerse" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumDiasAlertaVencerse" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM">                                                    
                                                </td>                                        
                                                <td class="CELDACAMPOFORM">                                                    
                                                </td>
                                                <td class="CELDAIMGERROR">                                                    
                                                </td>
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td class="SUBTITULOFORM" colspan="6" >Datos para el envío de E-Mails</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM">* Nombre del servidor:</td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNombreServidor" id="txtNombreServidor" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNombreServidor" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM">* Número del puerto:</td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNumeroPuerto" id="txtNumeroPuerto" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumeroPuerto" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM">* Usuario:</td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtUsuario" id="txtUsuario" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgUsuario" alt="Campo obligatorio" class="IMGERROR">                                            
                                                </td>
                                                <td class="LABELFORM">* Contraseña:</td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="password" name="txtPassword" id="txtPassword" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgPassword" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr><td colspan="6" class="CELDABOTONFORM"><input type="button" value="Guardar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;<input type="button" value="Limpiar" id="btnLimpiar" class="BOTONFORM"> &nbsp;&nbsp;<input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();"></td></tr>
                                            <tr><td colspan="6" class="MSGAVISOOBLG">Los campos marcados con (*) son obligatorios.</td></tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    <% }else{%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class="TITULOFORM">CONSULTA DE CONFIGURACIÓN GENERAL</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmGeneral" name="frmGeneral">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmGeneral">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="V">                       
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO" style="width: 1050px;">                
                                            <tr>                                                
                                                <td class="LABELFORM"><label for="txtNumRegPag" id="lblNumRegPag">* Nro. registros por página:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNumRegPag" id="txtNumRegPag" value="<%=strDatosGenerales[1]%>" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumRegPag" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtRutaCargaArchivo" id="lblRutaCargaArchivo">* Ruta de carga de archivos:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtRutaCargaArchivo" id="txtRutaCargaArchivo" value="<%=strDatosGenerales[2]%>" class="CAMPOFORM" style="width: 250px;">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumRegPag" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>                                                
                                                <td class="LABELFORM">
                                                    <label for="txtNumDiasAlertaVencerse" id="lblNumDiasAlertaVencerse">* Nro. días alerta vencimiento:</label>
                                                </td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNumDiasAlertaVencerse" id="txtNumDiasAlertaVencerse" value="<%=strDatosGenerales[3]%>" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumDiasAlertaVencerse" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM">                                                    
                                                </td>                                        
                                                <td class="CELDACAMPOFORM">                                                    
                                                </td>
                                                <td class="CELDAIMGERROR">                                                    
                                                </td>
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td class="SUBTITULOFORM" colspan="6" >Datos para el envío de E-Mails</td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM">* Nombre del servidor:</td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNombreServidor" id="txtNombreServidor" value="<%=strDatosGenerales[4]%>" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNombreServidor" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM">* Número del puerto:</td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNumeroPuerto" id="txtNumeroPuerto" value="<%=strDatosGenerales[5]%>" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumeroPuerto" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM">* Usuario:</td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtUsuario" id="txtUsuario" value="<%=strDatosGenerales[6]%>" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgUsuario" alt="Campo obligatorio" class="IMGERROR">                                            
                                                </td>
                                                <td class="LABELFORM">* Contraseña:</td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="password" name="txtPassword" id="txtPassword" value="<%=strDatosGenerales[7]%>" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgPassword" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr><td colspan="6" class="CELDABOTONFORM"><input type="button" value="Guardar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;<input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();"></td></tr>
                                            <tr><td colspan="6" class="MSGAVISOOBLG">Los campos marcados con (*) son obligatorios.</td></tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    <% }%>
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