Клиент для шины RabbitMQ.  
Умеет получать/отправлять сообщения. Сообщение представляет собой XML-текст.

Инструкции.  

##Получение.  

Сообщения читаются из очереди и складываются в базу MSSQL.  
Чтение запускается из cmd-файла. Можно настроить запуск через планировщик Windows.  
Файл описывается ниже.  

rem Установка переменных окружения (CP=Current Path).
rem Предполагается, что клиент запускается из каталога c:\rmq_test\
rem Классы лежат в каталоге c:\rmq_test\classes
set CP=.;"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\commons-io-1.2.jar";"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\commons-cli-1.1.jar";"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\rabbitmq-client.jar";"c:\rmq_test\Microsoft JDBC Driver 4.2 for SQL Server\sqljdbc_4.2\enu\sqljdbc42.jar";"c:\rmq_test\classes"

rem Удаляем лог
del c:\rmq_test\result.txt /Q

rem Запускаем чтение
java -classpath %CP% RabbitTo1CGate sourcename=FROMRMQ numberofmessages=1000000 bufserverdnsname="server" bufserverport=1433 bufserverusername=user bufserverpassword=pass bufdatabasename=dbname rmqserver="rmqsrv" rmqqueue="queue" rmqport=5672 rmqusername=rmquser rmqpassword="rmqpass" >> c:\rmq_test\result.txt

Параметры:
-classpath %CP%									путь к классам
RabbitTo1CGate									точка входа, имя класса 
sourcename=FROMRMQ								строка, значение пишется в колонку 'MSG_SOURCE' таблицы MSSQL 'msg_buffer'
numberofmessages=1000000						число сообщений, забираемое в одном сеансе 
bufserverdnsname="servername.domain" 			сетевое имя сервера MSSQL
bufserverport=1433 								порт MSSQL
bufserverusername=user_mssql					пользователь базы MSSQL 
bufserverpassword=pass_mssql					пароль 
bufdatabasename=database_name					имя базы MSSQL
rmqserver="rmqserver.domain" 					сетевое имя сервера RabbitMQ
rmqqueue="queue-name" 							имя очереди
rmqport=5672									порт доступа к RabbitMQ 
rmqusername=rmq_user							пользователь RabbitMQ	
rmqpassword="rmq_pass" 							пароль RabbitMQ

это не параметр, а переадресация вывода консоли в файл лога
>> c:\rmq_test\result.txt


##Отправка.

rem Установка переменных окружения (CP=Current Path).
rem Предполагается, что клиент запускается из каталога c:\rmq_test\
rem Классы лежат в каталоге c:\rmq_test\classes
set CP=.;"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\commons-io-1.2.jar";"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\commons-cli-1.1.jar";"c:\rmq_test\rabbitmq-java-client-bin-3.6.0\rabbitmq-client.jar";"c:\rmq_test\Microsoft JDBC Driver 4.2 for SQL Server\sqljdbc_4.2\enu\sqljdbc42.jar";"c:\rmq_test\classes"

rem Удаляем лог
del c:\rmq_test\sendresult.txt /Q

rem Запускаем отправку
java -classpath %CP% From1CtoRabbit sourcename=from1C numberofmessages=1000000  bufserverdnsname="sql-server" bufserverport=1433 bufserverusername=user bufserverpassword=pass rmqserver="rmq-server" rmqqueue="queue-name"  rmqport=5672 rmqusername=Rabbituser rmqpassword="rabbit pass" filespath="\\server\Exchange\toRabbit" filemask="*.xml" rmqexchange="point.of.exchange" rmqcommonheader="some header" batchmode=sendfile >> c:\rmq_test\sendresult.txt  

Параметры:
-classpath %CP% 
From1CtoRabbit 
sourcename=from1C 
numberofmessages=1000000  
bufserverdnsname="sql-server" 
bufserverport=1433  
bufserverusername=user 
bufserverpassword=pass 
rmqserver="rmqserver.domain" 					сетевое имя сервера RabbitMQ
rmqqueue="queue-where-to-post"  				имя очереди, куда публикуем сообщения
rmqport=5672 
rmqusername=rmq_user 
rmqpassword="rmq_pass" 
filespath="\\server\Exchange\toRabbit" 
filemask="*.xml" 
rmqexchange="point.of.exchange" 				имя точки обмена
rmqcommonheader="Some header" 					заголовок сообщений для очереди
batchmode=sendfile								будет выполняться отправка файла из каталога в параметре filespath 

>> c:\rmq_test\sendresult.txt

##Общее.

На SQL-сервере нужно создать базу, а в ней таблицу для приема сообщений.
Это только для приема. Отправка сообщений выполняется без использования данной таблицы.

CREATE TABLE [dbo].[msg_buffer](
	[MsgCounter] [bigint] IDENTITY(1,1) NOT NULL,
	[MsgXMLText] [xml] NULL,
	[MsgVarCharMax] [nvarchar](max) NULL,
	[MsgHeader] [nvarchar](2000) NULL,
	[MsgID] [nvarchar](50) NULL,
	[MsgReceiveTime] [datetime] NULL,
	[MsgDescription] [nvarchar](2000) NULL,
	[MsgSource] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_msg_buffer] PRIMARY KEY CLUSTERED 
(
	[MsgCounter] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO