

public class Os2 extends Thread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Network n = new Network();
        n.enterdata();


        Router r=new Router(n);
        r.start();
        try{


            r.join();
        }
        catch(InterruptedException e){
            System.out.println("join interrupted");
        }
    }

}
