# MQTTJavaClient

Projeto Java contendo exemplos de publicador e assinante usando protocolo MQTT.

Para executar os clientes, é preciso ter o Apache Maven instalado. Assim, a bilioteca
Paho Mqtt é baixada e instalada corretamente. 
Com o Maven instalado e adicionado ao path, passo para instalação do Paho MQTT
é executar o seguinte comando no terminal (dentro da pasta raíz do projeto):

mvn install

Após o comando, as classes poderão ser executadas. No Eclipse, 
com o plugin M2E instalado, clique com o botão direito no código da classe,
opção Run -> Java Application. Faça isso primeiro com a classe ClientSub, depois 
com a ClientPub.