namespace AccountingService.Model
{
    public class Order
    {
        private readonly int order_id;
        public int? OrderId { get { return order_id; } }
        public int StudentId { get; set; }
        public DateTime OrderDate { get; set; }
        public int BookId { get; set; }
        public int Price { get; set; }

        public Order(int order_id, int student_id, DateOnly order_date, TimeOnly order_time, int book_id, int price)
        {
            this.order_id = order_id;
            this.StudentId = student_id;
            this.OrderDate = order_date.ToDateTime(order_time);
            this.BookId = book_id;
            this.Price = price;

        }
        public Order(int order_id, int student_id, DateTime order_date, int book_id, int price)
        {
            this.order_id = order_id;
            this.StudentId = student_id;
            this.OrderDate = order_date;
            this.BookId = book_id;
            this.Price = price;
        }
    }
}
