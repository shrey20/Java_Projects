
// --== CS400 File Header Information ==--
// Name: Shreyans Sakhlecha
// Email: sakhlecha@wisc.edu
// Team: LB
// Role: Back end Developer
// TA: Dibyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <oThis code is written by Uday Malhotra and Rahul Sudhakar>


import java.util.Scanner;

public class InventoryTester {

  /**
   * This test method checks if a product is being added to the inventory succesfully
   * 
   * @return true if product is added
   */
  public static boolean test1() {
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    String input = "A" + "\n" + "Apple" + "\n" + "Food" + "\n" + "ABC" + "\n" + "4.50" + "\n"
        + "1234567890" + "\n" + "Y" + "\n" + "s" + "\n" + "1234567890" + "\n" + "N" + "\n";
    FrontEndInterface.sc = new Scanner(input);
    FrontEndInterface.beginPrompt(productMap);
    if (!productMap.containsKey((long) 1234567890))
      return false;
    return true;
  }

  /**
   * This method tests whether a product is removed. The test first adds a product, and then
   * proceeds to remove it.
   * 
   * @return true if product is removed succesfully
   */
  public static boolean test2() {
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    String input = "A" + "\n" + "Apple" + "\n" + "Food" + "\n" + "ABC" + "\n" + "4.50" + "\n"
        + "1234567890" + "\n" + "Y" + "\n" + "R" + "\n" + "1234567890" + "\n" + "N" + "\n";
    FrontEndInterface.sc = new Scanner(input);
    FrontEndInterface.beginPrompt(productMap);
    if (productMap.containsKey((long) 1234567890))
      return false;
    return true;
  }

 
  public static boolean test4() {
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    String input = "A" + "\n" + "Apple" + "\n" + "Food" + "\n" + "ABC" + "\n" + "4.50" + "\n"
        + "1234567890" + "\n" + "Y" + "\n" + "F" + "\n" + "1234567890" + "\n" + "N" + "\n";
    FrontEndInterface.sc = new Scanner(input);
    FrontEndInterface.beginPrompt(productMap);
    if (!productMap.containsKey((long) 1234567890))
      return false;
    return true;
  }

  /**
   * This method tries adding a product with the same barcode as a previous product.
   * 
   * Displays appropriate output statement saying product is not added if a unique barcode is not
   * used for add.
   * 
   * @return true
   */
  public static boolean test5() {
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    String input = "A" + "\n" + "Apple" + "\n" + "Food" + "\n" + "ABC" + "\n" + "4.50" + "\n"
        + "1234567890" + "\n" + "Y" + "\n" + "A" + "\n" + "Apple" + "\n" + "Food" + "\n" + "ABC"
        + "\n" + "4.50" + "\n" + "1234567890" + "\n" + "N" + "\n";
    FrontEndInterface.sc = new Scanner(input);
    FrontEndInterface.beginPrompt(productMap);
    return true;
  }

  /**
   * Main method that calls other tests, and displays what the test is about to the user.
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("This is a test to see if product is added.");
    System.out.println();
    System.out.println(test1());
    System.out.println();
    System.out.println("This is a test to see if product is removed succesfully.");
    System.out.println();
    System.out.println(test2());
    System.out.println();
    System.out.println("This is a test to see if partial matches are working.");
    System.out.println();
    
    System.out.println();
    System.out.println("This is a test to see if product finding is working.");
    System.out.println();
    System.out.println(test4());
    System.out.println();
    System.out.println("This is a test to see if you can add a product with the same"
        + "barcode as a previous product.");
    System.out.println();
    System.out.println(test5());
  }
}