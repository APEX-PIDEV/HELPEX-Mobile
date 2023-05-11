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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Tasks;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {

    public ArrayList<Tasks> tasks;

    public static ServiceTask instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
        req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    public boolean addTask(Tasks t) {
        int id =t.getId();
        String titre = t.getTitre();
        boolean is_valid =  t.isIs_valid();
        int valid=0;
        if(is_valid==true){
            valid=1;
        }
        String start_date = t.getStart_date();
        String end_date = t.getEnd_date();
        
       
                        String url = Statics.BASE_URL_Tasks+"json/ajoutTask?titre="+titre+"&is_valid="+valid+"&startdate="+start_date+"&enddate="+end_date;
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
    
    
    
      public boolean modifierTask(Tasks task,String t1) {
         int is_valid=0;
        if( task.isIs_valid()==true){
            
        is_valid=1;
            
        }
        String url = Statics.BASE_URL_Tasks +"/json/EditerTask/"+task.getId()+"?titre="+task.getTitre()+"&is_valid="+is_valid+"&startdate="+task.getStart_date()+"&enddate="+task.getEnd_date();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
        
    }
      
      
          //Delete 
    public boolean deleteTask(int id ) {
        String url = Statics.BASE_URL_Tasks +"json/deleteTask/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
 

    public ArrayList<Tasks> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//tasksListJson is a map of list
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            //list is a list of map
            for (Map<String, Object> obj : list) {
                //t is a task
                
                Tasks t = new Tasks();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setIs_valid((Boolean.valueOf(obj.get("is_valid").toString())));
                if (obj.get("titre") == null) {
                    t.setTitre("null");   //here
                } else {
                    t.setTitre(obj.get("titre").toString());
                }
                t.setStart_date(obj.get("start_date").toString());
                t.setEnd_date(obj.get("end_date").toString().substring(0, 10));
                //t.setStart_date( new Date(obj.get("Start_date");
                tasks.add(t);
                System.out.println("hereeeee"+tasks.toString().substring(0, 10));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasks;
    }

    public ArrayList<Tasks> getAllTasks() {
        String url = Statics.BASE_URL_Tasks + "json/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}
