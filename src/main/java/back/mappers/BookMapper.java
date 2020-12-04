package back.mappers;

import back.dto.BookDto;
import back.entity.Book;
import org.mapstruct.*;

@Mapper
public interface BookMapper {

    Book toEntity(BookDto bookDto);

}
