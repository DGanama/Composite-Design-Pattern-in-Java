Battleship Search using a Strategy Design Pattern

The Strategy design pattern (aka the policy pattern) is a behavioral software design pattern that enables selecting an algorithm at runtime. Instead of implementing a single algorithm directly, code receives run-time instructions as to which in a family of algorithms to use. This allows the algorithm to vary independently from clients that use it.

This project is essentially a Battleships game that presents an implementation of the Strategy design pattern.

The BattleshipSearch class contains the interface logic and a tokenizer method. 

BattleShipSearch retrieves coordinates of ships from a text file using the tokenizer method and places these ships on a grid.

Then, it uses the different strategies to look for these ships on the grid and prints out statistics on the performance of each search strategy. 

The three different strategies must implement certain methods in the Strategy interface. Each strategy has its own algorithm to find the targets:
  - HorizontalSearch looks at all the possible blocks in a row then it moves to the next row.
  - RandomSearch picks a new location to look at using the built-in Random class.
  - OptimizedSearch jumps 3 positions at a time. If it finds a ship, it looks around that area for the remaining blocks.
  
The Strategy design pattern is helpful when:
  - You have a variety of ways to perform an action
  - You might not know which approach to use until runtime
  - You want to easily add new ways to perform an action
  - You want to keep code maintainable as you add behaviors
 
