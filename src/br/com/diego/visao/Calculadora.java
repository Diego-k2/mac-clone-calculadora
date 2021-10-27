package br.com.diego.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame {
	
	public Calculadora() { //criar a tela 
		
		
		organizarLayout();
		
		setSize(240, 330);	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void organizarLayout() { // gerenciador de layout 
		setLayout(new BorderLayout());
		
		Display display = new  Display();
		display.setPreferredSize(new Dimension(241, 60));
		add(display, BorderLayout.NORTH);
		
		
		
		Teclado teclado = new Teclado();
		add(teclado, BorderLayout.CENTER);
		
		
	}

	public static void main(String[] args) {
		
		new Calculadora(); //inicia a aplicação
	}
	
}
