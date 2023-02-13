package org.spring.web.vaii.entities.image;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public void save(Image image)
    {
        this.imageRepository.save(image);
    }

    public Image getImage(final long id)
    {
        return this.imageRepository.findById(id).get();
    }

    public List<Image> getAll()
    {
        return (List<Image>) this.imageRepository.findAll();
    }

    public void delete(final long id)
    {
        this.imageRepository.deleteById(id);
    }

    public void deleteAll() {
        this.imageRepository.deleteAll();
    }
}
