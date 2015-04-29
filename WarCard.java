// Joshua D Sachar
// 4/25/2015
// Class WarCard
// Adds some functionality to the Card class for the "War" Card Game.

public class WarCard extends Card
{
	private boolean face;

	// Constuctor
	/**
	 *Primary Constructor.
	 *@param int An Integer expected to be a value between 1 and 13, representing the card's rank.
	 *@param int An Integer expected to be a value between 1 and 4, representing the card's suit.
	 *Should a value outside the expected be provided, the defaults wil be implimented, being "HEARTS" for suit and "ACE" for rank.
	 */
	public WarCard (int r, int s)
	{
		super(r,s);
		face = true;
		return;
	}
	/**
 	 *	Copy Constructor.
 	 *	@param Card A Card object who's properites will be copied.
	 */
	public WarCard (Card notThis)
	{
		super(notThis.getRank(), notThis.getSuit());
		face = true;
	}

	/**
	 * compareTo Method compares this WarCard to another WarCard
	 * @param WarCard Card to compare to.
	 * @return int difference between this card and parameter card (Aces High).
	 */
	public int compareTo(WarCard c)
	{
		int thisCardRank, thatCardRank;
		if (this.getRank() == 1)
			thisCardRank = 14;
		else 
			thisCardRank = this.getRank();
		if (c.getRank() == 1)
			thatCardRank = 14;
		else
			thatCardRank = c.getRank();
		return thisCardRank - thatCardRank;
	}

	/**
	 * equals compares this WarCard to another WarCard
	 * @param WarCard Card to compare to.
	 * @return boolean returns true if both WarCards are the same rank.
	 */
	public boolean equals(WarCard c)
	{
		boolean returnValue;
		if(this.getRank() == c.getRank())
			returnValue = true;
		else
			returnValue = false;
		return returnValue;
	}

	/**
	 * getFaceIcon returns the file name of the face icon. (If card is "face down", returns "back.jpg" file)
	 * @return String File name of the card's face.
	 */
	public String getFaceIcon()
	{
		String returnValue = "";
		int r = this.getRank(), s = this.getSuit();
		switch (r)
		{
			case 1:
			returnValue += "ace";
			break;
			case 11:
			returnValue += "jack";
			break;
			case 12:
			returnValue += "queen";
			break;
			case 13:
			returnValue += "king";
			break;
			default:
			returnValue += r;
			break;
		}
		switch (s)
		{
			case 1:
			returnValue += "h";
			break;
			case 2:
			returnValue += "d";
			break;
			case 3:
			returnValue += "c";
			break;
			case 4:
			returnValue += "s";
			break;
		}
		if (face)
			returnValue += ".jpg";
		else
			returnValue = "back.jpg";

		return returnValue;
	}

	/**
 	 *	toString Method
 	 *	@return String representing the card's current states. ("Rank of Suits") (Returns "Back" if face is down)
	 */
	public String toString()
	{
		String returnString;
		if(face)
			returnString = rankToString() + " of " + suitToString();
		else
			returnString = "Back of Card";
		return (returnString);
	}

	/**
	 * flip Method Flips a face down card face up, and vice versa.
	 */
	public void flip()
	{
		if(face)
			face = false;
		else
			face = true;
	}
   
   public void flip(boolean f)
   {
      if(face != f)
			face = f;
      return;
   }
	/**
	 * getFace checks card's face up/down
	 * @return boolean Is Card Face Up?
	 */

	public boolean getFace()
	{
		boolean returnValue = face;
		return returnValue;
	}

	// test function
	/*public static void main(String[] args) 
	{
		WarCard c1 = new WarCard(4,Card.HEARTS);
		WarCard c2 = new WarCard(1,Card.SPADES);
		WarCard c3 = new WarCard(11,Card.CLUBS);
		WarCard c4 = new WarCard(7,Card.HEARTS);
		WarCard c5 = new WarCard(5,Card.DIAMONDS);

		System.out.println(c1 + "\n" + c2 + "\n" + c3 + "\n" + c4 + "\n" + c5 + "\n");
		System.out.println(c1.compareTo(c2));
		System.out.println(c1.compareTo(c3));
		System.out.println(c1.compareTo(c4));
		System.out.println(c1.compareTo(c5));
		c1.flip();
		c3.flip();
		c5.flip();
		System.out.println(c1 + "\n" + c2 + "\n" + c3 + "\n" + c4 + "\n" + c5 + "\n");
		c2.flip();
		c3.flip();
		c4.flip();
		System.out.println(c1 + "\n" + c2 + "\n" + c3 + "\n" + c4 + "\n" + c5 + "\n");
		c3.flip();
		System.out.println(c1 + "\n" + c2 + "\n" + c3 + "\n" + c4 + "\n" + c5 + "\n");
	}*/
}