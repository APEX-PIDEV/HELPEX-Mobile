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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Item;
import com.mycompany.myapp.entities.Tasks;
import com.mycompany.myapp.services.ItemService;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 *
 * @author Eya
 */
public class EditItemForm extends SideMenuBaseForm
{

    public EditItemForm(Tasks t ,Item item,Form previous,Resources res) {
        
        
        getStyle().setBgColor(0x99CCCC);
        
        
    
        

        
        
    setTitle(" edit item");
        setLayout(BoxLayout.y());
        Label labelTitre=new Label("titre de task :");
          Label temps_t=new Label("temps à faire :");
        TextField tfTitre = new TextField(item.getTitre(),"titre");
        tfTitre.getStyle().setFgColor(0x000000);
        CheckBox cbStatus = new CheckBox("Status");
        cbStatus.getStyle().setBgColor(0x000000);
        cbStatus.setSelected(false);
         if(item.isIs_complete()){
             cbStatus.setSelected(true);
         }
        
        Button btnValider = new Button("editer item");
        
        Picker timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);

        timePicker.getStyle().setBgColor(0x000000);


        
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
                        
                        
                        

                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("hh:mm:ss");
                        Item i = new Item(item.getId(), tfTitre.getText().toString(),"12:06:00",status);
                        if( ItemService.getInstance().modifierItem(i,"12:06:00"))
                        {
                           Dialog.show("Success","editer avec succes",new Command("OK"));
                             infoTask infTask =new  infoTask(t, previous,res);
                            infTask.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                         infoTask infTask =new  infoTask(t, previous, res);
                            infTask.showBack();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(labelTitre,tfTitre,temps_t,timePicker,cbStatus,btnValider);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

                
    }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
    
   
        
    

  
    