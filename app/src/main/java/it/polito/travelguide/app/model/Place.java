package it.polito.travelguide.app.model;

import java.util.ArrayList;

/**
 * Created by jessica on 30/05/14.
 */
public class Place {
    private String name;
    private String description;
    private String imagePath;
    private String category;
    private String lat;
    private String lg;
    private ArrayList<String> pictures;
    
    public Place() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public void setPictures(ArrayList<String> pictures){
    	this.pictures = pictures;
    }
    
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getCategory() {
        return this.category;
    }

    public String getLat() {
        return this.lat;
    }

    public String getLg() {
        return this.lg;
    }
    
    public ArrayList<String> getPictures(){
    	return this.pictures;
    }
    
}
