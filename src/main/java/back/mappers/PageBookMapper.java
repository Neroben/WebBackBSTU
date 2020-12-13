package back.mappers;

import back.dto.book.ChapterBookDto;
import back.entity.Book;
import back.entity.PageBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper
public interface PageBookMapper {

    @Mapping(target = "text", source = "text")
    @Mapping(target = "numPage", source = "numChapter")
    @Mapping(target = "book", source = "bookId")
    PageBook toEntity(ChapterBookDto dto);

    default Book toBook(Long bookId){
        if(bookId == null)
            return null;
        return new Book(bookId);
    }

    @Mapping(target = "bookId", source = "book.id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "numChapter", source = "numPage")
    @Mapping(target = "text", source = "text")
    ChapterBookDto toDto(PageBook pageBook);

}
