import java.util.*;


/**Le probleme dit Sleeping-Barber Problem est un exemple classique, il illustre les probleemes
d'acces concurrents aux ressources et la synchronisation des processus. On considere un
salon de coiffure qui comporte une salle d'attente avec n chaises et une autre salle avec
un fauteuil et un coiffeur. Si il n'y a pas de client, le coiffeur dort. Si un client entre et
que toutes les chaises sont occuppees, le client s'en va. Si le coiffeur est occuppe et qu'au
moins une chaise est libre le client s'assied et attend. Si le coiffeur est endormi l'arrivee
d'un client le reveille.

1. On souhaite programmer l'activite du coiffeur et le fonctionnement du systeme au
moyen de threads. Identifer les classes qui constituent des ressources et les classes
qui donneront naissance a des threads.
2. Y-a-t'il des probleemes de concurrence ? Si oui expliquez de facon precise la methode
que vous choisissez pour les eviter ?
3. Ecrire les differentes classes du programme et la methode main().
4. On considere non plus 1 coiffeur mais 4 coiffeurs, quels sont les elements a changer
dans votre programme ?*/


public class Exo10{
    public static void main (String[] args){
        SalleAttente s = new SalleAttente(10);

        Barber b = new Barber(s);
        b.start();

        SimulArriveeClient simul = new SimulArriveeClient(s,25);
        simul.start();

        // On attend la fin de la simulation et que la salle d'attente soit vide pour arrêter le Coiffeur.
        try{
            simul.join();
        }catch(InterruptedException e){e.printStackTrace();}
        while(s.getTaille() != 0){
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){e.printStackTrace();}

        }
        b.arret();
    }
}


class Client{
    private int id; // Identifiant du client
    public Client (int id){
        this.id=id;
    }
}


class SalleAttente{
    private int chaises = 0; // Nombre de places maximum
    private boolean isOuvert ;
    private LinkedList<Client> liste;

    public SalleAttente(int max){
        isOuvert = true;
        this.chaises = max;
        this.liste = new LinkedList<Client>();
    }

    public int ajouter(Client c){
        if(this.getTaille() < this.chaises){
            liste.addLast(c);
            notifyAll();
            return 0;
        }
        else{return -1;}
    }

    public Client retirer(){

        // Si la salle est vide, le coiffeur dort. Il attend un client. wait() ici, notify() dans ajouter(c). 
        while (this.getTaille()==0){
            System.out.println("Attente ... zzz . . .");
            try{
                wait();
            }catch(InterruptedException e){e.printStackTrace();}
        }

        //Quand la salle se remplit, donc de taille au moins 1, on retire le premier client de la salle d'attente.
        return liste.removeFirst();
    }

    public int getTaille(){
        return this.liste.size();
    }
}


class Barber extends Thread{
    private SalleAttente s;
    private volatile boolean arret;
    private int nbClientsTraites;

    public Barber(SalleAttente s){
        this.s = s;
        arret = false;
        nbClientsTraites = 0;
    }

    public void run(){
        while(!arret){
            s.retirer();
            nbClientsTraites++;
            System.out.println("Le coiffeur coiffe!");            
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    public void arret(){
        this.arret = true;
    }
}

class SimulArriveeClient extends Thread{
    private int nbClients;
    private SalleAttente s;
    private int nbClientsPartis;

    public SimulArriveeClient(SalleAttente s, int nbClients){
        this.nbClients = nbClients;
        this.s = s;
        nbClientsPartis = 0;
    }

    public void run(){
        for(int i = 0 ; i < nbClients ; i++){
            System.out.println("Il y a "+s.getTaille()+" dans la salle.");
            if(s.ajouter(new Client(i))==0)
                System.out.println("Le client #"+i+" entre dans la salle");
            else{
                System.out.println("La salle est pleine! Le client "+i+" repart...");
                nbClientsPartis++;
            }
            
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){e.printStackTrace();}
        }
        System.out.println("Nombre de clients arrivés : "+this.nbClients);
        System.out.println("Nombre de clients repartis : "+this.nbClientsPartis);
    }
}

