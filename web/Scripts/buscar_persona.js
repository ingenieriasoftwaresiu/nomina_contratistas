/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dResultados").html("");
}

function seleccionarContratista(strNumId){
    
     if (confirm('¿Está seguro que desea asignar el contratista seleccionado?')){
        opener.frmContrato.txtContratista.value = strNumId;
        window.close();
    }
}

function seleccionarInterventor(strNumId){
    
     if (confirm('¿Está seguro que desea asignar el interventor seleccionado?')){
        opener.frmContrato.txtInterventor.value = strNumId;
        window.close();
    }
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});

$(function() {     
    
    $("#btnBuscar").on("click",function(){
       var strNumId = $.trim($("#txtNumId").val());
       var strNombre = $.trim($("#txtNombre").val());
       var strAccion = $("#txtAccion").val();
       
       if ((strNumId == "") && (strNombre == "")){
           alert("Debe diligenciar por lo menos uno de los campos para realizar la búsqueda correctamente!.");
           return false;
       }
       
       var dataString = "";
       
       if (strAccion == "C"){
           dataString = "txtAccion=buscar_contratista&txtNumId="+strNumId+"&txtNombre="+strNombre;
       }else{
           dataString = "txtAccion=buscar_interventor&txtNumId="+strNumId+"&txtNombre="+strNombre;
       }       
       
       AJAX("POST","Acciones",dataString,"dResultados"); 
       
   }); 
    
   $("#btnLimpiar").on("click",function(){
       $("#txtNumId").val("");
       $("#txtNombre").val("");
       $("#dResultados").empty("");
       $("#dResultados").hide("");
   }); 
});
