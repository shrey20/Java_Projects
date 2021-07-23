public class FlightRunner {
  public static void main(String[] args) {
    LoadFlight fileGen = new LoadFlight();
    fileGen.generateFile();
    System.out.println("Generating files");
    FlightScheduler schedule = new FlightScheduler();
    FrontEnd.beginPrompt(schedule);
    // Airport toAddNext = new Airport("JFK International Airport", 193, "New York");
    // Airport air = new Airport("JFK International Airport", 193, "New York");
    // FlightScheduler fl = new FlightScheduler();
    // fl.addAirport(toAddNext, fl.flightMap);
    // System.out.println(fl.flightMap.vertices.get(air));
  }
}