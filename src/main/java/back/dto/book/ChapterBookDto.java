package back.dto.book;

import lombok.Data;

@Data
public class ChapterBookDto {

    private Long bookId;
    private Long numChapter;
    private String text;

}
