<%-- 
    Document   : comprobarSesion
    Created on : 15-ene-2014, 15:32:41
    Author     : Jorge.correa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", String.valueOf(timeout) + ";URL=cerrar.jsp");
%>
<html>
    <head>
        <meta http-equiv=REFRESH CONTENT="<%=String.valueOf(timeout)%>;url=cerrar.jsp">
    </head>
</html>
