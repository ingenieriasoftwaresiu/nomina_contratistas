<%-- 
    Document   : notificacion
    Created on : 18/01/2015, 06:44:09 PM
    Author     : Jorge.correa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%                
    String strMensaje = (String)session.getAttribute("mensaje");        
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='Styles/comunes.css'/>
        <link rel='stylesheet' type='text/css' href='Styles/forms.css'/>
        <script type="text/javascript" src="Scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="Scripts/comunes.js"></script>
        <title>Notificación del sistema</title>
        <script type="text/javascript">            
            document.onkeydown= function(evt) {         
                if (!evt){
                    evt = event;
                }
                           
                if ((evt.keyCode == 116) || (evt.which == 8) || (evt.ctrlKey && evt.keyCode == 116)){   
                    evt.preventDefault();
                }                                
            }
            
           $(function(){
                var rx = /INPUT|SELECT|TEXTAREA/i;

                $(document).bind("keydown keypress", function(e){
                    if(e.which == 8){ // 8 == backspace
                        if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
                            e.preventDefault();
                        }
                    }
                });
            });
        </script>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" />
        </header>
        <section>
            <article>
                <br /><br /><br /><br/>
                <div class='TEXTOMENSAJE' align="center">
                    <%=strMensaje%>            
                    <br /><br/>
                    <input type="button" value="Salir" class="BOTONFORM" onclick="javascript:window.close();" />          
                </div>        
                <br /><br/><br/>
            </article>
        </section>
        <footer>
            <jsp:include page="footer.jsp" />     
        </footer>
    </body>
</html>
