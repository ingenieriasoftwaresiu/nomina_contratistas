/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Conexion.GestionSQL;
import Negocio.Comunes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jorge.correa
 */
public class Visualizacion extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(true); 
            String strAccion = request.getParameter("txtAccion");  
            String strEvento = request.getParameter("txtEvento");
            String strCedula = (String) session.getAttribute("txtCedula");
            String strTipoUsuario = (String) session.getAttribute("txtTipoUsuario");
            
            int intRegistrosAEmpezar;
            int intPaginaActual;
            int intRegistrosAMostrar;
            String strTipoConsulta = "";
            String strTitulo = "";
            String strCabecera = "";
            String strBusqueda = "";
            String strSQL = "";
            String strHTML = ""; 
            String strSQLTotal = "";
            String[] strDatosGenerales=null;
            String strCriterio = "";
            String strClave = "";
            String strResult = null;
            String strReqBtnCrearReg = "S";
            String strNumColumnas = "";
            
            strSQL = "select g.txtNumRegPag from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";
            strDatosGenerales = GestionSQL.getFila(strSQL,"Nomina");            
            intRegistrosAMostrar = Integer.parseInt(strDatosGenerales[0]);              
            
            if (request.getParameter("txtPagina") != null){
                intPaginaActual = Integer.parseInt(request.getParameter("txtPagina"));                
                intRegistrosAEmpezar = (intPaginaActual - 1) * intRegistrosAMostrar;                
            }else{
                intRegistrosAEmpezar = 0;
                intPaginaActual = 1;
            }
            
            if (strEvento == null){
                strEvento = "";
            }
            
            if (strEvento.equals("CANCELAR")){
                String strMensaje = "";
                String strConsecutivo = request.getParameter("txtConsecutivo");                
                
                strResult = validarCancelacion(strConsecutivo);
                
                if (strResult == null){
                    strSQL = "update nomina.tbl_contratos set txtIdEstado = 'CA' WHERE txtConsecutivo = '" + strConsecutivo +"'";
                    strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");                      
                }else{
                    out.println("<script type='text/javascript'>");
                    out.println("document.getElementById('dMensaje').scrollIntoView();");
                    out.println("</script>");
                }
            }
            
            if (strEvento.equals("FINALIZAR")){
                String strMensaje = "";
                String strConsecutivo = request.getParameter("txtConsecutivo");       
                String strFechaFin = request.getParameter("txtFechaFin"); 
                
                strResult = validarFinalizacion(strConsecutivo);
                
                if (strResult == null){
                    strSQL = "update nomina.tbl_contratos set txtIdEstado = 'FN', dtFechaFin = '" + strFechaFin + "' WHERE txtConsecutivo = '" + strConsecutivo +"'";
                    strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");                    
                }else{
                    out.println("<script type='text/javascript'>");
                    out.println("document.getElementById('dMensaje').scrollIntoView();");
                    out.println("</script>");
                }
            }
            
            if (strEvento.equals("PONER_EJECUCION")){
                String strMensaje = "";
                String strConsecutivo = request.getParameter("txtConsecutivo");                
                
                strResult = validarPonerEjecucion(strConsecutivo);
                
                if (strResult == null){
                    strSQL = "update nomina.tbl_contratos set txtIdEstado = 'EJ' WHERE txtConsecutivo = '" + strConsecutivo +"'";
                    strMensaje = GestionSQL.ejecuta(strSQL, "Nomina");                    
                }else{
                    out.println("<script type='text/javascript'>");
                    out.println("document.getElementById('dMensaje').scrollIntoView();");
                    out.println("</script>");
                }
            }
            
            if (strEvento.equals("Eliminar")){
                String strMensaje = "";
                String strCodigo = request.getParameter("txtCodigo");
                
                 if (strAccion.equals("roles")){
                     strResult = validarRegistro(strCodigo,"roles");                                        

                    if (strResult == null){          
                            strSQL = "delete from nomina.tbl_roles where txtCodigo = '" + strCodigo + "'";                             
                    }
                 }
                 
                 if (strAccion.equals("estados")){
                     strResult = validarRegistro(strCodigo,"estados");                                        

                    if (strResult == null){          
                            strSQL = "delete from nomina.tbl_estados_contrato where txtCodigo = '" + strCodigo + "'";                             
                    }
                 }
                 
                 if (strAccion.equals("rolesXpersona")){       
                     String[] strDatos = strCodigo.split(">");                     
                     strSQL = "delete from nomina.tbl_roles_x_persona where txtIdRol = '" + strDatos[1] + "' and txtIdPersona = '" + strDatos[0]  + "'";
                 }
                 
                 if (strAccion.equals("tiposC")){       
                     strResult = validarRegistro(strCodigo,"tiposC");                                        

                    if (strResult == null){ 
                        strSQL = "delete from tbl_tipos_contrato where txtCodigo = '" + strCodigo + "'";
                    }
                 }
                 
                 if (strAccion.equals("contratistas")){
                     strResult = validarRegistro(strCodigo,"contratistas");                                        

                    if (strResult == null){ 
                        strSQL = "delete from tbl_contratistas where txtNumId = '" + strCodigo + "'";
                    }
                 }
                                  
                 if (strAccion.equals("interventoresXproy")){      
                     String[] strDatos = strCodigo.split(">"); 
                     strSQL = "delete from nomina.tbl_interventores_x_proyecto where txtIdInterventor = '" + strDatos[0] + "' and txtCodProyecto = '" +  strDatos[1] + "'";
                 }
                 
                 if (strAccion.equals("contratos")){                  
                      strResult = validarRegistro(strCodigo,"contratos");                                        

                    if (strResult == null){ 
                        strSQL = "delete from tbl_contratos where txtConsecutivo = '" + strCodigo + "'";    
                    }
                 }
                
                 if (!strSQL.equals("")){
                    strMensaje = GestionSQL.ejecuta(strSQL,"Nomina");                             
                    }
            }
            
            if (strAccion.equals("roles")){       
                strTipoConsulta = "2";
                strTitulo = "CONSULTA DE ROLES";
                strNumColumnas = "3";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE ROLES";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)' disabled='disabled'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='N' selected>Nombre</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                strSQL = "select r.txtCodigo, r.txtNombre  from nomina.tbl_roles r ORDER BY r.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                strSQLTotal = "select count(*) from nomina.tbl_roles";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Código</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Eliminar?</td>";
            }
            
            if (strAccion.equals("estados")){       
                strTipoConsulta = "2";
                strTitulo = "CONSULTA DE ESTADOS DEL CONTRATO";
                strNumColumnas = "3";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";     
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE ESTADOS";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)' disabled='disabled'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='N' selected>Nombre</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                strSQL = "select e.txtCodigo, e.txtNombre  from nomina.tbl_estados_contrato e ORDER BY e.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                strSQLTotal = "select count(*) from nomina.tbl_roles";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Código</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Eliminar?</td>";
            }
            
            if (strAccion.equals("rolesXpersona")){       
                strTipoConsulta = "4";
                strTitulo = "CONSULTA DE ROLES POR PERSONA";
                strNumColumnas = "3";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";              
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE ROLES/PERSONA";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='NP'>Nombre de la persona</option>";
                strBusqueda = strBusqueda + "<option value='NR'>Nombre del rol</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                strSQL = "select rxp.txtIdPersona, rxp.txtIdRol, p.txtNombreCompleto, r.txtNombre  from nomina.tbl_roles_x_persona rxp, nomina.tbl_roles r, users.users_personas p where (rxp.txtIdPersona = p.txtIdentificacion) and (rxp.txtIdRol = r.txtCodigo) ORDER BY p.txtNombreCompleto, r.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                strSQLTotal = "select count(*) from nomina.tbl_roles_x_persona rxp, nomina.tbl_roles r, users.users_personas p where (rxp.txtIdPersona = p.txtIdentificacion) and (rxp.txtIdRol = r.txtCodigo)";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre de la persona</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del rol</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Eliminar?</td>";
            }
            
            if (strAccion.equals("tiposC")){       
                strTipoConsulta = "2";
                strTitulo = "CONSULTA DE TIPOS DE CONTRATO";
                strNumColumnas = "3";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";             
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE TIPOS DE CONTRATO";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)' disabled='disabled'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='N' selected>Nombre</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                strSQL = "select tc.txtCodigo, tc.txtNombre from nomina.tbl_tipos_contrato tc ORDER BY tc.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                strSQLTotal = "select count(*) from nomina.tbl_tipos_contrato tc";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Código</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Eliminar?</td>";
            }
            
            if (strAccion.equals("contratistas")){       
                strTipoConsulta = "4";
                strTitulo = "CONSULTA DE CONTRATISTAS";
                strNumColumnas = "5";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";    
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE CONTRATISTAS";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='I'>Identificación</option>";
                strBusqueda = strBusqueda + "<option value='N'>Nombres</option>";
                strBusqueda = strBusqueda + "<option value='A'>Apellidos</option>";
                strBusqueda = strBusqueda + "<option value='E'>Estado</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                strSQL = "select p.txtNumId, p.txtNombres, p.txtApellidos, IF(p.txtEstado = 'A','Activo','Inactivo') from nomina.tbl_contratistas p ORDER BY p.txtApellidos LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                strSQLTotal = "select COUNT(*) from nomina.tbl_contratistas p";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Identificación</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombres</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Apellidos</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Estado</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Eliminar?</td>";
            }
                        
            if (strAccion.equals("interventoresXproy")){       
                strTipoConsulta = "3";
                strTitulo = "CONSULTA DE INTERVENTORES POR PROYECTO";
                strNumColumnas = "4";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE INTERVENTORES/PROYECTO";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='I'>Identificación</option>";
                strBusqueda = strBusqueda + "<option value='N'>Nombre del interventor</option>";
                strBusqueda = strBusqueda + "<option value='C'>Código del proyecto</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                strSQL = "select ixp.txtIdInterventor, p.txtNombreCompleto, ixp.txtCodProyecto from nomina.tbl_interventores_x_proyecto ixp, users.users_personas p where ixp.txtIdInterventor = p.txtIdentificacion LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                strSQLTotal = "select COUNT(*) from nomina.tbl_interventores_x_proyecto ixp, users.users_personas p where ixp.txtIdInterventor = p.txtIdentificacion";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Identificación</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Código del proyecto</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Eliminar?</td>";
            }
            
            if (strAccion.equals("contratos")){       
                strTipoConsulta = "7";
                strReqBtnCrearReg = "S";
                strTitulo = "CONSULTA DE CONTRATOS ACTIVOS";
                strNumColumnas = "9";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";           
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE CONTRATOS";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='C'>Consecutivo</option>";
                strBusqueda = strBusqueda + "<option value='NC'>Nombre del contratista</option>";
                strBusqueda = strBusqueda + "<option value='E'>Estado</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' )";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Consecutivo</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del contratista</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del interventor</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Fecha de inicio<br>(aaaa-mm-dd)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Fecha de fin<br>(aaaa-mm-dd)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Valor ($)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Estado actual</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Seleccionar?</td>"; 
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Eliminar?</td>";  
            }
            
            if (strAccion.equals("contratosH")){       
                strTipoConsulta = "7";
                strReqBtnCrearReg = "N";
                strTitulo = "CONSULTA DE CONTRATOS HISTÓRICOS";
                strNumColumnas = "9";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";           
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE CONTRATOS";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='C'>Consecutivo</option>";
                strBusqueda = strBusqueda + "<option value='NC'>Nombre del contratista</option>";
                strBusqueda = strBusqueda + "<option value='E'>Estado</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' )";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Consecutivo</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del contratista</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del interventor</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Fecha de inicio<br>(aaaa-mm-dd)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Fecha de fin<br>(aaaa-mm-dd)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Valor ($)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Estado actual</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Seleccionar?</td>"; 
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Eliminar?</td>";  
            }
            
            if (strAccion.equals("contratosP")){        
                strTipoConsulta = "7";
                strReqBtnCrearReg = "N";
                strTitulo = "CONSULTA DE CONTRATOS ACTIVOS";
                strNumColumnas = "8";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE CONTRATOS";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='C'>Consecutivo</option>";
                strBusqueda = strBusqueda + "<option value='N'>Nombre contratista</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                if(strTipoUsuario.equals("C")){                    
                    strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdContratista = '" + strCedula + "') order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                    strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdContratista = '" + strCedula + "')";
                }                
                if(strTipoUsuario.equals("I")){                    
                    strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdInterventor = '" + strCedula + "') order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                    strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdInterventor = '" + strCedula + "')";
                }
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Consecutivo</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del contratista</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del interventor</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Fecha de inicio<br>(aaaa-mm-dd)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Fecha de fin<br>(aaaa-mm-dd)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Valor ($)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Estado actual</td>\n";    
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Seleccionar?</td>";
            }
            
            if (strAccion.equals("contratosPH")){       
                strTipoConsulta = "7";
                strReqBtnCrearReg = "N";
                strTitulo = "CONSULTA DE CONTRATOS HISTÓRICOS";
                strNumColumnas = "8";
                strCabecera = "<td colspan='" + strNumColumnas + "' class='TITULOMENU'>" + strTitulo + "</td>";
                strBusqueda = strBusqueda + "<form id='frmBusqueda' name='frmBusqueda' method='POST' action='#'>";
                strBusqueda = strBusqueda + "<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLAFORM'>";
                strBusqueda = strBusqueda + "<tr>";
                strBusqueda = strBusqueda + "<td class ='TITULOBUSQUEDA'>";
                strBusqueda = strBusqueda + "BÚSQUEDA DE CONTRATOS";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Criterio:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<select name='txtCriterio' id='txtCriterio' class='CAMPOFORMBUSQUEDA' style='height: 20px;' OnKeyPress='return disableKeyPress(event)'>&nbsp;";
                strBusqueda = strBusqueda + "<option value='S'>Seleccione una opción</option>";
                strBusqueda = strBusqueda + "<option value='C'>Consecutivo</option>";
                strBusqueda = strBusqueda + "<option value='N'>Nombre contratista</option>";
                strBusqueda = strBusqueda + "</select>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class ='LABELFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "Palabra clave:&nbsp;";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDACAMPOFORMBUSQUEDA'>";
                strBusqueda = strBusqueda + "<input type='text' name='txtClave' id='txtClave' class='CAMPOFORMBUSQUEDA' OnKeyPress='return disableKeyPress(event)'>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "<td class='CELDAIMAGENBUSQUEDA'>";
                strBusqueda = strBusqueda + "<a href=\"#\" onclick=\"buscarRegistro('" + strAccion + "')\"><img src='Images/lupa.gif'id='Buscar' class='IMAGENBUSQUEDA'></a>";
                strBusqueda = strBusqueda + "</td>";
                strBusqueda = strBusqueda + "</tr>";
                strBusqueda = strBusqueda + "</table>"; 
                strBusqueda = strBusqueda + "</form>";
                if(strTipoUsuario.equals("C")){                    
                    strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdContratista = '" + strCedula + "') order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                    strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdContratista = '" + strCedula + "')";
                }                
                if(strTipoUsuario.equals("I")){                    
                    strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdInterventor = '" + strCedula + "') order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                    strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdInterventor = '" + strCedula + "')";
                }
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Consecutivo</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del contratista</td>\n";               
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Nombre del interventor</td>\n";
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Fecha de inicio<br>(aaaa-mm-dd)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Fecha de fin<br>(aaaa-mm-dd)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Valor ($)</td>\n";  
                strHTML = strHTML + "<td class='SUBTITULOMENU'>Estado actual</td>\n";    
                strHTML = strHTML + "<td class='SUBTITULOMENU'>¿Seleccionar?</td>";
            }
            
             if (strEvento != null){                  
                if (strEvento.equals("busqueda")){                    
                    strCriterio = request.getParameter("txtCriterio");
                    strClave = request.getParameter("txtClave");    
                    
                    if (strAccion.equals("roles")){
                        if (strCriterio.equals("N")){
                            strSQL = "select r.txtCodigo, r.txtNombre  from nomina.tbl_roles r where r.txtNombre like '%" + strClave + "%' ORDER BY r.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                            strSQLTotal = "select count(*) from nomina.tbl_roles r where r.txtNombre like '%" + strClave + "%'";  
                        }
                    }
                    
                    if (strAccion.equals("estados")){
                        if (strCriterio.equals("N")){
                            strSQL = "select e.txtCodigo, e.txtNombre  from nomina.tbl_estados_contrato e where e.txtNombre like '%" + strClave + "%' ORDER BY e.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                            strSQLTotal = "select count(*) from nomina.tbl_estados_contrato e where e.txtNombre like '%" + strClave + "%'";  
                        }
                    }
                    
                     if (strAccion.equals("rolesXpersona")){       
                          if (strCriterio.equals("NP")){
                                strSQL = "select rxp.txtIdPersona, rxp.txtIdRol, p.txtNombreCompleto, r.txtNombre  from nomina.tbl_roles_x_persona rxp, nomina.tbl_roles r, users.users_personas p where (rxp.txtIdPersona = p.txtIdentificacion) and (rxp.txtIdRol = r.txtCodigo) and p.txtNombreCompleto like '%" + strClave + "%' ORDER BY p.txtNombreCompleto, r.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select count(*) from nomina.tbl_roles_x_persona rxp, nomina.tbl_roles r, users.users_personas p where (rxp.txtIdPersona = p.txtIdentificacion) and (rxp.txtIdRol = r.txtCodigo) and p.txtNombreCompleto like '%" + strClave + "%'";
                          }
                          
                          if (strCriterio.equals("NR")){
                                strSQL = "select rxp.txtIdPersona, rxp.txtIdRol, p.txtNombreCompleto, r.txtNombre  from nomina.tbl_roles_x_persona rxp, nomina.tbl_roles r, users.users_personas p where (rxp.txtIdPersona = p.txtIdentificacion) and (rxp.txtIdRol = r.txtCodigo) and r.txtNombre like '%" + strClave + "%' ORDER BY p.txtNombreCompleto, r.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select count(*) from nomina.tbl_roles_x_persona rxp, nomina.tbl_roles r, users.users_personas p where (rxp.txtIdPersona = p.txtIdentificacion) and (rxp.txtIdRol = r.txtCodigo) and r.txtNombre like '%" + strClave + "%'";
                          }            
                     }
                     
                     if (strAccion.equals("tiposC")){       
                         if (strCriterio.equals("N")){
                            strSQL = "select tc.txtCodigo, tc.txtNombre from nomina.tbl_tipos_contrato tc where tc.txtNombre like '%" + strClave + "%' ORDER BY tc.txtNombre LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                            strSQLTotal = "select count(*) from nomina.tbl_tipos_contrato tc where tc.txtNombre like '%" + strClave + "%'"; 
                         }
                     }                     
                     
                      if (strAccion.equals("contratistas")){  
                          if (strCriterio.equals("I")){
                            strSQL = "select p.txtNumId, p.txtNombres, p.txtApellidos, IF(p.txtEstado = 'A','Activo','Inactivo') from nomina.tbl_contratistas p where p.txtNumId = '" + strClave + "' ORDER BY p.txtApellidos LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                            strSQLTotal = "select count(*) from nomina.tbl_contratistas p where p.txtNumId = '" + strClave + "'"; 
                         }
                          
                          if (strCriterio.equals("N")){
                            strSQL = "select p.txtNumId, p.txtNombres, p.txtApellidos, IF(p.txtEstado = 'A','Activo','Inactivo') from nomina.tbl_contratistas p where p.txtNombres like '%" + strClave + "%' ORDER BY p.txtApellidos LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                            strSQLTotal = "select count(*) from nomina.tbl_contratistas p where p.txtNombres like '%" + strClave + "%'"; 
                         }
                          
                          if (strCriterio.equals("A")){
                            strSQL = "select p.txtNumId, p.txtNombres, p.txtApellidos, IF(p.txtEstado = 'A','Activo','Inactivo') from nomina.tbl_contratistas p where p.txtApellidos like '%" + strClave + "%' ORDER BY p.txtApellidos LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                            strSQLTotal = "select count(*) from nomina.tbl_contratistas p where p.txtApellidos like '%" + strClave + "%'"; 
                         }
                          
                          if (strCriterio.equals("E")){
                            strSQL = "select p.txtNumId, p.txtNombres, p.txtApellidos, IF(p.txtEstado = 'A','Activo','Inactivo') from nomina.tbl_contratistas p where IF(p.txtEstado = 'A','Activo','Inactivo') like '%" + strClave + "%' ORDER BY p.txtApellidos LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                            strSQLTotal = "select count(*) from nomina.tbl_contratistas p where IF(p.txtEstado = 'A','Activo','Inactivo') like '%" + strClave + "%'"; 
                         }
                      }
                                            
                      if (strAccion.equals("interventoresXproy")){       
                            if (strCriterio.equals("I")){
                                strSQL = "select ixp.txtIdInterventor, p.txtNombreCompleto, ixp.txtCodProyecto from nomina.tbl_interventores_x_proyecto ixp, users.users_personas p where ixp.txtIdInterventor = p.txtIdentificacion and ixp.txtIdInterventor = '" + strClave + "' LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select count(*) from nomina.tbl_interventores_x_proyecto ixp, users.users_personas p where ixp.txtIdInterventor = p.txtIdentificacion and ixp.txtIdInterventor = '" + strClave + "'";
                            }
                            
                            if (strCriterio.equals("N")){
                                strSQL = "select ixp.txtIdInterventor, p.txtNombreCompleto, ixp.txtCodProyecto from nomina.tbl_interventores_x_proyecto ixp, users.users_personas p where ixp.txtIdInterventor = p.txtIdentificacion and p.txtNombreCompleto like '%" + strClave + "%' LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select count(*) from nomina.tbl_interventores_x_proyecto ixp, users.users_personas p where ixp.txtIdInterventor = p.txtIdentificacion and p.txtNombreCompleto = '%" + strClave + "%'";
                            }
                           
                            if (strCriterio.equals("C")){
                                strSQL = "select ixp.txtIdInterventor, p.txtNombreCompleto, ixp.txtCodProyecto from nomina.tbl_interventores_x_proyecto ixp, users.users_personas p where ixp.txtIdInterventor = p.txtIdentificacion and ixp.txtCodProyecto like '%" + strClave + "%' LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select count(*) from nomina.tbl_interventores_x_proyecto ixp, users.users_personas p where ixp.txtIdInterventor = p.txtIdentificacion and ixp.txtCodProyecto like '%" + strClave + "%'";
                            }
                      }
                      
                      if (strAccion.equals("contratos")){       
                            if (strCriterio.equals("C")){
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and c.txtConsecutivo = '" + strClave + "' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and c.txtConsecutivo = '" + strClave + "'";
                            }
                           
                            if (strCriterio.equals("NC")){
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%'";
                            }            
                            
                            if (strCriterio.equals("E")){
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and e.txtNombre like '%" + strClave + "%' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and e.txtNombre like '%" + strClave + "%'";
                            }                                                       
                      }
                      
                      if (strAccion.equals("contratosH")){       
                            if (strCriterio.equals("C")){
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and c.txtConsecutivo = '" + strClave + "' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and c.txtConsecutivo = '" + strClave + "'";
                            }
                           
                            if (strCriterio.equals("NC")){
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%'";
                            }            
                            
                            if (strCriterio.equals("E")){
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and e.txtNombre like '%" + strClave + "%' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and e.txtNombre like '%" + strClave + "%'";
                            }                                                       
                      }
                      
                      if (strAccion.equals("contratosP")){       
                          if (strCriterio.equals("C")){    
                              if(strTipoUsuario.equals("C")){ 
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdContratista = '" + strCedula + "') and (c.txtConsecutivo = '" + strClave + "') order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdContratista = '" + strCedula +"') and (c.txtConsecutivo = '" + strClave + "')";
                              }
                              
                              if(strTipoUsuario.equals("I")){ 
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdInterventor = '" + strCedula + "') and (c.txtConsecutivo = '" + strClave + "') order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdInterventor = '" + strCedula +"') and (c.txtConsecutivo = '" + strClave + "')";
                              }
                          }
                          
                          if (strCriterio.equals("N")){      
                            if(strTipoUsuario.equals("C")){ 
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdContratista = '" + strCedula + "') and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdContratista = '" + strCedula + "') and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%'";
                            }
                            
                            if(strTipoUsuario.equals("I")){ 
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdInterventor = '" + strCedula + "') and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'FN' and c.txtIdEstado <> 'CA' ) and (c.txtIdInterventor = '" + strCedula + "') and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%'";
                            }
                          }
                      }                    
                      
                      if (strAccion.equals("contratosPH")){       
                          if (strCriterio.equals("C")){    
                              if(strTipoUsuario.equals("C")){ 
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdContratista = '" + strCedula + "') and (c.txtConsecutivo = '" + strClave + "') order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdContratista = '" + strCedula +"') and (c.txtConsecutivo = '" + strClave + "')";
                              }
                              
                              if(strTipoUsuario.equals("I")){ 
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdInterventor = '" + strCedula + "') and (c.txtConsecutivo = '" + strClave + "') order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdInterventor = '" + strCedula +"') and (c.txtConsecutivo = '" + strClave + "')";
                              }
                          }
                          
                          if (strCriterio.equals("N")){      
                            if(strTipoUsuario.equals("C")){ 
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdContratista = '" + strCedula + "') and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdContratista = '" + strCedula + "') and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%'";
                            }
                            
                            if(strTipoUsuario.equals("I")){ 
                                strSQL = "select c.txtConsecutivo, CONCAT(p.txtApellidos,' ',p.txtNombres), c.txtIdInterventor, c.dtFechaInicio, c.dtFechaFin, c.txtValor, e.txtNombre from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdInterventor = '" + strCedula + "') and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%' order by c.txtConsecutivo LIMIT " + intRegistrosAEmpezar + "," + intRegistrosAMostrar;     
                                strSQLTotal = "select COUNT(*) from nomina.tbl_contratos c, nomina.tbl_contratistas p, nomina.tbl_estados_contrato e where (c.txtIdContratista = p.txtNumId) and (c.txtIdEstado = e.txtCodigo) and (c.txtIdEstado <> 'EJ' and c.txtIdEstado <> 'CR' ) and (c.txtIdInterventor = '" + strCedula + "') and CONCAT(p.txtNombres,' ',p.txtApellidos) like '%" + strClave + "%'";
                            }
                          }
                      } 
                }
             }
            
            Vector arrDatos = GestionSQL.consultaSQL(strSQL,"Nomina",strTipoConsulta);
            String strTablaAcciones = "";
            
            if (arrDatos == null){            
                out.println("<html>");
                out.println("<head>");                
                out.println("</head>");
                out.println("<body OnKeyPress='return disableKeyPress(event)'>");                
                if (strEvento.equals("busqueda")){
                    out.println("<div class='TEXTOFALLO'>");
                    out.println("No se encontraron registros para visualizar.");
                    out.println("</div>");
                    out.println("<br>");
                    out.println("<span class='TEXTOEXITO'>");
                    out.println("<input type='button' value='Regresar' class='BOTONFORM' onclick=\"AJAX('POST','Visualizacion','txtAccion=" + strAccion + "','dMostrar');\" />");
                    out.println("</span>");
                }else{
                    strTablaAcciones += "<table cellpadding='3' cellspacing='0' border='0' width='100%'>\n";
                    strTablaAcciones += "<tr>\n";
                    strTablaAcciones += "<td class='CELDAICONOREFRESH'>\n";
                    strTablaAcciones += "<form name='frmRefresh' id='frmRefresh'>\n";
                    strTablaAcciones += "<img src='Images/Refresh.png' id='btnRefresh' class='ICONOREFRESH' onclick=\"AJAXC('POST','Visualizacion','txtAccion=" + strAccion + "','dMostrar');\">\n";
                    strTablaAcciones += "</form>\n";
                    strTablaAcciones += "</td>\n";
                    strTablaAcciones += "<td class='TEXTOREFRESH'>\n";
                    strTablaAcciones += "<a href='#' onclick=\"AJAXC('POST','Visualizacion','txtAccion=" + strAccion + "','dMostrar');\">Actualizar datos</a>\n";                    
                    strTablaAcciones += "</td>\n";              
                    if (strReqBtnCrearReg.equals("S")){
                        strTablaAcciones += "<td>\n";
                        strTablaAcciones += "<input type='button' value='Crear registro' class='BOTONACCION' onclick='crearRegistro(\"" + strAccion + "\");'>\n";
                        strTablaAcciones += "</td>\n";
                    }
                    strTablaAcciones += "</tr>\n";
                    strTablaAcciones += "</table>\n";

                    // Inicio de la impresión en el navegador.

                    out.println("<html>");
                    out.println("<head>");                
                    out.println("</head>");
                    out.println("<body OnKeyPress='return disableKeyPress(event)'>");
                    out.println(strBusqueda);
                    out.println("<div style='height:10px;'></div>");
                    out.println(strTablaAcciones);    
                    out.println("<div style='height:10px;'></div>");
                    out.println("<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLARESULT'>");
                    out.println("<tr>");
                    out.println(strCabecera);
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println(strHTML);
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan='" + strNumColumnas + "' class='TEXTOFALLO' style='text-align: center; vertical-align: middle;padding-top:0px;font-size: 13px;border-bottom: 1px solid #116043;'>NO SE ENCONTRARON REGISTROS</td>");                    
                    out.println("</tr>");
                    out.println("</table>");
                }                
                out.println("</body>");
                out.println("</html>");
            }else{                 
                                
                strTablaAcciones += "<table cellpadding='3' cellspacing='0' border='0' width='100%'>\n";
                strTablaAcciones += "<tr>\n";
                strTablaAcciones += "<td class='CELDAICONOREFRESH'>\n";
                strTablaAcciones += "<form name='frmRefresh' id='frmRefresh'>\n";
                strTablaAcciones += "<img src='Images/Refresh.png' id='btnRefresh' class='ICONOREFRESH' onclick=\"AJAXC('POST','Visualizacion','txtAccion=" + strAccion + "','dMostrar');\">\n";
                strTablaAcciones += "</form>\n";
                strTablaAcciones += "</td>\n";
                strTablaAcciones += "<td class='TEXTOREFRESH'>\n";
                strTablaAcciones += "<a href='#' onclick=\"AJAXC('POST','Visualizacion','txtAccion=" + strAccion + "','dMostrar');\">Actualizar datos</a>\n";                
                strTablaAcciones += "</td>\n";
                if ((strAccion.equals("contratos")) || (strAccion.equals("contratosH")) || (strAccion.equals("contratosP")) || (strAccion.equals("contratosPH"))){ 
                    strTablaAcciones += "<td style='text-align: right;width: 105px;'>\n";
                    strTablaAcciones += "<input type='button' value='Ver plan de pagos' class='BOTONACCION' onclick='verPlanPagos();' style='width: 105px;'>\n";                    
                    strTablaAcciones += "</td>\n";                    
                    strTablaAcciones += "<td style='text-align: right;width: 105px;'>\n";
                    strTablaAcciones += "<input type='button' value='Ver OTROSIs' class='BOTONACCION' onclick='verOTROSIs();' style='width: 105px;'>\n";                    
                    strTablaAcciones += "</td>\n";
                    if(strTipoUsuario.equals("A")){ 
                        if (strAccion.equals("contratos")) {
                            strTablaAcciones += "<td style='text-align: right;'>\n";
                            strTablaAcciones += "<input type='button' value='Crear OTROSI' class='BOTONACCION' onclick='crearOTROSI();' style='width: 105px;'>\n";
                            strTablaAcciones += "</td>\n";
                            strTablaAcciones += "<td style='text-align: right;'>\n";
                            strTablaAcciones += "<input type='button' value='Poner En Ejecución' class='BOTONACCION' onclick='ponerEnEjecucion();' style='width: 105px;'>\n";
                            strTablaAcciones += "</td>\n";
                            strTablaAcciones += "<td style='text-align: right;'>\n";
                            strTablaAcciones += "<input type='button' value='Finalizar contrato' class='BOTONACCION' onclick='finalizarContrato();' style='width: 105px;'>\n";
                            strTablaAcciones += "</td>\n";
                            strTablaAcciones += "<td style='text-align: right;'>\n";
                            strTablaAcciones += "<input type='button' value='Cancelar contrato' class='BOTONACCION' onclick='cancelarContrato();' style='width: 105px;'>\n";
                            strTablaAcciones += "</td>\n";
                        }                        
                    }
                }
                if (strReqBtnCrearReg.equals("S")){
                    strTablaAcciones += "<td style='text-align: right;'>\n";
                    strTablaAcciones += "<input type='button' value='Crear registro' class='BOTONACCION' onclick='crearRegistro(\"" + strAccion + "\");'>\n";
                    strTablaAcciones += "</td>\n";
                }
                strTablaAcciones += "</tr>\n";
                strTablaAcciones += "</table>\n";
                
                // Inicio de la impresión en el navegador.
                
                out.println("<html>");
                out.println("<head>");                
                out.println("</head>");
                out.println("<body OnKeyPress='return disableKeyPress(event)'>");
                out.println(strBusqueda);
                out.println("<div style='height:10px;'></div>");
                out.println(strTablaAcciones);                    
                out.println("<div id='dMensaje' align='center'>");
                if (!(strResult == null)){           
                    out.println(strResult);                                
                }
                out.println("</div>");       
                out.println("<div style='height:10px;'></div>");
                out.println("<table cellpadding='5' cellspacing='0' border='0' width='100%' class='TABLARESULT'>");
                out.println("<tr>");
                out.println(strCabecera);
                out.println("</tr>");
                out.println("<tr>");
                out.println(strHTML);
                out.println("</tr>");
                
                String[] strTemp = null;
                Comunes comun = new Comunes();
                
                 for(int i=0;i<arrDatos.size();i++){
                    strTemp = arrDatos.get(i).toString().split(">");                      
                    out.println("<tr class='FILARESULT'>");                                                     
                     
                    if (strAccion.equals("roles")){ 
                         out.println("<td class=\"TEXTORESULT\"><a href=\"#\" onclick=\"abrirRegRol('" + strTemp[0] + "')\">" + strTemp[0] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegRol('" + strTemp[0] + "')\">" + strTemp[1] + "</a></td>");     
                         out.println("<td class='TEXTORESULT'><img src='Images/Delete.png' width='11px' height='11px' onclick=\"eliminarRegistro('" + strTemp[0] + "','" + strAccion + "')\"></td>");         
                     } 
                    
                    if (strAccion.equals("estados")){     
                        out.println("<td class=\"TEXTORESULT\"><a href=\"#\" onclick=\"abrirRegEstado('" + strTemp[0] + "')\">" + strTemp[0] + "</a></td>");
                        out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegEstado('" + strTemp[0] + "')\">" + strTemp[1] + "</a></td>");     
                        out.println("<td class='TEXTORESULT'><img src='Images/Delete.png' width='11px' height='11px' onclick=\"eliminarRegistro('" + strTemp[0] + "','" + strAccion + "')\"></td>");  
                    }
                    
                    if (strAccion.equals("rolesXpersona")){ 
                         out.println("<td class=\"TEXTORESULT\"><a href=\"#\" onclick=\"abrirRegRolXPersona('" + strTemp[0] + ">" +  strTemp[1] + "')\">" + strTemp[2] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegRolXPersona('" + strTemp[0] + ">" +  strTemp[1] + "')\">" + strTemp[3] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><img src='Images/Delete.png' width='11px' height='11px' onclick=\"eliminarRegistro('" + strTemp[0] + ">" +  strTemp[1] + "','" + strAccion + "')\"></td>");         
                     } 
                    
                    if (strAccion.equals("tiposC")){ 
                         out.println("<td class=\"TEXTORESULT\"><a href=\"#\" onclick=\"abrirRegTipoC('" + strTemp[0] + "')\">" + strTemp[0] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegTipoC('" + strTemp[0] + "')\">" + strTemp[1] + "</a></td>");     
                         out.println("<td class='TEXTORESULT'><img src='Images/Delete.png' width='11px' height='11px' onclick=\"eliminarRegistro('" + strTemp[0] + "','" + strAccion + "')\"></td>");         
                     } 
                    
                     if (strAccion.equals("contratistas")){ 
                         out.println("<td class=\"TEXTORESULT\"><a href=\"#\" onclick=\"abrirRegContratista('" + strTemp[0] + "')\">" + comun.marcarMiles(strTemp[0]) + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContratista('" + strTemp[0] + "')\">" + strTemp[1] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContratista('" + strTemp[0] + "')\">" + strTemp[2] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContratista('" + strTemp[0] + "')\">" + strTemp[3] + "</a></td>");                        
                         out.println("<td class='TEXTORESULT'><img src='Images/Delete.png' width='11px' height='11px' onclick=\"eliminarRegistro('" + strTemp[0] + "','" + strAccion + "')\"></td>");         
                     }              
                                                                
                     if (strAccion.equals("interventoresXproy")){                          
                         out.println("<td class=\"TEXTORESULT\"><a href=\"#\" onclick=\"abrirRegInterventorXProy('" + strTemp[0] + ">" +  strTemp[2] + "')\">" + strTemp[0] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegInterventorXProy('" + strTemp[0] + ">" +  strTemp[2] + "')\">" +  strTemp[1] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegInterventorXProy('" + strTemp[0] + ">" +  strTemp[2] + "')\">" + strTemp[2] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><img src='Images/Delete.png' width='11px' height='11px' onclick=\"eliminarRegistro('" + strTemp[0] + ">" +  strTemp[2] + "','" + strAccion + "')\"></td>");         
                     }                    
                     
                     if ((strAccion.equals("contratos")) || (strAccion.equals("contratosH"))){                                                   
                         out.println("<td class=\"TEXTORESULT\"><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[0] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[1] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + getNomInterventor(strTemp[2]) + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[3] + "</a></td>");                
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[4] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + comun.marcarMiles(strTemp[5]) + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[6] + "</a></td>"); 
                         out.println("<td class='TEXTORESULT'><input type='radio' name='txtSeleccion' value='" + strTemp[0] + "' /></td>");
                         out.println("<td class='TEXTORESULT'><img src='Images/Delete.png' width='11px' height='11px' onclick=\"eliminarRegistro('" + strTemp[0] + "','" + strAccion + "')\"></td>");         
                     }
                     
                     if ((strAccion.equals("contratosP")) || (strAccion.equals("contratosPH"))){                                                   
                         out.println("<td class=\"TEXTORESULT\"><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[0] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[1] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + getNomInterventor(strTemp[2]) + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[3] + "</a></td>");                
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[4] + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + comun.marcarMiles(strTemp[5]) + "</a></td>");
                         out.println("<td class='TEXTORESULT'><a href=\"#\" onclick=\"abrirRegContrato('" + strTemp[0] + "')\">" + strTemp[6] + "</a></td>");                
                         out.println("<td class='TEXTORESULT'><input type='radio' name='txtSeleccion' value='" + strTemp[0] + "' /></td>");
                     }
                     
                 }                               
                 
                out.println("</table>");
                out.println("<br>");                
                 
                //Paginación.
                
                out.println("<table cellpadding='5' cellspacing='0' border='0' width='100%'>");
                out.println("<tr>");
                out.println("<td style='text-align:center;'>");
                
                int  intPagAnt, intPagSig, intPagUlt, intNroRegistros;
                float ftRes;
                                
                String[] strDatos = GestionSQL.getFila(strSQLTotal,"Nomina");
                intNroRegistros = Integer.parseInt(strDatos[0]);                    

                intPagAnt = intPaginaActual - 1;
                intPagSig = intPaginaActual + 1;
                intPagUlt = (intNroRegistros/intRegistrosAMostrar);
                               
                ftRes = (intNroRegistros % intRegistrosAMostrar);
                
                if (ftRes>0){
                    intPagUlt = ((int)intPagUlt) + 1;
                }                
                
                if (strEvento.equals("busqueda")){
                    out.println("<a href=\"#\" onclick=\"AJAXC('POST','Visualizacion','txtEvento=busqueda&txtCriterio=" + strCriterio + "&txtClave=" + strClave + "&txtAccion=" + strAccion + "&txtPagina=1','dMostrar');\" class=\"TEXTOPAGINACION\">Primera</a>");
                    if (intPaginaActual > 1){
                        out.println("<a href=\"#\" onclick=\"AJAXC('POST','Visualizacion','txtEvento=busqueda&txtCriterio=" + strCriterio + "&txtClave=" + strClave + "&txtAccion=" + strAccion + "&txtPagina=" + intPagAnt + "','dMostrar');\" class=\"TEXTOPAGINACION\">Anterior</a>");
                    }
                    out.println("<span class='TEXTOPAGINACION' style='font-weight: bold;'>Página " + intPaginaActual + "/" + intPagUlt + "</span>");
                    if (intPaginaActual < intPagUlt){                        
                        out.println("<a href=\"#\" onclick=\"AJAXC('POST','Visualizacion','txtEvento=busqueda&txtCriterio=" + strCriterio + "&txtClave=" + strClave + "&txtAccion=" + strAccion + "&txtPagina=" + intPagSig + "','dMostrar');\" class=\"TEXTOPAGINACION\">Siguiente</a>");
                    } 
                    out.println("<a href=\"#\"  onclick=\"AJAXC('POST','Visualizacion','txtEvento=busqueda&txtCriterio=" + strCriterio + "&txtClave=" + strClave + "&txtAccion=" + strAccion + "&txtPagina=" + intPagUlt + "','dMostrar');\" class=\"TEXTOPAGINACION\">Última</a>");
                }else{
                    out.println("<a href=\"#\" onclick=\"AJAXC('POST','Visualizacion','txtAccion=" + strAccion + "&txtPagina=1','dMostrar');\" class=\"TEXTOPAGINACION\">Primera</a>");
                    if (intPaginaActual > 1){
                        out.println("<a href=\"#\" onclick=\"AJAXC('POST','Visualizacion','txtAccion=" + strAccion + "&txtPagina=" + intPagAnt + "','dMostrar');\" class=\"TEXTOPAGINACION\">Anterior</a>");
                    }
                    out.println("<span class='TEXTOPAGINACION' style='font-weight: bold;'>Página " + intPaginaActual + "/" + intPagUlt + "</span>");
                    if (intPaginaActual < intPagUlt){
                        out.println("<a href=\"#\" onclick=\"AJAXC('POST','Visualizacion','txtAccion=" + strAccion + "&txtPagina=" + intPagSig + "','dMostrar');\" class=\"TEXTOPAGINACION\">Siguiente</a>");
                    } 
                    out.println("<a href=\"#\"  onclick=\"AJAXC('POST','Visualizacion','txtAccion=" + strAccion + "&txtPagina=" + intPagUlt + "','dMostrar');\" class=\"TEXTOPAGINACION\">Última</a>");
                }
                
                out.println("</td>");
                out.println("</tr>");                          
                out.println("</table>");                   
                out.println("</body>");
                out.println("</html>");              
            }         
            
        } finally {            
            out.close();
        }
    }
    
    private String validarFinalizacion(String strConsecutivo){
        String strSQL="", strMensaje=null;
        String[] strResult = null;
        
        strSQL = "select c.txtIdEstado from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
        strResult = GestionSQL.getFila(strSQL,"Nomina");
        
        if(!(strResult[0].equals("EJ"))){
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<div class='TEXTOFALLO' style='padding-top: 5px;'>\n";
            strMensaje = strMensaje + "** ADVERTENCIA: El contrato sólo puede finalizarse si se encuentra en el estado En Ejecución. **\n";
            strMensaje = strMensaje + "</div>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";              
            return strMensaje;            
        }
        
        strResult = null;
        
        strSQL = "select c.txtConsecutivo from nomina.tbl_contratos c, nomina.tbl_plan_pagos p where (c.txtConsecutivo = p.txtIdContrato) and c.txtConsecutivo = '" + strConsecutivo + "' and p.txtIdEstadoPago <> 'E'";
        strResult = GestionSQL.getFila(strSQL,"Nomina");
        
        if(strResult != null){
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<div class='TEXTOFALLO' style='padding-top: 5px;'>\n";
            strMensaje = strMensaje + "** ADVERTENCIA: No se puede finalizar el contrato debido a que actualmente tiene pagos en ejecución. (Ver plan de pagos) **\n";
            strMensaje = strMensaje + "</div>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";  
            return strMensaje; 
        }
        
        return null;
    }
    
    private String validarPonerEjecucion(String strConsecutivo){
        String strSQL="", strMensaje=null;
        String[] strResult = null;
        
        strSQL = "select c.txtIdEstado from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
        strResult = GestionSQL.getFila(strSQL,"Nomina");
        
        if(!(strResult[0].equals("CR"))){
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<div class='TEXTOFALLO' style='padding-top: 5px;'>\n";
            strMensaje = strMensaje + "** ADVERTENCIA: El contrato sólo puede ponerse en ejecución si se encuentra en el estado Creado. **\n";
            strMensaje = strMensaje + "</div>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";  
            return strMensaje;            
        }
        
        Comunes comun = new Comunes();
        Date dtFechaHoy = comun.getDateFromString(comun.getFechaActual());
        
        strSQL = "select c.dtFechaFin from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
        strResult = GestionSQL.getFila(strSQL,"Nomina");
        Date dtFechaFin = comun.getDateFromString(strResult[0]);
        long lgDiferencia;
        
        lgDiferencia = comun.getDiasDiferenciaFechas(dtFechaHoy, dtFechaFin);      
        
        if(lgDiferencia <= 0){
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<div class='TEXTOFALLO' style='padding-top: 5px;'>\n";
            strMensaje = strMensaje + "** ADVERTENCIA: El contrato no puede ponerse en ejecución, ya que se superó la fecha de fin. **\n";
            strMensaje = strMensaje + "</div>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";  
            return strMensaje; 
        }
        
        return null;
    }
    
    private String validarCancelacion(String strConsecutivo){  
        String strSQL="", strMensaje=null;
        String[] strResult = null;
        
        strSQL = "select c.txtIdEstado from nomina.tbl_contratos c where c.txtConsecutivo = '" + strConsecutivo + "'";
        strResult = GestionSQL.getFila(strSQL,"Nomina");
        
        if(!(strResult[0].equals("CR"))){
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<div class='TEXTOFALLO' style='padding-top: 5px;'>\n";
            strMensaje = strMensaje + "** ADVERTENCIA: El contrato sólo puede cancelarse si se encuentra en el estado Creado. **\n";
            strMensaje = strMensaje + "</div>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";  
            return strMensaje;            
        }          
        
        strResult = null;
        
        strSQL = "select c.txtConsecutivo from nomina.tbl_contratos c, nomina.tbl_plan_pagos p where (c.txtConsecutivo = p.txtIdContrato) and c.txtConsecutivo = '" + strConsecutivo + "' and (p.txtIdEstadoPago <> 'P' and p.txtIdEstadoPago <> 'PA')";
        strResult = GestionSQL.getFila(strSQL,"Nomina");
        
        if(strResult != null){
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<div class='TEXTOFALLO' style='padding-top: 5px;'>\n";
            strMensaje = strMensaje + "** ADVERTENCIA: No se puede cancelar el contrato debido a que actualmente tiene pagos en ejecución. (Ver plan de pagos) **\n";
            strMensaje = strMensaje + "</div>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";  
            return strMensaje; 
        }
        
        return null;
    }          
            
    
    private String getNomInterventor(String strNumId){
        String [] strNomInt = null;
        String strSQL="";

        strSQL = "select p.txtNombreCompleto from users.users_personas p where p.txtIdentificacion = '" + strNumId + "'";
        strNomInt = GestionSQL.getFila(strSQL,"Users");

        return strNomInt[0];
    }
      
    
     private String validarRegistro(String strClave,String strAccion){
        
        String[] strResult = null;
        String strSQL = null;
        String strMensaje = null;
                        
        // Roles.
        
        if (strAccion.equals("roles")){              
            strSQL = "select rxp.txtIdRol from nomina.tbl_roles_x_persona rxp where rxp.txtIdRol = '" + strClave + "' GROUP BY rxp.txtIdRol";        
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<span class='TEXTOFALLO'>\n";
            strMensaje = strMensaje + "No se puede eliminar el registro de rol seleccionado debido a que se encuentra asociado con personas existentes.\n";
            strMensaje = strMensaje + "</span>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";                       
        }
        
        // Estados.
        
        if (strAccion.equals("estados")){              
            strSQL = "select c.txtConsecutivo from nomina.tbl_contratos c where c.txtIdEstado = '" + strClave + "' GROUP BY c.txtConsecutivo";        
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<span class='TEXTOFALLO'>\n";
            strMensaje = strMensaje + "No se puede eliminar el registro de estado seleccionado debido a que se encuentra asociado con contratos existentes.\n";
            strMensaje = strMensaje + "</span>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";                       
        }
        
        // Tipos de contrato.
        
        if (strAccion.equals("tiposC")){              
            strSQL = "select c.txtIdTipoContrato from nomina.tbl_contratos c where c.txtIdTipoContrato = '" + strClave + "' GROUP BY c.txtIdTipoContrato";        
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<span class='TEXTOFALLO'>\n";
            strMensaje = strMensaje + "No se puede eliminar el registro de tipo de contrato seleccionado debido a que se encuentra asociado con contratos existentes.\n";
            strMensaje = strMensaje + "</span>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";                       
        }
        
        // Contratistas.
        
        if (strAccion.equals("contratistas")){              
            strSQL = "select c.txtConsecutivo from tbl_contratos c where c.txtIdContratista = '" + strClave + "'";        
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<span class='TEXTOFALLO'>\n";
            strMensaje = strMensaje + "No se puede eliminar el registro del contratista seleccionado debido a que se encuentra asociado con contratos existentes.\n";
            strMensaje = strMensaje + "</span>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";                       
        }
        
        // Interventores.
        
        if (strAccion.equals("interventores")){              
            strSQL = "select c.txtConsecutivo from tbl_contratos c where c.txtIdInterventor = '" + strClave + "'";        
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<span class='TEXTOFALLO'>\n";
            strMensaje = strMensaje + "No se puede eliminar el registro del interventor seleccionado debido a que se encuentra asociado con contratos existentes.\n";
            strMensaje = strMensaje + "</span>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";                       
        }
        
        if (strAccion.equals("contratos")){              
            strSQL = "select c.txtConsecutivo from tbl_contratos c where c.txtIdEstado = 'FN' and c.txtConsecutivo = '" + strClave + "'";        
            strMensaje = "<html>\n";
            strMensaje = strMensaje + "<head></head>\n";
            strMensaje = strMensaje + "<body>\n";
            strMensaje = strMensaje + "<span class='TEXTOFALLO'>\n";
            strMensaje = strMensaje + "No se puede eliminar el registro del contrato seleccionado ya que se encuentra en estado Finalizado.\n";
            strMensaje = strMensaje + "</span>\n";    
            strMensaje = strMensaje + "</body>\n";
            strMensaje = strMensaje + "</html>";                       
        }
                       
        if (strSQL != null){
            strResult = GestionSQL.getFila(strSQL,"Nomina");
            
            if (strResult != null){
                return strMensaje;
            }else{
                 return null;
            }
        }else{
            return null;
        }                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
