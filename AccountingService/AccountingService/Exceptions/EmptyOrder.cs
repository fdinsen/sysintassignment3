namespace AccountingService.Exceptions
{
    public class EmptyOrder : Exception
    {
        public EmptyOrder(string message) : base(message)
        {

        }
    }
}
