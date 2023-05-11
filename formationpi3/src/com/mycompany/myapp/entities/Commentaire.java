/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author USER
 */
public class Commentaire {
      private int id,poste;
    private String decription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoste() {
        return poste;
    }

    public void setPoste(int poste) {
        this.poste = poste;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
    public Commentaire(int id, int poste, String decription) {
        this.id = id;
        this.poste = poste;
        this.decription = decription;
    }

    public Commentaire(int poste, String decription) {
        this.poste = poste;
        this.decription = decription;
    }

    public Commentaire() {
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", poste=" + poste + ", decription=" + decription + '}';
    }

    
}
