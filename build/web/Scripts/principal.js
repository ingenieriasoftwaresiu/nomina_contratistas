/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var itPersonas,itContratos,itContratosH,itInterventores,itInterventoresXProy,itContratosP, itContratosPH, itCambioEstadoContrato,itCambioEstadoPago,itDetenerJobs;
var strTipoPersona;
    
function cargaInicial(){   
    
    
   strTipoPersona = $("#txtTipoUsuario").val();

    if (strTipoPersona == "A"){
        itPersonas = $("#itPersonas");
        itContratos = $("#itContratos");    
        itContratosH = $("#itContratosH");    
        itInterventores = $("#itInterventores");
        itInterventoresXProy = $("#itInterventoresXProy");
        itCambioEstadoContrato = $("#itCambioEstadoContrato");
        itCambioEstadoPago = $("#itCambioEstadoPago");
        itDetenerJobs = $("#itDetenerJobs");
        
        itPersonas.removeClass("SELECTED");
        itInterventores.removeClass("SELECTED");
        itInterventoresXProy.removeClass("SELECTED");
        itContratos.addClass("SELECTED");             
        itContratosH.removeClass("SELECTED");   
        itCambioEstadoContrato.removeClass("SELECTED");
        itCambioEstadoPago.removeClass("SELECTED");
        itDetenerJobs.removeClass("SELECTED");
        
        AJAX('POST','Visualizacion','txtAccion=contratos','dMostrar');
    }else{      
            itContratosP = $("#itContratosP");  
            itContratosPH = $("#itContratosPH");  
            
            itContratosP.addClass("SELECTED"); 
            itContratosPH.removeClass("SELECTED"); 

            AJAX('POST','Visualizacion','txtAccion=contratosP','dMostrar');                
    }    
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});

// Evento del menú.

$(function() { 
    
    if (strTipoPersona == "A"){
        
        itPersonas.click(function(){  
            itPersonas.addClass("SELECTED");
            itInterventores.removeClass("SELECTED");
            itInterventoresXProy.removeClass("SELECTED");
            itContratos.removeClass("SELECTED");           
            itCambioEstadoContrato.removeClass("SELECTED");
            itCambioEstadoPago.removeClass("SELECTED");
            itDetenerJobs.removeClass("SELECTED");
            itContratosH.removeClass("SELECTED");  

            AJAX('POST','Visualizacion','txtAccion=contratistas','dMostrar');
         });

        itInterventores.click(function(){  
           itPersonas.removeClass("SELECTED");
           itInterventores.addClass("SELECTED");
           itInterventoresXProy.removeClass("SELECTED");
           itContratos.removeClass("SELECTED");           
           itCambioEstadoContrato.removeClass("SELECTED");
           itCambioEstadoPago.removeClass("SELECTED");
           itDetenerJobs.removeClass("SELECTED");
           itContratosH.removeClass("SELECTED");  

           AJAX('POST','Visualizacion','txtAccion=interventores','dMostrar');
        });   

        itInterventoresXProy.click(function(){  
           itPersonas.removeClass("SELECTED");
           itInterventores.removeClass("SELECTED");
           itInterventoresXProy.addClass("SELECTED");
           itContratos.removeClass("SELECTED");           
           itCambioEstadoContrato.removeClass("SELECTED");
           itCambioEstadoPago.removeClass("SELECTED");
           itDetenerJobs.removeClass("SELECTED");
           itContratosH.removeClass("SELECTED");  

           AJAX('POST','Visualizacion','txtAccion=interventoresXproy','dMostrar');
        });

        itContratos.click(function(){  
           itPersonas.removeClass("SELECTED");
           itInterventores.removeClass("SELECTED");
           itInterventoresXProy.removeClass("SELECTED");
           itContratos.addClass("SELECTED");    
           itCambioEstadoContrato.removeClass("SELECTED");
           itCambioEstadoPago.removeClass("SELECTED");
           itDetenerJobs.removeClass("SELECTED");
           itContratosH.removeClass("SELECTED");  

           AJAX('POST','Visualizacion','txtAccion=contratos','dMostrar');
        });          
        
        itContratosH.click(function(){  
           itPersonas.removeClass("SELECTED");
           itInterventores.removeClass("SELECTED");
           itInterventoresXProy.removeClass("SELECTED");
           itContratos.removeClass("SELECTED");    
           itCambioEstadoContrato.removeClass("SELECTED");
           itCambioEstadoPago.removeClass("SELECTED");
           itDetenerJobs.removeClass("SELECTED");
           itContratosH.addClass("SELECTED");  

           AJAX('POST','Visualizacion','txtAccion=contratosH','dMostrar');
        });
        
        itCambioEstadoContrato.click(function(){  
           itPersonas.removeClass("SELECTED");
           itInterventores.removeClass("SELECTED");
           itInterventoresXProy.removeClass("SELECTED");
           itContratos.removeClass("SELECTED");    
           itCambioEstadoContrato.addClass("SELECTED");
           itCambioEstadoPago.removeClass("SELECTED");
           itDetenerJobs.removeClass("SELECTED");
           itContratosH.removeClass("SELECTED");  
           
           AJAX('POST','Acciones','txtAccion=CambioEstadoContrato','dMostrar');
        });  
        
        itCambioEstadoPago.click(function(){  
           itPersonas.removeClass("SELECTED");
           itInterventores.removeClass("SELECTED");
           itInterventoresXProy.removeClass("SELECTED");
           itContratos.removeClass("SELECTED");    
           itCambioEstadoContrato.removeClass("SELECTED");
           itCambioEstadoPago.addClass("SELECTED");
           itDetenerJobs.removeClass("SELECTED");
           itContratosH.removeClass("SELECTED");  
           
           AJAX('POST','Acciones','txtAccion=CambioEstadoPago','dMostrar');
        });
        
        itDetenerJobs.click(function(){  
           itPersonas.removeClass("SELECTED");
           itInterventores.removeClass("SELECTED");
           itInterventoresXProy.removeClass("SELECTED");
           itContratos.removeClass("SELECTED");    
           itCambioEstadoContrato.removeClass("SELECTED");
           itCambioEstadoPago.removeClass("SELECTED");
           itDetenerJobs.addClass("SELECTED");
           itContratosH.removeClass("SELECTED");  
           
           AJAX('POST','Acciones','txtAccion=DetenerJobs','dMostrar');
        });
  
    }else{
        
        itContratosP.click(function(){  
            itContratosP.addClass("SELECTED"); 
            itContratosPH.removeClass("SELECTED");
            
            AJAX('POST','Visualizacion','txtAccion=contratosP','dMostrar');
        });
        
        itContratosPH.click(function(){ 
            itContratosP.removeClass("SELECTED"); 
            itContratosPH.addClass("SELECTED");
            
            AJAX('POST','Visualizacion','txtAccion=contratosPH','dMostrar');
        });
        
    }
});


