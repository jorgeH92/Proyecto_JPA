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
import modelo.entidades.*;
import modelo.sesion.beans.*;

/**
 *
 * @author debian
 */
@ManagedBean
@ViewScoped
public class PedidosBeans implements Serializable {

    @Inject
    private PedidosFacade pedidosF;
    private Pedidos pedidos;

    @Inject
    private CategoriaClientesFacade cateCliF;
    private CategoriaClientes cateCli;

    @Inject
    private PedidoPersonasFacade pedPerF;
    private PedidoPersonas pedPer;

    @Inject
    private ZonaFacade zonaF;
    private Zona zona;

    private List<Pedidos> listPed = new ArrayList();
    private List<CategoriaClientes> listCatCli = new ArrayList();
    private List<PedidoPersonas> listPedPer = new ArrayList();

    private List<Zona> listZona = new ArrayList();

    private String sql;

    public PedidosBeans() {
    }

    @PostConstruct
    public void init() {
        pedidos = new Pedidos();
        cateCli = new CategoriaClientes();
        pedPer = new PedidoPersonas();
        zona = new Zona();
        listarZona();
        listarPedidos();
        listarCatCli();
        listarPedPer();
    }

    public String insertarPedidos() {
        pedidos.setIdCliente(cateCli);
        pedidos.setIdPedidoPersonas(pedPer);
        pedidos.setIdZona(zona);
        pedidosF.create(pedidos);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Pedidos Agregado"));
        return "TablaPedidos.xhtml?faces-redirect=true";
    }

    public String eliminarPedidos() {
        pedidosF.remove(pedidos);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Pedidos Eliminado"));
        listarPedidos();
        return "TablaPedidos.xhtml?faces-redirect=true";
    }

    public String modificarPedidos() {
        pedidos.setIdCliente(cateCli);
        pedidos.setIdPedidoPersonas(pedPer);
        pedidos.setIdZona(zona);
        pedidosF.edit(pedidos);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Pedidos Modificado"));
        listarPedidos();
        return "TablaPedidos.xhtml?faces-redirect=true";
    }

    public List<Pedidos> listarPedidos() {
        listPed = pedidosF.findAll();
        return listPed;
    }

    public List<CategoriaClientes> listarCatCli() {
        listCatCli = cateCliF.findAll();
        return listCatCli;
    }

    public List<PedidoPersonas> listarPedPer() {
        listPedPer = pedPerF.findAll();
        return listPedPer;
    }

    public List<Zona> listarZona() {
        listZona = zonaF.findAll();
        return listZona;
    }

    public int ultimoPedido() {
        String codigo = "";
        int r = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto_ConstanciaPU");
        EntityManager em = emf.createEntityManager();

        //Utilizo JPQL
        sql = "select count(*) from pedidos";
        Query q = em.createNativeQuery(sql);

        codigo = q.getSingleResult().toString();

        r = Integer.parseInt(codigo);
        em.close();
        emf.close();
        return r;

    }

    public PedidosFacade getPedidosF() {
        return pedidosF;
    }

    public void setPedidosF(PedidosFacade pedidosF) {
        this.pedidosF = pedidosF;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public CategoriaClientesFacade getCateCliF() {
        return cateCliF;
    }

    public void setCateCliF(CategoriaClientesFacade cateCliF) {
        this.cateCliF = cateCliF;
    }

    public CategoriaClientes getCateCli() {
        return cateCli;
    }

    public void setCateCli(CategoriaClientes cateCli) {
        this.cateCli = cateCli;
    }

    public PedidoPersonasFacade getPedPerF() {
        return pedPerF;
    }

    public void setPedPerF(PedidoPersonasFacade pedPerF) {
        this.pedPerF = pedPerF;
    }

    public PedidoPersonas getPedPer() {
        return pedPer;
    }

    public void setPedPer(PedidoPersonas pedPer) {
        this.pedPer = pedPer;
    }

    public List<Pedidos> getListPed() {
        return listPed;
    }

    public void setListPed(List<Pedidos> listPed) {
        this.listPed = listPed;
    }

    public List<CategoriaClientes> getListCatCli() {
        return listCatCli;
    }

    public void setListCatCli(List<CategoriaClientes> listCatCli) {
        this.listCatCli = listCatCli;
    }

    public List<PedidoPersonas> getListPedPer() {
        return listPedPer;
    }

    public void setListPedPer(List<PedidoPersonas> listPedPer) {
        this.listPedPer = listPedPer;
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
