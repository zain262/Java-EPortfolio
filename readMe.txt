Compilation:
javac ePortfolio.java Stock.java Investment.java mutualFund.java
java ePortfolio Stock Investment mutualFund

Description:

GUI:
A Main java frame was made, and panes were made for each option
a command menu was added to the main frame for each option a listener
function was added associated with each option in the menu as
a option was selected all other panes were set to invisible instead of 
the one selected. For each pane textFields labels buttons comboBox 
and textArea was added for user input and output. A scroll bar
was added to the text area incase input exceeds the provided area



Quit:
If quit is selected by the user end program
Buy:
If the user picks buy then they are asked weather they would like to buy a mutual 
fund or stock, this will decide what kind of investmnet is being made
Next it is checked if the arryalist is empty; if it is empty then user is asked 
for a symbol for the investment and then each attribute is set using mutator functions 
The mutator functions also check for inpropers input returning -1 if somethign went wrong
if arraylist is not empty then 
the user is asked for a symbol and the arraylist is searched for a matching symbol 
if a match is found then addInvestment function is called for that investment and 
a new quantity is added to the investment as well as price and book value gets 
updated. If a match is not found it is treated like the list is empty and new 
investment is created

Sell:
The user is asked to type the symbol name and price of the investment that they would like to 
sell the arraylist is searched to find a match, when a match is found sell 
function gets called for that investment if the amount they want to sell exceeds 
how much they have an error message gets printed, 
or if input is not proper (not a double for price) a error get printed
if a user sells all thier investment the element is deleted from the 
array list, or if they sell a certain amout quantity, price, gain and bookValue 
gets updated. If the symbol is not found in the array list a error message gets printed

Update:
A loops going through the arraylist, calling updatePrice function for each investment
Also ensuring that the input entered but the user is proper
The Prev button is disbaled when you are at the first invetsment
the next button is disabled when you are at the last investment
getGain:
A loop goes through the arraylist getGain is called for each 
investment and is added to a sum which is displayed onces each invetsment has been added
Also the indivual gains are added to a string which is displayed
at the end
search:
User is asked to enter a symbol, range and key words, then the input for errors is checked if
 the input is blank then a boolean corresponding to that input is set to false if 
 a boolean corresponding to a input is true then the arraylists are searched for 
 that input and if a match is found a boolean is set to true at the end if the 
 input booleans match the booleans from searching for that investment it is 
 displayed



Hash:
Each time a new investment is added its name is tokened into singular words and then added to the hashmap
Hash map records each unqiue word and the index at which it occurs at if a word occurs more than once then
the arraylist assoicated with that word has more than one index's
When selling the index is removed from the hash list and any index's higher are reduced by one any lower remain the same


Limitations: 
A limitation is that the sell function at times throws an out of
index error another limitation is that I feel like I couldve improved on the 
 polymorphism aspect of this assingment
 if I was given more time i'd try to redo the sell function
 and make the code more efficnet


/**********************************************************/
TEST CASE I buy sell search
***********************************************************/
Input:
Type: Stock
Symbol: AAPL
Name: Apple inc
Quantity:asff
Price:asf
Output:
Error Please Enter a Double For price
Error Please Enter an Integer for Quantity

Input:
Type: Stock
Symbol: AAPL
Name:
Quantity:asff
Price:asf
Output:
Error Please Enter a Name
Error Please Enter a Double For price
Error Please Enter an Integer for Quantity

Input:
Type: Stock
Symbol: AAPL
Name:Apple Inc
Quantity:500
Price:110.08
Output:
Sucess

Input:
Type: MutualFund
Symbol: AAPL
Name:Apple Inc
Quantity:500
Price:110.08
Output:
A stock with this symbol already exists

Input:
Symbol:
Name/KeyWords:
High Price:
Low Price:
Output:
Name:Apple Inc
Symbol: AAPL
Price:110.08
Quantity:500
Book Value: 55049.99
Gain: 0.0

Input:
Symbol:AAPL
Quantity:500
Price:200
Output:
Sold All Investment of AAPL


/**********************************************************/
TEST CASE II buy search update gain
***********************************************************/
Input:
Type: MutualFund
Symbol: SSETX
Name: BNY Mellon
Quantity:450
Price:53.26
Output:
Sucess

Input:
Type: MutualFund
Symbol: SYM
Name: Mellon Sym
Quantity:100
Price:24
Output:
Sucess

Input:
Symbol:
Name/KeyWords:Mellon
High Price:
Low Price:
Output:
Name:BNY Mellon
Symbol: SSETX
Price:53.26
Quantity:450
Book Value: 23967.0
Gain: 0.0
Name:Mellon Sym
Symbol: SYM
Price:24.0
Quantity:100
Book Value: 2400.0
Gain: 0.0

Input:
Symbol: SSETX
Name:BNY Mellon
Price:42.21

Symbol: SYM
Name: Mellon Sym
Price: 23

Output:
Total Gain: -5162.5
Individual Gain:
SSETX -5017.5
SYM -145.0

/**********************************************************/
TEST CASE II buy search
***********************************************************/
Input:
Type:Stock
Symbol:AAPL
Name:Apple
Quantity:10
Price:20
Output:
Sucess

Input:
Type:Stock
Symbol:Merc
Name:Mercedes
Quantity:100
Price:10
Output:
Sucess

Input:
Type:Stock
Symbol:BOT
Name:Bank of Toronto
Quantity:10
Price:15
Output:
Sucess

Input:
Type:Stock
Symbol:BOM
Name:Bank of Montana
Quantity:150
Price:10
Output:
Sucess

Input:
Symbol:
Name/KeyWord:Bank
High Price:
Low Price:
Output:
Name: Bank of Toronto
Symbol:BOT
Price:15.0
Quantity:10
Book Value: 159.99
Gain: 0.0
Name: Bank of Montana
Symbol:BOM
Price:150.0
Quantity:10
Book Value: 1509.99
Gain: 0.0

Input:
Symbol:
Name/KeyWord:
High Price:10
Low Price:10
Output:
Name: Mercedes
Symbol:Merc
Price:10.0
Quantity:100
Book Value: 1009.99
Gain: 0.0




