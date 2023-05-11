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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;

/**
 *
 * @author ASUS
 */
public class ModifierProduitForm extends BaseForm{
     Form current;
         public ModifierProduitForm(Resources res, Produit r)
         {
                  super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
 Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier centre");
        getContentPane().setScrollVisible(false);
        
                super.addSideMenu(res);
 
        TextField nomproduit = new TextField(r.getNomProduit(), "NomProduit" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescriptionProduit(), "description" , 20 , TextField.ANY);
                TextField etat = new TextField(r.getEtatProduit(), "etat" , 20 , TextField.ANY);
               TextField prix = new TextField(String.valueOf(r.getPrixProduit()) , "prix" , 20 , TextField.ANY);
                               TextField brand = new TextField(r.getBrand(), "brand" , 20 , TextField.ANY);
                                                              TextField localisation = new TextField(r.getLocalisationProduit(), "localisation" , 20 , TextField.ANY);


   nomproduit.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        etat.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");
        brand.setUIID("NewsTopLine");
        localisation.setUIID("NewsTopLine");
        
        nomproduit.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        etat.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(true);
        brand.setSingleLineTextArea(true);
        localisation.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       
         //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNomProduit(nomproduit.getText());
           r.setDescriptionProduit(description.getText());
           r.setEtatProduit(etat.getText());
         //  r.setTelephoneCentre(telephone.getText());
           
                     r.setLocalisationProduit(localisation.getText());
                                          r.setEtatProduit(etat.getText());


       if(ServiceProduit.getInstance().modifierProduit(r)) { // if true
           new ListProduitForm(res).show();
       }
        });
       
       
        Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListProduitForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nomproduit),
                createLineSeparator(),
                 new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(etat),
                  createLineSeparator(),
                 new FloatingHint(brand),
                createLineSeparator(),
                                 new FloatingHint(prix),

                createLineSeparator(),
                new FloatingHint(localisation),

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
