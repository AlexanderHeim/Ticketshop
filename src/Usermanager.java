import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Usermanager {

    private final String p = new String("C:\\Users\\Alex\\Desktop\\userdata.txt");
    private String infoSeperator = "<-<>->";
    private Scanner sc = new Scanner(new File(p));
    private static String currentUser = "guest";

    public String getCurrentUser(){
        return currentUser;
    }

    private void setCurrentUser(String s){
        currentUser = s;
    }

    public Usermanager() throws FileNotFoundException {

    }
    public boolean login(String name_, String password_) throws FileNotFoundException {
        sc = new Scanner(new File(p));
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            String[] parts = s.split(infoSeperator);
            String name = parts[0];
            String password = parts[1];

            if(name.equalsIgnoreCase(name_)){
                if(password.equals(password_)){
                    setCurrentUser(name);
                    sc.close();
                    return true;
                }
            }
        }
        sc.close();
        return false;
    }

    public boolean register(String name_, String password_) throws IOException {
        sc = new Scanner(new File(p));
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            String[] parts = s.split(infoSeperator);
            String name = parts[0];
            if(name.equalsIgnoreCase(name_)){
                sc.close();
                return false;
            }
        }
        Writer output = new BufferedWriter(new FileWriter(p, true));
        output.append(name_).append(infoSeperator).append(password_).append("\n");
        output.close();
        sc.close();
        return true;
    }

    public void addTicket(String name_, String key_) throws IOException {
        sc = new Scanner(new File(p));
        int count = -1;
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            String line = s;
            String[] parts = s.split(infoSeperator);
            String name = parts[0];
            count++;
            if(name.equals(name_)){
                line += infoSeperator;
                line += key_;
                sc = new Scanner(new File(p));
                String text = "";
                for(int i = 0; i < count; i++){
                    text += sc.nextLine();
                    text += "\n";
                }
                text += line;
                text += "\n";
                sc.nextLine();
                while(sc.hasNextLine()){
                    text += sc.nextLine();
                    text += "\n";
                }
                FileOutputStream writer = new FileOutputStream(p);
                writer.write((text).getBytes());
                writer.close();
                return;
            }
        }
    }

    public boolean removeTicket(String name_, String key_) throws IOException {
        sc = new Scanner(new File(p));
        int count = -1;
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            String newLine = s;
            String[] parts = s.split(infoSeperator);
            String name = parts[0];
            count++;
            if(name.equalsIgnoreCase(name_)){
                for(int i = 2; i < parts.length; i++){
                    if(parts[i].equals(key_)){
                        String n = newLine.replaceAll(key_, "");
                        int currentLine = 0;
                        String output = "";
                        sc = new Scanner(new File(p));
                        while(sc.hasNextLine()){
                            if(currentLine == count) break;
                            currentLine++;
                            output += sc.nextLine();
                            output += "\n";
                        }
                        sc.nextLine();
                        output += n;
                        output += "\n";
                        while(sc.hasNextLine()){
                            output += sc.nextLine();
                            output += "\n";
                        }
                        FileOutputStream writer = new FileOutputStream(p);
                        writer.write((output).getBytes());
                        writer.close();
                        return true;
                    }
                }
            }

        }

        sc.close();
        return false;
    }
    private void deleteData() throws IOException {
        FileOutputStream writer = new FileOutputStream(p);
        writer.write(("").getBytes());
        writer.close();
    }

}
