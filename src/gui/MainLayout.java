package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainLayout extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	/*public MainLayout () {
		
		// dimension de la fenêtre : 900*600
		
		
		
		super();
		
		this.setSize(900,600);
		this.setBackground(Color.green);
		
		JPanel cell1 = new JPanel();
		JPanel cell2 = new JPanel();
		JPanel cell3 = new JPanel();
		JPanel cell4 = new JPanel();
		JPanel cell5 = new JPanel();
		
		cell1.setBackground(Color.white);
		cell2.setBackground(Color.red);
		cell4.setBackground(Color.blue);
		cell5.setBackground(Color.DARK_GRAY);
		
		cell3.setBackground(Color.gray);
		
		cell1.setPreferredSize(new Dimension(600, 10));
		cell2.setPreferredSize(new Dimension(10, 880));
		cell3.setPreferredSize(new Dimension(440, 880));
		cell4.setPreferredSize(new Dimension(150, 880));
		cell5.setPreferredSize(new Dimension(600, 10));
		
		//************************  ************************
		this.setLayout(new GridBagLayout());
		
		//************************ Positionnement des composants ************************
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 2;
		this.add(cell1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.add(cell2, gbc);
		
		gbc.gridx = 1;
		this.add(cell3, gbc);
		
		gbc.gridx = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(cell4, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 2;
		this.add(cell5, gbc);		
	}*/
	
	
	public MainLayout (JPanel playPannel) {
		super();
		
		this.setSize(400,300);
		this.setBackground(Color.black);
		
		JPanel cell1 = new JPanel();
		JPanel cell2 = new JPanel();
		JPanel cell3 = new JPanel();
		JPanel cell4 = new JPanel();
		JPanel cell5 = new JPanel();
		JPanel cell6 = new JPanel();
		JPanel cell7 = new JPanel();
		JPanel cell8 = new JPanel();
		JPanel cell9 = new JPanel();
		JPanel cell10 = new JPanel();
		JPanel cell11 = new JPanel();
		JPanel cell12 = new JPanel();
		JPanel cell13 = new JPanel();
		
		JPanel cell14 = new JPanel();
		JPanel cell15 = new JPanel();
		JPanel cell16 = new JPanel();
		JPanel cell17 = new JPanel();
		JPanel cell18 = new JPanel();
		JPanel cell19 = new JPanel();
		JPanel cell20 = new JPanel();
		JPanel cell21 = new JPanel();
		JPanel cell22 = new JPanel();
		JPanel cell23 = new JPanel();
		JPanel cell24 = new JPanel();
		
		JLabel test = new JLabel("test");
		cell24.add(test);
		
		JPanel cell25 = new JPanel();
		JPanel cell26 = new JPanel();
		JPanel cell27 = new JPanel();
		JPanel cell28 = new JPanel();
		JPanel cell29 = new JPanel();
		JPanel cell30 = new JPanel();
		JLabel score = new JLabel("score");
		cell30.add(score);
		
		JPanel cell31 = new JPanel();
		JPanel cell32 = new JPanel();
		JPanel cell33 = new JPanel();
		JPanel cell34 = new JPanel();
		JPanel cell35 = new JPanel();
		JPanel cell36 = new JPanel();
		JLabel level = new JLabel("level");
		cell36.add(level);
		JPanel cell37 = new JPanel();
		JPanel cell38 = new JPanel();
		JPanel cell39 = new JPanel();
		JPanel cell40 = new JPanel();
		JPanel cell41 = new JPanel();
		JPanel cell42 = new JPanel();
		JLabel lines = new JLabel("lines");
		cell42.add(lines);
		JPanel cell43 = new JPanel();
		JPanel cell44 = new JPanel();
		JPanel cell45 = new JPanel();
		JPanel cell46 = new JPanel();
		JPanel cell47 = new JPanel();
		JPanel cell48 = new JPanel();
		JPanel cell49 = new JPanel();
		JPanel cell50 = new JPanel();
		JPanel cell51 = new JPanel();
		
		JPanel bot = new JPanel();
		
		cell1.setBackground(Color.DARK_GRAY);
		cell2.setBackground(Color.GRAY);
		cell3.setBackground(Color.DARK_GRAY);
		cell4.setBackground(Color.GRAY);
		cell5.setBackground(Color.DARK_GRAY);
		cell6.setBackground(Color.GRAY);
		cell7.setBackground(Color.DARK_GRAY);
		cell8.setBackground(Color.GRAY);
		cell9.setBackground(Color.DARK_GRAY);
		cell10.setBackground(Color.GRAY);
		cell11.setBackground(Color.DARK_GRAY);
		cell12.setBackground(Color.GRAY);
		cell13.setBackground(Color.DARK_GRAY);
		cell14.setBackground(Color.DARK_GRAY);
		cell15.setBackground(Color.white);
		cell16.setBackground(Color.gray);
		cell17.setBackground(Color.white);
		cell18.setBackground(Color.DARK_GRAY);
		cell19.setBackground(Color.white);
		cell20.setBackground(Color.DARK_GRAY);
		cell21.setBackground(Color.white);
		cell22.setBackground(Color.DARK_GRAY);
		cell23.setBackground(Color.white);
		cell24.setBackground(Color.DARK_GRAY);
		cell25.setBackground(Color.white);
		cell26.setBackground(Color.white);
		cell27.setBackground(Color.gray);
		cell28.setBackground(Color.white);
		cell29.setBackground(Color.white);
		cell30.setBackground(Color.DARK_GRAY);
		cell31.setBackground(Color.white);
		cell32.setBackground(Color.white);
		cell33.setBackground(Color.gray);
		cell34.setBackground(Color.white);
		cell35.setBackground(Color.white);
		cell36.setBackground(Color.DARK_GRAY);
		cell37.setBackground(Color.white);
		cell38.setBackground(Color.white);
		cell39.setBackground(Color.gray);
		cell40.setBackground(Color.white);
		cell41.setBackground(Color.white);
		cell42.setBackground(Color.DARK_GRAY);
		cell43.setBackground(Color.white);
		cell44.setBackground(Color.white);
		cell45.setBackground(Color.DARK_GRAY);
		cell46.setBackground(Color.DARK_GRAY);
		cell47.setBackground(Color.DARK_GRAY);
		cell48.setBackground(Color.DARK_GRAY);
		cell49.setBackground(Color.white);
		cell50.setBackground(Color.DARK_GRAY);
		cell51.setBackground(Color.DARK_GRAY);
		bot.setBackground(Color.DARK_GRAY);
		
		
		cell1.setPreferredSize(new Dimension (20, 20));
		cell2.setPreferredSize(new Dimension (10, 20));
		cell3.setPreferredSize(new Dimension (200, 20));
		cell4.setPreferredSize(new Dimension (10, 20));
		cell5.setPreferredSize(new Dimension (20, 20));
		cell6.setPreferredSize(new Dimension (20, 20));
		cell7.setPreferredSize(new Dimension (20, 20));
		cell8.setPreferredSize(new Dimension (20, 20));
		cell9.setPreferredSize(new Dimension (20, 20));
		cell10.setPreferredSize(new Dimension (10, 20));
		cell11.setPreferredSize(new Dimension (150, 20));
		cell12.setPreferredSize(new Dimension (10, 20));
		cell13.setPreferredSize(new Dimension (20, 20));
		//cell13.setPreferredSize(new Dimension (20, 20));
		cell14.setPreferredSize(new Dimension (20, 300));
		cell15.setPreferredSize(new Dimension (220, 10));
		
		cell18.setPreferredSize(new Dimension (20, 100));
		cell19.setPreferredSize(new Dimension (10, 400));
		cell20.setPreferredSize(new Dimension (200, 400));
		cell21.setPreferredSize(new Dimension (10, 400));
		
		cell22.setPreferredSize(new Dimension (400, 100));
		cell24.setPreferredSize(new Dimension (400, 130));
		cell27.setPreferredSize(new Dimension (10, 20));
		cell30.setPreferredSize(new Dimension (150, 100));
		cell33.setPreferredSize(new Dimension (10, 20));

		cell36.setPreferredSize(new Dimension (150, 100));
		
		cell39.setPreferredSize(new Dimension (10, 20));

		cell42.setPreferredSize(new Dimension (150, 100));
		//cell46.setPreferredSize(new Dimension (10, 20));
		cell51.setPreferredSize(new Dimension (10, 20));
		
		
		bot.setPreferredSize(new Dimension (200, 20));
		
		//************************  ************************
		this.setLayout(new GridBagLayout());
		
		//************************ Positionnement des composants ************************
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.add(cell1, gbc);
		
		gbc.gridx = 1;
		this.add(cell2, gbc);
		
		gbc.gridx = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell3, gbc);
		
		gbc.gridx = 3;
		this.add(cell4, gbc);
		
		gbc.gridx = 4;
		this.add(cell5, gbc);
		
		gbc.gridx = 5;
		this.add(cell6, gbc);
		
		gbc.gridx = 6;
		this.add(cell7, gbc);
		
		gbc.gridx = 7;
		this.add(cell8, gbc);
		
		gbc.gridx = 8;
		this.add(cell9, gbc);
		
		gbc.gridx = 9;
		this.add(cell10, gbc);
		
		gbc.gridx = 10;
		this.add(cell11, gbc);
		
		gbc.gridx = 11;
		this.add(cell12, gbc);
		
		gbc.gridx = 12;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(cell13, gbc);
		
		//******************************************
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 21;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell14, gbc);
		
		gbc.gridx = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell15, gbc);
		
		gbc.gridx = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell16, gbc);
		
		gbc.gridx = 9;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		this.add(cell17, gbc);
		
		gbc.gridx = 12;
		gbc.gridheight = 21;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(cell18, gbc);
		
		//******************************************	
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 18;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell19, gbc);	
		
		gbc.gridx = 2;
		gbc.fill = GridBagConstraints.NONE;
		this.add(playPannel, gbc);
		
		gbc.gridx = 3;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell21, gbc);
		
		gbc.gridx = 4;
		gbc.gridwidth = 5;
		this.add(cell22, gbc);
		
		gbc.gridx = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell23, gbc);
		
		gbc.gridx = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(cell24, gbc);		
		
		gbc.gridx = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell25, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 9;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell26, gbc);
		
		gbc.gridy = 4;
		this.add(cell27, gbc);	
		
		gbc.gridy = 5;
		this.add(cell28, gbc);	
		
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell29, gbc);	
		
		gbc.gridx ++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell30, gbc);	
		
		gbc.gridx ++;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell31, gbc);	
		
		gbc.gridx -=2;
		gbc.gridy = 7;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell32, gbc);	
		
		gbc.gridy = 8;
		this.add(cell33, gbc);	
		
		gbc.gridy = 9;
		this.add(cell34, gbc);	
		
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell35, gbc);	
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell36, gbc);	

		gbc.gridx++;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell37, gbc);	
		
		gbc.gridx -=2;
		gbc.gridy = 11;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell38, gbc);	
		
		gbc.gridy = 12;
		this.add(cell39, gbc);	
		
		gbc.gridy = 13;
		this.add(cell40, gbc);
		
		gbc.gridy = 14;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell41, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell42, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(cell43, gbc);
		
		gbc.gridx -= 2;
		gbc.gridy = 15;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell44, gbc);	
		
		gbc.gridy = 16;
		this.add(cell45, gbc);	
		
		gbc.gridy = 17;
		this.add(cell46, gbc);	
		
		gbc.gridy = 18;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(cell47, gbc);	
		
		gbc.gridy = 19;
		this.add(cell48, gbc);	
		
		gbc.gridy++;
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cell49, gbc);
		
		gbc.gridx = 4;
		gbc.gridwidth = 9;
		this.add(cell50, gbc);
		
		gbc.gridy++;
		gbc.gridx = 1;
		gbc.gridwidth = 11;
		this.add(cell51, gbc);
		
	}
}





















