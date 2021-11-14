package exam.objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Jacob Starr
 */
public class Inventory {
    private final ObservableList<Part> allParts = FXCollections.observableList(new ArrayList<>());
    private final ObservableList<Product> allProducts = FXCollections.observableList(new ArrayList<>());

    /**
     * Adds a new part to the list.
     * @param newPart the newPart to set
     */
    public void addPart(Part newPart){
        int lastId = allParts.size() > 0 ? allParts.get(allParts.size() - 1).getId() : 0;
        newPart.setId(lastId + 1);
        allParts.add(newPart);
    }

    /**
     * Returns a part matching the requested ID or null.
     * @param partId the partId to find
     * @return the Part with matching Id
     */
    public Part lookupPart(int partId){

        try{
            return allParts.get(partId - 1);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return a list of parts with matching names.
     * @param partName the partName to look for
     * @return the Part(s) with matching names
     */
    public ObservableList<Part> lookupPart(String partName){
        return allParts.filtered(p -> Objects.equals(p.getName(), partName));
    }

    /**
     * Returns a list of all parts.
     * @return a list of all parts.
     */
    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Remove the part at the selected index, then insert the updated part.
     * @param index the index of the list to update
     * @param selectedPart the data to update the part
     */
    public void updatePart(int index, Part selectedPart){
        try{
            allParts.remove(index);
            allParts.add(index, selectedPart);
        }catch(Exception e){
            System.out.println("An error occurred");
        }

    }

    /**
     * Remove selected part from the list of parts.
     * @param selectedPart the part to remove
     * @return boolean result of removal process.
     */
    public boolean deletePart(Part selectedPart){
        try{
            return allParts.remove(selectedPart);
        } catch (Exception e){
            return false;
        }

    }

    /**
     * Generate an ID and insert the new product.
     * @param newProduct the product to add to the list
     */
    public void addProduct(Product newProduct){
        int lastId = allProducts.size() > 0 ? allProducts.get(allProducts.size() - 1).getId() : 0;
        newProduct.setId(lastId + 1);
        allProducts.add(newProduct);
    }

    /**
     * Returns the product that matches the id supplied, or null.
     * @param productId the productId to search by
     * @return the Product with a matching id
     */
    public Product lookupProduct(int productId){
        try{
            return allProducts.get(productId);
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Returns a list of products that match the searched string.
     * @param productName the name of the product to search
     * @return the Product(s) that match the name
     */
    public ObservableList<Product> lookupProduct(String productName){
        return allProducts.filtered(p -> Objects.equals(p.getName(), productName));
    }

    /**
     * Remove product at the index, and insert the updated one at the same index.
     * @param index the index to search by
     * @param newProduct the product to replace
     */
    public void updateProduct(int index, Product newProduct){
        try {
            allProducts.remove(index);
            allProducts.add(index, newProduct);
        } catch(Exception e){
            System.out.println("An error occurred.");
        }

    }

    /**
     * Remove a product from the list.
     * @param selectedProduct the Product to remove
     * @return boolean result of removal from list
     */
    public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     * Get a list of all products.
     * @return a list of all products
     */
    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
