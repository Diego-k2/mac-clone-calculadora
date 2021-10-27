package br.com.diego.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;




@SuppressWarnings("serial")
public class Botao extends JButton{

	private final Color COR_BORDA = new Color(47, 48, 51);
	
	public Botao(String texto , Color cor) {
		setText(texto);
		setOpaque(true);
		setBackground(cor);
		setBorder(BorderFactory.createLineBorder(COR_BORDA));
		setFont(new Font("courier", Font.PLAIN, 20));
		setForeground(Color.WHITE); //MUDA COR DA FONTE 
	}
	
}
