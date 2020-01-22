public class Ticket {
    private String name;
    private double price;
    private int seat;
    private int row;
    private String sector;
    private String key;
    public Ticket(String name_, double price_, int seat_, int row_, String sector_){
        this.name = name_;
        this.price = price_;
        this.seat = seat_;
        this.row = row_;
        this.sector = sector_;
        this.generateKey();
    }

    public String getName(){
        return this.name;
    }

    public String getSector(){
        return this.sector;
    }

    public double getPrice(){
        return this.price;
    }

    public int getSeat(){
        return this.seat;
    }

    public int getRow(){
        return this.row;
    }

    public String getKey(){
        this.generateKey();
        return this.key;
    }

    private void generateKey(){
        String k = "";
        k += this.name;
        k += "<-T->";
        k += price;
        k += "<-T->";
        k += seat;
        k += "<-T->";
        k += row;
        k += "<-T->";
        k += sector;
        this.key = k;
    }
}
