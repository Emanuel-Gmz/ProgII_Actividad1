<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor de Turnos Médicos</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; text-align: center; padding: 50px; }
        .contenedor { background-color: white; padding: 40px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); display: inline-block; }
        h1 { color: #333; }
        .menu-btn { display: block; width: 250px; padding: 15px; margin: 15px auto; background-color: #007BFF; color: white; text-decoration: none; font-size: 16px; border-radius: 5px; transition: background-color 0.3s; }
        .menu-btn:hover { background-color: #0056b3; }
    </style>
</head>
<body>

    <div class="contenedor">
        <h1> Gestor de Turnos</h1>
        <p>Bienvenido al sistema de gestión de turnos médicos.</p>

        <a href="TurnoServlet?accion=listar" class="menu-btn"> Consultar Turnos</a>
        <a href="agregar.jsp" class="menu-btn"> Agregar Nuevo Turno</a>
        <a href="cancelar.jsp" class="menu-btn"> Cancelar Turnos por Fecha</a>
    </div>

</body>
</html>