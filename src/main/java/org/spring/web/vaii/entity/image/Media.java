package org.spring.web.vaii.entity.image;

import org.spring.web.vaii.DAY_TIME;

public abstract class Media
{
    private final String name;
    private final String path;
    private final DAY_TIME dayTime;


    public Media(String name, String path, DAY_TIME dayTime) {
        this.name = name;
        this.path = path;
        this.dayTime = dayTime;
    }

    public DAY_TIME getDayTime() {
        return dayTime;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}

