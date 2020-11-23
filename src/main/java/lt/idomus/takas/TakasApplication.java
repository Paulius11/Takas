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
            articleRepository.save(Article.builder().id(1L).title("The Bear Path").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(true).rating(5).difficulty("medium").region("Vilnius park").length(34.3).image("https://cdn-assets.alltrails.com/uploads/photo/image/22928103/large_2af738da41f0100ddc3dc12110e89c2d.jpg").build());
            articleRepository.save(Article.builder().id(1L).title("The Bear Path2").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(true).rating(4).difficulty("easy").region("Vilnius park").length(10.2).image("https://www.pittsburghmagazine.com/content/uploads/2020/03/cb-cook-forest-trail1.jpg").build());
            articleRepository.save(Article.builder().id(1L).title("The Bear Path3").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(true).rating(5).difficulty("medium").region("Vilnius park").length(9.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
        }
                ;
    }
}