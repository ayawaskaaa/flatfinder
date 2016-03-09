package com.flat.utils;

import com.flat.model.Apartment;
import com.google.appengine.api.datastore.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class Store
{
    public static final String KIND = "Apartments";
    public static final String LAST_UPDATED_FIELD_NAME = "lastUpdated";

    @Value("${apartment.number}")
    int numberOfRecords;

    private Key key = KeyFactory.createKey(KIND, "apartments");


    public void save(List<Apartment> apartments)
    {
        Assert.notNull(apartments);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        for(Apartment apartment : apartments)
        {
            datastore.put(createDatastoreEntity(key, apartment));
        }
    }

    public List<Long> getLastApartmentsIds()
    {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(KIND).addSort(LAST_UPDATED_FIELD_NAME, Query.SortDirection.DESCENDING);
        List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(numberOfRecords));
        return mapToIds(entities);
    }

    private List<Long> mapToIds(List<Entity> entities)
    {
        ArrayList<Long> result = new ArrayList();
        for(Entity entity : entities)
        {
            result.add(entity.getKey().getId());
        }
        return result;
    }

    private Entity createDatastoreEntity(Key key, Apartment apartment)
    {
        Entity entity = new Entity(KIND, apartment.getId(), key);
        entity.setProperty(LAST_UPDATED_FIELD_NAME, apartment.getLast_time_up());
        entity.setProperty("url", apartment.getUrl());
        return entity;
    }
}
