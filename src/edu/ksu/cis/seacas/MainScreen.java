package edu.ksu.cis.seacas;

import java.util.Arrays;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class MainScreen {

	private final Logger logger = Logger.getLogger(this.getClass().getPackage().getName());

	protected Shell shell;
	protected Canvas canvas;
	protected Image image;

	//TODO: Implement in config file
	private String currentDir ="/Users/chandan/seacas/cmds";
	private Text textExodusFilePath;
	
	/**
	 * The state of the Blot along with commands will be stored here.
	 */
	private BlotState blot = new BlotState();
	
	private Text textRotXNeg;
	private Text textRotX;
	private Text textRotYNeg;
	private Text textRotY;

	

	/**
	 * Launch the application.
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
		shell = new Shell();
		shell.setSize(830, 750);
		shell.setText("SEACAS - Blot Viewer");
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));

		/* Details of Exodus File Selector */
		Composite compositeExodus = new Composite(shell, SWT.NONE);
		compositeExodus.setLayout(new RowLayout(SWT.HORIZONTAL));
		compositeExodus.setLayoutData(new RowData(800, 40));

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
				//loadImage();
				/* Execute Blot to generate new image */
				executeBlot();
				
				loadImage();
			}
		}
		);

		/* Details of Canvas which will be used for displaying the image */
		Composite compositeCanvas = new Composite(shell, SWT.NONE);
		compositeCanvas.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		canvas = new Canvas(compositeCanvas, SWT.NONE);
		//canvas.setLayout(new FillLayout(SWT.HORIZONTAL));
		canvas.setLayoutData(new RowData(800, 600));
		
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				if (image != null) {
					//System.out.println("\npaintControl Invoked...");
					canvas.setLayoutData(new RowData(image.getBounds().width, image.getBounds().height));
					e.gc.drawImage(image, 0, 0);
					//image.dispose();
				}
				
			}
		});


		/* Details of X-axis buttons */
		Composite compositeBtnX = new Composite(shell, SWT.NONE);
		compositeBtnX.setLayout(new RowLayout(SWT.HORIZONTAL));
		compositeBtnX.setLayoutData(new RowData(700, 30));

		Button btnRotXLargeNeg = new Button(compositeBtnX, SWT.NONE);
		btnRotXLargeNeg.setText("<< 5");
		btnRotXLargeNeg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateX(-5);
			}
		});
		

		Button btnRotXSmallNeg = new Button(compositeBtnX, SWT.NONE);
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

		
		
		Button btnRotXSmallPos = new Button(compositeBtnX, SWT.NONE);
		btnRotXSmallPos.setText("2 >");
		btnRotXSmallPos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateX(2);
			}
		});

		
		Button btnRotXLargePos = new Button(compositeBtnX, SWT.NONE);
		btnRotXLargePos.setText("5 >>");
		btnRotXLargePos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateX(5);
			}
		});

		textRotX = new Text(compositeBtnX, SWT.BORDER);
		
		/* Details of Y-axis buttons */
		Composite compositeBtnY = new Composite(shell, SWT.NONE);
		compositeBtnY.setLayout(new RowLayout(SWT.HORIZONTAL));
		compositeBtnY.setLayoutData(new RowData(700, 30));

		
		Button btnRotYLargeNeg = new Button(compositeBtnY, SWT.NONE);
		btnRotYLargeNeg.setText("<< 5");
		btnRotYLargeNeg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateY(-5);
			}
		});
		
		Button btnRotYSmallNeg = new Button(compositeBtnY, SWT.NONE);		
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

		
		
		Button btnRotYSmallPos = new Button(compositeBtnY, SWT.NONE);
		btnRotYSmallPos.setText("2 >");
		btnRotYSmallPos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateY(2);
			}
		});


		Button btnRotYLargePos = new Button(compositeBtnY, SWT.NONE);
		btnRotYLargePos.setText("5 >>");
		btnRotYLargePos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rotateY(5);
			}
		});

		textRotY = new Text(compositeBtnY, SWT.BORDER);
	}

	/**
	 * Handle X-axis rotation and update the image.
	 * 
	 **/
	protected void rotateX(int degree) {
		logger.finer("Rotate X by " + degree);
		//Set the new X-coordinate to current X-coordinate plus degree
		this.blot.setCurrent_x(this.blot.getCurrent_x() + degree);
		// Execute Blot to generate the updated image
		executeBlot();
		//Load the updated image
		loadImage();
	}

	/**
	 * Handle Y-axis rotation and update the image.
	 *
	 **/
	protected void rotateY(int degree) {
		logger.finer("Rotate Y by " + degree);

		//Set the new Y-coordinate to current Y-coordinate plus degree
		this.blot.setCurrent_y(this.blot.getCurrent_y() + degree);
		
		// Execute Blot to generate the updated image
		executeBlot();
		//Load the updated image
		loadImage();
		
	}
	
	/**
	 * Handle Z-axis rotation and update the image.
	 *
	 **/
	protected void rotateZ(int degree) {
		logger.finer("Rotate Z by " + degree);

		//Set the new Y-coordinate to current Y-coordinate plus degree
		this.blot.setCurrent_z(this.blot.getCurrent_z() + degree);
		
		// Execute Blot to generate the updated image
		executeBlot();
		//Load the updated image
		loadImage();
		
	}

	protected void selectExodusFile() {
		FileDialog fileChooser = new FileDialog(this.shell, SWT.OPEN);
		fileChooser.setText("Select EXODUS file");
		fileChooser.setFilterPath(currentDir);
		fileChooser.setFilterExtensions(
		    new String[] { "*.e;" });
		fileChooser.setFilterNames (
		    new String[] { "EXODUS File" + " (e)" });
		String filename = fileChooser.open();
		if (filename != null){
			
			textExodusFilePath.setText(fileChooser.getFilterPath() + filename);
			currentDir = fileChooser.getFilterPath();
			
			blot.setBlotExodusFileDir(fileChooser.getFilterPath());
			
			logger.info("Got file: "+fileChooser.getFileName());
			String[] fileN = fileChooser.getFileName().split("\\.");
			logger.info("Array:" + Arrays.toString(fileN));
			//logger.info("Name part: "+fileN[0]);
			if(fileN != null && fileN.length > 0) {
				blot.setExodus_file(fileN[0]);
			}
			
			//TODO: Comment out when Exodus processing is enabled.
			//blot.setImg_path(filename);
			//loadImage();
		}
	}

	protected void executeBlot() {
		blot.updateCmdFile();
		blot.updateMakeFile();
		
		int returnValue = blot.execute();
		logger.fine("executeBlot Return Code: "+returnValue);
	}

	/**
	 * Load the image from file and send to Canvas for display.
	 * 
	 */
	protected void loadImage() {
		logger.info("loadImage() entered...");
		
		logger.info("Loading image at: "+blot.getImg_path());
		
		/* Get the image path from Blot state */
		logger.info("Loading Image: "+blot.getImg_path().trim());
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
			
			//GC gc = new GC(canvas);
			//gc.drawImage(image, image.getBounds().width, image.getBounds().height);
			
		}
	}
}
