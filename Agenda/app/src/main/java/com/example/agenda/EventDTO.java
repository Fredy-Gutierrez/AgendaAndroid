package com.example.agenda;

import java.io.Serializable;

public class EventDTO implements Serializable {
    private String eventName;
    private String eventType;
    private String eventStart;
    private String eventStartHour;
    private String eventEnd;
    private String eventEndHour;

    public String getEventStartHour() {
        return eventStartHour;
    }

    public void setEventStartHour(String eventStartHour) {
        this.eventStartHour = eventStartHour;
    }

    public String getEventEndHour() {
        return eventEndHour;
    }

    public void setEventEndHour(String eventEndHour) {
        this.eventEndHour = eventEndHour;
    }

    private boolean isAllDayDuration;
    private String eventDescription;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }

    public boolean isAllDayDuration() {
        return isAllDayDuration;
    }

    public void setAllDayDuration(boolean allDayDuration) {
        this.isAllDayDuration = allDayDuration;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
