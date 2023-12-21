package org.iesvdm.practicaservlet.dao;

import org.iesvdm.practicaservlet.model.Joputa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JoputaDAOImpl extends AbstractDAOImpl implements JoputaDAO {
    @Override
    public void create(Joputa joputa) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();


            //1 alternativas comentadas:
            //Ver también, AbstractDAOImpl.executeInsert ...
            //Columna fabricante.codigo es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente.
            ps = conn.prepareStatement("INSERT INTO joputa (fecha, equipo1, puntos_equipo1, equipo2, puntos_equipo2, tipo_partido) VALUES (?, ? , ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setDate(idx++, (Date)joputa.getFecha());
            ps.setString(idx++, joputa.getEquipo1());
            ps.setInt(idx++, joputa.getPuntos_equipo1());
            ps.setString(idx++, joputa.getEquipo2());
            ps.setInt(idx++, joputa.getPuntos_equipo2());
            ps.setString(idx++, joputa.getTipo_partido());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de joputa con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                joputa.setId(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException  e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public List<Joputa> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Joputa> listPartido = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM joputa");
            while (rs.next()) {
                Joputa joputa = new Joputa();

                joputa.setId(rs.getInt("id"));
                joputa.setEquipo1(rs.getString("equipo1"));
                joputa.setPuntos_equipo1(rs.getInt("puntos_equipo1"));
                joputa.setEquipo2(rs.getString("equipo2"));
                joputa.setPuntos_equipo2(rs.getInt("puntos_equipo2"));
                joputa.setTipo_partido(rs.getString("tipo_partido"));
                listPartido.add(joputa);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listPartido;

    }

    @Override
    public Optional<Joputa> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM joputa WHERE id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Joputa joputa = new Joputa();

                joputa.setId(rs.getInt("id"));
                joputa.setEquipo1(rs.getString("equipo1"));
                joputa.setPuntos_equipo1(rs.getInt("puntos_equipo1"));
                joputa.setEquipo2(rs.getString("equipo2"));
                joputa.setPuntos_equipo2(rs.getInt("puntos_equipo2"));
                joputa.setTipo_partido(rs.getString("tipo_partido"));

                return Optional.of(joputa);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();

    }

    @Override
    public void update(Joputa joputa) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE socio SET fecha = ?, equipo1 = ?, puntos_equipo1 = ?, equipo2 = ?, puntos_equipo2 = ?, tipo_partido = ?  WHERE id = ?");
            int idx = 1;
            ps.setDate(idx++, (Date)joputa.getFecha());
            ps.setString(idx++, joputa.getEquipo1());
            ps.setInt(idx++, joputa.getPuntos_equipo1());
            ps.setString(idx++, joputa.getEquipo2());
            ps.setInt(idx++, joputa.getPuntos_equipo2());
            ps.setString(idx, joputa.getTipo_partido());

            ps.setInt(idx++, joputa.getId());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de joputa con 0 registros actualizados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM joputa WHERE id = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de joputa con 0 registros eliminados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
}
