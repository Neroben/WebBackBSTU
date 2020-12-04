package back.controllers;

import back.dto.BookDto;
import back.dto.ChapterBookDto;
import back.dto.ShortBookDto;
import back.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Long> addBook(@RequestBody BookDto book) {
        return ResponseEntity.ok().body(bookService.createBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterBookDto> getBookChapter(@PathVariable Long id, @RequestParam("chapter") Integer chapter) {
        return ResponseEntity.ok().body(bookService.getBookChapter(id, chapter));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return HttpStatus.OK;
    }

    @PutMapping
    public ResponseEntity<Long> updateBook(@RequestBody BookDto book) {
        return ResponseEntity.ok().body(bookService.updateBook(book));
    }

    @GetMapping
    public ResponseEntity<List<ShortBookDto>> getAllBook() {
        return ResponseEntity.ok().body(bookService.getAllBook());
    }
}
