package com.t5.worldofzuul.Event;

public abstract class Event {
    private String eventDescription;

    public Event(String description) {
        this.eventDescription = description;
    }
}
