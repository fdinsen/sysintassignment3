package dk.cph.LibraryService.dto;

public class ConsumerInfo {
    private Integer studentId;
    private Integer bookId;

    public ConsumerInfo(Integer studentId, Integer bookId) {
        this.studentId = studentId;
        this.bookId = bookId;
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
}
