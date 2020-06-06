/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import dominio.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauit
 */
public class ClienteDaoJDBC implements ClienteDao {
    private static final String SQL_SELECT = "select id_cliente, nombre,"
            + " apellido, telefono, email, saldo FROM cliente";
    private static final String SQL_SELECT_BY_ID = "select id_cliente, nombre,"
            + " apellido, telefono, email, saldo FROM cliente where id_cliente = ?";
    private static final String SQL_INSERT = "insert into cliente set "
            + " nombre = ?, apellido = ?, telefono = ?, email = ?, saldo = ? ";
    private static final String SQL_UPDATE = "update cliente set "
            + " nombre = ?, apellido = ?, telefono = ?, email = ?, saldo = ? "
            + " where id_cliente = ?";
    private static final String SQL_DELETE = "delete from cliente "
            + " where id_cliente = ?";
    
    /**
     *
     * @return
     */
    public List<Cliente> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setSaldo(rs.getDouble("saldo"));
                
                clientes.add(cliente);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return clientes;
        
    }
    
    public Cliente encontrar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();
            rs.absolute(1); //nos posicionamos en el primer registro y el único

            cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellido(rs.getString("apellido"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setSaldo(rs.getDouble("saldo"));
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return cliente;
    }
    
    public int insertar(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setDouble(5, cliente.getSaldo());
            
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
    public int actualizar(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
    public int eliminar(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdCliente());
            
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
}
