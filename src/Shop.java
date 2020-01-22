import java.util.ArrayList;

public class Shop {

    private ArrayList<Ticket> ticketlist = new ArrayList<Ticket>();

    public Shop(){
        ticketlist.add(new Ticket("AREA 51 EVENT 1990", 160, 1, 1, "A"));
        ticketlist.add(new Ticket("Bruharena 2013", 13, 16, 12, "B"));
        System.out.println(ticketlist.get(0).getKey());
    }

    public ArrayList<Ticket> getTicketList(){
        return this.ticketlist;
    }

    public void printTicketList(){
        for(int i = 0; i < ticketlist.size(); i++){
            System.out.println(i + ". Ticket: " + ticketlist.get(i).getName() + " | Price: " + ticketlist.get(i).getPrice() + "€ | Sector: " + ticketlist.get(i).getSector() + " | Row: " + ticketlist.get(i).getRow() + " | Seat: " + ticketlist.get(i).getSeat());
        }
    }

    public boolean removeTicketFromTicketlist(int index){
        if(index >= 0 && index < ticketlist.size()){
            ticketlist.remove(index);
            return true;
        }
        return false;
    }
}
