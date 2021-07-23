// --== CS400 File Header Information ==--
// Name: Shreyans Sakhlecha
// Email: sakhlecha@wisc.edu
// Team: LB
// Role: Back end Developer
// TA: Dibyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <This code was written by shreyans sakhlecha.>

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class InventorySystem {

    public static List<String> PartialMatch(Long Barcode, HashTableMap<Long, Product> Table) { // takes partial/ full barcode and returns an array of barcode that match the sequence
        List<String> listString = new ArrayList<>();
        int i = 0;
        String test = (Long.toString(Barcode));// converts the barcode from Long to String
        for (int x = 0; x < Table.getCapacity(); x++) { //traversing through the array
            for (int y = 0; y < Table.arr[x].size(); y++) { //traversing through the linked list
                Entry entry = Table.arr[x].get(y);
                Product prod = (Product) entry.getValue();
                String comp = (Long.toString(prod.getBarcode())); // gets the barcode from the HashTable and converts it to string
                if (comp.contains(test)) { // checks wether the given barcode sequence matches any from the hash table
                    listString.add(comp);    // stores the matches
                    
                }
            }
        }
        return listString; // returns the list.
    }

    public static void displayGet(Product returnedProduct) {
        System.out.println("Barcode:      " + returnedProduct.getBarcode());
        System.out.println("Name:         " + returnedProduct.getName());
        System.out.println("Manufacturer: " + returnedProduct.getManufacturer());
        System.out.println("Type:         " + returnedProduct.getType());
        System.out.println("Price:        " + returnedProduct.getPrice());
    }

    public static void main(String args[]) {
        
        Generation.generateFile();
        HashTableMap<Long, Product> productMap = new HashTableMap<>();
        FrontEndInterface.beginPrompt(productMap);
    }

}
