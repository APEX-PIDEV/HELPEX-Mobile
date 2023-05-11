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
import com.mycompany.myapp.entities.Centre;
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
public class ServiceCentre {
     public static ServiceCentre instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
     public ArrayList<Centre> centres;

   
    public static ServiceCentre getInstance() {
        if(instance == null )
            instance = new ServiceCentre();
        return instance ;
    }
    
    
    
    public ServiceCentre() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajouter
    
     public void ajoutReclamation(Centre t) {
        
  String nom = t.getNomCentre();
        String adresse = t.getAdresseCentre();
        String email =t.getEmailCentre();
        int telephone=t.getTelephoneCentre();
        String siteweb =t.getSiteWebCentre();
        
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/centre/ajouterjson?nomCentre=" /*+ status + "/"*/ + nom + "&adresseCentre=" + adresse + "&emailCentre=" + email + "&telephoneCentre=" + telephone + "&siteWebCentre=" + siteweb;
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
     
     //affichage hedha mtaa chadi ma habech yekhdem aawadhtou bil les deux fonctions eli tahtou
     
      public ArrayList<Centre> affichageCentresxx() {
        ArrayList<Centre> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/centre/allcentress";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Centre t = new Centre();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
              //  t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                                t.setTelephoneCentre(((int) Float.parseFloat(obj.get("telephoneCentre").toString())));

                 t.setNomCentre(obj.get("nomCentre").toString());
                 t.setAdresseCentre(obj.get("adresseCentre").toString());
                 t.setEmailCentre(obj.get("emailCentre").toString());
                 t.setSiteWebCentre(obj.get("siteWebCentre").toString());
                        
                        //Date 
                       // String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                       // Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                       // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                       // String dateString = formatter.format(currentTime);
                       // t.setDate(dateString);
                        
                        //insert data into ArrayList result
                        result.add(t);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
       public ArrayList<Centre> parseCentres(String jsonText) {
        try {
            centres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Centre t = new Centre();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
              //  t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                                t.setTelephoneCentre(((int) Float.parseFloat(obj.get("telephoneCentre").toString())));

                 t.setNomCentre(obj.get("nomCentre").toString());
                 t.setAdresseCentre(obj.get("adresseCentre").toString());
                 t.setEmailCentre(obj.get("emailCentre").toString());
                 t.setSiteWebCentre(obj.get("siteWebCentre").toString());

                 
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

    public ArrayList<Centre> affichageCentres() {
        String url = Statics.BASE_URL + "/centre/allcentress";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                centres = parseCentres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return centres;
    }
      
      
      
      
      
      
      
      
      public Centre DetailRecalamation( int id , Centre t) {
        
        String url = Statics.BASE_URL+"/centrejson/"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
               
                 t.setTelephoneCentre(((int) Float.parseFloat(obj.get("telephoneCentre").toString())));
                 t.setNomCentre(obj.get("nomCentre").toString());
                 t.setAdresseCentre(obj.get("adresseCentre").toString());
                 t.setEmailCentre(obj.get("emailCentre").toString());
                 t.setSiteWebCentre(obj.get("siteWebCentre").toString());
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return t;
        
        
    }
      
      
      public boolean deleteCentre(int id ) {
        String url = Statics.BASE_URL +"/centre/deletecentrejson/"+id;
        
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
    public boolean modifierCentre(Centre centre) {
        String url = Statics.BASE_URL + "/centre/updatecentrejson/"+centre.getId()+ "?nomCentre=" + centre.getNomCentre() + "&adresseCentre=" + centre.getAdresseCentre() + "&emailCentre=" + centre.getEmailCentre() + "&telephoneCentre=" + centre.getTelephoneCentre() + "&siteWebCentre=" + centre.getSiteWebCentre();
        
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
