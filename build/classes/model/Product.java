package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Product class defines methods and attributes for Products.
 * @author Katelynn Auer
 */
public class Product {
    
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** 
     * Getter Method for ID. Returns the Product ID for a Product instance.
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * Setter Method for ID. Updates the Product ID for a Product instance.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter Method for Name. Returns the Product Name for a Product instance.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter Method for Name. Updates the Product Name for a Product instance.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter Method for Price. Returns the price for a Product instance.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter Method for Price. Updates the Product Price for a Product instance.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter Method for stock. Returns the stock for a Product instance.
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter Method for stock. Updates the Product stock levels for a Product instance.
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter Method for Min. Returns the stock minimum for a Product instance.
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter Method for min. Updates the inventory minimum for a Product instance.
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter Method for ID. Returns the inventory max for a Product instance.
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter Method for stock. Updates the inventory max levels for a Product instance.
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /** Associated a part with a product. The addAssociatedPart method adds a part to a Product object's associated parts.
     * @param part The part to add
     */
    public void addAssociatedPart(Part part) { 
        associatedParts.add(part);        
    }
    
    /** Removes a part association from a product. The deleteAssociatedPart method detaches a part from a Product object.
     * @param selectedAssociatedPart The part to delete
     * @return the updated associatedParts
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return getAllAssociatedParts().remove(selectedAssociatedPart);  
    }
    
    /** Returns associated parts for a product. GetAllAssociatedParts method fetches an array a Product object's associated parts.
     * @return the associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
