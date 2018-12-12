import com.rabbitmq.client.Channel;

class QueueLocker
{
  QueueLocker() {}
  
  void LockQueue(String QueueName, Channel ConnectedChannel) {}
  
  void UnlockQueue(String QueueName, Channel ConnectedChannel) {}
}
