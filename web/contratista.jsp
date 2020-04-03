<%-- 
    Document   : contratista
    Created on : 25-nov-2013, 12:17:39
    Author     : jorge.correa
--%>

<%@page import="java.util.Vector"%>
<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String strAccion = (String) request.getParameter("txtAccion");      
    String strCodigo = (String) request.getParameter("txtCodigo");  
    String strTipoRegistro = "";
    
    if(request.getParameter("txtTipoRegistro") != null){
        strTipoRegistro = (String) request.getParameter("txtTipoRegistro");
    }else{
        strTipoRegistro = "C";
    }
   
    String[] strDatosContratista = null;
    String[] strTemp=null;
    String strSQL = "";
    Vector arrIdsTI = new Vector();
    Vector arrNombresTI = new Vector();
    
     if (strAccion == null){
            response.sendRedirect("cerrar.jsp");
     }else{
        
        strSQL="select ti.txtIdentificador, ti.txtNombre from users.users_tipos_id ti ORDER BY ti.txtNombre";
        Vector arrTiposIds = GestionSQL.consultaSQL(strSQL, "Users","2");
        
        for(int i=0;i<arrTiposIds.size();i++){
            strTemp = arrTiposIds.get(i).toString().split(">");
            arrIdsTI.add(strTemp[0]);
            arrNombresTI.add(strTemp[1]);
        }
               
        if (strAccion.equals("V")){
            strSQL = "select * from nomina.tbl_contratistas c where c.txtNumId = '" +  strCodigo + "'";
            strDatosContratista = GestionSQL.getFila(strSQL,"Nomina");                               
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
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>  
        <script type="text/javascript" charset="UTF-8" src="Scripts/contratista.js"></script>   
        <%if(strTipoRegistro.equals("I")){%>
            <title>Sistema de Gestión de Pagos a Contratistas: Interventor</title>
        <%}else{%>
            <title>Sistema de Gestión de Pagos a Contratistas: Contratista</title>
        <%}%>         
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
                                <%if(strTipoRegistro.equals("I")){%>
                                    <td class="TITULOFORM">NUEVO REGISTRO DE INTERVENTOR</td>
                                <%}else{%>
                                    <td class="TITULOFORM">NUEVO REGISTRO DE CONTRATISTA</td>
                                <%}%>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmContratista" name="frmContratista">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmContratista">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="C">   
                                        <input type="hidden" name="txtTipoRegistro" id="txtTipoRegistro" value="<%=strTipoRegistro%>">
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">                                                       
                                            <tr>
                                                <td class="LABELFORM"><label for="txtTipoId" id="lblTipoId">* Tipo de identificación:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                   <select id="txtTipoId" name="txtTipoId" class="CAMPOSELECT">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsTI.size();i++){%>
                                                        <%if(arrIdsTI.get(i).equals("CC")){%>
                                                                <option value="<%=arrIdsTI.get(i)%>" selected><%=arrNombresTI.get(i)%></option>
                                                            <%}else{%>
                                                                <option value="<%=arrIdsTI.get(i)%>"><%=arrNombresTI.get(i)%></option>
                                                            <%}%>
                                                        <%}%>
                                                </select>                         
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgTipoId" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtNumId" id="lblNumId">* Nro. de identificación:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNumId" id="txtNumId" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumId" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtNombres" id="lblNombres">* Nombres:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNombres" id="txtNombres" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNombres" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtApellidos" id="lblApellidos">* Apellidos:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtApellidos" id="txtApellidos" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgApellidos" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtDireccion" id="lblDireccion">Dirección:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtDireccion" id="txtDireccion" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgDireccion" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtTelefono" id="lblTelefono">Teléfono:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtTelefono" id="txtTelefono" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgTelefono" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM">                                           
                                                    <label for="txtEmail" id="lblEmail">* Correo electrónico:</label>                                                                       
                                                </td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtEmail" id="txtEmail" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgEmail" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtEstado" id="lblEstado">* Estado:</label></td>                                        
                                                <td class="CELDARADIOFORM" style="text-align: left;">
                                                    <input type="radio" name="txtEstado" id="rdActivo" value="A" checked>Activo&nbsp;&nbsp;
                                                    <input type="radio" name="txtEstado" id="rdInactivo" value="I">Inactivo 
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgEstado" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <%if(strTipoRegistro.equals("C")){%>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtJubilado" id="lblJubilado">* ¿Es jubilado?:</label></td>                                        
                                                    <td class="CELDARADIOFORM" style="text-align: left;">
                                                        <input type="radio" name="txtJubilado" id="rdJubiladoSi" value="S">Si&nbsp;&nbsp;
                                                        <input type="radio" name="txtJubilado" id="rdJubiladoNo" value="N">No 
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgJubilado" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>
                                             <%}%>
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
                                <td class="TITULOFORM">REGISTRO DE CONTRATISTA</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmContratista" name="frmContratista">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmContratista">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="V">       
                                        <input type="hidden" name="txtTipoRegistro" id="txtTipoRegistro" value="<%=strTipoRegistro%>">
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">                                                       
                                            <tr>
                                                <td class="LABELFORM"><label for="txtTipoId" id="lblTipoId">* Tipo de identificación:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                   <select id="txtTipoId" name="txtTipoId" class="CAMPOSELECT" disabled="disabled">
                                                        <option value="-1">Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsTI.size();i++){%>
                                                            <option value="<%=arrIdsTI.get(i)%>"><%=arrNombresTI.get(i)%></option>
                                                        <%}%>
                                                </select>                         
                                                <script type="text/javascript">
                                                     $("#txtTipoId option[value='<%=strDatosContratista[0]%>']").attr('selected', 'selected');
                                                </script>
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgTipoId" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtNumId" id="lblNumId">* Nro. de identificación:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNumId" id="txtNumId" value="<%=strDatosContratista[1]%>" class="CAMPOFORM" disabled="disabled">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNumId" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtNombres" id="lblNombres">* Nombres:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtNombres" id="txtNombres" value="<%=strDatosContratista[2]%>" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgNombres" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtApellidos" id="lblApellidos">* Apellidos:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtApellidos" id="txtApellidos" value="<%=strDatosContratista[3]%>" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgApellidos" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtDireccion" id="lblDireccion">Dirección:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtDireccion" id="txtDireccion" value="<%=strDatosContratista[4]%>" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgDireccion" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtTelefono" id="lblTelefono">Teléfono:</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtTelefono" id="txtTelefono" value="<%=strDatosContratista[5]%>" class="CAMPOFORM">                                           
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgTelefono" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="LABELFORM"><label for="txtEmail" id="lblEmail">Correo electrónico:</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtEmail" id="txtEmail" value="<%=strDatosContratista[6]%>" class="CAMPOFORM">
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgEmail" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtEstado" id="lblEstado">* Estado:</label></td>                                        
                                                <td class="CELDARADIOFORM" style="text-align: left;">
                                                    <input type="radio" name="txtEstado" id="rdActivo" value="A">Activo&nbsp;&nbsp;
                                                    <input type="radio" name="txtEstado" id="rdInactivo" value="I">Inactivo 
                                                    <script type="text/javascript">
                                                            $("[name=txtEstado]").filter("[value='<%=strDatosContratista[7]%>']").prop("checked",true);
                                                    </script>
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgEstado" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>
                                            <%if(strTipoRegistro.equals("C")){%>
                                                <tr>
                                                    <td class="LABELFORM"><label for="txtJubilado" id="lblJubilado">* ¿Es jubilado?:</label></td>                                        
                                                    <td class="CELDARADIOFORM" style="text-align: left;">
                                                        <input type="radio" name="txtJubilado" id="rdJubiladoSi" value="S">Si&nbsp;&nbsp;
                                                        <input type="radio" name="txtJubilado" id="rdJubiladoNo" value="N">No 
                                                        <script type="text/javascript">
                                                                $("[name=txtJubilado]").filter("[value='<%=strDatosContratista[8]%>']").prop("checked",true);
                                                        </script>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgJubilado" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>
                                             <%}%>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr><td colspan="6" class="CELDABOTONFORM"><input type="button" value="Guardar" id="btnGuardar" class="BOTONFORM"> &nbsp;&nbsp;<input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();"></td></tr>
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