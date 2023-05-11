/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Eya
 */
public class Item {
    private int id;
    private String titre ;
    private String time ;
    private boolean is_complete;

    public Item() {
    }

    public Item(int id, String titre, String time, boolean is_complete) {
        this.id = id;
        this.titre = titre;
        this.time = time;
        this.is_complete = is_complete;
    }

    public Item(String titre, String time, boolean is_complete) {
        this.titre = titre;
        this.time = time;
        this.is_complete = is_complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isIs_complete() {
        return is_complete;
    }

    public void setIs_complete(boolean is_complete) {
        this.is_complete = is_complete;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", titre=" + titre + ", time=" + time + ", is_complete=" + is_complete + '}';
    }
   
    
}
