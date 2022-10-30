package dk.cph.LibraryService.consumer;

import com.google.gson.Gson;
import dk.cph.LibraryService.datalayer.BookStorage;
import dk.cph.LibraryService.dto.Book;
import dk.cph.LibraryService.dto.ConsumerInfo;
import dk.cph.LibraryService.servicelayer.BookService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookConsumer {

    Gson gson = new Gson();
    private static final String conStr = "jdbc:mysql://localhost:3306/library";
    private static final String user = "root";
    private static final String pass = "password123";

    BookService bookService = new BookService(new BookStorage(conStr, user, pass));

    @KafkaListener(topics = "info",
            groupId = "group_id")

    public void consume(String message) {
        ConsumerInfo info = gson.fromJson(message, ConsumerInfo.class);
        bookService.buyBook(info.getStudentId(), info.getBookId());
    }
}
