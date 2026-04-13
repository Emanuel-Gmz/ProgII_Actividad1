<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Turno" %>
<html>
<head>
    <title>Gestión de Turnos</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid black; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        .botones { text-align: center; margin: 20px; }
    </style>
</head>
<body>

    <h2 style="text-align: center;">Listado de Turnos</h2>

    <div class="botones">
        <a href="agregar.jsp"><button>Agregar Nuevo Turno</button></a>
        <a href="cancelar.jsp"><button>Cancelar Turnos por Fecha</button></a>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID Turno</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Nro Consultorio</th>
                <th>Nro Paciente</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Turno> listaTurnos = (List<Turno>) request.getAttribute("turnos");

                if (listaTurnos != null && !listaTurnos.isEmpty()) {
                    for (Turno turno : listaTurnos) {
            %>
            <tr>
                <td><%= turno.getId_turno() %></td>
                <td><%= turno.getDia() %></td>
                <td><%= turno.getHora() %></td>
                <td><%= turno.getNroConsultorio() %></td>
                <td><%= turno.getNroPaciente() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No hay turnos registrados en el sistema.</td>
            </tr>
            <%  } %>
        </tbody>
    </table>

</body>
</html>