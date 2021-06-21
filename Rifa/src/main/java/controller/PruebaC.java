/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Prueba;

@Named(value = "pruebaC")  //ManagedBean
@SessionScoped
public class PruebaC implements Serializable {

    private Prueba prueba = new Prueba();
    private List<Prueba> lista = new ArrayList<>();
    
    
    public void agregar(){
        Prueba p = new Prueba();
        p.setPrecio(prueba.getPrecio());
        p.setTotal(prueba.getTotal());
        lista.add(p);
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public List<Prueba> getLista() {
        return lista;
    }

    public void setLista(List<Prueba> lista) {
        this.lista = lista;
    }
    
    
}
