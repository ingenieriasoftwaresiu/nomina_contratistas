/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dMensaje").html("");       
    
    var strAccion = $("#txtAccion").val();
    
    if (strAccion == "C"){
        
        $("#txtConsecutivo").focus();
        $("#txtJustificacion").val("");
         var strFechaCreacion = $("#txtFechaCreacion").val();
         
        if (strFechaCreacion == ""){
            var strFechaCreacion = obtiene_fecha();
            $("#txtFechaCreacion").val(strFechaCreacion);
        }
    }
    
    if (strAccion == "V"){
        var strJustificacion = $.trim($("#txtJustificacion").val());
        $("#txtJustificacion").val("");
        $("#txtJustificacion").val(strJustificacion);
    }
}

function actualizar(){
    location.reload();
}

function cargarCalendarios(){
    setCalendario("txtFechaFinNueva","imgFechaFinNueva");
}

function verOTROSI(strConsecutivo,strCodigo){
    window.open("otrosi.jsp?txtAccion=V&txtConsecutivo=" + strConsecutivo + "&txtCodigo=" +strCodigo,"Otrosi",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function validarOTROSI(){
    
    var strFechaFinActual = $("#txtFechaFinActual").val();
    var strFechaFinNueva = $("#txtFechaFinNueva").val();
        
    if (strFechaFinNueva == ""){
        $("#imgFechaFinNuevaC").show();
        $("#txtFechaFinNueva").focus();
        return false;
    }else{
        $("#imgFechaFinNuevaC").hide();
        
        var comparacion = compare_dates(strFechaFinActual,strFechaFinNueva);
        
        if (comparacion){
            $("#imgFechaFinNuevaC").show();
            $("#txtFechaFinNueva").focus();
            alert("La fecha de finalización nueva debe ser mayor a la fecha de finalización actual.");
            return false;
        }else{
            $("#imgFechaFinNuevaC").hide();
        }
    }  
    
    var strJustificacion = $.trim($("#txtJustificacion").val());    
        
    if (strJustificacion == ""){
        $("#imgJustificacion").show();
        $("#txtJustificacion").focus();
        return false;
    }else{
        $("#imgJustificacion").hide();
        strJustificacion = strJustificacion.replace(/\r?\n/g, " ");
        $("#txtJustificacion").val(strJustificacion);
    } 
    
    var strActa = $("#txtActa").val();
    var strRutaActa = $("#txtRutaActa").val();
    
    if ((strActa == "") && (strRutaActa == "-")){
        $("#imgActa").show();
        $("#txtActa").focus();
        return false;
    }else{  
        if (strRutaActa == "-"){            
            var extension = (strActa.substring(strActa.lastIndexOf("."))).toLowerCase();         
            if (extension != ".pdf"){
                $("#imgActa").show();
                alert("El acta debe adjuntarse en archivo PDF (.pdf).");
                return false;
            }else{
                $("#imgActa").hide();
            }                       
        }else{
            $("#imgActa").hide();
        } 
    }                
}

function limpiar(){
    
    $("#txtFechaFinNueva").val("");
    $("#txtJustificacion").val("");
    $("#txtActa").val("");
    $("#txtRutaActa").val("-");
    
    cargaInicial();
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});
    
$(function() { 
    
    $("#btnLimpiar").on("click",function(){
        limpiar();
    });
    
    $("input[type=file]").filestyle({ 
        image: "Images/lupa.gif",
        imageheight : 17,
        imagewidth : 35,
        width : 180       
    });   
    
});

