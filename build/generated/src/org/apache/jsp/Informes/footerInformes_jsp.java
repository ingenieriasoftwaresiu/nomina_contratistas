package org.apache.jsp.Informes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class footerInformes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("         <link rel=\"stylesheet\" type=\"text/css\" href=\"../Styles/comunes.css\" />\n");
      out.write("    </head>\n");
      out.write("    <body>  \n");
      out.write("            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"TABLAFOOTER\">\n");
      out.write("                <tr>              \n");
      out.write("                    <td class=\"CELDALOGOSIU\">\n");
      out.write("                        <img src=\"../Images/Logo_SIU.png\" class=\"IMGLOGOSIU\" />\n");
      out.write("                    </td>\n");
      out.write("                    <td class=\"CELDATEXTOFOOTER\">\n");
      out.write("                            <b>ADMINISTRACIÓN DE LA SIU:</b> Carrera 53 N° 61 - 30<br>\n");
      out.write("                            <b>Teléfono:</b> (+57)(4) 2196402,  <b>Fax:</b> (+57)(4) 2191077, <b>NIT:</b> 890980040-8, <b>Apartado:</b> 1226<br>\n");
      out.write("                            <b>Web: </b><a href=\"http://www.udea.edu.co/portal/page/portal/Programas/GruposInvestigacion/sedeInvestigacionUniversitaria/A.institucional\" target=\"_blank\">http://www.udea.edu.co/Investigacion</a><br>\n");
      out.write("                            Medellín – Colombia<br>\n");
      out.write("                            2014\n");
      out.write("                    </td>\n");
      out.write("                    <td class=\"TEXTOINGSW\">\n");
      out.write("                        <img src=\"../Images/Ing_sw.png\" class=\"IMGLOGOINGSW\" />\n");
      out.write("                        <br>&#169Desarrollado por <b><a href=\"mailto:ingenieria.software@siu.udea.edu.co\">Ingeniería de Software</a></b> -<b>SIU</b>-<br>\n");
      out.write("                </td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
