package edu.ksu.cis.seacas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;

public class MainScreenSwing {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenSwing window = new MainScreenSwing();
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
		frame.setBounds(100, 100, 1100, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenExodusFile = new JMenuItem("Open Exodus File...");
		mnFile.add(mntmOpenExodusFile);
		
		JMenuItem mntmSaveImageAs = new JMenuItem("Save Image As...");
		mnFile.add(mntmSaveImageAs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnBlotScript = new JMenu("Script");
		menuBar.add(mnBlotScript);
		
		JMenuItem mntmOpenBlotScript = new JMenuItem("Open Blot Script...");
		mnBlotScript.add(mntmOpenBlotScript);
		
		JMenuItem mntmSaveBlotScript = new JMenuItem("Save Blot Script As...");
		mnBlotScript.add(mntmSaveBlotScript);
		
		//JMenuItem mntmCloseBlotScript = new JMenuItem("Close Blot Script");
		//mnBlotScript.add(mntmCloseBlotScript);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBounds(12, 12, 340, 126);
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
		panel_3.add(textField_2);
		textField_2.setColumns(5);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4.setBounds(12, 150, 340, 350);
		frame.getContentPane().add(panel_4);
		
		JCheckBox chckbxEnableScript = new JCheckBox("Enable Script");
		panel_4.add(chckbxEnableScript);
		
		JButton btnClear = new JButton("Clear");
		panel_4.add(btnClear);
		
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_5.setBorder(new TitledBorder(null, "Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(364, 12, 730, 550);
		frame.getContentPane().add(panel_5);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
