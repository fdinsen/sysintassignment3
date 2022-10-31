package dk.cph.LibraryService.servicelayer;

import dk.cph.LibraryService.datalayer.BookStorage;
import dk.cph.LibraryService.dto.Book;

import java.util.List;


public class BookService {
    private BookStorage bookStorage;

    public BookService(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    public List<Book> getOwnedBooksByStudent(Integer studentid) {
        return bookStorage.getOwnedBooksByStudentId(studentid);
    }

    public List<Book> getBooksByProgramId(Integer programId) {
        return bookStorage.getBooksByProgramId(programId);
    }

    public void buyBook(int studentId, int bookId) {
        bookStorage.buyBook(studentId, bookId);
    }

    public List<Book> getRecommendedBooksByStudentId(Integer studentid) {
        return bookStorage.getRecommendedBooksByStudentId(studentid);
    }



}
