package back.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String author;
    private String time;
    private String text;
}
