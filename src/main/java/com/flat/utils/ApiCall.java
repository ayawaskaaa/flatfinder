package com.flat.utils;

import com.flat.model.Apartment;
import com.flat.model.ApartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ApiCall
{
    @Autowired
    RestTemplate restTemplate;

    @Value("${rent.onliner.api}")
    String apiUrl;

    public List<Apartment> doCall()
    {
        ResponseEntity<ApartmentResponse> response = restTemplate.getForEntity(apiUrl, ApartmentResponse.class);
        return response.getBody().getApartments();
    }
}
