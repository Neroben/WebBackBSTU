package back.dto.book;

import lombok.Data;

@Data
public class BookDto {

    private String name;

    private String description;

    private Long author_id;

    private Short sizeChapter;

}
