package com.company;

import java.util.List;

public class Dept {

    String codi, nom, localitat;
    List<Emp>list_emp;

    public Dept(String codi, String nom, String localitat, List<Emp> list_emp) {
        this.codi = codi;
        this.nom = nom;
        this.localitat = localitat;
        this.list_emp = list_emp;
    }

    public List<Emp> getList_emp() {
        return list_emp;
    }

    public void setList_emp(List<Emp> list_emp) {
        this.list_emp = list_emp;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalitat() {
        return localitat;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "codi='" + codi + '\'' +
                ", nom='" + nom + '\'' +
                ", localitat='" + localitat + '\'' +
                ", list_emp=" + list_emp +
                '}';
    }
}
