/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dMensaje").html("");
    
     var strAccion = $("#txtAccion").val();

     if (strAccion == "C"){
         $("#txtPersona").focus();
     }   
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});
    
$(function() { 
    
    $("#btnGuardar").on("click",function(){
        $("#dMensaje").show();
                
       var strPersona = $("#txtPersona").val();
        
        if (strPersona == '-1'){        
            $("img#imgPersona").show();  
            $("#txtPersona").focus();  
            return false;  
        }else{
            $("img#imgPersona").hide();
        }
        
        var strRol = $("#txtRol").val();
        
        if (strRol == '-1'){        
            $("img#imgRol").show();  
            $("#txtRol").focus();  
            return false;  
        }else{
            $("img#imgRol").hide();
        }
        
         // Instrucciones cuando se hace el submit correctamente.
                
        var strForm = $("#txtForm").val();
        var strAccion = $("#txtAccion").val();        
        
        // Construcción de parámetros para el Servlet.
        
        var dataString = "txtForm=" + strForm + "&txtAccion=" + strAccion + '&txtIdPersona='+ strPersona + '&txtIdRol=' + strRol;          
       
        // Envío de petición AJAX.
        
        AJAX("POST","Registro",dataString,"dMensaje");        

    });
    
    // Función para limpiar los todos los campos del formulario.
    
    $("#btnLimpiar").click(function(){
        $("#txtPersona").val("-1");
         $("#txtRol").val("-1");
        cargaInicial();
    }); 
    
    // Función para ocultar la respuesta AJAX al momento de ingresar nueva información.
    
    $(".CAMPOSELECT").focus(function(){
        $("#dMensaje").html("");  
    });
    
});


