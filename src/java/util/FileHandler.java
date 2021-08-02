package util;

import modell.City;
import modell.Country;
import module.WorldStatistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileHandler {

    private static final String RELATIVE_PATH_COUNTRIES = "src/resources/ orszagok.txt";
    private static final String RELATIVE_PATH_CITIES = "src/resources/ varosok.txt";
    private static final String RELATIVE_PATH_TEST_COUNTRY = "src/resources/test_country.txt";
    private static final String RELATIVE_PATH_TEST_CITY = "src/resources/test_cities.txt";

    public void readCity() {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Path.of(RELATIVE_PATH_TEST_CITY), StandardCharsets.UTF_8);

            for (String line; (line = bufferedReader.readLine()) != null; ) {
                City currentCity = new City(line.split(","));
                WorldStatistics.citiesMap.putIfAbsent(currentCity.getCode(), new ArrayList<>());
                WorldStatistics.citiesMap.get(currentCity.getCode()).add(currentCity);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCounty() throws IndexOutOfBoundsException {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Path.of(RELATIVE_PATH_TEST_COUNTRY), StandardCharsets.UTF_8);

            for (String line; (line = bufferedReader.readLine()) != null; ) {
                Country currentCountry = new Country(line.split(","));
                for (String key : WorldStatistics.citiesMap.keySet())
                    if (currentCountry.getCities().contains(key)) {
                        currentCountry.setCities(currentCountry.getCities());
                }
                WorldStatistics.countries.add(currentCountry);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
