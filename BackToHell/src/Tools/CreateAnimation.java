package Tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Utils.FileLoader;

public class CreateAnimation {

	private JFrame frame;
	private File[] files;
	private String animationName;
	private final String PATH = "./res/animations/";

	public CreateAnimation() {
		nameWindow();
	}

	private void nameWindow() {
		JFrame frame = new JFrame("Nome da Animção");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		JTextArea txt = new JTextArea("Insira o nome da animção!");
		JTextField nome = new JTextField();
		JButton ok = new JButton("OK");

		txt.setFont(new Font("ARIAL", 1, 20));
		txt.setEditable(false);
		nome.setPreferredSize(new Dimension(200, 50));
		nome.setFont(new Font("ARIAL", 4, 18));

		frame.add(txt, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.SOUTH);
		panel.add(nome);
		panel.add(ok);

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

		loadPics.setPreferredSize(new Dimension(150, 80));
		GenerateAnim.setPreferredSize(new Dimension(150, 80));
		exit.setPreferredSize(new Dimension(150, 80));

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
				JOptionPane.showMessageDialog(frame, "Animção criada com Sucesso!", "Operação Concluida", 3);
			}
		}
	}

	public static void main(String[] args) {
		new CreateAnimation();

	}

}
