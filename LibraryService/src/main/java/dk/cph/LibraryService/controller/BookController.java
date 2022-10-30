package dk.cph.LibraryService.controller;

import dk.cph.LibraryService.datalayer.BookStorage;
import dk.cph.LibraryService.dto.Book;
import dk.cph.LibraryService.servicelayer.BookService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
public class BookController {
    private static final String conStr = "jdbc:mysql://localhost:3306/library";
    private static final String user = "root";
    private static final String pass = "password123";

    BookService bookService = new BookService(new BookStorage(conStr, user, pass));

    @GetMapping("/student/{id}")
    public CollectionModel<Book> booksByStudent(@PathVariable int id)
    {
        List<Book> books = bookService.getOwnedBooksByStudent(id);

        CollectionModel<Book> resource = CollectionModel.of(books);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).booksByProgram(id));
        resource.add(linkTo.withRel("book-by-program"));

        return resource;
    }

    @GetMapping("/program/{id}")
    public CollectionModel<Book> booksByProgram(@PathVariable int id)
    {
        List<Book> books = bookService.getBooksByProgramId(id);

        CollectionModel<Book> resource = CollectionModel.of(books);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).booksByStudent(id));
        resource.add(linkTo.withRel("book-by-student"));

        return resource;
    }
}
