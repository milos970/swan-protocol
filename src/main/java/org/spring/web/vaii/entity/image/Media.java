package org.spring.web.vaii.entity.image;

import org.spring.web.vaii.DayTime;

public abstract class Media
{
    private final String name;
    private final String path;
    private final DayTime dayTime;


    public Media(String name, String path, DayTime dayTime) {
        this.name = name;
        this.path = path;
        this.dayTime = dayTime;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}

