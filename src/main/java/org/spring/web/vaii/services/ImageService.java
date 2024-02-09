package org.spring.web.vaii.services;


import org.spring.web.vaii.entities.image.Image;
import org.spring.web.vaii.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public Image getImage(final long id)
    {
        return this.imageRepository.findById(id).get();
    }

    public void delete(final long id)
    {
        this.imageRepository.deleteById(id);
    }

    public void deleteAll() {
        this.imageRepository.deleteAll();
    }
}
