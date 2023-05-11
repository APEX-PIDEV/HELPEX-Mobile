/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Item;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.concurrent.Task;

/**
 *
 * @author Eya
 */
public class ItemService {
      public ArrayList<Item> items;

    public static ItemService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ItemService() {
        req = new ConnectionRequest();
    }

    public static ItemService getInstance() {
        if (instance == null) {
            instance = new ItemService();
        }
        return instance;
    }
 public boolean addItem(Item i,int t_id,String time1) {
     
        int id =i.getId();
        String titre = i.getTitre();
        int is_complete=0;
        if( i.isIs_complete()==true){
            
        is_complete=1;
            
        }
      String time = i.getTime();
        int taskid=t_id ;
                String url = Statics.BASE_URL_Item+"json/ajout?titre="+titre+"&is_complete="+is_complete+"&time="+time1+"&taskid="+t_id ;

        
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   //Update 
    public boolean modifierItem(Item item,String t1) {
         int is_complete=0;
        if( item.isIs_complete()==true){
            
        is_complete=1;
            
        }
        System.out.println("whatatatattatatattatthe helll"+item.getId());
        String url = Statics.BASE_URL_Item +"editerJson/"+item.getId()+"?titre="+item.getTitre()+"&is_complete="+is_complete+"&time="+"00:00:40";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                boolean resultOk = req.getResponseCode() == 200; // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return false;
        
    }
        //Delete 
    public boolean deleteitem(int id ) {
        String url = Statics.BASE_URL_Item +"json/deleteitem/"+id;
        
        req.setUrl(url);
        
               req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }

    public ArrayList<Item> parseItem(String jsonText) {
        try {
            items = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ItemsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//tasksListJson is a map of list
            List<Map<String, Object>> list = (List<Map<String, Object>>) ItemsListJson.get("root");
            //list is a list of map
            for (Map<String, Object> obj : list) {
                //t is a task
                
                Item i = new Item();
                float id = Float.parseFloat(obj.get("id").toString());
                i.setId((int) id);
                  i.setTime(obj.get("time").toString().substring(11, 19));
               
                
                
                i.setIs_complete((Boolean.valueOf(obj.get("is_complete").toString())));
                if (obj.get("titre") == null) {
                    i.setTitre("null");   //here
                } else {
                    i.setTitre(obj.get("titre").toString());
                }
                i.setTime(obj.get("time").toString());
               
                //t.setStart_date( new Date(obj.get("Start_date");
                items.add(i);
                System.out.println("hereeeee"+items.toString());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return items;
    }

    public ArrayList<Item> getAllItems(int t) {
        String url = Statics.BASE_URL_Item + "json/"+t;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                items = parseItem(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return items;
    }
    
}
