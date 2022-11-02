namespace AccountingService.Model
{
    public class OrderDTO
    {
        public int studentId { get; set; }
        public DateTime orderDate { get; set; }
        public int bookId { get; set; }
        public int price { get; set; }

        public OrderDTO()
        {
        }
        public OrderDTO(int studentId, int bookId, int price)
        {
            this.studentId = studentId;
            orderDate = DateTime.Now;
            this.bookId = bookId;
            this.price = price;
        }
    }
}
