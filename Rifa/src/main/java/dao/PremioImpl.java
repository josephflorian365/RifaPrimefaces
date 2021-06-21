/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Persona;
import model.Premio;

/**
 *
 * @author jesus
 */
public class PremioImpl extends Conexion {

    public void registrar(Premio pre) throws Exception {
        String sql = "INSERT INTO PREMIO (NOMPRE,DESPRE,ESTPRE,ORDPRE) VALUES (?,?,'A',?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pre.getNompre());
            ps.setString(2, pre.getDespre());
            ps.setInt(3, pre.getOrdpre());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    public void cambiarEstado(Premio pre) throws Exception {
        String sql = "UPDATE PREMIO SET ESTPRE = 'I' WHERE IDPRE = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, pre.getIdpre());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }
    
    public void quitarPrimerGanador(Integer premio) throws Exception {
        String sql = "UPDATE PREMIO SET ESTPRE = 'I' WHERE IDPRE = '"+premio+"' AND ESTPRE = 'A' ";
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
    
    public Premio mostrarPremio(Integer numpre) throws Exception {
        Premio premio = null;
        String sql = "SELECT DESPRE,NOMPRE FROM PREMIO WHERE IDPRE = '"+numpre+"' AND ESTPRE = 'A' ";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("existe");
                premio = new Premio();
                premio.setDespre(rs.getString("DESPRE"));
                premio.setNompre(rs.getString("NOMPRE"));
            }
            
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
        return premio;
    }

    public List<Premio> listar() throws Exception {
        List<Premio> listado = new ArrayList<>();
        Premio premio;
        String sql = "SELECT IDPRE,NOMPRE,DESPRE,ORDPRE FROM PREMIO WHERE ESTPRE = 'A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                premio = new Premio();
                premio.setIdpre(rs.getInt("IDPRE"));
                premio.setNompre(rs.getString("NOMPRE"));
                premio.setDespre(rs.getString("DESPRE"));
                premio.setOrdpre(rs.getInt("ORDPRE"));
                listado.add(premio);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listadoDaoPremio" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
}
