package latex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class Frame {
	
	JFrame frame;
	JLabel icon;
	JSlider slider;
	JTextField input;
	
	LaTex latex;
	
	int Width, Height;
	int size;
	
	public Frame(String title, LaTex latex, int W, int H, int latexSize)
	{
		Width = W;
		Height = H;
		size = latexSize;
		
		this.latex = latex;
		
		JPanel redPanel = new JPanel();
		redPanel.setPreferredSize(new Dimension(100, 75));
		//redPanel.setBackground(Color.red);
		redPanel.setLayout(new BorderLayout());
		
		JPanel bluePanel = new JPanel();
		bluePanel.setPreferredSize(new Dimension(75, 100));
		//bluePanel.setBackground(Color.blue);
		//bluePanel.setLayout(new BorderLayout());
		
		JPanel greenPanel = new JPanel();
		greenPanel.setPreferredSize(new Dimension(100, 100));
		//greenPanel.setBackground(Color.green);
		greenPanel.setLayout(new BorderLayout());
		
		input = new JTextField();
		input.setText(latex.math);
		input.setPreferredSize(new Dimension(100, 100));
		input.setFont(new Font("New Roman", Font.PLAIN, 15));
		input.setHorizontalAlignment(JLabel.CENTER);
		
		JButton btn = new JButton();
		btn.addActionListener((e) -> remakeLatex());
		btn.setFocusable(false);
		btn.setText("Validate");
		btn.setPreferredSize(new Dimension(100,100));
	
		slider = new JSlider(1, 125, size);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(25);
		slider.setPaintLabels(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.addChangeListener((e) -> { remakeLatex(); });
		
		icon = new JLabel();
		icon.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		
		frame = new JFrame();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(W, H);
		frame.setLayout(new BorderLayout(10, 10));
		
		redPanel.add(input, BorderLayout.CENTER);
		redPanel.add(btn, BorderLayout.EAST);
		
		greenPanel.add(icon);
		
		bluePanel.add(slider);
		
		frame.add(redPanel, BorderLayout.NORTH);
		frame.add(bluePanel, BorderLayout.WEST);
		frame.add(greenPanel, BorderLayout.CENTER);
		
		frame.setVisible(true);
		
		remakeLatex();
	}
	
	private void remakeLatex()
	{
		icon.setIcon(latex.remakeIconLaTex(input.getText(), slider.getValue(), icon.getWidth()));
	}
}
