/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 * A class for storing the results of mySQL COUNT and GROUP BY queries.
 * @author KC Green
 */
public class GroupCount {
    /**
     * The value that is being counted.
     */
    private String value;
    /**
     * The number of instances of the value.
     */
    private int count;
    
    /**
     * Creates a GroupCount object with no data.
     */
    public GroupCount(){}
    
    /**
     * Creates a GroupCount object with the specified value and count.
     * @param value The value on which the results were Grouped and Counted by.
     * @param count The Count of each value.
     */
    public GroupCount(String value, int count){
        this.value = value;
        this.count = count;
    }
    
    /**
     * Sets the value of the GroupCount object.
     * @param value The value on which the results were Grouped and Counted by.
     */
    public void setValue(String value){
        this.value = value;
    }
    
    /**
     * Sets the count of the GroupCount object
     * @param count The Count of each value.
     */
    public void setCount(int count){
        this.count = count;
    }
    
    /**
     * Retrieves the value of the GroupCount object.
     * @return The value of the GroupCount object.
     */
    public String getValue(){
        return this.value;
    }
    
    /**
     * Retrieves the count of the GroupCount object.
     * @return The count of the GroupCount object.
     */
    public int getCount(){
        return this.count;
    }
}
