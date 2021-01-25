//package TP.Exo3;
import java.util.*;

class Coureur extends Thread{
    Classement c;

    public Coureur(Classement c){
        this.c = c;
    }

    public void run(){
        int n = 10000; //Durée de la course

        System.out.println("Thread rentrant dans la course : "+Thread.currentThread().getName());
        
          try{
              Thread.sleep((int)(Math.random()*n));
          }
          catch(InterruptedException e){e.printStackTrace();}
       
       System.out.println("Un thread a fini la course : "+Thread.currentThread().getName());
       this.c.ajout(Thread.currentThread().getName());
    }
}

class Classement{
    private String rank[];
    private int dernier;
    private int taille;

    public Classement(int taille){
        this.rank = new String[taille];
        this.taille = taille;
        this.dernier = 0;
    }

    public void afficher(){
        for(int i = 0 ; i < taille ; i++){
            System.out.println("Position : "+i+" - Thread #"+rank[i]);
        }
    }

    public synchronized void ajout(String s){
        rank[dernier] = s;
        dernier++;
    }
}

public class Exo3 {
    public static void main(String args[]){
        int participants = 5;

        Coureur arrT[] = new Coureur[participants];
        Classement ranking = new Classement(participants);

        System.out.println("DEBUT DE LA COURSE!!");

        for(int i = 0 ; i < participants ; i++){
            arrT[i] = new Coureur(ranking);
            arrT[i].start();
        }

        for(int i = 0 ; i < participants ; i++){
            try{
                arrT[i].join();
            }catch(InterruptedException e){e.printStackTrace();}
        }

        System.out.println("FIN DE LA COURSE!! Classement : ");

        ranking.afficher();
        
    }
}
