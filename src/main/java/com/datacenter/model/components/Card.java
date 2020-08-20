package com.datacenter.model.components;

import com.datacenter.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
	private String id;
    private int numberOfPorts;
    
    public Card() {
    	this.id = UUID.unique();
    }
}
