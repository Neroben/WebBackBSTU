package back.dto.book;

import lombok.Data;

import java.util.Set;

@Data
public class BookDto {

    private Long id;

    private String name;

    private String description;

    private String author;

    private Set<Long> genreId;

    private Short sizeChapter;

}
