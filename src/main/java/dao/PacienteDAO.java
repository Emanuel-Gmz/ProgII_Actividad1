package dao;

import entities.Paciente;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.Connection;
import java.util.List;

public class PacienteDAO implements AdmConexion {

    @Override
    public Connection obtenerConexion() {
        return AdmConexion.super.obtenerConexion();
    }
}
