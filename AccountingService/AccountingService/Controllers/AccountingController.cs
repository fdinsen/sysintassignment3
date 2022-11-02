using AccountingService.Model;
using AccountingService.Services;
using Microsoft.AspNetCore.Mvc;

namespace AccountingService.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class AccountingController : ControllerBase
    {

        private readonly ILogger<AccountingController> _logger;
        private readonly IAccountingService _service;

        public AccountingController(ILoggerFactory factory)
        {
            _logger = factory.CreateLogger<AccountingController>();
            _service = new Services.AccountingService(factory);
        }

        [HttpGet(Name = "GetOrders")]
        public IEnumerable<Order> Get(int student_id)
        {
            return _service.GetAllOrdersFromStudent(student_id);
        }

        [HttpPost(Name = "AddOrder")]
        public IActionResult Post([FromBody] OrderDTO order)
        {
            _service.AddOrder(order);
            return Ok();
        }
    }
}