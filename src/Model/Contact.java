/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * The Contact class contains the data necessary to give an appointment a Contact.
 * @author KC Green
 */
public class Contact {
    /**
     * The ID of the Contact.
     */
    private int id;
    /**
     * The Name of the Contact.
     */
    private String name;
    /**
     * The Email of the Appointment.
     */
    private String email;
    
    /**
     * Default Constructor. Creates a Contact with no values in its members.
     */
    public Contact(){};
    
    /**
     * Class Constructor. Creates a Contact with the specified values in its members.
     * @param id The ID of the Contact.
     * @param name The Name of the Contact.
     * @param email The Email of the Contact.
     */
    public Contact(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    /**
     * Sets the ID of the Contact.
     * @param id The ID of the Contact.
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * Sets the Name of the Contact.
     * @param name The Name of the Contact.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Sets the Email of the Contact.
     * @param email The Email of the Contact.
     */
    public void setEmail(String email){
        this.email = email;
    }
    
    /**
     * Retrieves the ID of the Contact.
     * @return The ID of the Contact.
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Retrieves the Name of the Contact.
     * @return The Name of the Contact.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Retrieves the Email of the Contact.
     * @return The Email of the Contact.
     */
    public String getEmail(){
        return this.email;
    }
}
