package br.com.vicente027.calc.vision;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.vicente027.calc.model.Memory;
import br.com.vicente027.calc.model.MemoryObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoryObserver {

	private final JLabel label;

	public Display() {
		Memory.getInstance().addObserver(this); // Já que faz a implementação do método, pode ser utilizado o "this"

		setBackground(new Color(46, 49, 50));
		label = new JLabel(Memory.getInstance().getCurrentText());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("calibri", Font.PLAIN, 30));

		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));

		add(label);
	}

	@Override
	public void changedValue(String newValue) {
		label.setText(newValue);
	}
}
