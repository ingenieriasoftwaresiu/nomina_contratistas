 <%-- 
    Document   : otrosi
    Created on : 30/01/2015, 02:16:35 PM
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="comprobarSesion.jsp"></jsp:include>
<%
    String strConsecutivo = (String) request.getParameter("txtConsecutivo");
    String strAccion = (String)request.getParameter("txtAccion");
    String strTipoUsuario = null, strCedula=null, strSQL = "";
    String[] strDatosUsuario = null, strDatosContrato = null, strDatosOTROSI=null;
    
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
        strCedula = (String) session.getAttribute("txtCedula");
        
        strSQL = "SELECT txtNombreCompleto FROM users.users_personas where txtIdentificacion = '" + strCedula + "'";
        strDatosUsuario = GestionSQL.getFila(strSQL, "Nomina");        
        
        strSQL = "SELECT dtFechaFin FROM nomina.tbl_contratos WHERE txtConsecutivo = '" + strConsecutivo + "'";
        strDatosContrato = GestionSQL.getFila(strSQL, "Nomina");
        
        if (strAccion.equals("V")){
            
            String strCodigoOTROSI = request.getParameter("txtCodigo");
            
            strSQL = "SELECT * from nomina.tbl_otrosi_contratos where txtCodigo = '" + strCodigoOTROSI + "' and txtIdContrato = '" + strConsecutivo + "'";
            strDatosOTROSI = GestionSQL.getFila(strSQL,"Nomina");
        }
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <link rel="stylesheet" type="text/css" href="Styles/calendar-system.css" />        
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="Scripts/JSCalendar.js"></script>
        <script type="text/javascript" src="Scripts/JSCalendar-es.js"></script>
        <script type="text/javascript" src="Scripts/JSCalendar-setup.js"></script>
        <script type="text/javascript" src="Scripts/jquery.filestyle.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>  
        <script type="text/javascript" charset="UTF-8" src="Scripts/otrosi.js"></script>
        <title>OTROSI del contrato <%=strConsecutivo%></title>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />  
       </header>
       <section>            
            <div align="center">
                <br /><br />
                <% if (strAccion.equals("C")){%>                    
                    <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                        <tr>
                            <td class="TITULOFORM">NUEVO OTROSI DEL CONTRATO <%=strConsecutivo%></td>
                        </tr>
                        <tr>
                            <td>
                                <form method="POST" id="frmOTROSI" name="frmOTROSI" enctype="multipart/form-data" action="RegistroOTROSI" onsubmit="return validarOTROSI();">
                                    <input type="hidden" name="txtForm" id="txtForm" value="frmOTROSI" />                   
                                    <input type="hidden" name="txtIdAutor" id="txtIdAutor" value="<%=strCedula%>" />             
                                    <input type="hidden" name="txtAccion" id="txtAccion" value="<%=strAccion%>">
                                    <input type="hidden" name="txtConsecutivo" id="txtConsecutivo" value="<%=strConsecutivo%>">
                                    <input type="hidden" name="txtRutaActa" id="txtRutaActa" value="-" />
                                    <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">
                                        <tr>
                                            <td class="LABELFORM">
                                                <label for="txtFechaCreacion" id="lblFechaCreacion">Fecha de creación:<br />(aaaa-mm-dd)</label>
                                            </td>
                                            <td class="CELDACAMPOFORM">
                                                <input type="text" name="txtFechaCreacion" id="txtFechaCreacion" class="CAMPOFORM" readOnly>
                                            </td>                               
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgFechaCreacion" alt="Campo obligatorio" class="IMGERROR">
                                            </td>                    
                                            <td class="LABELFORM">
                                                <label for="txtAutor" id="lblAutor">Nombre del autor(a):</label>
                                            </td>
                                            <td class="CELDACAMPOFORM">
                                                <%
                                                    strSQL = "SELECT txtNombreCompleto FROM users.users_personas where txtIdentificacion = '" + strCedula + "'";
                                                    strDatosUsuario = GestionSQL.getFila(strSQL, "Nomina");
                                                %>
                                                <input type="text" name="txtAutor" id="txtAutor" value="<%=strDatosUsuario[0]%>" class="CAMPOFORM" readonly>
                                            </td>                               
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgAutor" alt="Campo obligatorio" class="IMGERROR">                                                            
                                            </td>                                                
                                        </tr>
                                        <tr>
                                            <td class="LABELFORM">
                                                <label for="txtFechaFinActual" id="lblFechaFinActual">Fecha de finalización actual:<br />(aaaa-mm-dd)</label>
                                            </td>
                                            <td class="CELDACAMPOFORM">
                                                <input type="text" name="txtFechaFinActual" id="txtFechaFinActual" value="<%=strDatosContrato[0].toString()%>" class="CAMPOFORM" readonly>                                                    
                                            </td>                               
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgFechaFinActual" alt="Campo obligatorio" class="IMGERROR">                                                     
                                            </td>                                                
                                            <td class="LABELFORM">
                                                <label for="txtFechaFinNueva" id="lblFechaFinNueva">* Fecha de finalización nueva:<br />(aaaa-mm-dd)</label>
                                            </td>
                                            <td class="CELDACAMPOFORM">
                                                <input type="text" name="txtFechaFinNueva" id="txtFechaFinNueva" class="CAMPOFORM" style="width: 180px;" readonly>
                                                &nbsp;<img src="Images/Calendario.JPG" class="IMGCALENDAR" id="imgFechaFinNueva">
                                            </td>                               
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgFechaFinNuevaC" alt="Campo obligatorio" class="IMGERROR">                                                     
                                            </td>                                                 
                                        </tr>
                                        <tr>
                                            <td class="LABELFORM">
                                                <label for="txtJustificacion" id="lblJustificacion">* Justificación:</label>
                                            </td>
                                            <td class="CELDACAMPOFORM" colspan="4">
                                                <textarea name="txtJustificacion" id="txtJustificacion" rows="4" cols="50" wrap="soft" class="TEXTAREA">                                                        
                                                </textarea>                                                                                                       
                                            </td>
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgJustificacion" alt="Campo obligatorio" class="IMGERROR">                                                     
                                            </td>
                                        </tr>
                                        <tr>                                                                    
                                            <td class="LABELFORM"><label for="txtActa" id="lblActa">* Acta de oficialización:</label></td>                                        
                                            <td class="CELDACAMPOFORM">
                                                <input type="file" name="txtActa" id="txtActa" class="CAMPOFORM"><br />
                                                <span class="MSGAVISOOBLG" style="font-size: 9px;">[El archivo debe adjuntarse en formato PDF]</span>              
                                            </td>                                                      
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgActa" alt="Campo obligatorio" class="IMGERROR">
                                            </td>
                                        </tr>
                                        <tr><td colspan="6" style="height: 0px;"></td></tr>
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
                                        <script type="text/javascript">
                                            cargarCalendarios();
                                        </script>  
                                    </table>
                                </form>
                            </td>
                        </tr>
                    </table>                    
             <%}else{%>
                <%if(strDatosOTROSI != null){%>
                    <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class="TITULOFORM">OTROSI <%=strDatosOTROSI[0]%> DEL CONTRATO <%=strConsecutivo%></td>
                            </tr>
                            <tr>
                                <td>
                                    <form method="POST" id="frmOTROSI" name="frmOTROSI" enctype="multipart/form-data" action="RegistroOTROSI" onsubmit="return validarOTROSI();">
                                            <input type="hidden" name="txtForm" id="txtForm" value="frmOTROSI" />                   
                                            <input type="hidden" name="txtIdAutor" id="txtIdAutor" value="<%=strCedula%>" />             
                                            <input type="hidden" name="txtAccion" id="txtAccion" value="<%=strAccion%>">
                                            <input type="hidden" name="txtConsecutivo" id="txtConsecutivo" value="<%=strConsecutivo%>">
                                            <input type="hidden" name="txtCodigo" id="txtCodigo" value="<%=strDatosOTROSI[0]%>">
                                            <input type="hidden" name="txtRutaActa" id="txtRutaActa" value="-" />
                                            <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">
                                                <tr>
                                                        <td class="LABELFORM">
                                                            <label for="txtFechaCreacion" id="lblFechaCreacion">Fecha de creación:<br />(aaaa-mm-dd)</label>
                                                        </td>
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="text" name="txtFechaCreacion" id="txtFechaCreacion" value="<%=strDatosOTROSI[3]%>" class="CAMPOFORM" readOnly>
                                                        </td>                               
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgFechaCreacion" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>                    
                                                        <td class="LABELFORM">
                                                            <label for="txtAutor" id="lblAutor">Nombre del autor(a):</label>
                                                        </td>
                                                        <td class="CELDACAMPOFORM">
                                                            <%
                                                                strSQL = "SELECT txtNombreCompleto FROM users.users_personas where txtIdentificacion = '" + strDatosOTROSI[2] + "'";
                                                                strDatosUsuario = GestionSQL.getFila(strSQL, "Nomina");
                                                            %>
                                                            <input type="text" name="txtAutor" id="txtAutor" value="<%=strDatosUsuario[0]%>" class="CAMPOFORM" readonly>
                                                        </td>                               
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgAutor" alt="Campo obligatorio" class="IMGERROR">                                                            
                                                        </td>                                                
                                                    </tr>
                                                    <tr>
                                                        <td class="LABELFORM">
                                                            <label for="txtFechaFinActual" id="lblFechaFinActual">Fecha de finalización actual:<br />(aaaa-mm-dd)</label>
                                                        </td>
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="text" name="txtFechaFinActual" id="txtFechaFinActual" value="<%=strDatosOTROSI[4]%>" class="CAMPOFORM" readonly>                                                    
                                                        </td>                               
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgFechaFinActual" alt="Campo obligatorio" class="IMGERROR">                                                     
                                                        </td>                                                
                                                        <td class="LABELFORM">
                                                            <label for="txtFechaFinNueva" id="lblFechaFinNueva">Fecha de finalización nueva:<br />(aaaa-mm-dd)</label>
                                                        </td>
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="text" name="txtFechaFinNueva" id="txtFechaFinNueva" value="<%=strDatosOTROSI[5]%>" class="CAMPOFORM" style="width: 180px;" readonly>                                                            
                                                        </td>                               
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgFechaFinNuevaC" alt="Campo obligatorio" class="IMGERROR">                                                     
                                                        </td>                                                 
                                                    </tr>
                                                    <tr>
                                                        <td class="LABELFORM">
                                                            <label for="txtJustificacion" id="lblJustificacion">* Justificación:</label>
                                                        </td>
                                                        <td class="CELDACAMPOFORM" colspan="4">
                                                            <textarea name="txtJustificacion" id="txtJustificacion" rows="4" cols="50" wrap="soft" class="TEXTAREA">        
                                                                <%=strDatosOTROSI[6]%>
                                                            </textarea>                                                                                                       
                                                        </td>
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgJustificacion" alt="Campo obligatorio" class="IMGERROR">                                                     
                                                        </td>
                                                    </tr>
                                                    <tr>                                                                    
                                                        <td class="LABELFORM"><label for="txtActa" id="lblActa">Acta de oficialización:</label></td>                                                               
                                                        <%if(strDatosOTROSI[7].equals("-")){%>
                                                            <td class="CELDACAMPOFORM">
                                                                <span class="MSGAVISOOBLG">NOTA: No se tiene acta de oficialización adjunta .</td>   
                                                            </td>                         
                                                        <%}else{%>
                                                            <td class="CELDACAMPOFORM">
                                                                    <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosOTROSI[7]%>');" />                                                            
                                                            </td>
                                                        <%}%>                                                                                                              
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgActa" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                    </tr>
                                                    <tr><td colspan="6" style="height: 0px;"></td></tr>
                                                    <tr>
                                                        <td colspan="6" class="CELDABOTONFORM">
                                                            <input type="submit" value="Actualizar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;                                                         
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
                        <div class="TEXTOFALLO">
                            No se recuperaron los datos del OTROSI seleccionado. Por favor contacte al Administrador del Sistema.                            
                        </div>
                        <br />
                    <%}%>
             <%}%>
            </div>
       </section>
        <footer>                    
            <br />
            <jsp:include page="footer.jsp" />                     
        </footer> 
    </body>
</html>
