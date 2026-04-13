<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agregar Turno</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .formulario { background: white; padding: 30px; border-radius: 8px; width: 300px; margin: 0 auto; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        .campo { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn-submit { background-color: #28a745; color: white; border: none; padding: 10px; width: 100%; cursor: pointer; border-radius: 4px; margin-top: 10px; }
        .btn-submit:hover { background-color: #218838; }
        .btn-volver { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #007BFF; }
    </style>
</head>
<body>

    <div class="formulario">
        <h2 style="text-align: center;">Nuevo Turno</h2>

        <form action="TurnoServlet?accion=agregar" method="POST">
            <div class="campo">
                <label>Día del Turno:</label>
                <input type="date" name="dia" required>
            </div>

            <div class="campo">
                <label>Hora:</label>
                <input type="time" name="hora" required>
            </div>

            <div class="campo">
                <label>Nro. Consultorio:</label>
                <input type="number" name="nroConsultorio" required>
            </div>

            <div class="campo">
                <label>Nro. Paciente:</label>
                <input type="number" name="nroPaciente" required>
            </div>

            <button type="submit" class="btn-submit">Guardar Turno</button>
        </form>

        <a href="index.jsp" class="btn-volver">Volver al Menú</a>
    </div>

</body>
</html>