package dao;

import entities.Turno;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO implements AdmConexion, DAO<Turno, Integer> {
    private Connection conn = obtenerConexion();
    private static final String SQL_INSERT = "INSERT INTO Turno (dia, hora, nroConsultorio, nroPaciente) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Turno SET dia = ?, hora = ?, nroConsultorio = ?, nroPaciente = ? WHERE id_turno = ?";
    private static final String SQL_DELETE = "DELETE FROM Turno WHERE id_turno = ?";
    private static final String SQL_GETALL = "SELECT * FROM Turno ORDER BY dia ASC, hora ASC";
    private static final String SQL_GETBYID = "SELECT * FROM Turno WHERE id_turno = ?";
    private static final String SQL_EXISTSBYID = "SELECT * FROM Turno WHERE id_turno = ?";
    private static final String SQL_DELETEDIA = "DELETE FROM Turno WHERE nroConsultorio = ? AND dia = ?";

    @Override
    public Connection obtenerConexion() {
        return AdmConexion.super.obtenerConexion();
    }

    @Override
    public List<Turno> getAll() {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Turno> lista = new ArrayList<>();

        try {
            pst = conn.prepareStatement(SQL_GETALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Turno turno = new Turno(
                        rs.getDate("dia"),
                        rs.getTime("hora"),
                        rs.getInt("nroConsultorio"),
                        rs.getInt("nroPaciente")
                );
                turno.setId_turno(rs.getInt("id_turno"));

                lista.add(turno);
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
    public void insert(Turno objeto) {
        conn = obtenerConexion();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(SQL_INSERT);

            java.sql.Date fechaSQL = new java.sql.Date(objeto.getDia().getTime());
            pst.setDate(1, fechaSQL);
            pst.setTime(2, objeto.getHora());
            pst.setInt(3, objeto.getNroConsultorio());
            pst.setInt(4, objeto.getNroPaciente());

            int resultado = pst.executeUpdate();

            if (resultado == 1) {
                System.out.println("Turno agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar el turno.");
            }

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Turno objeto) {
        if (this.existsById(objeto.getId_turno())) {
            conn = obtenerConexion();
            PreparedStatement pst = null;

            try {
                pst = conn.prepareStatement(SQL_UPDATE);

                java.sql.Date fechaSQL = new java.sql.Date(objeto.getDia().getTime());
                pst.setDate(1, fechaSQL);
                pst.setTime(2, objeto.getHora());
                pst.setInt(3, objeto.getNroConsultorio());
                pst.setInt(4, objeto.getNroPaciente());
                pst.setInt(5, objeto.getId_turno());

                int resultado = pst.executeUpdate();

                if (resultado == 1) {
                    System.out.println("Turno actualizado correctamente.");
                } else {
                    System.out.println("No se pudo actualizar el turno.");
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
        conn = obtenerConexion();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);

            int resultado = pst.executeUpdate();

            if (resultado == 1) {
                System.out.println("Turno eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el turno.");
            }

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelarTurnos(int nroConsultorio, java.util.Date fechaPintura) {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(SQL_DELETEDIA);
            pst.setInt(1, nroConsultorio);
            java.sql.Date fechaSQL = new java.sql.Date(fechaPintura.getTime());
            pst.setDate(2, fechaSQL);

            int turnosCancelados = pst.executeUpdate();
            System.out.println("Se cancelaron " + turnosCancelados + " turnos en el consultorio " + nroConsultorio);

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Turno getById(Integer id) {
        conn = obtenerConexion();
        Turno turno = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                turno = new Turno(
                        rs.getDate("dia"),
                        rs.getTime("hora"),
                        rs.getInt("nroConsultorio"),
                        rs.getInt("nroPaciente")
                );
                turno.setId_turno(rs.getInt("id_turno"));
            }

            pst.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return turno;
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
