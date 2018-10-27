package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;

public class ImageHandler {

	public static BufferedImage[] loadAnim(String path) {
		try {
			FileInputStream in = new FileInputStream(path);
			ObjectInputStream ob = new ObjectInputStream(in);
			File[] temp = (File[]) ob.readObject();

			BufferedImage[] imgs = new BufferedImage[temp.length];
			for (int i = 0; i != temp.length; i++)
				imgs[i] = ImageIO.read(temp[i]);

			in.close();
			ob.close();
			return imgs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageHandler.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
