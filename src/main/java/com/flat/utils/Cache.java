package com.flat.utils;

import com.flat.model.Apartment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cache
{
    private List<Apartment> apartments = new ArrayList<>();

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public boolean isInitialized()
    {
        return !apartments.isEmpty();
    }

}
