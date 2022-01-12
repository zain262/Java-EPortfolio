import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.lang.Boolean;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



class ePortfolio extends JFrame
{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    //Create constants for main frame dimensions
    private ArrayList < Investment > investments = new ArrayList < Investment > ();
        //Initlize array lists to store the investments
    private HashMap<String, ArrayList<Integer>> investNames = new HashMap<String, ArrayList<Integer>>();

    private JPanel mainPanel;
    private JPanel buyPanel;
    private JPanel sellPanel;
    private JPanel updatePanel;
    private JPanel gainPanel;
    private JPanel searchPanel;
    private String[] invStrings = { "Stock", "MutualFund"};
    private JComboBox invList;
    private JTextField symbolBuy;
    private JTextField nameBuy;
    private JTextField quantityBuy;
    private JTextField priceBuy;
    private JTextField symbolSell;
    private JTextField quantitySell;
    private JTextField priceSell;
    private JTextField symbolUpdate;
    private JTextField nameUpdate;
    private JTextField priceUpdate;
    private JTextField symbolSearch;
    private JTextField nameSearch;
    private JTextField highSearch;
    private JTextField lowSearch;
    private JTextField tGain;
    private JButton reset;
    private JButton resetSell;
    private JButton buy;
    private JButton sell;
    private JButton prev;
    private JButton next;
    private JButton save; 
    private JButton resetSearch;
    private JButton search;
    private JPanel messagePanelUpdate;
    private JTextArea updateText;   
    private JPanel messagePanel;
    private JTextArea buyText;
    private JPanel messagePanelSell;
    private JTextArea sellText;
    private JPanel messagePanelGain;
    private JTextArea gainText;
    private JPanel messagePanelSearch;
    private JTextArea searchText;
    private int updateIndex=0;

