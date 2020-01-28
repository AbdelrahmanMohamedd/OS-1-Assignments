
package OS2;


public class Semaphore {
    public int value = 0 ;
    public Semaphore() { value = 0 ; }
    public Semaphore(int initial) { value = initial ; }
    public synchronized void P() {
        value-- ;
        if (value < 0)
            try { wait() ; } catch( InterruptedException e ) { }
    }
    public synchronized void V() {
        value++ ; if (value <= 0) notify() ;
    }

}
