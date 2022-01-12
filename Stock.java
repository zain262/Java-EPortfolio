  import java.util.Scanner;
  import java.lang.String;


/*
Class to store modify and update values for stock
*/
  public class Stock extends Investment{

      public static double COMISSION = 9.99;

      /*
      Calculate the book value (quantity*price+comission)
      */
      public void bookValue() {
        //Calculate the book value
      newBookVal(getQuantity() * getPrice() + COMISSION);
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
          newBookVal(getBook() + amnt * Double.parseDouble(price_) + COMISSION);
          //Update quantity and book value
          
      }
      /*
      Initialize the attributes to a new investment
      */
    
      /*
      Sell a specefic amout of an investment
      */
      
      public int sell(String amnt_, String Price_) {
        
          int amnt;
          double Price;
         
         try {
        Double.parseDouble(Price_);
        } catch (NumberFormatException d) {
            
        return -1;
        }

        try {
        Integer.parseInt(amnt_);
        } catch (NumberFormatException d) {
            
        return -1;
        }
        
        Price=Double.parseDouble(Price_);
        amnt=Integer.parseInt(amnt_);

          if (getQuantity() < amnt) {
                     
              //If user tries to sell more than they have return 2
              return 2;
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
          payment = getQuantity() * getPrice() - COMISSION;
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
    public String fileOutput()
    {
      return "type =“stock” \nsymbol= “" + getSymbol() +"”\nname= “" + getName() + "”\nquantity = “" + getQuantity() + "”\nprice = “" + getPrice() + "”\nbookValue= “" + getBook() + "”\n";
    }*/


     
  }