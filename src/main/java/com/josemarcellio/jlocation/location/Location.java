package com.josemarcellio.jlocation.location;

import com.josemarcellio.jlocation.common.LocationInfo;

public class Location
        implements LocationInfo {
    private final String city;
    private final String region;
    private final String country;
    private final String isp;
    private final String as;
    private final String asname;
    private final String lat;
    private final String lon;

    public Location(
            String city, String region, String country,
            String isp, String as, String asname,
            String lat, String lon) {
        this.city = city;
        this.region = region;
        this.country = country;
        this.isp = isp;
        this.as = as;
        this.asname = asname;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getRegionName() {
        return region;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getIsp() {
        return isp;
    }

    @Override
    public String getAs() {
        return as;
    }

    @Override
    public String getAsname() {
        return asname;
    }

    @Override
    public String getLat() {
        return lat;
    }

    @Override
    public String getLon() {
        return lon;
    }
}