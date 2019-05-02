import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MesoFrame extends JFrame
{
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 1000;
	
	/**
	 * This is a class to create a GUI to obtain information on four letter station codes
	 * Calculates hamming distance and shows outputs
	 * @author nicholasnjoku
	 *
	 */
	private final class MesoPanel extends JPanel 
	{
		
		public MesoPanel()
		{
			this.setPreferredSize(new Dimension(FRAME_WIDTH - 600,FRAME_HEIGHT));
			this.setLayout(new GridBagLayout());
			
		}
	}
	
	
	/**
	 * Reads mesonet.txt file and adds to ArrayList
	 * @return
	 * @throws IOException
	 */
	
	public ArrayList <String> readFile() throws IOException
	{
		// Creates Scanner that will read Mesonnet.txt file
		Scanner scnr = new Scanner(new File("Mesonet.txt"));
		
		// ArrayList that will hold all stations
		ArrayList<String> stations = new ArrayList<String>();
		
		String station = "";
		// Fills ArrayList with individual stations
		while(scnr.hasNextLine())
		{
			station = scnr.nextLine();
			stations.add(station);
			
		}
		
		return stations;
	}
	
	// This section of code is for all the componets of the JFrame (Panels, JLabels, JTextField, etc.)
	private MesoPanel mesoPanel = new MesoPanel();
	private GridBagConstraints mp = new GridBagConstraints();
	private BorderLayout border = new BorderLayout();
	
	// Enter Hamming JLabel and the JTextFiled that displays hamming Distance
	private JLabel enterHam = new JLabel("Enter Hamming Distance:");
	private JTextField hamNum = new JTextField("       ");
	
	// JSlider to change Hamming Distance and finals ints to set size of JSlider
	static final int MIN = 1;
	static final int MAX = 4;
	static final int INIT = 1;
	private JSlider slider = new JSlider(MIN,MAX,INIT);
	
	// "Show Station" JButton, the JTextArea that the stations print out to, and the scroll bar for the JTextArea
	private JButton showStation = new JButton("Show Station");
	private JTextArea stationList = new JTextArea("",18,18);
	private JScrollPane scroll = new JScrollPane(stationList);
	
	// Compare JLabel and the drop down menu that holds all the stations
	private JLabel compare = new JLabel("Compare With:");
	private JComboBox menu = new JComboBox();
	
	// Calculate Hamming Distance button 
	private JButton calcHD = new JButton("Calculate HD");
	
	// JPanel that will hold the distances, and the addStation button uses a GridLayout layout manager 
	private JPanel distances = new JPanel(new GridLayout(6,6));
	private JLabel dist0 = new JLabel("Distance 0");
	private JTextField d0 = new JTextField("");
	private JLabel dist1 = new JLabel("Distance 1");
	private JTextField d1 = new JTextField("");
	private JLabel dist2 = new JLabel("Distance 2");
	private JTextField d2 = new JTextField("");
	private JLabel dist3 = new JLabel("Distance 3");
	private JTextField d3 = new JTextField("");
	private JLabel dist4 = new JLabel("Distance 4");
	private JTextField d4 = new JTextField("");
	private JButton addStation = new JButton("Add Station");
	private JTextField newStation = new JTextField("");
	
	// Label that displays if the adding station feature succeeds 
	private JLabel success = new JLabel("Station Added!!");
	// JLabel that is used to show the Mesonet logo
	private JLabel imageLabel;
	// 
	private JLabel hammBanner = new JLabel("Hamming Distance");
	
	
	public MesoFrame() throws IOException 
	{
		super("Hamming Distance");
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(border);
		
		mp.insets = new Insets(2,2,0,2);
	
		hamNum.setEditable(false);
		hamNum.setText(String.valueOf(slider.getValue()) + "       ");
		
		mp.gridx = 0;
		mp.gridy = 0;
		mesoPanel.add(enterHam,mp);
		mp.gridx = 1;
		mp.gridy = 0;
		mesoPanel.add(hamNum, mp);
		
		// Slider Section
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		mp.gridx = 0;
		mp.gridy = 1;
		mesoPanel.add(slider, mp);
		
		// changeListener that changes the num output of hamNum with the current number the slider is on
		slider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				
				hamNum.setText(String.valueOf(slider.getValue()));
			}
		});
		
		// gridx and gridy are being used to position components correctly
		mp.gridx = 0;
		mp.gridy = 2;
		mesoPanel.add(showStation,mp);
		
		// actionListner for show station button. Prints out stations in JTextArea
		// Checkes to see what value the slider is on to determine which arrayList to display on textField
		showStation.addActionListener((e) ->
		{
			HammingDist hd2;
			if(slider.getValue()==1) // If the slider is on the 1 tick
			{
				try 
				{
					String list = "";
					hd2 = new HammingDist(String.valueOf(menu.getSelectedItem()));
					for(int index = 0; index < hd2.getDistOneStations().size(); ++index)
					{
						list += hd2.getDistOneStations().get(index) + "\n";
					}
					stationList.setText(list);
					
				} 
				catch (IOException e1) 
				{
					
				}
				
			}
			
			else if(slider.getValue()==2) // if slider is on the 2 tick
			{
				try 
				{
					String list = "";
					hd2 = new HammingDist(String.valueOf(menu.getSelectedItem()));
					for(int index = 0; index < hd2.getDistTwoStations().size(); ++index)
					{
						list += hd2.getDistTwoStations().get(index) + "\n";
					}
					stationList.setText(list);
				} 
				catch (IOException e1) 
				{
					
					e1.printStackTrace();
				}
			}
			else if(slider.getValue()==3) // if the slider is one the 3 tick
			{
				try 
				{
					String list = "";
					hd2 = new HammingDist(String.valueOf(menu.getSelectedItem()));
					for(int index = 0; index < hd2.getDistThreeStations().size(); ++index)
					{
						list += hd2.getDistThreeStations().get(index) + "\n";
					}
					stationList.setText(list);
				} 
				catch (IOException e1) 
				{
					
					e1.printStackTrace();
				}
			}
			
			else // if the slider is on the fourth tick
			{
				try 
				{
					String list = "";
					hd2 = new HammingDist(String.valueOf(menu.getSelectedItem()));
					for(int index = 0; index < hd2.getDistFourStations().size(); ++index)
					{
						list += hd2.getDistFourStations().get(index) + "\n";
					}
					stationList.setText(list);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		
		mp.gridx = 0;
		mp.gridy = 3;
		mesoPanel.add(scroll,mp);
		
		mp.gridx = 0;
		mp.gridy = 4;
		mesoPanel.add(compare,mp);
		
		// reads stations into an array in order for them to be added to drop down box
		String [] stations = readFile().toArray(new String[120]); 
		menu = new JComboBox(stations);
		mp.gridx = 1;
		mp.gridy = 4;
		mesoPanel.add(menu,mp);
		
		mp.gridx = 0;
		mp.gridy = 6;
		mesoPanel.add(calcHD,mp);
		
		// This does the same thing as the show station button but instead of printing out the stations
		// it prints out the how many stations are either 0,1,2,3, or 4 distanes from the station being compared to
		calcHD.addActionListener((e) ->
		{
			HammingDist hd2;
			try 
			{
				hd2 = new HammingDist(String.valueOf(menu.getSelectedItem()));
				
				d0.setText(String.valueOf(hd2.getDistZero()));
				d1.setText(String.valueOf(hd2.getDistOne()));
				d2.setText(String.valueOf(hd2.getDistTwo()));
				d3.setText(String.valueOf(hd2.getDistThree()));
				d4.setText(String.valueOf(hd2.getDistFour()));
				stationList.setText("");
			} 
			catch (IOException e1) 
			{
				
				e1.printStackTrace();
			}			
		});
		// Distances Panel, 
		mp.gridx = 0;
		mp.gridy = 8;
		distances.add(dist0);
		distances.add(d0);
		distances.add(dist1);
		distances.add(d1);
		distances.add(dist2);
		distances.add(d2);
		distances.add(dist3);
		distances.add(d3);
		distances.add(dist4);
		distances.add(d4);
		distances.add(addStation);
		distances.add(newStation);
		mesoPanel.add(distances,mp);
		d0.setEditable(false);
		d1.setEditable(false);
		d2.setEditable(false);
		d3.setEditable(false);
		d4.setEditable(false);
		
		// This actionListner is for when the addStation button is clicked
		// This should create a new HammingDist object as well as adding it to the JComboBox
		addStation.addActionListener((e)->
		{
			
			if(newStation.getText().isEmpty()==false && newStation.getText().length()==4)
			{
				try 
				{
					String newEntry = newStation.getText();
					HammingDist hd3 = new HammingDist(newEntry);
					menu.addItem(newEntry);
					
					this.add(success, BorderLayout.SOUTH);
				} 
				catch (IOException e1) 
				{
					
					e1.printStackTrace();
				}		
			}
		});
		
		// Looked up method on StackOverFlow to figure out how to rezie image
		// https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
		ImageIcon imageToShow = new ImageIcon("Mesonet.png");
		Image image = imageToShow.getImage();
		Image resizedImage = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(resizedImage);
		imageLabel = new JLabel(newImage);

		hammBanner.setFont(hammBanner.getFont().deriveFont(50f));
		
		// adds all panels and componets to the main frame
		this.add(hammBanner, BorderLayout.NORTH);
		this.add(imageLabel, BorderLayout.CENTER);
		this.add(mesoPanel, BorderLayout.WEST);
		this.pack();
		this.setVisible(true);
		
	}
	
	
}