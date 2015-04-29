// Joshua D Sachar
// CS 110
// WarDeck Class - Class that contains a deck (or decks) of cards.

import java.util.Random;
import java.util.ArrayList;

public class WarDeck
{
	private ArrayList<WarCard> deck;

	
	/**
	 * Default Constructor
	 * Builds a deck of 52 cards, 1 of each card
	 */
	public WarDeck()
	{
		this(1);
	}
	
	/**
	 * Constructor
	 * Builds a number of complete decks equal to the provided integer parameter
	 * @param int Number of Complete Decks to place in this deck of cards
	 */
	public WarDeck(int d)
	{
		deck = new ArrayList<WarCard>();
		for(int i = 1; i <= d; i++)
		{
			for(int j = 1; j < 14; j++)
			{
				for(int k = 1; k < 5; k++)
				{
					deck.add(new WarCard(j,k));
				}
			}
		}
		this.shuffle();
	}

	/**
	 * shuffle Method
	 * Randomizes the deck of cards, "Shuffling" it.
	 */
	public void shuffle()
	{
		int randomNumber;
		WarCard temporaryCard;
		Random r = new Random();
		for(int i = 0; i < deck.size(); i++)
		{
			randomNumber = r.nextInt(deck.size());
			temporaryCard = deck.get(i);
			deck.set(i,deck.get(randomNumber));
			deck.set(randomNumber, temporaryCard);
		}
	}

	/**
	 * isEmpty Method
	 * Returns true if there are no cards left in deck.
	 * @return boolean Is the Deck Empty?
	 */
	public boolean isEmpty()
	{
		return(deck.size() == 0);
	}

	/**
	 * cardsRemaining Method
	 * Returns the number of Card objects left in the deck
	 * @return int Number of cards remaining in the deck
	 */
	public int cardsRemaining()
	{
		return deck.size();
	}

	/**
	 * dealCard Method
	 * "Deals" the top card, returning a WarCard object.
	 * @return WarCard Removes "Top Card of the Deck" and returns it.
	 */
	public WarCard dealCard()
	{
		WarCard returnValue = deck.remove(0);
		return returnValue;
	}

	/*public static void main(String[] args) 
	{
		WarDeck deck = new WarDeck(1);
		WarCard tempCard;
		while(!deck.isEmpty())
		{
			tempCard = deck.dealCard();
			tempCard.flip();
			System.out.println(tempCard);
		}
	}*/
}