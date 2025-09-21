package org.spring.web.vaii.sevice;

import org.spring.web.vaii.DayTime;
import org.spring.web.vaii.entity.image.Media;
import org.spring.web.vaii.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<Media> getMediaForTimeOfDay() {
        LocalDateTime now = LocalDateTime.now();
        boolean isEvening = (now.getHour() >= 18) && (now.getHour() <= 23);
        DayTime expected = isEvening ? DayTime.NIGHT : DayTime.DAY;
        return null;
    }



}
