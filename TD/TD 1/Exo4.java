/* EXO 4 du TD */

class Compteur extends Thread{
    private volatile boolean arret = false;

    public void run(){
        int i = 0;
        while(arret == false){
            i++;
            //System.out.println(i);
        }
    }
    
    public void stopper(){
        arret = true;
    }
}

public class Exo4{
    public static void main (String[] args){
        Compteur c = new Compteur();
        c.start();

        //lecture
        try{
            Thread.sleep(Integer.parseInt(args[0]));
        }catch(Exception e){e.printStackTrace();}
        c.stopper();
        System.out.println("Fin");
    }
}