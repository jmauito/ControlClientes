/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Cliente;
import java.util.List;

/**
 *
 * @author mauit
 */
public interface ClienteDao {
    public List<Cliente> listar();
    public Cliente encontrar(Cliente cliente);
    public int insertar(Cliente cliente);
    public int actualizar(Cliente cliente);
    public int eliminar(Cliente cliente);
    
}
