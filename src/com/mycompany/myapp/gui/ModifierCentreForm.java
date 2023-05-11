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
import com.mycompany.myapp.entities.Centre;
import com.mycompany.myapp.services.ServiceCentre;

/**
 *
 * @author ASUS
 */
public class ModifierCentreForm extends BaseForm{
    Form current;
         public ModifierCentreForm(Resources res, Centre r)
         {
                  super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
 Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier centre");
        getContentPane().setScrollVisible(false);
        
                super.addSideMenu(res);
 
        TextField nomcentre = new TextField(r.getNomCentre(), "nomCentre" , 20 , TextField.ANY);
        TextField adressecentre = new TextField(r.getAdresseCentre() , "adresse" , 20 , TextField.ANY);
                TextField email = new TextField(r.getEmailCentre(), "email" , 20 , TextField.ANY);
               TextField telephone = new TextField(String.valueOf(r.getTelephoneCentre()) , "telephone" , 20 , TextField.ANY);
                               TextField siteweb = new TextField(r.getSiteWebCentre(), "siteWeb" , 20 , TextField.ANY);

   nomcentre.setUIID("NewsTopLine");
        adressecentre.setUIID("NewsTopLine");
        email.setUIID("NewsTopLine");
        telephone.setUIID("NewsTopLine");
        siteweb.setUIID("NewsTopLine");
        
        nomcentre.setSingleLineTextArea(true);
        adressecentre.setSingleLineTextArea(true);
        email.setSingleLineTextArea(true);
        telephone.setSingleLineTextArea(true);
        siteweb.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       
         //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNomCentre(nomcentre.getText());
           r.setAdresseCentre(adressecentre.getText());
           r.setEmailCentre(email.getText());
         //  r.setTelephoneCentre(telephone.getText());
           
                     r.setSiteWebCentre(email.getText());

       if(ServiceCentre.getInstance().modifierCentre(r)) { // if true
           new ListCentreForm(res).show();
       }
        });
       
       
        Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCentreForm(res).show();
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
                new FloatingHint(email),
                  createLineSeparator(),
                 new FloatingHint(telephone),
                createLineSeparator(),
                                 new FloatingHint(siteweb),

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
