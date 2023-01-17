package br.com.vicente027.calc.vision;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.vicente027.calc.model.Memory;

@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener{
	private final Color DARK_GRAY = new Color(68, 69, 68);
	private final Color LIGHT_GRAY = new Color(97, 100, 99);
	private final Color ORANGE = new Color(242, 163, 60);
	
	public Keyboard() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		
		c.weightx = 1; // faz o preenchimento de todo o espaço
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH; // Preenchimento automático dos espaços dos botões no eixo x e y
		
		// Line 1
		c.gridwidth = 2;
		addButton("AC", DARK_GRAY, c, 0, 0);
	//	addButton("+/-", DARK_GRAY, c, 1, 0);
	//	addButton("%", DARK_GRAY, c, 2, 0);
		c.gridwidth = 1;
		addButton("±", DARK_GRAY, c, 2, 0);
		addButton("÷", ORANGE, c, 3, 0);
		
		// Line 2
		addButton("7", LIGHT_GRAY, c, 0, 1);
		addButton("8", LIGHT_GRAY, c, 1, 1);
		addButton("9", LIGHT_GRAY, c, 2, 1);
		addButton("*", ORANGE, c, 3, 1);
		
		// Line 3
		addButton("4", LIGHT_GRAY, c, 0, 2);
		addButton("5", LIGHT_GRAY, c, 1, 2);
		addButton("6", LIGHT_GRAY, c, 2, 2);
		addButton("-", ORANGE, c, 3, 2);
		
		// Line 4
		addButton("1", LIGHT_GRAY, c, 0, 3);
		addButton("2", LIGHT_GRAY, c, 1, 3);
		addButton("3", LIGHT_GRAY, c, 2, 3);
		addButton("+", ORANGE, c, 3, 3);
		
		// Line 5
		c.gridwidth = 2;
		addButton("0", LIGHT_GRAY, c, 0, 4);
		c.gridwidth = 1;
		addButton(",", LIGHT_GRAY, c, 2, 4);
		addButton("=", ORANGE, c, 3, 4);
		
	}

	private void addButton(String text, Color color, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Button button = new Button(text, color);
		button.addActionListener(this); // 
		add(button, c);
		
	}
	
	// Método necessário para cada um dos botões
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource(); // getSource gera um object, sendo assim necessário fazer um cast
			Memory.getInstance().commandProcess(button.getText());
		}
	}
}
	