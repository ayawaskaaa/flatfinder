package com.flat.utils;

import com.flat.model.Apartment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class MessageBuilder {
    public static DateFormat MINSK_DATE_FORMAT = createMinskDateFormatter();

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
        builder.append(MINSK_DATE_FORMAT.format(apartment.getLast_time_up()));
        builder.append('\n');

        builder.append("Link - ");
        builder.append(apartment.getUrl());
        builder.append('\n');

        return builder.toString();
    }

    private static DateFormat createMinskDateFormatter()
    {
        DateFormat formatter= new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
        return formatter;
    }
}
