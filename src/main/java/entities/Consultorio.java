package entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consultorio {
    private int nroConsultorio;
    private String medico;
    private List<Turno> turnos;

    public Consultorio(int nroConsultorio, String medico) {
        this.nroConsultorio = nroConsultorio;
        this.medico = medico;
        this.turnos = new ArrayList<>();
    }


    public void agregarTurno(Date dia, Time hora, int nroPaciente) {
        Turno nuevoTurno = new Turno(dia, hora, this.nroConsultorio, nroPaciente);
        this.turnos.add(nuevoTurno);
    }

    public int getNroConsultorio() {
        return nroConsultorio;
    }

    public void setNroConsultorio(int nroConsultorio) {
        this.nroConsultorio = nroConsultorio;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
}
