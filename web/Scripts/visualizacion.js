/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function eliminarRegistro(strCodigo, strAccion){
    
    if (confirm('¿Está seguro que desea eliminar el registro seleccionado?')){
        if (strAccion == "rolesXpersona"){        
            AJAXC("POST","Visualizacion","txtAccion=rolesXpersona&txtEvento=Eliminar&txtCodigo=" + strCodigo,"dMostrar");
        }       

        if (strAccion == "roles"){        
            AJAXC("POST","Visualizacion","txtAccion=roles&txtEvento=Eliminar&txtCodigo=" + strCodigo,"dMostrar");
        }     
        
        if (strAccion == "estados"){
            AJAXC("POST","Visualizacion","txtAccion=estados&txtEvento=Eliminar&txtCodigo=" + strCodigo,"dMostrar");
        }
        
         if (strAccion == "tiposC"){        
            AJAXC("POST","Visualizacion","txtAccion=tiposC&txtEvento=Eliminar&txtCodigo=" + strCodigo,"dMostrar");
        }
        
        if (strAccion == "contratistas"){        
            AJAXC("POST","Visualizacion","txtAccion=contratistas&txtEvento=Eliminar&txtCodigo=" + strCodigo,"dMostrar");
        }
        
        if (strAccion == "interventores"){        
            AJAXC("POST","Visualizacion","txtAccion=interventores&txtEvento=Eliminar&txtCodigo=" + strCodigo,"dMostrar");
        }
        
         if(strAccion == "interventoresXproy"){
             AJAXC("POST","Visualizacion","txtAccion=interventoresXproy&txtEvento=Eliminar&txtCodigo=" + strCodigo,"dMostrar");
         }
         
         if(strAccion == "contratos"){
             AJAXC("POST","Visualizacion","txtAccion=contratos&txtEvento=Eliminar&txtCodigo=" + strCodigo,"dMostrar");
        }
    
    }
}

