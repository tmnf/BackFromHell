package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class FileLoader {

	public static BufferedImage[] loadFile(String path) {
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
			return null;
		}
	}

	public static void saveFile(String path, File[] imgs) {
		try {
			FileOutputStream in = new FileOutputStream(path);
			ObjectOutputStream ob = new ObjectOutputStream(in);

			ob.writeObject(imgs);
			in.close();
			ob.close();
			System.out.println("Sucesso");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(FileLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
