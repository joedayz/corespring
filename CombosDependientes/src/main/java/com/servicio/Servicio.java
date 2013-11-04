package com.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bean.City;


/**
 * 
 * @author josediaz
 *
 */

@Component 
@Qualifier("servicio") 
@Service 
public class Servicio {

	
    public List<City> getEstados(String idcity) 
    { 
        List<City>  cities = new ArrayList<City>(); 
        if(idcity.equalsIgnoreCase("MX")){ 
            cities.add(new City("MORMX","Morelos")); 
            cities.add(new City("CANMX","Cancun")); 
            cities.add(new City("VERMX","Veracruz")); 
        } 
        else if(idcity.equalsIgnoreCase("US")){ 
            cities.add(new City("MS","Masachusets")); 
            cities.add(new City("SF","San Francisco")); 
            cities.add(new City("HT","Houston")); 
        } 
        else if(idcity.equalsIgnoreCase("COL")){ 
            cities.add(new City("BOG","Bogota")); 
            cities.add(new City("MED","Medellin")); 
            cities.add(new City("CTG","Cartagena")); 
        } 
        else if(idcity.equalsIgnoreCase("IND")){ 
            cities.add(new City("YAK","Yakarta")); 
            cities.add(new City("BOY","Bombay")); 
            cities.add(new City("NDL","New Delhi")); 
        } 
        else if(idcity.equalsIgnoreCase("AU")){ 
            cities.add(new City("SYD","Sydney")); 
            cities.add(new City("MEL","Melbourne")); 
            cities.add(new City("AYR","Ayres Rock")); 
        } 
        return cities; 
    }  
}
