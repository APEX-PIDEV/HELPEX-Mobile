/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author MSI
 */
public class Organisation {
      private int id;
    private String descriptionOrganisation,emailOrganisation,numTelOrganisation,paymentInfo,NomOrg;

    public Organisation() {
    }

    public Organisation(String descriptionOrganisation, String emailOrganisation, String numTelOrganisation, String paymentInfo, String NomOrg) {
        this.descriptionOrganisation = descriptionOrganisation;
        this.emailOrganisation = emailOrganisation;
        this.numTelOrganisation = numTelOrganisation;
        this.paymentInfo = paymentInfo;
        this.NomOrg = NomOrg;
    }

    public Organisation(int id, String descriptionOrganisation, String emailOrganisation, String numTelOrganisation, String paymentInfo, String NomOrg) {
        this.id = id;
        this.descriptionOrganisation = descriptionOrganisation;
        this.emailOrganisation = emailOrganisation;
        this.numTelOrganisation = numTelOrganisation;
        this.paymentInfo = paymentInfo;
        this.NomOrg = NomOrg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptionOrganisation() {
        return descriptionOrganisation;
    }

    public void setDescriptionOrganisation(String descriptionOrganisation) {
        this.descriptionOrganisation = descriptionOrganisation;
    }

    public String getEmailOrganisation() {
        return emailOrganisation;
    }

    public void setEmailOrganisation(String emailOrganisation) {
        this.emailOrganisation = emailOrganisation;
    }

    public String getNumTelOrganisation() {
        return numTelOrganisation;
    }

    public void setNumTelOrganisation(String numTelOrganisation) {
        this.numTelOrganisation = numTelOrganisation;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getNomOrg() {
        return NomOrg;
    }

    public void setNomOrg(String NomOrg) {
        this.NomOrg = NomOrg;
    }
    
}
