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
import com.mycompany.myapp.entities.Poste;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServicePoste {
     public static ServicePoste instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
     public ArrayList<Poste> centres;

   
    public static ServicePoste getInstance() {
        if(instance == null )
            instance = new ServicePoste();
        return instance ;
    }
    
    
    
    public ServicePoste() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajouter
    
     public void ajoutPoste(Poste t) {
        
  String nom = t.getTitre();
        String adresse = t.getDecription();
        
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URLsalim + "ajouterjson?titre=" /*+ status + "/"*/ +nom+ "&description="+adresse;
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
     
    
       public ArrayList<Poste> parsePostes(String jsonText) {
        try {
            centres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Poste t = new Poste();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
              //  t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                 t.setTitre(obj.get("titre").toString());
                 t.setDecription(obj.get("description").toString());
              /*  if (obj.get("name") == null) {
                    t.setName("null");
                } else {
                    t.setName(obj.get("name").toString());
                }*/
                centres.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return centres;
    }
  
    public ArrayList<Poste> affichagePostes() {
        String url = Statics.BASE_URLsalim + "afficherjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                centres = parsePostes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return centres;
    }

      
      
      
      
      
      
      public Poste DetailPoste( int id , Poste t) {
        
        String url = Statics.BASE_URLsalim+"/centrejson/"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
               
                 t.setTitre(obj.get("titre").toString());
                 t.setDecription(obj.get("description").toString());

                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return t;
        
        
    }
      
      
      public boolean deletePoste(int id ) {
        String url = Statics.BASE_URLsalim +"deletepostejson/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
      
     //Update 
    public boolean modifierPoste(Poste centre) {
        String url = Statics.BASE_URLsalim + "updatepostejson/"+centre.getId()+ "?titre=" + centre.getTitre() + "&description=" + centre.getDecription();
        
//String url = Statics.BASE_URL +"/updateReclamation?id="+reclamation.getId()+"&objet="+reclamation.getObjet()+"&description="+reclamation.getDescription()+"&etat="+reclamation.getEtat();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
}
