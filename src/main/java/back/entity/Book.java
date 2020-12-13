package back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book extends AbstractEntity {

    @NotBlank
    private String name;

    private String description;

    @Column(name = "path_logo")
    private String pathLogo;

    @OneToMany(mappedBy = "book")
    private List<PageBook> pages;


    @ManyToMany
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id",
                    referencedColumnName = "id"))
    private Set<Genre> genre;

    private String author;

    public Book(Long bookId) {
        this.setId(bookId);
    }

}
