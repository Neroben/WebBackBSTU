package back.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "page_books")
public class PageBook extends AbstractEntity {

    @NotBlank private String filePath;
    @NotNull private Integer numPage;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookId;

}
