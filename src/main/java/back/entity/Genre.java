package back.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genre extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id",
                    referencedColumnName = "id"))
    private List<Book> books;

}
