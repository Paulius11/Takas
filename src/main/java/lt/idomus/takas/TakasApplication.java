package lt.idomus.takas;

import lombok.extern.slf4j.Slf4j;
import lt.idomus.takas.doa.ArticleRepository;
import lt.idomus.takas.doa.RoleRepository;
import lt.idomus.takas.doa.UserRepository;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.model.Article;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.Role;
import lt.idomus.takas.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
@Slf4j
public class TakasApplication {


    public static void main(String[] args) {
        SpringApplication.run(TakasApplication.class, args);
    }

    /**
     * Main java method for creating projects directly from java code
     *
     * @param articleRepository access task data
     */
    @Bean
    public CommandLineRunner demo(ArticleRepository articleRepository, RoleRepository roleRepo, UserRepository userRepo) {
        return (args) ->
        {
            log.info("Starting Command line runner");
            articleRepository.save(Article.builder().title("The Bear Path").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(true).rating(5).difficulty("easy").region("Vilnius park").length(34.3).image("https://cdn-assets.alltrails.com/uploads/photo/image/22928103/large_2af738da41f0100ddc3dc12110e89c2d.jpg").build());
            articleRepository.save(Article.builder().title("The Greatest Road").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(true).rating(4).difficulty("easy").region("Vilnius park").length(10.2).image("https://www.pittsburghmagazine.com/content/uploads/2020/03/cb-cook-forest-trail1.jpg").build());
            articleRepository.save(Article.builder().title("The Dark Mist").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(true).rating(5).difficulty("medium").region("Vilnius park").length(1.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
            articleRepository.save(Article.builder().title("Wildwood Trail").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(false).rating(5).difficulty("medium").region("Kaunas park").length(9.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
            articleRepository.save(Article.builder().title("Forest Park").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(true).rating(5).difficulty("hard").region("Vilnius park").length(6.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
            articleRepository.save(Article.builder().title("Sunny Way").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(false).rating(1).difficulty("medium").region("Kaunas park").length(8.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
            articleRepository.save(Article.builder().title("Sandy").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(true).rating(1).difficulty("medium").region("Vilnius park").length(1.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
            articleRepository.save(Article.builder().title("Wildwood").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(false).rating(5).difficulty("medium").region("Vilnius park").length(30.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
            articleRepository.save(Article.builder().title("Wild Cherry").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(false).rating(3).difficulty("hard").region("Vilnius park").length(91.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
            articleRepository.save(Article.builder().title("Dogwood").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(false).rating(5).difficulty("medium").region("Vilnius park").length(19.1).image("https://www.hikespeak.com/img/Oregon/Forest_Park/Wildwood_Trail_Forest_Park_Portland_hike_5405.jpg").build());
            articleRepository.save(Article.builder().title("Something Cool").description("Lorem ipsum dolor sit amet, consectetur adipiscing elit").featured(false).rating(2).difficulty("easy").region("Klaipėda park").length(6.1).image("https://images.unsplash.com/photo-1595514807053-2c594370091a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80").build());


            Role userRole = new Role();
            userRole.setRole("USER");
            roleRepo.save(userRole);

            Role adminRole = new Role();
            adminRole.setRole("ADMIN");
            roleRepo.save(adminRole);

            ArticleUser adminUser = new ArticleUser();
            adminUser.setFullName("admin");
            adminUser.setUsername("admin@admin.com");
            adminUser.setPassword("admin123");
            adminUser.setRoles(Set.of(userRole, adminRole));

            userRepo.save(adminUser);

        };
    }


};
