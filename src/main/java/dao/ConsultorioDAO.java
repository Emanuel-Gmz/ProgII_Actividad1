package dao;

import entities.Consultorio;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.Connection;
import java.util.List;

public class ConsultorioDAO implements AdmConexion{


    public Connection obtenerConexion() {
        return AdmConexion.super.obtenerConexion();
    }
}
