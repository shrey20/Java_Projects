// --== CS400 File Header Information ==--
// Name: Ayushi Msihra
// Email: mishra37@wisc.edu
// Team: LB
// Role: Data Wrangler
// TA: Divyanshu Saxena
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
/**
 * This is the Airport class. Objects of this class will be stored as vertices in the graph
 * @author ayushi mishra
 *
 */
public class Airport {

  private String airportName;
  private long code;
  private String location;

  /**
   * constructor that initializes attributes of the Airport
   * 
   * @param airportName
   * @param code
   * @param location
   */
  public Airport(String airportName, long code, String location) {
    this.airportName = airportName;
    this.code = code;
    this.location = location;
  }

  /**
   * getter for airportName
   * 
   * @return Name of the Airport
   */
  public String getAirportName() {
    return this.airportName;
  }

  /**
   * getter for code
   * 
   * @return unique code number for the airport
   */
  public long getCode() {
    return this.code;
  }

  /**
   * getter for location
   * 
   * @return location of the airport
   */
  public String getLocation() {
    return this.location;
  }
  
  @Override
  public boolean equals(Object compareObj) {
    Airport testObj = (Airport)compareObj;
    if (!this.getAirportName().equals(testObj.getAirportName())) {
      return false;
    }
    Long code = this.getCode();
    if (!code.equals(testObj.getCode())) {
      return false;
    }
    if (!this.getLocation().equals(testObj.getLocation())) {
      return false;
    }
    return true;
  }
  
  @Override
  public String toString() {
    return this.getAirportName() + " with code " + this.getCode() + " in " + this.getLocation();
  }
  
  @Override
  public int hashCode() {
    return this.getAirportName().hashCode();
  }

}
