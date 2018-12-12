set CP=.;"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\commons-io-1.2.jar";"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\commons-cli-1.1.jar";"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\rabbitmq-client.jar";"c:\rmq_test\Microsoft JDBC Driver 4.2 for SQL Server\sqljdbc_4.2\enu\sqljdbc42.jar";"c:\rmq_test\classes"

del c:\rmq_test\result.txt /Q

java -classpath %CP% RabbitTo1CGate sourcename=FROMRABBIT numberofmessages=1000000 bufserverdnsname="sql-server" bufserverport=1433 bufserverusername=sqluser bufserverpassword=sqlpass bufdatabasename=dbname rmqserver="rmq-server" rmqqueue="queue-name" rmqport=5672 rmqusername=rmquser rmqpassword="rmqpass" >> c:\rmq_test\result.txt