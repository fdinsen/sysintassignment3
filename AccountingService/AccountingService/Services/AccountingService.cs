using AccountingService.Model;
using AccountingService.Storage;

namespace AccountingService.Services
{
    public class AccountingService : IAccountingService
    {
        private readonly ILogger<AccountingService> _logger;
        private readonly IAccountingStorage _storage;

        public AccountingService(ILoggerFactory factory)
        {
            _logger = factory.CreateLogger<AccountingService>();
            _storage = new AccountingStorage(factory);
        }
        public void AddOrder(OrderDTO order)
        {
            _storage.AddOrder(order);
        }

        public IEnumerable<Order> GetAllOrdersFromStudent(string student_id)
        {
            return _storage.GetAllOrdersFromStudent(student_id);
        }
    }
}
