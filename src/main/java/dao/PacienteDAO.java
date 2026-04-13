package dao;

import entities.Paciente;
import interfaces.AdmConexion;
import interfaces.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements AdmConexion, DAO<Paciente, Integer> {
    private Connection conn = obtenerConexion();
    private static  String SQL_INSERT = "INSERT INTO Paciente (telefono, nombre) VALUES (?, ?)";
    private static  String SQL_UPDATE = "UPDATE Paciente SET telefono = ?, nombre = ? WHERE nroPaciente = ?";
    private static  String SQL_DELETE = "DELETE FROM Paciente WHERE nroPaciente = ?";
    private static  String SQL_GETALL = "SELECT * FROM Paciente ORDER BY nroPaciente ASC";
    private static  String SQL_GETBYID = "SELECT * FROM Paciente WHERE nroPaciente = ?";
    private static  String SQL_EXISTSBYID = "SELECT * FROM Paciente WHERE nroPaciente = ?";

    @Override
    public Connection obtenerConexion() {
        return AdmConexion.super.obtenerConexion();
    }

    @Override
    public List<Paciente> getAll() {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Paciente> lista = new ArrayList<>();

        try {
            pst = conn.prepareStatement(SQL_GETALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente(rs.getInt("telefono"), rs.getString("nombre")
                );
                paciente.setNroPaciente(rs.getInt("nroPaciente"));

                lista.add(paciente);
            }

            pst.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public void insert(Paciente objeto) {
        conn = obtenerConexion();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(SQL_INSERT);
            pst.setInt(1, objeto.getTelefono());
            pst.setString(2, objeto.getNombre());

            int resultado = pst.executeUpdate();

            if (resultado == 1) {
                System.out.println("Paciente agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar el paciente.");
            }

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Paciente objeto) {
        if (this.existsById(objeto.getNroPaciente())) {
            Connection conn = obtenerConexion();
            PreparedStatement pst = null;

            try {
                pst = conn.prepareStatement(SQL_UPDATE);
                pst.setInt(1, objeto.getTelefono());
                pst.setString(2, objeto.getNombre());
                pst.setInt(3, objeto.getNroPaciente());

                int resultado = pst.executeUpdate();

                if (resultado == 1) {
                    System.out.println("Paciente actualizado correctamente.");
                } else {
                    System.out.println("No se pudo actualizar el paciente.");
                }

                pst.close();
                conn.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Connection conn = obtenerConexion();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);

            int resultado = pst.executeUpdate();

            if (resultado == 1) {
                System.out.println("Paciente eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el paciente.");
            }

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Paciente getById(Integer id) {
        conn = obtenerConexion();
        Paciente paciente = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                paciente = new Paciente(
                        rs.getInt("telefono"),
                        rs.getString("nombre")
                );
                paciente.setNroPaciente(rs.getInt("nroPaciente"));
            }

            pst.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }

    @Override
    public boolean existsById(Integer id) {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            pst = conn.prepareStatement(SQL_EXISTSBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                existe = true;
            }

            pst.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return existe;
    }
}
