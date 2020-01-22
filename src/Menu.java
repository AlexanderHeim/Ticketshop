import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private int cstate = 0;
    private boolean inMenu = true;
    private Usermanager um = new Usermanager();
    private Shop sh = new Shop();
    public Menu() throws FileNotFoundException {

    }


    private void moveToMainMenu0(){
        System.out.println("------------------------------------");
        System.out.println("Enter /help to see all the commands!\n");
        this.cstate = 1;
    }

    private void mainMenu1(){
        String input = "";
        Scanner s = new Scanner(System.in);
        input = s.nextLine();
        if(input.equalsIgnoreCase("/help")){
            System.out.println("/help ..... show commands");
            System.out.println("/login ..... log into an account");
            System.out.println("/register .... register an account");
            System.out.println("/shop .... enter the shop");
        }
        if(input.equalsIgnoreCase("/login")){
            cstate = 2;
            inMenu = true;
        }
        if(input.equalsIgnoreCase("/register")){
            cstate = 3;
            inMenu = true;
        }
        if(input.equalsIgnoreCase("/shop")){
            cstate = 4;
        }
        if(input.equalsIgnoreCase("/whoami")){
            System.out.println("Your username: " + um.getCurrentUser());
        }
    }

    private void login2() throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        String name;
        String password;
        System.out.println("Username: ");
        name = s.nextLine();
        System.out.println("Password: ");
        password = s.nextLine();
        //Usermanager
        if(inMenu) cstate = 1;
        else cstate = 5;
        if(um.login(name, password)){
            System.out.println("Login successful");
        }
        else {
            System.out.println("Login failed!");
        }
    }

    private void register3() throws IOException {
        System.out.println("A new account will be created: ");
        Scanner s = new Scanner(System.in);
        String name = "";
        String password;
        do{
            System.out.println("Dein Name darf kein Leerzeichen enthalten");
            System.out.println("Username: ");
            name = s.nextLine();
        }while(name.contains(" "));
        name.trim();
        System.out.println("Password: ");
        password = s.nextLine();
        if(inMenu) cstate = 1;
        else cstate = 5;
        if(um.register(name, password)){
            System.out.println("Account successfully registered!");
        } else {
            System.out.println("Accountregistration failed!");
        }
    }

    private void toShop4(){
        System.out.println("You are now in the shop! Use /help to see shop commmands.");
        cstate = 5;
    }

    private void shop5() throws IOException {
        String input = "";
        Scanner s = new Scanner(System.in);
        input = s.nextLine();
        if(input.equalsIgnoreCase("/help")){
            System.out.println("/help ..... show commands");
            System.out.println("/login ..... login to an account");
            System.out.println("/register .... register an account");
            System.out.println("/items .... show all purchaseable items");
            System.out.println("/basket .... show all items in your basket");
            System.out.println("/buy .... buy an item");
        }
        if(input.equalsIgnoreCase("/login")){
            cstate = 2;
            inMenu = false;
        }
        if(input.equalsIgnoreCase("/register")){
            cstate = 3;
            inMenu = false;
        }
        if(input.equalsIgnoreCase("/items")){
            sh.printTicketList();
        }
        if(input.equalsIgnoreCase("/basket")){

        }
        if(input.equalsIgnoreCase("/buy")){
            System.out.println("Which ticket do you want to buy? (Enter the index)");
            int i = Integer.parseInt(s.nextLine());
            if(!(sh.getTicketList().size() > i && i > -1)) {
                System.out.println("Sorry, this ticket doesn't exist!");
                return;
            }
            um.addTicket(um.getCurrentUser(), sh.getTicketList().get(i).getKey());
            sh.removeTicketFromTicketlist(i);
            System.out.println("You bought a ticket!");
        }
        if(input.equalsIgnoreCase("/menu")){
            cstate = 1;
        }
    }


    public void startMenu() throws IOException {
        while(true){
            switch(cstate){
                case 0:
                    moveToMainMenu0();
                    break;
                case 1:
                    mainMenu1();
                    break;
                case 2:
                    login2();
                    break;
                case 3:
                    register3();
                    break;
                case 4:
                    toShop4();
                    break;
                case 5:
                    shop5();
                    break;
            }
        }
    }

}