package TP.Exo3;
import java.util.*;

class Coureur extends Thread{
    public void run(){
        int n = 1000; //Durée de la course
        System.out.println("Thread rentrant dans la course : "+Thread.currentThread().getName());
        for(int i=1;i<n;i++){
          try{
              Thread.sleep(Math.random()*100);
          }
       }
       System.out.println("Un thread a fini la course : "+Thread.currentThread().getName());
    }
 }

public class Exo3 {
    public static void main(String args[]){

        Thread t1 = new Coureur();
        t1.start();

    }
}
