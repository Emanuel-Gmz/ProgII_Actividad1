package entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consultorio {
    private int nroConsultorio;
    private String medico;
    private List<Turno> turnos;

    public Consultorio(int nroConsultorio, String medico, List<Turno> turnos) {
        this.nroConsultorio = nroConsultorio;
        this.medico = medico;
        this.turnos = new ArrayList<>();
    }

    public void agregarTurno(Date dia, Time hora, int nroPaciente) {
        Turno nuevoTurno = new Turno(dia, hora, this.nroConsultorio, nroPaciente);
        this.turnos.add(nuevoTurno);
    }


}
