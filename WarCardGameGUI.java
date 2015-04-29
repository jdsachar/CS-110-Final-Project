// Joshua D Sachar
// CS 110
// WarCardGameGUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridBagLayout;

public class WarCardGameGUI extends JFrame
{
	private WarGame game;
	private JPanel player1Side, player2Side, center;
	private JButton play;
	private JLabel player1Deck, player2Deck, player1Field, player2Field, player1Label, player2Label;
	private Player p1 = new Player("Player 1");
	private Player p2 = new Player("Player 2");
//	private String playButtonLabel;
	private GridLayout standardPlayerLayout = new GridLayout(1,0,10,10);

	public WarCardGameGUI()
	{
		game = new WarGame(p1,p2);
		
		//setLayout(new GridLayout(0,1,5,5));
		setLayout(new BorderLayout());

		center = new JPanel(new GridLayout(0,1,5,5));
		player1Side = new JPanel(standardPlayerLayout);
		player1Side.setBackground(Color.red);
		player2Side = new JPanel(standardPlayerLayout);
		player2Side.setBackground(Color.blue);
		
		play = new JButton();
		play.setText("Play!");
		play.addActionListener(new listener());

		player1Field = new JLabel(new ImageIcon("blank.jpg"));
		player2Field = new JLabel(new ImageIcon("blank.jpg"));
		player1Deck = new JLabel(new ImageIcon("back.jpg"));
		player2Deck = new JLabel(new ImageIcon("back.jpg"));
		player1Label = new JLabel(p1.getName());
		player1Label.setHorizontalAlignment(JLabel.CENTER);
		player2Label = new JLabel(p2.getName());
		player2Label.setHorizontalAlignment(JLabel.CENTER);

		player2Side.add(player2Field);
		player2Side.add(player2Deck);
		center.add(player2Label);
		center.add(play);
		center.add(player1Label);
		center.setPreferredSize( new Dimension(100,100));
		player1Side.add(player1Deck);
		player1Side.add(player1Field);	

		add(player2Side, BorderLayout.PAGE_START);
		add(center, BorderLayout.CENTER);
		add(player1Side, BorderLayout.PAGE_END);

		updateDeckLabels(p1, p2);
		updateFieldLabels(p1, p2);
		
	}

	public void updateDeckLabels(Player p1, Player p2)
	{
		ImageIcon back = new ImageIcon("back.jpg");
		ImageIcon skull = new ImageIcon("skull.jpg");
		if(!p1.isDeckEmpty())
			player1Deck.setIcon(back);
		else
			player1Deck.setIcon(skull);
		if(!p2.isDeckEmpty())
			player2Deck.setIcon(back);
		else
			player2Deck.setIcon(skull);
	}

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
		player1Field.setVisible(true);
		player2Field.setVisible(true);
	}

	public static void main(String[] args) 
	{
		WarCardGameGUI war = new WarCardGameGUI();
		war.setSize(400,800);
		war.validate();
		war.setVisible(true);
	}

	private class listener implements ActionListener 
	{
		private Player winner;
		private int state;
		public void actionPerformed(ActionEvent g)
		{
			if(play.getText().equals("Play!") && game.checkEndGame(p1,p2) == 0)
			{
				game.startBattle(p1,p2);
				updateFieldLabels(p1,p2);
				updateDeckLabels(p1,p2);
				winner = game.resolveBattle(p1, p2);
				if(winner == p1)
				{
					play.setText(p1.getName() + ": Click To Pick Up");
				}
				else if(winner == p2)
				{
					play.setText(p2.getName() + ": Click To Pick Up");
				}
				else if(winner == null && (game.checkEndGame(p1,p2) == 0))
					play.setText("WAR!");
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
			}
			else if(play.getText().equals("WAR!"))
			{
				game.war(p1,p2);
				play.setText("Play!");
			}
			else if(play.getText().equals(p1.getName() + ": Click To Pick Up"))
			{
				game.giveSpoils(p1,p2);
				play.setText("Play!");
			}
			else if(play.getText().equals(p2.getName() + ": Click To Pick Up"))
			{
				game.giveSpoils(p2,p1);
				play.setText("Play!");
			}
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
         updateFieldLabels(p1,p2);
		}
	}


	
}
