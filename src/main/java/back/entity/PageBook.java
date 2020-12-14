package back.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "page_books")
public class PageBook extends AbstractEntity {

    @NotBlank private String text;
    @NotNull private Long numPage;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
