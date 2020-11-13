/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.entidades.Zona;
import modelo.sesion.beans.ZonaFacade;

/**
 *
 * @author debian
 */

@ManagedBean
@ViewScoped
public class ZonaBeans  implements Serializable{
     
    @Inject
    private ZonaFacade zonaF;

    private Zona zona;
    
        private List<Zona> listZona = new ArrayList();
    private String sql;

    public ZonaBeans() {
    }


    
    @PostConstruct
    public void init() {
        zona = new Zona();
        listarZona();
    }
    

    
    public int ultimaZona() {
        String codigo="";
int r=0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto_ConstanciaPU");
        EntityManager em = emf.createEntityManager();

        //Utilizo JPQL
        sql = "select count(*) from zona";
    Query q = em.createNativeQuery(sql);
        

    codigo=q.getSingleResult().toString();
    
            r=Integer.parseInt(codigo);     
        em.close();
        emf.close();
        return r;

    }
    
    
    
    public String insertarZona() {
        zonaF.create(zona);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Zona Agregada"));
        return "TablaZona.xhtml?faces-redirect=true";
    }
    
        public String eliminarZona() {
        zonaF.remove(zona);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Zona Eliminada"));
        listarZona();
        return "TablaZona.xhtml?faces-redirect=true";
    }

    public String modificarZona() {
        zonaF.edit(zona);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Zona Modificada"));
        listarZona();
        return "TablaZona.xhtml?faces-redirect=true";
    }

    public List<Zona> listarZona(){
        listZona=zonaF.findAll();
        return listZona;
    }

    public ZonaFacade getZonaF() {
        return zonaF;
    }

    public void setZonaF(ZonaFacade zonaF) {
        this.zonaF = zonaF;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public List<Zona> getListZona() {
        return listZona;
    }

    public void setListZona(List<Zona> listZona) {
        this.listZona = listZona;
    }



    
}

