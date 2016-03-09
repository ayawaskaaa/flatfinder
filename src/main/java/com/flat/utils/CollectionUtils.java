package com.flat.utils;

import com.flat.model.Apartment;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CollectionUtils {
    public static List<Apartment> getNewApartments(List<Apartment> apartments, List<Long> existingIds)
    {
        List<Apartment> newApartments = new ArrayList<>(apartments);

        for(Long existingId: existingIds)
        {
            ListIterator<Apartment> apartmentListIterator = newApartments.listIterator();
            while (apartmentListIterator.hasNext())
            {
                Apartment apartment = apartmentListIterator.next();
                if(apartment.getId().equals(existingId))
                {
                    apartmentListIterator.remove();
                }
            }
        }

        return newApartments;
    }
}
