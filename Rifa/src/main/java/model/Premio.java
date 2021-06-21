/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jesus
 */
public class Premio {
    private Integer idpre;
    private String nompre;
    private String despre;
    private Integer ordpre;

    public Premio() {
    }

    public Premio(Integer idpre, String nompre, String despre, Integer ordpre) {
        this.idpre = idpre;
        this.nompre = nompre;
        this.despre = despre;
        this.ordpre = ordpre;
    }

    public Integer getIdpre() {
        return idpre;
    }

    public void setIdpre(Integer idpre) {
        this.idpre = idpre;
    }

    public String getNompre() {
        return nompre;
    }

    public void setNompre(String nompre) {
        this.nompre = nompre;
    }

    public String getDespre() {
        return despre;
    }

    public void setDespre(String despre) {
        this.despre = despre;
    }

    public Integer getOrdpre() {
        return ordpre;
    }

    public void setOrdpre(Integer ordpre) {
        this.ordpre = ordpre;
    }




    
}
