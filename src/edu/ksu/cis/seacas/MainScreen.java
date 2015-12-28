package edu.ksu.cis.seacas;

import java.util.logging.Logger;

import org.eclipse.swt.*;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MainScreen {

	private final Logger logger = Logger.getLogger(this.getClass().getPackage().getName());

	Shell shell;
	Canvas canvas;
	Image image;

	// TODO: Implement a config file to read the parameters from there
	private String currentDir = "/Users/chandan/Desktop/cmds";
	private Text textExodusFilePath;

	/**
	 * The state of the Blot along with commands will be stored here.
	 */
	private BlotState blot;

	Text textRotX;
	Text textRotY;
	Text textRotZ;
	Text textCMD;

	Button buttonCMD;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainScreen window = new MainScreen();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		//Display display = new Display();
		shell = new Shell();
		shell.setSize(1024, 768);
		shell.setText("SEACAS - Blot Viewer");

		// Create the BlotState instance
		this.blot = new BlotState();
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));
		Composite compositeExodus = new Composite(shell, SWT.BORDER_DASH);
		compositeExodus.setLayoutData(new RowData(963, SWT.DEFAULT));
		compositeExodus.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button btnSelectExodusFile = new Button(compositeExodus, SWT.NONE);
		btnSelectExodusFile.setLayoutData(new RowData(SWT.DEFAULT, 30));
		btnSelectExodusFile.setText("Select Exodus File");

		btnSelectExodusFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectExodusFile();
			}
		});

		textExodusFilePath = new Text(compositeExodus, SWT.BORDER);
		textExodusFilePath.setLayoutData(new RowData(437, 20));

		Button btnGeneratePlot = new Button(compositeExodus, SWT.NONE);
		btnGeneratePlot.setText("Generate Plot");
		btnGeneratePlot.setLayoutData(new RowData(SWT.DEFAULT, 30));
		btnGeneratePlot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// loadImage();
				/* Execute Blot to generate new image */
				executeBlot();

				loadImage();
			}
		});

		Composite compositeRow2 = new Composite(shell, SWT.BORDER_SOLID);
		compositeRow2.setLayoutData(new RowData(960, SWT.DEFAULT));
		compositeRow2.setLayout(new RowLayout(SWT.HORIZONTAL));

		Composite compositeBtn = new Composite(compositeRow2, SWT.BORDER);
		compositeBtn.setLayoutData(new RowData(300, SWT.DEFAULT));
		compositeBtn.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		/* Details of X-axis buttons */
		
		Composite compositeBtnX = new Composite(compositeBtn, SWT.BORDER);
		compositeBtnX.setLayoutData(new RowData(300, SWT.DEFAULT));
		compositeBtnX.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button btnRotXLargeNeg = new Button(compositeBtnX, SWT.BORDER);
		btnRotXLargeNeg.setText("<< 5");
		btnRotXLargeNeg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateX(-5);
			}
		});

		Button btnRotXSmallNeg = new Button(compositeBtnX, SWT.BORDER);
		btnRotXSmallNeg.setText("< 2");
		btnRotXSmallNeg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateX(-2);
			}
		});

		Label lblX = new Label(compositeBtnX, SWT.CENTER);
		lblX.setAlignment(SWT.CENTER);
		lblX.setLayoutData(new RowData(17, 17));
		lblX.setText("X");

		Button btnRotXSmallPos = new Button(compositeBtnX, SWT.BORDER);
		btnRotXSmallPos.setText("2 >");
		btnRotXSmallPos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateX(2);
			}
		});

		Button btnRotXLargePos = new Button(compositeBtnX, SWT.BORDER);
		btnRotXLargePos.setText("5 >>");
		btnRotXLargePos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateX(5);
			}
		});

		textRotX = new Text(compositeBtnX, SWT.BORDER);
		textRotX.setLayoutData(new RowData(48, SWT.DEFAULT));
		Composite compositeBtnY = new Composite(compositeBtn, SWT.BORDER);
		compositeBtnY.setLayoutData(new RowData(300, SWT.DEFAULT));
		compositeBtnY.setLayout(new RowLayout(SWT.HORIZONTAL));

		/* Details of Y-axis buttons */

		Button btnRotYLargeNeg = new Button(compositeBtnY, SWT.BORDER);
		btnRotYLargeNeg.setText("<< 5");
		btnRotYLargeNeg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateY(-5);
			}
		});

		Button btnRotYSmallNeg = new Button(compositeBtnY, SWT.BORDER);
		btnRotYSmallNeg.setText("< 2");
		btnRotYSmallNeg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateY(-2);
			}
		});

		Label lblY = new Label(compositeBtnY, SWT.CENTER);
		lblY.setAlignment(SWT.CENTER);
		lblY.setLayoutData(new RowData(17, 17));
		lblY.setText("Y");

		Button btnRotYSmallPos = new Button(compositeBtnY, SWT.BORDER);
		btnRotYSmallPos.setText("2 >");
		btnRotYSmallPos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateY(2);
			}
		});

		Button btnRotYLargePos = new Button(compositeBtnY, SWT.BORDER);
		btnRotYLargePos.setText("5 >>");
		btnRotYLargePos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateY(5);
			}
		});

		textRotY = new Text(compositeBtnY, SWT.BORDER);
		textRotY.setLayoutData(new RowData(48, SWT.DEFAULT));
		
		/* Details of Z-axis buttons */
		Composite compositeBtnZ = new Composite(compositeBtn, SWT.BORDER);
		compositeBtnZ.setLayoutData(new RowData(300, SWT.DEFAULT));
		compositeBtnZ.setLayout(new RowLayout(SWT.HORIZONTAL));

		
		Button btnRotZLargeNeg = new Button(compositeBtnZ, SWT.BORDER);
		btnRotZLargeNeg.setText("<< 5");
		btnRotZLargeNeg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateZ(-5);
			}
		});

		Button btnRotZSmallNeg = new Button(compositeBtnZ, SWT.BORDER);
		btnRotZSmallNeg.setText("< 2");
		btnRotZSmallNeg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateZ(-2);
			}
		});

		Label lblZ = new Label(compositeBtnZ, SWT.CENTER);
		lblZ.setAlignment(SWT.CENTER);
		lblZ.setLayoutData(new RowData(17, 17));
		lblZ.setText("Z");

		Button btnRotZSmallPos = new Button(compositeBtnZ, SWT.BORDER);
		btnRotZSmallPos.setText("2 >");
		btnRotZSmallPos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateZ(2);
			}
		});

		Button btnRotZLargePos = new Button(compositeBtnZ, SWT.BORDER);
		btnRotZLargePos.setText("5 >>");
		btnRotZLargePos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateZ(5);
			}
		});

		textRotZ = new Text(compositeBtnZ, SWT.BORDER);
		textRotZ.setLayoutData(new RowData(48, SWT.DEFAULT));

		Composite compositeCmd = new Composite(compositeRow2, SWT.NONE);
		compositeCmd.setLayoutData(new RowData(600, 125));

		Button btnEnableCommand = new Button(compositeCmd, SWT.CHECK);
		btnEnableCommand.setBounds(0, 0, 155, 18);
		btnEnableCommand.setText("Enable Command");
		
		btnEnableCommand.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//System.out.println("Selection toggle...");
				Button bt =(Button) e.getSource();
				setCmdText(bt.getSelection());
			}
		});

		textCMD = new Text(compositeCmd, SWT.BORDER | SWT.MULTI);
		textCMD.setBounds(0, 25, 400, 100);
		
		
		
		textRotX.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				logger.fine("Rotating on input value");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {

					//logger.fine("Rotating X: " + textRotX.getText());
					//rotateX(45);
					rotateX(Integer.parseInt(textRotX.getText()));
				}
			}
		});

		textRotY.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// logger.fine("Rotating Y on input value");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					// logger.fine("Rotating Y: "+e.getSource().toString());
					rotateY(Integer.parseInt(textRotY.getText()));
				}
			}
		});
		
		textRotZ.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// logger.fine("Rotating Z on input value");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					// logger.fine("Rotating Z: "+e.getSource().toString());
					rotateZ(Integer.parseInt(textRotZ.getText()));
				}
			}
		});
		
		/* Details of Canvas which will be used for displaying the image */
		Composite compositeCanvas = new Composite(shell, SWT.BORDER);
		compositeCanvas.setLayout(new RowLayout(SWT.HORIZONTAL));

		canvas = new Canvas(compositeCanvas, SWT.BORDER);
		// canvas.setLayout(new FillLayout(SWT.HORIZONTAL));
		canvas.setLayoutData(new RowData(900, 600));

		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				if (image != null) {
					canvas.setLayoutData(new RowData(image.getBounds().width, image.getBounds().height));
					e.gc.drawImage(image, 0, 0);
					// image.dispose();
				}

			}
		});
	}

	/**
	 * Handle X-axis rotation and update the image.
	 * 
	 **/
	protected void rotateX(int degree) {
		logger.finer("Rotate X by " + degree);
		// Set the new X-coordinate to current X-coordinate plus degree
		this.blot.setCurrent_x(this.blot.getCurrent_x() + degree);
		// Execute Blot to generate the updated image
		executeBlot();
		// Load the updated image
		loadImage();
	}

	/**
	 * Handle Y-axis rotation and update the image.
	 *
	 **/
	protected void rotateY(int degree) {
		logger.finer("Rotate Y by " + degree);

		// Set the new Y-coordinate to current Y-coordinate plus degree
		this.blot.setCurrent_y(this.blot.getCurrent_y() + degree);

		// Execute Blot to generate the updated image
		executeBlot();
		// Load the updated image
		loadImage();

	}

	/**
	 * Handle Z-axis rotation and update the image.
	 *
	 **/
	protected void rotateZ(int degree) {
		logger.finer("Rotate Z by " + degree);

		// Set the new Y-coordinate to current Y-coordinate plus degree
		this.blot.setCurrent_z(this.blot.getCurrent_z() + degree);

		// Execute Blot to generate the updated image
		executeBlot();
		// Load the updated image
		loadImage();

	}

	/**
	 * Handle the "Select Exodus File" button and display the file chooser
	 * dialog
	 */
	protected void selectExodusFile() {
		FileDialog fileChooser = new FileDialog(this.shell, SWT.OPEN);
		fileChooser.setText("Select EXODUS file");
		fileChooser.setFilterPath(currentDir);
		fileChooser.setFilterExtensions(new String[] { "*.e;" });
		fileChooser.setFilterNames(new String[] { "EXODUS File" + " (e)" });
		String filename = fileChooser.open();
		if (filename != null) {

			textExodusFilePath.setText(fileChooser.getFilterPath() + filename);
			currentDir = fileChooser.getFilterPath();

			blot.setBlotExodusFileDir(fileChooser.getFilterPath());

			logger.info("Got file: " + fileChooser.getFileName());
			String[] fileN = fileChooser.getFileName().split("\\.");
			// logger.info("Array:" + Arrays.toString(fileN));

			if (fileN != null && fileN.length > 0) {
				blot.setExodus_file(fileN[0]);
			}
		}
	}

	/**
	 * Update/Generate the required files and invoke Blot command via BlotState
	 * instance
	 */
	protected void executeBlot() {
		logger.info("executeBlot() entered");

		//logger.info("Custom Command: " + textCMD.getText());
		//blot.setBlotCmdText(textCMD.getText());

		/*if (buttonCMD.getSelection()) {
			logger.fine(textCMD.getText());
			blot.setBlotCmdText(textCMD.getText());
		}*/

		if (blot.getExodus_file() != "") {
			blot.updateCmdFile();
			blot.updateMakeFile();

			int returnValue = blot.execute();
			logger.fine("executeBlot Return Code: " + returnValue);
			// TODO: Check return value from BlotState and inform user if any
			// error has occurred

			loadImage();
		}

	}

	/**
	 * Load the image from file and send to Canvas for display.
	 * 
	 */
	protected void loadImage() {
		logger.info("loadImage() entered...");

		logger.info("Loading image at: " + blot.getImg_path());

		/* Get the image path from Blot state */
		logger.info("Loading Image: " + blot.getImg_path().trim());
		String img_src = blot.getImg_path().trim();

		if (img_src != "") {
			/* Clear the old image */
			if (image != null && image.isDisposed() == false)
				image.dispose();
			image = null;

			/* Load new image */
			image = new Image(canvas.getDisplay(), blot.getImg_path());

			/* Resize canvas layout */
			canvas.setLayoutData(new RowData(image.getBounds().width, image.getBounds().height));

			/* Set the image in canvas */
			canvas.setBackgroundImage(image);

			// GC gc = new GC(canvas);
			// gc.drawImage(image, image.getBounds().width,
			// image.getBounds().height);

		}
	}
	
	void setCmdText(boolean active) {
		if (active) {
			logger.info("Activating custom command");
			String blotCmdText = this.textCMD.getText();
			this.blot.setBlotCmdText(blotCmdText);
		}
		else {
			logger.info("Deactivating custom command");
			this.blot.setBlotCmdText("");
		}
	}
}
