package view;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import controller.Fifa;

public class main {
	public main(String [] args) {
		String caminho = "C:\\TEMP";
		String nome = "data.csv";
		Fifa fifa = new Fifa();
		try {
			Stack <String> pilha = fifa.empilhaBrasileiros(caminho, nome);
			List<String> lista = fifa.listaRevelacoes(caminho, nome);
			fifa.desempilhaBonsBrasileiros(pilha);
			fifa.buscaListaBonsJovens(lista);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
