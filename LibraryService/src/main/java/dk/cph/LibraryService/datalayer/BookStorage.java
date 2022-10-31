package dk.cph.LibraryService.datalayer;

import dk.cph.LibraryService.dto.Book;
import dk.cph.LibraryService.dto.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookStorage {


    private String connectionString;
    private String username, password;
    public BookStorage(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }


    private final String GET_OWNED_BOOKS = "SELECT idBooks, stock, studyprogramid, price FROM library.Books book" +
            " JOIN library.Studyprogram program ON program.id = book.studyprogramid" +
            " JOIN library.Students students ON students.programId = program.id" +
            " WHERE students.studentid = ?;";

    public List<Book> getOwnedBooksByStudentId(Integer studentId) {
        List<Book> books = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(GET_OWNED_BOOKS);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idBooks");
                int stock = resultSet.getInt("stock");
                int studyprogramid = resultSet.getInt("studyprogramid");
                float price = resultSet.getFloat("price");

                Book book = new Book(id, stock, studyprogramid, price);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    private static final String GET_BOOKS_BY_PROGRAM = "SELECT idBooks, stock, studyprogramid, price FROM library.Books book" +
            " WHERE book.studyprogramid = ?;";

    public List<Book> getBooksByProgramId(Integer programId) {
        List<Book> books = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(GET_BOOKS_BY_PROGRAM);
            statement.setInt(1, programId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idBooks");
                int stock = resultSet.getInt("stock");
                int studyprogramid = resultSet.getInt("studyprogramid");
                float price = resultSet.getFloat("price");

                Book book = new Book(id, stock, studyprogramid, price);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }


    private static final String GET_BOOK_BY_ID = "SELECT idBooks, stock, studyprogramid, price FROM library.Books book WHERE book.idBooks = ?";

    private Book getBookById(int id) {
        try(Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(GET_BOOK_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int bookId = resultSet.getInt("idBooks");
                int stock = resultSet.getInt("stock");
                int studyprogramid = resultSet.getInt("studyprogramid");
                float price = resultSet.getFloat("price");
                Book book = new Book(bookId, stock, studyprogramid, price);
                return book;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private static final String GET_STUDENT_BY_ID = "SELECT studentId, wallet, studentname, programId FROM library.Students WHERE studentid = ?;";
    private Student getStudentById(int id) {
        try(Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(GET_STUDENT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                float wallet = resultSet.getFloat("wallet");
                String name = resultSet.getString("studentname");
                int programId = resultSet.getInt("programId");
                Student student = new Student(studentId, wallet, name, programId);
                return student;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static final String UPDATE_STUDENT_WALLET ="UPDATE library.Students SET wallet = ? WHERE studentId = ?;";
    public void buyBook(int studentId, int bookId) {
        try (Connection con = getConnection()) {
            Student student = getStudentById(studentId);
            Book book = getBookById(bookId);
            if (book.getPrice() > student.getWallet()) {
                throw new RuntimeException("Not enough money");
            }
            PreparedStatement statement = con.prepareStatement(UPDATE_STUDENT_WALLET);
            statement.setFloat(1, student.getWallet() - book.getPrice());
            statement.setInt(2, studentId);
            statement.executeUpdate();
            insertBookRelation(studentId, bookId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    private static final String INSERT_BOOK_RELATION ="INSERT INTO library.StudentBookRel (studentid, bookid) VALUES (?, ?);";
    private void insertBookRelation(int studentId, int bookId) {
        try (Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_BOOK_RELATION);
            statement.setInt(1, studentId);
            statement.setInt(2, bookId);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    private static final String GET_RECOMMENDATIONS_BY_STUDENT_ID = "SELECT idBooks, stock, studyprogramid, price FROM library.Books" +
            " JOIN library.Students student ON student.programid = studyprogramid" +
            " WHERE student.studentid = ?";

    public List<Book> getRecommendedBooksByStudentId(Integer studentId) {
        List<Book> books = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(GET_RECOMMENDATIONS_BY_STUDENT_ID);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idBooks");
                int stock = resultSet.getInt("stock");
                int studyprogramid = resultSet.getInt("studyprogramid");
                float price = resultSet.getFloat("price");

                Book book = new Book(id, stock, studyprogramid, price);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;

    }

}
