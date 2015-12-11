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
#include <stdio.h>
#include <math.h>
#include <LiquidCrystal.h>
#include <SPI.h>
#include <Ethernet.h>
#include <PubSubClient.h>
  
  LiquidCrystal lcd(9, 8, 7, 6, 5, 4);
  int analogPin=5;   // pino sensor temperatura
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
  byte ip[]     = {192,168,0,190};
 //byte ip[] = { 10, 0, 0, 110 };
  
          
 // Callback function header
 void callback(char* topic, byte* payload, unsigned int length);
    
 EthernetClient ethClient;
 PubSubClient client(server, 1883, callback, ethClient);
    
 char message_buff[100];
    
 // Callback function
 void callback(char* topic, byte* payload, unsigned int length) {
 }

  
  void setup() {
    
  // inicializacao serial
    
    Serial.begin(9600);   //velocidade     
    lcd.begin(16, 2);
    lcd.print("IMPReSS");
     
  // inicializacao de pinos
    
      pinMode(analogPin, INPUT);  // pino sensor home  
  
  //  parametros globais calculo temperatura
    
    beta=(log(RT1/RT2))/((1/T1)-(1/T2)); // calculo de beta
    Rinf=R0*exp(-beta/T0);               // calculo resistencia 
          
    Ethernet.begin(mac, ip);
    if (client.connect("arduinoClient2")) {
     //client.publish("impress/temeprature","23");
     //client.subscribe("impress/action");
      client.subscribe("impress/action");
    }
    
    
    
  }
  
    
  
  void loop()
  {
     
     sensor_home();               // calculo de temperatura
     //delay(1000);                   // tempo para loop
      
     char buffer[10];
     dtostrf(TempC, 5, 2, buffer);

     client.publish("impress/temperature",buffer);
     
//     Serial.println(float(TempC));
     lcd.setCursor(0, 1);
     lcd.print(float(TempC));
     
     delay(5000);
     //Serial.println("published ...");
     //client.subscribe("impress/temeprature");
     client.loop();
            
  }
  
  
  
  
  // calculo de temperatura 
  
     void sensor_home()  
   
  {
      
    Vout=Vin*((float)(analogRead(analogPin))/1024.0);
    Rout=(Raux*Vout/(Vin-Vout));
  
    TempK=(beta/log(Rout/Rinf));
    TempC=TempK-273.15;
  
  }
  

