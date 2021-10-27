package br.com.diego.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	//TODO CRIAR BOTAO MUDR DE SINAL E BOTAO PORCENTAGEM
	
	private static final Memoria instancia = new Memoria();
	
	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";
	
	private final List<MemoriaObservador> observadores = new ArrayList<>();
	
	private Memoria () {
	
	}

	public static Memoria getInstancia() {
		return instancia;
	}

	public void adicionarObservador (MemoriaObservador observador) {
		observadores.add(observador);
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}	
	
	public void processarComando(String texto) {
		
		TipoComando tipoComando = detectarTipoComando(texto);
		
		if(tipoComando == null) { 
			return;
		} else if (tipoComando == TipoComando.ZERAR) {
			ultimaOperacao = null;
			substituir = false;
			textoAtual = "";
			textoBuffer = "";
		} else if (tipoComando == TipoComando.NUMERO
				|| tipoComando == TipoComando.VIRGULA) {
			
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else if (tipoComando == TipoComando.MUDARSINAL && textoAtual.contains("-")) {
			textoAtual = textoAtual.substring(1);
		} else if (tipoComando == TipoComando.MUDARSINAL && !textoAtual.contains("-")) {
			textoAtual = "-" + textoAtual;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;
		} 
		
		
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		
		double resultado = 0;
		
		if(ultimaOperacao == TipoComando.SOMAR) {
			resultado = numeroBuffer + numeroAtual;
		} else if (ultimaOperacao == TipoComando.SUBTRAIR) {
			resultado = numeroBuffer - numeroAtual;
		} else if (ultimaOperacao == TipoComando.MULTIPLICAR) {
			resultado = numeroBuffer * numeroAtual;
		} else if (ultimaOperacao == TipoComando.DIVIDIR) {
			resultado = numeroBuffer / numeroAtual;
		} else if (ultimaOperacao == TipoComando.PORCENTAGEM) {
			resultado = numeroAtual * (numeroBuffer /100);
		}
		
		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");
		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	}

	private TipoComando detectarTipoComando(String texto) {
		
		if(textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			//quando nao for numero 
			if("AC".equals(texto)) {
				return TipoComando.ZERAR;
			} else if ("/".equals(texto)) {
				return TipoComando.DIVIDIR;
			} else if ("*".equals(texto)) {
				return TipoComando.MULTIPLICAR;
			} else if ("+".equals(texto)) {
				return TipoComando.SOMAR;
			} else if ("-".equals(texto)) {
				return TipoComando.SUBTRAIR;
			} else if ("=".equals(texto)) {
				return TipoComando.IGUAL;
			} else if (",".equals(texto) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			} else if ("±".equals(texto)) {
				return TipoComando.MUDARSINAL;
			} else if ("%".equals(texto)) {
				return TipoComando.PORCENTAGEM;
			}
			
		}
		
		return null;
	}
	
}
