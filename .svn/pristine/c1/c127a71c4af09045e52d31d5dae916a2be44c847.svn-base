/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dMensaje").html("");
    
     var strAccion = $("#txtAccion").val();
     $("#txtNumRegPag").focus();       
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});

$(function() { 
    
    $("#btnGuardar").on("click",function(){
        $("#dMensaje").show();
                
        var strNumRegPag = $("#txtNumRegPag").val();  

        if (strNumRegPag == "") {  
            $("img#imgNumRegPag").show();  
            $("input#txtNumRegPag").focus();  
            return false;  
        }else{
             $("img#imgNumRegPag").hide();  
        }
                
        var strRutaArchivoCarga = $("#txtRutaCargaArchivo").val();  

        if (strRutaArchivoCarga == "") {  
            $("img#imgRutaCargaArchivo").show();  
            $("input#txtRutaCargaArchivo").focus();  
            return false;  
        }else{
             $("img#imgRutaCargaArchivo").hide();  
        }
        
        var strNumDiasAlertaVencerse = $("#txtNumDiasAlertaVencerse").val();
        
        if (strNumDiasAlertaVencerse == "") {  
            $("#imgNumDiasAlertaVencerse").show();  
            $("#txtNumDiasAlertaVencerse").focus();  
            return false;  
        }else{
             $("#imgNumDiasAlertaVencerse").hide();  
        }
        
        var strNombreServidor = $("#txtNombreServidor").val();
        
        if (strNombreServidor == "") {  
            $("#imgNombreServidor").show();  
            $("#txtNombreServidor").focus();  
            return false;  
        }else{
             $("#imgNombreServidor").hide();  
        }
        
        var strNumeroPuerto = $("#txtNumeroPuerto").val();
        
        if (strNumeroPuerto == "") {  
            $("#imgNumeroPuerto").show();  
            $("#txtNumeroPuerto").focus();  
            return false;  
        }else{
             $("#imgNumeroPuerto").hide();  
        }
        
        var strUsuario = $("#txtUsuario").val();
        
        if (strUsuario == "") {  
            $("#imgUsuario").show();  
            $("#txtUsuario").focus();  
            return false;  
        }else{
             $("#imgUsuario").hide();  
        }
        
        var strPassword = $("#txtPassword").val();
        
        if (strPassword == "") {  
            $("#imgPassword").show();  
            $("#txtPassword").focus();  
            return false;  
        }else{
             $("#imgPassword").hide();  
        }
                                        
        // Instrucciones cuando se hace el submit correctamente.
                
        var strForm = $("#txtForm").val();
        var strAccion = $("#txtAccion").val();        
        
        // Construcción de parámetros para el Servlet.
        
        var dataString = "txtForm=" + strForm + "&txtAccion=" + strAccion + '&txtNumRegPag=' + strNumRegPag + '&txtRutaArchivoCarga=' + strRutaArchivoCarga + '&txtNumDiasAlertaVencerse='+strNumDiasAlertaVencerse
                                       + "&txtNombreServidor="+strNombreServidor+ "&txtNumeroPuerto="+ strNumeroPuerto + "&txtUsuario="+strUsuario+ "&txtPassword="+strPassword;          
                                
        // Envío de petición AJAX.
        
        AJAX("POST","Registro",dataString,"dMensaje");        
        
        setTimeout(function(){window.close();},2000);

    });
    
    // Función para limpiar los todos los campos del formulario.
    
    $("#btnLimpiar").click(function(){        
        document.getElementById("frmGeneral").reset();        
        cargaInicial();
    }); 
    
    // Función para ocultar la respuesta AJAX al momento de ingresar nueva información.
    
    $(".CAMPOFORM").focus(function(){
        $("#dMensaje").html("");  
    });   
    
});

