using AccountingService.Exceptions;
using AccountingService.Model;
using Npgsql;
using System.Data.Common;

namespace AccountingService.Storage
{
    public class AccountingStorage : IAccountingStorage
    {
        private readonly ILogger<AccountingStorage> _logger;

        private string connection_string = "Server=localhost;Port=5432;Userid=postgres;Password=postgres;Database=postgres;";

        public AccountingStorage(ILoggerFactory factory)
        {
            _logger = factory.CreateLogger<AccountingStorage>();
        }

        public void AddOrder(OrderDTO order)
        {
            using var con = new NpgsqlConnection(connection_string);
            con.Open();
            var transaction = con.BeginTransaction();
            var id = InsertOrderInDatabase(con, order);
            if(id == -1)
            {
                transaction.Rollback();
                con.Close();
                return;
            }
            InsertOrderlinesInDatabase(con, order, id);
            transaction.Commit();
            con.Close();
        }

        public IEnumerable<Order> GetAllOrdersFromStudent(int student_id)
        {
            var sql = "SELECT orders.o_id, student_id, order_date, order_time, ol_id, book_id, price FROM orders JOIN orderlines ON orders.o_id = orderlines.o_id WHERE student_id = @sid;";
            using var con = new NpgsqlConnection(connection_string);
            con.Open();
            var cmd = con.CreateCommand();
            cmd.CommandText = sql;
            cmd.Parameters.Add(new NpgsqlParameter("sid", NpgsqlTypes.NpgsqlDbType.Integer) { Value = student_id });
            cmd.Prepare();
            var reader = cmd.ExecuteReader();
            var orders = new List<Order>();
            Order? order = null;
            while(reader.Read())
            {
                int oid = reader.GetInt32(0);
                order = GetOrderFromReader(reader, oid);
                orders.Add(order);

            }
            return orders;
        }

        private Order GetOrderFromReader(NpgsqlDataReader reader, int oid)
        {
            var sid = reader.GetInt32(1);
            var od = DateOnly.FromDateTime((DateTime)reader.GetDate(2));
            var ot = TimeOnly.FromTimeSpan(reader.GetTimeSpan(3));
            var bid = reader.GetInt32(5);
            var price = reader.GetInt32(6);
            return new Order(oid, sid, od, ot, bid, price);
        }

        private int InsertOrderInDatabase(DbConnection con, OrderDTO order)
        {
            try 
            {
                var sql = "INSERT INTO orders (student_id, order_date, order_time) VALUES (@sid, @odate, @otime) RETURNING orders.o_id;";
                using var cmd = con.CreateCommand();
                cmd.CommandText = sql;
                cmd.Parameters.Add(new NpgsqlParameter("sid", NpgsqlTypes.NpgsqlDbType.Integer) { Value= order.studentId});
                cmd.Parameters.Add(new NpgsqlParameter("odate", NpgsqlTypes.NpgsqlDbType.Date) { Value = order.orderDate.Date});
                cmd.Parameters.Add(new NpgsqlParameter("otime", NpgsqlTypes.NpgsqlDbType.Time) { Value = order.orderDate.TimeOfDay });
                cmd.Prepare();
                int id = (int) cmd.ExecuteScalar();
                return id;
            }
            catch(Exception e)
            {
                return -1;
            }
            
        }

        private void InsertOrderlinesInDatabase(DbConnection con, OrderDTO order, int orderId)
        {
            var sql = "INSERT INTO orderlines (o_id, book_id, price) VALUES (@oid, @bid, @price);";
            using var cmd = con.CreateCommand();
            cmd.Parameters.Add(new NpgsqlParameter("oid", NpgsqlTypes.NpgsqlDbType.Integer) { Value = orderId });
            cmd.Parameters.Add(new NpgsqlParameter("bid", NpgsqlTypes.NpgsqlDbType.Integer) { Value = order.bookId });
            cmd.Parameters.Add(new NpgsqlParameter("price", NpgsqlTypes.NpgsqlDbType.Integer) { Value = order.price });
            cmd.CommandText = sql;
            cmd.Prepare();
            cmd.ExecuteNonQuery();
        }
    }
}