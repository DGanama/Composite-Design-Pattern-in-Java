Battleship Search using a Strategy Design Pattern

This project is essentially a Battleships game and presents an implementation of the Strategy design pattern.

The BattleshipSearch class contains the interface logic and a tokenizer method. 

BattleShipSearch retrieves ship coordinates from a text file using the tokenizer method and places these ships on a grid.

Then, it uses the different strategies to look for these ships on the grid and prints out statistics on the performance of each search strategy. 

The three different strategies must implement certain methods in the Strategy interface. Each strategy has its own algorithm to find the targets:
  - HorizontalSearch looks at all the possible blocks in a row then it moves to the next row.
  - RandomSearch picks a new location to look at using the built-in Random class.
  - OptimizedSearch jumps 3 positions at a time. If it finds a ship, it looks around that area for the remaining blocks.
  

