package Tools;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Utils.FileLoader;

public class CreateAnimation {

	private JFrame frame;
	private File[] files;
	private String animationName;
	private static final String PATH = "/Animations/";

	public CreateAnimation() {
		nameWindow();
	}

	private void nameWindow() {
		JFrame frame = new JFrame("Nome da Animção");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 2));

		frame.setResizable(false);

		JTextField nome = new JTextField();
		JButton ok = new JButton("OK");

		frame.add(nome);
		frame.add(ok);

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nome.getText().isEmpty()) {
					animationName = nome.getText();
					frame.dispose();
					initWindow();
					getWindowContents();
				} else
					return;
			}
		});
		frame.pack();
		frame.setVisible(true);
	}

	private void initWindow() {
		frame = new JFrame("Animation Creator");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.setResizable(false);
		frame.setVisible(true);
	}

	private void getWindowContents() {
		JButton loadPics = new JButton("LoadPics");
		JButton GenerateAnim = new JButton("GenerateAnim");
		JButton exit = new JButton("Exit");

		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		loadPics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showOpenDialog(frame);
				saveFiles(chooser.getSelectedFiles());
			}
		});

		GenerateAnim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createAnim();
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		frame.add(loadPics, BorderLayout.WEST);
		frame.add(GenerateAnim, BorderLayout.CENTER);
		frame.add(exit, BorderLayout.EAST);
		frame.pack();
	}

	private void saveFiles(File[] selectedFiles) {
		files = selectedFiles;
	}

	private void createAnim() {
		if (files != null && files.length != 0) {
			if (files.length == 1)
				System.out.println("So um frame nao forma uma animação");
			else {
				FileLoader.saveFile(PATH + animationName + ".anim", files);
			}
		}
	}

	public static void main(String[] args) {
		new CreateAnimation();
	}

}
