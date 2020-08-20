package com.datacenter.model.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.datacenter.model.components.Rack;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataCenter {
    private List<Rack> racks;

    public DataCenter() {
        this.racks = new ArrayList<>();
    }

    public void addRack(Rack rack) {
        this.racks.add(rack);
    }

    public void removeRack(Rack rack) {
        this.racks.remove(rack);
    }

    public void listRacks() {
        System.out.println(Arrays.asList(racks));
    }

}
