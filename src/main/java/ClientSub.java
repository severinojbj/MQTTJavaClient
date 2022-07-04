import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;

public class ClientSub implements MqttCallback{	
	
	public String topic;
	public String content;
    public int qos;
    public String broker;
    public String clientId; //Para cada classe, coloque um nome diferente
	
	public ClientSub () {
		this.topic        = "test";
		this.content      = "test_pub";
        this.qos          = 2;
        this.broker       = "tcp://localhost:1883";
        this.clientId     = "ClientSub";
	}
	
	public ClientSub (String host, int port) {
		this.topic        = "test";
		this.content      = "test_pub";
        this.qos          = 2;
        this.broker       = "tcp://" + host + ":" + String.valueOf(port);
        this.clientId     = "ClientSub";
	}
		
	//Metodo nao utilizado
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	//Metodo nao utilizado
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	//Metodo chamado quando ha uma nova mensagem num dos topicos inscritos 
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println (arg1.toString());
	}
	
	public void runSub () {		
        MemoryPersistence persistence = new MemoryPersistence();		
        try {
        	MqttClient sampleClient = new MqttClient(this.broker, this.clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            System.out.println("Connecting to broker: "+ this.broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");  
            sampleClient.subscribe (this.topic); 
            sampleClient.setCallback(this);
           
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
	}	
	
	public static void main (String [] args) {
		ClientSub client = new ClientSub("localhost", 1883);
		client.runSub();
	}
}