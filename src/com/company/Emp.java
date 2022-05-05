package com.company;

public class Emp {
    String codi;
    String cognom;
    String ofici;
    String dataAlta;


    String codi_jefe;
    float salari, comissio;

    public Emp(String codi, String cognom, String ofici, String dataAlta, String codi_jefe, float salari, float comissio) {
        this.codi = codi;
        this.cognom = cognom;
        this.ofici = ofici;
        this.dataAlta = dataAlta;
        this.codi_jefe = codi_jefe;
        this.salari = salari;
        this.comissio = comissio;
    }

    public String getCodi_jefe() {
        return codi_jefe;
    }

    public void setCodi_jefe(String codi_jefe) {
        this.codi_jefe = codi_jefe;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getOfici() {
        return ofici;
    }

    public void setOfici(String ofici) {
        this.ofici = ofici;
    }

    public String getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(String dataAlta) {
        this.dataAlta = dataAlta;
    }

    public float getSalari() {
        return salari;
    }

    public void setSalari(float salari) {
        this.salari = salari;
    }

    public float getComissio() {
        return comissio;
    }

    public void setComissio(float comissio) {
        this.comissio = comissio;
    }
}
