/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){    
    $("#dNPrint").show();
    $("#dSPrint").hide();
    $(".IMGERROR").hide();
    $("#dMensaje").empty();             
}

function onLoad(){
    $("#txtPorcNuevo").focus();
}

function validar(){    
    
    var strDuracionContrato = $("#txtDuracionContrato").val();
    var strUltimoPago = $("#txtUltimoPago").val();
    var strPagoActual = $("#txtNumPago").val();
    var strIdEstado = $("#txtIdEstado").val();
    var strJubilado = $("#txtJubilado").val();
    var strRutaArchivo = "";
    var strAdjunto = "";
        
    if(strIdEstado == "P"){
        if(((parseInt(strDuracionContrato) > 30) || ((parseInt(strDuracionContrato) == 30) && (parseInt(strUltimoPago) > 1)))){
            if(strPagoActual != strUltimoPago){
                
                var strValorSalud = $("#txtValorSalud").val();
                                                   
                if ((strValorSalud == "0") || (strValorSalud == "")){
                    $("#imgValorSalud").show();
                    $("#txtValorSalud").focus();
                    return false;
                }else{
                    $("#txtValorSalud").val(replaceCadena(strValorSalud,".",""));
                    strValorSalud = $("#txtValorSalud").val();
                    if (!(validarNumero(strValorSalud))){                
                        $("#imgValorSalud").show();
                        alert("ADVERTENCIA: El valor Salud debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                        $("#txtValorSalud").focus();
                         return false;
                    }else{
                        $("#imgValorSalud").hide();
                    }
                }
               
                var strValorPension = $("#txtValorPension").val();
                
                if (strJubilado == "N"){ 
                    if ((strValorPension == "0") || (strValorPension == "")){
                        $("#imgValorPension").show();
                        $("#txtValorPension").focus();
                        return false;
                    }else{
                        $("#txtValorPension").val(replaceCadena(strValorPension,".",""));
                        strValorPension = $("#txtValorPension").val();
                        if (!(validarNumero(strValorPension))){                
                            $("#imgValorPension").show();
                            alert("ADVERTENCIA: El valor Pensión debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                            $("#txtValorPension").focus();
                             return false;
                        }else{
                            $("#imgValorPension").hide();
                        }
                    }
                }else{
                    $("#txtValorPension").val("0");
                }

                var strValorARL = $("#txtValorARL").val();

                if ((strValorARL == "0") || (strValorARL == "")){
                    $("#imgValorARL").show();
                    $("#txtValorARL").focus();
                    return false;
                }else{
                    $("#txtValorARL").val(replaceCadena(strValorARL,".",""));
                    strValorARL = $("#txtValorARL").val();
                    if (!(validarNumero(strValorARL))){                
                        $("#imgValorARL").show();
                        alert("ADVERTENCIA: El valor ARL debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                        $("#txtValorARL").focus();
                         return false;
                    }else{
                        $("#imgValorARL").hide();
                    }
                }

                strAdjunto = $("#txtAdjunto").val();
                strRutaArchivo = $("#txtRutaArchivo").val();

                if ((strAdjunto == "") && (strRutaArchivo == "-")){
                    $("#imgAdjunto").show();
                    $("#txtAdjunto").focus();
                    return false;
                }else{        
                    if (strRutaArchivo == "-"){
                        var extension = (strAdjunto.substring(strAdjunto.lastIndexOf("."))).toLowerCase();         
                        if (extension != ".pdf"){
                            $("#imgAdjunto").show();
                            alert("El soporte debe adjuntarse en archivo PDF (.pdf).");
                            return false;
                        }else{
                            $("#imgAdjunto").hide();
                        }
                    }else{
                        $("#imgAdjunto").hide();
                    }
                }
                
                var strRutaFormato = "";
                var strFormato = "";
                
                strFormato = $("#txtFormato").val();
                strRutaFormato = $("#txtRutaFormato").val();

                if ((strFormato == "") && (strRutaFormato == "-")){
                    $("#imgFormato").show();
                    $("#txtFormato").focus();
                    return false;
                }else{        
                    if (strRutaFormato == "-"){
                        var extension = (strFormato.substring(strFormato.lastIndexOf("."))).toLowerCase();         
                        if (extension != ".pdf"){
                            $("#imgFormato").show();
                            alert("El formato debe adjuntarse en archivo PDF (.pdf).");
                            return false;
                        }else{
                            $("#imgFormato").hide();
                        }
                    }else{
                        $("#imgFormato").hide();
                    }
                }
            }else{
                strRutaArchivo = "-";
                strAdjunto = "";
                strRutaFormato = "-";
                strFormato = "";
            }        
        }else{        
            strRutaArchivo = "-";
            strAdjunto = "";      
            strRutaFormato = "-";
            strFormato = "";
        }
    }             
    $("#dLoader").show();   
}

function cambiarPorcentaje(strConsecutivo,strCodigoPago, strPorc, strEvento){
    window.open("cambiarPorcentaje.jsp?txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" +strCodigoPago + "&txtPorcAPagar=" +strPorc + "&txtEvento=" +strEvento,"Cambiar_Porc",'top='+(((screen.availHeight)/2)-150)+',left='+(((screen.availWidth)/2)-340)+',width=650px,height=290px,toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=0,scrolling=1,scrollbars=yes');
}

function verDetalle(strConsecutivo,strCodigoPago){
    window.open("detalle_pago.jsp?txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" +strCodigoPago,"Detalle_Pago",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function verObs(strConsecutivo,strCodigoPago){
    window.open("obsXpago.jsp?txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" +strCodigoPago,"Obs_Pago",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function verHistorico(strConsecutivo,strCodigoPago){
    window.open("historico_pago.jsp?txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" +strCodigoPago,"Historico_Pago",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function aprobarPago(strConsecutivo,strCodigoPago,strEvento){
    
    if(confirm("¿Está seguro que desea aprobar el pago?")){
        var strAccion = "APROBAR";  
        var strObs="-";
                
        if(strEvento == "DD"){
            strObs= $("#txtObs").val();            
        }
        
        var dataString = "txtAccion=" + strAccion + "&txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" + strCodigoPago + "&txtEvento=" + strEvento + "&txtObs=" + strObs;          

        // Envío de petición AJAX.
        
        $("#dLoader").show();
        AJAX("POST","Pagos",dataString,"dMensaje");    
      
    }     
}

function preaprobarPago(strConsecutivo,strCodigoPago,strEvento){
    
    if(confirm("¿Está seguro que desea aprobar el pago?")){
        var strAccion = "PREAPROBAR";  
        var strObs="-";
        
        if(strEvento == "DD"){
            strObs= $("#txtObs").val();                   
        }
        
        var dataString = "txtAccion=" + strAccion + "&txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" + strCodigoPago + "&txtEvento=" + strEvento + "&txtObs=" + strObs;          

        // Envío de petición AJAX.        
        
        $("#dLoader").show();
        AJAX("POST","Pagos",dataString,"dMensaje");    
      
    }     
}

function reprocesarPagoC(strConsecutivo,strCodigoPago,strEvento){
    
    if(confirm("¿Está seguro que desea reprocesar el pago?")){
        var strAccion = "REPROCESARC";    
        var strObs="";
        
        if(strEvento == "DD"){
            strObs= $("#txtObs").val();
            
            if (strObs == ""){
                $("#imgObs").show();
                $("#txtObs").focus();
                return false;
            }else{
                $("#imgObs").hide();
            }        
            $("#dLoader").show();
        }else{
            strObs="-";
        }
        
        var dataString = "txtAccion=" + strAccion + "&txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" + strCodigoPago + "&txtEvento=" + strEvento + "&txtObs=" + strObs;       

        // Envío de petición AJAX.
                
        AJAX("POST","Pagos",dataString,"dMensaje");     
        
    }
}

function reprocesarPagoI(strConsecutivo,strCodigoPago,strEvento){
    
    if(confirm("¿Está seguro que desea reprocesar el pago?")){
        var strAccion = "REPROCESARI";    
        var strObs="";
        
        if(strEvento == "DD"){
            strObs= $("#txtObs").val();
            
            if (strObs == ""){
                $("#imgObs").show();
                $("#txtObs").focus();
                return false;
            }else{
                $("#imgObs").hide();
            }         
            $("#dLoader").show();
        }else{
            strObs="-";
        }
        
        var dataString = "txtAccion=" + strAccion + "&txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" + strCodigoPago + "&txtEvento=" + strEvento + "&txtObs=" + strObs;       

        // Envío de petición AJAX.
                
        AJAX("POST","Pagos",dataString,"dMensaje");     
        
    }
}

function ejecutarPago(strConsecutivo,strCodigoPago,strEvento){
    
    if(confirm("¿Está seguro que desea poner como ejecutado el pago?")){
        var strAccion = "EJECUTAR";    
        var strObs="-";
     
        if(strEvento == "DD"){
            strObs= $("#txtObs").val();            
        }
     
        var dataString = "txtAccion=" + strAccion + "&txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" + strCodigoPago + "&txtEvento=" + strEvento + "&txtObs=" + strObs;      

        // Envío de petición AJAX.
        
        $("#dLoader").show();
        AJAX("POST","Pagos",dataString,"dMensaje"); 
        
    }
}

function eliminarPago(strConsecutivo,strCodigoPago){
    
    if(confirm("¿Está seguro que desea eliminar el pago?")){
        var strAccion = "ELIMINAR";    
        var dataString = "txtAccion=" + strAccion + "&txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" + strCodigoPago;          

        // Envío de petición AJAX.
        
        $("#dLoader").show();
        AJAX("POST","Pagos",dataString,"dMensaje"); 
    }
}

function refrescar(strCodigo){
    location.href = "plan_pagos.jsp?txtConsecutivo=" + strCodigo;
}


$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});

$(function() { 
   
    /*$("#btnGuardar").click(function(){
        
        var strValorSalud = $("#txtValorSalud").val();
        
        if (strValorSalud == ""){
            $("#imgValorSalud").show();
            $("#txtValorSalud").focus();
            return false;
        }else{
            $("#txtValorSalud").val(replaceCadena(strValorSalud,".",""));
            strValorSalud = $("#txtValorSalud").val();
            if (!(validarNumero(strValorSalud))){                
                $("#imgValorSalud").show();
                alert("ADVERTENCIA: El valor Salud debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                $("#txtValorSalud").focus();
                 return false;
            }else{
                $("#imgValorSalud").hide();
            }
        }
        
        var strValorPension = $("#txtValorPension").val();
        
        if (strValorPension == ""){
            $("#imgValorPension").show();
            $("#txtValorPension").focus();
            return false;
        }else{
            $("#txtValorPension").val(replaceCadena(strValorPension,".",""));
            strValorPension = $("#txtValorPension").val();
            if (!(validarNumero(strValorPension))){                
                $("#imgValorPension").show();
                alert("ADVERTENCIA: El valor Pensión debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                $("#txtValorPension").focus();
                 return false;
            }else{
                $("#imgValorPension").hide();
            }
        }
        
        var strValorARL = $("#txtValorARL").val();
        
        if (strValorARL == ""){
            $("#imgValorARL").show();
            $("#txtValorARL").focus();
            return false;
        }else{
            $("#txtValorARL").val(replaceCadena(strValorARL,".",""));
            strValorARL = $("#txtValorARL").val();
            if (!(validarNumero(strValorARL))){                
                $("#imgValorARL").show();
                alert("ADVERTENCIA: El valor ARL debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                $("#txtValorARL").focus();
                 return false;
            }else{
                $("#imgValorARL").hide();
            }
        }
        
        var strAdjunto = $("#txtAdjunto").val();
        
        if (strAdjunto == ""){
            $("#imgAdjunto").show();
            $("#txtAdjunto").focus();
            return false;
        }else{
            $("#imgAdjunto").hide();
        }
        
        var strConsecutivo = $("#txtConsecutivo").val();
        var strNumPago = $("#txtNumPago").val();
        var strForm = $("#txtForm").val();
        var strObs = $.trim($("#txtObs").text());
        
        var dataString = "txtForm=" + strForm + "&txtConsecutivo=" + strConsecutivo + "&txtNumPago=" + strNumPago + "&txtValorSalud=" + strValorSalud + "&txtValorPension=" + strValorPension + "&txtValorARL=" + strValorARL + "&txtObs=" + strObs;
        
        // Envío de petición AJAX.
        
        AJAX("POST","Registro",dataString,"dMensaje");
    });*/
    
    $("#btnCambiar").click(function(){
        var strPorcNuevo = $("#txtPorcNuevo").val();
        var strEvento = $("#txtEvento").val();
        
        if(strPorcNuevo == ""){
            $("#imgPorcNuevo").show();
            $("#txtPorcNuevo").focus();
            return false;
        }else{
            $("#txtPorcNuevo").val(replaceCadena(strPorcNuevo,",","."));
            strPorcNuevo = $("#txtPorcNuevo").val();
            if (!(validarNumero(strPorcNuevo))){                
                $("#imgPorcNuevo").show();
                alert("ADVERTENCIA: El porcentaje debe ser un valor numérico positivo utilizando el punto (.) como separador de decimales.");            
                $("#txtPorcNuevo").focus();
                 return false;
            }else{
                $("#imgPorcNuevo").hide();
            }            
        }
        
        var ftPorcNuevo = parseFloat(strPorcNuevo);
        var ftPorcAcum = parseFloat($("#txtPorcAcum").val());
        var ftPorcActual = parseFloat($("#txtPorcActual").val());
          
        if(ftPorcNuevo < 0){
            alert("ADVERTENCIA: El porcentaje debe ser un valor numérico positivo.");
            return false;
        }
        
        if(ftPorcNuevo > 100){
            alert("ADVERTENCIA: El porcentaje nuevo no debe superar el 100%.");
            return false;
        }
        
        var suma = ftPorcAcum + ftPorcNuevo;     
            
        if(suma > 100.00){
            alert("ADVERTENCIA: La suma entre el porcentaje acumulado y el porcentaje nuevo no debe superar el 100%.");
            return false;
        }      
                
        var strAccion = "";
        var dataString = "";
        var strIdContrato = $("#txtConsecutivo").val();             
        var strNumPago = $("#txtNumPago").val();
        
        if(confirm("¿Está seguro que desea modificar el porcentaje a pagar actual?")){
            //if ((suma >= 98) && (suma < 101)){
            if (suma > 100.00){
                strAccion = "ELIMINARPAGOSSGTES";                 
            }else{
                strAccion = "RECALCULARPAGOS";         
            }
                        
            var dataString = "txtConsecutivo=" + strIdContrato + "&txtNumPago=" + strNumPago + "&txtAccion=" + strAccion + "&txtPorcNuevo=" + ftPorcNuevo + "&txtEvento=" + strEvento;    
            AJAX("POST","Pagos",dataString,"dMensaje"); 
        }       
        
    });    
     
   $("#btnImprimir").click(function(){
      $("#dNPrint").hide();
      $("#dSPrint").show();
      window.print();
   });
   
   $("#btnVolver").click(function(){
      $("#dNPrint").show();
      $("#dSPrint").hide();
   });   
   
    $("input[type=file]").filestyle({ 
        image: "Images/lupa.gif",
        imageheight : 17,
        imagewidth : 35,
        width : 180       
    });
});