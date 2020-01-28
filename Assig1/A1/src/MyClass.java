//import javax.swing.text.html.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class MyClass {
    public static void  main(String[] args) throws IOException {
        Parser P = new Parser (); ///object from class parser.
        Terminal t = new Terminal (); ///object from class Terminal.
        Scanner in = new Scanner(System.in);
        System.out.println("ENTER YOUR TEXT");
        String input;
       // File file1, file2;
        String check_cmd , file_1 , file_2 ;
        Vector<String> get_Args =new Vector();
        check_cmd = P.getCmd(); //to get the name of commend .
        get_Args = P.getArguments(); //to get the arguments of the commend .
        boolean test;
        while (true)
        {
            input = in.nextLine();
            if (input.contains(">>"))
            {
                t.redirection_two_operator(input);
            }
            else if (input.contains(">"))
            {
                t.redirection_one_operator(input);
            }
            else

            {
                String[] pipe = input.split(Pattern.quote("|"));
                for (int i = 0; i < pipe.length; i++) {
                    test = P.parse(pipe[i]);
                    System.out.println(test); ///call function Parse.

                    if (test) {

                        if (P.getCmd().matches("clear")) {
                            //System.out.println("lu");
                            t.clear();
                        } else if (P.getCmd().matches("pwd")) {
                            t.pwd();
                        }else if (P.getCmd().matches("ls")) {
                            t.ls();
                        } else if (P.getCmd().matches("help")) {
                            t.help();
                        } else if (P.getCmd().matches("cat")) {
                            t.cat(get_Args);
                        } else if (P.getCmd().matches("exit")) {
                            System.exit(0);
                        } else if (P.getCmd().matches("cp")) {
                            file_1 = get_Args.get(0);
                            file_2 = get_Args.get(1);
                            t.cp(file_1,file_2);
                        } else if (P.getCmd().matches("cd")) {
                            t.cd(get_Args);
                        } else if (P.getCmd().matches("mkdir")) {
                            t.mkdir(get_Args);
                        } else if (P.getCmd().matches("rmdir")) {
                            t.rmdir(get_Args);
                        }else if (P.getCmd().matches("mv")) {
                            file_1 = get_Args.get(0);
                            file_2 = get_Args.get(1);
                            t.mv(file_1,file_2);
                        } else if (P.getCmd().matches("more")) {
                            file_1 = get_Args.get(0);
                            //file_2 = get_Args.get(1);
                            t.more(file_1);
                        }else if (P.getCmd().matches("rm")) {
                            file_1 = get_Args.get(0);
                            t.rm(file_1);
                        }
                        System.out.println(P.getCmd());  ///print the command.
                        System.out.println(P.getArguments()); ///print the Arguments.
                    }
                }
            }
        }

        /*
            input = in.nextLine();

        */
       /// System.out.println("hello world!");
    }


}
