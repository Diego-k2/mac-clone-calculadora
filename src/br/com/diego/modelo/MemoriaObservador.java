package br.com.diego.modelo;
 
@FunctionalInterface

public interface MemoriaObservador {

	public void valorAlterado(String novoValor);
	
}
