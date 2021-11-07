package exam.objects;

import javafx.collections.ObservableList;

import java.util.Objects;

/**
 *
 * @author Jacob Starr
 */
public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    /**
     *
     * @param newPart the newPart to set
     */
    public void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     *
     * @param partId the partId to find
     * @return the Part with matching Id
     */
    public Part lookupPart(int partId){
        return allParts.get(partId);
    }

    /**
     *
     * @param partName the partName to look for
     * @return the Part(s) with matching names
     */
    public ObservableList<Part> lookupPart(String partName){
        return allParts.filtered(p -> Objects.equals(p.getName(), partName));
    }

    /**
     *
     * @return a list of all part
     */
    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     *
     * @param newProduct the product to add to the list
     */
    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     *
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
     *
     * @param selectedPart the part to remove
     * @return boolean result of removal process.
     */
    public boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /**
     *
     * @param productId the productId to search by
     * @return the Product with a matching id
     */
    public Product lookupProduct(int productId){
        return allProducts.get(productId);
    }

    /**
     *
     * @param productName the name of the product to search
     * @return the Product(s) that match the name
     */
    public ObservableList<Product> lookupProduct(String productName){
        return allProducts.filtered(p -> Objects.equals(p.getName(), productName));
    }

    /**
     *
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
     *
     * @param selectedProduct the Product to remove
     * @return boolean result of removal from list
     */
    public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     *
     * @return a list of all products
     */
    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
