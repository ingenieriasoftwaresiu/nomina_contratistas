<%-- 
    Document   : rolXpersona
    Created on : 19-nov-2013, 16:29:12
    Author     : jorge.correa
--%>

<%@page import="java.util.Vector"%>
<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String strAccion = (String) request.getParameter("txtAccion");        
    String strCodigo = (String) request.getParameter("txtCodigo");   
    
    String strSQL = "";
    String[] strDatosRegistro = null;    
    String[] strTemp = null;
    Vector arrIdsP = new Vector();
    Vector arrNombresP = new Vector();
    Vector arrIdsR = new Vector();
    Vector arrNombresR = new Vector();
    
     if (strAccion == null) {
        response.sendRedirect("cerrar.jsp");
     }else{      
        
        strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl where tbl.txtEstadoActual = 'A' ORDER BY tbl.txtNombreCompleto";
        Vector arrPersonas = GestionSQL.consultaSQL(strSQL,"Users","2");
  
        for (int i=0;i<arrPersonas.size();i++){
            strTemp = arrPersonas.get(i).toString().split(">");
            arrIdsP.add(strTemp[0]);
            arrNombresP.add(strTemp[1]);
        }
        
        strSQL = "select tbl.txtCodigo, tbl.txtNombre from nomina.tbl_roles tbl ORDER BY tbl.txtNombre";
        Vector arrRoles = GestionSQL.consultaSQL(strSQL,"Nomina","2");
  
        for (int i=0;i<arrRoles.size();i++){
            strTemp = arrRoles.get(i).toString().split(">");
            arrIdsR.add(strTemp[0]);
            arrNombresR.add(strTemp[1]);
        }
        
         if (strAccion.equals("V")){           
            strTemp = strCodigo.split(">");
            strSQL = "select tbl.txtIdPersona, tbl.txtIdRol from nomina.tbl_roles_x_persona tbl where tbl.txtIdPersona = '" + strTemp[0] + "' and tbl.txtIdRol = '" + strTemp[1] + "'";
             strDatosRegistro = GestionSQL.getFila(strSQL,"Nomina");
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
        <script type="text/javascript" charset="UTF-8" src="Scripts/rolXpersona.js"></script>
        <title>Administración: Rol por persona</title>
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
                                <td class="TITULOFORM">NUEVO REGISTRO DE ROL POR PERSONA</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmRolXPersona" name="frmRolXPersona">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmRolXPersona">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="C">                       
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">
                                            <tr><td style="height: 10px;"></td></tr>                                                           
                                            <tr>
                                                <td class="LABELFORM"><label for="txtPersona" id="lblPersona">* Nombre de la persona:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <select id="txtPersona" name="txtPersona" class="CAMPOSELECT">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsP.size();i++){%>
                                                            <option value="<%=arrIdsP.get(i)%>"><%=arrNombresP.get(i)%></option>
                                                        <%}%>
                                                </select>                                  
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgPersona" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtRol" id="lblRol">* Rol a asignar:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <select id="txtRol" name="txtRol" class="CAMPOSELECT">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsR.size();i++){%>
                                                            <option value="<%=arrIdsR.get(i)%>"><%=arrNombresR.get(i)%></option>
                                                        <%}%>
                                                </select>                                  
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgRol" alt="Campo obligatorio" class="IMGERROR">
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
                                <td class="TITULOFORM">REGISTRO DE ROL POR PERSONA</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmRolXPersona" name="frmRolXPersona">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmRolXPersona">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="V">          
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">
                                            <tr><td style="height: 10px;"></td></tr>                                                           
                                            <tr>
                                                <td class="LABELFORM"><label for="txtPersona" id="lblPersona">Nombre de la persona:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <select id="txtPersona" name="txtPersona" class="CAMPOSELECT" disabled="disabled">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsP.size();i++){%>
                                                            <option value="<%=arrIdsP.get(i)%>"><%=arrNombresP.get(i)%></option>
                                                        <%}%>
                                                </select>                   
                                                <script type="text/javascript">
                                                     $("#txtPersona option[value='<%=strDatosRegistro[0]%>']").attr('selected', 'selected');
                                                </script>
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgPersona" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtRol" id="lblRol">Rol a asignar:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <select id="txtRol" name="txtRol" class="CAMPOSELECT" disabled="disabled">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsR.size();i++){%>
                                                            <option value="<%=arrIdsR.get(i)%>"><%=arrNombresR.get(i)%></option>
                                                        <%}%>
                                                </select>    
                                                <script type="text/javascript">
                                                     $("#txtRol option[value='<%=strDatosRegistro[1]%>']").attr('selected', 'selected');
                                                </script>
                                                </td>                               
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgRol" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>                                            
                                            <tr><td colspan="6" style="height: 10px;"></td></tr>
                                            <tr><td colspan="6" class="CELDABOTONFORM"><input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();"></td></tr>
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