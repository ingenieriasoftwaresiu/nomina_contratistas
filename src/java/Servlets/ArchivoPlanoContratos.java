/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Conexion.GestionSQL;
import DAO.ArchivoPlanoContratoDAO;
import DAOImpl.ArchivoPlanoContratoDAOImpl;
import DTO.ArchivoPlanoContrato;
import Negocio.ValoresContrato;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorge.correa
 */
public class ArchivoPlanoContratos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code|GET</code| and <code|POST</code|
     * methods.
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
            
            String strSQL="", strRutaArchivo="", strNombreArchivo="", strCadena="", strObjeto="", strCaracter="";             
            String[] strRuta = null;
            ArchivoPlanoContratoDAO archivoPlanoContratoDAO = new ArchivoPlanoContratoDAOImpl();
            List<ArchivoPlanoContrato> contratos = null;
            BufferedWriter bw;
            ValoresContrato valoresContrato = new ValoresContrato();
                        
            strSQL = "select g.txtRutaCargaArchivo from nomina.tbl_generales g where g.txtForm = 'frmGeneral'";
            strRuta = GestionSQL.getFila(strSQL, "Nomina");
            
            if (strRuta != null){            
                
                contratos = archivoPlanoContratoDAO.obtenerTodos();
                strNombreArchivo = "/Archivoplanocontratos.txt";
                strCaracter = "<";
                
                strRutaArchivo = strRuta[0] + strNombreArchivo;
                
                File archivo = new File(strRutaArchivo);                               
                bw = new BufferedWriter(new FileWriter(archivo));
                
                // Conformación de la cabecera.
                
                strCadena = strCadena + "Consecutivo " + strCaracter;
                strCadena = strCadena + "Contratista" + strCaracter;
                strCadena = strCadena + "Correo Contratista" + strCaracter;
                strCadena = strCadena + "Interventor" + strCaracter;
                strCadena = strCadena + "TipoContrato" + strCaracter;
                strCadena = strCadena + "Grupo" + strCaracter;
                strCadena = strCadena + "FechaInicio" + strCaracter;
                strCadena = strCadena + "FechaFin" + strCaracter;
                strCadena = strCadena + "Duracion" + strCaracter;
                strCadena = strCadena + "Valor" + strCaracter;
                strCadena = strCadena + "CentroCostos" + strCaracter;
                strCadena = strCadena + "ConsecutivoLaborales" + strCaracter;
                strCadena = strCadena + "NumeroCDP" + strCaracter;
                strCadena = strCadena + "Estado" + strCaracter;
                strCadena = strCadena + "FechaCreacion" + strCaracter;
                strCadena = strCadena + "Objeto" + "\r\n";
                     
                bw.write(strCadena);
                
                
                for(ArchivoPlanoContrato contrato : contratos){
                    strCadena = "";
                    
                    strCadena = strCadena + contrato.getConsecutivo() + strCaracter;
                    strCadena = strCadena + valoresContrato.obtenerContratista(contrato.getContratista()) + strCaracter;
                    strCadena = strCadena + valoresContrato.obtenerCorreoContratista(contrato.getContratista()) + strCaracter;
                    strCadena = strCadena + valoresContrato.obtenerInterventor(contrato.getInterventor()) + strCaracter;
                    strCadena = strCadena + valoresContrato.obtenerTipoContrato(contrato.getTipoContrato()) + strCaracter;
                    strCadena = strCadena + valoresContrato.obtenerGrupo(contrato.getGrupo()) + strCaracter;
                    strCadena = strCadena + contrato.getFechaInicio()+ strCaracter;
                    strCadena = strCadena + contrato.getFechaFin() + strCaracter;
                    strCadena = strCadena + contrato.getDuracion()+ strCaracter;
                    strCadena = strCadena + contrato.getValor() + strCaracter;
                    strCadena = strCadena + contrato.getCentroCostos() + strCaracter;
                    strCadena = strCadena + valoresContrato.obtenerVacio(contrato.getConsecutivoLaborales()) + strCaracter;
                    strCadena = strCadena + valoresContrato.obtenerVacio(contrato.getNumeroCDP()) + strCaracter;
                    strCadena = strCadena + valoresContrato.obtenerEstado(contrato.getEstadoActual()) + strCaracter;
                    strCadena = strCadena + contrato.getFechaCreacion() + strCaracter;
                    
                    strObjeto = contrato.getObjeto().replaceAll("\r\n", " ");
                    strCadena = strCadena + strObjeto + "\r\n";
                    
                    bw.write(strCadena);                        
                    strObjeto = "";
                    
                }
                                              
                bw.close();
                
                out.println("<div class='TEXTOEXITO'>");
                out.println("El archivo plano fue generado satisfactoriamente!.");
                out.println("<br /><br />");
                out.println("<input type='button' id='btnDescargar' name='btnDescargar' value='Descargar' class='BOTONFORM' onclick=\"descargarArchivo('" + strRutaArchivo + "'); \" />");
                out.println("</div>");
            }else{
                out.println("<div class='TEXTOFALLO>");
                out.println("No se tiene configurada la ruta para la creación del archivo plano. Por favor contacte al Administrador del Sistema!.");
                out.println("</div>");
            }
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."|
    /**
     * Handles the HTTP <code|GET</code| method.
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
     * Handles the HTTP <code|POST</code| method.
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
    }// </editor-fold|

}
