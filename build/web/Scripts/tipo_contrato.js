/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dMensaje").html("");
    
     var strAccion = $("#txtAccion").val();
    
    if (strAccion == "C"){
         $("#txtCodigo").focus();
     }else{
         $("#txtNombre").focus();
     }
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});
    
$(function() { 
    
    $("#btnGuardar").on("click",function(){
        $("#dMensaje").show();
        
        var strCodigo = $("input#txtCodigo").val();  

        if (strCodigo == "") {  
            $("img#imgCodigo").show();  
            $("input#txtCodigo").focus();  
            return false;  
        }else{
             $("img#imgCodigo").hide();  
        }
        
        var strNombre = $("input#txtNombre").val();  

        if (strNombre == "") {  
            $("img#imgNombre").show();  
            $("input#txtNombre").focus();  
            return false;  
        }else{
             $("img#imgNombre").hide();  
        }
        
         // Instrucciones cuando se hace el submit correctamente.
        
        var strForm = $("input#txtForm").val();
        var strAccion = $("input#txtAccion").val();        
        
        // Construcción de parámetros para el Servlet.
        
        var dataString = "txtForm=" + strForm + "&txtAccion=" + strAccion + '&txtCodigo='+ strCodigo + '&txtNombre=' + strNombre;          
       
        // Envío de petición AJAX.
        
        AJAX("POST","Registro",dataString,"dMensaje");        

    });
    
    // Función para limpiar los todos los campos del formulario.
    
    $("#btnLimpiar").click(function(){
        $("input#txtCodigo").val("");
        $("input#txtNombre").val("");
        cargaInicial();
    }); 
    
    // Función para ocultar la respuesta AJAX al momento de ingresar nueva información.
    
    $(".CAMPOFORM").focus(function(){
        $("#dMensaje").html("");  
    });
    
});

