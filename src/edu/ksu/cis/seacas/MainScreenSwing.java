package edu.ksu.cis.seacas;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainScreenSwing {
	
	private final Logger logger = Logger.getLogger(this.getClass().getPackage().getName());

	private JFrame frame;
	private JTextField textField_X;
	private JTextField textField_Y;
	private JTextField textField_Z;
	
	private JTextArea textAreaScript ;
	private JPanel panel_5;
	private JLabel lblImage;
	private BufferedImage img;
	
	private File currentBlotScript;
	private File currentExodusFile;
	
	private BlotState blot;


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
		this.blot = new BlotState();
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
		
		JMenuItem mntmReset = new JMenuItem("Reset");
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetApp();
			}
		});
		mnFile.add(mntmReset);
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
		
		JButton btnRotXNegLarge = new JButton("<<5");
		btnRotXNegLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateX(-5);
				
			}
		});
		panel_1.add(btnRotXNegLarge);
		
		JButton btnRotXNegSmall = new JButton("<2");
		btnRotXNegSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateX(-2);
			}
		});
		panel_1.add(btnRotXNegSmall);
		
		JLabel lblX = new JLabel("X");
		panel_1.add(lblX);
		
		JButton btnRotXPosSmall = new JButton("2>");
		btnRotXPosSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rotateX(2);
			}
		});
		panel_1.add(btnRotXPosSmall);
		
		JButton btnRotXPosLarge = new JButton("5>>");
		btnRotXPosLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateX(5);
			}
		});
		panel_1.add(btnRotXPosLarge);
		
		textField_X = new JTextField();
		textField_X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int d = Integer.parseInt(((JTextField) e.getSource()).getText());
				rotateX(d);
			}
		});
		textField_X.setText("0");
		panel_1.add(textField_X);
		textField_X.setColumns(5);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		
		JButton btnRotYNegLarge = new JButton("<<5");
		btnRotYNegLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateY(-5);
			}
		});
		panel_2.add(btnRotYNegLarge);
		
		JButton btnRotYNegSmall = new JButton("<2");
		btnRotYNegSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateY(-2);
			}
		});
		panel_2.add(btnRotYNegSmall);
		
		JLabel lblY = new JLabel("Y");
		panel_2.add(lblY);
		
		JButton btnRotYPosSmall = new JButton("2>");
		btnRotYPosSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateY(2);
			}
		});
		panel_2.add(btnRotYPosSmall);
		
		JButton btnRotYPosLarge = new JButton("5>>");
		btnRotYPosLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateY(5);
			}
		});
		panel_2.add(btnRotYPosLarge);
		
		textField_Y = new JTextField();
		textField_Y.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int d = Integer.parseInt(((JTextField) e.getSource()).getText());
					rotateY(d);
				}
			}
		});
		textField_Y.setText("0");
		panel_2.add(textField_Y);
		textField_Y.setColumns(5);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JButton btnRotZNegLarge = new JButton("<<5");
		btnRotZNegLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateZ(-5);
			}
		});
		panel_3.add(btnRotZNegLarge);
		
		JButton btnRotZNegSmall = new JButton("<2");
		btnRotZNegSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateZ(-2);
			}
		});
		panel_3.add(btnRotZNegSmall);
		
		JLabel lblZ = new JLabel("Z");
		panel_3.add(lblZ);
		
		JButton btnRotZPosSmall = new JButton("2>");
		btnRotZPosSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateZ(2);
			}
		});
		panel_3.add(btnRotZPosSmall);
		
		JButton btnRotZPosLarge = new JButton("5>>");
		btnRotZPosLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotateZ(5);
			}
		});
		panel_3.add(btnRotZPosLarge);
		
		textField_Z = new JTextField();
		textField_Z.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int d = Integer.parseInt(((JTextField) e.getSource()).getText());
					rotateZ(d);
				}
			}
		});
		textField_Z.setText("0");
		panel_3.add(textField_Z);
		textField_Z.setColumns(5);
		
		panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(449, 6, 750, 586);
		
		/* TODO: boiler plate code, remove when testing complete */
		/*
		try {
			//img = ImageIO.read(new File("/home/chandan/BLOT-Gui/mug.jpg"));
			img = ImageIO.read(new File("/Users/chandan/seacas_cmds/c_final.jpg"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		lblImage = new JLabel(new ImageIcon(img));
		*/
		
		lblImage = new JLabel();
		panel_5.add(lblImage);
		
		frame.getContentPane().add(panel_5);
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(12, 169, 431, 387);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_6.setBounds(6, 6, 419, 39);
		panel_4.add(panel_6);
		
		JCheckBox chckbxEnableScript = new JCheckBox("Enable Script");

		chckbxEnableScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox chkBox = (JCheckBox) e.getSource();
				if(chkBox.isSelected()) {
					textAreaScript.setEnabled(true);
				}
				else {
					textAreaScript.setEnabled(false);
				}
			}
		});
		panel_6.add(chckbxEnableScript);
		
		JButton btnRunScript = new JButton("Run Script");
		btnRunScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: function to get script content and set in BlotState.blotCmdText
			}
		});
		panel_6.add(btnRunScript);
		
		textAreaScript = new JTextArea();
		textAreaScript.setTabSize(0);
		textAreaScript.setLineWrap(true);
		textAreaScript.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
		textAreaScript.setRows(22);
		textAreaScript.setColumns(25);
		textAreaScript.setEnabled(false);
		
		
		JScrollPane scrollPane = new JScrollPane(textAreaScript);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(6, 50, 420, 330);
		panel_4.add(scrollPane);
		
	}
	
	void selectExodusFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	currentExodusFile = fileChooser.getSelectedFile();
            
            logger.info("Exodus File: " + currentExodusFile.getName() + ".");
            
            panel_5.setBorder(new TitledBorder(null, currentExodusFile.getAbsolutePath(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
            
    		this.blot.setBlotExodusFileDir(getDirectoryOnly(currentExodusFile.getPath()));
    		this.blot.setExodus_file(currentExodusFile.getName());
    		
    		/* reset the rotation values */
    		textField_X.setText("0");
    		textField_Y.setText("0");
    		textField_Z.setText("0");
    		
    		executeBlot();
        }
	}

	@SuppressWarnings("resource")
	void selectBlotScriptFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            currentBlotScript = fileChooser.getSelectedFile();
            logger.info("Opening: " + currentBlotScript.getName() + ".");
            
    		try {
				String text = new Scanner(currentBlotScript).useDelimiter("\\A").next();
				textAreaScript.setText(text);
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
				out.print(this.textAreaScript.getText());
				out.close();
			}
			catch(IOException ioe) {
				logger.severe("Error saving blot script file");
				ioe.printStackTrace();
			}
		}
	}
	
	/**
	 * Handle X-axis rotation and update the image.
	 * 
	 **/
	protected void rotateX(int degree) {
		logger.finer("Rotate X by " + degree);
		
		/* Set the new X-coordinate to current X-coordinate plus degree */
		this.blot.setCurrent_x(this.blot.getCurrent_x() + degree);
		
		/* Update textField_X */
		this.textField_X.setText(String.valueOf(this.blot.getCurrent_x()));
		
		/* Execute Blot to generate the updated image */
		executeBlot();
	}

	/**
	 * Handle Y-axis rotation and update the image.
	 *
	 **/
	protected void rotateY(int degree) {
		logger.finer("Rotate Y by " + degree);

		/* Set the new Y-coordinate to current Y-coordinate plus degree */
		this.blot.setCurrent_y(this.blot.getCurrent_y() + degree);
		
		/* Update textField_Y */
		this.textField_Y.setText(String.valueOf(this.blot.getCurrent_y()));

		
		/* Execute Blot to generate the updated image */
		executeBlot();
	}

	/**
	 * Handle Z-axis rotation and update the image.
	 *
	 **/
	protected void rotateZ(int degree) {
		logger.finer("Rotate Z by " + degree);

		/* Set the new Y-coordinate to current Y-coordinate plus degree */
		this.blot.setCurrent_z(this.blot.getCurrent_z() + degree);
		
		/* Update textField_X */
		this.textField_Z.setText(String.valueOf(this.blot.getCurrent_z()));


		/* Execute Blot to generate the updated image */
		executeBlot();
		/* Load the updated image */
		loadImage();

	}
	
	/**
	 * Update/Generate the required files and invoke Blot command via BlotState
	 * instance
	 */
	protected void executeBlot() {
		logger.info("executeBlot() entered");

		if (blot.getExodus_file() != "") {
			blot.updateCmdFile();
			blot.updateMakeFile();

			int returnValue = blot.execute();
			logger.info("executeBlot Return Code: " + returnValue);

			if (returnValue == 0) {
				loadImage();
			} else {
				JOptionPane.showMessageDialog(frame, "Error executing Blot. Please check log.", "Error", JOptionPane.ERROR_MESSAGE);				
			}
		}
	}
	
	/**
	 * Load the image from file and display.
	 * 
	 */
	void loadImage() {
		logger.info("loadImage() entered...");

		logger.info("Loading image at: " + blot.getImg_path());

		/* Get the image path from Blot state */
		logger.info("Loading Image: " + blot.getImg_path().trim());
		String img_src = blot.getImg_path().trim();

		if (img_src != "") {

			lblImage = null;
			
			try {
				/* Load new image */
				img = ImageIO.read(new File(this.blot.getImg_path().trim()));
				
				/* Show the image */
				lblImage = new JLabel(new ImageIcon(img));
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(frame, "Error loading Image. Please check log.", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}	
		}
	}
	
	String getDirectoryOnly(String path) {
		
		String directoryPattern = "^(.*/)?(?:$|(.+?)(?:(\\\\.[^.]*$)|$))";
        Pattern r = Pattern.compile(directoryPattern);
        Matcher m = r.matcher(path);
        
        /* if pattern matches */
        if (m.matches()) {
        	/* return the directory path without trailing slash symbol */
        	String p = m.group(1);
        	return p.substring(0, p.length()-1);
        }
        else 
        	return "";
        
	}
	
	/**
	 * 
	 */
	void resetApp() {
		/* Clear the internal values */
		this.currentBlotScript = null;
		this.currentExodusFile = null;
		this.blot = null;
		this.blot = new BlotState();
		
		/* clear the image */
		lblImage.setIcon(null);
		lblImage = null;
		lblImage = new JLabel();
		
		/* clear the path shown as image title */
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		/* clear the script contents */
		textAreaScript.setText("");
		
		/* clear the rotation values */
		textField_X.setText("0");
		textField_Y.setText("0");
		textField_Z.setText("0");
	}
}
