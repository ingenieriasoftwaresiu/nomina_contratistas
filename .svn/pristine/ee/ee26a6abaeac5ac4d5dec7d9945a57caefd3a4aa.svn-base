/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dMensaje").html("");
    
     var strAccion = $("#txtAccion").val();
    
    if (strAccion == "C"){
         $("#txtTipoId").focus();
     }else{
         $("#txtNombres").focus();
     }
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});
    
$(function() { 
    
    $("#btnGuardar").on("click",function(){
        $("#dMensaje").show();
        var strTipoRegistro = $("#txtTipoRegistro").val();
        
        var strTipoId = $("#txtTipoId").val();  

        if (strTipoId == "-1") {  
            $("#imgTipoId").show();  
            $("#txtTipoId").focus();  
            return false;  
        }else{
             $("#imgTipoId").hide();  
        }
        
        var strNumId = $.trim($("#txtNumId").val());  

        if (strNumId == "") {  
            $("#imgNumId").show();  
            $("#txtNumId").focus();  
            return false;  
        }else{
             $("#imgNumId").hide();  
             strNumId = strNumId.replace(/\./g, '');
             $("#txtNumId").val(strNumId);
        }
        
        var strNombres = $.trim($("#txtNombres").val());  

        if (strNombres == "") {  
            $("#imgNombres").show();  
            $("#txtNombres").focus();  
            return false;  
        }else{
             $("#imgNombres").hide();  
        }
        
        var strApellidos = $.trim($("#txtApellidos").val());  

        if (strApellidos == "") {  
            $("#imgApellidos").show();  
            $("#txtApellidos").focus();  
            return false;  
        }else{
             $("#imgApellidos").hide();  
        }
                   
        var strEmail = $.trim($("#txtEmail").val());

        if (strEmail == "") {  
            $("#imgEmail").show();  
            $("#txtEmail").focus();  
            return false;  
        }else{
            $("#imgEmail").hide();  
        }
                
        if(!($("input[name='txtEstado']:radio").is(':checked'))) {         
              $("#imgEstado").show();
              return false;  
         }else{
             $("#imgEstado").hide();
         }                           
         
         var strJubilado = "";
         
         if (strTipoRegistro == "C"){                          
             if(!($("input[name='txtJubilado']:radio").is(':checked'))) {         
                    $("#imgJubilado").show();
                    return false;  
               }else{                   
                   $("#imgJubilado").hide();
                   strJubilado = $("input[name='txtJubilado']:checked").val();  
               }
         }
                 
         // Instrucciones cuando se hace el submit correctamente.
        
        var strForm = $("#txtForm").val();
        var strAccion = $("#txtAccion").val();        
        var strDireccion = $("#txtDireccion").val();    
        var strTelefono = $("#txtTelefono").val();    
        var strEmail = $.trim($("#txtEmail").val());    
        var strEstado = $("input[name='txtEstado']:checked").val();        
        var strTipoRegistro = $("#txtTipoRegistro").val();
        
        // Construcción de parámetros para el Servlet.
        
        var dataString = "txtForm=" + strForm + "&txtAccion=" + strAccion + "&txtTipoId=" + strTipoId + '&txtNumId='+ strNumId + '&txtNombres=' + strNombres + '&txtApellidos=' + strApellidos + '&txtDireccion=' + strDireccion + '&txtTelefono=' + strTelefono
                                            + '&txtEmail=' + strEmail + '&txtEstado=' + strEstado + '&txtTipoRegistro=' + strTipoRegistro + '&txtJubilado=' + strJubilado;          
                   
        // Envío de petición AJAX.
        
      AJAX("POST","Registro",dataString,"dMensaje");        

    });
    
    // Función para limpiar los todos los campos del formulario.
    
    $("#btnLimpiar").click(function(){
        $("#txtTipoId").val("CC");
        $("#txtNumId").val("");
        $("#txtNombres").val("");
        $("#txtApellidos").val("");
        $("#txtDireccion").val("");
        $("#txtTelefono").val("");
        $("#txtEmail").val("");
        $('#rdActivo').attr('checked', 'checked');
        cargaInicial();
    }); 
    
    // Función para ocultar la respuesta AJAX al momento de ingresar nueva información.
    
    $(".CAMPOFORM").focus(function(){
        $("#dMensaje").html("");  
    });
    
    $(".CAMPOSELECT").change(function(){
        $("#dMensaje").html("");  
    });
    
    $("input[name='txtEstado']:radio").on("change",function(){
          $("#dMensaje").html("");           
    });
    
    $("input[name='txtJubilado']:radio").on("change",function(){
          $("#imgJubilado").hide();
    });
    
});



