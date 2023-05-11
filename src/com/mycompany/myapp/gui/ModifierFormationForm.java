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
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.services.ServiceFormation;


/**
 *
 * @author ASUS
 */
public class ModifierFormationForm extends BaseForm{
      Form current;
         public ModifierFormationForm(Resources res, Formation r)
         {
                  super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
 Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier centre");
        getContentPane().setScrollVisible(false);
        
                super.addSideMenu(res);
 
        TextField nomformation = new TextField(r.getNomFormation(), "nomformation" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescriptionFormation(), "description" , 20 , TextField.ANY);
               TextField cout = new TextField(String.valueOf(r.getCoutFormation()) , "cout" , 20 , TextField.ANY);
               TextField nombredeplace = new TextField(String.valueOf(r.getNombreDePlace()) , "nombre de place" , 20 , TextField.ANY);
               TextField duree = new TextField(String.valueOf(r.getDuree()) , "duree" , 20 , TextField.ANY);

   nomformation.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        cout.setUIID("NewsTopLine");
        nombredeplace.setUIID("NewsTopLine");
        duree.setUIID("NewsTopLine");
        
         nomformation.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        cout.setSingleLineTextArea(true);
        nombredeplace.setSingleLineTextArea(true);
        duree.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       
         //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNomFormation(nomformation.getText());
           r.setDescriptionFormation(description.getText());
          // r.setEmailCentre(email.getText());
         //  r.setTelephoneCentre(telephone.getText());
           // ma aarftech kifeh naayet lil int
            //         r.setSiteWebCentre(email.getText());

       if(ServiceFormation.getInstance().modifierCentre(r)) { // if true
           new ListFormationForm(res).show();
       }
        });
       
       
        Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListFormationForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nomformation),
                createLineSeparator(),
                 new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(duree),
                  createLineSeparator(),
                 new FloatingHint(cout),
                createLineSeparator(),
                                 new FloatingHint(nombredeplace),

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
