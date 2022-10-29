using AccountingService.Model;

namespace AccountingService.Storage
{
    public interface IAccountingStorage
    {
        public IEnumerable<Order> GetAllOrdersFromStudent(string student_id);
        public void AddOrder(OrderDTO order);
    }
}
