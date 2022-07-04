import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClientPub {

	public String topic;
	public String content;
    public int qos;
    public String broker;
    public String clientId; //Para cada classe, coloque um nome diferente
    
    public ClientPub () {
    	this.topic        = "test";
		this.content      = "test_pub";
        this.qos          = 2;
        this.broker       = "tcp://localhost:1883";
        this.clientId     = "ClientSub";
    }
    
    public ClientPub (String host, int port) {
    	this.topic        = "test";
		this.content      = "test_pub";
        this.qos          = 2;
        this.broker       = "tcp://" + host + ":" + String.valueOf(port);
        this.clientId     = "ClientSub";
    }	
	
	public static void main (String [] args) {
		 ClientPub client = new ClientPub ("localhost", 1883);
         MemoryPersistence persistence = new MemoryPersistence();

         try {
             MqttClient sampleClient = new MqttClient(client.broker, client.clientId, persistence);
             MqttConnectOptions connOpts = new MqttConnectOptions();
             connOpts.setCleanSession(true);
             System.out.println("Connecting to broker: "+ client.broker);
             sampleClient.connect(connOpts);
             System.out.println("Connected");
             System.out.println("Publishing message: "+ client.content);
             MqttMessage message = new MqttMessage(client.content.getBytes());
             message.setQos(client.qos);
             sampleClient.publish(client.topic, message);
             System.out.println("Message published");
             sampleClient.disconnect();
             System.out.println("Disconnected");
             System.exit(0);
         } catch(MqttException me) {
             System.out.println("reason "+me.getReasonCode());
             System.out.println("msg "+me.getMessage());
             System.out.println("loc "+me.getLocalizedMessage());
             System.out.println("cause "+me.getCause());
             System.out.println("excep "+me);
             me.printStackTrace();
         }
	}
}
