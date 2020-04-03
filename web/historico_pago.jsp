<%-- 
    Document   : historico_pago
    Created on : 22/01/2015, 08:42:54 AM
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="comprobarSesion.jsp"></jsp:include>
<%
    String strConsecutivo = request.getParameter("txtConsecutivo");
    String strCodigoPago = request.getParameter("txtCodigoPago");
    String strSQL="";
    String[] strTemp = null;
    Vector arrIdsAutores = new Vector();
    Vector arrFechas = new Vector();
    Vector arrAcciones = new Vector();
    Vector arrEstados = new Vector();
    Vector arrObs = new Vector();
    
    strSQL = "select h.txtIdAutor, h.dtFecha, h.txtAccion, h.txtIdEstadoActual, h.txtObs from nomina.tbl_historico_pagos h where h.txtIdContrato = '" + strConsecutivo + "' and h.txtNumPago = '" + strCodigoPago + "' ORDER BY h.dtFecha DESC";
    Vector arrHistorico = GestionSQL.consultaSQL(strSQL, "Nomina", "5");
    
    if (arrHistorico != null){    
        for(int i=0;i<arrHistorico.size();i++){
            strTemp = arrHistorico.get(i).toString().split(">");
            arrIdsAutores.add(strTemp[0]);            
            arrFechas.add(strTemp[1].replace(".0", ""));
            arrAcciones.add(strTemp[2]);                   
            arrEstados.add(strTemp[3]);
            arrObs.add(strTemp[4]);
        }
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <title>Histórico de transacciones del pago <%=strCodigoPago%></title>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />  
       </header>
       <section>
                <div align="center">
                    <br /><br />                
                    <%if(arrHistorico != null){%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA" style="width: 95%;">
                            <tr>
                                <td class="TITULOFORM">HISTÓRICO DE TRANSACCIONES DEL PAGO # <%=strCodigoPago%></td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmHistoricoPago" name="frmHistoricoPago">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmHistoricoPago" />                                              
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO" style="width: 100%;">         
                                            <tr>                                   
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Fecha de registro<br/>(aaaa-mm-dd hh:mm:ss)</td> 
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Autor</td>                                                                                               
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Transacción</td>                 
                                                <td class="SUBTITULOFORM">Observación</td>
                                            </tr>                                                     
                                            <%for(int i=0;i<arrIdsAutores.size();i++){%>
                                                <tr>                    
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043;width: 15%;"><%=arrFechas.get(i).toString()%></td> 
                                                    <%if(arrIdsAutores.get(i).equals("A")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043;width: 10%;">Administrador</td>                                                                                                                
                                                    <%}%>
                                                    <%if(arrIdsAutores.get(i).equals("I")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043;width: 10%;">Interventor</td>
                                                    <%}%>
                                                    <%if(arrIdsAutores.get(i).equals("C")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043;width: 10%;">Contratista</td>
                                                    <%}%>                                                    
                                                    
                                                    <%if(arrAcciones.get(i).equals("GUARDAR")){%>
                                                        <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: Pendiente --> <b>Estado final</b>: Pendiente Aprobación</td>
                                                    <%}%>
                                                    
                                                    <%if(arrAcciones.get(i).equals("APROBAR")){%>
                                                        <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: PreAprobado --> <b>Estado final</b>: Aprobado</td>
                                                    <%}%>
                                                    
                                                    <%if(arrAcciones.get(i).equals("PREAPROBAR")){%>
                                                        <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: Pendiente Aprobación --> <b>Estado final</b>: PreAprobado</td>
                                                    <%}%>
                                                    
                                                    <%if(arrAcciones.get(i).equals("REPROCESARC")){%>
                                                        <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: PreAprobado --> <b>Estado final</b>: Pendiente Aprobación</td>
                                                    <%}%>
                                                    
                                                    <%if(arrAcciones.get(i).equals("REPROCESARI")){%>
                                                        <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: Pendiente Aprobación --> <b>Estado final</b>: Pendiente</td>
                                                    <%}%>
                                                    
                                                    <%if(arrAcciones.get(i).equals("EJECUTAR")){%>
                                                        <%if(arrEstados.get(i).equals("P")){%>
                                                            <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: Pendiente --> <b>Estado final</b>: Ejecutado</td>
                                                        <%}%>
                                                        
                                                        <%if(arrEstados.get(i).equals("PA")){%>
                                                            <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: Pendiente Aprobación --> <b>Estado final</b>: Ejecutado</td>
                                                        <%}%>
                                                        
                                                        <%if(arrEstados.get(i).equals("PRA")){%>
                                                            <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: PreAprobado --> <b>Estado final</b>: Ejecutado</td>
                                                        <%}%>
                                                        
                                                        <%if(arrEstados.get(i).equals("A")){%>
                                                            <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 30%;border-right: 1px solid #116043;"><b>Estado inicial</b>: Aprobado --> <b>Estado final</b>: Ejecutado</td>
                                                        <%}%>
                                                    <%}%>                                  
                                                    
                                                    <%if(arrObs.get(i).equals("-")){%>
                                                        <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 45%;">(Ninguna)</td> 
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: justify;text-justify: auto;width: 45%;"><%=arrObs.get(i).toString()%></td> 
                                                    <%}%>
                                                </tr>
                                            <%}%>
                                            <tr>
                                                <td colspan="10" class="CELDABOTONFORM">
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
                         ESTE PAGO AÚN NO CUENTA CON HISTÓRICO DE TRANSACCIONES.
                     </div>
                     <br />
                     <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();" />
                     <%}%>
                </div> 
            </section>
           <footer>        
            <div id="dFooter" class="FOOTER">
                <jsp:include page="footer.jsp" />          
            </div>
        </footer> 
    </body>
</html>
