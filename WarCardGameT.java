// Joshua D Sachar
// CS 110
// WarCardGameT
// Terminal Interface for War(Card Game)
import java.util.Scanner;
public class WarCardGameT
{
	// main
	public static void main(String[] args) 
	{
		// create players to play war
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		// Initialize a WarGame with said players
		WarGame game = new WarGame(player1, player2);
		// Initialize a "winner" to null.
		Player winner = null;
		// Welcome operator
		System.out.println("\nHow about a Nice Civilized Game of War?\n");
		// Set gameOver flag to false;
		boolean gameOver = false;
		// initialize response string to something that is not truce/quit/exit
		String response = "War!!";
		// Create a scanner for operator input
		Scanner input = new Scanner(System.in);
		// Stay in loop until someone loses, or operator sends a quit command.
		while(game.checkEndGame(player1,player2) == 0 && (!response.toLowerCase().equals("truce") && !response.toLowerCase().equals("quit") && !response.toLowerCase().equals("exit") && !response.toLowerCase().equals("peace")))
		{
			// Play loop, will stay here until someone loses or a player wins a hand
			while(winner == null && gameOver == false)
			{
				// Call startBattle with players
				game.startBattle(player1, player2);
				// Show played cards
				System.out.println(player1.getName() + " :\t" + player1.peekCurrent() + "\n" + player2.getName() + " :\t" +player2.peekCurrent());
				// Resolve the battle and return winner
				winner = game.resolveBattle(player1, player2);
			// If Block to resolve winner
			if(winner == player1)
			{
				System.out.println(player1.getName() + " Wins!");
				response = input.nextLine();
			}
			else if(winner == player2)
			{
				System.out.println(player2.getName() + " Wins!");
				response = input.nextLine();
			}
			// If no winner and the game isn't over, this must be a tie and therefore a WAR! Call war and Show 2 facedown cards 
			else if(winner == null && (game.checkEndGame(player1,player2) == 0))
			{
				game.war(player1, player2);
				System.out.println("\n\t\tThis is WAR!!\n");
				System.out.println(player1.getName() + " :\t" + player1.peekCurrent() + "\n" + player2.getName() + " :\t" +player2.peekCurrent());
			}
			// If none of these, then game must be over.
			else
				gameOver = true;
			}
			// Give appropriate player the cards they won. If no one won, set winner to null.
			if(winner == player1)
				game.giveSpoils(winner, player2);
			else if(winner == player2)
				game.giveSpoils(winner, player1);
			else;
				winner = null;
		}
		// Game has ended! Report the loser. (Case 3 in case both lose simultaniously, Highly unlikely yet possible.)
		switch(game.checkEndGame(player1, player2))
		{
			case 1: 
			System.out.println(player1.getName() + " has lost!");
			break;
			case 2:
			System.out.println(player2.getName() + " has lost!");
			break;
			case 0:
			System.out.println("Truce Accepted");
			break;
			case 3:
			System.out.println("No Winners! You all lost!");
		}
	}
}