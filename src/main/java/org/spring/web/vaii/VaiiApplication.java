package org.spring.web.vaii;

import org.spring.web.vaii.entities.image.Image;
import org.spring.web.vaii.entities.image.ImageRepository;
import org.spring.web.vaii.entities.score.Score;
import org.spring.web.vaii.entities.score.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaiiApplication implements CommandLineRunner {
	@Autowired
	ScoreRepository scoreRepository;

	@Autowired
	ImageRepository imageRepository;
	public static void main(String[] args) {

		SpringApplication.run(VaiiApplication.class, args);
		Countdown.getInstance().start();





	}



	@Override
	public void run(String... args) throws Exception
	{
		Score score = new Score();
		this.scoreRepository.save(score);

		Image image1 = new Image();
		image1.setName("day");
		image1.setPath("/files/videos/day.mp4");
		image1.setId(1L);
		this.imageRepository.save(image1);

		Image image2 = new Image();
		image2.setName("night");
		image2.setPath("/files/videos/night.mp4");
		image2.setId(2L);
		this.imageRepository.save(image2);

		Image image3 = new Image();
		image3.setName("mountain");
		image3.setPath("/files/images/1.jpg");
		image3.setId(3L);
		this.imageRepository.save(image3);

		Image image4 = new Image();
		image4.setName("field");
		image4.setPath("/files/images/2.jpg");
		image4.setId(4L);
		this.imageRepository.save(image4);

		Image image5 = new Image();
		image5.setName("sky");
		image5.setPath("/files/images/1-night.jpg");
		image5.setId(5L);
		this.imageRepository.save(image5);

		Image image6 = new Image();
		image6.setName("moon");
		image6.setPath("/files/images/2-night.jpg");
		image6.setId(6L);
		this.imageRepository.save(image6);

		Image image7 = new Image();
		image7.setName("bridge");
		image7.setPath("/files/images/7.jpg");
		image7.setId(7L);
		this.imageRepository.save(image7);

		Image image8 = new Image();
		image8.setName("tree");
		image8.setPath("/files/images/8.jpg");
		image8.setId(8L);
		this.imageRepository.save(image8);

		Image image9 = new Image();
		image9.setName("night-forest");
		image9.setPath("/files/images/7-night.jpg");
		image9.setId(9L);
		this.imageRepository.save(image9);

		Image image10 = new Image();
		image10.setName("night-sky");
		image10.setPath("/files/images/8-night.jpg");
		image10.setId(10L);
		this.imageRepository.save(image10);



	}

}

