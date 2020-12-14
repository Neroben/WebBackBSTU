package back.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment extends AbstractEntity {
    private String author;
    private String text;
    private String time;
}
