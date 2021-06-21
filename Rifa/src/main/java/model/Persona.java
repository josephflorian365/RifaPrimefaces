/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Persona {
    private Integer idper;
    private String nomper;
    private Integer numper;
    private String estper;

    public Persona() {
    }

    public Persona(Integer idper, String nomper, Integer numper, String estper) {
        this.idper = idper;
        this.nomper = nomper;
        this.numper = numper;
        this.estper = estper;
    }

    public Integer getIdper() {
        return idper;
    }

    public void setIdper(Integer idper) {
        this.idper = idper;
    }

    public String getNomper() {
        return nomper;
    }

    public void setNomper(String nomper) {
        this.nomper = nomper;
    }

    public Integer getNumper() {
        return numper;
    }

    public void setNumper(Integer numper) {
        this.numper = numper;
    }

    public String getEstper() {
        return estper;
    }

    public void setEstper(String estper) {
        this.estper = estper;
    }

    
    
}
