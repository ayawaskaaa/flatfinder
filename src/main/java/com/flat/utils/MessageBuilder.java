package com.flat.utils;

import com.flat.model.Apartment;

import java.util.List;

public class MessageBuilder {

    public static String buildApartmentsMessage(List<Apartment> apartments)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- New apartments ---");
        builder.append('\n');
        for(Apartment apartment : apartments)
        {
            builder.append("++++++++++++++++++++++++++++++");
            builder.append('\n');
            builder.append(buildApartmentMessage(apartment));
            builder.append("++++++++++++++++++++++++++++++");
        }
        return builder.toString();
    }

    public static String buildApartmentMessage(Apartment apartment)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Address - ");
        builder.append(apartment.getLocation().getAddress());
        builder.append('\n');

        builder.append("Price - ");
        builder.append(apartment.getPrice().getUsd());
        builder.append('\n');

        builder.append("Last Updated - ");
        builder.append(apartment.getLast_time_up());
        builder.append('\n');

        builder.append("Link - ");
        builder.append(apartment.getUrl());
        builder.append('\n');

        return builder.toString();
    }
}
