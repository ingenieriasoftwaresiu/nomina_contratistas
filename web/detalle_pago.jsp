<%-- 
    Document   : detalle_pago
    Created on : 06-dic-2013, 11:58:28
    Author     : jorge.correa
--%>

<%@page import="Negocio.Comunes"%>
<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="comprobarSesion.jsp"></jsp:include>
<!DOCTYPE html>
<%
    String strConsecutivo = request.getParameter("txtConsecutivo");
    String strCodigoPago = request.getParameter("txtCodigoPago");
    String strTipoUsuario = null, strJubilado="N", strIdContratista="";
    String strSQL = "", strCedula = null;
    Comunes comun = new Comunes();
    
     if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
        strCedula = (String) session.getAttribute("txtCedula");
    }     
     
    strSQL = "select p.txtNumPago, p.dtFechaPago, p.txtPorcPago, p.txtValorSalud, p.txtValorPension, p.txtValorARL, p.txtIdEstadoPago, p.txtRutaArchivo, p.txtRutaFormato from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' and p.txtNumPago = '" + strCodigoPago + "'";
    String[] strDatosPago = GestionSQL.getFila(strSQL, "Nomina");
      
    strSQL = "select c.txtDuracion, c.txtIdEstado, c.txtIdContratista from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
    String[] strDatosContrato = GestionSQL.getFila(strSQL, "Nomina");
    
    strSQL = "select p.txtNumPago from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' ORDER BY CAST(p.txtNumPago AS SIGNED) DESC limit 1";
    String[] strDatos = GestionSQL.getFila(strSQL, "Nomina");
    
    String[] strDatosContratista = null;
    strIdContratista = strDatosContrato[2];
    
    strSQL = "SELECT txtJubilado FROM nomina.tbl_contratistas WHERE txtNumId = '" + strIdContratista + "'";
    strDatosContratista = GestionSQL.getFila(strSQL,"Nomina");
    strJubilado = strDatosContratista[0];
    
 %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="Scripts/jquery.filestyle.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/plan_pagos.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>
        <title>Detalle de pago</title>
        <script type="text/javascript">
            window.onbeforeunload = cerrar;
            function cerrar(){
                opener.frmPlanPagos.btnRefrescar.click();
            }                   
        </script>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />  
       </header>
       <section>
            <div align="center">
                <br />
                 <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA">
                        <tr>
                            <td class="TITULOFORM">DETALLE DE PAGO</td>
                        </tr>                
                        <tr>
                            <td>
                                <form method="POST" id="frmDetallePago" name="frmDetallePago" enctype="multipart/form-data" action="DetallePago" onsubmit="return validar();">
                                    <input type="hidden" name="txtForm" id="txtForm" value="frmDetallePago">                                          
                                    <input type="hidden" name="txtConsecutivo" id="txtConsecutivo" value="<%=strConsecutivo%>" />
                                    <input type="hidden" name="txtRutaArchivo" id="txtRutaArchivo" value="<%=strDatosPago[7]%>" />
                                    <input type="hidden" name="txtRutaFormato" id="txtRutaFormato" value="<%=strDatosPago[8]%>" />
                                    <input type="hidden" name="txtIdEstado" id="txtIdEstado" value="<%=strDatosPago[6]%>" />
                                    <input type="hidden" name="txtDuracionContrato" id="txtDuracionContrato" value="<%=strDatosContrato[0]%>" />
                                    <input type="hidden" name="txtUltimoPago" id="txtUltimoPago" value="<%=strDatos[0]%>" />
                                    <input type="hidden" name="txtJubilado" id="txtJubilado" value="<%=strJubilado%>" />
                                    <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO">         
                                        <tr>                                    
                                            <td class="LABELFORM"><label for="txtNumPago" id="lblNumPago">Nro. de pago:</label></td>
                                            <td class="CELDACAMPOFORM">
                                                <input type="text" name="txtNumPago" id="txtNumPago" class="CAMPOFORM" value="<%=strDatosPago[0]%>" readOnly>                                     
                                            </td>       
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgNumPago" alt="Campo obligatorio" class="IMGERROR">
                                            </td>
                                            <td class="LABELFORM"><label for="txtFechaPago" id="lblFechaPago">Fecha de pago:<br />(aaaa-mm-dd)</label></td>
                                            <td class="CELDACAMPOFORM">
                                                <input type="text" name="txtFechaPago" id="txtFechaPago" class="CAMPOFORM" value="<%=strDatosPago[1]%>" readOnly disabled="disabled">                                     
                                            </td>                  
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgFechaPago" alt="Campo obligatorio" class="IMGERROR">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="LABELFORM"><label for="txtEstado" id="lblEstado">Estado:</label></td>
                                            <td class="CELDACAMPOFORM">
                                                <input type="text" name="txtEstado" id="txtEstado" class="CAMPOFORM" value="<%=comun.validarEstado(strDatosPago[6])%>" readOnly disabled="disabled">                                     
                                            </td> 
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgEstado" alt="Campo obligatorio" class="IMGERROR">
                                            </td>
                                            <td class="LABELFORM"><label for="txtPorcPagar" id="lblPorcPagar">% a pagar:</label></td>
                                            <td class="CELDACAMPOFORM">
                                                <input type="text" name="txtPorcPagar" id="txtPorcPagar" class="CAMPOFORM" value="<%=strDatosPago[2]%>" readOnly disabled="disabled">                                     
                                            </td>
                                            <td class="CELDAIMGERROR">
                                                <img src="Images/error.png" id="imgPorcPagar" alt="Campo obligatorio" class="IMGERROR">
                                            </td>
                                        </tr>                
                                        
                                        <%--ACCIONES EN EL ESTADO: PENDIENTE --%>
                                        
                                        <%if(strDatosPago[6].equals("P")){%>
                                            <%if(strTipoUsuario.equals("C")){%>
                                                <tr>                                                          
                                                    <td class="LABELFORM"><label for="txtValorSalud" id="lblValorSalud">* Valor salud ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorSalud" id="txtValorSalud" value="<%=strDatosPago[3]%>" class="CAMPOFORM">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorSalud" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <%if(strJubilado.equals("N")){%>
                                                        <td class="LABELFORM"><label for="txtValorPension" id="lblValorPension">* Valor pensión ($):</label></td>                                        
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtValorPension" id="lblValorPension">Valor pensión ($):</label></td>                                                        
                                                    <%}%>                                                    
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorPension" id="txtValorPension" value="<%=strDatosPago[4]%>" class="CAMPOFORM">      
                                                        <%if(strJubilado.equals("S")){%>
                                                            <br />    
                                                            <span class="MSGAVISOOBLG">[El contratista es Jubilado]</span>
                                                        <%}%> 
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorPension" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorARL" id="lblValorARL">* Valor ARL ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorARL" id="txtValorARL" value="<%=strDatosPago[5]%>" class="CAMPOFORM">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorARL" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <%if(strDatosPago[7].equals("-")){%>
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">* Soporte de pago:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="file" name="txtAdjunto" id="txtAdjunto" class="CAMPOFORM"><br />
                                                            <span class="MSGAVISOOBLG">[El soporte debe adjuntarse en archivo PDF]</span>              
                                                        </td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">* Soporte de pago:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="file" name="txtAdjunto" id="txtAdjunto" class="CAMPOFORM"><br />
                                                            <span class="MSGAVISOOBLG">[El soporte debe adjuntarse en archivo PDF]</span><br />
                                                            <span class="MSGAVISOOBLG">NOTA: Este pago ya tiene un soporte adjunto. Si desea sustituirlo, adjunte un nuevo soporte.</span>
                                                        </td>
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgAdjunto" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>       
                                                <tr>
                                                    <%if(strDatosPago[8].equals("-")){%>                              
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">* Formato de interventoría:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="file" name="txtFormato" id="txtFormato" class="CAMPOFORM"><br />
                                                            <span class="MSGAVISOOBLG">[El formato debe adjuntarse en archivo PDF]</span>              
                                                        </td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">* Formato de interventoría:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="file" name="txtFormato" id="txtFormato" class="CAMPOFORM"><br />
                                                            <span class="MSGAVISOOBLG">[El formato debe adjuntarse en archivo PDF]</span><br />
                                                            <span class="MSGAVISOOBLG">NOTA: Este pago ya tiene un formato adjunto. Si desea sustituirlo, adjunte un nuevo formato.</span>
                                                        </td>                                                
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFormato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>
                                                <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>
                                                    <tr>
                                                        <td class="LABELFORM"><label for="txtObs" id="lblObs">Observación:</label></td>
                                                        <td class="CELDACAMPOFORM" colspan="4">
                                                            <input type="text" name="txtObs" id="txtObs" class="CAMPOFORM" style="width: 650px;" />
                                                        </td>
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgObs" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                    </tr>
                                                <%}%>
                                                <tr>
                                                    <td colspan="6" class="CELDABOTONFORM">
                                                        <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>
                                                            <input type="submit" value="Guardar" id="btnGuardar" class="BOTONFORM">&nbsp;&nbsp;                               
                                                        <%}%>
                                                        <input type="button" value="Ver Histórico" id="btnVerHistorico" class="BOTONFORM" onclick="verHistorico('<%=strConsecutivo%>','<%=strCodigoPago%>');" style="width: 100px;">&nbsp;&nbsp;
                                                        <input type="button" value="Salir" id="btnSalir" class="BOTONFORM" onclick="javascript:window.close();">
                                                    </td>
                                                </tr>                                                
                                            <%}else{%>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorSalud" id="lblValorSalud">Valor salud ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorSalud" id="txtValorSalud" value="<%=strDatosPago[3]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorSalud" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtValorPension" id="lblValorPension">Valor pensión ($):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorPension" id="txtValorPension" value="<%=strDatosPago[4]%>" class="CAMPOFORM" readOnly disabled="disabled">      
                                                        <%if(strJubilado.equals("S")){%>
                                                            <br />    
                                                            <span class="MSGAVISOOBLG">[El contratista es Jubilado]</span>
                                                        <%}%>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorPension" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorARL" id="lblValorARL">Valor ARL ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorARL" id="txtValorARL" value="<%=strDatosPago[5]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorARL" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <%if(strDatosPago[7].equals("-")){%>
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>                                        
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene soporte adjunto por parte del Contratista.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[7]%>');" />                                                            
                                                        </td>
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgAdjunto" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr> 
                                                <tr>
                                                    <%if(strDatosPago[8].equals("-")){%>
                                                        <td class="LABELFORM"><label for="txtFormato" id="txtFormato">Formato adjunto:</label></td>                                        
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene formato adjunto por parte del Contratista.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtFormato" id="txtFormato">Formato adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[8]%>');" />                                                            
                                                        </td>
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgAdjunto" alt="Campo obligatorio" class="IMGERROR">
                                                    </td> 
                                                </tr>
                                                <tr>
                                                    <td colspan="6" class="CELDABOTONFORM">   
                                                        <%if(strTipoUsuario.equals("A")){%>
                                                            <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>                                                            
                                                                <input type="button" value="Cambiar % a pagar" id="btnCambiarPorc" class="BOTONFORM" onclick="cambiarPorcentaje('<%=strConsecutivo%>','<%=strCodigoPago%>','<%=strDatosPago[2]%>','DD');" style="width: 115px;">&nbsp;&nbsp;
                                                                <input type="button" value="Poner ejecutado" id="btnEjecutar" class="BOTONFORM" onclick="ejecutarPago('<%=strConsecutivo%>','<%=strCodigoPago%>','DD');" style="width: 100px;">&nbsp;&nbsp;                                                                
                                                            <%}%>                                                            
                                                        <%}%>                                                        
                                                        <input type="button" value="Ver Histórico" id="btnVerHistorico" class="BOTONFORM" onclick="verHistorico('<%=strConsecutivo%>','<%=strCodigoPago%>');" style="width: 100px;">&nbsp;&nbsp;
                                                        <input type="button" value="Salir" id="btnSalir" class="BOTONFORM" onclick="javascript:window.close();">
                                                    </td>
                                                </tr>
                                            <%}%> 
                                        <%}%>
                                        
                                        <%--ACCIONES EN EL ESTADO: PENDIENTE APROBACIÓN --%>
                                        
                                        <%if(strDatosPago[6].equals("PA")){%>
                                            <%if(strTipoUsuario.equals("I")){%>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorSalud" id="lblValorSalud">Valor salud ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorSalud" id="txtValorSalud" value="<%=strDatosPago[3]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorSalud" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtValorPension" id="lblValorPension">Valor pensión ($):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorPension" id="txtValorPension" value="<%=strDatosPago[4]%>" class="CAMPOFORM" readOnly disabled="disabled">             
                                                        <%if(strJubilado.equals("S")){%>
                                                            <br />    
                                                            <span class="MSGAVISOOBLG">[El contratista es Jubilado]</span>
                                                        <%}%>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorPension" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorARL" id="lblValorARL">Valor ARL ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorARL" id="txtValorARL" value="<%=strDatosPago[5]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorARL" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <%if(strDatosPago[7].equals("-")){%>     
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene archivo adjunto, ya que se trata del último pago del contrato.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[7]%>');" />                                                            
                                                        </td>
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgAdjunto" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>
                                                    <%if(strDatosPago[8].equals("-")){%>                              
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene formato adjunto, ya que se trata del último pago del contrato.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[8]%>');" />
                                                        </td>                                                
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFormato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>   
                                                <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>
                                                    <tr>
                                                        <td class="LABELFORM"><label for="txtObs" id="lblObs">Observación:</label></td>
                                                        <td class="CELDACAMPOFORM" colspan="4">
                                                            <input type="text" name="txtObs" id="txtObs" class="CAMPOFORM" style="width: 650px;" />
                                                        </td>
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgObs" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                    </tr>
                                                <%}%>
                                                <tr>
                                                    <td colspan="6" class="CELDABOTONFORM">              
                                                        <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>
                                                            <input type="button" value="Aprobar" id="btnGuardar" class="BOTONFORM" onclick="preaprobarPago('<%=strConsecutivo%>','<%=strCodigoPago%>','DD')">&nbsp;&nbsp;                                                         
                                                            <input type="button" value="Reprocesar" id="btnReprocesar" class="BOTONFORM" onclick="reprocesarPagoI('<%=strConsecutivo%>','<%=strCodigoPago%>','DD')">&nbsp;&nbsp;
                                                        <%}%>
                                                        <input type="button" value="Ver Histórico" id="btnVerHistorico" class="BOTONFORM" onclick="verHistorico('<%=strConsecutivo%>','<%=strCodigoPago%>');" style="width: 100px;">&nbsp;&nbsp;
                                                        <input type="button" value="Salir" id="btnSalir" class="BOTONFORM" onclick="javascript:window.close();">
                                                    </td>
                                                </tr>
                                            <%}else{%>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorSalud" id="lblValorSalud">Valor salud ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorSalud" id="txtValorSalud" value="<%=strDatosPago[3]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorSalud" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtValorPension" id="lblValorPension">Valor pensión ($):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorPension" id="txtValorPension" value="<%=strDatosPago[4]%>" class="CAMPOFORM" readOnly disabled="disabled">       
                                                        <%if(strJubilado.equals("S")){%>
                                                            <br />    
                                                            <span class="MSGAVISOOBLG">[El contratista es Jubilado]</span>
                                                        <%}%>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorPension" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorARL" id="lblValorARL">Valor ARL ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorARL" id="txtValorARL" value="<%=strDatosPago[5]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorARL" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <%if(strDatosPago[7].equals("-")){%>     
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene archivo adjunto, ya que se trata del último pago del contrato.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[7]%>');" />                                                            
                                                        </td>
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgAdjunto" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>
                                                    <%if(strDatosPago[8].equals("-")){%>                              
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene archivo formato, ya que se trata del último pago del contrato.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[8]%>');" />
                                                        </td>                                                
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFormato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>                                                
                                                <tr>
                                                    <td colspan="6" class="CELDABOTONFORM"> 
                                                        <%if(strTipoUsuario.equals("A")){%>
                                                            <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>                                                            
                                                                <input type="button" value="Cambiar % a pagar" id="btnCambiarPorc" class="BOTONFORM" onclick="cambiarPorcentaje('<%=strConsecutivo%>','<%=strCodigoPago%>','<%=strDatosPago[2]%>','DD');" style="width: 115px;">&nbsp;&nbsp;
                                                                <input type="button" value="Poner ejecutado" id="btnEjecutar" class="BOTONFORM" onclick="ejecutarPago('<%=strConsecutivo%>','<%=strCodigoPago%>','DD');" style="width: 100px;">&nbsp;&nbsp;                                                                
                                                            <%}%>                                                            
                                                        <%}%>
                                                        <input type="button" value="Ver Histórico" id="btnVerHistorico" class="BOTONFORM" onclick="verHistorico('<%=strConsecutivo%>','<%=strCodigoPago%>');" style="width: 100px;">&nbsp;&nbsp;
                                                        <input type="button" value="Salir" id="btnSalir" class="BOTONFORM" onclick="javascript:window.close();">
                                                    </td>
                                                </tr>                                            
                                            <%}%>
                                        <%}%>                                                         
                                        
                                        <%--ACCIONES EN EL ESTADO: PREAPROBADO --%>
                                        
                                        <%if(strDatosPago[6].equals("PRA")){%>
                                            <%if(strTipoUsuario.equals("A")){%>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorSalud" id="lblValorSalud">Valor salud ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorSalud" id="txtValorSalud" value="<%=strDatosPago[3]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorSalud" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtValorPension" id="lblValorPension">Valor pensión ($):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorPension" id="txtValorPension" value="<%=strDatosPago[4]%>" class="CAMPOFORM" readOnly disabled="disabled">              
                                                        <%if(strJubilado.equals("S")){%>
                                                            <br />    
                                                            <span class="MSGAVISOOBLG">[El contratista es Jubilado]</span>
                                                        <%}%>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorPension" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorARL" id="lblValorARL">Valor ARL ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorARL" id="txtValorARL" value="<%=strDatosPago[5]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorARL" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <%if(strDatosPago[7].equals("-")){%>     
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene archivo adjunto, ya que se trata del último pago del contrato.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[7]%>');" />                                                            
                                                        </td>
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgAdjunto" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>
                                                    <%if(strDatosPago[8].equals("-")){%>                              
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene formato adjunto, ya que se trata del último pago del contrato.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[8]%>');" />
                                                        </td>                                                
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFormato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>   
                                                <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>
                                                    <tr>
                                                        <td class="LABELFORM"><label for="txtObs" id="lblObs">Observación:</label></td>
                                                        <td class="CELDACAMPOFORM" colspan="4">
                                                            <input type="text" name="txtObs" id="txtObs" class="CAMPOFORM" style="width: 650px;" />
                                                        </td>
                                                        <td class="CELDAIMGERROR">
                                                            <img src="Images/error.png" id="imgObs" alt="Campo obligatorio" class="IMGERROR">
                                                        </td>
                                                    </tr>
                                                <%}%>
                                                <tr>
                                                    <td colspan="6" class="CELDABOTONFORM">              
                                                        <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>
                                                            <input type="button" value="Aprobar" id="btnGuardar" class="BOTONFORM" onclick="aprobarPago('<%=strConsecutivo%>','<%=strCodigoPago%>','DD')">&nbsp;&nbsp;                                                         
                                                            <input type="button" value="Reprocesar" id="btnReprocesar" class="BOTONFORM" onclick="reprocesarPagoC('<%=strConsecutivo%>','<%=strCodigoPago%>','DD')">&nbsp;&nbsp;
                                                            <input type="button" value="Cambiar % a pagar" id="btnCambiarPorc" class="BOTONFORM" onclick="cambiarPorcentaje('<%=strConsecutivo%>','<%=strCodigoPago%>','<%=strDatosPago[2]%>','DD');" style="width: 115px;">&nbsp;&nbsp;
                                                            <input type="button" value="Poner ejecutado" id="btnEjecutar" class="BOTONFORM" onclick="ejecutarPago('<%=strConsecutivo%>','<%=strCodigoPago%>','DD');" style="width: 100px;">&nbsp;&nbsp;
                                                        <%}%>
                                                        <input type="button" value="Ver Histórico" id="btnVerHistorico" class="BOTONFORM" onclick="verHistorico('<%=strConsecutivo%>','<%=strCodigoPago%>');" style="width: 100px;">&nbsp;&nbsp;                                                        
                                                        <input type="button" value="Salir" id="btnSalir" class="BOTONFORM" onclick="javascript:window.close();">
                                                    </td>
                                                </tr>
                                            <%}else{%>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorSalud" id="lblValorSalud">Valor salud ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorSalud" id="txtValorSalud" value="<%=strDatosPago[3]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorSalud" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <td class="LABELFORM"><label for="txtValorPension" id="lblValorPension">Valor pensión ($):</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorPension" id="txtValorPension" value="<%=strDatosPago[4]%>" class="CAMPOFORM" readOnly disabled="disabled">   
                                                        <%if(strJubilado.equals("S")){%>
                                                            <br />    
                                                            <span class="MSGAVISOOBLG">[El contratista es Jubilado]</span>
                                                        <%}%>
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorPension" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>                                       
                                                    <td class="LABELFORM"><label for="txtValorARL" id="lblValorARL">Valor ARL ($):</label></td>
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="text" name="txtValorARL" id="txtValorARL" value="<%=strDatosPago[5]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                    </td>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgValorARL" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                    <%if(strDatosPago[7].equals("-")){%>     
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene archivo adjunto, ya que se trata del último pago del contrato.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[7]%>');" />                                                            
                                                        </td>
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgAdjunto" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>                                           
                                                </tr>
                                                <tr>
                                                    <%if(strDatosPago[8].equals("-")){%>                              
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                        <td class="MSGAVISOOBLG">NOTA: No se tiene archivo formato, ya que se trata del último pago del contrato.</td>
                                                    <%}else{%>
                                                        <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                        <td class="CELDACAMPOFORM">
                                                            <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[8]%>');" />
                                                        </td>                                                
                                                    <%}%>
                                                    <td class="CELDAIMGERROR">
                                                        <img src="Images/error.png" id="imgFormato" alt="Campo obligatorio" class="IMGERROR">
                                                    </td>
                                                </tr>                                                
                                                <tr>
                                                    <td colspan="6" class="CELDABOTONFORM">                                                         
                                                        <input type="button" value="Ver Histórico" id="btnVerHistorico" class="BOTONFORM" onclick="verHistorico('<%=strConsecutivo%>','<%=strCodigoPago%>');" style="width: 100px;">&nbsp;&nbsp;
                                                        <input type="button" value="Salir" id="btnSalir" class="BOTONFORM" onclick="javascript:window.close();">
                                                    </td>
                                                </tr>                                            
                                            <%}%>
                                        <%}%>
                                        
                                        <%--ACCIONES EN EL ESTADO: APROBADO / EJECUTADO --%>
                                       
                                        <%if((strDatosPago[6].equals("A")) || (strDatosPago[6].equals("E"))){%>
                                            <tr>                                       
                                                <td class="LABELFORM"><label for="txtValorSalud" id="lblValorSalud">Valor salud ($):</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtValorSalud" id="txtValorSalud" value="<%=strDatosPago[3]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgValorSalud" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <td class="LABELFORM"><label for="txtValorPension" id="lblValorPension">Valor pensión ($):</label></td>                                        
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtValorPension" id="txtValorPension" value="<%=strDatosPago[4]%>" class="CAMPOFORM" readOnly disabled="disabled">   
                                                    <%if(strJubilado.equals("S")){%>
                                                        <br />    
                                                        <span class="MSGAVISOOBLG">[El contratista es Jubilado]</span>
                                                    <%}%>
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgValorPension" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                           
                                            </tr>
                                            <tr>                                       
                                                <td class="LABELFORM"><label for="txtValorARL" id="lblValorARL">Valor ARL ($):</label></td>
                                                <td class="CELDACAMPOFORM">
                                                    <input type="text" name="txtValorARL" id="txtValorARL" value="<%=strDatosPago[5]%>" class="CAMPOFORM" readOnly disabled="disabled">                                     
                                                </td>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgValorARL" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                                <%if(strDatosPago[7].equals("-")){%>     
                                                    <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>
                                                    <td class="MSGAVISOOBLG">NOTA: No se tiene archivo adjunto por parte del Contratista.</td>
                                                <%}else{%>
                                                    <td class="LABELFORM"><label for="txtAdjunto" id="lblAdjunto">Soporte adjunto:</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[7]%>');" />                                                            
                                                    </td>
                                                <%}%>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgAdjunto" alt="Campo obligatorio" class="IMGERROR">
                                                </td>                                           
                                            </tr>
                                            <tr>
                                                <%if(strDatosPago[8].equals("-")){%>                              
                                                    <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                    <td class="MSGAVISOOBLG">NOTA: No se tiene formato adjunto por parte del Contratista.</td>
                                                <%}else{%>
                                                    <td class="LABELFORM"><label for="txtFormato" id="lblFormato">Formato adjunto:</label></td>                                        
                                                    <td class="CELDACAMPOFORM">
                                                        <input type="button" value="Descargar" id="btnDescargar" class="BOTONFORM" onclick="descargarArchivo('<%=strDatosPago[8]%>');" />
                                                    </td>                                                
                                                <%}%>
                                                <td class="CELDAIMGERROR">
                                                    <img src="Images/error.png" id="imgFormato" alt="Campo obligatorio" class="IMGERROR">
                                                </td>
                                            </tr>                        
                                            <%if(strTipoUsuario.equals("A")){%>
                                                <%if(strDatosPago[6].equals("A")){%>
                                                        <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>
                                                            <tr>
                                                                <td class="LABELFORM"><label for="txtObs" id="lblObs">Observación:</label></td>
                                                                <td class="CELDACAMPOFORM" colspan="4">
                                                                    <input type="text" name="txtObs" id="txtObs" class="CAMPOFORM" style="width: 650px;" />
                                                                </td>
                                                                <td class="CELDAIMGERROR">
                                                                    <img src="Images/error.png" id="imgObs" alt="Campo obligatorio" class="IMGERROR">
                                                                </td>
                                                            </tr>
                                                        <%}%>
                                                    <%}%>
                                            <%}%>
                                            <tr>
                                                <td colspan="6" class="CELDABOTONFORM">  
                                                    <%if(strTipoUsuario.equals("A")){%> 
                                                        <%if(!(strDatosContrato[1].equals("FN")) && !(strDatosContrato[1].equals("CA"))){%>
                                                            <%if(!(strDatosPago[6].equals("E"))){%>                                                                                                              
                                                                    <input type="button" value="Poner ejecutado" id="btnEjecutar" class="BOTONFORM" onclick="ejecutarPago('<%=strConsecutivo%>','<%=strCodigoPago%>','DD');" style="width: 100px;">&nbsp;&nbsp;                                                                    
                                                            <%}%>
                                                        <%}%>                                                        
                                                     <%}%>
                                                     <input type="button" value="Ver Histórico" id="btnVerHistorico" class="BOTONFORM" onclick="verHistorico('<%=strConsecutivo%>','<%=strCodigoPago%>');" style="width: 100px;">&nbsp;&nbsp;
                                                    <input type="button" value="Salir" id="btnSalir" class="BOTONFORM" onclick="javascript:window.close();">
                                                </td>
                                            </tr>                                       
                                        <%}%>                                           
                                        <tr><td colspan="6" class="MSGAVISOOBLG">Los campos marcados con (*) son obligatorios.</td></tr>
                                    </table>
                                </form>
                            </td>
                        </tr>
                 </table>
            </div>
            <br />
            <div id="dLoader" class="TEXTOEXITO" style="display: none;">
                <img src="Images/loader.gif" style="vertical-align: middle;width: 30px;height: 30px;"/>&nbsp;&nbsp;Procesando...
            </div>
            <div id="dMensaje">
            </div>                        
        </section>
       <footer>        
        <div id="dFooter" class="FOOTER">
            <jsp:include page="footer.jsp" />          
        </div>
       </footer>
    </body>
</html>
