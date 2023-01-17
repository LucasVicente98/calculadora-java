package br.com.vicente027.calc.vision;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {

	public Button(String label, Color color) {
		setText(label);
		setFont(new Font("calibri", Font.PLAIN, 20));
		setOpaque(true);
		setBackground(color);
		setForeground(Color.WHITE);
	//	setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
