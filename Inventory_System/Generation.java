/// --== CS400 File Header Information ==--
// Name: Shreyans Sakhlecha
// Email: sakhlecha@wisc.edu
// Team: LB
// Role: Back end Developer
// TA: Dibyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <This code is written by Uday Malhotra>
import java.util.Random;
import java.io.FileWriter;

/**
 * This is the class that initially adds random products to the hash table.
 * 
 * It generates and calls the put method to do the same.
 * 
 */
public class Generation {
  private static Random rand;
  private static Product[] products;
  // array of random product names to store initially
  public static String[] names = {"Iphone X", "Ipad Pro", "Macbook Pro", "XPS 13",
      "Noise Cancelling Series 2", "Inspiron", "Macbook Air", "Chips", "Milk", "Yoghurt", "Cheese",
      "Chicken Breast", "Frozen Pizza", "Bagels", "Bread", "Cookies", "Sparkling Water",
      "Energy Drink", "Soda", "Root Beer", "Juice", "Toilet Paper", "Tooth Brush", "Mouthwash",
      "Toothpaste", "Soap", "Shampoo", "Shower Gel", "Dish Soap", "Towels", "Cutlery", "Sanitizer",
      "Laundry Detergent", "Fabric Softener", "Dishwasher", "Refrigerator", "TV", "Sound System",
      "Microwave", "Oven", "Electric Kettle", "Coffee Maker", "Heater", "Humidifier",
      "Gaming Console", "Pen", "Pencil", "Notepad", "Sticky Notes", "Calendar", "Planner",
      "Highlighter", "Sharpener", "Notebook", "Journal", "Diary", "Printer", "Trashcan", "Chair",
      "Tape", "Eraser"};

  // array of random product types to add initially
  public static String[] types =
      {"Personal Gadget", "Food", "Home Supplies", "Home Appliances", "Office Supplies"};

  // array of random product manufacturers to add initially
  public static String[] manufacturers = {"Apple", "Dell", "HP", "Lays", "Ruffles", "Doritos",
      "Chobani", "Lactaid", "Shamrock Farms", "Kraft", "DiGiorno", "Pepperidge Farm", "Klarbrunn",
      "La Croix", "Perrier", "Red Bull", "Monster", "Pepsi", "Coca Cola", "A & W", "Tropicana",
      "Kimberley Clark", "P & G", "Colgate", "Crest", "Oral B", "Old Spice", "Dove", "Cetaphil",
      "Dawn", "Kirkland", "Tide", "Bosch", "LG", "Phillips", "Samsung", "Bose", "Kenmore",
      "Panasonic", "Keurig", "Black & Decker", "Playstation", "Microsoft", "Staedtler",
      "Faber Castell", "KOKUYO", "Canson", "Bic", "Durian", "Wipro"};

  /*
   * This method fills an array of random product, and writes to a file.
   */
  public static void generateFile() {
    rand = new Random();
    products = new Product[3050];
    int j = 0;
    // looping through all possible manufacturers and product names
    for (int i = 0; i < manufacturers.length; i++) {
      for (int m = 0; m < names.length; m++) {
        products[j] = new Product(names[m], types[rand.nextInt(5)], manufacturers[i],
            (long) ((long)(Math.random()*1000000000)+1000000000L), rand.nextFloat() * 1000.0);
        j++;
      }
    }
    // writing to a file
    try {
      FileWriter fw = new FileWriter("src/listProducts.txt");
      // comma separated file
      fw.write("name, type, manufacturer, barcode, price\n");
      for (int i = 0; i < products.length; i++) {
        fw.write(products[i].getName() + "," + products[i].getType() + ","
            + products[i].getManufacturer() + "," + products[i].getBarcode() + ","
            + products[i].getPrice() + "\n");
      }
      fw.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}