/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dMensaje").html("");
    $("#txtTipoUsuario").focus();
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});
    
$(function() { 
    
    $("#btnIngresar").on("click",function(){
        $("#dMensaje").show();
             
        var strTipoUsuario = $("#txtTipoUsuario").val();        
        
        if(strTipoUsuario == "-1"){
            $("#imgTipoUsuario").show();
            $("#txtTipoUsuario").focus();
            return false;  
        }else{
            $("#imgTipoUsuario").hide();
        }        
        
        var strUsuario= $("#txtUsuario").val();  

        if (strUsuario == "") {  
            $("img#imgUsuario").show();  
            $("#txtUsuario").focus();  
            return false;  
        }else{
             $("img#imgUsuario").hide();  
        }
        
        var strPwd = $("#txtPwd").val();  

        if (strPwd == "") {  
            $("img#imgPwd").show();  
            $("#txtPwd").focus();  
            return false;  
        }else{
             $("img#imgPwd").hide();  
        }
                
        // Construcción de parámetros para el Servlet.
        
        var dataString = "txtUsuario="+ strUsuario + "&txtPwd=" + strPwd + "&txtTipoUsuario=" + strTipoUsuario;          
       
        // Envío de petición AJAX.
        
        AJAX_REDIRECT("POST","Ingreso",dataString,"dMensaje","principal.jsp");

    });
    
    // Función para limpiar los todos los campos del formulario.
    
    $("#btnLimpiar").click(function(){
        $("#txtUsuario").val("");
        $("#txtPwd").val("");
        $("#txtTipoUsuario").val("-1");
        cargaInicial();
    }); 
    
    // Función para ocultar la respuesta AJAX al momento de ingresar nueva información.
    
    $(".CAMPOFORM").focus(function(){
        $("#dMensaje").html("");  
    });
    
    $(".CAMPOSELECT").focus(function(){
        $("#dMensaje").html("");  
    });
    
    $("#txtUsuario").keypress(function(event){
        if (event.which == 13){
            event.preventDefault();
            $("#btnIngresar").click();
        }
         $("img#imgUsuario").hide();  
   });
    
    $("#txtPwd").keypress(function(event){
        if (event.which == 13){
            event.preventDefault();
            $("#btnIngresar").click();
        }
         $("img#imgPwd").hide();  
    });
    
    $("#txtTipoUsuario").change(function(){
        
        var strTipoUsuario = $("#txtTipoUsuario").val();        
        
        if(strTipoUsuario == "-1"){
            $("#imgTipoUsuario").show();
            $("#txtTipoUsuario").focus();
        }else{
            $("#imgTipoUsuario").hide();
            $("#txtUsuario").focus();
        }         
    });
    
});



