set CP=.;"c:\rmqprod\rabbitmq-java-client-bin-3.6.0\commons-io-1.2.jar";"c:\rmqprod\rabbitmq-java-client-bin-3.6.0\commons-cli-1.1.jar";"c:\rmqprod\rabbitmq-java-client-bin-3.6.0\rabbitmq-client.jar";"c:\rmqprod\Microsoft JDBC Driver 4.2 for SQL Server\sqljdbc_4.2\enu\sqljdbc42.jar";"c:\rmqprod\classes"

del c:\rmqprod\result_test.txt /Q

java -classpath %CP% From1CtoRabbit sourcename=FROM1C numberofmessages=1000000  bufserverdnsname="sql-server" bufserverport=1433  bufserverusername=sqluser bufserverpassword=sqlpass rmqserver="rmq-server" rmqqueue="queue-to-post"  rmqport=5672 rmqusername=rmquser rmqpassword="rmqpass" filespath="\\server\folder\" filemask="*.xml" rmqexchange="point.exchange" rmqcommonheader="Messages from 1C" batchmode=sendfile >> c:\rmqprod\sendresult.txt


