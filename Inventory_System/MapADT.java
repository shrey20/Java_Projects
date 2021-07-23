// --== CS400 File Header Information ==--
// Name: Shreyans Sakhlecha
// Email: sakhlecha@wisc.edu
// Team: LB
// Role: Back end Developer
// TA: Dibyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.util.LinkedList;

public interface MapADT<KeyType,ValueType> {
    public boolean put(KeyType key, ValueType value);
    public ValueType get(KeyType key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(KeyType key);
    public ValueType remove(KeyType key);
    public void clear();
}
