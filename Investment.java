import java.util.Scanner;
import java.lang.String;

/*
Class to store update and modify values of a mutual fund
*/
public class Investment {


    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    private double gain;
    //Create attributes for mutual fund
  
    
      /*
      Update the book value multiply the book value by the percent of orignal quantity left over
      */
    public void updateBookValue(int amnt) {
        //Update the book value when a certain amout of investment is sold
        double amntLeft = quantity - amnt;
        double holder = quantity;
        bookValue = bookValue * (amntLeft / holder);
    }
    public void addInvestment(String price, String quantity)
    {
      
    }

    public int sell(String quantity, String price) {
    return 1;
    }
    public void updatePrice(String Price)
    {
      
    }
    /*
    Add more quantity to an existing investment update value of price quantity and book value 
    */
   /* public void newInvestment(String Symbol) {
        //Initialize a new investment
        symbol = Symbol;
        Scanner scannerObject = new Scanner(System.in);
        System.out.print("Please Enter a Name For Your Investment\n");
        name = scannerObject.nextLine();
        System.out.print("Please Enter a Price For You Investment\n");
        price = scannerObject.nextDouble();
        System.out.print("Please Enter Amount of Invtesment\n");
        quantity = scannerObject.nextInt();
        //Ask user to enter name, price and amount of investment
       // bookValue();
        //Update book value
        gain = 0;
        //Initlize gain to 0
       
    }*/
    /*
    Sell a specefic amout of an investment
    */
    
    /*
    Calculate the gain for this investment gain=payment=bookValue
    */

  /*
  Return the value of symbol
  */  
    public String getSymbol() {
      //Return the symbol
        return symbol;
    }
  
    public int setName(String Name)
    {
      if(Name.isBlank())
      {
        return -1;
      }
      //Return -1 if blank
      this.name=Name;
      return 0;
    }
    public int setSymbol(String symb)
    {
      if(symb.isBlank())
      {
        return -1;
      }
      //Return -1 if blank
      this.symbol=symb;
      return 0;
    }
    public double getGain() {
        return gain;
        //Return gain
    }
    public double getPrice() {
        return price;
        //Return price
    }
    public String getName() {
        return name;
        //Return name
    }
    public int getQuantity() {
        return quantity;
        //Return quantity
    }
    //Mutator function for Book Value
    public void newBookVal(double bookvalue)
    {
      this.bookValue=bookvalue;
    }
    /*
    Display the attributes of the investment
    */
    public int setPrice(String price_)
    {

      try {
        Double.parseDouble(price_);
        } catch (NumberFormatException d) {
            
        return -1;
        }
        //Return -1 if string is not double

      this.price=Double.parseDouble(price_);
      return 0;
    }

    public int setAmnt(String amount)
    {
      try {
      Integer.parseInt(amount);
      } catch (NumberFormatException d) {
        return -1;
      }
      //Return -1 if string is not int
      this.quantity=Integer.parseInt(amount);
      return 0;
    }
    public double getBook()
    {
      return bookValue;
    }
    public void setGain(double gain)
    {
      this.gain=gain;
    }
    public String display() {
        //Display the attributes of the mutual fund
        return "Name: " + name + "\n"+ "Symbol: " + symbol + "\n" + "Price: " + price + "\n"+"Quantity: " + quantity + "\n"+"Book Value: " + bookValue + "\n"+ "Gain: " + gain + "\n";
    }
    public String fileOutput()
    {
      return " ";
    }
}