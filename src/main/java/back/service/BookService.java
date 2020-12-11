package back.service;

import back.dto.BookDto;
import back.dto.ChapterBookDto;
import back.dto.ShortBookDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    Long createBook(BookDto book, MultipartFile logo);

    Long addPageBook(ChapterBookDto chapter);

    List<ShortBookDto> getAllBook();

    ChapterBookDto getBookChapter(Long id, Integer chapter);

    void deleteBook(Long id);

    void deleteChapterBook(Long bookId, Long chapterId);


    Long updateBook(BookDto dto);

    BookDto getBook(Long id);

}
