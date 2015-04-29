// Joshua D Sachar
// CS 110
// February 26, 2015
// Card Class
/**
An object that simulates a playing card.
This object has a Suit and a Rank. When Built, these need to be provided. A copy constructor is also available.
*/

// Class Card
public class Card
{
	// Class Variables
	private int suit, rank;
	// suits and ranks
	public final static int HEARTS=1, DIAMONDS=2, CLUBS=3, SPADES=4;
	public final static int ACE=1,TWO=2,THREE=3,FOUR=4,FIVE=5,SIX=6,SEVEN=7,EIGHT=8,NINE=9,TEN=10,JACK=11,QUEEN=12,KING=13;

	// Constuctor
	/**
	 *Primary Constructor.
	 *@param int An Integer expected to be a value between 1 and 13, representing the card's rank.
	 *@param int An Integer expected to be a value between 1 and 4, representing the card's suit.
	 *Should a value outside the expected be provided, the defaults wil be implimented, being "HEARTS" for suit and "ACE" for rank.
	 */
	public Card (int r, int s)
	{
		if (s <= SPADES && s >= HEARTS)
			suit = s;
		else 
			suit = HEARTS;
		if (r <= KING && r >= ACE)
			rank = r;
		else
			rank = ACE;
		return;
	}
	/**
 	 *	Copy Constructor.
 	 *	@param Card A Card object who's properites will be copied.
	 */
	public Card (Card notThis)
	{
		this(notThis.getRank(), notThis.getSuit());
	}
	/**
 	 *	toString Method
 	 *	@return String representing the card's current states. ("Rank of Suits")
	 */
	public String toString()
	{
		String returnString = rankToString() + " of " + suitToString();
		return (returnString);
	}
	/**
 	 *	getRank Method
 	 *	@return int The Integer Value of the Card's Rank, Ace being 1.
	 */
	public int getRank()
	{
		int returnValue = rank;
		return returnValue;
	}
	/**
 	 *	getSuit Method
 	 *	@return int The Integer Value of the Card's Suit, Heart's being 1, Diamond's 2, Club's 3, and Spades 4.
	 */
	public int getSuit()
	{
		int returnValue = suit;
		return returnValue;
	}
	/**
 	 *	rankToString Method
 	 *	@return String The String representation of the Card's Rank.
	 */
	public String rankToString()
	{
		switch (rank)
		{
			case 1:
			return "Ace";
			case 2:
			return "Two";
			case 3:
			return "Three";
			case 4:
			return "Four";
			case 5:
			return "Five";
			case 6:
			return "Six";
			case 7:
			return "Seven";
			case 8:
			return "Eight";
			case 9:
			return "Nine";
			case 10:
			return "Ten";
			case 11:
			return "Jack";
			case 12:
			return "Queen";
			case 13:
			return "King";
			default:
			return "Something Has Gone Horribly Wrong!";
		}
	}
	/**
 	 *	suitToString Method
 	 *	@return String The String representation of the Card's Suit.
	 */
	public String suitToString()
	{
		switch (suit)
		{
			case 1:
			return "Hearts";
			case 2:
			return "Diamonds";
			case 3:
			return "Clubs";
			case 4:
			return "Spades";
			default:
			return "Something Has Gone Horribly Wrong!";
		}
	}
	/**
 	 *	equals Method
 	 *	@param	Card 	A card to compare this with.
 	 *	@return boolean The result of comparing this card to the @param. If they are the same rank and suit, this returns true.
	 */
	public boolean equals(Card otherCard)
	{
		if((this.getRank() == otherCard.getRank()) && (this.getSuit() == otherCard.getSuit()))
			return true;
		else
			return false;
	}
/*
	This was a test method to verify class functionality.
	public static void main(String [] args)
	{
		Card c1 = new Card(155,34);
		Card c2 = new Card(Card.TWO,Card.CLUBS);
		Card c3 = new Card(Card.QUEEN,Card.DIAMONDS);
		Card c4 = new Card(Card.ACE,Card.HEARTS);
		Card c5 = new Card(2,Card.SPADES);
		Card c6 = new Card(Card.ACE,Card.SPADES);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c5);
		System.out.println(c6);
		System.out.println(c1.equals(c2));
		System.out.println(c1.equals(c3));
		System.out.println(c1.equals(c4));
		System.out.println(c1.equals(c5));
		System.out.println(c1.equals(c6));
		}
*/
}