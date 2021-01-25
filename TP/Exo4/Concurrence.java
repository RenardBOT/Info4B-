//package TP.Exo3;
import java.util.*;

class Utilisateur extends Thread{
    public int solde;
    public Compte compte;

    public int montant; // Montant à retirer 1000 fois

    public Utilisateur(Compte c, int m){
        this.solde = 0;
        this.compte = c;
        this.montant = m;
    }

    public void Retirer(int q){

    }

    public void run(){
        for(int i = 0 ; i < 1000 ; i++){
            int soldeTemporaire = compte.solde;
            this.solde += this.montant;
            this.compte.solde = compte.solde - this.montant;
        }

    }
}

class Compte{
    public int solde;

    public Compte(int s){
        this.solde = s;
    }
}

public class Concurrence {
    public static void main(String args[]){
        Compte compte = new Compte(800000);
        System.out.println("Le compte contient 800.000");
        Utilisateur u1 = new Utilisateur(compte,10);
        Utilisateur u2 = new Utilisateur(compte,50);
        u1.start();
        u2.start();
        u1.join();
        u2.join();
        System.out.println("Le compte contient à la fin : "+compte.solde+"\nU1 possède : "+u1.solde+"\nU2 possède : "+u2.solde);
        
    }
}
