/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dMensaje").html("");    
    $("#txtInterventor").focus();     
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});

$(function() { 
    
    $("#btnGuardar").on("click",function(){       
         
         var strInterventor = $("#txtInterventor").val();
         
         if (strInterventor == "-1"){
              $("#imgInterventor").show();    
              $("#txtInterventor").focus();
              return false;
         }else{
             $("#imgInterventor").hide();    
         }
                  
         var strCodProy = $("#txtCodProy").val();
         
         if (strCodProy == ""){
             strCodProy = "-";
         }
                           
          var strForm = $("#txtForm").val();
          var strAccion = $("#txtAccion").val();          
          var dataString = "txtForm=" + strForm + "&txtAccion=" + strAccion + "&txtInterventor=" + strInterventor + "&txtCodProy=" + strCodProy;
           
           // Envío de petición AJAX.
        
           AJAX("POST","Registro",dataString,"dMensaje");      
          
    });
    
    $("#btnLimpiar").on("click",function(){        
        $("#txtInterventor").val("-1");
        $("#txtCodProy").val("");
        cargaInicial();
    });    
    
    $("#btnAgregarInterventor").click(function(){
        window.open("contratista.jsp?txtAccion=C&txtTipoRegistro=I","Interventor",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    });
    
    $(".IMGREFRESH").click(function(){
        var strConsecutivo = $("#txtConsecutivo").val();
        location.reload();
        $("#txtConsecutivo").val(strConsecutivo);
    });
    
});
