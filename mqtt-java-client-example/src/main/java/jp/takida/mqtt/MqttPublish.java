package jp.takida.mqtt;

import java.util.concurrent.CountDownLatch;

public class MqttPublish {

    public MqttPublish() {
    }

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(Param.number_of_topics);

        SendToken s = new SendToken();
        
        s.setId(9999);
        s.setToken("START");
        s.sendToken();
        

        
        for (int i = 0; i < (Param.number_of_devices/3); i++) {

            Modelling se = null;
            Thread thread = null;
        
            se = new Modelling(3000, 3, 5, latch);
            thread = new Thread(se);
            thread.start();
            
            se = new Modelling(3000, 3, 6, latch);
            thread = new Thread(se);
            thread.start();
            
            se = new Modelling(3000, 3, 16, latch);
            thread = new Thread(se);
            thread.start();
            
//            se = new Modelling(3000, 3, 13, latch);
//            thread = new Thread(se);
//            thread.start();
//            //SBRC se = new SBRC(3, latch);
//            
//            se = new Modelling(3000, 3, 16, latch);
//            thread = new Thread(se);
//            thread.start();
//            
//            se = new Modelling(3000, 3, 16, latch);
//            thread = new Thread(se);
//            thread.start();


        }

        try {
            latch.await();  //main thread is waiting on CountDownLatch to finish
            System.out.println("Barrier....");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

//       s.sendToken("FINISH", 1000);
        System.out.println("Message sent");

    }

}
