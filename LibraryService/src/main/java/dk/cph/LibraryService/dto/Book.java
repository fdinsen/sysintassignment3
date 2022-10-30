package dk.cph.LibraryService.dto;

public class Book {
    private Integer id;
    private Integer stock;
    private Integer programId;
    private Float price;

    public Book(Integer id, Integer stock, Integer programId, Float price) {
        this.id = id;
        this.stock = stock;
        this.programId = programId;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", stock=" + stock +
                ", programId=" + programId +
                ", price=" + price +
                '}';
    }
}
