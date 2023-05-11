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
public class Produit {
    private int id;
    private String NomProduit;
        private String EtatProduit;
        private int PrixProduit; 
        private String DescriptionProduit;
private String localisationProduit;
private String Brand;

    public Produit(String NomProduit, String EtatProduit, int PrixProduit, String DescriptionProduit, String localisationProduit, String Brand) {
        this.NomProduit = NomProduit;
        this.EtatProduit = EtatProduit;
        this.PrixProduit = PrixProduit;
        this.DescriptionProduit = DescriptionProduit;
        this.localisationProduit = localisationProduit;
        this.Brand = Brand;
    }

    public Produit(int id, String NomProduit, String EtatProduit, int PrixProduit, String DescriptionProduit, String localisationProduit, String Brand) {
        this.id = id;
        this.NomProduit = NomProduit;
        this.EtatProduit = EtatProduit;
        this.PrixProduit = PrixProduit;
        this.DescriptionProduit = DescriptionProduit;
        this.localisationProduit = localisationProduit;
        this.Brand = Brand;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", NomProduit=" + NomProduit + ", EtatProduit=" + EtatProduit + ", PrixProduit=" + PrixProduit + ", DescriptionProduit=" + DescriptionProduit + ", localisationProduit=" + localisationProduit + ", Brand=" + Brand + '}';
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public String getEtatProduit() {
        return EtatProduit;
    }

    public void setEtatProduit(String EtatProduit) {
        this.EtatProduit = EtatProduit;
    }

    public int getPrixProduit() {
        return PrixProduit;
    }

    public void setPrixProduit(int PrixProduit) {
        this.PrixProduit = PrixProduit;
    }

    public String getDescriptionProduit() {
        return DescriptionProduit;
    }

    public void setDescriptionProduit(String DescriptionProduit) {
        this.DescriptionProduit = DescriptionProduit;
    }

    public String getLocalisationProduit() {
        return localisationProduit;
    }

    public void setLocalisationProduit(String localisationProduit) {
        this.localisationProduit = localisationProduit;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }
    
    
}
