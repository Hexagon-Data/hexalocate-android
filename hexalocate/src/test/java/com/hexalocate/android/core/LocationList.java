package com.hexalocate.android.core;

import java.util.ArrayList;
import java.util.List;

final class LocationList implements LocationDataSource {

    List<HexaLocateLocation> locations;

    LocationList() {
        this.locations = new ArrayList<>();
    }

    @Override
    public void addAll(List<HexaLocateLocation> locationList) {
        this.locations.addAll(locationList);
    }

    @Override
    public void add(HexaLocateLocation location) {
        this.locations.add(location);
    }

    @Override
    public List<HexaLocateLocation> popAll() {
        List<HexaLocateLocation> locations = this.locations;
        this.locations.clear();
        return locations;
    }

    @Override
    public long size() {
        return this.locations.size();
    }

    @Override
    public void close() {

    }
}