function crearRegistro(strAccion){        
    if (strAccion == "tiposC"){        
        window.open("tipo_contrato.jsp?txtAccion=C","Tipo_Contrato",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    }    
    
    if (strAccion == "rolesXpersona"){ 
        window.open("rolXpersona.jsp?txtAccion=C","Rol_X_Persona",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    }
    
    if (strAccion == "roles"){        
        window.open("rol.jsp?txtAccion=C","Rol",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    }
    
    if (strAccion == "estados"){        
        window.open("estado.jsp?txtAccion=C","Rol",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    }
    
    if (strAccion == "contratistas"){        
        window.open("contratista.jsp?txtAccion=C&txtTipoRegistro=C","Contratista",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    } 
    
    if(strAccion == "interventores"){
        window.open("interventor.jsp?txtAccion=C","Interventor",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    }
    
    if(strAccion == "interventoresXproy"){
        window.open("interventorXproyecto.jsp?txtAccion=C","Interventor_X_Proyecto",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    }
    
    if(strAccion == "contratos"){
        window.open("contrato.jsp?txtAccion=C","Contrato",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    }
}

function abrirRegRol(strCodigo){
    window.open("rol.jsp?txtAccion=V&txtCodigo=" + strCodigo,"Rol",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function abrirRegEstado(strCodigo){
    window.open("estado.jsp?txtAccion=V&txtCodigo=" + strCodigo,"Rol",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function abrirRegTipoC(strCodigo){
    window.open("tipo_contrato.jsp?txtAccion=V&txtCodigo=" + strCodigo,"Tipo_Contrato",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function abrirRegRolXPersona(strCodigo){
    window.open("rolXpersona.jsp?txtAccion=V&txtCodigo=" + strCodigo,"Rol_X_Persona",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function abrirRegContratista(strCodigo){
    window.open("contratista.jsp?txtAccion=V&txtTipoRegistro=C&txtCodigo=" + strCodigo,"Contratista",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function abrirRegInterventor(strCodigo){
    window.open("interventor.jsp?txtAccion=V&txtCodigo=" + strCodigo,"Interventor",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function abrirRegInterventorXProy(strCodigo){
    window.open("interventorXproyecto.jsp?txtAccion=V&txtCodigo=" + strCodigo,"Interventor_X_Proyecto",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function abrirRegContrato(strCodigo){
    window.open("contrato.jsp?txtAccion=V&txtCodigo=" + strCodigo,"Contrato",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function verPlanPagos(){
    
   if(!($("input[name='txtSeleccion']:radio").is(':checked'))) {         
        alert("Debe seleccionar un registro de contrato para continuar.");
        return false;
   }
   
    var strConsecutivo = $("input[name='txtSeleccion']:checked").val();
    window.open("plan_pagos.jsp?txtConsecutivo=" + strConsecutivo,"Plan_Pagos",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function verOTROSIs(){
    if(!($("input[name='txtSeleccion']:radio").is(':checked'))) {         
        alert("Debe seleccionar un registro de contrato para continuar.");
        return false;
   }
   
    var strConsecutivo = $("input[name='txtSeleccion']:checked").val();
    window.open("lista_otrosi.jsp?txtConsecutivo=" + strConsecutivo,"Lista_Otrosi",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}

function finalizarContrato(){
    if(!($("input[name='txtSeleccion']:radio").is(':checked'))) {         
        alert("Debe seleccionar un registro de contrato para continuar.");
        return false;
   }
   
    if (confirm('¿Está seguro que desea finalizar el contrato seleccionado?')){
        var strFechaFin=prompt("Ingrese la nueva fecha de fin del contrato (aaaa-mm-dd):","");
        
        if(strFechaFin.indexOf("-") == -1){
            alert("La nueva fecha de fin debe tener el formato (aaaa-mm-dd)");
            return false;
        }

        if ((strFechaFin!=null) && (strFechaFin != "")){
            var strConsecutivo = $("input[name='txtSeleccion']:checked").val();
            var strAccion = "FINALIZAR"; 

            AJAXC("POST","Visualizacion","txtAccion=contratos&txtEvento=" + strAccion + "&txtConsecutivo=" + strConsecutivo + "&txtFechaFin=" + strFechaFin,"dMostrar");

        } 
     }
}

function ponerEnEjecucion(){
   if(!($("input[name='txtSeleccion']:radio").is(':checked'))) {         
        alert("Debe seleccionar un registro de contrato para continuar.");
        return false;
   }
   
   if (confirm('¿Está seguro que desea poner en ejecución el contrato seleccionado?')){
        var strConsecutivo = $("input[name='txtSeleccion']:checked").val();
        var strAccion = "PONER_EJECUCION";             
  
        AJAXC("POST","Visualizacion","txtAccion=contratos&txtEvento=" + strAccion + "&txtConsecutivo=" + strConsecutivo,"dMostrar");      
    }
}

function cancelarContrato(){
    if(!($("input[name='txtSeleccion']:radio").is(':checked'))) {         
         alert("Debe seleccionar un registro de contrato para continuar.");
         return false;
    }
   
    if(confirm("¿Está seguro que desea cancelar el contrato actual?. Está operación no puede reversarse una vez realizada.")){
        var strConsecutivo = $("input[name='txtSeleccion']:checked").val();
        var strAccion = "CANCELAR";       

        AJAXC("POST","Visualizacion","txtAccion=contratos&txtEvento=" + strAccion + "&txtConsecutivo=" + strConsecutivo,"dMostrar");
    }
}

function buscarRegistro(strAccion){
    form = document.frmBusqueda;  

    var strMsg = "";
    var strHead = "Antes de continuar debe diligenciar los siguientes campos:\n\n";
	
    strMsg += validarCampoSelect(form.txtCriterio,"Criterio");
    strMsg += validarCampoVacio(form.txtClave,"Palabra clave");
    
    if (strMsg != ""){
        strMsg = strHead + strMsg;
        alert(strMsg);

        strCriterio = form.txtCriterio;
        strClave = form.txtClave.value;                

        if ((strCriterio.selectedIndex == 0) && (strClave == "")){
                form.txtCriterio.focus();
        }else{
                if (strCriterio.selectedIndex == 0){
                        form.txtCriterio.focus();
                }else{
                        form.txtClave.focus();
                }
        }

        return false;
      }
          
      AJAXC("POST","Visualizacion","txtAccion=" + strAccion + "&txtEvento=busqueda&txtCriterio=" + form.txtCriterio.value + "&txtClave=" + form.txtClave.value,"dMostrar");   
}

function generarArchivoPlanoContrato(){    
    if(confirm('¿Está seguro que desea generar el archivo plano de contratos?')){
        
        var elemento = document.getElementById("dMostrar");
        var strLoad = "";
                
        strLoad = strLoad + "<div align='center' class='TEXTOEXITO'>";        
        strLoad = strLoad + "<img src='Images/loader.gif' style='vertical-align: middle;' />&nbsp;&nbsp;Generando archivo...";
        strLoad = strLoad + "</div>";
        elemento.innerHTML = strLoad;
        
        AJAXC("POST","ArchivoPlanoContratos","","dMostrar");  
    }
}

function crearOTROSI(){
    if(!($("input[name='txtSeleccion']:radio").is(':checked'))) {         
        alert("Debe seleccionar un registro de contrato para continuar.");
        return false;
   }
   
    var strConsecutivo = $("input[name='txtSeleccion']:checked").val();
    window.open("otrosi.jsp?txtAccion=C&txtConsecutivo=" + strConsecutivo,"Otrosi",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
}