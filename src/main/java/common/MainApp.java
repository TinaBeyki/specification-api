package common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

import static common.SearchOperation.*;

@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
       SpringApplication.run(MainApp.class, args);
    }

    @Bean
    public CommandLineRunner specificationDemo(final MovieRepository repository) {
        return args -> {
            repository.saveAll(Arrays.asList(
                    new Movie(1L, "Lucifer", "drama, crime",  7.0, 2017),
                    new Movie(2L, "Harry Potter", "imaginary", 7.0, 2000),
                    new Movie(3L, "Caroline", "kids", 6.8, 2011),
                    new Movie(4L, "The Godfather", "crime", 9.2, 1972)
            ));

            System.out.println("***************************  Search movies by genre  ********************************");

            MovieSpecification movieSpecification = new MovieSpecification();
           // movieSpecification.add(new SearchCriteria("genre", "kids", EQUAL));
            List<Movie> movies = repository.findAll(movieSpecification);
            for (Movie movie : movies) {
                System.out.println(movie.toString());
                System.out.println("-------------------");
            }

            System.out.println("***************************  Search movies by title and rate > 6  ********************************");

            movieSpecification.add(new SearchCriteria("title", "Lucifer", EQUAL));
            movieSpecification.add(new SearchCriteria("rate", 6,  SearchOperation.GREATER_THAN));
            List<Movie> movies2 = repository.findAll(movieSpecification);
            for (Movie movie : movies2) {
                System.out.println(movie.toString());
                System.out.println("-------------------");
            }

            System.out.println("***************************  Search movies by releaseYear < 2011 title and rate > 6  ********************************");

            MovieSpecification releaseRate = new MovieSpecification();
            releaseRate.add(new SearchCriteria("releaseYear", "2000", LESS_THAN));
            releaseRate.add(new SearchCriteria("rate", "6", GREATER_THAN));

            //these 2 filters -> and(you can change it in toPredicate return statement

            List<Movie> specificYearAndRate = repository.findAll(releaseRate);
            for (Movie movie : specificYearAndRate) {
                System.out.println(movie.toString());
                System.out.println("-------------------");
            }
        };
    }
}
