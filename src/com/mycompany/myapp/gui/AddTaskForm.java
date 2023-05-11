/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.mycompany.myapp.entities.Item;
import com.mycompany.myapp.entities.Tasks;
import com.mycompany.myapp.services.ItemService;
import com.mycompany.myapp.services.ServiceTask;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eya
 */
public class AddTaskForm extends Form{
     public AddTaskForm(Form previous) {
        setTitle(" nouveau task");
        setLayout(BoxLayout.y());
                Label labelTitre=new Label("titre de task :");
                Label complete=new Label("compltete :");
                
             
                            Label t1=new Label("debut :");
              Label t2=new Label("fin :");


        TextField tfTitre = new TextField("","titre");
        
         tfTitre.getStyle().setFgColor(0x000000);
        CheckBox cbStatus = new CheckBox("Status");
         cbStatus.getStyle().setFgColor(0x000000);
         
         
         
         
         
        Button btnValider = new Button("Add task");
        Picker timePicker_start = new Picker();
timePicker_start.setType(Display.PICKER_TYPE_DATE);
  Picker timePicker_end = new Picker();
timePicker_end.setType(Display.PICKER_TYPE_DATE);


        
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
                        
                        
                        
  
 Date date = timePicker_start.getDate();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String dateString1 = dateFormat.format(date);

 Date date2 = timePicker_end.getDate();
SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

String dateString2 = dateFormat.format(date2);





                        Tasks t = new Tasks( tfTitre.getText(),  dateString1,  dateString2,status);
                        
                        
                        
                        if( ServiceTask.getInstance().addTask(t))
                        {
                            
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                         
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(labelTitre,tfTitre,t1,timePicker_start,t2,timePicker_end,cbStatus,btnValider);
                        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
   
        
    

  
    
   
   
    
    
}
