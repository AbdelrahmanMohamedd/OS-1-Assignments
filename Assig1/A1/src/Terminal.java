import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;

public class Terminal {
    //private static boolean default_path;
    String default_path = System.getProperty("user.dir") + "\\";

    public static void clear() {
        String default_path = System.getProperty("user.dir") + "\\";
        for (int i = 0; i < 50; i++) {
            System.out.println(" ");
        }
        System.out.println(default_path);
    }

    public static String date() {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myformaObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myformaObj);
        System.out.println("After formatting: " + formattedDate);
        return formattedDate;
    }

    public static void help() {
        System.out.println("clear + noArgs : Clear the screen");
        System.out.println("pwd + noArgs : Display current user director");
        System.out.println("date + noArgs : Display or Set the date and time");
        System.out.println("rm + SourcePath : Remove each specified file");
        System.out.println("mv + SourcePath and destinationPath : Move each other given file into a file with the same name in that directory");
        System.out.println("rmdir + sourcePath : remove each given empty directory");
        System.out.println("mkdir + sourcePath : Creates a directory with each given name");
        System.out.println("more + sourcePath : Display and scroll down the output in one direction only");
        System.out.println("cat + Arguments : Display files");
        System.out.println("cp + sourcePath and destinationPath : Change the current directory to another one");
        System.out.println("ls + noArgs : list each given file or directory name");
        System.out.println("cd + Arguments : Change the currant directory to another one");
        System.out.println("exit + noArgs : Stop all");
    }

    public void ls() { // list

        try {
            String Direction = pwd();  //call function PWD to know the current direction.
            File f = new File(Direction);
            String[] files = f.list();
            System.out.println("Files Are:");
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i]); // print the content of folder.
            }
        } catch (Exception e) {
            System.out.println("Error The Exception iS " + e);
        }
    }

    public void mkdir(Vector sourcePath) {
        //System.out.println(default_path);
        if (!sourcePath.contains(":")) sourcePath.add(default_path + "\\" + sourcePath);

        if (sourcePath.get(0) == "") {
            System.out.println("Error!...Insert a parameters for mkdir command ");
            return;
        }
        File myfile = new File((String) sourcePath.get(0));
        myfile.mkdir();
        System.out.println("lololololyyyyy");
    }

    public void rmdir(Vector sourcePath) {
        //System.out.println(default_path);
        if (!sourcePath.contains(":")) sourcePath.add(default_path + "\\" + sourcePath);

        File myfile = new File((String) sourcePath.get(0));
        if (sourcePath.get(0) == "") {
            System.out.println("Error!... Insert a parameter for rmdir command ");
            return;
        }
        if (myfile.length() == 0 && myfile.delete()) {
            System.out.println("The Directory is Deleted successfully");
        } else System.out.println("No such Directory");
    }

    /*public void mv(String sourcePath, String destinationPath) {
        System.out.println(default_path);
        if (!sourcePath.contains(":")) {
            sourcePath = default_path + "\\" + sourcePath;
        }
        if (!destinationPath.contains(":")) {
            destinationPath = default_path + "\\" + destinationPath;
        }

        if (sourcePath == "" || destinationPath == "") {   //TESTING
            System.out.println("Error!... Insert two parameters for mv command ");
            return;
        }
        File file = new File(sourcePath);
        File file1 = new File(destinationPath);


       if(file.renameTo(new File(destinationPath)))   // renaming the file and moving it to a new location
        {
            file.delete(); // if file copied successfully then delete the original file
            System.out.println("File moved successfully");
        }
        else
        {
            System.out.println("Failed to move the file");
        }
      //  System.out.println(default_path);
    }*/
   /* public void mv(String sourcePath, String destinationPath) throws IOException {
        if (sourcePath == "" || destinationPath == "")
        {   //TESTING
            System.out.println("Error!... Insert two parameters for mv command ");
            return;
        }
        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);
        boolean sourceexists = sourceFile.exists();
        boolean destinationexists = destinationFile.exists();

        if(sourceexists && destinationexists)  { // check an l file ra7 al Dest
            Files.move(Paths.get(sourcePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully");
        }
        else
        {
            System.out.println("Failed to move the file");
        }
    }*/
    public void mv(String sourcePath, String destinationPath)
    {
        cp(sourcePath,destinationPath);
        rm(sourcePath);
    }

    public void rm(String sourcePath) {
        //System.out.println(default_path);
        if (!sourcePath.contains(":")) {
            sourcePath = default_path + "\\" + sourcePath;
        }
        File myfile = new File(sourcePath);
        if (sourcePath == "") {
            System.out.println("Error!... Insert a parameter for rm command ");
            return;
        }
        if (myfile.delete()) System.out.println("The Directory is Deleted successfully");
        else System.out.println("No such Directory");
    }

    public static String pwd() {
        String pwd = System.getProperty("user.dir");
        System.out.println(pwd);
        return pwd;
    }

    public void cat(Vector v) throws FileNotFoundException, IOException {

        File directory = new File("D:\\folder3");

        PrintWriter write = new PrintWriter("concat.txt");  // new file to write in it.

        String[] fileNames = directory.list(); //put files founded in array

        for (int i = 0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            System.out.println("File_Name: " + fileName);

            File f = new File(directory, fileName); //object from class file.
            //take the list in fileName and put it in directory.
            BufferedReader read = new BufferedReader(new FileReader(f)); // To read

            String Reading_line = read.readLine();   // Read the current direction.
            while (Reading_line != null) {

                write.println(Reading_line);   // write to the output file
                Reading_line = read.readLine();
            }
            write.flush(); //makes the buffer empty but does not closes the stream permanently
        }
        System.out.println("Done");
    }
    public static void cp(String sourcePath , String destinationPath ){
        String default_path = System.getProperty("user.dir") + "\\";
        if (!sourcePath.contains(":")) {
            sourcePath = default_path + "\\" + sourcePath;
        }
        else if (!destinationPath.contains(":")) {
            destinationPath = default_path + "\\" + destinationPath;
        }
        File src = new File(sourcePath);
        File tar = new File(destinationPath);
        if(src.isDirectory() && !tar.exists()){
            tar.mkdir();
        }
        if (src.isDirectory()) {
            File[] listOfFiles = src.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                cp(sourcePath + "\\" + listOfFiles[i].getName() , destinationPath + "\\" + listOfFiles[i].getName());
            }
        } else {
            try (
                    InputStream in = new FileInputStream(sourcePath);
                    OutputStream out = new FileOutputStream(destinationPath)
            ) {
                byte[] buf = new byte[1024];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                System.out.println("Error file not found");
            } catch (IOException e) {
                System.out.println("Error file not found");
            }
        }
    }

    public void more(String sourcePath) {
        if (!sourcePath.contains(":")) {
            sourcePath = default_path + "\\" + sourcePath;
        }
        try {
            File file = new File(sourcePath);
            int c = 10;
            Scanner in = new Scanner(file), userInput = new Scanner(System.in);
            while (c > 0 && in.hasNextLine()) {
                System.out.println(in.nextLine());
                c--;
            }
            char reply;
            while (in.hasNextLine()) {
                System.out.println("more... ? -> ENTER ( Y or N ) ?");
                //System.out.print("How many lines you want ENTER ( 1...or 3...or 5...or 7...or 10 ");
                reply = userInput.next().charAt(0);
                if (reply == 'N'||reply == 'n')
                    break;
                c = 5;
                while (c > 0 && in.hasNextLine()) {
                    System.out.println(in.nextLine());
                    c--;
                }
               // System.out.println(in.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error!... " + e);
        }
    }
   /* public void cat(Vector paths) throws IOException {
       // System.out.println(default_path);
        //if (!paths.contains(":")) paths.add(default_path + "\\" + paths);
//Vector v =new Vector();
        //if(paths.size()== 1){
        PrintWriter out = new PrintWriter("concat.txt");

        //for (int i=0;i<paths.size();i++){
                File fileReader = new File(String.valueOf(paths));
                BufferedReader in = new BufferedReader (new FileReader( fileReader));

                System.out.println("askdjnkasjd");

               // FileReader fileReader = new FileReader((File) paths.get(0));
                String line;
                while((line = in.readLine())!= null){
                    //System.out.println(line);
                    out.println(line);
                    line = in.readLine();
                }
                out.flush();
            }*/
    //}
    //
    public void cd(Vector args) {
        System.out.println(default_path);
        if (!args.contains(":")) args.add(default_path + "\\" + args);
        if (args.size() == 1) {
            File dir = new File((String) args.get(0));
            if (!dir.isDirectory()) {
                System.out.println(args.get(0) + "is not a directory.");
            } else {
                System.setProperty("user.dir", dir.getAbsolutePath());
            }
        }
    }

    // Add any other required command in the same structure…..
    public static void redirection_one_operator(String input) {
        String cmd = null;
        String filename = null;
        input = input.trim().replaceAll(" +", " ");  //remove duplicated spaces
        String[] arr = input.split(" ");
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                cmd = arr[i];
            } else if (i == arr.length - 1) {
                filename = arr[i];
            }
        }
        try {
            File myObj = new File(filename);
            FileOutputStream fos = new FileOutputStream(myObj);
            BufferedWriter myWriter = new BufferedWriter(new OutputStreamWriter(fos));
            switch (cmd) {
                case "pwd":
                    myWriter.write(pwd());
                    myWriter.close();
                    break;
                case "help":
                    myWriter.write("clear + noArgs : Clear the screen");
                    myWriter.newLine();
                    myWriter.write("pwd + noArgs : Display current user director");
                    myWriter.newLine();
                    myWriter.write("date + noArgs : Display or Set the date and time");
                    myWriter.newLine();
                    myWriter.write("rm + SourcePath : Remove each specified file");
                    myWriter.newLine();
                    myWriter.write("mv + SourcePath and destinationPath : Move each other given file into a file with the same name in that directory");
                    myWriter.newLine();
                    myWriter.write("rmdir + : remove each given empty directory");
                    myWriter.newLine();
                    myWriter.write("mkdir + : Creates a directory with each given name");
                    myWriter.newLine();
                    myWriter.write("more + : Display and scroll down the output in one direction only");
                    myWriter.newLine();
                    myWriter.write("cat + Arguments : Display files");
                    myWriter.newLine();
                    myWriter.write("cp + sourcePath and destinationPath : Change the current directory to another one");
                    myWriter.newLine();
                    myWriter.write("ls + noArgs : list each given file or directory name");
                    myWriter.newLine();
                    myWriter.write("cd + Arguments : Change the currant directory to another one");
                    myWriter.newLine();
                    myWriter.write("exit + noArgs : Stop all");
                    myWriter.close();
                    break;
                case "cat":
                    String text = in.nextLine();
                	  myWriter.write(text);
                      myWriter.close();
	            case "ls":
	             try {
            String currentDirect = pwd();
            File f = new File(currentDirect);
            String[] files = f.list();  // take all files in directory
            myWriter.write("Files Are:");
            myWriter.newLine();
            for (int i = 0; i < files.length; i++) {
                myWriter.write(files[i]);
            }
        } catch (Exception e) {
            System.out.println("Error The Exeption iS " + e);
        }
                      myWriter.close();
                      break;
                case "date":
                    myWriter.write(date());
                    myWriter.close();
                    break;
                default:
                    System.out.println("An error occurred.");

            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void redirection_two_operator(String input) {
        String cmd = null;
        String filename = null;
        Scanner in = new Scanner(System.in);

        input = input.trim().replaceAll(" +", " ");  //remove duplicated spaces
        String[] arr = input.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                cmd = arr[i];
            } else if (i == arr.length - 1) {
                filename = arr[i];
            }
        }
        try {
            File file = new File(filename);
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter myWriter = new BufferedWriter(fr);
            switch (cmd) {
                case "pwd":
                    myWriter.write(pwd());
                    myWriter.newLine();
                    myWriter.close();
                    fr.close();
                    break;
                case "help":
                    myWriter.write("clear + noArgs : Clear the screen");
                    myWriter.newLine();
                    myWriter.write("pwd + noArgs : Display current user director");
                    myWriter.newLine();
                    myWriter.write("date + noArgs : Display or Set the date and time");
                    myWriter.newLine();
                    myWriter.write("rm + SourcePath : Remove each specified file");
                    myWriter.newLine();
                    myWriter.write("mv + SourcePath and destinationPath : Move each other given file into a file with the same name in that directory");
                    myWriter.newLine();
                    myWriter.write("rmdir + : remove each given empty directory");
                    myWriter.newLine();
                    myWriter.write("mkdir + : Creates a directory with each given name");
                    myWriter.newLine();
                    myWriter.write("more + : Display and scroll down the output in one direction only");
                    myWriter.newLine();
                    myWriter.write("cat + Arguments : Display files");
                    myWriter.newLine();
                    myWriter.write("cp + sourcePath and destinationPath : Change the current directory to another one");
                    myWriter.newLine();
                    myWriter.write("ls + noArgs : list each given file or directory name");
                    myWriter.newLine();
                    myWriter.write("cd + Arguments : Change the currant directory to another one");
                    myWriter.newLine();
                    myWriter.write("exit + noArgs : Stop all");
                    myWriter.newLine();
                    myWriter.close();
                    fr.close();
                    break;
            case "cat":
                  String text=in.nextLine();
            	  myWriter.write(text);
            	  myWriter.newLine();
                  myWriter.close();
                  fr.close();
                  break;
            case "ls":
            	  try {
            String currentDirect = pwd();
            File f = new File(currentDirect);
            String[] files = f.list();  // take all files in directory
            myWriter.write("Files Are:");
            myWriter.newLine();
            for (int i = 0; i < files.length; i++) {
                myWriter.write(files[i]);
            }
        } catch (Exception e) {
            System.out.println("Error The Exeption iS " + e);
        }
            	  myWriter.newLine();
                  myWriter.close();
                  fr.close();
                  break;
                case "date":
                    myWriter.write(date());
                    myWriter.newLine();
                    myWriter.close();
                    fr.close();
                    break;
                default:
                    System.out.println("An error occurred.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

// Add any other required command in the same structure…..
}

