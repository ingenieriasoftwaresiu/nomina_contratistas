<%-- 
    Document   : cerrar
    Created on : 25-nov-2013, 11:53:54
    Author     : jorge.correa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    try{
            if (session != null){          
                session.invalidate();
            }
    }catch(IllegalStateException ise){
        response.sendRedirect("index.jsp?txtCedula=null");
    }
%>
<html>
    <head>        
        <link rel="SHORTCUT ICON" href="Images/App.ico" />
        <link rel='stylesheet' type='text/css' href='Styles/forms.css'/> 
        <link rel='stylesheet' type='text/css' href='Styles/comunes.css'/> 
        <script language="javascript">
                function redirect(){
                    location.href = "index.jsp";
                }
        </script>
        <title>Sistema de Gestión de Pagos a Contratistas: Sesión finalizada</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class='TEXTOCIERRE'>
            ¡Ha finalizado la sesión!. Para cerrar esta ventana, utiliza el botón "X" ubicado en la esquina superior derecha de tu navegador.<br /><br />
            <input type="button" value="Regresar al Inicio de Sesión" class="BOTONFORM" onClick="redirect();" style="width: 150px;">            
        </div>
        <br><br><br><br><br><br><br>
        <jsp:include page="footer.jsp" />     
    </body>
</html>