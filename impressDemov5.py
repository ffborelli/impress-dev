# -*- coding: utf-8 -*-
# Author: Ivan Dimitry - UFABC
# Program : Raspberry Pi B+ with ultrasonic sensor via MQTT

#load libraries
import RPi.GPIO as GPIO
import time
import paho.mqtt.client as client
from struct import unpack
import time
from threading import Thread


# -----------------------
# Define some functions
# -----------------------

def measure():
  # This function measures a distance
  print "measuring... "
  GPIO.output(GPIO_TRIGGER, True)
  time.sleep(0.00001)
  GPIO.output(GPIO_TRIGGER, False)
  start = time.time()
  stop = time.time()
  while GPIO.input(GPIO_ECHO)==0:
    start = time.time()
  while GPIO.input(GPIO_ECHO)==1:
    stop = time.time()
  elapsed = stop-start
  distance = (elapsed * 34300)/2
  print "distance: %d" % distance
  return distance
# -----------------------
# Main Script
# -----------------------

# Use BCM GPIO references
# instead of physical pin numbers
GPIO.setmode(GPIO.BCM)

# Define GPIO to use on Pi
GPIO_TRIGGER = 2
GPIO_ECHO    = 3
ACTUATOR = 19
#print "Ultrasonic Measurement"

# Set pins as output and input
GPIO.setup(GPIO_TRIGGER,GPIO.OUT)  # Trigger
GPIO.setup(GPIO_ECHO,GPIO.IN)      # Echo
# Set trigger to False (Low)
GPIO.output(GPIO_TRIGGER, False)

GPIO.setup(ACTUATOR,GPIO.OUT)
GPIO.output(ACTUATOR, False)

#broker configurations
IP = '192.168.0.103'
PORT = 1884
TOPIC_ACT = "impress/demo"
TOPIC_SUB = "impress/action"
CLIENT_NAME = 'RASPBERRY'

 # Connect to MQTT broker.
client = client.Mosquitto(CLIENT_NAME)
client.connect(IP,PORT,60)


class Th(Thread):

        def __init__ (self, num):
                Thread.__init__(self)
                self.num = num
                def on_connect(client, data, rc):
                    client.subscribe(TOPIC_SUB)
                   print("Connection returned result: " + str(rc))
                def on_message(client, userdata, msg):
                    message = str(msg.payload)
                    arrMessage = message.split(";")
                    print(arrMessage[0]);

                    if arrMessage[0] == "2" and arrMessage[1] == "8" and arrMes$
                        GPIO.output(7,0)
                        print("turn on")

                    elif arrMessage[0] == "2" and arrMessage[1] == "8" and arrM$
                        GPIO.output(7,1)
                        print("turn off")
                    else: print("is not a message for this actuator")

                def on_publish(mosq, obj, mid):
                   print ("Publish message " + str(mid))

                def on_subscribe(mosq, obj, mid,qos):
                   print("Subcribe " + str(mid) + " " + str(qos))

                # estabelece as funçõe de conexão e mensagens
                client.on_connect = on_connect
                client.on_message = on_message
                client.on_publish = on_publish
                client.on_subscribe = on_subscribe

        def run(self):

                print self.num
                client.loop_forever()

def readUltrasonicSensors():
   while(1):

      distance = measure()
      if distance < 30:
         client.publish(TOPIC_ACT, "1;1;1")
      else:
         client.publish(TOPIC_ACT, "1;1;0")
      time.sleep(1)


init = Th(1)
init.start()

tUltra = Thread (target=readUltrasonicSensors, args = () )
tUltra.start()






