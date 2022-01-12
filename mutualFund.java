import java.util.Scanner;
import java.lang.String;


/*
Class to store update and modify values of a mutual fund
*/
public class mutualFund extends Investment{

    public static double REDEMPTION_FEE = 45;

  /*
  Calculate the book value (quantity*price)
  */
       
  public void bookValue() {
        //Calculate the book value
      newBookVal(getQuantity() * getPrice());
      }
    
      
      /*
      Add more quantity to an existing investment update value of price quantity and book value 
      */
    public void addInvestment(String price_, String quantity_) {
        //Add more to an exisiting investment
        int amnt=Integer.parseInt(quantity_);
        setPrice(price_);
        
        //Ask user for the price and how much they bought the investment for
        int quantity;
        quantity = getQuantity() + amnt;
        setAmnt(Integer.toString(quantity));
        newBookVal(getBook() + amnt * Double.parseDouble(price_));
        //Update quantity and book value   
    }
    

    /*
    Sell a specefic amout of an investment
    */
    public int sell(String amnt_, String Price_) {
        Scanner sc = new Scanner(System.in);
        int amnt;
        double Price;
       
        try {
        Double.parseDouble(Price_);
        } catch (NumberFormatException d) {
            
        return -1;
        }
        //Return -1 if string is not double
        try {
        Integer.parseInt(amnt_);
        } catch (NumberFormatException d) {
            
        return -1;
        }

        Price=Double.parseDouble(Price_);
        amnt=Integer.parseInt(amnt_);
        //Ask user for the amount they would like and sell and they price they are selling at
        if (getQuantity() < amnt) {
            
            return 2;
          //If user tries to sell more than they have return 2
        }
        if (getQuantity() == amnt) {
            setPrice(Price_);
            setAmnt(Integer.toString(getQuantity()-amnt));
            totalGain();
            //Update price quantity and gain
            //If user sells all their investment return 1
            
            return 1;
        } else {
          //Update book value, price, quantity, and gain
            updateBookValue(amnt);
            setPrice(Price_);
            setAmnt(Integer.toString(getQuantity()-amnt));
            totalGain();
        }
        
        return 0;
    }
    /*
    Calculate the gain for this investment gain=payment=bookValue
    */
    public void totalGain() {
      //Calculate total gain by substrating market value from book value
        double payment = 0;
          double gain=0;
          payment = getQuantity() * getPrice() - REDEMPTION_FEE;
          gain = payment - getBook();
          setGain(gain);

    }
    
    /*
    Update the price by asking user for a new price and setting that to the new price
    */
   public void updatePrice(String Price) {
      //Ask user to input a new price and update the value price and gain

        setPrice(Price);
        totalGain();
     
        
    }
    /*
    type = “stock”
symbol= “AAPL”
name= “Apple Inc.”
quantity = “500”
price = “142.23”
bookValue= “55049.99”
*/
 /*   public String fileOutput()
    {
      return "type =“mutualfund” \nsymbol= “" + getSymbol() +"”\nname= “" + getName() + "”\nquantity = “" + getQuantity() + "”\nprice = “" + getPrice() + "”\nbookValue= “" + getBook() + "”\n";
    }*/
/*
     public void newInvestment(String Symbol) {
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
        double bookValue=0;
        bookValue=
        gain = 0;
        //Initlize gain to 0
       
    }*/
    
    /*
    Display the attributes of the investment
    */
}