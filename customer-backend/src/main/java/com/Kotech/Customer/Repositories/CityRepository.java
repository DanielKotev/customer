package com.Kotech.Customer.Repositories;

import com.Kotech.Customer.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
    Boolean existsByName(String Name);
    City findCityById(Long cityId);
}
