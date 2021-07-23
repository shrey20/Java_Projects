// --== CS400 File Header Information ==--
// Name: Shreyans Sakhlecha
// Email: sakhlecha@wisc.edu
// Team: LB
// Role: Back end Developer
// TA: Dibyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <This code was written by Shreyans Sakhlecha>

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

    public int size, capacity, growth = 0; // initializes the size, capacity and threshold (growth) of a new hashtable to 0
    private double max_load_factor;
    public LinkedList<Entry> arr[]; // intializes a new Linked list with variable name array

    private double load_factor = 0.80; //sets default load factor to 0.80
    private int def_capacity = 10; // sets default capacity to 10

    public HashTableMap(int capacity) { // constructor for HashTableMap (user defined capacity)
        this.capacity = capacity;
        this.max_load_factor = load_factor;
        arr = new LinkedList[this.capacity];
        for (int i = 0; i < arr.length; i++) // intializes a linked list for each array index 
        {
            arr[i] = new LinkedList<Entry>();
        }
        growth = (int) (this.capacity * max_load_factor);
    }

    public HashTableMap() { // constructor for hashtable with predefined capacity 
        max_load_factor = load_factor;
        capacity = def_capacity;
        arr = new LinkedList[this.capacity]; 
        for (int i = 0; i < arr.length; i++) 
        {
            arr[i] = new LinkedList<Entry>();
        }
    }

    @Override
    public int size() { // returns the number of key-value pair stored in the hash table
        return size;

    }
    
    public int getCapacity(){
        return capacity;
    }
    @Override
    public void clear() { // clears the whole hash table map.
        for (int i = 0; i < capacity; i++) {
            arr[i].clear();
            size = 0;
        }
    }

    private int absolute(int hash_key) { //method to calculate the hash code and keep it positive and within capacity.
        return (Math.abs(hash_key)) % capacity;
    }

    public ValueType get(KeyType key) throws NoSuchElementException { // method takes the key as a parameter and returns value if found. 
        // else throws NoSuchElementException
        int dex = absolute(key.hashCode());//calls absolute method to calculate index
        Entry<KeyType, ValueType> test;
        if (arr[dex] == null) {
            throw new NoSuchElementException();  // throw exception if the key refers to a null value
        } else {
            for (int i = 0; i < arr[dex].size(); i++) { // loop for traversing though the linked list at certain index
                test = this.arr[dex].get(i); // stores the key- value pair stored in linked list
                if (test.key.equals(key)) { // compares the key
                    return test.value; // returns the value if key matches
                }
            }
        }
        throw new NoSuchElementException(); // throws an exception if the key doesn't match
    }

    public boolean containsKey(KeyType key) { // method to check wether a key-value pair exists in the hashtable or not.
        KeyType Test;
        for(int x =0; x<this.capacity; x++){
         for (int y =0; y<arr[x].size(); y++){
             Test = (KeyType)(arr[x].get(y)).key;
             if(Test.equals(key)){
                 return true;
             }
         }   
        }
        return false;
    }

    public boolean put(KeyType key, ValueType value) { //method to insert key-value pairs in the table, returns false if the key already exists.
        Entry<KeyType, ValueType> test;
        for (int i = 0; i < capacity; i++) { // loop for going through the array.
            for (int j = 0; j < arr[i].size(); j++) { // loop for traversing the linked list.    
                test = this.arr[i].get(j); //storing the key in test for comparision.
                if (test.key.equals(key)) { // comparing the keys.
                    return false; // returns false if key already exists
                }
            }
        }
        int dex = absolute(key.hashCode()); //generates hash code for the key
        arr[dex].add(new Entry(key, value));
        size++;// stores the key at the calculated index.
        grow(); // checks if the the insersion is below threshhold capacity and calls grow function if true.
        return true; // returns true on successfull insertion.
    }

    private void grow() { // method to resize the array on crossing the threshhold value.

        if (((float) size / (float) (capacity)) >= 0.8) 
        {

            Entry test[] = new Entry[capacity]; 
            int counter = 0; 

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].size(); j++) {
                    if (arr[i].get(j) != null) {
                        test[counter++] = (new Entry(arr[i].get(j).key, arr[i].get(j).value)); 
                    }

                }
            }

            clear(); 
            capacity = capacity * 2;
            arr = new LinkedList[capacity]; 
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new LinkedList<Entry>();
            }

            for (int i = 0; i < counter; i++) {
                put((KeyType) test[i].key, (ValueType) test[i].value); 

            }

        }
    }

    

    public ValueType remove(KeyType key) { // method for removing a key-value pair, returns the value removed.
        int dex = absolute(key.hashCode()); // calculates the hash code.
        Entry<KeyType, ValueType> test;
        if (arr[dex] == null) {
            return null; //returns null if there is no element on the given index. 
        } else {
            for (int i = 0; i < arr[dex].size(); i++) { // goes through the linked list.
                test = this.arr[dex].get(i);
                if (test.key.equals(key)) { //compres the keys
                    ValueType v = test.value;
                    arr[dex].remove(i); // removes the key-value pair if they match
                    size--; // reduces the size
                    return v; //returns the value
                }
            }
        }
        return null;
    }

}
