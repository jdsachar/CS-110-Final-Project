// Joshua D Sachar
// CS 110
// WarCardGameGUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridBagLayout;

// GUI Interface for Playing War(Card Game)
public class WarCardGameGUI extends JFrame
{
	// Declare Variables
	private WarGame game;
	private JPanel player1Side, player2Side, center;
	private JButton play;
	private JLabel player1Deck, player2Deck, player1Field, player2Field, player1Label, player2Label;
	// Declare and set Players
	private Player p1 = new Player("Player 1");
	private Player p2 = new Player("Player 2");
	// Standard Layout for each player
	private GridLayout standardPlayerLayout = new GridLayout(1,0,10,10);

	// Main Constructor
	public WarCardGameGUI()
	{
		// Intiialize Game
		game = new WarGame(p1,p2);
		// Base Layout
		setLayout(new BorderLayout());
		// Center Panel initialize
		center = new JPanel(new GridLayout(0,1,5,5));
		// set End on Close
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// set title
		this.setTitle("It ain't peace...");
		// Player1Side initialize
		player1Side = new JPanel(standardPlayerLayout);
		// Set Background to red for Player 1
		player1Side.setBackground(Color.red);
		// Player1Side initialize
		player2Side = new JPanel(standardPlayerLayout);
		// Set Background to blue for Player 2
		player2Side.setBackground(Color.blue);
		// Initialize "Play" button - actually a multipurpose button
		play = new JButton();
		play.setText("Play!");
		play.setFont(new Font("Serif" , Font.BOLD, 18));
		// Add listener
		play.addActionListener(new listener());

		// Initialize the fields and decks, start Blank Fields, Back of cards for decks
		player1Field = new JLabel(new ImageIcon("blank.jpg"));
		player2Field = new JLabel(new ImageIcon("blank.jpg"));
		player1Deck = new JLabel(new ImageIcon("back.jpg"));
		player2Deck = new JLabel(new ImageIcon("back.jpg"));

		// Set player labels
		player1Label = new JLabel(p1.getName());
		player1Label.setHorizontalAlignment(JLabel.CENTER);
		player1Label.setFont(new Font("Serif" , Font.PLAIN, 24));
		player2Label = new JLabel(p2.getName());
		player2Label.setHorizontalAlignment(JLabel.CENTER);
		player2Label.setFont(new Font("Serif" , Font.PLAIN, 24));

		// Add componants to Layouts
		player2Side.add(player2Field);
		player2Side.add(player2Deck);
		center.add(player2Label);
		center.add(play);
		center.add(player1Label);
		player1Side.add(player1Deck);
		player1Side.add(player1Field);	
		add(player2Side, BorderLayout.PAGE_START);
		add(center, BorderLayout.CENTER);
		add(player1Side, BorderLayout.PAGE_END);
		// Update Deck and Field Labels. (May be unnessicary)
		updateDeckLabels(p1, p2);
		updateFieldLabels(p1, p2);
		
	}

	// Updates Deck Labels, showing back of Card if cards remain in deck, Skull and crossbones if deck is empty
	public void updateDeckLabels(Player p1, Player p2)
	{
		// Set icons
		ImageIcon back = new ImageIcon("back.jpg");
		ImageIcon skull = new ImageIcon("skull.jpg");
		// If Block
		if(!p1.isDeckEmpty())
			player1Deck.setIcon(back);
		else
			player1Deck.setIcon(skull);
		if(!p2.isDeckEmpty())
			player2Deck.setIcon(back);
		else
			player2Deck.setIcon(skull);
	}

	// Updates Field Labels, showing appropriate label based on a method call to WarCard.getFace(), blank if field is empty.
	public void updateFieldLabels(Player p1, Player p2)
	{
		if(p1.isFieldEmpty())
		{
			player1Field.setIcon(new ImageIcon("blank.jpg"));
		}
		else
		{
			player1Field.setIcon(new ImageIcon(p1.peekCurrent().getFaceIcon()));
			//System.out.println(p1.peekCurrent().getFaceIcon());
		}
		if(p2.isFieldEmpty())
		{
			player2Field.setIcon(new ImageIcon("blank.jpg"));
		}
		else
		{	
			player2Field.setIcon(new ImageIcon(p2.peekCurrent().getFaceIcon()));
			//System.out.println(p2.peekCurrent().getFaceIcon());
		}
		// Set Visible forces imageicons to update
		player1Field.setVisible(true);
		player2Field.setVisible(true);
	}

	// Main
	public static void main(String[] args) 
	{
		WarCardGameGUI war = new WarCardGameGUI();
		war.setSize(350,750);
		war.validate();
		war.setVisible(true);
	}

	// listener
	private class listener implements ActionListener 
	{
		// declare variables
		private Player winner;
		private int state;

		// Action Performed
		public void actionPerformed(ActionEvent g)
		{
			// If Button text is "Play", play top card of deck to field, as long as no one has lost
			if(play.getText().equals("Play!") && game.checkEndGame(p1,p2) == 0)
			{
				// Start battle, then update labels to reflect card playes
				game.startBattle(p1,p2);
				updateFieldLabels(p1,p2);
				updateDeckLabels(p1,p2);

				// Winner holds the return value from the battle resoilution method in game 
				winner = game.resolveBattle(p1, p2);
				if(winner == p1)
				{
					// Changes state of button text, next action should start a pickup method
					play.setText(p1.getName() + ": Click To Pick Up");
				}
				else if(winner == p2)
				{
					// Changes state of button text, next action should start a pickup method
					play.setText(p2.getName() + ": Click To Pick Up");
				}
				else if(winner == null && (game.checkEndGame(p1,p2) == 0)) // Starts a war if no winner is returned
					play.setText("WAR!");
				else
				{
					// If checkEndGame returns a non-zero value and winner is null, discernes who has lost.
					switch(game.checkEndGame(p1,p2))
					{
						case 1: 
						play.setText(p1.getName() + " has lost!");
						play.disable();
						break;
						case 2:
						play.setText(p2.getName() + " has lost!");
						play.disable();
						break;
						case 0:
						play.setText("Something went wrong");
						play.disable();
						break;
						case 3:
						play.setText("No Winners! You all lost!");
						play.disable();
						break;
					}
				}
			}
			// if Button is set to "WAR!", calls war method in game
			else if(play.getText().equals("WAR!"))
			{
				game.war(p1,p2);
				play.setText("Play!");
			}
			// Pickup Cards method for player 1
			else if(play.getText().equals(p1.getName() + ": Click To Pick Up"))
			{
				game.giveSpoils(p1,p2);
				play.setText("Play!");
			}
			// Pickup Cards method for player 2
			else if(play.getText().equals(p2.getName() + ": Click To Pick Up"))
			{
				game.giveSpoils(p2,p1);
				play.setText("Play!");
			}
			// Find out who lost and report back
			else
				{
					switch(game.checkEndGame(p1,p2))
					{
						case 1: 
						play.setText(p1.getName() + " has lost!");
						play.disable();
						break;
						case 2:
						play.setText(p2.getName() + " has lost!");
						play.disable();
						break;
						case 0:
						play.setText("Something went wrong");
						play.disable();
						break;
						case 3:
						play.setText("No Winners! You all lost!");
						play.disable();
						break;
					}
				}
		// Each button press will force a field and deck label update.
         updateFieldLabels(p1,p2);
         updateDeckLabels(p1,p2);
		}
	}


	
}
