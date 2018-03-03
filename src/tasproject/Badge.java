/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasproject;

/**
 *
 * @author Dallen
 */

public class Badge {
    
    //Instance Fields
    private String id;
    private String description;
    
    //Empty Constructor for TASDatabase
    public Badge(){}
    
    //Constructor
    public Badge(String id, String description){
        this.id = id;
        this.description = description;
    }
    
    public String getID(){
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    //Formatting the output to look like the following: #D2CC71D4 (Lawson, Matthew J)
    public String toString(){
        return "#" + this.getID() + " " + "(" + this.getDescription() + ")";
    }
}
