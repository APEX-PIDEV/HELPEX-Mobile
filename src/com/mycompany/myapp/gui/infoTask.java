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
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
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
import com.mycompany.myapp.entities.Item;
import com.mycompany.myapp.entities.Tasks;
import com.mycompany.myapp.services.ItemService;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;
import javafx.concurrent.Task;

/**
 *
 * @author Eya
 */
public class infoTask extends SideMenuBaseForm{
    
     public infoTask() {}

    public infoTask(Tasks t,Form previous,Resources res) {
      
           super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
                     Label l= new Label("date début");
                       l.getStyle().setBgColor(0x000000);
                         Label lfin= new Label("date fin");
                       l.getStyle().setBgColor(0x000000);
        Container remainingTasks = BoxLayout.encloseY(                      
             
l, new Label(t.getStart_date())
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
               lfin,
                        new Label(t.getEnd_date())
                     
        );
        completedTasks.setUIID("CompletedTasks");
           Label val = new Label("non valid" );
            val.getStyle().setFgColor(0xFF0000);
        if(t.isIs_valid()){
            val.setText("valid");
            val.getStyle().setFgColor(0x00FF00);
                }
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(t.getTitre(), "Title"),
                                 
                                  val
                                )
                            ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
       fab.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    AddItemForm addItemForm =new AddItemForm(t,previous,res);
                    addItemForm.show();
                }
            });
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                        
        add(new Label("liste des items :"));
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Label", 3);
        
         ArrayList<Item> items = ItemService.getInstance().getAllItems(t.getId());
        for (Item i :items) {
                         addButtonBottom( t,res,arrowDown,i.getTitre()+"   "+i.getTime().substring(11, 19), 0xd997f1, true,i);

            
        }
        
        setupSideMenu(res);
    }
    
    private void addButtonBottom(Tasks t ,Resources res,Image arrowDown, String text, int color, boolean first,Item i) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
        finishLandingPage.addPointerReleasedListener(l->{
            EditItemForm editItemForm =new EditItemForm(t,i,this,res);
                editItemForm.show();
            
        });
         finishLandingPage.addLongPressListener(o->{
           Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce item "+i.getTitre()+"?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                if(ItemService.getInstance().deleteitem(i.getId())) {
                   // new tasksForm(previous).show();
                   //previous.showBack();
                    this.removeComponent(finishLandingPage.getParent());
        this.revalidate();
                    
                    
                }
   
       });
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
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}

    
    
    
   
    
    
    
  



