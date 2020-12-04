package back.service;

import back.dto.BookDto;
import back.dto.ChapterBookDto;
import back.dto.ShortBookDto;

import java.util.List;

public interface BookService {

    Long createBook(BookDto book);

    List<ShortBookDto> getAllBook();

    ChapterBookDto getBookChapter(Long id, Integer chapter);

    void deleteBook(Long id);

    Long updateBook(BookDto dto);

}
