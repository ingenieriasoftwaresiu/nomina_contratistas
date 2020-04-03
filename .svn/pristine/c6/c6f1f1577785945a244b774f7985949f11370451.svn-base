<%-- 
    Document   : cambiarPorcentaje
    Created on : 20-ene-2014, 16:01:08
    Author     : jorge.correa
--%>

<%@page import="Conexion.GestionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String strConsecutivo = request.getParameter("txtConsecutivo");
    String strCodigoPago = request.getParameter("txtCodigoPago");
    String strPorcAPagar = request.getParameter("txtPorcAPagar");    
    String strEvento = request.getParameter("txtEvento");
    String strTipoUsuario = null;
    String strSQL = "";
    double dblPorcAcum = 0;
    
     if (session.getAttribute("txtTipoUsuario") == null){
        request.getRequestDispatcher("cerrar.jsp").forward(request, response);
    }else{
        strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
    }     
    
     if (Integer.parseInt(strCodigoPago) > 1){
         strSQL = "select SUM(p.txtPorcPago) from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + strConsecutivo + "' and CAST(p.txtNumPago AS SIGNED) < " + strCodigoPago + "";
         String[] strDatosPago = GestionSQL.getFila(strSQL, "Nomina");
         dblPorcAcum = Math.rint(Double.parseDouble(strDatosPago[0]) * 100)/100;
     }else{
         dblPorcAcum = 0.00;
     }
         
 %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="Styles/forms.css" />
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/plan_pagos.js"></script>
        <script type="text/javascript" charset="UTF-8" src="Scripts/comunes.js"></script>
        <title>Sistema de Gestión de Pagos a Contratistas: Cambio de porcentaje</title>
    </head>
    <body onLoad="onLoad();">       
        <div align="center">
            <br />
            <table cellspacing="0" cellpadding="0" border="0" class="TABLACONTENEDORA" style="width: 450px;">
                <tr>
                    <td class="TITULOFORM">CAMBIO DE PORCENTAJE A PAGAR</td>
                </tr>                
                <tr>
                    <td>
                        <form method="POST" id="frmCambPorc" name="frmCambPorc">
                            <input type="hidden" name="txtForm" id="txtForm" value="frmCambPorc">     
                            <input type="hidden" name="txtConsecutivo" id="txtConsecutivo" value="<%=strConsecutivo%>">
                            <input type="hidden" name="txtNumPago" id="txtNumPago" value="<%=strCodigoPago%>">
                            <input type="hidden" name="txtEvento" id="txtEvento" value="<%=strEvento%>">
                            <table cellspacing="0" cellpadding="5" border="0" class="TABLAMAESTRO" style="width: 450px;">
                                <tr>                                    
                                    <td class="LABELFORM"><label for="txtPorcActual" id="lblPorcActual">% de pago actual:</label></td>
                                    <td class="CELDACAMPOFORM">
                                        <input type="text" name="txtPorcActual" id="txtPorcActual" class="CAMPOFORM" value="<%=strPorcAPagar%>" readOnly disabled="disabled">                                     
                                    </td>       
                                    <td class="CELDAIMGERROR">
                                        <img src="Images/error.png" id="imgPorcActual" alt="Campo obligatorio" class="IMGERROR">
                                    </td>
                                </tr>
                                <tr>                                    
                                    <td class="LABELFORM"><label for="txtPorcAcum" id="lblPorcAcum">% de pago acumulado:</label></td>
                                    <td class="CELDACAMPOFORM">
                                        <input type="text" name="txtPorcAcum" id="txtPorcAcum" class="CAMPOFORM" value="<%=dblPorcAcum%>" readOnly disabled="disabled">                                     
                                    </td>       
                                    <td class="CELDAIMGERROR">
                                        <img src="Images/error.png" id="imgPorcAcum" alt="Campo obligatorio" class="IMGERROR">
                                    </td>
                                </tr>
                                <tr>                                    
                                    <td class="LABELFORM"><label for="txtPorcNuevo" id="lblPorcNuevo">* % de pago nuevo:</label></td>
                                    <td class="CELDACAMPOFORM">
                                        <input type="text" name="txtPorcNuevo" id="txtPorcNuevo" class="CAMPOFORM">                                     
                                    </td>       
                                    <td class="CELDAIMGERROR">
                                        <img src="Images/error.png" id="imgPorcNuevo" alt="Campo obligatorio" class="IMGERROR">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="CELDABOTONFORM">
                                        <input type="button" value="Cambiar" id="btnCambiar" class="BOTONFORM">&nbsp;&nbsp;                                                    
                                        <input type="button" value="Salir" id="btnGuardar" class="BOTONFORM" onclick="javascript:window.close();">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
            <br />
            <div id="dMensaje">
            </div>
        </div>
    </body>
</html>
