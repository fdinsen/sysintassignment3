using AccountingService.Exceptions;
using AccountingService.Model;
using Npgsql;
using System.Data.Common;

namespace AccountingService.Storage
{
    public class AccountingStorage : IAccountingStorage
    {
        private readonly ILogger<AccountingStorage> _logger;

        private string connection_string = "Server=db;Port=5432;Userid=postgres;Password=postgres;Database=postgres;";

        public AccountingStorage(ILoggerFactory factory)
        {
            _logger = factory.CreateLogger<AccountingStorage>();
        }

        public void AddOrder(OrderDTO order)
        {
            if (order.Orderlines.Count == 0) throw new EmptyOrder("No orderlines in the order.");
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

        public IEnumerable<Order> GetAllOrdersFromStudent(string student_id)
        {
            var sql = "SELECT orders.o_id, student_id, order_date, order_time, ol_id, book_isbn, price FROM orders JOIN orderlines ON orders.o_id = orderlines.o_id WHERE student_id = @sid;";
            using var con = new NpgsqlConnection(connection_string);
            con.Open();
            var cmd = con.CreateCommand();
            cmd.CommandText = sql;
            cmd.Parameters.Add(new NpgsqlParameter("sid", NpgsqlTypes.NpgsqlDbType.Char) { Value = student_id });
            cmd.Prepare();
            var reader = cmd.ExecuteReader();
            var orders = new List<Order>();
            Order? order = null;
            while(reader.Read())
            {
                int oid = reader.GetInt32(0);
                if(order == null || order.OrderId != oid)
                {
                    order = GetOrderFromReader(reader, oid);
                    order.AddBookToOrder(GetBookFromreader(reader));
                    orders.Add(order);
                }
                else
                {
                    order.AddBookToOrder(GetBookFromreader(reader));
                }
            }
            return orders;
        }

        private Order GetOrderFromReader(NpgsqlDataReader reader, int oid)
        {
            string sid = reader.GetString(1);
            var od = DateOnly.FromDateTime((DateTime)reader.GetDate(2));
            var ot = TimeOnly.FromTimeSpan(reader.GetTimeSpan(3));

            return new Order(oid, sid, od, ot);
        }
        private Book GetBookFromreader(NpgsqlDataReader reader)
        {
            var isbn = reader.GetString(5);
            var price = reader.GetInt32(6);
            return new Book(isbn, price);
        }

        private int InsertOrderInDatabase(DbConnection con, OrderDTO order)
        {
            try 
            {
                var sql = "INSERT INTO orders (student_id, order_date, order_time) VALUES (@sid, @odate, @otime) RETURNING orders.o_id;";
                using var cmd = con.CreateCommand();
                cmd.CommandText = sql;
                cmd.Parameters.Add(new NpgsqlParameter("sid", NpgsqlTypes.NpgsqlDbType.Char) { Value= order.StudentId});
                cmd.Parameters.Add(new NpgsqlParameter("odate", NpgsqlTypes.NpgsqlDbType.Date) { Value = order.OrderDate.Date});
                cmd.Parameters.Add(new NpgsqlParameter("otime", NpgsqlTypes.NpgsqlDbType.Time) { Value = order.OrderDate.TimeOfDay });
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
            var sql = "INSERT INTO orderlines (o_id, book_isbn, price) VALUES ";
            using var cmd = con.CreateCommand();

            for(int i = 0; i < order.Orderlines.Count; i++)
            {
                var line = order.Orderlines[i];
                sql += string.Format("(@oid{0}, @isbn{0}, @price{0})", i);
                if(i < order.Orderlines.Count - 1)
                {
                    sql += ", ";
                }
                cmd.Parameters.Add(new NpgsqlParameter("oid" + i, NpgsqlTypes.NpgsqlDbType.Integer) { Value = orderId });
                cmd.Parameters.Add(new NpgsqlParameter("isbn" + i, NpgsqlTypes.NpgsqlDbType.Char) { Value = line.ISBN });
                cmd.Parameters.Add(new NpgsqlParameter("price" + i, NpgsqlTypes.NpgsqlDbType.Integer) { Value = line.Price });
            }
            sql += ";";
            cmd.CommandText = sql;
            cmd.Prepare();
            cmd.ExecuteNonQuery();
        }
    }
}