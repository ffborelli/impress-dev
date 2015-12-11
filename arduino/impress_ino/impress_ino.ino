    /*
     Publishing in the callback 
     
      - connects to an MQTT server
      - subscribes to the topic "inTopic"
      - when a message is received, republishes it to "outTopic"
      
      This example shows how to publish messages within the
      callback function. The callback function header needs to
      be declared before the PubSubClient constructor and the 
      actual callback defined afterwards.
      This ensures the client reference in the callback function
      is valid.
      
    */
    
    #include <SPI.h>
    #include <Ethernet.h>
    #include <PubSubClient.h>
    #include <stdio.h>
    #include <math.h>
    
    int analogPin=5;   // pino sensor temperatura
    int ledGreen=2;   // led
    int ledRed=3;
    float Vin=4.77;    // tensão alimentacao micro aproximada
    float Raux=10000;  // resistencia em serie com sensor
    float R0=9600;     // resistencia sensor a 25 graus
    float T0=298.15;   // temperatura em kelvin 25 graus
      
    float Vout=0.0;        // tensao apresentada no adc
    float Rout=0.0;        // resistencia do ntc ( calculada )
      
    float T1=273;          // temperatura em kelvin parametro 1 ponto 0 graus
    float T2=323;          // temperatura em kelvin paremetro 2 ponto 50 graus
    float RT1=21650;       // resistencia paramentro 1 ponto 0 graus
    float RT2=4350;        // resistencia paramentro 2 ponto 50 graus
      
    float beta=0.0;        // parametro beta
    float Rinf=0.0;        // [ohm]      Rinf parameter
    float TempK=0.0;       // temperatura em kelvin calculada
    float TempC=0.0;       // temperatura em graus  calculada
      
    
    // Update these with values suitable for your network.
    byte mac[]    = {  0xDE, 0xED, 0xBA, 0xFE, 0xFE, 0xED };
    //byte server[] = { 10, 0, 0, 102 };
    byte server[] = {192,168,0,101};
    byte ip[]     = {192,168,0,199};
    //byte ip[] = { 10, 0, 0, 110 };
    
    // Callback function header
    void callback(char* topic, byte* payload, unsigned int length);
    
    EthernetClient ethClient;
    PubSubClient client(server, 1883, callback, ethClient);
    
    int randNumber; 
    char message_buff[100];
    
    // Callback function
    void callback(char* topic, byte* payload, unsigned int length) {
      // In order to republish this payload, a copy must be made
      // as the orignal payload buffer will be overwritten whilst
      // constructing the PUBLISH packet.
      Serial.println("Message arrived:  topic: " + String(topic));
      // Allocate the correct amount of memory for the payload copy
      //byte* p = (byte*)malloc(length);
      // Copy the payload to the new buffer
      //memcpy(p,payload,length);
      //client.publish("impress/temperature", p, length);
      // Free the memory
      //free(p);
      
      
      
        int i = 0;
    
      //Serial.println("Message arrived:  topic: " + String(topic));
      //Serial.println("Length: " + String(length,DEC));
      
      // create character buffer with ending null terminator (string)
      for(i=0; i<length; i++) {
        message_buff[i] = payload[i];
      }
      message_buff[i] = '\0';
      
      String msgString = String(message_buff);
      
      //Serial.println("Payload: " + msgString);
      
      if (msgString.equals("0")) {
        
        Serial.println("OFF");
        digitalWrite(ledGreen, HIGH);   // turn the LED on (HIGH is the voltage level)
        delay(500);    // wait for a second
        digitalWrite(ledRed, LOW);    // turn the LED off by making the voltage LOW
        delay(500);   // wait for a second
        
      } else if (msgString.equals("1")) {
        
        Serial.println("ON");
        digitalWrite(ledRed, HIGH);   // turn the LED on (HIGH is the voltage level)
        delay(500);    // wait for a second
        digitalWrite(ledGreen, LOW);    // turn the LED off by making the voltage LOW
        delay(500);               // wait for a second
        
      } else {
        Serial.println("ERROR");

      }
      
    
    }
    
void setup()
{

    Serial.begin(9600);
    
      
    // inicializacao de pinos
      
    pinMode(analogPin, INPUT);  // pino sensor home
    pinMode(ledGreen, OUTPUT);
    pinMode(ledRed, OUTPUT);
        
    digitalWrite(ledGreen, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(500);    // wait for a second
    digitalWrite(ledRed, LOW);    // turn the LED off by making the voltage LOW
    delay(500);   // wait for a second
    
    //  parametros globais calculo temperatura
      
    beta=(log(RT1/RT2))/((1/T1)-(1/T2)); // calculo de beta
    Rinf=R0*exp(-beta/T0); 
      
    Ethernet.begin(mac, ip);
    if (client.connect("arduinoClient")) {
      //client.publish("impress/temeprature","23");
      //client.subscribe("impress/action");
      client.subscribe("impress/action");
    }
      
      
      
      
    }
    
    void loop()
    {
      
       
       //randNumber = random(18,30);
       //Serial.println(randNumber);
       //String str=String(randNumber); //converting integer into a string
       //char b[2];
       //str.toCharArray(b,4); 
       //if (client.connect("arduinoClient")) {
         
        calculate_sensor_temperature();               // calculo de temperatura
        Serial.println(TempC);
        delay(1000);                   // tempo para loop
        
        char buffer[10];
        dtostrf(TempC, 5, 2, buffer);

       client.publish("impress/temperature",buffer);
       //client.subscribe("impress/action");
       delay(5000);
       //Serial.println("published ...");
       //client.subscribe("impress/temeprature");
       client.loop();
      //}
      
    }
    
    // calculo de temperatura 
    
    void calculate_sensor_temperature()  
     
    {
        
      Vout=Vin*((float)(analogRead(analogPin))/1024.0);
      Rout=(Raux*Vout/(Vin-Vout));
    
      TempK=(beta/log(Rout/Rinf));
      TempC=TempK-273.15;
    
    }

