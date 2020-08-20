package com.datacenter.model.components;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Switch extends Device{
    private Card[] slots;
    // TODO Slot can be added
    private final int maxCardSlots = 4;
    private int slotCounter = 0;

    public Switch() {
        this.slots = new Card[maxCardSlots];
    }

    public void addCard(Card card) {
        if (this.slotCounter < this.maxCardSlots) {
            this.slots[slotCounter] = card;
            this.slotCounter = this.slotCounter + 1;
        } else {
        	log.info("Reached to max Slot number");
        }
    }

    public void removeCard(Card card) {
    	if(this.slots.length != 0) {
    		for (int index = 0; index < this.maxCardSlots; index++) {
                if (this.slots[index] != null && this.slots[index].equals(card)) {
                    this.slots[index] = null;
                    this.slotCounter = this.slotCounter - 1;
                }
            }
    	}
    }
}
