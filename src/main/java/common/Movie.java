package common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "BEYKI_MOVIES")
public class Movie implements Serializable {

    @Id
    private Long id;
    private String title;
    private String genre;
    private Double rate;
    //@Column(name = "WATCHTIME")
    //private Integer watchTime;
    @Column(name = "RELEASE_YEAR")
    private Integer releaseYear;

    public String toString() {

        return "Movie Information : "
                + "title : " +  getTitle() + "\n"
                + "genre : " + getGenre() + "\n"
                + "rate : " + getRate()  + "\n"
                + "release year : " + getReleaseYear();
        }
}
