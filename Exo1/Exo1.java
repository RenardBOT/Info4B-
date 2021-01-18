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
      Thread arrT[] = new Thread[10];
      for(int i = 0; i<10 ; i++)
     {
        arrT[i] = new MonThread1();
        arrT[i].start();
     } 
      System.out.println("Je suis le thread principal!");
   }
}
