package book;

import com.bookstore.grpc.Book;
import com.bookstore.grpc.bookStoreGrpc;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import producer.KafkaSendMessage;

import javax.json.Json;

public class BookService extends bookStoreGrpc.bookStoreImplBase {
    @Override
    public void buyBook(Book.BuyBookRequest request, StreamObserver<Book.APIResponse> responseObserver) {
        System.out.print("Inside buy book");

        int studentId = request.getStudentId();
        Timestamp orderDate = request.getOrderDate();
        String isbn = request.getIsbn();
        int bookPrice = request.getBookPrice();

        //TODO INVOKE KAFKA SERVER
        KafkaSendMessage kafkaMessage = new KafkaSendMessage();

        String message = Json.createObjectBuilder()
                .add("studentId", studentId)
                .add("orderDate", String.valueOf(orderDate))
                .add("isbn", isbn)
                .add("price", bookPrice)
                .build()
                .toString();


        kafkaMessage.sendMessage("OrderCreated", message);

        Book.APIResponse.Builder response = Book.APIResponse.newBuilder();

        response.setResponseCode(0).setResponseMessage("SUCCES BYING THE BOOK: " + isbn);


        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
