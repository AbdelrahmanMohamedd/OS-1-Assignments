import java.util.Scanner;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

/**
 *
 * @author dell
 */
public class Network {

    public Device dev;
    public Network(){
        dev=new Device();


    }





    public void enterdata(){
        Scanner sc=new Scanner(System.in);
        int num=0;
        String name;
        System.out.println("What is number of WI-FI Connections?");
        num=sc.nextInt();
        dev.limitednumconnection=num;
        System.out.println("What is number of devices Clients want to connect?");
        num=sc.nextInt();
        name=sc.nextLine();
        for(int i=0;i<num;i++){
            name=sc.nextLine();
            dev.getdevices().add(name);

        }

    }
}