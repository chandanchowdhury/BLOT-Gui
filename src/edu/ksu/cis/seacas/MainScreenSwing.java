package edu.ksu.cis.seacas;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class MainScreenSwing {
	
	private final Logger logger = Logger.getLogger(this.getClass().getPackage().getName());

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private JTextArea textArea ;
	private JPanel panel_5;
	private BufferedImage img;
	
	private File currentBlotScript;
	private File currentExodusFile;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenSwing window = new MainScreenSwing();
					//window.frame.pack();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreenSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenExodusFile = new JMenuItem("Open Exodus File...");
	
		mntmOpenExodusFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("Menu -> File -> Open Exodus File selected...");
				selectExodusFile();
			}
		});
		mnFile.add(mntmOpenExodusFile);
		
		JMenuItem mntmSaveImageAs = new JMenuItem("Save Image As...");
		mntmSaveImageAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("Menu -> Save Image As selected...");
			}
		});
		mnFile.add(mntmSaveImageAs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("Menu -> Exit selected...");
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnBlotScript = new JMenu("Script");
		menuBar.add(mnBlotScript);
		
		JMenuItem mntmOpenBlotScript = new JMenuItem("Open Blot Script...");
		mntmOpenBlotScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("Menu -> Script -> Open Blot Script selected...");
				selectBlotScriptFile();
			}
		});
		mnBlotScript.add(mntmOpenBlotScript);
		
		JMenuItem mntmSaveBlotScript = new JMenuItem("Save Blot Script As...");
		mntmSaveBlotScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("Menu -> Script -> Save Blot Script As selected...");
				selectBlotScriptSave();
			}
		});
		mnBlotScript.add(mntmSaveBlotScript);
		
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBounds(12, 12, 431, 141);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JButton button = new JButton("<<5");
		panel_1.add(button);
		
		JButton button_1 = new JButton("<2");
		panel_1.add(button_1);
		
		JLabel lblX = new JLabel("X");
		panel_1.add(lblX);
		
		JButton button_2 = new JButton("2>");
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("5>>");
		panel_1.add(button_3);
		
		textField = new JTextField();
		textField.setText("0");
		panel_1.add(textField);
		textField.setColumns(5);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton button_4 = new JButton("<<5");
		panel_2.add(button_4);
		
		JButton button_5 = new JButton("<2");
		panel_2.add(button_5);
		
		JLabel lblY = new JLabel("Y");
		panel_2.add(lblY);
		
		JButton button_6 = new JButton("2>");
		panel_2.add(button_6);
		
		JButton button_7 = new JButton("5>>");
		panel_2.add(button_7);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		panel_2.add(textField_1);
		textField_1.setColumns(5);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JButton btnNewButton = new JButton("<<5");
		panel_3.add(btnNewButton);
		
		JButton button_8 = new JButton("<2");
		panel_3.add(button_8);
		
		JLabel lblZ = new JLabel("Z");
		panel_3.add(lblZ);
		
		JButton button_9 = new JButton("2>");
		panel_3.add(button_9);
		
		JButton button_10 = new JButton("5>>");
		panel_3.add(button_10);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		panel_3.add(textField_2);
		textField_2.setColumns(5);
		
		try {
			//img = ImageIO.read(new File("/home/chandan/BLOT-Gui/mug.jpg"));
			img = ImageIO.read(new File("/Users/chandan/seacas_cmds/c_final.jpg"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_5.setBorder(new TitledBorder(null, "Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(449, 6, 750, 586);
		frame.getContentPane().add(panel_5);
		
		JLabel lblImage = new JLabel(new ImageIcon(img));
		panel_5.add(lblImage);
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(12, 169, 431, 387);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(6, 6, 204, 39);
		panel_4.add(panel_6);
		
		JCheckBox chckbxEnableScript = new JCheckBox("Enable Script");

		chckbxEnableScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox chkBox = (JCheckBox) e.getSource();
				if(chkBox.isSelected()) {
					textArea.setEnabled(true);
				}
				else {
					textArea.setEnabled(false);
				}
			}
		});
		panel_6.add(chckbxEnableScript);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		panel_6.add(btnClear);
		
		textArea = new JTextArea();
		textArea.setTabSize(0);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
		textArea.setRows(22);
		textArea.setColumns(25);
		textArea.setEnabled(false);
		
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(6, 50, 420, 330);
		panel_4.add(scrollPane);
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
	}
	
	void selectExodusFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	currentExodusFile = fileChooser.getSelectedFile();
            
            logger.info("Exodus File: " + currentExodusFile.getName() + ".");
            
            panel_5.setBorder(new TitledBorder(null, currentExodusFile.getAbsolutePath(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
            
    		//TODO: Set the file path in BlotState
        }
	}

	@SuppressWarnings("resource")
	void selectBlotScriptFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	logger.info("Opening: " + currentBlotScript.getName() + ".");
        	
            currentBlotScript = fileChooser.getSelectedFile();
            
    		try {
				String text = new Scanner(currentBlotScript).useDelimiter("\\A").next();
				textArea.setText(text);
			}
			catch(IOException ioe) {
				logger.severe("Error with Blot Script file");
				ioe.printStackTrace();
			}
        }
	}
	
	void selectBlotScriptSave() {
		logger.entering(this.getClass().getName(),"selectBlotScriptSave");
		
		JFileChooser fileChooser = new JFileChooser(currentBlotScript);
		int returnVal = fileChooser.showSaveDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			logger.info("Saving Blot Script file: "+fileChooser.getSelectedFile());
		
			try {
				PrintWriter out = new PrintWriter(fileChooser.getSelectedFile());
				out.print(this.textArea.getText());
				out.close();
			}
			catch(IOException ioe) {
				logger.severe("Error saving blot script file");
				ioe.printStackTrace();
			}
		}
	}

}
