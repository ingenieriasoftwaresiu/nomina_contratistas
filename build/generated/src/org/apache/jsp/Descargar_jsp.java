package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.FileNotFoundException;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.File;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public final class Descargar_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

    String txtRuta =  request.getParameter("txtRuta");
    int intPos = txtRuta.lastIndexOf("\\");    
    String strFilename = txtRuta.substring(intPos+1, txtRuta.length());    
    
    BufferedInputStream filein = null;
    BufferedOutputStream outputs = null;
    try {
        File file = new File(txtRuta);
        byte b[] = new byte[2048];
        int len = 0;
        filein = new BufferedInputStream(new FileInputStream(file));
        outputs = new BufferedOutputStream(response.getOutputStream());
        response.setHeader("Content-Length", ""+file.length());
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition","attachment;filename=" + strFilename);
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        while ((len = filein.read(b)) > 0) {
            outputs.write(b, 0, len);
            outputs.flush();
        }
        filein.close();
        outputs.close();
    }catch(FileNotFoundException fnfe){
        out.println("<script type='text/javascript'>");
        out.println("alert('No se encontró el archivo seleccionado para descarga. Por favor contacte el Administrador del Sistema.');");
        out.println("window.close();");
        out.println("</script>");
    }catch(Exception e){
        out.println(e);
    }finally{
        if (filein != null){
            filein.close();
        }        
        if (outputs != null){
            outputs.close();
        }        
    }
    
    /*
    response.setContentType("application/pdf"); 
    response.setHeader("Content-Disposition","attachment;filename=" + strFilename);
    
    ServletOutputStream  outs = null;
    
    try{
        File fileToDownload = new File(txtRuta);
        InputStream in =new FileInputStream(fileToDownload);
        outs = response.getOutputStream();
        int bit = 256;

        while ((bit) >= 0){
            bit = in.read();
            outs.write(bit);
        }
        outs.flush();
        outs.close();
        in.close(); 
    }catch(IllegalStateException ise){
        ise.getMessage();
    }catch(Exception e){
       e.printStackTrace();
    }finally{
        if (outs != null){
            try{
                outs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }*/

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
