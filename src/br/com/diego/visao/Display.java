package br.com.diego.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import br.com.diego.modelo.Memoria;
import br.com.diego.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador {

	private final JLabel label;
	
	
	public Display() {
		
		Memoria.getInstancia().adicionarObservador(this);
		
		
		setBackground(new Color(47, 48, 51));
		label = new JLabel(Memoria.getInstancia().getTextoAtual()); //colocar o texto no display
		label.setForeground(Color.WHITE);
		label.setFont(new FontUIResource("courier", Font.PLAIN, 35));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 15));  //gerenciador de layout 
		
		add(label);
	}
	
	
	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
	}
	
}
