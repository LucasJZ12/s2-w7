import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * WildlifeSimulator - Manages population dynamics for multiple species
 * Uses an array of Species objects to store and simulate species data
 */
public class WildlifeSimulator {
    private Species[] species;
    private int speciesCount;
    
    public WildlifeSimulator(int maxSpecies) {
        species= new Species[maxSpecies];
    }
    
    /**
     * Add a species to the simulator
     */
    public void addSpecies(Species s) {
        species[speciesCount++] = s;
    }
    
    /**
     * Simulate one year of population changes for all species
     */
    public void simulateYear() {
        for(int i = 0; i < speciesCount ; i++){
            species[i].simulateYear();
        }
    }
    
    /**
     * Simulate multiple years
     */
    public void simulate(int years) {
        for(int i = 0; i<= years; i++){
            simulateYear();
        }
    }
    
    /**
     * Get species at given index
     */
    public Species getSpecies(int index) {
        if(index>=speciesCount){
            throw new IllegalArgumentException("Out of range");
        }
        return species[index];
    }
    
    /**
     * Get species info as formatted string
     */
    public String getSpeciesInfo(int index) {
        return species[index].toString();
    }

    public String toString(){
        String m = "";
        for(Species s : species){
            if(s != null)
                m += s.toString() + "\n";
        }
        return m;
    }
    
    /**
     * Get total wildlife count across all species
     */
    public double getTotalPopulation() {
        int sum = 0;
        for(int i = 0; i<speciesCount; i++){
            sum += species[i].getPopulation();
        }
        return sum;
    }
    
    /**
     * Find the species with largest population
     */
    public int getMostPopulousIndex() {
        int maxindex = 0;
        for(int i = 0; i<speciesCount;i++){
            if(species[i].getPopulation()>species[maxindex].getPopulation()){
                maxindex = i;
            }
        }
        return maxindex;
    }
    
    /**
     * Find the species with smallest population (most endangered)
     */
    public int getMostEndangeredIndex() {
        int minindex = 0;
        for(int i = 0; i<speciesCount;i++){
            if(species[i].getPopulation()<species[minindex].getPopulation()){
                minindex = i;
            }
        }
        return minindex;
    }
    
    public int getSpeciesCount() {
        return speciesCount;
    }
    
    /**
     * Get array of all species
     */
    public Species[] getSpeciesArray() {
        return species;
    }
}
