package jackage;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

public class JackOhare_CSC407FinalProject extends JFrame implements Runnable {

	//GUI Elements
	private JPanel contentPane1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	private JPanel contentPane;
	//gifs
	String deadlift = "C:\\Users\\Jack\\Downloads\\deadlift.gif";
	String squat = "C:\\Users\\Jack\\Downloads\\squat.gif";
	String press = "C:\\Users\\Jack\\Downloads\\press.gif";
	//Hash tables
	Hashtable<Integer, Float> ht = new Hashtable<Integer, Float>(); //weight
	Hashtable<Integer,Integer> reps = new Hashtable<Integer,Integer>(); //reps
	Hashtable<Integer, Float> oneMax = new Hashtable<Integer, Float>();  //one rep maxes
	//while not in rest
	boolean goToRest = false;
	//to move between each set
	int iterator = 1;
	
	/*Run controls hold long the user should be resting for */
	public void run()
	{
		
		while(true)
		{
			while(!goToRest)
			{
				System.out.println("");
			}
			//disables button so user cannot do next set until completed
			btnNewButton.setEnabled(false);
			for(int i = 60; i >=0; i--)
			{
				lblNewLabel_4.setText("Rest: " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			lblNewLabel_4.setText("");
			btnNewButton.setEnabled(true);
			goToRest = false;
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JackOhare_CSC407FinalProject frame = new JackOhare_CSC407FinalProject();
					frame.setVisible(true);
					Thread t  = new Thread(frame);
					t.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame for main menu
	 */
	public JackOhare_CSC407FinalProject() {
		setTitle("Compound Lift Analyzer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 62, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Lifting Basics and Improvement");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.gridx = 6;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		
		//PRESS BUTTON
		JButton btnNewButton = new JButton("Press");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DummyGUI(press,"Press");
				}
			});
		
		
		//DEADLIFT BUTTON
		JButton btnNewButton_1 = new JButton("Deadlifts");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 9;
		gbc_btnNewButton_1.gridy = 6;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DummyGUI(deadlift,"Deadlift");	
				}
			});
			
		
		//SQUAT BUTTON
		JButton btnNewButton_2 = new JButton("Squat");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 10;
		gbc_btnNewButton_2.gridy = 6;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DummyGUI(squat,"Squat");	
				}
			});
	}

/*GUI FOR ALL EXERCISES 
 * TAKES IN LIFT TYPE AND NAME FOR FRAME
 * */
void DummyGUI(String liftType,String liftName)
	{
		
		
		/**
		 * Create the frame for exercise
		 */
		setTitle(liftName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 72, 142, 142, 142, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		//gif of lift
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(liftType));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.gridheight = 5;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		//Label to tell you how long you have to rest
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("Arial Black", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 6;
		gbc_lblNewLabel_4.gridy = 6;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		//REPS input field
		textField = new JTextField();
		textField.setToolTipText("ex)8");
		textField.setEditable(true);
		textField.setText("1");
		textField.setFont(new Font("Arial Black", Font.PLAIN, 17));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 6;
		gbc_textField.gridheight = 3;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 8;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		//Weight input field
		textField_1 = new JTextField();
		textField_1.setToolTipText("ex)135");
		textField_1.setFont(new Font("Arial Black", Font.PLAIN, 17));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.gridheight = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 8;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Reps");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 6;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 11;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Set #1");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 6;
		gbc_lblNewLabel_3.gridy = 11;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblNewLabel_1 = new JLabel("Weight (lbs)");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 7;
		gbc_lblNewLabel_1.gridy = 11;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		//Button to submit info
		btnNewButton = new JButton("Submit");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.gridwidth = 6;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 15;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) throws NumberFormatException{
				//if have not done 3 sets yet and the text box is not empty for weight or reps
				if(iterator<=3 && textField_1.getText() != null && textField.getText() != null)
				{
					
					//make sure not empty
					//put into hash table
					ht.put(iterator, Float.parseFloat(textField_1.getText()));
					reps.put(iterator, Integer.parseInt(textField.getText()));
					iterator++;
					lblNewLabel_3.setText("Set #"+Integer.toString(iterator));
					goToRest = true;
					//if greator go to results page
					if(iterator>3)
					{
						lblNewLabel_3.setText("Set #"+Integer.toString(iterator-1));
						btnNewButton.setBackground(new Color(255, 0, 0));
						btnNewButton.setText("Get results");
					}
				
				}
				else
				{			//go to results from here on a new page
					
					
					//Converts weight and reps into a one rep maxes array
					for(Integer i: reps.keySet())
					{
						//calculation for one rep max
						oneMax.put(i,(float) (ht.get(i)*reps.get(i)*.03333)+ht.get(i));
					}
					Plot plt = Plot.create(PythonConfig.pythonBinPathConfig("C:\\Program Files\\Python38\\python.exe"));
					  plt.plot()
			          .add(Arrays.asList(1,2,3), Arrays.asList(ht.get(1), ht.get(2), ht.get(3)))
			          .label("Weight Lifted")
			          .linestyle("--")
			          .linewidth(2.0);
					  plt.plot()
			          .add(Arrays.asList(1,2,3), Arrays.asList(oneMax.get(1), oneMax.get(2), oneMax.get(3)))
			          .label("label")
			          .linestyle("--")
			          .label("One Rep Max")
			          .linestyle("--")
			          .linewidth(2.0);	  
					  
					  
					  
					//Display numbers next to each lift  
			       plt.xlabel("Set #");
			       plt.ylabel("Pounds Lifted");
			       plt.text(1, ht.get(1), Float.toString( ht.get(1)));
			       plt.text(1, oneMax.get(1), Float.toString(oneMax.get(1)));
			       
			       plt.text(2,ht.get(2), Float.toString( ht.get(2)));
			       plt.text(2, oneMax.get(2), Float.toString(oneMax.get(2)));
			       
			       plt.text(3,ht.get(3), Float.toString( ht.get(3)));
			       plt.text(3, oneMax.get(3), Float.toString(oneMax.get(3)));
			       plt.title("Pounds Lifted Vs 1 Rep Max");
			       plt.legend();
			       try {
					plt.show();
			       } catch (IOException | PythonExecutionException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					}
					
			
				}	
				
				}
			
			});
		
		}
		
}
