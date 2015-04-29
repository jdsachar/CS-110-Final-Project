// Joshua D Sachar
// CS 110
// Player Class
// Player was initially designed for my N player War build, but when I ran into issues with that I just reused it for the 2 player War.

public class Player
{
	private String name;
	private boolean computer, active;
	private QueueReferenceBased deck;
	private StackReferenceBased field;

	/**
	 * Primary Constructor
	 * Builds a "Player" object with a provided name. Can be flagged as a "Computer" player depending on provided argument
	 * @param String Name of Player
	 * @param boolean Is this a Computer Controlled Player?
	 */
	public Player(String n, boolean c)
	{
		name = n;
		computer = c;
		active = true;
		deck = new QueueReferenceBased();
		field = new StackReferenceBased();
	}
	/**
	 * Alternate Constructor
	 * Builds a "Player" object with a provided name. Is flagged as not a "Computer" player.
	 * @param String Name of Player
	 */
	public Player(String n)
	{
		name = n;
		computer = false;
		active = true;
		deck = new QueueReferenceBased();
		field = new StackReferenceBased();
	}
	/**
	 * getCard Method
	 * Adds the parameter WarCard to the Player's Deck
	 * @param WarCard Card to be added to this player's deck
	 */
	public void getCard(WarCard c)
	{
		c.flip(false);
		deck.enqueue(c);
	}
	/**
	 * play Method
	 * Places the Top Card of the Player's deck on the top of the playing field stack. 
	 */
	public void play()
	{
		if(getState())
			field.push(deck.dequeue());
		else
			active = false;
	}
	/**
	 * play Method - Alternate
	 * Accepts a boolean setting Card to face Up if true, face down if false. Then takes top card of Player's deck and places it on top of the playing field stack.
	 * @param boolean Play this card Face Up?
	 */
	public void play(boolean f)
	{
		WarCard temp = (WarCard)deck.dequeue();
		if(f == temp.getFace())
			field.push(temp);
		else
		{
			temp.flip();
			field.push(temp);
		}
		return;
	}
	/**
	 * giveSpoils Method
	 * Returns the top card of this Player's field.
	 * @return WarCard Top WarCard of this player's field.
	 */
	public WarCard giveSpoils()
	{
		return (WarCard)field.pop();
	}
	/**
	 * isFieldEmpty Method
	 * Returns true if the field has no cards in it.
	 * @return boolean is the Field Empty?
	 */
	public boolean isFieldEmpty()
	{
		return field.isEmpty();
	}
	/**
	 * isDeckEmpty Method
	 * Returns true if the deck has no cards in it.
	 * @return boolean is the Deck Empty?
	 */
	public boolean isDeckEmpty()
	{
		return deck.isEmpty();
	}
	/**
	 * getState Method
	 * Returns true if the player can still play. (If the deck is not empty)
	 * @return boolean Is this player still in the game?
	 */
	public boolean getState()
	{
		if(isDeckEmpty())
			active = false;
		return active;
	}
	/**
	 * giveSpoils Method
	 * Returns a copy of the top card of this Player's field, for comparing.
	 * @return WarCard A copy of the Top WarCard of this player's field.
	 */
	public WarCard peekCurrent()
	{
		WarCard temp;
		if(!isFieldEmpty())
			temp = (WarCard)field.peek();
		else 
			temp = null;
		return temp;
	}
	/**
	 * getName Method
	 * Returns this player's name as a String
	 * @return String Players Name
	 */
	public String getName()
	{
		String returnValue = name;
		return returnValue;
	}
	// Test Function
	public static void main(String[] args) 
	{
		Player player1 = new Player("Bob", false);
		Player player2 = new Player("George", false);

		Player [] parts = {player1, player2};
		WarDeck deck = new WarDeck(1);
		int currentPlayer = 0;
		while(!deck.isEmpty())
		{
			parts[currentPlayer].getCard(deck.dealCard());
			currentPlayer++;
			if (currentPlayer == parts.length)
				currentPlayer = 0;
		}

		for(int i = 0; i < parts.length; i++)
			parts[i].play(false);
		WarCard compare1 = player1.peekCurrent();
		WarCard compare2 = player2.peekCurrent();

		if(compare1.compareTo(compare2) > 0)
			System.out.println(compare1 + " beats " + compare2);
		else if(compare1.compareTo(compare2) < 0)
			System.out.println(compare2 + " beats " + compare1);
		else
			System.out.println(compare1 + " equals " + compare2);
	}
}