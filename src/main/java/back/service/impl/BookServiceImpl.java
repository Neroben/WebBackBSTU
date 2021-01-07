package back.service.impl;

import back.config.MultipartConfig;
import back.dto.book.BookDto;
import back.dto.book.ChapterBookDto;
import back.dto.book.ShortBookDto;
import back.entity.Book;
import back.entity.PageBook;
import back.exception.BadRequestException;
import back.exception.ResourceNotFoundException;
import back.mappers.BookMapper;
import back.mappers.PageBookMapper;
import back.repository.BookRepository;
import back.repository.PageBookRepository;
import back.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final MultipartConfig multipartConfig;
    private final BookMapper bookMapper;
    private final PageBookMapper pageBookMapper;
    private final BookRepository bookRepository;
    private final PageBookRepository pageBookRepository;

    @Override
    public Long createBook(BookDto book, MultipartFile logo) {
        Book entity = bookMapper.toEntity(book);
        entity.setPathLogo(downloadLogo(logo));
        return bookRepository.save(entity).getId();
    }

    @Override
    public Long addPageBook(ChapterBookDto chapter) {
        if(pageBookRepository.findByBook_IdAndNumPage(chapter.getBookId(),chapter.getNumChapter()).isPresent())
            throw new BadRequestException("This chapter is exist");
        PageBook entity = pageBookMapper.toEntity(chapter);
        return pageBookRepository.save(entity).getId();
    }

    @Override
    @Transactional
    public List<ShortBookDto> getAllBook() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toShortBookDtos(books);
    }

    @Override
    public ChapterBookDto getBookChapter(Long id, Long chapter) {
        PageBook pageBook = pageBookRepository.findByBook_IdAndNumPage(id, chapter).orElseThrow(() -> new ResourceNotFoundException("PageBook not found by book id = " + id + "chapter id " + chapter));
        return pageBookMapper.toDto(pageBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        for(PageBook page: book.getPages()){
            pageBookRepository.delete(page);
        }
        bookRepository.delete(book);
    }

    @Override
    public void deleteChapterBook(Long bookId, Long chapterId) {
        PageBook pageBook = pageBookRepository.findByBook_IdAndNumPage(bookId, chapterId).orElseThrow(() -> new ResourceNotFoundException("PageBook not found by book id = " + bookId + "chapter id " + chapterId));
        pageBookRepository.delete(pageBook);
    }

    @Override
    public Long updateBook(BookDto dto) {
        return bookRepository.save(bookMapper.toEntity(dto)).getId();
    }

    @Override
    public Long updatePageBook(ChapterBookDto dto) {
        return pageBookRepository.save(pageBookMapper.toEntity(dto)).getId();
    }

    @SneakyThrows
    @Override
    public Resource downloadPhoto(Long bookId) {
        log.info("download id: [{}] ", bookId);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Photo logo", "bookId", bookId));
        File file = new File(multipartConfig.getLocation() + book.getPathLogo());
        String mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null) {
            throw new ResourceNotFoundException("Photo", "id", bookId.toString() + " is broken mimeType = null");
        }
        Path path = Paths.get(file.getAbsolutePath());
        try {
            return new ByteArrayResource(Files.readAllBytes(path));
        } catch (NoSuchFileException ex){
            throw new ResourceNotFoundException("Photo", "bookId", bookId);
        }
    }

    @SneakyThrows
    private String downloadLogo(MultipartFile logo) {
        if(logo == null)
            return null;
        if (logo.isEmpty()) {
            throw new BadRequestException("Can not upload file Image. Cause file is empty");
        }
        String originalFilename = logo.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new BadRequestException("Can not upload file Image. Cause no name file");
        }
        String mimeType = logo.getContentType();
        if (mimeType == null || !mimeType.startsWith("image/")) {
            throw new BadRequestException("Can not upload file Image. Cause type is unsupported");
        }
        String fileExtension = "." + MimeTypeUtils.parseMimeType(mimeType).getSubtype();
        String newNamePhotoUUID = UUID.randomUUID().toString();
        String folderUuid = newNamePhotoUUID.substring(0, 2) + File.separator + newNamePhotoUUID.substring(2, 4);
        String uuidNamePhoto = File.separator + newNamePhotoUUID + fileExtension;
        String uri = folderUuid + uuidNamePhoto;
        String directory = multipartConfig.getLocation() + folderUuid;
        File file = fileWithDirectoryAssurance(directory, uuidNamePhoto);
        logo.transferTo(file);
        return uri;
    }

    private File fileWithDirectoryAssurance(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        return new File(directory + filename);
    }

}
