namespace AccountingService.Model
{
    public class Order
    {
        private readonly int order_id;
        public int? OrderId { get { return order_id; } }
        public string StudentId { get; set; }
        public DateTime OrderDate { get; set; }
        public List<Book> Orderlines { get; }

        public int TotalPrice { get; private set; }

        public Order(int order_id, string student_id, DateOnly order_date, TimeOnly order_time,  List<Book> orderlines)
        {
            this.order_id = order_id;
            this.StudentId = student_id;
            this.OrderDate = order_date.ToDateTime(order_time);
            this.Orderlines = orderlines;

            this.TotalPrice = CalculateTotalPrice(orderlines);
        }
        public Order(int order_id, string student_id, DateTime order_date, List<Book> orderlines)
        {
            this.order_id = order_id;
            this.StudentId = student_id;
            this.OrderDate = order_date;
            this.Orderlines = orderlines;

            this.TotalPrice = CalculateTotalPrice(orderlines);
        }
        public Order(int order_id, string student_id, DateOnly order_date, TimeOnly order_time)
        {
            this.order_id = order_id;
            this.StudentId = student_id;
            this.OrderDate = order_date.ToDateTime(order_time);
            
            TotalPrice = 0;
            Orderlines = new List<Book>();
        }
        public Order(int order_id, string student_id, DateTime order_date)
        {
            this.order_id = order_id;
            this.StudentId = student_id;
            this.OrderDate = order_date;
            
            TotalPrice = 0;
            Orderlines = new List<Book>();
        }

        public void AddBookToOrder(Book book)
        {
            Orderlines.Add(book);
            TotalPrice += book.Price;
        }

        private static int CalculateTotalPrice(List<Book> orderlines)
        {
            int total = 0;
            foreach(Book book in orderlines)
            {
                total += book.Price;
            }
            return total;
        } 
    }
}
