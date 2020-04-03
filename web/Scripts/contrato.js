/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function cargaInicial(){
    $(".IMGERROR").hide();
    $("#dMensaje").html("");
    
     var strAccion = $("#txtAccion").val();
    
    if (strAccion == "C"){
         $("#txtConsecutivo").focus();
         var strFechaCreacion = $("#txtFechaCreacion").val();
        if (strFechaCreacion == ""){
            var strFechaCreacion = obtiene_fecha();
            $("#txtFechaCreacion").val(strFechaCreacion);
        }
        alert("ADVERTENCIA: Recuerde verificar la existencia del Contratista e Interventor antes de diligenciar el resto de la información del contrato.");
     }else{
         $("#txtContratista").focus();
     }
}

function cargarCalendarios(){
    setCalendario("txtFechaIni","imgFechaIni");
    setCalendario("txtFechaFin","imgFechaFin");
}

function refreshFechaFin(){
    var strDuracion = $("#txtDuracion").val();
    var strFechaIni = $("#txtFechaIni").val();
    
    if ((strFechaIni != "") && (strDuracion != "")){                
        var strFechaNueva = incrementarDiasFecha(strFechaIni,strDuracion);
        $("#txtFechaFin").val(strFechaNueva);
    }
}

function validarContrato(){
    var strConsecutivo = $.trim($("#txtConsecutivo").val());
        
        if (strConsecutivo == ""){
            $("#imgConsecutivo").show();
            $("#txtConsecutivo").focus();
            return false;
        }else{
            $("#imgConsecutivo").hide();
            strConsecutivo = strConsecutivo.replace(/\./g, '');
            $("#txtConsecutivo").val(strConsecutivo);
        }
        
        var strContratista = $("#txtContratista").val();
        
        if (strContratista == "-1"){
            $("#imgContratista").show();
            $("#txtContratista").focus();
            return false;
        }else{
            $("#imgContratista").hide();
        }
        
        var strTipoContrato = $("#txtTipoContrato").val();
        
        if (strTipoContrato == "-1"){
            $("#imgTipoContrato").show();
            $("#txtTipoContrato").focus();
            return false;
        }else{
            $("#imgTipoContrato").hide();
        }
        
        var strInterventor = $("#txtInterventor").val();
        
        if (strInterventor == "-1"){
            $("#imgInterventor").show();
            $("#txtInterventor").focus();
            return false;
        }else{
            $("#imgInterventor").hide();
        }
        
        var strGrupo = $("#txtGrupo").val();
        
        if (strGrupo == "-1"){
            $("#imgGrupo").show();
            $("#txtGrupo").focus();
            return false;
        }else{
            $("#imgGrupo").hide();
        }
        
        var strFechaIni = $("#txtFechaIni").val();
        
        if (strFechaIni == ""){
            $("#imgFechaInicio").show();
            $("#txtFechaIni").focus();
            return false;
        }else{
            $("#imgFechaInicio").hide();
        }
        
        var strDuracion = $("#txtDuracion").val();
        
        if (strDuracion == ""){
            $("#imgDuracion").show();
            $("#txtDuracion").focus();
            return false;
        }else{            
            $("#txtDuracion").val(replaceCadena(strDuracion,".",""));
            strDuracion = $("#txtDuracion").val();
             if (!(validarNumero(strDuracion))){                
                 $("#imgDuracion").show();
                alert("ADVERTENCIA: La duración del contrato debe ser un valor entero expresado en días.");            
                $("#txtDuracion").focus();
                 return false;
            }else{
                $("#imgDuracion").hide();
            }
        }
        
        var strValor = $("#txtValor").val();
        
        if (strValor == ""){
            $("#imgValor").show();
            $("#txtValor").focus();
            return false;
        }else{
            $("#txtValor").val(replaceCadena(strValor,".",""));
            strValor = $("#txtValor").val();
            if (!(validarNumero(strValor))){                
                $("#imgValor").show();
                alert("ADVERTENCIA: El valor del contrato debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                $("#txtValor").focus();
                 return false;
            }else{
                $("#imgValor").hide();
            }
        }
        
        var strCentroC = $("#txtCentroC").val();
        
        if (strCentroC == "-1"){
            $("#imgCentroC").show();
            $("#txtCentroC").focus();
            return false;
        }else{
            $("#imgCentroC").hide();
        }        
                   
        var strObjeto = $.trim($("#txtObjeto").text());
        
        if (strObjeto == ""){
            $("#imgObjeto").show();
            $("#txtObjeto").focus();
            return false;
        }else{
            $("#imgObjeto").hide();
            $("#txtObjetoC").val(strObjeto);
        }
        
        var strActaInicio = $.trim($("#txtActaInicio").val());
        var strRutaActaInicio = $("#txtRutaActaInicio").val();
       
       if (strActaInicio != ""){
            if (strRutaActaInicio == "-"){
                var extension = (strActaInicio.substring(strActaInicio.lastIndexOf("."))).toLowerCase();         
                if (extension != ".pdf"){
                    $("#imgActaInicio").show();
                    alert("El Acta de Inicio debe adjuntarse en formato PDF (.pdf).");
                    return false;
                }else{
                    $("#imgActaInicio").hide();
                }
            }else{
                $("#imgActaInicio").hide();
            }        
        }                
        
        var strActaFin = $.trim($("#txtActaFin").val());
        var strRutaActaFin = $("#txtRutaActaFinalizacion").val();
       
       if (strActaFin != ""){
            if (strRutaActaFin == "-"){
                var extension = (strActaFin.substring(strActaFin.lastIndexOf("."))).toLowerCase();         
                if (extension != ".pdf"){
                    $("#imgActaFin").show();
                    alert("El Acta de Finalización debe adjuntarse en formato PDF (.pdf).");
                    return false;
                }else{
                    $("#imgActaFin").hide();
                }
            }else{
                $("#imgActaFin").hide();
            }        
        }
        
        var strMinutaContrato = $.trim($("#txtMinutaContrato").val());
        var strRutaMinutaContrato = $("#txtRutaMinutaContrato").val();
       
       if (strMinutaContrato != ""){
            if (strRutaMinutaContrato == "-"){
                var extension = (strMinutaContrato.substring(strMinutaContrato.lastIndexOf("."))).toLowerCase();         
                if (extension != ".pdf"){
                    $("#imgMinutaContrato").show();
                    alert("La Minuta del Contrato debe adjuntarse en formato PDF (.pdf).");
                    return false;
                }else{
                    $("#imgMinutaContrato").hide();
                }
            }else{
                $("#imgMinutaContrato").hide();
            }        
      }      
      
      if(!(confirm("¿Está seguro(a) que desea guardar la información registrada en el contrato?. Presione 'Ok' para continuar o 'Cancelar' para editar la información ingresada."))){
          return false;      
      }      
}

$(document).ready(function(){    
    
    // Instrucciones ejecutadas cuando el formulario esté listo.
    
    cargaInicial();
});

$(function() {     
    /*$("#btnGuardar").on("click",function(){
        $("#dMensaje").show();
        
        var strConsecutivo = $("#txtConsecutivo").val();
        
        if (strConsecutivo == ""){
            $("#imgConsecutivo").show();
            $("#txtConsecutivo").focus();
            return false;
        }else{
            $("#imgConsecutivo").hide();
        }
        
        var strContratista = $("#txtContratista").val();
        
        if (strContratista == "-1"){
            $("#imgContratista").show();
            $("#txtContratista").focus();
            return false;
        }else{
            $("#imgContratista").hide();
        }
        
        var strTipoContrato = $("#txtTipoContrato").val();
        
        if (strTipoContrato == "-1"){
            $("#imgTipoContrato").show();
            $("#txtTipoContrato").focus();
            return false;
        }else{
            $("#imgTipoContrato").hide();
        }
        
        var strInterventor = $("#txtInterventor").val();
        
        if (strInterventor == "-1"){
            $("#imgInterventor").show();
            $("#txtInterventor").focus();
            return false;
        }else{
            $("#imgInterventor").hide();
        }
        
        var strGrupo = $("#txtGrupo").val();
        
        if (strGrupo == "-1"){
            $("#imgGrupo").show();
            $("#txtGrupo").focus();
            return false;
        }else{
            $("#imgGrupo").hide();
        }
        
        var strFechaIni = $("#txtFechaIni").val();
        
        if (strFechaIni == ""){
            $("#imgFechaInicio").show();
            $("#txtFechaIni").focus();
            return false;
        }else{
            $("#imgFechaInicio").hide();
        }
        
        var strDuracion = $("#txtDuracion").val();
        
        if (strDuracion == ""){
            $("#imgDuracion").show();
            $("#txtDuracion").focus();
            return false;
        }else{            
            $("#txtDuracion").val(replaceCadena(strDuracion,".",""));
            strDuracion = $("#txtDuracion").val();
             if (!(validarNumero(strDuracion))){                
                 $("#imgDuracion").show();
                alert("ADVERTENCIA: La duración del contrato debe ser un valor entero expresado en días.");            
                $("#txtDuracion").focus();
                 return false;
            }else{
                $("#imgDuracion").hide();
            }
        }
        
        var strValor = $("#txtValor").val();
        
        if (strValor == ""){
            $("#imgValor").show();
            $("#txtValor").focus();
            return false;
        }else{
            $("#txtValor").val(replaceCadena(strValor,".",""));
            strValor = $("#txtValor").val();
            if (!(validarNumero(strValor))){                
                $("#imgValor").show();
                alert("ADVERTENCIA: El valor del contrato debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                $("#txtValor").focus();
                 return false;
            }else{
                $("#imgValor").hide();
            }
        }
        
        var strCentroC = $("#txtCentroC").val();
        
        if (strCentroC == "-1"){
            $("#imgCentroC").show();
            $("#txtCentroC").focus();
            return false;
        }else{
            $("#imgCentroC").hide();
        }
        
        var strAccion = $("#txtAccion").val();
        var strForm = $("#txtForm").val();        
        var strFechaFin = $("#txtFechaFin").val();
        var strConsecutivoL = $("#txtConsecutivoL").val();   
        var strNumCDP = $("#txtNumCDP").val();  
        //var strObjeto = $.trim($("#txtObjeto").text());
        var strObjeto = document.getElementById("txtObjeto");
        var strIdEstado = $("#txtIdEstado").val();  
        var strFechaCreacion = $("#txtFechaCreacion").val();
        
        if(strObjeto.innerText != undefined){
            strObjeto.innerHTML=strObjeto.innerText;
        }else{
            strObjeto.innerHTML=strObjeto.textContent;
        }
        
        strObjeto = $.trim($("#txtObjeto").text());
        
        if (strObjeto == ""){
            $("#imgObjeto").show();
            $("#txtObjeto").focus();
            return false;
        }else{
            $("#imgObjeto").hide();
        }
        
        strObjeto = formatearTextoLago(strObjeto);
        
        var  dataString = "txtForm=" + strForm + "&txtAccion=" + strAccion + "&txtConsecutivo=" + strConsecutivo + "&txtContratista=" + strContratista + "&txtTipoContrato=" + strTipoContrato + "&txtInterventor=" + strInterventor
                                                + "&txtGrupo=" + strGrupo+ "&txtFechaIni=" + strFechaIni+ "&txtFechaFin=" + strFechaFin+ "&txtDuracion=" + strDuracion+ "&txtValor=" + strValor
                                                + "&txtCentroC=" + strCentroC+ "&txtConsecutivoL=" + strConsecutivoL + "&txtNumCDP=" + strNumCDP+ "&txtObjeto=" + strObjeto + "&txtEstado=" + strIdEstado  + "&txtFechaCreacion=" + strFechaCreacion;   
       
       // Envío de petición AJAX.
        
        AJAX("POST","Registro",dataString,"dMensaje");   
        
    });      */  
    
    $("#btnLimpiar").on("click",function(){
        $("#txtConsecutivo").val("");
        $("#txtContratista").val("-1");
        $("#txtTipoContrato").val("-1");
        $("#txtInterventor").val("-1");
        $("#txtGrupo").val("-1");
        $("#txtFechaIni").val("");
        $("#txtFechaFin").val("");
        $("#txtDuracion").val("");
        $("#txtValor").val("");
        $("#txtCentroC").val("-1");
        $("#txtConsecutivoL").val("");
        $("#txtNumCDP").val("");
        $("#txtObjeto").text("");
        cargaInicial();
    });
    
    $("#btnPlanPagos").click(function(){
        var strConsecutivo = $("#txtConsecutivo").val();
        window.open("plan_pagos.jsp?txtConsecutivo=" + strConsecutivo,"Plan_Pagos",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    });
    
    $("#btnOtrosi").click(function(){
        var strConsecutivo = $("#txtConsecutivo").val();
        window.open("lista_otrosi.jsp?txtConsecutivo=" + strConsecutivo,"Lista_Otrosi",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    });
    
    $("#txtValor").blur(function(){
        
        var strValor = $("#txtValor").val();
         if (strValor != ""){
            $("#txtValor").val(replaceCadena(strValor,".",""));
                strValor = $("#txtValor").val();
                if (!(validarNumero(strValor))){                
                    $("#imgValor").show();
                    alert("ADVERTENCIA: El valor del contrato debe ser un valor entero expresado en pesos colombianos sin puntos(.) ni comas(,).");            
                    $("#txtValor").focus();
                    return false;
                }else{
                    $("#imgValor").hide();
                }       
         }
    });
    
    $("#txtDuracion").blur(function(){
        var strDuracion = $("#txtDuracion").val();
        
        if (strDuracion != ""){
            $("#txtDuracion").val(replaceCadena(strDuracion,".",""));
                strDuracion = $("#txtDuracion").val();
                if (!(validarNumero(strDuracion))){                
                    $("#imgDuracion").show();
                    alert("ADVERTENCIA: La duración del contrato debe ser un valor entero expresado en días.");            
                    $("#txtDuracion").focus();
                    return false;
                }else{
                    $("#imgDuracion").hide();
                    refreshFechaFin();
                }
          }
    });
    
    $("#txtFechaIni").change(function(){
        var strDuracion = $("#txtDuracion").val();
        
        if (strDuracion != ""){
            $("#txtDuracion").val(replaceCadena(strDuracion,".",""));
                strDuracion = $("#txtDuracion").val();
                if (!(validarNumero(strDuracion))){                
                    $("#imgDuracion").show();
                    alert("ADVERTENCIA: La duración del contrato debe ser un valor entero expresado en días.");            
                    $("#txtDuracion").focus();
                    return false;
                }else{
                    $("#imgDuracion").hide();
                    refreshFechaFin();
                }
          }
    });
    
    $("#btnAgregarContratista").click(function(){
        window.open("contratista.jsp?txtAccion=C&txtTipoRegistro=C","Contratista",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    });
    
    $("#btnAgregarInterventor").click(function(){
        window.open("contratista.jsp?txtAccion=C&txtTipoRegistro=I","Interventor",'top=0,left=0,width='+(screen.availWidth)+',height ='+(screen.availHeight)+',toolbar=0 ,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=1,scrollbars=yes');
    });
    
    $("#btnBuscarContratista").click(function(){
        popupCentrado("buscarPersona.jsp?txtAccion=C","buscar_contratista",800,300);      
    });
    
    $("#btnBuscarInterventor").click(function(){
        popupCentrado("buscarPersona.jsp?txtAccion=I","buscar_interventor",800,300);      
    });
    
    $(".IMGREFRESH").click(function(){
        var strConsecutivo = $("#txtConsecutivo").val();
        location.reload();
        $("#txtConsecutivo").val(strConsecutivo);
    });
    
    $(".CAMPOFORM").focus(function(){
        $("#dMensaje").html("");  
    });
    
    $(".CAMPOSELECT").change(function(){
        $("#dMensaje").html("");  
    });
    
    $(".IMGCALENDAR").click(function(){
        $("#dMensaje").html("");  
    });    
    
    $("input[type=file]").filestyle({ 
        image: "Images/lupa.gif",
        imageheight : 17,
        imagewidth : 35,
        width : 180       
    });        
});