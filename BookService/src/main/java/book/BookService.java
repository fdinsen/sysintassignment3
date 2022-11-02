package book;

import com.bookstore.grpc.Book;
import com.bookstore.grpc.bookStoreGrpc;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import producer.KafkaProducerConfig;

import javax.json.Json;

public class BookService extends bookStoreGrpc.bookStoreImplBase {
    @Override
    public void buyBook(Book.BuyBookRequest request, StreamObserver<Book.APIResponse> responseObserver) {
        System.out.print("Inside buy book");

        int studentId = request.getStudentId();
        int bookId = request.getBookId();
        int bookPrice = request.getBookPrice();

        KafkaProducerConfig producer = new KafkaProducerConfig();

        String message = Json.createObjectBuilder()
                .add("studentId", studentId)
                .add("bookId", bookId)
                .add("price", bookPrice)
                .build()
                .toString();

        producer.sendMessageAsync(message);

        Book.APIResponse.Builder response = Book.APIResponse.newBuilder();

        response.setResponseCode(0).setResponseMessage("SUCCES BYING THE BOOK: " + bookId);


        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
