<%-- 
    Document   : admin
    Created on : 19-nov-2013, 14:25:37
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="comprobarSesion.jsp"></jsp:include>
<!DOCTYPE html>
<%
    String[] strDatos= null; 
    String[] strDatosDllo = null;
    String strSQL = "", strUsuario=null, strDllo="N";
    
    strUsuario = (String) session.getAttribute("txtCedula");
    
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);    
    }
    
    strSQL = "select rxp.txtIdPersona from nomina.tbl_roles_x_persona rxp where rxp.txtIdPersona = '" + strUsuario + "' and rxp.txtIdRol = 'DES'";
    strDatosDllo = GestionSQL.getFila(strSQL,"Nomina");
    
    if(strDatosDllo != null){
        strDllo="S";
    }
    
    strSQL = "select g.txtForm from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";                  
    strDatos = GestionSQL.getFila(strSQL,"Nomina");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/menu.css" />
        <link rel="stylesheet" type="text/css" href="Styles/visualizacion.css" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/admin.js"></script>          
        <script type="text/javascript" charset="UTF-8" src="Scripts/visualizacion.js"></script>  
        <title>Sistema de Gestión de Pagos a Contratistas - Administración</title>
        <script type="text/javascript">
            function disableKeyPress(evt){               
                var evt = (evt) ? evt : ((event) ? event : null); 
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
                if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
            }
        </script>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />  
       </header>
       <div align="center">
            <br>
            <table cellspacing="0" cellpadding="0" border="0" width="99%" >
                <tr>
                    <td style="width: 1080px;"></td>      
                    <td class="CELDAACCION" style="border-left: 1px solid #116043; text-align: right;width: 120px;"><input type="button" value="Salir" class="BOTONACCION" onclick="javascript:window.close();" /></td>
                </tr>
            </table>         
            <br>
           <nav>
                   <input type="hidden" name="txtTipoUsuario" id="txtTipoUsuario" value="<%=strDllo%>"/>
                   <table cellspacing="0" cellpadding="0" border="0" width="99%" >
                       <tr>
                           <td class="CELDAMENU" width="18%" >
                               <div id="dMenu" align="right">
                                   <table cellspacing="0" cellpadding="5" border="0" class="TABLAMENU" width="100%" >
                                       <tr><td class="TITULOMENU">MENÚ ADMINISTRATIVO</td></tr>               
                                       <% if (strDllo.equals("N")){ %>
                                           <tr><td class="ITEMMENU" id="itRolesXPersona" style="border-bottom:  1px solid #116043;"><a href="#">Roles por persona</a></td></tr>
                                           <tr><td class="ITEMMENU" id="itTiposC"><a href="#">Tipos de contrato</a></td></tr>
                                       <%}else{%>
                                           <tr><td class="ITEMMENU" id="itRoles" style="border-bottom:  1px solid #116043;"><a href="#">Roles</a></td></tr>           
                                           <tr><td class="ITEMMENU" id="itEstados" style="border-bottom:  1px solid #116043;"><a href="#">Estados del contrato</a></td></tr>               
                                           <tr><td class="ITEMMENU" id="itRolesXPersona" style="border-bottom:  1px solid #116043;"><a href="#">Roles por persona</a></td></tr>
                                           <tr><td class="ITEMMENU" id="itTiposC" style="border-bottom:  1px solid #116043;"><a href="#">Tipos de contrato</a></td></tr>                                                                        
                                           <%if (strDatos == null){%>
                                               <tr><td class="ITEMMENU"><a href="#"  onclick="window.open('general.jsp?txtAccion=C','Admin','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');">Crear configuración general</a></td></tr>
                                           <%}else{%>
                                               <tr><td class="ITEMMENU"><a href="#"  onclick="window.open('general.jsp?txtAccion=V','Admin','top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');">Consultar configuración general</a></td></tr>
                                           <%}%>
                                         <%}%>
                                   </table>
                               </div>
                           </td>
                           <td class="CELDAESPACIO" width="1%"></td>
                           <td class="CELDAMOSTRAR" style="width:80%;text-align: center;vertical-align: top;">
                               <div id="dMostrar">
                               </div>
                           </td>
                       </tr>
                   </table>  
           </nav>
        </div>
        <br>
       <footer>
            <div id="dFooter" class="FOOTER">
                <jsp:include page="footer.jsp" />          
            </div>
       </footer>
    </body>
</html>
