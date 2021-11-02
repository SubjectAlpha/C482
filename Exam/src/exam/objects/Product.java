package exam.objects;

import javafx.collections.ObservableList;

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

    public boolean addAssociatedPart(Part p){
        try{
            return associatedParts.add(p);
        } catch(Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean deleteAssociatedPart(Part p){
        try{
            int index = associatedParts.indexOf(p);
            associatedParts.remove(index);
            return true;
        } catch(Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getStock(){
        return this.stock;
    }

    public int getMax(){
        return this.max;
    }

    public int getMin(){
        return this.min;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public void setMax(int max){
        this.max = max;
    }

    public void setMin(int min){
        this.min = min;
    }
}
