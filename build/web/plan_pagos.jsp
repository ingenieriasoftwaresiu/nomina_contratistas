<%-- 
    Document   : plan_pagos
    Created on : 05-dic-2013, 10:52:45
    Author     : jorge.correa
--%>

<%@page import="Negocio.Comunes"%>
<%@page import="java.util.Vector"%>
<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="comprobarSesion.jsp"></jsp:include>
<!DOCTYPE html>
<%
    String strTipoUsuario = null;
    String strConsecutivo = request.getParameter("txtConsecutivo");
    String[] strTemp=null;
    String strSQL;
    Vector arrNumPagos = new Vector();
    Vector arrFechasPagos = new Vector();
    Vector arrPocPagos = new Vector();
    Vector arrValorSalud = new Vector();
    Vector arrValorPension = new Vector();
    Vector arrValorARL = new Vector();
    Vector arrEstados= new Vector();
    Vector arrSoportes = new Vector();    
    Vector arrFormatos = new Vector(); 
    Comunes comun = new Comunes();
    
    if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
    }
    
    strSQL = "select c.txtIdEstado from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
    String[] strDatosContrato = GestionSQL.getFila(strSQL, "Nomina");
    
    strSQL = "select p.txtNumPago, p.dtFechaPago, p.txtPorcPago, txtValorSalud, p.txtValorPension, p.txtValorARL, p.txtIdEstadoPago, p.txtRutaArchivo, p.txtRutaFormato from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' order by p.dtFechaPago";
    Vector arrPagos =GestionSQL.consultaSQL(strSQL, "Nomina", "9");
    
    if (arrPagos != null){    
        for(int i=0;i<arrPagos.size();i++){
            strTemp = arrPagos.get(i).toString().split(">");
            arrNumPagos.add(strTemp[0]);
            arrFechasPagos.add(strTemp[1]);
            arrPocPagos.add(strTemp[2]);
            arrValorSalud.add(strTemp[3]);
            arrValorPension.add(strTemp[4]);
            arrValorARL.add(strTemp[5]);
            arrEstados.add(strTemp[6]);
            arrSoportes.add(strTemp[7]);
            arrFormatos.add(strTemp[8]);
        }
    }    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <link rel="stylesheet" type="text/css" href="Styles/plan_pagos.css" />
        <link rel="stylesheet" type="text/css" href="Styles/print.css" media="print" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/plan_pagos.js"></script>
        <title>Plan de pagos del contrato #<%=strConsecutivo%></title>
    </head>
    <body>
        <div id="dNPrint">
            <header>
                <jsp:include page="header.jsp" />  
           </header>
           <section>
                <div align="center">
                    <br />                
                    <%if(arrPagos != null){%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA" style="width: 90%;">
                            <tr>
                                <td class="TITULOFORM">PLAN DE PAGOS DEL CONTRATO #<%=strConsecutivo%></td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmPlanPagos" name="frmPlanPagos">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmPlanPagos" />                                              
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO" style="width: 100%;">         
                                            <tr>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Nro. pago</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Fecha de pago<br/>(aaaa-mm-dd)</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">% a pagar</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Valor salud ($)</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Valor pensión ($)</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Valor ARL ($)</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">Estado del pago</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">¿Tiene soporte?</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;">¿Tiene formato?</td>
                                                <td class="SUBTITULOFORM">Acciones</td>                                        
                                            </tr>                
                                            <%
                                                double sumaPorc = 0;
                                                int sumaSalud = 0;
                                                int sumaPension = 0;
                                                int sumaARL = 0;
                                                double ftValorPorc = 0;
                                                int ftValorSalud = 0;
                                                int ftValorPension = 0;
                                                int ftValorARL = 0;
                                            %>
                                            <%for(int i=0;i<arrNumPagos.size();i++){%>
                                                <tr>
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=arrNumPagos.get(i)%></td>
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=arrFechasPagos.get(i)%></td>
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=arrPocPagos.get(i)%></td>
                                                    <%if(arrValorSalud.get(i).toString().equals("0")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">-</td>
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=comun.marcarMiles(arrValorSalud.get(i).toString())%></td>
                                                    <%}%>                      
                                                    <%if(arrValorPension.get(i).toString().equals("0")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">-</td>
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=comun.marcarMiles(arrValorPension.get(i).toString())%></td>
                                                    <%}%>
                                                    <%if(arrValorARL.get(i).toString().equals("0")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">-</td>
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=comun.marcarMiles(arrValorARL.get(i).toString())%></td>
                                                     <%}%>                                                
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=comun.validarEstado(arrEstados.get(i).toString())%></td>
                                                    <%if(arrSoportes.get(i).toString().equals("-")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">No</td>
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">Si</td>
                                                    <%}%>
                                                    <%if(arrFormatos.get(i).toString().equals("-")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">No</td>
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">Si</td>
                                                    <%}%>
                                                    <td class="DATOREPORTE" style="text-align: center;">
                                                        <a href="#" onclick="verDetalle('<%=strConsecutivo%>','<%=arrNumPagos.get(i).toString()%>');" style="text-decoration: underline;"><img src="Images/Ver_Detalle.png" alt="Ver detalle" title="Ver detalle" class="IMGACCION" /></a>&nbsp;&nbsp;&nbsp;
                                                                                                                
                                                        <%if(!(strDatosContrato[0].equals("FN")) && !(strDatosContrato[0].equals("CA"))){%>
                                                            <%--ACCIONES EN EL ESTADO PENDIENTE --%>
                                                            <%if(arrEstados.get(i).toString().equals("P")){%>
                                                                
                                                            <%}%>

                                                            <%--ACCIONES EN EL ESTADO PENDIENTE APROBACION --%>
                                                            <%if(arrEstados.get(i).toString().equals("PA")){%>
                                                                <%if(strTipoUsuario.equals("I")){%>
                                                                    <a href="#" onclick="preaprobarPago('<%=strConsecutivo%>','<%=arrNumPagos.get(i).toString()%>','PP');" style="text-decoration: underline;"><img src="Images/Aprobar.png" alt="Aprobar" title="Aprobar" class="IMGACCION" /></a>&nbsp;&nbsp;&nbsp;                                                                    
                                                                 <%}%>                                                                        
                                                            <%}%>
                                                                    
                                                           <%--ACCIONES EN EL ESTADO PREAPROBADO--%>
                                                            <%if(arrEstados.get(i).toString().equals("PRA")){%>
                                                                <%if(strTipoUsuario.equals("A")){%>
                                                                    <a href="#" onclick="aprobarPago('<%=strConsecutivo%>','<%=arrNumPagos.get(i).toString()%>','PP');" style="text-decoration: underline;"><img src="Images/Aprobar.png" alt="Aprobar" title="Aprobar" class="IMGACCION" /></a>&nbsp;&nbsp;&nbsp;                                                                    
                                                                <%}%>                                                     
                                                            <%}%>         
                                                                                                                            
                                                            <%if(strTipoUsuario.equals("A")){%>
                                                                    <%if(!(arrEstados.get(i).toString().equals("E"))){%>                
                                                                    <a href="#" onclick="cambiarPorcentaje('<%=strConsecutivo%>','<%=arrNumPagos.get(i).toString()%>','<%=arrPocPagos.get(i).toString()%>','PP');" style="text-decoration: underline;"><img src="Images/Cambiar_Porc.png" alt="Cambiar %" title="Cambiar %" class="IMGACCION" /></a>&nbsp;&nbsp;&nbsp;                                                                    
                                                                    <a href="#" onclick="ejecutarPago('<%=strConsecutivo%>','<%=arrNumPagos.get(i).toString()%>','PP');" style="text-decoration: underline;"><img src="Images/Ejecutar.png" alt="Poner ejecutado" title="Poner ejecutado" class="IMGACCION" /></a>&nbsp;&nbsp;&nbsp;
                                                                    <a href="#" onclick="eliminarPago('<%=strConsecutivo%>','<%=arrNumPagos.get(i).toString()%>');" style="text-decoration: underline;"><img src="Images/Eliminar.png" alt="Eliminar" title="Eliminar" class="IMGACCION" /></a>&nbsp;&nbsp;&nbsp;                                                                    
                                                                <%}%>                                                                
                                                            <%}%>                                                           
                                                       <%}%>         
                                                                    
                                                        <a href="#" onclick="verHistorico('<%=strConsecutivo%>','<%=arrNumPagos.get(i).toString()%>');" style="text-decoration: underline;"><img src="Images/historico.png" alt="Ver Histórico" title="Ver Histórico" class="IMGACCION" /></a>&nbsp;&nbsp;&nbsp;
                                                    </td>
                                                </tr>
                                                <% if((arrEstados.get(i).toString().equals("E"))){
                                                            ftValorPorc = Math.rint(Double.parseDouble(arrPocPagos.get(i).toString())*100)/100;
                                                            sumaPorc=sumaPorc+ftValorPorc;

                                                            ftValorSalud = Integer.parseInt(arrValorSalud.get(i).toString());
                                                            sumaSalud = sumaSalud + ftValorSalud;

                                                            ftValorPension = Integer.parseInt(arrValorPension.get(i).toString());
                                                            sumaPension = sumaPension + ftValorPension;

                                                            ftValorARL = Integer.parseInt(arrValorARL.get(i).toString());
                                                            sumaARL = sumaARL + ftValorARL;
                                                }%>
                                            <%}%>
                                            <tr>
                                                <td colspan="2" class="SUBTITULOFORM" style="border-bottom: 1px solid #116043;border-right:  1px solid #116043;">Total acumulado</td>
                                                <td class="TEXTONORMAL" style="font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;"><%=Math.rint(sumaPorc*10)/10%>%</td>
                                                <td class="TEXTONORMAL" style="font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;">$<%=comun.marcarMiles(String.valueOf(sumaSalud))%></td>
                                                <td class="TEXTONORMAL" style="font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;">$<%=comun.marcarMiles(String.valueOf(sumaPension))%></td>
                                                <td class="TEXTONORMAL" style="font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;">$<%=comun.marcarMiles(String.valueOf(sumaARL))%></td>
                                                <td colspan="2"></td>
                                            </tr>
                                            <tr>
                                                    <td colspan="10" class="CELDABOTONFORM">
                                                        <input type="button" name="btnRefrescar" id ="btnRefrescar" value="Actualizar datos" style="width: 100px;" onclick="refrescar('<%=strConsecutivo%>');" class="BOTONFORM" />&nbsp;&nbsp;
                                                        <input type="button" id="btnImprimir" value="Imprimir" class="BOTONFORM" />&nbsp;&nbsp;
                                                        <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();" />
                                                    </td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>   
                        </table>   
                        <br />
                        <div id="dLoader" class="TEXTOEXITO" style="display: none;">
                            <img src="Images/loader.gif" style="vertical-align: middle;width: 30px;height: 30px;"/>&nbsp;&nbsp;Procesando...
                        </div>
                    <%}else{%>
                    <br />
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class='TEXTOFALLO'>NO SE ENCONTRÓ UN PLAN DE PAGOS ASOCIADO AL CONTRATO #<%=strConsecutivo%>.</td>
                            </tr>
                            <tr><td style="height: 0px;"></td></tr>
                            <tr>
                                    <td class="CELDABOTONFORM">
                                        <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();" />
                                    </td>
                            </tr>
                        </table>
                    <%}%>
                    </div>
                    <div id="dMensaje">
                    </div>
                </section>
               <footer>        
                <div id="dFooter" class="FOOTER">
                    <jsp:include page="footer.jsp" />          
                </div>
            </footer>
        </div>
        <div id="dSPrint">
            <header>
                <jsp:include page="header_print.jsp" />  
           </header>
           <section>
                <div align="center">
                    <br /><br />                
                    <%if(arrPagos != null){%>
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class="TITULOFORM" style="border-right: 1px solid #116043;border-left: 1px solid #116043;border-top: 1px solid #116043;">PLAN DE PAGOS DEL CONTRATO #<%=strConsecutivo%></td>
                            </tr>                
                            <tr>
                                <td>
                                    <form method="POST" id="frmPlanPagos" name="frmPlanPagos">
                                        <input type="hidden" name="txtForm" id="txtForm" value="frmPlanPagos">                                   
                                        <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">         
                                            <tr>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;border-bottom: 1px solid #116043;">Nro. pago</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;border-bottom: 1px solid #116043;">Fecha de pago<br/>(aaaa-mm-dd)</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;border-bottom: 1px solid #116043;">% a pagar</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;border-bottom: 1px solid #116043;">Valor salud ($)</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;border-bottom: 1px solid #116043;">Valor pensión ($)</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;border-bottom: 1px solid #116043;">Valor ARL ($)</td>
                                                <td class="SUBTITULOFORM" style="border-right: 1px solid #116043;border-bottom: 1px solid #116043;">Estado del pago</td>                                                           
                                            </tr>                
                                            <%
                                                double sumaPorc = 0;
                                                int sumaSalud = 0;
                                                int sumaPension = 0;
                                                int sumaARL = 0;
                                                double ftValorPorc = 0;
                                                int ftValorSalud = 0;
                                                int ftValorPension = 0;
                                                int ftValorARL = 0;
                                            %>
                                            <%for(int i=0;i<arrNumPagos.size();i++){%>
                                                <tr>
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=arrNumPagos.get(i)%></td>
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=arrFechasPagos.get(i)%></td>
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=arrPocPagos.get(i)%></td>
                                                    <%if(arrValorSalud.get(i).toString().equals("0")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">-</td>
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=comun.marcarMiles(arrValorSalud.get(i).toString())%></td>
                                                    <%}%>                      
                                                    <%if(arrValorPension.get(i).toString().equals("0")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">-</td>
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=comun.marcarMiles(arrValorPension.get(i).toString())%></td>
                                                    <%}%>
                                                    <%if(arrValorARL.get(i).toString().equals("0")){%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043">-</td>
                                                    <%}else{%>
                                                        <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=comun.marcarMiles(arrValorARL.get(i).toString())%></td>
                                                     <%}%>                                                
                                                    <td class="DATOREPORTE" style="text-align: center;border-right: 1px solid #116043"><%=comun.validarEstado(arrEstados.get(i).toString())%></td>                                                    
                                                </tr>
                                                <% if((arrEstados.get(i).toString().equals("E"))){
                                                            ftValorPorc = comun.redondear(Double.parseDouble(arrPocPagos.get(i).toString()),0);
                                                            sumaPorc=sumaPorc+ftValorPorc;

                                                            ftValorSalud = Integer.parseInt(arrValorSalud.get(i).toString());
                                                            sumaSalud = sumaSalud + ftValorSalud;

                                                            ftValorPension = Integer.parseInt(arrValorPension.get(i).toString());
                                                            sumaPension = sumaPension + ftValorPension;

                                                            ftValorARL = Integer.parseInt(arrValorARL.get(i).toString());
                                                            sumaARL = sumaARL + ftValorARL;
                                                }%>
                                            <%}%>
                                            <tr>
                                                <td colspan="2" class="SUBTITULOFORM" style="border-bottom: 1px solid #116043;border-right:  1px solid #116043;">Total ejecutado</td>
                                                <td class="TEXTONORMAL" style="font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;"><%=sumaPorc%>%</td>
                                                <td class="TEXTONORMAL" style="font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;">$<%=comun.marcarMiles(String.valueOf(sumaSalud))%></td>
                                                <td class="TEXTONORMAL" style="font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;">$<%=comun.marcarMiles(String.valueOf(sumaPension))%></td>
                                                <td class="TEXTONORMAL" style="font-weight: bold;border-bottom: 1px solid #116043;border-right:  1px solid #116043;background-color: #EAEAEB;">$<%=comun.marcarMiles(String.valueOf(sumaARL))%></td>
                                                <td colspan="2"></td>
                                            </tr>
                                            <tr>
                                                    <td colspan="8" class="CELDABOTONFORM">
                                                        <div class="noPrint">
                                                            <input type="button" value="Volver" id="btnVolver" class="BOTONFORM" />
                                                       </div>
                                                    </td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>   
                        </table>   
                    <%}else{%>
                    <br />
                        <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                            <tr>
                                <td class='TEXTOFALLO'>NO SE ENCONTRÓ UN PLAN DE PAGOS ASOCIADO AL CONTRATO #<%=strConsecutivo%>.</td>
                            </tr>
                            <tr><td style="height: 0px;"></td></tr>
                            <tr>
                                    <td class="CELDABOTONFORM">
                                        <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();" />
                                    </td>
                            </tr>
                        </table>
                    <%}%>
                    </div>
                    <div id="dMensaje">
                    </div>
                </section>
               <footer>        
                <div id="dFooter" class="FOOTER">
                    <jsp:include page="footer_print.jsp" />          
                </div>
            </footer>            
        </div>            
    </body>
</html>