    private class BuyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mainPanel.setVisible(false);
            searchPanel.setVisible(false);
            buyPanel.setVisible(true);
            updatePanel.setVisible(false);
            sellPanel.setVisible(false);
            gainPanel.setVisible(false);
            //If user selects buy make buy pane visible and all others invisible
        }
    }

    private class PrevBut implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        //Reduce index by one 
        updateIndex--;
        if (updateIndex==0)
            {
              prev.setEnabled(false);
              next.setEnabled(true);
              //If the value of index is 0 now then make prev unpressable
            }
            else
            {
              prev.setEnabled(true);
              next.setEnabled(true);
              //Else make both pressable
            }
        symbolUpdate.setText(investments.get(updateIndex).getSymbol());
        nameUpdate.setText(investments.get(updateIndex).getName());
        //Display the name and symbol for the investment stored at index
        
        }
    }

    private class NextBut implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
          updateIndex++;
          //Increase index by one
          if (updateIndex==investments.size()-1)
            {
              prev.setEnabled(true);
              next.setEnabled(false);
              //If its the last investment then disbale next button
            //
            }
            else
            {
              prev.setEnabled(true);
              next.setEnabled(true);
              //Else enable both buttons
            }
        
        symbolUpdate.setText(investments.get(updateIndex).getSymbol());
        nameUpdate.setText(investments.get(updateIndex).getName());
        //Display the name and symbol for the investment stored at index
        
        }
    }

    private class saveBut implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           investments.get(updateIndex).updatePrice(priceUpdate.getText()); 
           //Update the price
        }
    }

    private class UpdateListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
        {
          //If update meny is chose
            updateIndex=0;
            //Reset the value of index
            if (investments.size()==0)
            {
              prev.setEnabled(false);
              next.setEnabled(false);
              //If there are no investements then disable both buttons
            }
            else if (investments.size()>1)
            {
              //If there are more than one investments
              if (updateIndex==0)
              {
              //If index is currently zero than disble prev button
              prev.setEnabled(false);
              next.setEnabled(true);
              }
              else
             {
               //Else set both to true
              prev.setEnabled(true);
              next.setEnabled(true);
             }
            symbolUpdate.setText(investments.get(updateIndex).getSymbol());
           nameUpdate.setText(investments.get(updateIndex).getName());
            }
            //Display the investment attribute
            mainPanel.setVisible(false);
            updatePanel.setVisible(true);
            buyPanel.setVisible(false);
            sellPanel.setVisible(false);
            gainPanel.setVisible(false);
            searchPanel.setVisible(false);
            //Make all other panels invisble
        }
    }

    private class QuitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
            //Exit the program
        }
    } 


    
    private class SearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            mainPanel.setVisible(false);
            updatePanel.setVisible(false);
            searchPanel.setVisible(true);
            buyPanel.setVisible(false);
            sellPanel.setVisible(false);
            gainPanel.setVisible(false);
            //Make all other panels invisble
        }
    }


    private class SellListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buyPanel.setVisible(false);
            searchPanel.setVisible(false);
            sellPanel.setVisible(true);
            gainPanel.setVisible(false);
            mainPanel.setVisible(false);
            updatePanel.setVisible(false);
            //Make all other panels invisble
        }
    } 

    private class GainListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
          String invGain=" ";
            mainPanel.setVisible(false);
            buyPanel.setVisible(false);
            searchPanel.setVisible(false);
            sellPanel.setVisible(false);
            gainPanel.setVisible(true);
            updatePanel.setVisible(false);
            //Make all other panels invisble

           //Sum of gain for each investment
            double sum = 0;
            for (int i = 0; i < investments.size(); i++) {
                invGain=invGain+ " " + investments.get(i).getSymbol()+ " " + investments.get(i).getGain() + "\n";  
                //Add invidual gain to invGain string
                sum = sum + investments.get(i).getGain();
                }
            gainText.setText(invGain);
            //Disply the indivual gain 
            tGain.setText(sum + " ");
            //Display the total gain
        }
    } //End of BlueListener inner class

    private class resetBuy implements ActionListener{
        
    public void actionPerformed(ActionEvent e)
    {
    symbolBuy.setText("  ");
    nameBuy.setText("  ");
    quantityBuy.setText("  ");
    priceBuy.setText("  ");
    //Clear all textfields
    }
    }

    private class Sell implements ActionListener{
        
    public void actionPerformed(ActionEvent e)
    {


    String symbol,quantity,price;

    boolean error=false;
    boolean match = false;
    String errorMessage=" ";
    symbol=symbolSell.getText();
    quantity=quantitySell.getText();
    price=priceSell.getText();

  
    if (symbol.isBlank())
    {
      //If the symbol text field is blank add to error message
      error=true;
      errorMessage=errorMessage+ "Please Enter a Symbol\n";
    }
    if (!error)
    {
      //If no error
      //System.out.println("sdafsdgf___");
      for (int i = 0; i < investments.size(); i++) {
      //Loop through the array list to find a investment with the same symbol
      if (symbol.equals(investments.get(i).getSymbol()) == true) {
      //If a match is found ask user the amount they would like to sell and its price
      match = true;
      int holder;
      holder = investments.get(i).sell(quantity,price);
      //If holder is 1 then all the investment has been sold
      if (holder==-1)
      {
        error=true;
        errorMessage=errorMessage+ "Please Ensure That The Quantity is an Integer and Price is a Double\n";
        //If holder returns -1 then there is a type casting issue
      }
      else if (holder==2)
      {
        error=true;
        errorMessage=errorMessage+ "Please type a number equal to or less than the amount you have\n";
        //If holder returns 2 then user is trying to sell more than they own
        
      }
      else if (holder==1)
      {
        sellText.setText("Sold All Investment of " + symbol + "\n");  
          String Name;
          Name=investments.get(i).getName();
          String[] parts = Name.split("[,. ;:/]+");
          for (int k=0;k<parts.length;k++)
                        {
                          String partsLow=parts[k].toLowerCase();
                          if (investNames.containsKey(partsLow))
                          {
                            ArrayList<Integer> list=investNames.get(partsLow);
                            for (int x=0;x<list.size();x++)
                            {
                            if (list.get(x)==i)
                            {
                            list.remove(i);
                            }
                            }
                            if (list.isEmpty())
                            {

                              investNames.remove(parts[k].toLowerCase());
                            }
                            else
                            {
                            investNames.put(partsLow,list);
                            }
                          }                          
                        }
                       Iterator <String> it=investNames.keySet().iterator();
                       while (it.hasNext())
                       {
                         String key=it.next();
                         ArrayList<Integer> list=investNames.get(key);
                         for (int x=0;x<list.size();x++)
                         {
                           if (list.get(x)>i)
                           {
                             list.set(x,list.get(x)-1);
                           }
                           investNames.put(key,list);
                         }
                       }
                       //Remove from arraylist
                        investments.remove(i);
      
      }
      }
    }
    if (!match) {
      
      sellText.setText("No match found for " + symbol + "\n");
      //If investment doesnt exist
      }

    }
    if (error)
    {  
    sellText.setText(errorMessage);
     //If there was an error display it
    }
    else
    {        
   // sellText.setText("Sucess");           
    }
    }
    }

    private class resetSell implements ActionListener{
        
    public void actionPerformed(ActionEvent e)
    {
      
    symbolSell.setText("  ");
    quantitySell.setText("  ");
    priceSell.setText("  ");
    //Clear all text fields

    }
    }

    private class searchBut implements ActionListener{
        
    public void actionPerformed(ActionEvent e)
    {
      searchText.setText("No investment were found");

    boolean found=false;
    boolean error=false;
    String errorMessage= " ";
    String totalInvestments="\n";
    boolean exact=false;
    boolean rangeCheck = true;
    boolean symbolCheck = true;
    boolean keyWordCheck = true;
    //Create variables too check if user entered a blank space

    String upRange=highSearch.getText();
    String botRange=lowSearch.getText();
    String symbol_=symbolSearch.getText();
    String keyWords=nameSearch.getText();
    //Get the input from text fields
    double HRange=0;
    double LRange=0;

    //If the input is blank then ignore it
    if (symbol_.isBlank())
    {
      symbolCheck=false;
    }
    if(keyWords.isBlank())
    {
      keyWordCheck=false;
    }
    if (upRange.isBlank() && botRange.isBlank())
    {
      rangeCheck=false;
    }

    if (!upRange.isBlank())
    {
    try {
        Double.parseDouble(upRange);
        } catch (NumberFormatException d) {
            
        error=true;
        errorMessage=errorMessage+ "Please type a Double for High Range\n";
        }
    }
    //If user enters a non double disply a error
    if (!botRange.isBlank())
    {
    try {
        Double.parseDouble(botRange);
        } catch (NumberFormatException d) {     
        error=true;
        errorMessage=errorMessage+ "Please type a Double for Lower Range\n";
        }
    }
    //If user enters a non double disply a error

      if (!error)
      {
        //If no error then set the vakues of range
        if (rangeCheck)
        {
        if (!upRange.isBlank() && !botRange.isBlank())
        {
          LRange=Double.parseDouble(botRange);
          HRange= Double.parseDouble(upRange);
          if (LRange==HRange)
          {
            exact=true;
            //If both numbers are the same
           // System.out.println("exact");
          }
        }
        else if (upRange.isBlank() && !botRange.isBlank())
        {
          LRange=Double.parseDouble(botRange);
          HRange=100000000;
          //If only lower range is given assume higher ranger is 100000000
          //System.out.println(LRange + "exact" + HRange+ "\n");
        }
        else if (!upRange.isBlank() && botRange.isBlank())
        {
          HRange=Double.parseDouble(upRange);
          LRange=0;
          //If only higher range is given then assume lower range is 0
          //System.out.println(LRange + "exact" + HRange +"\n");
        }

      }
       
        for (int i = 0; i < investments.size(); i++) {
        //Loop through arraylist
        boolean rangeChecker = false;
        boolean symbolChecker = false;
        boolean keyWordChecker = false;
        //Create booleans for when a match is found
        if (symbolCheck) {
        if (symbol_.equals(investments.get(i).getSymbol()) == true) {
          symbolChecker = true;
        }
          //If the user entered an input for symbol and a match is found set symbolChecker to true
        }
        if (rangeCheck) {
          double holderPrice = investments.get(i).getPrice();
          if (holderPrice <= HRange && holderPrice >= LRange) {
            rangeChecker = true;
            }
            //If the user entered an input for range and match is found set rangeChecker to true
                    }
                    if (keyWordCheck) {
                        //If the user entered an input for keywords and match is found set keyWordChecker to true
                        boolean Match = false;
                        boolean matchFinal = true;
                        String keyWrds[] = keyWords.split("[,. ;:/]+");
                        //Split the key words into single words
                        
                        //Split the name of the investment into words
                        for (int x = 0; x < keyWrds.length; x++) {
                            Match = false;
                            ArrayList<Integer> list=investNames.get(keyWrds[x].toLowerCase());
                            for (int k = 0; k < list.size(); k++) {
                                if (list.get(k)==i) {
                                    //If a match is found for the specific key word in the name of the investment set Match to true
                                    Match = true;
                                }
                            }
                            if (!Match) {
                                //If no match is found for that key word set matchFinal to false
                                matchFinal = false;
                            }
                        }
                        if (matchFinal) {
                            //If a match is found set keyWordChecker to true
                            keyWordChecker = true;
                        }
                    }
                    if (keyWordCheck == keyWordChecker && rangeCheck == rangeChecker && symbolCheck == symbolChecker) {
                        totalInvestments=totalInvestments+investments.get(i).display();
                        found=true;
                        //If a match is found for the variables that were entered by the user then display
                    }
                }

            if (!found)
            {
              //If no investment match the users search 
              searchText.setText("No investment were found");
            }
            else
            {
              searchText.setText(totalInvestments);
              //Display the matching investments
            }
      }
    if (error)
    {  
      //Display the error
    searchText.setText(errorMessage); 
    }
    }
    }

    private class resetS implements ActionListener{
        
    public void actionPerformed(ActionEvent e)
    {
    symbolSearch.setText("  ");
    nameSearch.setText("  ");
    highSearch.setText("  ");
    lowSearch.setText("  ");
    //Clear all text fields
    }
    }
    private class buyA implements ActionListener{
        
    public void actionPerformed(ActionEvent e)
    {
      System.out.println(investNames);
      boolean error=false;
      String errorMessage=" ";
      String symbol,name,quantity,price;
      symbol=symbolBuy.getText();
      name=nameBuy.getText();
      price=priceBuy.getText();
      quantity=quantityBuy.getText();
      //Get user input from text fields

      
      
        
      if (invList.getSelectedIndex()==0) 
      {
        
      Stock holder = new Stock();
      if (investments.isEmpty()) {
      //  System.out.println("eeee\n");
      //If it is empty create a new stock variable

      //If any function returns -1 it means the input is incorrect
      if (holder.setName(name)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter a Name\n";
      }
      if (holder.setPrice(price)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter a Double For Price\n";
      }
      if (holder.setAmnt(quantity)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter an Integer for Quantity\n";
      }
      if(holder.setSymbol(symbol)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter a Symbol\n";
      }
      if (!error)
      {
        //If no error
        holder.setName(name);
        holder.setSymbol(symbol);
        holder.setPrice(price);
        holder.setAmnt(quantity);
        holder.bookValue();
        //Set attriubtes
        String Name;                      
        Name=holder.getName();
        String[] parts = Name.split("[,. ;:/]+");
        for (int i=0;i<parts.length;i++)
        { 
        String partsLow=parts[i].toLowerCase();
        investNames.put(partsLow,new ArrayList<Integer>()); 
        ArrayList<Integer> list=investNames.get(partsLow); 
        list.add(0); 
        //Hash the name with index
        investNames.put(partsLow,list);
        }
        investments.add(holder);
        //Add it to the arraylist
      }
      
      }
      else
      {
      //If any function returns -1 it means the input is incorrect
      if (holder.setName(name)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Ensure That All Fields Are Filled In\n";
      }
      if (holder.setPrice(price)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter a Double For Price\n";
      }
      if (holder.setAmnt(quantity)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter an Integer for Quantity\n";
      }
      if(holder.setSymbol(symbol)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Ensure That All Fields Are Filled In\n";
      }
      if (!error)
      {
      int posHolder = 0;
      boolean found = false;
      //Loop through the arraylist and check if a investment with the symbol already exists
      for (int i = 0; i < investments.size(); i++) {
      if (symbol.equals(investments.get(i).getSymbol()) == true) {
      found = true;
      posHolder = i;
      //If a investment exists then save its index
        }
      }
      if (found)
      {
        if (investments.get(posHolder) instanceof Stock)
        {
        investments.get(posHolder).addInvestment(price,quantity);
        System.out.println("ccc\n");
        //Add more quantity to the investment
        }
        else
        {
          error=true;
         errorMessage=errorMessage+"A mutualfund with this symbol already exists\n"; 
        }
      }
      else
      {
        holder.setName(name);
        holder.setSymbol(symbol);
        holder.setPrice(price);
        holder.setAmnt(quantity);
        holder.bookValue();
        String Name;     
        //Set attriubtes                 
        Name=holder.getName();
        String[] parts = Name.split("[,. ;:/]+");
        for (int i=0;i<parts.length;i++)
        { 
        String partsLow=parts[i].toLowerCase();
        if (investNames.containsKey(partsLow))
          {
          ArrayList<Integer> list=investNames.get(partsLow);
          list.add(investments.size());
          investNames.put(partsLow,list);
          //If the name is already in hash then add index
          }
          else
          {
            //Otherwise add a new word to the hash
          investNames.put(partsLow,new ArrayList<Integer>()); 
          ArrayList<Integer> list=investNames.get(partsLow); 
          list.add(investments.size()); 
          investNames.put(partsLow,list); 
          }
        }
        investments.add(holder);
        //Add it to the arraylist
      }
      }
      }
      }
      else if (invList.getSelectedIndex()==1)
      {
        //If mutual fund is chosen
      //System.out.println("yyyyy\n");
      mutualFund holder = new mutualFund();
      if (investments.isEmpty()) {
      //If it is empty create a new stock variable
      //If any function returns -1 it means the input is incorrect
      if (holder.setName(name)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter a Name\n";
      }
      if (holder.setPrice(price)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter a Double For Price\n";
      }
      if (holder.setAmnt(quantity)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter an Integer for Quantity\n";
      }
      if(holder.setSymbol(symbol)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter a Symbol\n";
      }
      if (!error)
      {
        holder.setName(name);
        holder.setSymbol(symbol);
        holder.setPrice(price);
        holder.setAmnt(quantity);
        holder.bookValue();
        String Name;   
        //Set attributes                   
        Name=holder.getName();
        String[] parts = Name.split("[,. ;:/]+");
        for (int i=0;i<parts.length;i++)
        { 
        String partsLow=parts[i].toLowerCase();
        investNames.put(partsLow,new ArrayList<Integer>()); 
        ArrayList<Integer> list=investNames.get(partsLow); 
        list.add(0); 
        //Hash the name with index
        investNames.put(partsLow,list);
        }
        investments.add(holder);
        //Add it to the arraylist
      }
      
      }
      else
      {
        //If any function returns -1 it means the input is incorrect
      if (holder.setName(name)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Ensure That All Fields Are Filled In\n";
      }
      if (holder.setPrice(price)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter a Double For Price\n";
      }
      if (holder.setAmnt(quantity)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Enter an Integer for Quantity\n";
      }
      if(holder.setSymbol(symbol)==-1)
      {
        error=true;
        errorMessage=errorMessage+"Error Please Ensure That All Fields Are Filled In\n";
      }
      if (!error)
      {
      int posHolder = 0;
      boolean found = false;
      //Loop through the arraylist and check if a investment with the symbol already exists
      for (int i = 0; i < investments.size(); i++) {
      if (symbol.equals(investments.get(i).getSymbol()) == true) {
      found = true;
      posHolder = i;
      //If a investment exists then save its index
        }
      }
      if (found)
      {
        if (investments.get(posHolder) instanceof mutualFund)
        {
        investments.get(posHolder).addInvestment(price,quantity);
        //System.out.println("xxxx\n");
        //Add more quantity to the investment
        }
        else
        {
          
          error=true;
         errorMessage=errorMessage+"A stock with this symbol already exists\n"; 
        }
      }
      else
      {
        holder.setName(name);
        holder.setSymbol(symbol);
        holder.setPrice(price);
        holder.setAmnt(quantity);
        holder.bookValue();
        String Name;
        //Set attributes                   
        Name=holder.getName();
        String[] parts = Name.split("[,. ;:/]+");
        for (int i=0;i<parts.length;i++)
        { 
        String partsLow=parts[i].toLowerCase();
      if (investNames.containsKey(partsLow))
      {
      ArrayList<Integer> list=investNames.get(partsLow);
      list.add(investments.size());
      investNames.put(partsLow,list);
      //If name already exists add new index to the name
      }
      else
      {
      investNames.put(partsLow,new ArrayList<Integer>()); 
      ArrayList<Integer> list=investNames.get(partsLow); 
      list.add(investments.size()); 
      investNames.put(partsLow,list);
      //Else add a new name and index 
            }
        }
        investments.add(holder);
        //Add it to the arraylist
      }
      }
      }

      }
      

            if (error)
            {
              
              buyText.setText(errorMessage);
              //Display the errorMessage
            }
            else
            {
              //If no errors then display sucess
              buyText.setText("Sucess");
           
            }

      
      //buyText.setText("sucess \n aaa");
    }
    }

    public static void main(String[] args)
    {
        ePortfolio gui = new ePortfolio( );
        gui.setVisible(true);
    }

    

    public ePortfolio( )
    {
        super("ePortfolio");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        //Create Initial Window
        mainPanel = new JPanel(new GridLayout(0, 1));
        //Create Main Pane
        JLabel Welcome= new JLabel("Welcome to ePortfolio");
        JLabel commandMsg= new JLabel();
        commandMsg.setText("<html>"+ "Choose a command from the “Commands” menu to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program." +"</html>");
        mainPanel.add(Welcome);
        mainPanel.add(commandMsg);
        mainPanel.setVisible(true);
        //Add the labels to the pane
        add(mainPanel);
        //Add the pane to the window

        //Create update panel and add it to the main window
        updatePanel= new JPanel();
        updatePanel.setVisible(false);
        add(updatePanel);

        //Create search panel and add it to the main window
        searchPanel= new JPanel();
        searchPanel.setVisible(false);
        add(searchPanel);

        //Create Buy Panel
        buyPanel = new JPanel(new BorderLayout());
        buyPanel.setLayout(null);
        //Create and set the labels fot the buy panel
        JLabel TypeL= new JLabel("Type");
        TypeL.setBounds(0,0,90,45);
        JLabel SymbolL= new JLabel("Symbol");
        SymbolL.setBounds(0,30,90,45);
        JLabel NameL= new JLabel("Name");
        NameL.setBounds(0,60,90,45);
        JLabel QuantityL= new JLabel("Quantity");
        QuantityL.setBounds(0,90,90,45);
        JLabel PriceL= new JLabel("Price");
        PriceL.setBounds(0,120,90,45);

        //Create and set textfields for buy panel
        symbolBuy=new JTextField();
        symbolBuy.setBounds(70,40,70,20);
        nameBuy=new JTextField();
        nameBuy.setBounds(70,70,70,20);
        quantityBuy=new JTextField();
        quantityBuy.setBounds(70,100,70,20);
        priceBuy=new JTextField();
        priceBuy.setBounds(70,130,70,20);
        //Create and set a combo box
        invList= new JComboBox(invStrings);
        //invList.setSelectedIndex(4);
        invList.setBounds(70,10,70,20);
        //JComboBox investmentList = new JComboBox(invStrings);
        //investmentList.setSelectedIndex(2);
        //Add everything decalred so far to the buy panel
        buyPanel.add(invList);
        buyPanel.add(symbolBuy);
        buyPanel.add(nameBuy);
        buyPanel.add(quantityBuy);
        buyPanel.add(priceBuy);
        buyPanel.add(TypeL);
        buyPanel.add(SymbolL);
        buyPanel.add(NameL);
        buyPanel.add(QuantityL);
        buyPanel.add(PriceL);
        //Create reset and buy btton
        reset=new JButton("Reset");
        reset.addActionListener(new resetBuy());
        reset.setBounds(250,40,90,20);
        buy= new JButton("Buy");
        buy.setBounds(250,100,90,20);
        buy.addActionListener(new buyA());
        //Add the buttons to the panels
        buyPanel.add(buy);
        buyPanel.add(reset);

        //Create message display text area for the buy panel
        messagePanel=new JPanel(new BorderLayout());
        buyText=new JTextArea();
        buyText.setLineWrap(true);
        buyText.setEditable(false);
        //Make text wrap and make the text area non editable
        JScrollPane buyPane= new JScrollPane(buyText);
        buyPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        buyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Add the scroll bars
        buyPane.setPreferredSize(new Dimension(400,75));
        messagePanel.add(buyPane,BorderLayout.CENTER);
        //Add the textarea to the messagepane
        messagePanel.setVisible(true);
        messagePanel.setBounds(0,175,400,75);
        buyPanel.add(messagePanel);
        //Add the message pane to the buy pane
        buyPanel.setVisible(false);
        //add(redPanel, BorderLayout.CENTER);
        //Add buy to the main window
        add(buyPanel);

        //Create sell panel 
        sellPanel = new JPanel( );
        sellPanel.setLayout(null);
        //Remove layout
        JLabel sellTitle= new JLabel("Selling an investment");
        sellTitle.setBounds(0,0,200,45);
        JLabel SymbolSL= new JLabel("Symbol");
        SymbolSL.setBounds(0,30,90,45);
        JLabel QuantitySL= new JLabel("Quantity");
        QuantitySL.setBounds(0,60,90,45);
        JLabel PriceSL= new JLabel("Price");
        PriceSL.setBounds(0,90,90,45);
        JLabel Message=new JLabel("Message");
        Message.setBounds(0,120,90,45);
        //Create and set the labels for sell Pane
        symbolSell=new JTextField();
        quantitySell=new JTextField();
        priceSell=new JTextField();
        //Create the text fields
        resetSell=new JButton("Reset");
        resetSell.addActionListener(new resetSell());
       
        sell=new JButton("Sell");
        sell.addActionListener(new Sell());
        sell.setBounds(250,40,90,20);
        resetSell.setBounds(250,80,90,20);
        //Create button set it and add its listener functions

        symbolSell.setBounds(70,40,70,20);
        quantitySell.setBounds(70,70,70,20);
        priceSell.setBounds(70,100,70,20);
        //Set the textfields

        //Create the display area for sell panel
        messagePanelSell=new JPanel(new BorderLayout());
        sellText=new JTextArea();
        sellText.setLineWrap(true);
        sellText.setEditable(false);
        //Create text area and make it non editable and wrap text
        JScrollPane sellPane= new JScrollPane(sellText);
        sellPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sellPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Set scroll bar policies
        sellPane.setPreferredSize(new Dimension(400,75));
        messagePanelSell.add(sellPane,BorderLayout.CENTER);
        //Add the scroll to the display panel
        messagePanelSell.setVisible(true);
        messagePanelSell.setBounds(0,150,400,100);
        //Display and set its position
        sellPanel.add(messagePanelSell);
        //Add the display panel to sell


        sellPanel.add(Message);
        sellPanel.add(sell);
        sellPanel.add(resetSell);
        sellPanel.add(symbolSell);
        sellPanel.add(quantitySell);
        sellPanel.add(priceSell);
        sellPanel.add(sellTitle);
        sellPanel.add(SymbolSL);
        sellPanel.add(QuantitySL);
        sellPanel.add(PriceSL);
        sellPanel.setVisible(false);
        //Add all labels textfield and buttons to sell
        //add(whitePanel, BorderLayout.CENTER);
        //Add the sell panel to the main window
        add(sellPanel);

        //Create gain panel
        gainPanel = new JPanel( );
        gainPanel.setLayout(null);
        //Set it to no layout
        JLabel gainTitle= new JLabel("Getting Total Gain");
        gainTitle.setBounds(0,0,200,45);
        JLabel gainL= new JLabel("Total Gain:");
        gainL.setBounds(0,30,90,45);
        tGain= new JTextField();
        tGain.setEditable(false);
        tGain.setBounds(90,40,100,20);
        JLabel InvL= new JLabel("Individual Gain");
        InvL.setBounds(0,60,200,45);
        //Create labels and non editable text field and set their positions
        
        //Create display panels for gain
        messagePanelGain=new JPanel(new BorderLayout());
        gainText=new JTextArea();
        gainText.setLineWrap(true);
        gainText.setEditable(false);
        //Create text area and make it non editable and wrap text
        JScrollPane gainPane= new JScrollPane(gainText);
        gainPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        gainPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Set scroll bar policies
        gainPane.setPreferredSize(new Dimension(400,75));
        messagePanelGain.add(gainPane,BorderLayout.CENTER);
        messagePanelGain.setVisible(true);
        messagePanelGain.setBounds(0,100,400,150);
        //Display and set its position
        gainPanel.add(messagePanelGain);
        //Add display panel to gain panel

        gainPanel.add(tGain);
        gainPanel.add(gainTitle);
        gainPanel.add(gainL);
        gainPanel.add(InvL);
        gainPanel.setVisible(false);
        //Add all the buttons texts and labels to gain panel
        //add(bluePanel, BorderLayout.CENTER);
        add(gainPanel);
        //Add gain panel to main frame

        //Create update panel

        updatePanel= new JPanel();
        updatePanel.setLayout(null);
        JLabel updateTitle= new JLabel("Updating investments");
        updateTitle.setBounds(0,0,200,45);
        JLabel SymbolU= new JLabel("Symbol");
        SymbolU.setBounds(0,30,90,45);
        JLabel NameU= new JLabel("Name");
        NameU.setBounds(0,60,90,45);
        JLabel PriceU= new JLabel("Price");
        PriceU.setBounds(0,90,90,45);
        JLabel MessageU=new JLabel("Message");
        MessageU.setBounds(0,120,90,45);
        //Create a set labels
        symbolUpdate= new JTextField();
        symbolUpdate.setBounds(70,40,90,20);
        symbolUpdate.setEditable(false);
        nameUpdate= new JTextField();
        nameUpdate.setBounds(70,70,90,20);
        nameUpdate.setEditable(false);
        priceUpdate= new JTextField();
        priceUpdate.setBounds(70,100,90,20);
        MessageU.setBounds(0,120,90,45);
        prev= new JButton ("Prev");
        prev.setBounds(250,20,90,20);
        prev.addActionListener(new PrevBut());
        next=new JButton ("Next");
        next.setBounds(250,60,90,20);
        next.addActionListener(new NextBut());
        save=new JButton ("Save");
        save.setBounds(250,100,90,20);
        save.addActionListener(new saveBut());
        //Create and set textfield and buttons

        //Create display panels for update
        messagePanelUpdate=new JPanel(new BorderLayout());
        updateText=new JTextArea();
        updateText.setLineWrap(true);
        updateText.setEditable(false);
        //Create text area and make it non editable and wrap text
        JScrollPane updatePane= new JScrollPane(updateText);
        updatePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        updatePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Set scroll bar policies
        updatePane.setPreferredSize(new Dimension(400,75));
        messagePanelUpdate.add(updatePane,BorderLayout.CENTER);
        messagePanelUpdate.setVisible(true);
        messagePanelUpdate.setBounds(0,150,400,100);
         //Display and set its position
        updatePanel.add(messagePanelUpdate);
        //Add display panel to update panel

        updatePanel.add(next);
        updatePanel.add(prev);
        updatePanel.add(save);
        updatePanel.add(nameUpdate);
        updatePanel.add(priceUpdate);
        updatePanel.add(symbolUpdate);
        updatePanel.add(updateTitle);
        updatePanel.add(SymbolU);
        updatePanel.add(NameU);
        updatePanel.add(PriceU);
        updatePanel.setVisible(false);
        add(updatePanel);
        //Add everything to update panel

        //Create new panel for search
        searchPanel= new JPanel();
        searchPanel.setLayout(null);
        JLabel searchTitle= new JLabel("Searching investments");
        searchTitle.setBounds(0,0,200,45);
        JLabel SymbolS= new JLabel("Symbol");
        SymbolS.setBounds(0,30,90,45);
        JLabel NameS= new JLabel("Name");
        NameS.setBounds(0,60,90,45);
        JLabel KeyW= new JLabel("keywords");
        KeyW.setBounds(0,75,90,45);
        JLabel HPrice= new JLabel("High Price");
        HPrice.setBounds(0,105,90,45);
        JLabel LPrice= new JLabel("Low Price");
        LPrice.setBounds(0,135,90,45);
        JLabel MessageS=new JLabel("Message");
        MessageS.setBounds(0,90,90,45);

        //Create and set all labels
        symbolSearch=new JTextField();
        symbolSearch.setBounds(80,40,70,20);
        nameSearch=new JTextField();
        nameSearch.setBounds(80,70,70,20);
        highSearch=new JTextField();
        highSearch.setBounds(80,120,70,20);
        lowSearch=new JTextField();
        lowSearch.setBounds(80,150,70,20);

        resetSearch= new JButton("Reset");
        resetSearch.setBounds(250,40,90,20);
        resetSearch.addActionListener(new resetS());
        search= new JButton("Search");
        search.setBounds(250,100,90,20);
        search.addActionListener(new searchBut());
        //Create and set all textfields and buttons


        //Create display panels for search
        messagePanelSearch=new JPanel(new BorderLayout());
        searchText=new JTextArea();
        searchText.setLineWrap(true);
        searchText.setEditable(false);
        //Create text area and make it non editable and wrap text
        JScrollPane searchPane= new JScrollPane(searchText);
        searchPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        searchPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Set scroll bar policies
        searchPane.setPreferredSize(new Dimension(400,75));
        messagePanelSearch.add(searchPane,BorderLayout.CENTER);
        messagePanelSearch.setVisible(true);
        //Display and set its position
        messagePanelSearch.setBounds(0,175,400,75);
        //Add display panel to search panel
        searchPanel.add(messagePanelSearch);

        searchPanel.add(symbolSearch);
        searchPanel.add(resetSearch);
        searchPanel.add(search);
        searchPanel.add(nameSearch);
        searchPanel.add(highSearch);
        searchPanel.add(lowSearch);
        searchPanel.add(searchTitle);
        searchPanel.add(SymbolS);
        searchPanel.add(NameS);
        searchPanel.add(KeyW);
        searchPanel.add(HPrice);
        searchPanel.add(LPrice);
        searchPanel.setVisible(false);
        //Add everything to search panel
        add(searchPanel);
        //Add search panel to main frame
        //symbolUpdate= new JTextField();



        JMenu choiceMenu = new JMenu("Commands");
        //Create menu 
        JMenuItem buyChoice = new JMenuItem("Buy");
        buyChoice.addActionListener(new BuyListener( ));
        choiceMenu.add(buyChoice);
        //Add Buy Option
        JMenuItem sellChoice = new JMenuItem("Sell");
        sellChoice.addActionListener(new SellListener( ));
        choiceMenu.add(sellChoice);
        //Add Sell Option
        JMenuItem gainChoice = new JMenuItem("Gain");
        gainChoice.addActionListener(new GainListener( ));
        choiceMenu.add(gainChoice);
        //Add Gain Option

        JMenuItem updateChoice = new JMenuItem("Update");
        updateChoice.addActionListener(new UpdateListener());
        choiceMenu.add(updateChoice);
        //Add Update Option

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(new SearchListener());
        choiceMenu.add(searchChoice);
        //Add Searcg Option

        JMenuItem quitChoice = new JMenuItem("Quit");
        quitChoice.addActionListener(new QuitListener());
        choiceMenu.add(quitChoice);
        //Add Quit Option


        JMenuBar bar = new JMenuBar( );
        bar.add(choiceMenu);
        //Add the menu to the menu bar
        setJMenuBar(bar);
    }


    }

