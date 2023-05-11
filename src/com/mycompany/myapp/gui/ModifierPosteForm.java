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
import com.mycompany.myapp.entities.Poste;
import com.mycompany.myapp.services.ServicePoste;

/**
 *
 * @author ASUS
 */
public class ModifierPosteForm extends BaseForm{
    Form current;
         public ModifierPosteForm(Resources res, Poste r)
         {
                  super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
 Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier centre");
        getContentPane().setScrollVisible(false);
        
               // super.addSideMenu(res);
 
        TextField nomcentre = new TextField(r.getTitre(), "Titre" , 20 , TextField.ANY);
        TextField adressecentre = new TextField(r.getDecription() , "Description" , 20 , TextField.ANY);
              

   nomcentre.setUIID("NewsTopLine");
        adressecentre.setUIID("NewsTopLine");
        
        nomcentre.setSingleLineTextArea(true);
        adressecentre.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       
         //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setTitre(nomcentre.getText());
           r.setDecription(adressecentre.getText());
         //  r.setTelephoneCentre(telephone.getText());

       if(ServicePoste.getInstance().modifierPoste(r)) { // if true
           new ListPosteForm(res).show();
       }
        });
       
       
        Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListPosteForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nomcentre),
                createLineSeparator(),
                 new FloatingHint(adressecentre),
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
