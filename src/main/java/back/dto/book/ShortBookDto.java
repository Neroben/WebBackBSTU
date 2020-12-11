package back.dto.book;

import lombok.Data;

@Data
public class ShortBookDto {

    private String name;

    private String description;

    private Long author_id;

    private Short sizeChapter;

}
