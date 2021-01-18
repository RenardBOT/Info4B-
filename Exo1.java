class MonThread1 extends Thread{
   public void run(){
      for(int i=1;i<5;i++){
         System.out.println("je suis le thread "+"i="+i);
      }
   }
}

class MonThread2 implements Runnable{
   public void run(){
      for(int i=1;i<5;i++){
         System.out.println("je suis le thread "+"i="+i);
      }
   }
}

public class Exo1{
   public static void main(String args[]){
      Thread tr1 = new MonThread1();
      tr1.start();
      Runnable runner = new MonThread2();
      Thread tr2 = new Thread(runner);
      tr2.start();
      System.out.println("Je suis le thread principal");
   }
}
