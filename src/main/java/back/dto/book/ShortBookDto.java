package back.dto.book;

import lombok.Data;

@Data
public class ShortBookDto {

    private Long id;

    private String name;

    private String description;

    private String author;

    private int sizeChapter;

}
