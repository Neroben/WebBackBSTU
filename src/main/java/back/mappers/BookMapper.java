package back.mappers;

import back.dto.book.BookDto;
import back.dto.book.ChapterBookDto;
import back.dto.book.ShortBookDto;
import back.entity.Book;
import back.entity.Genre;
import back.entity.PageBook;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface BookMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "genre", source = "genreId")
    Book toEntity(BookDto bookDto);

    default Set<Genre> toGenre(Set<Long> genreId) {
        if(genreId == null)
            return new HashSet<>();
        return genreId.stream().map(Genre::new).collect(Collectors.toSet());
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "sizeChapter", expression = "java((book.getPages() == null) ? 0:book.getPages().size())")
    ShortBookDto toShortBookDto(Book book);

    default List<ShortBookDto> toShortBookDtos(List<Book> books) {
        return books.stream().map(this::toShortBookDto).collect(Collectors.toList());
    }

}
