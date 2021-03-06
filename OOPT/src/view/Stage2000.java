package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stage2000 extends JPanel{

	Image img = null;
	Image img2 = null;

	public Stage2000() {
		try{
			String FileName = "." + File.separator + "image" + File.separator + "stage2000.png";
			File sourceimage = new File(FileName);
			img = ImageIO.read(sourceimage);
			img2 = img.getScaledInstance(500, 400, Image.SCALE_SMOOTH);

		}
		catch(IOException e){
			System.out.println("Do not exist image file ");
		}
		JLabel lblNewLabel = new JLabel(new ImageIcon(img2));
		this.setLayout(new BorderLayout());
		add(lblNewLabel);
	}
}
