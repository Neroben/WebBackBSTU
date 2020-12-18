package back.dto.book;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class BookDto {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private String author;

    private Set<Long> genreId;

    private Short sizeChapter;

}
