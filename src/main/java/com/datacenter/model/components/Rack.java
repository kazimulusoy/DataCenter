package com.datacenter.model.components;

import java.util.ArrayList;
import java.util.List;

import com.datacenter.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rack {
    private List<Unit> units;
    private String id;

    public Rack() {
        this.units = new ArrayList<>();
        this.id = UUID.unique();
    }

    public void addUnit(Unit unit) {
        if(unit != null) {
            this.units.add(unit);
        }
    }
}
