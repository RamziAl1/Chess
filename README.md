# Chess
ChessGame using OOP and clean code principles and design patterns
Chess assignment
Done by:
Ramzi Alyahya

In my design for the Chess game, I followed the clean code, SOLID, and OOP principles as much as I could, while incorporating some design patterns in order to create a maintainable and extendable piece of software, in general my design aims to achieve a high degree of abstraction by decomposing the design into smaller simple classes and achieves encapsulation by always minimizing accessibility of fields and methods that we don’t want to be exposed, using setter methods sparingly, and judiciously returning copies of data in getter methods.



PieceColor enum:
Holds the colors of the pieces to be used in the game.

![image](https://user-images.githubusercontent.com/81943021/208049581-99c1e991-5870-4348-b765-e20b2d7895cc.png)



The Pieces:
I used an abstract base class called Piece to represent pieces on the board, each subclass of Piece has its own rules regarding how it can move, and some of the pieces have similar moving patterns such as queen and rook, which’s why I chose use the strategy pattern to validate the movements of each piece, each piece holds a list of MoveValidator objects so each piece can use combination of these objects to define it’s movements, where each subclass of MoveValidator checks the validity of a certain type of movement.

![image](https://user-images.githubusercontent.com/81943021/208049804-1b795e35-5daf-484a-b8fc-ef13266ff561.png)
![image](https://user-images.githubusercontent.com/81943021/208049884-736a4eed-59ff-4514-9fe1-3e7ce2bc2f70.png)



This design ensures a high level of abstraction and ensures separation of concerns and single responsibility principle, due to using strategy pattern, and eliminates code redundancy by defining specific types of moves as strategies such as vertical, horizontal move validators so pieces and use any combination of them.
This design also follows the open closed principle, liskov’s principle and the other SOLID principles because it’s using strategy pattern.



The Board:
The board stores all the pieces in an 8x8 2-d array, it uses a 2-d String array which stores the starting configuration of pieces for both players then uses the factory pattern to initialize the board with pieces in PieceFactory object, this reduces coupling and code redundancy.
The Board class achieves total encapsulation by minimizing accessibility and having its getter return deep copy of the 2-d Piece array, this way it’s internal contents can’t be modified from outside the Board class.



Move and Spot:
In order to get cleaner code and simplify the representation locations on the board I made a class called Spot whish holds x and y coordinates.
I also made class to represent move operations called Move, it holds the position of the piece to be moved and the position to be moved to.
Both classes are data clumps and thus code smells, but I believe the fact they shorten long function parameters and fulfill separation of concerns and single responsibility principles makes them worth keeping.

![image](https://user-images.githubusercontent.com/81943021/208050126-2a23b626-a537-4356-8d20-4074ed97a405.png)



Movement:
Movements in chess can split into 2 categories:
1) regular moves: moves designated by each piece’s rule set and they are almost always applicable.
2) Special moves: moves which are conditional mutually exclusive and are not easily grouped together in a single function like regular moves. (Castling EnPassant, Promotion , ….etc).
So, in order to account for each move we’ll have to test each one, which’s why I’m using the chain of responsibility design pattern to handle each movement request starting with the Special moves as they are less likely to occur and my be missed if they come after MoveHandler.

![image](https://user-images.githubusercontent.com/81943021/208050279-0e799413-56e3-46aa-a5f3-ec9d67f89501.png)

Starting off with an interface IMoveHandler following the dependency inversion principle, this design also fulfills single responsibility principle by separating each special movement into its own class.
Using chain of responsibility pattern increases modularity, leaving it open for extension in the future with new movement rules, reduces the degree of coupling, increases cohesion and fulfills all the SOLID principles.



PiecesMetadata:
Throughout the program We’ll need to get certain information about the pieces based on the state of the board such as if a piece is threated or if a piece can make any moves at all, I’ve extracted the methods which get this information into a class called PiecesMetadata, the weakness of this class is that it’s a utility class so it’s a code smell, has low cohesion and increases coupling while reducing code redundancy.

![image](https://user-images.githubusercontent.com/81943021/208050503-91133f6f-5df2-426b-8043-cebffcab7c16.png)



Gamestate:
There are many states the game could be in at any given time such as check, checkmate, and various types of draw, all of them are mutually exclusive except for check and checkmate.
My design checks the game state using the strategy design pattern where each state is represented by a class (is a strategy), which all implement an interface called GameStateCheck.
It makes an array of concrete GameStateCheck objects and iterates over them to check all the possible states and prioritize the more consequential states.
I abstracted this away from the client even further by creating a class which holds this list and performs the iteration called GameStateChecker, so the client isn’t concerned with the any of these implementation details.
The GameStateChecker also inherits from an abstract class called AbstractStateChecker in accordance with the dependency inversion principle.

![image](https://user-images.githubusercontent.com/81943021/208050578-a5d6a3d0-7115-4435-bd7b-b2285de66f8a.png)

While this design may be a bit complex, it ensures encapsulation of the game state algorithms, while fulfilling separation of concerns, liskov principle along with the other SOLID principles by using strategy pattern.
On the other hand, it must return the state information in an object called StateInfo which results in control coupling, however I don’t think there’s any way around this problem, considering the rules of chess which dictate that the state of the game affects how the players should act, such as how being in check greatly reduces the amount of moves available to the player.



ChessGame:
The responsibility of this class is to enforce the rules of the game, like the rule of each player getting a turn where they must play a legal move till someone wins, it accomplishes this using encapsulated, abstracted and simple methods provided by the previously discussed classes, in this way it has high cohesion, and almost acts as a façade of the whole game.

![image](https://user-images.githubusercontent.com/81943021/208050627-54dc1811-e9f9-404e-89ad-d3f844d8618f.png)

One thing to note about my implementation is that all the default constructors setup the game as a default chess game, however I did provide typical constructors so anyone can insert whatever rules and pieces they desire.

ChessGame in execution:
• The game prints the board state at the start of every turn.
• The white player goes first, and they will be prompted to enter a move.
• If it’s a legal move it’ll be executed, and the board will be altered accordingly.
• If it’s an illegal move or invalid input the game will print the appropriate response to the screen and reset the turn.
• The same will occur but now it’s the other player’s turn.
• This continues till a draw occurs or someone loses.



Closing remarks:
In my design I applied OOP principles and used the SOLID principles as a guide, while constantly looking at my code and criticizing it with Uncle Bob’s clean code principles to iteratively improve my code, I decomposed the problem into smaller classes and functions to improve readability, cohesion and coupling, and applying DRY principle to reduce code redundancy wherever possible.
Whenever I got stuck on a design issue or saw that my code had major SOLID violations I’d search through Uncle Bob’s book and go through design patterns again till I find a simple solution, (as a beginner I still haven’t fully wrapped my head around all of the design patterns).
