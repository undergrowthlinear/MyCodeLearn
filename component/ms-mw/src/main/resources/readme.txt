JMS----java message service,为异步消息而定制
	消息代理(Message Broker)----类似于邮局
	目的地(Destination)----
		队列----point-to-point----点对点----每一个消息都有一个发送者和接收者----银行网点
		主题----pub/sub----发布/订阅----发送到一个主题----杂志发行商与订阅者
优点----客户端无需等待/面向消息与解耦/客户端与服务端位置独立/确保投递
activemq----启动----activemq start
http----http://localhost:8161/----admin/admin
tcp----tcp://localhost:61616----tcp://127.0.0.1:61616
支持tcp/amqp/stomp/mqtt/ws
tcp----transmission control protocol
amqp----advanced message queuing protocol
stomp----simple/streaming text oriented messaging protocol
mqtt----message queue telemetry transport
ws----web services