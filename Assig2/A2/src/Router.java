


        import java.util.logging.Level;
        import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class Router extends Thread {

    public Network n=new Network();
    public Router(Network n) {

        this.n=n;
    }

    public void run (){

        Device D=new Device();
        try {
            D.Arrived(n);
        } catch (InterruptedException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }


    }



}
