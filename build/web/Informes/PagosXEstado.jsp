<%-- 
    Document   : PagosXEstado
    Created on : 24-ene-2014, 8:24:08
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
        <script type="text/javascript" src="../Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" charset="UTF-8" src="../Scripts/ajax.js"></script>
        <script type="text/javascript" charset="UTF-8" src="../Scripts/informes.js"></script>
        <title>Informe: Pagos por estado</title>
    </head>
    <body>
        <jsp:include page="headerInformes.jsp" />
        <div align="center">            
            <br><br>              
            <form method="POST" action="" id="frmParametros" name="frmParametros">
                <table cellspacing="0" cellpadding="0" width="980px" border="0" class="TABLAMAESTRO">                     
                    <tr>
                        <td colspan="3" class="TITULOFORM">PARÁMETROS PARA LA GENERACIÓN DEL INFORME</td>
                    </tr>
                    <tr><td style="height: 10px;"></td></tr>
                    <tr>                       
                        <td style="vertical-align: middle;text-align: left;width: 150px;">                  
                                <label for="txtIdEstado" class="LABELFORM">* Estado del pago:</label>&nbsp;&nbsp;  
                        </td>
                        <td class="CELDACAMPOFORM">
                                <select name="txtIdEstado" id="txtIdEstado" class="CAMPOFORM" style="width: 200px;">
                                    <option value="-1">Seleccione una opción</option>     
                                    <option value="*">--Todos los estados--</option>
                                    <option value="A">Aprobado</option>
                                    <option value="E">Ejecutado</option>
                                    <option value="P">Pendiente</option>
                                    <option value="PA">Pendiente Aprobación</option>    
                                    <option value="PRA">PreAprobado</option> 
                                </select>                                                              
                        </td>
                        <td style="vertical-align: middle;text-align: left;">
                            &nbsp;&nbsp;<img src="../Images/error.png" id="imgIdEstado" alt="Campo obligatorio" class="IMGERROR" style="vertical-align: bottom;">
                        </td>
                    </tr>
                    <tr><td style="height: 10px;"></td></tr>
                    <tr>
                        <td colspan="3" class="CELDABOTONFORM">
                            <input type="button" name="btnGenerar" id="btnGenerar" value="Generar" class="BOTONFORM">&nbsp;
                            <input type="button" name="btnLimpiar" id="btnLimpiar" value="Limpiar" class="BOTONFORM">&nbsp;
                            <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();">
                        </td>                        
                    </tr>                 
                </table>
            </form>                 
            <br>          
            <div id="dMostrarInforme">                
            </div>
            <br>
        </div>
        <jsp:include page="footerInformes.jsp" />
    </body>
</html>
