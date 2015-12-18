package jp.takida.mqtt;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MqttPublish {

    private static final Random random = new Random();

    public MqttPublish() {
    }

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(Param.number_of_topics);

        SendToken s = new SendToken();
        s.sendToken("START", 9999);
        for (int i = 0; i < (Param.number_of_devices); i = i + 2) {

            Modelling se;
            Thread thread;

            se = new Modelling(getTimeBetweenEvents(), 5, i + 1, Param.number_of_messages, latch);
            thread = new Thread(se);
            thread.start();
            
            if (i + 2 <= Param.number_of_devices) {
                se = new Modelling(getTimeBetweenEvents(), 6, i + 2, Param.number_of_messages, latch);
                thread = new Thread(se);
                thread.start();

            }

            try {
                latch.await();  //main thread is waiting on CountDownLatch to finish
                //System.out.println("Barrier....");
            } catch (InterruptedException ie) {
            }

//        s.sendToken("FINISH", 1000);
//            System.out.println("Message sent");
        }
    }

    private static int getTimeBetweenEvents() {

        boolean check = true;
        int time = 0;

        do {

            time = random.nextInt(Param.maxTimeBetweenEvents);

            if (time > Param.minTimeBetweenEvents) {
                check = false;
            }

        } while (check);

        return time;
    }

}
