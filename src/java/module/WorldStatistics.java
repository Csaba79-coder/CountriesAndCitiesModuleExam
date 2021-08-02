package module;

import modell.City;
import modell.Country;
import util.FileHandler;

import java.util.*;

public class WorldStatistics {

    public static List<Country> countries = new ArrayList<>();
    public static Map<String, List<City>> citiesMap = new HashMap<>();

    public void runFirst() {} {
        FileHandler fileHandler = new FileHandler();
        fileHandler.readCity();
        try {
            fileHandler.readCounty();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bound!");
        }
    }

    public void runSecond() {
        System.out.println();
    }

    public void runThird() {
        // double rural = new Country().getRuralPopulation();
    }

    public void runFourth() {
        Country curr = new Country();
        curr = findCountryByISoCode(countries, "HUN");
        // System.out.println(findCountryByISoCode(countries, "HUN"));
        System.out.println(curr);
    }

    public void runFifth() {
        ArrayList<String> countryCodes = new ArrayList<>();
        countryCodes = getCountriesOfContinent(countries, "Europe");
        // System.out.println(getCountriesOfContinent(countries, "Europe"));
        System.out.println(countryCodes);
    }

    public void runSixth() {
        HashSet<String> getCitiesOfCountry = new HashSet<>();
        getCitiesOfCountry = getCitiesOfCountry(countries, "HUN");
        System.out.println(getCitiesOfCountry);
    }

    public void runSeventh() {
        int nameContains = getAhmedCount(countries);
        System.out.println(nameContains);
    }

    public void runEighth() {
        String getPopularFirstLetter = getPopularFirstLetter(countries);
        System.out.println(getPopularFirstLetter);
    }

    public void runNinth() {
        String independent = lastIndependentCountry(countries);
        System.out.println(independent);
    }

    public void runTenth() {

    }

    public Country findCountryByISoCode(List<Country> countries, String isoCode) {
        for (Country country : countries) {
            if (findCode(countries, isoCode)) {
                return country;
            }
        }
        return null;
    }

    private boolean findCode(List<Country> countries, String isoCode) {
        for (Country country : countries) {
            if (country.getCodeOfCountry().equals(isoCode)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getCountriesOfContinent(List<Country> countries,String continentName) {
        ArrayList<String> tempList = new ArrayList<>();
        for (Country country : countries) {
            if (country.getContinent().equals(continentName)) {
                tempList.add(country.getCodeOfCountry());
            }
        }
        return tempList;
    }

    public HashSet<String> getCitiesOfCountry(List<Country> countries, String countryCode) {
        HashSet<String> namesOfCities = new HashSet<>();
        for (Country country : countries) {
            for (City city : country.getCities()) {
                if (country.getCodeOfCountry().equals(countryCode)) {
                    namesOfCities.add(city.getName());
                }

            }
        }
        return namesOfCities;
    }

    public int getAhmedCount(List<Country> countries) {
        List<String> tempList = new ArrayList<>();
        for (Country country : countries) {
            if (country.getHeadOfState().contains("Hamad") || country.getHeadOfState().contains("Ahmad") ||
                    country.getHeadOfState().contains("Ahmed")) {
                tempList.add(country.getHeadOfState());
            }
        }
        return tempList.size();
    }

    public String getPopularFirstLetter(List<Country> countries) {
        int maxValue = findMaxFirstLetter(countFirstLetter(countries));
        for (List<String> characters: countFirstLetter(countries).values()) {
            if (maxValue == characters.size()) {
                return String.valueOf(characters.get(0).charAt(0));
            }
        }
        return null;
    }

    private Map<String, List<String>> countFirstLetter(List<Country> countries) {
        Map<String, List<String>> firstLetterMap = new HashMap<>();
        for (Country country : countries) {
            if (firstLetterMap.get(String.valueOf(country.getName().charAt(0))) == null){
                firstLetterMap.put(String.valueOf(country.getName().charAt(0)), new ArrayList(Arrays.asList(country.getName())));
            }
            else{
                firstLetterMap.get(String.valueOf(country.getName().charAt(0))).add(country.getName());
            }
        }
        return firstLetterMap;
    }

    private int findMaxFirstLetter(Map<String, List<String>> firstLetterMap){
        int maxValue = 0;
        for (Map.Entry<String, List<String>> entry : firstLetterMap.entrySet()) {
            if (entry.getValue().size() > maxValue) {
                maxValue = entry.getValue().size();
            }
        }
        return maxValue;
    }

    public String lastIndependentCountry(List<Country> countries) {
        int max = findMax();
        for (Country country : countries) {
            if (max == country.getIndependence()) {
                return country.getName();
            }
        }
        return null;
    }

    private int findMax() {
        int max = 0;
        for (Country country : countries) {
            if (country.getIndependence() > max) {
                max = country.getIndependence();
            }
        }
        return max;
    }
}
