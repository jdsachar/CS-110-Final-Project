public class WarGame
{
	public WarGame(Player p1, Player p2)
	{
		initialize(p1,p2);
	}

	public static void initialize(Player p1, Player p2)
	{
		WarDeck deck = new WarDeck(1);
		int iterator = 0;
		while(!deck.isEmpty())
		{
			if(iterator == 0)
			{
				p1.getCard(deck.dealCard());
				iterator++;
			}
			else
			{
				p2.getCard(deck.dealCard());
				iterator--;
			}
		}
		return;
	}

	public static void startBattle(Player p1, Player p2)
	{
		if(checkEndGame(p1, p2) == 0)
		{
			p1.play(true);
			p2.play(true);
			return;
		}
	}

	public static Player resolveBattle(Player p1, Player p2)
	{
		WarCard p1TopCard = p1.peekCurrent();
		WarCard p2TopCard = p2.peekCurrent();
		if(p1TopCard.compareTo(p2TopCard) == 0)
		{
			return null;
		}
		else if(p1TopCard.compareTo(p2TopCard) > 0)
		{
			return p1;
		}
		else
		{
			return p2;
		}
	}

	public static void war(Player p1, Player p2)
	{
		if(checkEndGame(p1, p2) == 0)
		{
			p1.play(false);
			p2.play(false);
			return;	
		}
		else
			return;
	}

	public static void giveSpoils(Player winner, Player loser)
	{
		while(!winner.isFieldEmpty() || !loser.isFieldEmpty())
		{
			WarCard tempCard;
			if(!loser.isFieldEmpty())
			{
				tempCard = loser.giveSpoils();
				tempCard.flip(true);
				winner.getCard(tempCard);
			}
			if(!winner.isFieldEmpty())
			{
				tempCard = winner.giveSpoils();
				tempCard.flip(true);
				winner.getCard(tempCard);
			}
		}
		return;
	}

	public static int checkEndGame(Player p1, Player p2)
	{
		if(p1.isDeckEmpty() || p2.isDeckEmpty())
		{
			if(p2.isDeckEmpty())
				return 2;
			else if(p1.isDeckEmpty())
				return 1;
			else 
				return 3;
		}
		else
			return 0;
	}

	public static void main(String[] args) 
	{
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		WarGame game = new WarGame(player1, player2);
		Player winner = null;
		System.out.println("\t" + player1.getName() + "\t" + player2.getName());
		boolean gameOver = false;
		while(game.checkEndGame(player1,player2) == 0)
		{
			while(winner == null && gameOver == false)
			{
           	game.startBattle(player1, player2);
            System.out.println("\t" + player1.peekCurrent() + "\t" + player2.peekCurrent());
				winner = resolveBattle(player1, player2);
            if(winner == player1)
               System.out.println("\t\tWinner!");
            else if(winner == player2)
               System.out.println("\t\t\t\tWinner!");
				else if(winner == null && (game.checkEndGame(player1,player2) == 0))
				{
					game.war(player1, player2);
               System.out.println("\t\t\tWAR!!");
				}
            else
               gameOver = true;
			}
         if(winner == player1)
				giveSpoils(winner, player2);
			else if(winner == player2)
				giveSpoils(winner, player1);
         else;
         winner = null;
		}

		switch(game.checkEndGame(player1, player2))
		{
			case 1: 
			System.out.println(player1.getName() + " has lost!");
         break;
			case 2:
			System.out.println(player2.getName() + " has lost!");
         break;
			case 0:
			System.out.println("Something went wrong");
         break;
         case 3:
			System.out.println("No Winners! You all lost!");
		}
	}
}