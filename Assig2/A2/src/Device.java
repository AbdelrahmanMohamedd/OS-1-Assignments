

import OS2.Semaphore;

import java.util.ArrayList;
import java.util.List;

        import static java.lang.Thread.sleep;
        import java.util.ArrayList;
        import java.util.List;

/**
 *
 * @author dell
 */
public class Device extends Thread{

    public List<String> listofdevices;
    public List<String>temp;
    public int limitednumconnection;
    public int counter=0;

    public int countlog=0;
    public Device() {
        listofdevices=new ArrayList<>();
        temp=new ArrayList<>();

    }

    public List<String> getdevices(){

        return listofdevices;

    }



    public synchronized void Arrived(Network d) throws InterruptedException{
        Semaphore s=new Semaphore(d.dev.getdevices().size()+d.dev.limitednumconnection);


        for(int i=0;i<d.dev.getdevices().size();i++){
            if(s.value>d.dev.getdevices().size()){
                System.out.println(d.dev.getdevices().get(i)+" "+"arrived");

                s.P();
                sleep(1000);
                counter++;
            }
        }
        for(int i=counter;i<d.dev.getdevices().size();i++){
            System.out.println(d.dev.getdevices().get(i)+" "+"arrived and waiting");
            temp.add(d.dev.getdevices().get(i));
            sleep(1000);
        }
        ocup(d);

    }

    public synchronized void ocup(Network d) throws InterruptedException{
        Semaphore s=new Semaphore(d.dev.getdevices().size()+d.dev.limitednumconnection);


        for(int i=0;i<d.dev.getdevices().size();i++){
            if(s.value>d.dev.listofdevices.size()){
                System.out.println(d.dev.getdevices().get(i)+" "+"occupied");
                s.P();
                sleep(1000);

            }
        }
        perform(d);


    }
    public synchronized void perform(Network d) throws InterruptedException{
        Semaphore s=new Semaphore(d.dev.getdevices().size()+d.dev.limitednumconnection);


        for(int i=0;i<d.dev.getdevices().size();i++){
            if(s.value>d.dev.listofdevices.size()){
                System.out.println(d.dev.getdevices().get(i)+" "+"perform online activity");
                s.P();
                sleep(1000);

            }
        }
        logout(d);


    }


    public synchronized void check(Network d,List<String>l) throws InterruptedException{
        int j=0;


        if(temp.size()==0){
            logout(d);
        }
        else{
            System.out.println(l.get(j)+" "+"occupied");
            l.remove(j);

            sleep(1000);

            logout(d);

        }





    }


    public synchronized void logout(Network d) throws InterruptedException{



        if(countlog<d.dev.limitednumconnection){
            System.out.println(d.dev.getdevices().get(0)+" "+"logout");


            d.dev.getdevices().remove(0);
            countlog++;

            sleep(1000);
            check(d,temp);


        }
        else{
            if(temp.size()==0){

                return;
            }
            else{
                countlog=0;
                System.out.println(d.dev.listofdevices.size());
                perform(d);

            }

        }



    }
}
