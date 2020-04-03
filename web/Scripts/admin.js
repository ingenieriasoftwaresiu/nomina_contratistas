/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var itRoles,itRolesXPersona,itTiposC,itEstados;
    
function cargaInicial(){   
        
    itRoles = $("#itRoles");
    itRolesXPersona = $("#itRolesXPersona");    
    itTiposC = $("#itTiposC");
    itEstados = $("#itEstados");
    
    var strEsDllo = $("#txtTipoUsuario").val();
    
    if (strEsDllo == "S"){
        itRoles.addClass("SELECTED");    
        itRolesXPersona.removeClass("SELECTED");
        itTiposC.removeClass("SELECTED");  
        itEstados.removeClass("SELECTED"); 
        AJAX('POST','Visualizacion','txtAccion=roles','dMostrar');
    }else{
        itRoles.removeClass("SELECTED");    
        itRolesXPersona.addClass("SELECTED");
        itTiposC.removeClass("SELECTED");  
        itEstados.removeClass("SELECTED"); 
        AJAX('POST','Visualizacion','txtAccion=rolesXpersona','dMostrar');
    }   

}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});

$(function() { 
    
    itRoles.click(function(){  
        itRoles.addClass("SELECTED");
        itRolesXPersona.removeClass("SELECTED");
        itTiposC.removeClass("SELECTED");       
        itEstados.removeClass("SELECTED"); 

        AJAX('POST','Visualizacion','txtAccion=roles','dMostrar');
    });
     
     itRolesXPersona.click(function(){  
        itRoles.removeClass("SELECTED");
        itRolesXPersona.addClass("SELECTED");
        itTiposC.removeClass("SELECTED");       
        itEstados.removeClass("SELECTED"); 
        
        AJAX('POST','Visualizacion','txtAccion=rolesXpersona','dMostrar');
     });
     
     itTiposC.click(function(){  
        itRoles.removeClass("SELECTED");
        itRolesXPersona.removeClass("SELECTED");
        itTiposC.addClass("SELECTED");       
        itEstados.removeClass("SELECTED"); 
        
        AJAX('POST','Visualizacion','txtAccion=tiposC','dMostrar');
     });
     
      itEstados.click(function(){  
        itRoles.removeClass("SELECTED");
        itRolesXPersona.removeClass("SELECTED");
        itTiposC.removeClass("SELECTED");       
        itEstados.addClass("SELECTED"); 
        
        AJAX('POST','Visualizacion','txtAccion=estados','dMostrar');
     });
     
     
});
