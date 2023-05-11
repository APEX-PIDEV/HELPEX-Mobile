/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Tasks;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author Eya
 */
public class tasksForm extends SideMenuBaseForm{

    String searching ;
    
   public tasksForm(Form previous,Resources res) {
        super(BoxLayout.y());
               setLayout(new BorderLayout());
               
final FontImage placeholderiMAGE =FontImage.createMaterial(FontImage.MATERIAL_FOLDER_OPEN, "label", 6);


       InfiniteContainer list= new InfiniteContainer() {
           private ArrayList<Tasks> tasks_Array ;
            @Override
            public Component[] fetchComponents(int index, int amount) {
                   if (index ==0){
                       tasks_Array=ServiceTask.getInstance().getAllTasks();
                       if (searching !=null && searching.length()>0){
                           ArrayList<Tasks> TASKS_list= new ArrayList<>();
                           searching=searching.toLowerCase();
                           for(Tasks t: TASKS_list){
                               if(t.getTitre().toLowerCase().indexOf(searching )> -1){
                                   TASKS_list.add(t);
                               }
                               
                           }
                           tasks_Array= new ArrayList <Tasks>();
                           TASKS_list=tasks_Array ;
                           
                       }
                   }
                   if (index + amount > tasks_Array.size()){
                       amount = tasks_Array.size() -index;
                       if (amount<=0){
                           return null ;
                       }
                   }
                   Component[] more = new Component[amount];
                   for (int iter =0 ; iter< amount ; iter++){
                       int offset = index+iter;
                       MultiButton mb = new MultiButton(tasks_Array.get(offset).toString());

                       //style
                       
                       mb.addPointerReleasedListener(al->{
                           infoTask infoTask1 = new infoTask(tasks_Array.get(offset),previous,res);
                           infoTask1.show();
                       });
                       mb.setIcon(placeholderiMAGE);
                       mb.addLongPressListener(l->{
                           Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce task ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                if(ServiceTask.getInstance().deleteTask(tasks_Array.get(offset).getId())) {
                   // new tasksForm(previous).show();
                   //previous.showBack();
            revalidate();
                    
                }

                       });
                       
        
                       Display.getInstance().callSeriallyOnIdle(()->{

                           mb.setIcon(placeholderiMAGE);
                           
                       });
                       more[iter]=mb ;
                   }
             
                                       return more ;

       }};
       getToolbar().addSearchCommand(e->{
           searching =(String)e.getSource();
           list.refresh();
       });
       add(CENTER,list);

            
      

      

    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first,Tasks t) {
        
        Container buttonContainer = new Container(new FlowLayout(Component.CENTER));

        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        //finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
       Button customButton = new Button("editer");

       
       
customButton.addActionListener(e -> {
    EditTaskForm editTaskForm =new EditTaskForm(t,this);
                editTaskForm.show();
    
  
});
               // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
 //  finishLandingPage.addPointerReleasedListener(l->{
 //           infoTask infoTask= new infoTask(t);
 //           infoTask.show();
            
          
 //       });

//customButton.setUIID("finishLandingPage");

//finishLandingPage.addComponent(BorderLayout.WEST, customButton);

        //add(FlowLayout.encloseIn(finishLandingPage));
       buttonContainer.addAll(finishLandingPage, customButton);
               add(buttonContainer);


        
        finishLandingPage.addPointerReleasedListener(l->{
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce task ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                if(ServiceTask.getInstance().deleteTask(t.getId())) {
                   // new tasksForm(previous).show();
                   //previous.showBack();
                    System.out.println("deleteed but how to turn backkkkk");
                    refreshTheme();
                }
            
        });
        
        
        
        
      //  finishLandingPage.addLongPressListener(l->{
      //          EditTaskForm editTaskForm =new EditTaskForm(t);
      //          editTaskForm.show();
      //  });
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
      
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
    
    
    
}
