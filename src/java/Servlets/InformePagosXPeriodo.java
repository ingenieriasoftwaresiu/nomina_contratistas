/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Conexion.GestionSQL;
import Negocio.Comunes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorge.correa
 */
public class InformePagosXPeriodo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
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
            
            String strFechaIni = request.getParameter("txtFechaIni");
            String strFechaFin = request.getParameter("txtFechaFin");
            String strSQL = "";
            final String strUndsTotales = "100";
            String[] strTemp = null, strAcum = null;
            Vector arrIdsContratos = new Vector();
            Vector arrNumsPago = new Vector();
            Vector arrPorcPago = new Vector();
            Vector arrValoresSalud = new Vector();
            Vector arrValoresPension= new Vector();
            Vector arrValoresARL = new Vector();
            Comunes comun = new Comunes();
            
            strSQL = "SELECT p.txtIdContrato, p.txtNumPago, p.txtPorcPago, p.txtValorSalud, p.txtValorPension, p.txtValorARL from nomina.tbl_plan_pagos p, nomina.tbl_contratos c, nomina.tbl_contratistas cnt where (p.txtIdContrato = c.txtConsecutivo) and (c.txtIdContratista = cnt.txtNumId) and (c.txtIdEstado = 'EJ') and (p.dtFechaPago BETWEEN '" + strFechaIni + "' and '" + strFechaFin + "') and p.txtIdEstadoPago = 'A' ORDER BY cnt.txtApellidos, p.txtIdContrato, CAST(p.txtNumPago as SIGNED)";
            Vector arrPagos = GestionSQL.consultaSQL(strSQL, "Nomina", "6");
            
            if (arrPagos == null){            
                out.println("<html>");
                out.println("<head>");              
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='TEXTOFALLO'>");
                out.println("No se encontraron registros que concuerden con el filtro aplicado a la consulta.");
                out.println("</div>");             
                out.println("</body>");
                out.println("</html>");
            }else{                 
                for(int i=0;i<arrPagos.size();i++){
                    strTemp = arrPagos.get(i).toString().split(">");                    
                    arrIdsContratos.add(strTemp[0]);
                    arrNumsPago.add(strTemp[1]);         
                    arrPorcPago.add(strTemp[2]);
                    arrValoresSalud.add(strTemp[3]);
                    arrValoresPension.add(strTemp[4]);  
                    arrValoresARL.add(strTemp[5]); 
                }
                
                out.println("<html>");
                out.println("<head>");   
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");                       
                out.println("<table cellspacing='0' cellpadding='5' width='1280px' border='0' class='TABLARESULT'>");
                out.println("<tr>");
                out.println("<td class='TITULOTABLAIZQ'>CONSECUTIVO<br />DEL CONTRATO</td>");
                out.println("<td class='TITULOTABLACENTER'>IDENTIFICACIÓN<br />DEL CONTRATISTA</td>");
                out.println("<td class='TITULOTABLACENTER'>NOMBRE DEL CONTRATISTA</td>");
                out.println("<td class='TITULOTABLACENTER'>UNIDADES<br />TOTALES (%)</td>");
                out.println("<td class='TITULOTABLACENTER'>UNIDADES<br />PAGADAS (%)</td>");
                out.println("<td class='TITULOTABLACENTER'>UNIDADES<br />REPORTADAS (%)</td>");
                out.println("<td class='TITULOTABLACENTER'>APORTE SALUD ($)</td>");
                out.println("<td class='TITULOTABLACENTER'>APORTE PENSIÓN ($)</td>");
                out.println("<td class='TITULOTABLACENTER'>APORTE ARL ($)</td>");
                out.println("<td class='TITULOTABLACENTER'>FECHA FIN<br />DEL CONTRATO<br />(aaaa-mm-dd)</td>");     
                out.println("<td class='TITULOTABLACENTER'>CONSECUTIVO<br />LABORALES</td>");
                out.println("</tr>");
                
                for(int i=0;i<arrIdsContratos.size();i++){  
                    
                    strSQL = "select c.txtIdContratista, CONCAT(co.txtApellidos,' ',co.txtNombres), c.dtFechaFin, c.txtConsecutivoL from nomina.tbl_contratos c, nomina.tbl_contratistas co where (c.txtIdContratista = co.txtNumId) and c.txtConsecutivo = '" + arrIdsContratos.get(i).toString() +"'";
                    strTemp = GestionSQL.getFila(strSQL,"Nomina");

                    strSQL = "select sum(p.txtPorcPago) from nomina.tbl_plan_pagos p where p.txtIdContrato = '" + arrIdsContratos.get(i).toString() +"' and CAST(p.txtNumPago AS SIGNED) < " + arrNumsPago.get(i).toString() + " and p.txtIdEstadoPago = 'E'";
                    strAcum = GestionSQL.getFila(strSQL,"Nomina");
                    
                    out.println("<tr class='FILARESULT'>");
                    out.println("<td class='TEXTOCELDAIZQ'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + arrIdsContratos.get(i) + "</a></td>");                     
                    out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + comun.marcarMiles(strTemp[0]) + "</a></td>");
                    out.println("<td class='TEXTOCELDACENTER' style='text-align: justify;text-justify:inter-word;'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + strTemp[1] + "</a></td>");      
                    out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + strUndsTotales + "</a></td>");
                    if (strAcum[0] == null){
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + 0 + "</a></td>");
                    }else{
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + strAcum[0] + "</a></td>");
                    }                    
                    out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + arrPorcPago.get(i) + "</a></td>");
                    out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + comun.marcarMiles(arrValoresSalud.get(i).toString()) + "</a></td>");
                    out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + comun.marcarMiles(arrValoresPension.get(i).toString()) + "</a></td>");
                    out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + comun.marcarMiles(arrValoresARL.get(i).toString()) + "</a></td>");
                    out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + strTemp[2] + "</a></td>");              
                    if(strTemp[3].equals("")){
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + "-"+ "</a></td>");
                    }else{
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' style='color: #000000;' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + strTemp[3] + "</a></td>");
                    }                    
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");                
            }
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
