package modell;

import module.WorldStatistics;

import java.util.*;

public class Country {

    List<City> cities = new ArrayList<>();
    String name;
    String continent;
    String region;
    Double area;
    int independence;
    Integer population;
    String headOfState;
    String codeOfCountry;
    double populationDestiny;

    public Country() {
    }

    public Country(String[] parts) {
        this(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
    }

    public Country(String code, String name, String continent, String region, String area,
                   String independence, String population, String headOfState) {
        this.cities = WorldStatistics.citiesMap.put(code, null);
        this.name = name.trim();
        this.continent = continent.trim();
        this.region = region.trim();
        this.area = Double.parseDouble(area.trim());
        this.independence = createNumFromNULL(independence.trim());
        this.population = Integer.parseInt(population.trim());
        this.headOfState = headOfState.trim();
        this.codeOfCountry = code;
        this.getPopulationDensity();
        this.getRuralPopulation();
    }

    @Override
    public String toString() {
        return ("\nCountry code: " + this.getCodeOfCountry() + " - name of the country: " + this.getName() +
                "\n\tContinent: " + this.getContinent() + " - name of the region: " + this.getRegion() +
                "\n\tAria: " + this.getArea() + " - independence year: " + this.getIndependence() +
                "\n\tPopulation: " + this.getPopulation() +
                "\n\tHead of State: " + this.getHeadOfState() +
                "\n\tDensity: " + getPopulationDensity() + " person/square meter " +
                "\n\tRural population: " + getRuralPopulation() + " % " +
                "\n\tCities as follows: \n "+ this.getCities());
    }

    private int createNumFromNULL(String independence) {
        if (independence.equals("NULL")) {
            return -1;
        }
        return Integer.parseInt(independence);
    }

    public double getPopulationDensity() {
        if(getPopulation() == 0 || getArea() == 0){
            return -1;
        }else{
            populationDestiny = (double)this.population / this.area;
        }
        return populationDestiny;
    }

    public double getRuralPopulation() {
        if(sumOfCitiesPopulation() != -1)
            return ((sumOfCitiesPopulation() / this.getPopulation()) * 100);
        return -1;
    }

    private double sumOfCitiesPopulation() {
        double sum = 0;
        for (City city : getCities()) {
            sum += city.getPopulation();
        }
        return sum;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public int getIndependence() {
        return independence;
    }

    public void setIndependence(int independence) {
        this.independence = independence;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public String getCodeOfCountry() {
        return codeOfCountry;
    }
}
