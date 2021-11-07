package exam.objects;

import javafx.collections.ObservableList;

/**
 *
 * @author Jacob Starr
 */
public class Product {
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int max;
    private int min;

    public Product(int id, String name, double price, int stock, int max, int min){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;
    }

    /**
     *
     * @param p part to add to the list
     * @return boolean value of add result
     */
    public boolean addAssociatedPart(Part p){
        try{
            return associatedParts.add(p);
        } catch(Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     *
     * @param p part to add to the list
     * @return boolean value of delete result
     */
    public boolean deleteAssociatedPart(Part p){
        try{
            return associatedParts.remove(p);
        } catch(Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     *
     * @return list of associated parts
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     *
     * @return the id of this object
     */

    public int getId(){
        return this.id;
    }

    /**
     *
     * @return the name of this object
     */
    public String getName(){
        return this.name;
    }

    /**
     *
     * @return the price of this object
     */
    public double getPrice(){
        return this.price;
    }

    /**
     *
     * @return the stock count of this object
     */
    public int getStock(){
        return this.stock;
    }

    /**
     *
     * @return the maximum amount of objects available
     */
    public int getMax(){
        return this.max;
    }

    /**
     *
     * @return the minimum amount of objects available
     */
    public int getMin(){
        return this.min;
    }

    /**
     *
     * @param id the id to set
     */

    public void setId(int id){
        this.id = id;
    }

    /**
     *
     * @param name the name to set
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     *
     * @param price the price to set
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     *
     * @param stock the stock amount to set
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     *
     * @param max the maximum amount of objects
     */
    public void setMax(int max){
        this.max = max;
    }

    /**
     *
     * @param min the minimum amount of objects
     */
    public void setMin(int min){
        this.min = min;
    }
}
