package modell;

import module.WorldStatistics;

import java.util.List;

public class City {

    String name;
    String code;
    Integer population;
    double populationPercentage;

    public City() {
    }

    public City(String[] parts) {
        this(parts[0], parts[1], parts[2]);
    }

    public City(String name, String code, String population) {
        this.name = name;
        this.code = code;
        this.population = Integer.parseInt(population);
        this.populationPercentage = getPopulationPercentage();
    }

    @ Override
    public String toString() {
        return ("\nName of city:  " + this.getName() +
                "\n\tCountry code: " + this.getCode() +
                "\n\tPopulation of the city: " + this.getPopulation() +
                "\n\tPopulation percentage (current city / country) -> " + this.getPopulationPercentage() + " %");
    }

    public double getPopulationPercentage() {
        double sumPopulation = sumPopulation(WorldStatistics.countries);
        if (sumPopulation != -1) {
            return ((getPopulation() / sumPopulation) * 100);
        }
        return sumPopulation;
    }

    private double sumPopulation(List<Country> counties) {
        for (Country country : counties) {
            return country.getPopulation();
        }
        return -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

}
