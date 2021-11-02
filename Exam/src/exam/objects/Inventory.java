package exam.objects;

import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart){
        allParts.add(newPart);
    }

    public Part lookupPart(int partId){
        return allParts.get(partId);
    }

    public ObservableList<Part> lookupPart(String partName){
        return allParts.filtered(p -> p.getName() == partName);
    }

    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public void updatePart(int index, Part selectedPart){
        allParts.remove(index);
        allParts.add(index, selectedPart);
    }

    public boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    public Product lookupProduct(int productId){
        return allProducts.get(productId);
    }

    public ObservableList<Product> lookupProduct(String productName){
        return allProducts.filtered(p -> p.getName() == productName);
    }

    public void updateProduct(int index, Product newProduct){
        allProducts.remove(index);
        allProducts.add(index, newProduct);
    }

    public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
