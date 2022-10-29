namespace AccountingService.Model
{
    public class OrderDTO
    {
        public string StudentId { get; set; }
        public DateTime OrderDate { get; set; }
        public List<Book> Orderlines { get; set;  }

        public OrderDTO()
        {
            Orderlines = new List<Book>();
        }

        public OrderDTO(string student_id, DateOnly order_date, TimeOnly order_time, List<Book> orderlines)
        {
            this.StudentId = student_id;
            this.OrderDate = order_date.ToDateTime(order_time);
            this.Orderlines = orderlines;

        }
        public OrderDTO(string student_id, DateTime order_date, List<Book> orderlines)
        {
            this.StudentId = student_id;
            this.OrderDate = order_date;
            this.Orderlines = orderlines;
        }
        public OrderDTO(string student_id, DateOnly order_date, TimeOnly order_time)
        {
            this.StudentId = student_id;
            this.OrderDate = order_date.ToDateTime(order_time);

            Orderlines = new List<Book>();
        }
        public OrderDTO(string student_id, DateTime order_date)
        {
            this.StudentId = student_id;
            this.OrderDate = order_date;

            Orderlines = new List<Book>();
        }

        public void AddBookToOrder(Book book)
        {
            Orderlines.Add(book);
        }

    }
}
