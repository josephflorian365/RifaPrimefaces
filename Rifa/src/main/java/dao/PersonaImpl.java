package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Persona;

public class PersonaImpl extends Conexion {

    public void registrar(Persona per) throws Exception {
        String sql = "INSERT INTO PERSONA (NOMPER,NUMPER,ESTPER) VALUES (?,?,'A')";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, per.getNomper());
            ps.setInt(2, per.getNumper());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    public void cambiarEstado(Persona per) throws Exception {
        String sql = "UPDATE PERSONA SET ESTPER = 'I' WHERE IDPER = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, per.getIdper());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    public void quitarPersonaGanador(Integer idper) throws Exception {
        String sql = "UPDATE PERSONA SET ESTPER = 'I' WHERE IDPER = '"+ idper+"' AND ESTPER = 'A'";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

//   public List<Persona> listar() throws Exception {
//      ResultSet rs = null;
//      PreparedStatement pst = null;
//
//      String stm = "Select NOMPER,NUMPER from persona";
//      List<Persona> records = new ArrayList<Persona>();
//      
//      try {   
//         pst = conectar().prepareStatement(stm);
//         pst.execute();
//         rs = pst.getResultSet();
//
//         while(rs.next()) {
//             System.out.println("hay datos");
//            Persona persona = new Persona();
//            persona.setNomper(rs.getString(1));
//            persona.setNumper(rs.getInt(2));
//            records.add(persona);				
//         }
//         pst.close();
//         rs.close();
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//      return records;
//   }
    public List<Persona> listar() throws Exception {
        List<Persona> listado = new ArrayList<>();
        Persona persona;
        String sql = "SELECT IDPER,NOMPER,NUMPER FROM PERSONA WHERE ESTPER = 'A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persona = new Persona();
                persona.setIdper(rs.getInt("IDPER"));
                persona.setNomper(rs.getString("NOMPER"));
                persona.setNumper(rs.getInt("NUMPER"));
                listado.add(persona);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listadoDaoCliente" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
}
