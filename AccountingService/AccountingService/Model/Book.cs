namespace AccountingService.Model
{
    public class Book
    {
        public string ISBN { get; set; }
        public int Price { get; set; }
        public Book() { }
        public Book(string isbn, int price)
        {
            ISBN = isbn;
            Price = price;
        }
    }
}
