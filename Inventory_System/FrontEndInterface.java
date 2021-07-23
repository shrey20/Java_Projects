// --== CS400 File Header Information ==--
// Name: Shreyans Sakhlecha
// Email: sakhlecha@wisc.edu
// Team: LB
// Role: Back end Developer
// TA: Dibyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <This code is written by Rahul Sudhakar>

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FrontEndInterface {
  static Scanner sc = new Scanner(System.in);

  /**
   * The driver method that begins the front end user interface. It inputs a command from the user 
   * and calls the appropriate method through switch statements.
   * 
   * @param inventory
   */
  public static void beginPrompt(HashTableMap<Long, Product> inventory) {
    menu(); // prints the menu options
    Boolean whileBool = true;

    String usrIn = sc.nextLine();

    while (whileBool) {
      switch (usrIn) {
        case "A":
        case "a":
          addProduct(inventory);
          break;
        case "S":
        case "s":
          productStored(inventory);
          break;
        case "L":
        case "l":
          loadFromFile(inventory);
          break;
        case "F":
        case "f":
          findProduct(inventory);
          break;
        case "R":
        case "r":
          removeProduct(inventory);
          break;
        case "M":
        case "m":
          partialMatchKey(inventory);
        case "C":
        case "c":
          menu();
          break;
        case "E":
        case "e":
          whileBool = false;
          break;
        default:
          System.out.println("Please enter a valid command");
          break;
      }

      System.out.println("Proceed(Y/N): ");
      usrIn = sc.next();

      if (usrIn.equals("N") || usrIn.equals("n")) {
        usrIn = sc.nextLine();
        break;
      } else if (usrIn.equals("Y") || usrIn.equals("y")) {
        usrIn = sc.nextLine();
        System.out.println("Enter another command.");
        usrIn = sc.nextLine();
      }
    }
  }

  /**
   * Adds the product to the inventory once the user has inputed its attributes correctly. It
   * invokes the createProduct() method to create and store the product (value) in the inventory.
   * 
   * @param inventory
   */
  private static void addProduct(HashTableMap<Long, Product> inventory) {
    System.out.println("Enter the name, type, manufacturer, and the price of the product to create "
        + "the product to add to the system.");
    System.out.println("Please enter each attribute on a new line.");

    String name = sc.nextLine();
    String type = sc.nextLine();
    String manufacturer = sc.nextLine();
    Double price = 0.0;

    Boolean boolVal = true;

    while (boolVal) {
      try {
        price = sc.nextDouble();
        boolVal = false;
      } catch (Exception e) {
        System.out.println("Please enter the price correctly.");
        sc.nextLine();
      }
    }

    boolVal = true;

    System.out.println("Please enter the 10 Digit Barcode associated with the product.");
    Long barcode = barcodeHelper();
    
    Product userProd = createProduct(name, type, manufacturer, barcode, price);
    if(inventory.put(barcode, userProd)) {
    System.out.println("The product was stored successfully.");
    System.out.println();
    }
    else {
      System.out.println("The product was not stored.");
      System.out.println();
    }
  }

  /**
   * Helper method which creates and returns the product once calling the default constructor of the
   * Product class.
   * 
   * @param name
   * @param type
   * @param manufacturer
   * @param barcode
   * @param price
   * @return Product generated with the attributes as inputed by the user
   * 
   */
  private static Product createProduct(String name, String type, String manufacturer, Long barcode,
      Double price) {

    Product userProd = new Product(name, type, manufacturer, barcode, price);
    return userProd;

  }


  /**
   * Removes the product from the inventory associated with the particular barcode (key) value.
   * Once found it displays the attributes of the product that has to be removed.
   * 
   * @param inventory
   */
  private static void removeProduct(HashTableMap<Long, Product> inventory) {
    System.out.println("Enter the barcode of the product you want to remove");
    Long barcode = barcodeHelper();

    System.out.println("The product trying to be removed is: ");

    try {
      InventorySystem.displayGet((Product) inventory.get(barcode));
    } catch (NoSuchElementException e1) {
      System.out.println(
          "The product you were trying to remove did not exist in the inventory. Please try adding "
          + "the product, or checking if it exists in the inventory first.");
      return;
    }

    if(inventory.remove(barcode) != null) {
    System.out.println("The product was removed successfully.");
    System.out.println();
    }
    else {
      System.out.println("The product you were trying to remove did not exist in the inventory.");
    }
  }

  /**
   * Checks if the product associated with the barcode (key) value inputed in the method exists in
   * the inventory.
   * 
   * @param inventory
   * @return true if product is found in the inventory, else false.
   */
  private static boolean productStored(HashTableMap<Long, Product> inventory) {
    System.out.println("Enter the barcode of the product you want to find in the system.");
    Boolean boolVal = true;
    Long barcode = barcodeHelper();

    if (inventory.containsKey(barcode)) {
      System.out.println("The product is stored in the inventory.");
      return true;
    }
    System.out.println("The product could not be found in the inventory.");
    return false;
  }

  /**
   * Finds the product associated with the barcode (key) value inputed in the method, if it exists 
   * in the inventory. Once found it displays the attributes of the desired product. 
   * 
   * @param inventory
   */
  private static void findProduct(HashTableMap<Long, Product> inventory) {
    System.out.println("Please enter the 10 Digit Barcode associated with the product.");
    Boolean boolVal = true;
    Long barcode = barcodeHelper();

    try {
      InventorySystem.displayGet((Product) inventory.get(barcode));
    } catch (Exception e) {
      System.out.println("The product could not be found in the inventory.");
    }
  }

  /**
   * Loads and adds products from a file into the inventory. It does so by parsing through the file
   * and then sorting the input, and if it is correct it gets added to the inventory.
   * 
   * @param inventory
   */
  public static void loadFromFile(HashTableMap<Long, Product> inventory) {
    File file = new File("src/listProducts.txt");

    try (Scanner sc = new Scanner(file)) {

      if (sc.nextLine().equals("name, type, manufacturer, barcode, price")) {
        while (sc.hasNextLine()) {
          String[] allData = sc.nextLine().split(",");
          String name = allData[0];
          String type = allData[1];
          String manufacturer = allData[2];
          Long barcode = Long.parseLong(allData[3]);
          Double price = Double.parseDouble(allData[4]);
          System.out.println(barcode);
          if (inventory.put(barcode, new Product(name, type, manufacturer, barcode, price))) {
            continue;
          } else {
            System.out.println("Some products were not added the inventory.");
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Partially matches products stored in the inventory to the first and last three values stored
   * in its barcode (key) value. Prints out the set of all products with common first and last 
   * three barcode digits.
   * 
   * @param inventory
   */
  public static void partialMatchKey(HashTableMap<Long, Product> productMap) {
        Scanner scnr = new Scanner(System.in);
        // initializes a long variable
        //int firstThree = 0;
        //int lastThree = 0;
        Long barCode = 0L;
        // initializes a boolean variable happened to false
        boolean happened = false;

        // loop goes until the condition evaluates to false, i.e. when user enter correct numerical
        // value as input
        while (happened == false) {

            // handles the input mismatch exception thrown in case user enters some other data type
            try {

                System.out.println("Enter the required barcode: ");
                barCode = scnr.nextLong();

                List<String> listString = InventorySystem.PartialMatch(barCode, productMap);
                if (listString.size() > 0) {
                    happened = true;
                    for (String s : listString) {
                        System.err.println("Matched: " + s);
                    }
                }
            } // catches exception thrown and displays a message which prompts the user to enter data
            // in correct format
            catch (Exception e) {
                System.out.println("\n******Error in format encountered."
                        + "Please enter a number which is numerical!******\n");
                scnr.nextLine();
            }
        }

    }
  /**
   * Prints the set of commands that can be invoked by the user.
   * 
   */
  private static void menu() {
    System.out.println("---------------------------------------------------------------");
    System.out.println("Welcome to the Inventory Management App.");
    System.out.println("You can use the following commands to manage your inventory.");
    System.out.println("Enter 'A' to add the product into the system.");
    System.out.println("Enter 'S' to check if the product is stored in the system.");
    System.out.println("Enter 'L' to load a file to add products to the inventory." );
    System.out.println("Enter 'F' to find the product.");
    System.out.println("Enter 'R' to remove the product.");
    System.out.println("Enter 'M' to partially match a barcode number.");
    System.out.println("Enter 'E' to exit entering commands");
    System.out.println("Enter 'C' to see the list of commands again.");
    System.out.println("----------------------------------------------------------------");
  }
  /**
   * Helper method that helps the user input a barcode, till it is inputed correctly.
   * Helper method for addProduct(), removeProduct(), productStored(), and findProduct().
   * 
   * @return barcode - once inputed correctly
   */
  private static Long barcodeHelper() {
    Boolean boolVal = true;
    Long barcode = 0L;
    
    while (boolVal) {
      try {
        barcode = sc.nextLong();
        if (barcode.toString().length() == 10) { // ensures correct length of input 
          boolVal = false;
        }
        else {
          System.out.println("Please enter the barcode correctly.");
          continue;
        }
      } catch (Exception e) {
        System.out.println("Please enter the barcode correctly.");
        sc.nextLine();
      }
    }
    
    return barcode;
  }
}