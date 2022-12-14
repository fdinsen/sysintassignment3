using AccountingService.Model;

namespace AccountingService.Storage
{
    public interface IAccountingStorage
    {
        public IEnumerable<Order> GetAllOrdersFromStudent(int student_id);
        public void AddOrder(OrderDTO order);
    }
}
