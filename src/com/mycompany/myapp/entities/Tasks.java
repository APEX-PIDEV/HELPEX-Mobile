/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author bhk
 */
public class Tasks {
        private int id ;

 private String titre ;
    private String start_date ,end_date ;
    private  boolean  is_valid ;
    //it has a lit o items

    public Tasks() {
    }

    public Tasks(int id, String titre, String start_date, String end_date, boolean is_valid) {
        this.id = id;
        this.titre = titre;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_valid = is_valid;
    }
    
    public Tasks( String titre, String start_date, String end_date, boolean is_valid) {
        this.titre = titre;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_valid = is_valid;
    }

    public Tasks(String titre, boolean is_valid) {
        this.titre = titre;
        this.is_valid = is_valid;
    }

    @Override
    public String toString() {
                return  "# "+id+ " task : "+titre ;

       // return  "#"+id+ "task"+titre+ "commance le "+start_date+"et cloture le "+end_date ;
       // return "Tasks{" + "id=" + id + ", titre=" + titre + ", start_date=" + start_date + ", end_date=" + end_date + ", is_valid=" + is_valid + '}';
    }
    
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return the start_date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * @return the end_date
     */
    public String getEnd_date() {
        return end_date;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    /**
     * @return the is_valid
     */
    public boolean isIs_valid() {
        return is_valid;
    }

    /**
     * @param is_valid the is_valid to set
     */
    public void setIs_valid(boolean is_valid) {
        this.is_valid = is_valid;
    }
    
    

    
}
