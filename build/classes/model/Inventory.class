package model;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Katelynn Auer
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    /**
     * @param newPart the part to add into allParts
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    
    /**
     * @param newProduct the product to add into allProducts
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    
    /**
     * @param partId the part's id to lookup
     * @return the filtered parts
     */
    public static Part lookupPart(int partId) {
        for (Part part : getAllParts()) {
            if (partId == part.getId()) {
                MainController.filteredParts.add(part);
            }
        }
        return null;
    }
    
    /**
     * @param productId the product's id to lookup
     * @return the filtered products
     */
    public static Product lookupProduct(int productId) {
        for (Product product : getAllProducts()) {
            if (productId == product.getId()) {
                MainController.filteredProducts.add(product);
            }
        }
        return null;
    }
    
    /**
     * @param partName the part name to lookup
     * @return the filtered parts
     */
    public static ObservableList<Part> lookupPart(String partName) {
        for (Part part : getAllParts()) {
            if (part.getName().contains(partName)) {
                MainController.filteredParts.add(part);
            }
        }
        return null;
    }
    
    /**
     * @param productName the product name to lookup
     * @return the filtered products
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        for (Product product : getAllProducts()) {
            if (product.getName().contains(productName)) {
                MainController.filteredProducts.add(product);
            }
        }
        return null;
    }
    /**
     * @param index the index to update
     * @param selectedPart the updated part
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    
    /**
     * @param index the index to update
     * @param selectedProduct the updated product
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }
    
    /**
     * @param selectedPart the part to delete
     * @return the update allParts
     */
    public static boolean deletePart(Part selectedPart) {
        for (Part part : getAllParts()) {
            if (part == selectedPart) {
                return getAllParts().remove(part);
            }
        }
        return false;
    }
    
    /**
     * @param selectedProduct the product to delete
     * @return the updated allProducts
     */
    public static boolean deleteProduct(Product selectedProduct) {        
        for (Product product : getAllProducts()) {
            if (product == selectedProduct) {
                return getAllProducts().remove(product); 
            } 
        }
        return false;
    }
    
    /**
     * @return allParts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    /**
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }    
}
