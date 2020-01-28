import java.io.OptionalDataException;
import java.util.Vector;
import java.util.regex.Pattern;
public class  Parser
{

    Vector <String> args=new Vector();
    // Will be filled by arguments extracted by parse method
    String cmd; // Will be filled by the command extracted by parse method
    // Returns true if it was able to parse user input correctly. Otherwise false
// In case of success, it should save the extracted command and arguments
// to args and cmd variables
// It should also print error messages in case of too few arguments for a commands
// eg. “cp requires 2 arguments”
    public boolean parse(String input)
    {
       // boolean found = input . contains("|");
        //String [] pipe = input .split(Pattern.quote("|"));
        args.clear();
        boolean check = false;
            input = input.trim().replaceAll(" +", " ");  //remove duplicated spaces
            String [] arr = input .split(" ");
            for (int x=0; x<arr.length;x++)
            {
                if (x==0)
                {
                    cmd=arr[x];
                }
                else
                {
                    args.add(arr[x]);
                }
            }


      /*for (int i=0; i<args.size();i++)
      {
          System.out.println(args.get(i));
      }
      System.out.println(cmd);*/
     switch (cmd)
     {
         case "clear":
         case "date":
         case "pwd":
         case "help":
         case "exit":
         case "ls":
             if (args.size() == 0)
             {
                 check = true;
             }
             break;
         case "cd":
         case "rm":
             if (args.size() == 1)
             {
                check = true;
             }
             break;
         case "rmdir":
         case "mkdir":
         case "cat":
             if (args.size() >= 1)
             {
                 check = true;
             }
             break;
         case "cp":
         case "mv":
             if (args.size() == 2)
             {
                 check = true;
             }
             break;
         case "more":
             if (args.size() <= 2)
             {
                 check = true;
             }
             break;


         default:
            // throw new IllegalStateException("Unexpected value: " + cmd);
             check =  false;
     }

     if (!check)
     {
         System.out.print("COMMEND NOT FOUND! ");
     }

     else
     {
         System.out.print("OK ");
     }
     return check;
    }
    public String getCmd()
    {

       return cmd;
    }
    public Vector<String> getArguments()
    {
       return args;
    }
}
