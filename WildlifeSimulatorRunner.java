import java.io.*;
import java.util.*;

/**
 * WildlifeSimulatorApp - Main program for the Wildlife Population Simulator
 * 
 * This program:
 * 1. Reads real species data from a CSV file using split()
 * 2. Creates Species objects from the data
 * 3. Simulates population changes over time
 * 4. Writes results to an output CSV file
 */
public class WildlifeSimulatorRunner {
    
    public static void main(String[] args) throws IOException{
        // Create simulator with capacity for 20 species
        
        WildlifeSimulator simulator = new WildlifeSimulator(20);
        // Load species data from CSV
        File f = new File("species-data.csv");
        Scanner s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] temp = line.split(",");
            Species species = new Species(temp[0], Long.parseLong(temp[1]), Double.parseDouble(temp[2]), Double.parseDouble(temp[3]), Long.parseLong(temp[4]), temp[5]);
            simulator.addSpecies(species);
        }
        s.close();
        
        
        System.out.println("\n========================================");
        System.out.println("WILDLIFE POPULATION SIMULATOR");
        System.out.println("========================================\n");
        
        
        // Display initial populations
        System.out.println("INITIAL POPULATIONS:");
        System.out.println("------------------");
        System.out.println(simulator);
        
        // Run simulation for 10 years and record data year-by-year
        int simulationYears = 10;
        simulateAndRecordData(simulator, simulationYears, "simulation-results.txt");
        
        System.out.println("FINAL POPULATIONS:");
        System.out.println("------------------");
        // Display final populations
        System.out.println(simulator);
        
        // Display statistics
        System.out.println("\n\nSIMULATION STATISTICS:");
        System.out.println("------------------");
        int mostPopulous = simulator.getMostPopulousIndex();
        int mostEndangered = simulator.getMostEndangeredIndex();
        System.out.println("Most populous: " + simulator.getSpecies(mostPopulous) + " Most endangered: " + simulator.getSpecies(mostEndangered));
        System.out.println("Species count: "+ simulator.getSpeciesCount());
        
        System.out.println("\nSimulation complete!");
        
    }
    
    /**
     * Simulate year-by-year and record data to CSV file
     * Writes: simulation_year,species,population,population_change
     */
    private static void simulateAndRecordData(WildlifeSimulator simulator, int years, String filename) {
        // Clear file first (so old runs don’t stack)
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        writer.println("year,species,population"); // header
    } catch (IOException e) {
        System.err.println("Error creating file");
    }

    // Simulate year-by-year
    for (int year = 1; year <= years; year++) {

        simulator.simulate(1);   // simulate ONE year

        simulator.writeYear(year);  // record that year
    }
    
    }    
    
}