/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.sesion.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.entidades.CategoriaClientes;

/**
 *
 * @author Reynaldo
 */
@Stateless
public class CategoriaClientesFacade extends AbstractFacade<CategoriaClientes> {

    @PersistenceContext(unitName = "Proyecto_ConstanciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaClientesFacade() {
        super(CategoriaClientes.class);
    }
    
}
