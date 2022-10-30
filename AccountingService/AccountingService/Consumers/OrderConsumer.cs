﻿using AccountingService.Model;
using Confluent.Kafka;
using System.Diagnostics;
using System.Text.Json;

namespace AccountingService.Consumers
{
    public class OrderConsumer : IHostedService
    {
        private readonly string _topic = "OrderCreated";
        private readonly string _groupID = "test_group";
        private readonly string _bootstrapServer = "host.docker.internal:9092";
        public Task StartAsync(CancellationToken cancellationToken)
        {
            var config = new ConsumerConfig
            {
                BootstrapServers = _bootstrapServer,
                GroupId = _groupID,
                AutoOffsetReset = AutoOffsetReset.Earliest,
            };
            try
            {
                using (var consumerBuilder = new ConsumerBuilder<Ignore, string>(config).Build())
                {
                    consumerBuilder.Subscribe(_topic);
                    var cancelToken = new CancellationTokenSource();
                    try
                    {
                        while(true)
                        {
                            var consumer = consumerBuilder.Consume(cancelToken.Token);
                            //var orderRequest = JsonSerializer.Deserialize<OrderDTO>(consumer.Message.Value);
                            Debug.WriteLine("Test " + consumer.Message.Value);
                        }
                    }catch(OperationCanceledException)
                    {
                        consumerBuilder.Close();
                    }
                }
            }
            catch(Exception ex)
            {
                
            }

            return Task.CompletedTask;
        }
        public Task StopAsync(CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }
    }
}
