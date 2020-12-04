package back.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book extends AbstractEntity {

    private String name;

    private String description;

    private String filePath;

    @ManyToMany
    @JoinTable(name = "books_genres", joinColumns = {@JoinColumn(name = "book_fk")},
        inverseJoinColumns = {@JoinColumn(name = "genre_fk")})
    @JoinColumn(name = "genre_id")
    private Set<Genre> genre;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User author;

}
