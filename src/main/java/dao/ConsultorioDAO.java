package dao;

import com.mysql.cj.protocol.Resultset;
import entities.Consultorio;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultorioDAO implements AdmConexion,DAO<Consultorio,Integer>{
    private Connection conn = obtenerConexion();

    private static String SQL_INSERT =
            "INSERT INTO Consultorio (nroConsultorio, medico) " +
                    " VALUES (?, ?)";

    private static String SQL_UPDATE = "UPDATE Consultorio SET medico = ? WHERE nroConsultorio = ?";

    private static String SQL_DELETE = "DELETE FROM Consultorio WHERE nroConsultorio = ?";
    private static String SQL_GETALL = "SELECT * FROM Consultorio ORDER BY nroConsultorio ASC";
    private static String SQL_GETBYID = "SELECT * FROM Consultorio WHERE nroConsultorio = ?";
    private static String SQL_EXISTSBYID = "SELECT * FROM Consultorio WHERE nroConsultorio = ?";


    public Connection obtenerConexion() {
        return AdmConexion.super.obtenerConexion();
    }

    @Override
    public List<Consultorio> getAll() {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Consultorio> lista = new ArrayList<>();
        try{
            pst = conn.prepareStatement(SQL_GETALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Consultorio consultorio = new Consultorio(rs.getInt("nroConsultorio"),rs.getString("medico"));
                consultorio.setNroConsultorio(rs.getInt("nroConsultorio"));
                consultorio.setMedico(rs.getString("medico"));

                lista.add(consultorio);
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
    public void insert(Consultorio objeto) {
        conn = obtenerConexion();
        PreparedStatement pst = null;

        try{
            pst =conn.prepareStatement(SQL_INSERT);
            pst.setInt(1, (objeto.getNroConsultorio()));
            pst.setString(2, objeto.getMedico());

            int resultado = pst.executeUpdate();

            if (resultado == 1) {
                System.out.println("Consultorio agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar el consultorio.");
            }

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Consultorio objeto) {
        conn = obtenerConexion();
        if(this.existsById(objeto.getNroConsultorio())){
            try{
                PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);
                pst.setString(1, objeto.getMedico());
                pst.setInt(2, objeto.getNroConsultorio());
                int resultado = pst.executeUpdate();

                if (resultado == 1) {
                    System.out.println("Consultorio actualizado correctamente");
                } else {
                    System.out.println("No se pudo actualizar el consultorio");
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
        try {PreparedStatement pst= conn.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);
            int resultado = pst.executeUpdate();

            if (resultado == 1) {
                System.out.println("Consultorio eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el consultorio");
            }
            pst.close();
            conn.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Consultorio getById(Integer id) {
        conn = obtenerConexion();
        Consultorio consultorio = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                consultorio = new Consultorio(rs.getInt("nroConsultorio"), rs.getString("medico"));
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return consultorio;
    }


    @Override
    public boolean existsById(Integer id) {
        conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean existe = false;
        try{
            pst = conn.prepareStatement(SQL_EXISTSBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                existe = true;
            }

            conn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return existe;
    }

}
