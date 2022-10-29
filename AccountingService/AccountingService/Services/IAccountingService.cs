using AccountingService.Model;

namespace AccountingService.Services
{
    public interface IAccountingService
    {
        public void AddOrder(OrderDTO order);
        public IEnumerable<Order> GetAllOrdersFromStudent(string student_id);
    }
}
