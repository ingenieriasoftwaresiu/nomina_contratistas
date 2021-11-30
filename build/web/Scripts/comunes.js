/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validarCampoVacio(campo,nombre){
	
    if (campo.value == ""){
            return nombre + "\n";
    }	
    return "";	
}

function validarCampoSelect(campo,nombre){
    if (campo.selectedIndex == 0){
        return nombre + "\n";
    }
    return "";
}

function cerrarSesion(){
    window.location = "cerrar.jsp";
}

function obtiene_fecha() {  
      
    var fecha_actual = new Date()  
  
    var dia = fecha_actual.getDate();  
    var mes = fecha_actual.getMonth() + 1;
    var anio = fecha_actual.getFullYear();  
  
    if (mes < 10)  
        mes = '0' + mes;  
  
    if (dia < 10)  
        dia = '0' + dia; 
  
    return (anio + "-" + mes + "-" + dia);
}

function incrementarDiasFecha(strFecha, numDias){
        strFecha.replace("-", "/").replace("-", "/");

        var fechaNueva= new Date(strFecha);                 	
        fechaNueva.setDate(fechaNueva.getDate() + parseInt(numDias));

        var anio=fechaNueva.getFullYear();
        var mes= fechaNueva.getMonth()+1;
        var dia= fechaNueva.getDate();

        if(mes.toString().length<2){
            mes="0".concat(mes);        
        }    

        if(dia.toString().length<2){
            dia="0".concat(dia);        
        }

        var strFechaNueva = anio+"-"+mes+"-"+dia;
        
        return strFechaNueva;
}

function setCalendario(campo,imagen){
    Calendar.setup({
        inputField: campo,
        ifFormat: "%Y-%m-%d",
        button: imagen,
        align: "TI",
        singleClick: true
    });
}

function deleteSpecialSigns(strCadena){   
    
    strCadena = strCadena.replace("#","Nro.");   
    
    return strCadena;
    
}

function trim(strCadena){
    
    return strCadena.replace(/^\s+/g,'').replace(/\s+$/g,'');
}


function validarSiNumero(numero){
    var numero = false;
    
    if (!/^([0-9])*$/.test(numero)){
        numero = false;
    }else{
        numero = true;
    }        
  }
  
  function validarNumero(numero){
      if( isNaN(numero) ) {
             return false;
      }else{
          return true;
      }
  }
  
  function replaceCadena(cadena, strIni, strFin){
      var rest = cadena.replace(strIni,strFin).replace(strIni,strFin); 
      return rest;
  }
    
  function formatearTextoLago(cadena){
      var arrDatos = cadena.split(/\r\n|\r|\n/);
        
        var i = 0;
        var strCadena= "";

        while (i<arrDatos.length){
            if ((i+1) == arrDatos.length){
                 strCadena = strCadena + trim(arrDatos[i]);
            }else{
                strCadena = strCadena + trim(arrDatos[i]) + ">";
            }          
            i++;
        }
        return strCadena;
  }
  
 function popupCentrado(url,nombre,ancho,alto) {
    var posicion_x; 
    var posicion_y; 
    posicion_x=(screen.width/2)-(ancho/2); 
    posicion_y=(screen.height/2)-(alto/2); 
    window.open(url, nombre, "width="+ancho+",height="+alto+",menubar=0,toolbar=0,directories=0,scrollbars=yes,resizable=no,left="+posicion_x+",top="+posicion_y+"");
}

function descargarArchivo(strRuta){    
    while(strRuta.indexOf("/") != -1){
        strRuta = strRuta.replace("/","\\");
    }    
    
    window.open("Descargar.jsp?txtRuta=" + strRuta);    
}

function compare_dates(fecha, fecha2)  
  {  
    var xMonth=fecha.substring(5, 7);  
    var xYear=fecha.substring(0, 4);  
    var xDay=fecha.substring(8,10);  
            
    var yMonth=fecha2.substring(5, 7);  
    var yYear=fecha2.substring(0, 4);  
    var yDay=fecha2.substring(8,10);  
           
    if (xYear> yYear){  
        return(true)  
    }else{  
      if (xYear == yYear){   
          if (xMonth> yMonth){  
            return(true)  
        }else{   
          if (xMonth == yMonth){          
            if (xDay> yDay){  
              return(true);  
            }else{  
                if (xDay == yDay){  
                    return(true);
                }else{   
                    return(false);
                }                                  
            }
          }else{  
            return(false);  
          }
        }  
      }else{             
        return(false);            
      }
    }  
}
