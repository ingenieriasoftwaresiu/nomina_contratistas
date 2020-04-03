<%-- 
    Document   : interventorXproyecto
    Created on : 26-nov-2013, 11:11:14
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String strAccion = (String) request.getParameter("txtAccion");      
    String strCodigo = (String) request.getParameter("txtCodigo");  
   
    String[] strDatos = null;
    String[] strTemp = null;
    String strSQL = "";   
    Vector arrInterventores;
    Vector arrIdsI = new Vector();
    Vector arrNombresI = new Vector();
    
     if (strAccion == null){
            response.sendRedirect("cerrar.jsp");
     }else{
        
        if (strAccion.equals("V")){
            strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl where tbl.txtEstadoActual = 'A' ORDER BY tbl.txtNombreCompleto";
        }else{
            strSQL = "select tbl.txtIdentificacion, txtNombreCompleto from users_personas tbl ORDER BY tbl.txtNombreCompleto";
        }
        
        arrInterventores = GestionSQL.consultaSQL(strSQL,"Users","2"); 

        for (int i=0;i<arrInterventores.size();i++){
                strTemp = arrInterventores.get(i).toString().split(">");
                arrIdsI.add(strTemp[0]);
                arrNombresI.add(strTemp[1]);
        }
         
        if (strAccion.equals("V")){
            strTemp = strCodigo.split(">");
            strSQL = "select ixp.txtIdInterventor, ixp.txtCodProyecto from nomina.tbl_interventores_x_proyecto ixp where ixp.txtIdInterventor = '" + strTemp[0] + "' and ixp.txtCodProyecto = '" + strTemp[1]  + "'";
            strDatos = GestionSQL.getFila(strSQL,"Nomina");             
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
        <script type="text/javascript" charset="UTF-8" src="Scripts/interventorXproyecto.js"></script>
        <title>Sistema de Gestión de Pagos a Contratistas: Interventor</title>
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
                                <td class="TITULOFORM">NUEVO REGISTRO DE INTERVENTOR POR PROYECTO</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmInterventorXProyecto" name="frmInterventorXProyecto">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmInterventorXProyecto">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="C">                       
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">                
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td class='LABELFORM'><label for='txtInterventor' id='lblInterventor'>* Nombre del interventor:</label></td>
                                                <td class='CELDACAMPOFORM'>
                                                    <select id='txtInterventor' name='txtInterventor' class='CAMPOSELECT'>
                                                        <option value='-1'>Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsI.size();i++){%>
                                                        <option value="<%=arrIdsI.get(i)%>"><%=arrNombresI.get(i)%></option>
                                                        <%}%>
                                                    </select>      
                                                    <br />
                                                    <img src="Images/Agregar.png" alt="Agregar Interventor" title="Agregar Interventor" id="btnAgregarInterventor" style="width: 15px;height: 15px;padding-top: 5px;" />&nbsp;
                                                    <img src="Images/Ejecutar.png" alt="Refrescar" title="Refrescar" class="IMGREFRESH" style="width: 15px;height: 15px;padding-top: 5px;" />
                                                </td>
                                                <td class='CELDAIMGERROR'>
                                                    <img src='Images/error.png' id='imgInterventor' alt='Campo obligatorio' class='IMGERROR'>
                                                </td>
                                                <td class='LABELFORM'><label for='txtCodProy' id='lblCodProy'>Código del proyecto:</label></td>                                   
                                                <td class='CELDACAMPOFORM'>
                                                    <input type='text' name='txtCodProy' id='txtCodProy' class='CAMPOFORM'>                                        
                                                </td>
                                                <td class='CELDAIMGERROR'>
                                                    <img src='Images/error.png' id='imgCodProy' alt='Campo obligatorio' class='IMGERROR'>
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
                                <td class="TITULOFORM">REGISTRO DE INTERVENTOR POR PROYECTO</td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmInterventorXProyecto" name="frmInterventorXProyecto">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmInterventorXProyecto">
                                        <input type="hidden" name="txtAccion" id="txtAccion" value="V">                                         
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">            
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr>
                                                <td class='LABELFORM'><label for='txtInterventor' id='lblInterventor'>Nombre del interventor:</label></td>
                                                <td class='CELDACAMPOFORM'>
                                                    <select id='txtInterventor' name='txtInterventor' class='CAMPOSELECT' disabled="disabled">
                                                        <option value='-1'>Seleccione una opción</option>
                                                        <%for (int i=0;i<arrIdsI.size();i++){%>
                                                        <option value="<%=arrIdsI.get(i)%>"><%=arrNombresI.get(i)%></option>
                                                        <%}%>
                                                    </select>              
                                                    <script type="text/javascript">
                                                     $("#txtInterventor option[value='<%=strDatos[0]%>']").attr('selected', 'selected');
                                                </script>
                                                </td>
                                                <td class='CELDAIMGERROR'>
                                                    <img src='Images/error.png' id='imgInterventor' alt='Campo obligatorio' class='IMGERROR'>
                                                </td>
                                                <td class='LABELFORM'><label for='txtCodProy' id='lblCodProy'>Código del proyecto:</label></td>                                   
                                                <td class='CELDACAMPOFORM'>
                                                    <input type='text' name='txtCodProy' id='txtCodProy' value="<%=strDatos[1]%>" class='CAMPOFORM' disabled="disabled">                                        
                                                </td>
                                                <td class='CELDAIMGERROR'>
                                                    <img src='Images/error.png' id='imgCodProy' alt='Campo obligatorio' class='IMGERROR'>
                                                </td>
                                            </tr>
                                            <tr><td colspan="6" style="height: 0px;"></td></tr>
                                            <tr><td colspan="6" class="CELDABOTONFORM"><input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();"></td></tr>
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