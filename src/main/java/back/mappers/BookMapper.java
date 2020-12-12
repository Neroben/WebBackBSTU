package back.mappers;

import back.dto.book.BookDto;
import back.entity.Book;
import back.entity.Genre;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface BookMapper {

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

}
