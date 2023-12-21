<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Introduzca los datos del nuevo partido:</h2>
<form method="post" action="GrabarPartidosServlet">

    Fecha <input type="date" name="fecha"/></br>
    Equipo1 <input type="text" name="equipo1"/></br>
    Puntos_equipo1 <input type="number" name="puntos_equipo1"/></br>
    Equipo2 <input type="text" name="equipo1"/></br>
    Puntos_equipo2 <input type="number" name="puntos_equipo2"/></br>
    Tipo_partido <input type="text" name="tipo_partido"/></br>
    <input type="submit" value="Aceptar">
</form>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div style="color: red"><%=error%></div>
<%
    }
%>

</body>
</html>