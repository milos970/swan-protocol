package org.spring.web.vaii.sevice;

import org.spring.web.vaii.DAY_TIME;
import org.spring.web.vaii.entity.image.Media;
import org.spring.web.vaii.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<Media> getMediaForTimeOfDay() {
        LocalDateTime now = LocalDateTime.now();
        boolean isEvening = (now.getHour() >= 18) && (now.getHour() <= 23);
        DAY_TIME expected = isEvening ? DAY_TIME.NIGHT : DAY_TIME.DAY;
        return this.mediaRepository.findAllByDayTime(expected);
    }



}
