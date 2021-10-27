package br.com.diego.visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.diego.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {

	private final Color COR_CINZA_ESCURO = new Color(71, 67, 74);
	private final Color COR_CINZA_CLARO = new Color(99, 97, 99);
	private final Color COR_LARANJA = new Color(242, 161, 62);
	
	
	public Teclado() {
		
		GridBagLayout layout = new GridBagLayout(); //gerenciador de layout 
		GridBagConstraints c = new GridBagConstraints();
		setLayout(layout);
		
		c.weightx = 1; // para centralizar na aplicação
		c.weighty = 1; //para centralizar na aplicação 
		c.fill = GridBagConstraints.BOTH; //PREENCHER ESPAÇOS FALTANTES
		
		
		//linha 1
		adicionarBotao("AC", COR_CINZA_ESCURO, c, 0,0);
		adicionarBotao("%", COR_CINZA_ESCURO, c, 1,0);
		adicionarBotao("±", COR_CINZA_ESCURO, c, 2,0);
		adicionarBotao("/", COR_LARANJA, c, 3,0);
		
		//linha 2
		adicionarBotao("7", COR_CINZA_CLARO, c, 0,1);
		adicionarBotao("8", COR_CINZA_CLARO, c, 1,1);
		adicionarBotao("9", COR_CINZA_CLARO, c, 2,1);
		adicionarBotao("*", COR_LARANJA, c, 3,1);
		
		//linha 3
		adicionarBotao("4", COR_CINZA_CLARO, c, 0,2);
		adicionarBotao("5", COR_CINZA_CLARO, c, 1,2);
		adicionarBotao("6", COR_CINZA_CLARO, c, 2,2);
		adicionarBotao("-", COR_LARANJA, c, 3,2);
		
		//linha 4
		adicionarBotao("1", COR_CINZA_CLARO, c, 0,3);
		adicionarBotao("2", COR_CINZA_CLARO, c, 1,3);
		adicionarBotao("3", COR_CINZA_CLARO, c, 2,3);
		adicionarBotao("+", COR_LARANJA, c, 3,3);
		
		//linha 5
		c.gridwidth = 2; //fazer ocupar mais de uma posição
		adicionarBotao("0", COR_CINZA_CLARO, c, 0,4);
		c.gridwidth = 1;
		adicionarBotao(",", COR_CINZA_CLARO, c, 2,4);
		adicionarBotao("=", COR_LARANJA, c, 3,4);
		
		
		
	}


	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) { //criado para add os obotes 
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao, c);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { //evento acontecendo 
		if(e.getSource() instanceof JButton) {
		JButton botao = (JButton) e.getSource();
		Memoria.getInstancia().processarComando(botao.getText());
		}
	}
	
}
