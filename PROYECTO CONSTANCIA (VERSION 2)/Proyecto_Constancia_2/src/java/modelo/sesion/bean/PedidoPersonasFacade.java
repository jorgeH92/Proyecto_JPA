/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.sesion.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.entidades.PedidoPersonas;

/**
 *
 * @author Reynaldo
 */
@Stateless
public class PedidoPersonasFacade extends AbstractFacade<PedidoPersonas> {

    @PersistenceContext(unitName = "Proyecto_Constancia_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoPersonasFacade() {
        super(PedidoPersonas.class);
    }
    
}
