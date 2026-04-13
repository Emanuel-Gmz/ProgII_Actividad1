package servlets;

import dao.TurnoDAO;
import entities.Turno;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TurnoServlet extends HttpServlet {

    private TurnoDAO turnoDAO = new TurnoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null || accion.equals("listar")) {

            List<Turno> lista = turnoDAO.getAll();
            request.setAttribute("turnos", lista);
            request.getRequestDispatcher("turnos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (accion.equals("agregar")) {
                String diaStr = request.getParameter("dia");
                String horaStr = request.getParameter("hora");
                int nroConsultorio = Integer.parseInt(request.getParameter("nroConsultorio"));
                int nroPaciente = Integer.parseInt(request.getParameter("nroPaciente"));

                Date dia = sdf.parse(diaStr);
                Time hora = Time.valueOf(horaStr + ":00");

                Turno nuevoTurno = new Turno(dia, hora, nroConsultorio, nroPaciente);
                turnoDAO.insert(nuevoTurno);

            } else if (accion.equals("cancelar")) {
                int nroConsultorio = Integer.parseInt(request.getParameter("nroConsultorio"));
                String fechaStr = request.getParameter("fechaPintura");
                Date fechaPintura = sdf.parse(fechaStr);
                turnoDAO.cancelarTurnos(nroConsultorio, fechaPintura);
            }

            response.sendRedirect("TurnoServlet?accion=listar");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}