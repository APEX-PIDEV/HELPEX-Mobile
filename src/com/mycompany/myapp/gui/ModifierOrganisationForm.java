/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Organisation;
import com.mycompany.myapp.services.ServiceOrganisation;

/**
 *
 * @author MSI
 */
public class ModifierOrganisationForm extends BaseForm{
     Form current;
         public ModifierOrganisationForm(Resources res, Organisation r)
         {
                  super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
 Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier centre");
        getContentPane().setScrollVisible(false);
        
                super.addSideMenu(res);
         //descriptionOrganisation,emailOrganisation,numTelOrganisation,paymentInfo,NomOrg

        TextField descriptionOrganisation = new TextField(r.getDescriptionOrganisation(), "descriptionOrganisation" , 20 , TextField.ANY);
        TextField emailOrganisation = new TextField(r.getEmailOrganisation() , "adresse" , 20 , TextField.ANY);
                TextField numTelOrganisation = new TextField(r.getNumTelOrganisation(), "email" , 20 , TextField.ANY);
               TextField paymentInfo = new TextField(String.valueOf(r.getPaymentInfo()) , "telephone" , 20 , TextField.ANY);
                               TextField NomOrg = new TextField(r.getNomOrg(), "siteWeb" , 20 , TextField.ANY);

   descriptionOrganisation.setUIID("NewsTopLine");
        emailOrganisation.setUIID("NewsTopLine");
        numTelOrganisation.setUIID("NewsTopLine");
        paymentInfo.setUIID("NewsTopLine");
        NomOrg.setUIID("NewsTopLine");
        
        descriptionOrganisation.setSingleLineTextArea(true);
        emailOrganisation.setSingleLineTextArea(true);
        numTelOrganisation.setSingleLineTextArea(true);
        paymentInfo.setSingleLineTextArea(true);
        NomOrg.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       
         //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setDescriptionOrganisation(descriptionOrganisation.getText());
           r.setEmailOrganisation(emailOrganisation.getText());
           r.setNumTelOrganisation(numTelOrganisation.getText());
           r.setNomOrg(NomOrg.getText());
           
                     r.setPaymentInfo(paymentInfo.getText());

       if(ServiceOrganisation.getInstance().modifierCentre(r)) { // if true
           new ListOrganisationForm(res).show();
       }
        });
       
       
        Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListOrganisationForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(descriptionOrganisation),
                createLineSeparator(),
                 new FloatingHint(emailOrganisation),
                createLineSeparator(),
                new FloatingHint(numTelOrganisation),
                  createLineSeparator(),
                 new FloatingHint(paymentInfo),
                createLineSeparator(),
                                 new FloatingHint(NomOrg),

                createLineSeparator(),
               // etatCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
       
       
         }
    
}
