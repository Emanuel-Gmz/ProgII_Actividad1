package entities;

import java.sql.Time;
import java.text.SimpleDateFormat;
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

    public void cancelarTurnos(Date fechaPintura) {
        if (this.turnos != null) {
            this.turnos.removeIf(turno -> esMismoDia(turno.getDia(), fechaPintura));
            System.out.println("Turnos cancelados en la fecha indicada.");
        }
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

    @Override
    public String toString() {
        return "Consultorio{" +
                "nroConsultorio=" + nroConsultorio +
                ", medico='" + medico + '\'' +
                ", turnos=" + turnos +
                '}';
    }

    private boolean esMismoDia(Date dia1, Date dia2) {
        if (dia1 == null || dia2 == null) {
            return false;
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(dia1).equals(fmt.format(dia2));
    }
}
