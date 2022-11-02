package dk.cph.LibraryService.dto;

public class ConsumerInfo {
    private Integer studentId;
    private Integer bookId;
    private Integer bookPrice;

    public ConsumerInfo(Integer studentId, Integer bookId, Integer bookPrice) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.bookPrice = bookPrice;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }
}
