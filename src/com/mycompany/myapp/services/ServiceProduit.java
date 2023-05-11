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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceProduit {
       public static ServiceProduit instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
     public ArrayList<Produit> produits;

   
    public static ServiceProduit getInstance() {
        if(instance == null )
            instance = new ServiceProduit();
        return instance ;
    }
    
    
    
    public ServiceProduit() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajouter
    
     public void ajoutReclamation(Produit t) {
        
  String NomProduit = t.getNomProduit();
        String EtatProduit = t.getEtatProduit();
                int PrixProduit=t.getPrixProduit();

        String DescriptionProduit =t.getDescriptionProduit();
        String localisationProduit =t.getLocalisationProduit();
        String Brand =t.getBrand();
        
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URLahmed + "/produits/ajouterjson?NomProduit=" /*+ status + "/"*/ + NomProduit + "&EtatProduit=" + EtatProduit + "&DescriptionProduit=" + DescriptionProduit + "&localisationProduit=" + localisationProduit + "&Brand=" + Brand+ "&PrixProduit=" +PrixProduit;
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
     
     //affichage hedha mtaa chadi ma habech yekhdem aawadhtou bil les deux fonctions eli tahtou
     /*
      public ArrayList<Produit> affichageCentresxx() {
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
        
        
    }*/
       public ArrayList<Produit> parseProduits(String jsonText) {
        try {
            produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Produit t = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
              //  t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                                t.setPrixProduit(((int) Float.parseFloat(obj.get("PrixProduit").toString())));

                 t.setNomProduit(obj.get("NomProduit").toString());
                 t.setDescriptionProduit(obj.get("DescriptionProduit").toString());
                 t.setEtatProduit(obj.get("EtatProduit").toString());
                 t.setBrand(obj.get("Brand").toString());
                                  t.setLocalisationProduit(obj.get("localisationProduit").toString());



                 
              /*  if (obj.get("name") == null) {
                    t.setName("null");
                } else {
                    t.setName(obj.get("name").toString());
                }*/
                produits.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return produits;
    }

    public ArrayList<Produit> affichageProduits() {
        String url = Statics.BASE_URLahmed + "/produits/allproduits";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
      
      
      
      
      
      
      
      /*
      public Produit DetailRecalamation( int id , Produit t) {
        
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
      */
      
      public boolean deleteProduit(int id ) {
        String url = Statics.BASE_URLahmed +"/produits/deleteproduitjson/"+id;
        
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
    public boolean modifierProduit(Produit produit) {
        String url = Statics.BASE_URLahmed + "/produits/updateprduitjson/"+produit.getId()+ "?NomProduit=" + produit.getNomProduit() + "&EtatProduit=" + produit.getEtatProduit() + "&DescriptionProduit=" + produit.getDescriptionProduit() + "&localisationProduit=" + produit.getLocalisationProduit() + "&Brand=" + produit.getBrand()+ "&PrixProduit=" +produit.getPrixProduit();
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
