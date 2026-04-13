package entities;

import java.sql.Time;
import java.util.Date;

public class Turno {
    private Date dia;
    private Time hora;
    private int nroConsultorio;
    private int nroPaciente;
    private int id_turno;

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public Turno(Date dia, Time hora, int nroConsultorio, int nroPaciente) {
        this.dia = dia;
        this.hora = hora;
        this.nroConsultorio = nroConsultorio;
        this.nroPaciente = nroPaciente;
    }

    public Date getDia() {return dia;}

    public void setDia(Date dia) {this.dia = dia;}

    public Time getHora() {return hora;}

    public int getNroConsultorio() {return nroConsultorio;}

    public void setNroConsultorio(int nroConsultorio) {this.nroConsultorio = nroConsultorio;}

    public int getNroPaciente() {return nroPaciente;}

    @Override
    public String toString() {
        return "Turno{" +
                "dia=" + dia +
                ", hora=" + hora +
                ", nroConsultorio=" + nroConsultorio +
                ", nroPaciente=" + nroPaciente +
                ", id_turno=" + id_turno +
                '}';
    }
}
