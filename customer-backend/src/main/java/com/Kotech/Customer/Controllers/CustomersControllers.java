package com.Kotech.Customer.Controllers;

import com.Kotech.Customer.Entities.City;
import com.Kotech.Customer.Repositories.CityRepository;
import com.Kotech.Customer.Repositories.CustomersRepository;
import com.Kotech.Customer.Entities.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("customer")
public class CustomersControllers {

    private final CustomersRepository customersRepository;
    private final CityRepository cityRepository;

   CustomersControllers(CustomersRepository customersRepository,
                       CityRepository cityRepository){
       this.customersRepository= customersRepository;
      this.cityRepository = cityRepository;

   }
    @GetMapping("/all")
    public List<Customers> getAllCustomers(){
        return customersRepository.findAll();
    }


    @GetMapping("/search/id")
    public Optional<Customers> getPersonById(@RequestParam Long id){ return customersRepository.findById(id);}

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomers(@RequestParam String name){
        customersRepository.findCustomersByFirstName(name.toLowerCase()).ifPresent(test-> customersRepository.delete(test));

        return ResponseEntity.ok("Успешено изтрито!");
    }

    @GetMapping("/page")
    public ResponseEntity<?> paginateCustomer(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                                              @RequestParam(value = "perPage",defaultValue = "5") int perPage,
                                              @RequestParam() String name)
    {
        Pageable pageable = PageRequest.of(currentPage - 1,perPage);
        Page<Customers> people = customersRepository.findPageCustomers(pageable,name.toLowerCase());

        if(people.isEmpty())
        {
            return  new ResponseEntity<>("Няма резултати", HttpStatus.OK);
        }

        Map<String,Object> response = new HashMap<>();
        response.put("customers", people.getContent());
        response.put("currentPage", people.getNumber());
        response.put("totalItems", people.getTotalElements());
        response.put("totalPages", people.getTotalPages());

        return  new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<?> SaveOrUpdate(@RequestParam(required = false)Long id,
                                          @RequestParam(required = false)String firstName,
                                          @RequestParam(required = false)String lastName,
                                          @RequestParam(required = false)Integer num,
                                          @RequestParam(required = false)Long cityId,
                                          @RequestParam(required = false)String cityName)
    {
        boolean isNew =id ==null;


        Customers customers= new Customers(num, firstName, lastName, id);
        if(!cityRepository.existsByName(cityName)){
            cityRepository.save(new City(cityId,cityName));
        }
        customers.setCity(cityId == null? cityRepository.save(new City(cityId,cityName)):cityRepository.findCityById(cityId));

        Customers customer =customersRepository.save(customers);
        Map<String,Object> response = new HashMap<>();
        response.put("generatedId" ,customer.getId());

        if(isNew)
        {
            response.put("message","Успешно добавен");
        }
        else {
            response.put("message","Успешно редактиран");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/search/name")
    public ResponseEntity<?> GetCustomersName(@RequestParam String name){

        if( name ==null||name.isBlank()){
            return ResponseEntity.ok().body("не сте въвели критерий за име!");
        }
        Optional<Customers> result = customersRepository.findCustomersByFirstName(name.toLowerCase());
        return result.isPresent() ? ResponseEntity.ok(result) : ResponseEntity.ok("Въведеното име не е намерено");
    }

}
