package dk.cph.LibraryService.dto;

public class Student {
    private Integer studentId;
    private Float wallet;
    private String studentName;
    private Integer programId;

    public Student(Integer studentId, Float wallet, String studentName, Integer programId) {
        this.studentId = studentId;
        this.wallet = wallet;
        this.studentName = studentName;
        this.programId = programId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Float getWallet() {
        return wallet;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", wallet=" + wallet +
                ", studentName='" + studentName + '\'' +
                ", programId=" + programId +
                '}';
    }
}
