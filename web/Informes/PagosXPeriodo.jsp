<%-- 
    Document   : PagosXPeriodo
    Created on : 30-ene-2014, 9:31:32
    Author     : jorge.correa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel="stylesheet" type="text/css" href="../Styles/forms.css" />
        <link rel="stylesheet" type="text/css" href="../Styles/informes.css" />
        <link rel="stylesheet" type="text/css" href="../Styles/calendar-system.css" />       
        <link rel="stylesheet" type="text/css" href="../Styles/print.css" media="print" />
        <script type="text/javascript" src="../Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="../Scripts/JSCalendar.js"></script>
        <script type="text/javascript" src="../Scripts/JSCalendar-es.js"></script>
        <script type="text/javascript" src="../Scripts/JSCalendar-setup.js"></script>        
        <script type="text/javascript" charset="UTF-8" src="../Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="../Scripts/informes.js"></script>
        <script type="text/javascript" charset="UTF-8" src="../Scripts/comunes.js"></script>
        <title>Informe: Pagos por periodo</title>            
    </head>
    <body>
        <jsp:include page="headerInformes.jsp" />
        <div align="center">            
            <br><br>        
            <div id="dParametros">
                <form method="POST" action="" id="frmParametros" name="frmParametros">
                    <table cellspacing="0" cellpadding="0" width="980px" border="0" class="TABLAMAESTRO">                     
                        <tr>
                            <td colspan="6" class="TITULOFORM">PARÁMETROS PARA LA GENERACIÓN DEL INFORME</td>
                        </tr>
                        <tr><td colspan="6" style="height: 10px;"></td></tr>
                        <tr>                       
                            <td class="LABELFORM"><label for="txtFechaIni" id="lblFechaIni">* Fecha de inicio:<br />(aaaa-mm-dd)</label></td>
                            <td class="CELDARADIOFORM">
                                <input type="text" name="txtFechaIni" id="txtFechaIni" class="CAMPOFORM" style="width: 150px;" readOnly>&nbsp;<img src="../Images/Calendario.JPG" class="IMGCALENDAR" id="imgFechaIni">
                            </td>                               
                            <td class="CELDAIMGERROR" style="text-align: center;width: 70px;">
                                <img src="../Images/error.png" id="imgFechaInicio" alt="Campo obligatorio" class="IMGERROR">
                            </td>
                            <td class="LABELFORM"><label for="txtFechaFin" id="lblFechaFin">* Fecha de fin:<br />(aaaa-mm-dd)</label></td>
                            <td class="CELDARADIOFORM">
                                <input type="text" name="txtFechaFin" id="txtFechaFin" class="CAMPOFORM" style="width: 150px;" readOnly>&nbsp;<img src="../Images/Calendario.JPG" class="IMGCALENDAR" id="imgFechaFin">
                            </td>                               
                            <td class="CELDAIMGERROR" style="text-align: center;width: 70px;">
                                <img src="../Images/error.png" id="imgFechaFinal" alt="Campo obligatorio" class="IMGERROR">
                            </td>
                        </tr>
                        <tr><td colspan="6"  style="height: 10px;"></td></tr>
                        <tr>
                            <td colspan="6" class="CELDABOTONFORM">
                                <input type="button" name="btnGenerarPP" id="btnGenerarPP" value="Generar" class="BOTONFORM">&nbsp;
                                <input type="button" name="btnLimpiarPP" id="btnLimpiarPP" value="Limpiar" class="BOTONFORM">&nbsp;
                                <input type="button" name="btnImprimir" id="btnImprimir" value="Imprimir" class="BOTONFORM" style="display: none;">&nbsp;
                                <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();">
                            </td>                        
                        </tr>                 
                    </table>
                    <script language="javascript" type="text/javascript">
                        cargarCalendarios();
                    </script>
                </form>    
            </div>
            <div id="dVolver">
                <div class="noPrint">             
                    <input type="button" name="btnVolver" id="btnVolver" value="Volver" class="BOTONFORM"><br /><br /> 
                    <span class="TEXTOFALLO">                        
                        RECOMENDACIÓN: Para una mejor visualización, imprima la hoja en sentido horizontal desde las propiedades de la impresora.
                    </span><br />
                </div>
            </div>
            <br />          
            <div id="dMostrarInforme">                
            </div>
            <br />
        </div>        
        <jsp:include page="footerInformes.jsp" />
    </body>
</html>
