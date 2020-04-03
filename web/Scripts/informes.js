/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function cargaInicial(){    
    $(".IMGERROR").hide();     
    $("#dVolver").hide();
    $("#dParametros").show();
}

function verDetallePago(strConsecutivo,strCodigoPago){
    window.open("../detalle_pago.jsp?txtConsecutivo=" + strConsecutivo + "&txtCodigoPago=" +strCodigoPago,"Detalle_Pago",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function cargarCalendarios(){
    setCalendario("txtFechaIni","imgFechaIni");
    setCalendario("txtFechaFin","imgFechaFin");
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});

$(function(){ 
    
    /* ACCIONES DEL INFORME PAGOS POR ESTADO */
    
    $("#btnGenerar").click(function(){
        
        var strIdEstado = $("#txtIdEstado").val();
        
        if(strIdEstado == "-1"){
            $("#imgIdEstado").show();
            $("#txtIdEstado").focus();
            return false;
        }else{
            $("#imgIdEstado").hide();
        }
        
        var dataString = "txtIdEstado=" + strIdEstado;
        
        AJAX("POST","..//InformePagosXEstado",dataString,"dMostrarInforme");
    });
    
    $("#btnLimpiar").click(function(){
        $("#txtIdEstado").val("-1");
        $("#dMostrarInforme").hide();
        cargaInicial();
    });
    
    $("#btnGenerarPP").click(function(){
        var strFechaIni = $("#txtFechaIni").val();             

        if(strFechaIni == ""){   
            $("img#imgFechaInicio").show();
            $("#txtFechaIni").focus();
            return false;
        }else{
            $("img#imgFechaInicio").hide();
        }

        var strFechaFin = $("#txtFechaFin").val();

        if(strFechaFin == ""){
            $("img#imgFechaFinal").show();
            $("#txtFechaFin").focus();
            return false;
        }else{
            $("img#imgFechaFinal").hide();
        }

        var dataString = "txtFechaIni=" + strFechaIni + "&txtFechaFin=" + strFechaFin;
        $("#btnImprimir").show();

        AJAX("POST","..//InformePagosXPeriodo",dataString,"dMostrarInforme");
    });    
    
    $("#btnLimpiarPP").click(function(){
        $("#txtFechaIni").val("");
        $("#txtFechaFin").val("");
        $("#dMostrarInforme").hide();
        $("#btnImprimir").hide();
        cargaInicial();
    });
    
    $("#txtIdEstado").change(function(){
        var strIdEstado = $("#txtIdEstado").val();
        
        if(strIdEstado == "-1"){
            $("#imgIdEstado").show();
        }else{
            $("#imgIdEstado").hide();
        }        
        
    });
    
    $("#btnVolver").click(function(){
        $("#dVolver").hide();
        $("#dParametros").show();
    });
    
    $("#btnImprimir").click(function(){
        $("#dVolver").show();
        $("#dParametros").hide();
        window.print();
    });

});