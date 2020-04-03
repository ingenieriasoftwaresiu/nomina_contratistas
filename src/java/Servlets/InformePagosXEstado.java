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
public class InformePagosXEstado extends HttpServlet {

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
           
            String strIdEstado = request.getParameter("txtIdEstado");
            String strSQL = "";
            String[] strTemp = null;
            Vector arrIdsContratos = new Vector();
            Vector arrNumsPago = new Vector();
            Vector arrFechasPago =new Vector();
            Vector arrPorcPago =new Vector();
            Vector arrValoresSalud =new Vector();
            Vector arrValoresPension =new Vector();
            Vector arrValoresARL =new Vector();
            Vector arrSoportes =new Vector();
            Vector arrIdEstados =new Vector();
            Vector arrFormatos =new Vector();
            Comunes comun = new Comunes();
            
            if(strIdEstado.equals("*")){
                strSQL = "select p.txtIdContrato, p.txtNumPago, p.dtFechaPago, p.txtPorcPago, p.txtValorSalud, p.txtValorPension, p.txtValorARL, p.txtRutaArchivo, p.txtIdEstadoPago, p.txtRutaFormato from nomina.tbl_plan_pagos p ORDER BY p.txtIdContrato, CAST(p.txtNumPago AS SIGNED)";
            }else{
                strSQL = "select p.txtIdContrato, p.txtNumPago, p.dtFechaPago, p.txtPorcPago, p.txtValorSalud, p.txtValorPension, p.txtValorARL, p.txtRutaArchivo, p.txtIdEstadoPago, p.txtRutaFormato from nomina.tbl_plan_pagos p where p.txtIdEstadoPago = '" + strIdEstado + "' ORDER BY p.txtIdContrato, CAST(p.txtNumPago AS SIGNED)";
            }
                        
            Vector arrPagos = GestionSQL.consultaSQL(strSQL, "Nomina", "10");
            
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
                    arrFechasPago.add(strTemp[2]);
                    arrPorcPago.add(strTemp[3]);
                    arrValoresSalud.add(strTemp[4]);
                    arrValoresPension.add(strTemp[5]);
                    arrValoresARL.add(strTemp[6]);
                    arrSoportes.add(strTemp[7]);
                    arrIdEstados.add(strTemp[8]);              
                    arrFormatos.add(strTemp[9]);
                }
                
                out.println("<html>");
                out.println("<head>");                 
                out.println("</head>");
                out.println("<body>");                       
                out.println("<table cellspacing='0' cellpadding='5' width='99%' border='0' class='TABLARESULT'>");
                out.println("<tr>");
                out.println("<td class='TITULOTABLAIZQ'>CONSECUTIVO<br /> CONTRATO</td>");
                out.println("<td class='TITULOTABLACENTER'>NÚMERO DE PAGO</td>");
                out.println("<td class='TITULOTABLACENTER'>FECHA DE PAGO<br>(aaaa-mm-dd)</td>");
                out.println("<td class='TITULOTABLACENTER'>% DE PAGO</td>");
                out.println("<td class='TITULOTABLACENTER'>VALOR SALUD ($)</td>");
                out.println("<td class='TITULOTABLACENTER'>VALOR PENSIÓN ($)</td>");
                out.println("<td class='TITULOTABLACENTER'>VALOR ARL ($)</td>");
                out.println("<td class='TITULOTABLACENTER'>¿TIENE SOPORTE?</td>");
                out.println("<td class='TITULOTABLACENTER'>¿TIENE FORMATO?</td>");
                out.println("<td class='TITULOTABLACENTER'>ESTADO</td>");                    
                out.println("</tr>");
                
                for(int i=0;i<arrIdsContratos.size();i++){      
                        out.println("<tr class='FILARESULT'>");
                        out.println("<td class='TEXTOCELDAIZQ'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + arrIdsContratos.get(i) + "</a></td>");
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + arrNumsPago.get(i) + "</a></td>");
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + arrFechasPago.get(i) + "</a></td>");
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + arrPorcPago.get(i).toString() + "</a></td>");      
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + comun.marcarMiles(arrValoresSalud.get(i).toString()) + "</a></td>");
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + comun.marcarMiles(arrValoresPension.get(i).toString()) + "</a></td>");
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + comun.marcarMiles(arrValoresARL.get(i).toString()) + "</a></td>");
                        if(arrSoportes.get(i).equals("-")){
                            out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>No</a></td>");
                        }else{
                            out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>Si</a></td>");
                        }          
                        if(arrFormatos.get(i).equals("-")){
                            out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>No</a></td>");
                        }else{
                            out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>Si</a></td>");
                        }
                        out.println("<td class='TEXTOCELDACENTER'><a href='#' onclick='verDetallePago(\"" + arrIdsContratos.get(i).toString() + "\",\"" + arrNumsPago.get(i).toString() + "\")'>" + comun.validarEstado(arrIdEstados.get(i).toString()) + "</a></td>");                        
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
