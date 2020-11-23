package lt.idomus.takas;

import lt.idomus.takas.doa.ArticleRepository;
import lt.idomus.takas.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TakasApplication {

	private static final Logger log = LoggerFactory.getLogger(TakasApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TakasApplication.class, args);
	}

	/**
	 * Main java method for creating projects directly from java code
	 *
	 * @param articleRepository access task data
	 */
	@Bean
	public CommandLineRunner demo(ArticleRepository articleRepository) {
		return (args) -> {
//			TODO: medium to enum
			log.info("Starting Command line runner");
			articleRepository.save(new Article("The Bear Path", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", true, 5, "medium", "Vilnius park",  34.3, "https://cdn-assets.alltrails.com/uploads/photo/image/22928103/large_2af738da41f0100ddc3dc12110e89c2d.jpg"));
			articleRepository.save(new Article("The Bear Path2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", true, 4, "easy", "Vilnius park",  10.2, "https://www.pittsburghmagazine.com/content/uploads/2020/03/cb-cook-forest-trail1.jpg"));
			articleRepository.save(new Article("The Bear Path3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", false, 5, "medium", "Vilnius park",  9.1, "https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg"));
		};
	}
}