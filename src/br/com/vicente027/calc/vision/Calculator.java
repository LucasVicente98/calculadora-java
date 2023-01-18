package br.com.vicente027.calc.vision;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculator extends JFrame {

	public Calculator() {

		arrangeLayout();

		setSize(250, 322);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // garante o encerramento da aplicação
		setLocationRelativeTo(null); // abre a calculadora no ponto central da tela
		setVisible(true);
	}

	private void arrangeLayout() {
		setLayout(new BorderLayout());

		Display display = new Display();
		display.setPreferredSize(new Dimension(233, 60));
		add(display, BorderLayout.NORTH);

		Keyboard keyboard = new Keyboard();
		add(keyboard, BorderLayout.CENTER);
	}

	public static void main(String[] args) {

		new Calculator();
	}
}
