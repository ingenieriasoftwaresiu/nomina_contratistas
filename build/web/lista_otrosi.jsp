<%-- 
    Document   : lista_otrosi
    Created on : 31/01/2015, 12:44:34 PM
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="comprobarSesion.jsp"></jsp:include>
<%
    String strConsecutivo = (String) request.getParameter("txtConsecutivo");
    String strTipoUsuario = null;
    
    String strSQL="";
    String[] strTemp = null, strDatosContrato=null;
    Vector arrCodigos = new Vector();
    Vector arrAutores = new Vector();
    Vector arrFechasCreacion = new Vector();
    Vector arrFechasIniciales = new Vector();
    Vector arrFechasFinales = new Vector();
    Vector arrJustificaciones = new Vector();
    Vector arrRutas = new Vector();
    
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }
    
    strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
        
    strSQL = "SELECT txtIdEstado from nomina.tbl_contratos WHERE txtConsecutivo = '" + strConsecutivo + "'";
    strDatosContrato = GestionSQL.getFila(strSQL,"Nomina");

    strSQL = "select c.txtCodigo, p.txtNombreCompleto, c.dtFechaCreacion, c.dtFechaInicial, c.dtFechaFinal, c.txtJustificacion, c.txtRutaActa from nomina.tbl_otrosi_contratos c, users.users_personas p where (c.txtAutor = p.txtIdentificacion) and c.txtIdContrato = '" + strConsecutivo + "' ORDER BY c.txtCodigo DESC";
    Vector arrListado = GestionSQL.consultaSQL(strSQL, "Nomina", "7");
    
    if (arrListado != null){    
        for(int i=0;i<arrListado.size();i++){
            strTemp = arrListado.get(i).toString().split(">");
            arrCodigos.add(strTemp[0]);
            arrAutores.add(strTemp[1]);
            arrFechasCreacion.add(strTemp[2]);
            arrFechasIniciales.add(strTemp[3]);
            arrFechasFinales.add(strTemp[4]);
            arrJustificaciones.add(strTemp[5]);
            arrRutas.add(strTemp[6]);
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>  
        <script type="text/javascript" charset="UTF-8" src="Scripts/otrosi.js"></script>
        <title>Lista de OTROSI del contrato <%=strConsecutivo%></title>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />  
       </header>
       <section>            
            <div align="center">
                <br /><br />
                <%if(arrListado != null){%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA" style="width: 99%;">
                            <tr>
                                <td class="TITULOFORM">LISTADO DE OTROSI DEL CONTRATO <%=strConsecutivo%></td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmListadoOTROSI" name="frmListadoOTROSI">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmListadoOTROSI" />                                              
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO" style="width: 100%;">         
                                            <tr>                                   
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Nro.</td> 
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Autor</td>                                                                                               
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Fecha de creación<br/>(aaaa-mm-dd)</td>                 
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Fecha de inicial<br/>(aaaa-mm-dd)</td> 
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Fecha de final<br/>(aaaa-mm-dd)</td> 
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Acta</td> 
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Justificación</td>
                                                <%if(strTipoUsuario.equals("A")){%>
                                                    <%if((!(strDatosContrato[0].equals("FN"))) && (!(strDatosContrato[0].equals("CA")))){%>
                                                        <td class="SUBTITULOFORM">Editar</td>
                                                    <%}%>
                                               <%}%>
                                            </tr>                                                     
                                            <%for(int i=0;i<arrCodigos.size();i++){%>
                                                <tr>  
                                                    <td class="DATOREPORTE" style="text-align: center; vertical-align: middle;border-right: 1px solid #116043;">
                                                        <%=arrCodigos.get(i).toString()%>
                                                    </td>
                                                    <td class="DATOREPORTE" style="text-align: center; vertical-align: middle;border-right: 1px solid #116043;">
                                                        <%=arrAutores.get(i).toString()%>
                                                    </td>
                                                    <td class="DATOREPORTE" style="text-align: center; vertical-align: middle;;border-right: 1px solid #116043;">
                                                        <%=arrFechasCreacion.get(i).toString()%>
                                                    </td>
                                                    <td class="DATOREPORTE" style="text-align: center; vertical-align: middle;border-right: 1px solid #116043;">
                                                        <%=arrFechasIniciales.get(i).toString()%>
                                                    </td>
                                                    <td class="DATOREPORTE" style="text-align: center; vertical-align: middle;border-right: 1px solid #116043;">
                                                        <%=arrFechasFinales.get(i).toString()%>
                                                    </td>
                                                    <td class="DATOREPORTE" style="text-align: center; vertical-align: middle;border-right: 1px solid #116043;">
                                                        <input type="button" name="btnDescargar" id="btnDescargar" class="BOTONFORM" value="Descargar" onclick="descargarArchivo('<%=arrRutas.get(i).toString()%>');" />
                                                    </td>
                                                    <td class="DATOREPORTE" style="text-align: justify; vertical-align: middle;border-right: 1px solid #116043;">
                                                        <%=arrJustificaciones.get(i).toString()%>
                                                    </td>
                                                    <%if(strTipoUsuario.equals("A")){%>
                                                        <%if((!(strDatosContrato[0].equals("FN"))) && (!(strDatosContrato[0].equals("CA")))){%>
                                                            <td class="DATOREPORTE" style="text-align: center; vertical-align: middle;">
                                                                <input type="button" name="btnEditar" id="btnEditar" class="BOTONFORM" value="" style="width: 15px; height: 15px;" onclick="verOTROSI('<%=strConsecutivo%>','<%=arrCodigos.get(i).toString()%>')" />
                                                            </td>
                                                        <%}%>
                                                   <%}%>
                                                </tr> 
                                            <%}%>
                                            <tr>
                                                <td colspan="10" class="CELDABOTONFORM">
                                                    <input type="button" name="btnRefresh" id="btnRefresh" value="Actualizar" class="BOTONFORM" onclick="actualizar()" />&nbsp;&nbsp;
                                                    <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();" />
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>   
                        </table>
                     <%}else{%>       
                     <br />
                     <div class="TEXTOFALLO">
                         ESTE CONTRATO AÚN NO CUENTA CON OTROSI CREADOS.
                     </div>
                     <br />
                     <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();" />
                     <br /><br />
                     <%}%>
             </div>
       </section>
        <footer>                    
            <br />
            <jsp:include page="footer.jsp" />                     
        </footer>
    </body>
</html>
