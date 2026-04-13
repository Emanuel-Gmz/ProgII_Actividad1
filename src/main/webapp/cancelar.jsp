<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cancelar Turnos</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .formulario { background: white; padding: 30px; border-radius: 8px; width: 300px; margin: 0 auto; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        .campo { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn-submit { background-color: #dc3545; color: white; border: none; padding: 10px; width: 100%; cursor: pointer; border-radius: 4px; margin-top: 10px; }
        .btn-submit:hover { background-color: #c82333; }
        .btn-volver { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #007BFF; }
    </style>
</head>
<body>

    <div class="formulario">
        <h2 style="text-align: center;">Cancelar por Pintura</h2>

        <form action="TurnoServlet?accion=cancelar" method="POST">
            <div class="campo">
                <label>Nro. Consultorio a pintar:</label>
                <input type="number" name="nroConsultorio" required>
            </div>

            <div class="campo">
                <label>Fecha de pintura (Día a cancelar):</label>
                <input type="date" name="fechaPintura" required>
            </div>

            <button type="submit" class="btn-submit">Confirmar</button>
        </form>

        <a href="index.jsp" class="btn-volver">Volver al Menú</a>
    </div>

</body>
</html>