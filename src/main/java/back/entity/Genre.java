package back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    private List<Book> books;

}
