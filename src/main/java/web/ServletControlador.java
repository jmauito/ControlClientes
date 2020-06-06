/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import datos.ClienteDao;
import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author mauit
 */
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        //this.accionDefault(req,resp);
        String accion = req.getParameter("accion");
        
        if(accion != null){
            switch(accion){
                
                case "editar":
                    this.editarCliente(req, resp);
                    break;
                case "confirmarEliminarCliente":
                    this.confirmarEliminarCliente(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
            }
        } else {
            this.accionDefault(req, resp);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        String accion = req.getParameter("accion");
        
        if(accion != null){
            switch(accion){
                case "insertar":
                    this.insertarCliente(req, resp);
                    break;
                case "modificar":
                    this.modificarCliente(req, resp);
                    break;
                case "eliminar":
                    this.eliminarCliente(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
            }
        } else {
            this.accionDefault(req, resp);
        }
        
        
        
    }
    
    private void accionDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("Clientes: " + clientes);
        HttpSession sesion = req.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        //req.getRequestDispatcher("clientes.jsp").forward(req, resp);
        resp.sendRedirect("clientes.jsp");
    }
    
    private double calcularSaldoTotal(List<Cliente> clientes){
        double saldoTotal = 0;
        for(Cliente cliente: clientes){
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
    
    
    private void insertarCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String email = req.getParameter("email");
        String telefono = req.getParameter("telefono");
        double saldo = 0;
        String sSaldo = req.getParameter("saldo");
        
        if (sSaldo != null && !"".equals(sSaldo)){
            saldo = Double.parseDouble(sSaldo);
        }
        
        Cliente cliente = new Cliente(nombre,apellido,email,telefono,saldo);
        ClienteDao clienteDao = new ClienteDaoJDBC();
        int rows = clienteDao.insertar(cliente);
        System.out.println("Registros insertados: " + rows);
        
        this.accionDefault(req, resp);
        
    }
    
       
    private void editarCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCliente = Integer.parseInt(req.getParameter("idCliente"));
        
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        req.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        req.getRequestDispatcher(jspEditar).forward(req, resp);
    }
    
    private void modificarCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCliente = Integer.parseInt(req.getParameter("idCliente"));
        
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String email = req.getParameter("email");
        String telefono = req.getParameter("telefono");
        String sSaldo = req.getParameter("saldo");
        
        Double saldo = 0.00;
        if (sSaldo != null && !"".equals(sSaldo)){
            saldo = Double.parseDouble(sSaldo);
        }
        
        Cliente cliente = new Cliente(idCliente,nombre,apellido,email, telefono, saldo);
        ClienteDao clienteDao = new ClienteDaoJDBC();
        int rows = clienteDao.actualizar(cliente);
        System.out.println("Registroas actualizados: " + rows);
        
        this.accionDefault(req, resp);
        
    }
    
    private void confirmarEliminarCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCliente = Integer.parseInt(req.getParameter("idCliente"));
        
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        req.setAttribute("cliente", cliente);
        String jspConfirmarEliminacion = "/WEB-INF/paginas/cliente/confirmarEliminarCliente.jsp";
        req.getRequestDispatcher(jspConfirmarEliminacion).forward(req, resp);
        
    }
    
    private void eliminarCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCliente = Integer.parseInt(req.getParameter("idCliente"));
        
        ClienteDao clienteDao = new ClienteDaoJDBC();
        int rows = clienteDao.eliminar(new Cliente(idCliente));
        
        System.out.println("Registros eliminados: " + rows);
        this.accionDefault(req, resp);
        
    }
}
