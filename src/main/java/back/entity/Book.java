package back.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends AbstractEntity {

    @NotBlank
    private String name;

    private String description;

    @OneToMany(mappedBy = "bookId")
    private List<PageBook> filePath;

    @ManyToMany
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id",
                    referencedColumnName = "id"))
    private Set<Genre> genre;

    private String author;

}
