// --== CS400 File Header Information ==--
// Name: Shreyans Sakhlecha
// Email: sakhlecha@wisc.edu
// Team: LB
// Role: Back end Developer
// TA: Dibyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <This code was written by Shreyans Sakhlecha>
public class Entry<KeyType, ValueType> {

    int hashcode;
    KeyType key;
    ValueType value;

    public Entry(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
        this.hashcode = key.hashCode();
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

}
