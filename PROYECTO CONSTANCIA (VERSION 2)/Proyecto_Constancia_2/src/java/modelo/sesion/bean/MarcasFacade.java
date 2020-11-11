/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.sesion.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.entidades.Marcas;

/**
 *
 * @author Reynaldo
 */
@Stateless
public class MarcasFacade extends AbstractFacade<Marcas> {

    @PersistenceContext(unitName = "Proyecto_Constancia_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcasFacade() {
        super(Marcas.class);
    }
    
}
