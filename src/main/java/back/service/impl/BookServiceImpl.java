package back.service.impl;

import back.config.MultipartConfig;
import back.dto.book.BookDto;
import back.dto.book.ChapterBookDto;
import back.dto.book.ShortBookDto;
import back.entity.Book;
import back.entity.PageBook;
import back.exception.BadRequestException;
import back.mappers.BookMapper;
import back.mappers.PageBookMapper;
import back.repository.BookRepository;
import back.repository.PageBookRepository;
import back.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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
    public List<ShortBookDto> getAllBook() {
        return null;
    }

    @Override
    public ChapterBookDto getBookChapter(Long id, Integer chapter) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public void deleteChapterBook(Long bookId, Long chapterId) {

    }

    @Override
    public Long updateBook(BookDto dto) {
        return null;
    }

    @Override
    public BookDto getBook(Long id) {
        return null;
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
