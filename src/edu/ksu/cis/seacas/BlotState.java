/**
 * 
 */
package edu.ksu.cis.seacas;

import java.util.logging.*;

/**
 * @author chandan
 * 
 * A simple java class to maintain the state of the plot.
 */
class BlotState {
	
	private String exodus_file;
	private int current_x;
	private int current_y;
	private String ps_path;
	private String img_path;
	
	private final Logger logger = Logger.getLogger(this.getClass().getPackage().getName());
	
	/**
	 * This will execute the Blot command and save the output on PS and also convert into GIF.
	 * Later, the Get can be used to get the new image. 
	 * 
	 * @return Success code. Zero is OK. 
	 */
	public int execute() {
		
		logger.entering(this.getClass().toString(),"execute()");
		
		return 0;	
	}
	
	public String getExodus_file() {
		return exodus_file;
	}
	public void setExodus_file(String exodus_file) {
		this.exodus_file = exodus_file;
	}
	public int getCurrent_x() {
		return current_x;
	}
	public void setCurrent_x(int current_x) {
		this.current_x = current_x;
	}
	public int getCurrent_y() {
		return current_y;
	}
	public void setCurrent_y(int current_y) {
		this.current_y = current_y;
	}
	public String getPs_path() {
		return ps_path;
	}
	public void setPs_path(String ps_path) {
		this.ps_path = ps_path;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
}
