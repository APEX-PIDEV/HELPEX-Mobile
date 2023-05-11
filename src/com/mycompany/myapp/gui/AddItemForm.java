/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import java.text.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Item;
import com.mycompany.myapp.services.ItemService;
import javafx.concurrent.Task;
import com.mycompany.myapp.entities.Tasks;



/**
 *
 * @author Eya
 */
public class AddItemForm extends Form{
     public AddItemForm(Tasks t,Form previous,Resources res) {
        setTitle(" nouveau item");
        setLayout(BoxLayout.y());
                Label labelTitre=new Label("titre de item :");
          Label temps_t=new Label("temps à faire :");

        TextField tfTitre = new TextField("","titre");
         tfTitre.getStyle().setFgColor(0x000000);
        CheckBox cbStatus = new CheckBox("Status");
         cbStatus.getStyle().setFgColor(0x000000);
        Button btnValider = new Button("Add task");
        Picker timePicker = new Picker();
timePicker.setType(Display.PICKER_TYPE_TIME);
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()<6))
                    Dialog.show("Alert", "saisir une titre valide", new Command("OK"));
                else
                {
                    try {
                        boolean status=false;
                        if(cbStatus.isSelected())
                            status=true;
                        int time=timePicker.getTime();
                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("hh:mm:ss");
                        Item i = new Item( tfTitre.getText().toString(),"12:06:00",status);
                        if( ItemService.getInstance().addItem(i,t.getId(),simpleDateFormat.format(timePicker.getTime())))
                        {  Dialog.show("Success","item ajouter!",new Command("OK"));

                            infoTask infTask =new  infoTask(t,previous,res);
                            infTask.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(labelTitre,tfTitre,temps_t,timePicker,cbStatus,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
   
        
    

  
    
   
   
    
}
