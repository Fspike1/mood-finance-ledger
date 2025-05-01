package moodledger;

//THIS CODE IS AN INANIMATE OBJECT. IT CANT DO ANYTHING UNTIL SOMETHING IS DONE IN THE MAIN
//create a public class so that code will run in the main
public class Transactions {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private float amount;



    //.this tells java to use variable from class and not the parameter(What is in the parentheses below)
    public Transactions(String date, String time, String description, String vendor, float amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Date:" + date + " | Time:" + time + " | Product Description:"+ description + " | Vendor:" + vendor + "| Amount:$" + amount;

    }
    public  String getDate(){
        //in order to use get in the main, you must add it here first
        return date;
    }
    public String getTime(){
        return time;
    }
    public String getDescription(){
        return description;
    }
    public String getVendor() {
        return vendor;
    }
    public float getAmount() {
        return amount;
    }

}



