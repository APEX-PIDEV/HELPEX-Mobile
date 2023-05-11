/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ASUS
 */
public class Formation {
   private int id;
   private String nomFormation;
   private String descriptionFormation ;
   private int coutFormation ;
   private int NombreDePlace;
   private int duree;

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", nomFormation=" + nomFormation + ", descriptionFormation=" + descriptionFormation + ", coutFormation=" + coutFormation + ", NombreDePlace=" + NombreDePlace + ", duree=" + duree + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public void setDescriptionFormation(String descriptionFormation) {
        this.descriptionFormation = descriptionFormation;
    }

    public void setCoutFormation(int coutFormation) {
        this.coutFormation = coutFormation;
    }

    public void setNombreDePlace(int NombreDePlace) {
        this.NombreDePlace = NombreDePlace;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public String getDescriptionFormation() {
        return descriptionFormation;
    }

    public int getCoutFormation() {
        return coutFormation;
    }

    public int getNombreDePlace() {
        return NombreDePlace;
    }

    public int getDuree() {
        return duree;
    }

    public Formation(String nomFormation, String descriptionFormation, int coutFormation, int NombreDePlace, int duree) {
        this.nomFormation = nomFormation;
        this.descriptionFormation = descriptionFormation;
        this.coutFormation = coutFormation;
        this.NombreDePlace = NombreDePlace;
        this.duree = duree;
    }

    public Formation(){
    }
    public Formation(int id, String nomFormation, String descriptionFormation, int coutFormation, int NombreDePlace, int duree) {
        this.id = id;
        this.nomFormation = nomFormation;
        this.descriptionFormation = descriptionFormation;
        this.coutFormation = coutFormation;
        this.NombreDePlace = NombreDePlace;
        this.duree = duree;
    }
    
}
