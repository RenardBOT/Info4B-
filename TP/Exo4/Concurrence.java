//package TP.Exo3;
import java.util.*;
import java.util.concurrent.locks.*;

class Utilisateur extends Thread{
    public int solde;
    public Compte compte;

    public int montant; // Montant Ã  retirer 1000 fois

    public Utilisateur(Compte c, int m){
        this.solde = 0;
        this.compte = c;
        this.montant = m;
    }

    public void Retirer(int q){

    }

    public void run(){
        for(int i = 0 ; i < 1000 ; i++){
            int r = this.compte.retrait(this.montant);
            this.solde += r;
        }

    }
}

class Compte{
    private int solde;
    Lock l = new ReentrantLock();


    public int getSolde(){return this.solde;}
    public void setSolde(int s){this.solde = s;}

    public int retrait(int r){
        l.lock();
        int res = 0;
        if(r <= this.getSolde()){
            this.setSolde(this.getSolde()-r);
            res = r;
        }
        l.unlock();
        return res;
    }

    public Compte(int s){
        this.setSolde(s);
    }
}

public class Concurrence {
    public static void main(String args[]){
        for(int i = 1 ; i<=5 ; i++)
        { 
            System.out.println("ESSAI#"+i);
            Compte compte = new Compte(800000);
            System.out.println("Le compte contient 800.000");
            Utilisateur u1 = new Utilisateur(compte,10);
            Utilisateur u2 = new Utilisateur(compte,50);
            u1.start();
            u2.start();
            try{u1.join();}catch(InterruptedException e){e.printStackTrace();}   
            try{u2.join();}catch(InterruptedException e){e.printStackTrace();} 
            System.out.println("Le compte contient a  la fin : "+compte.getSolde()+"\nU1 possede : "+u1.solde+"\nU2 possede : "+u2.solde+"\n---------------");
        } 
    }
}
