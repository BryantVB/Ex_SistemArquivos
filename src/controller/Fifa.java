package controller;

import java.util.List;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.util.Stack;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.io.IOException;

public class Fifa implements FifaController {
	
	@Override
	public Stack<String> empilhaBrasileiros(String caminho, String nome) throws IOException {
		String nationality = "Brasil";
		Stack<String> stack = new Stack<String>();
		File file = new File(caminho, nome);
		if(!file.exists() || !file.isFile())
			throw new IOException("Arquivo Inexistente!");
		
		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);
		
		String line = buffer.readLine();
		while(line != null) {
			String line_array[] = line.split(",");
	
			if(line_array[5].contains(nationality)) {
				stack.push(line);
			}
			line = buffer.readLine();
		}
		
		buffer.close();
		reader.close();
		stream.close();
		return stack;
	}

	@Override
	public void desempilhaBonsBrasileiros(Stack<String> pilha) throws IOException {
		System.out.println("[Pilha : melhores brasileiros]");
		while(!pilha.isEmpty()) {
			String jogador[] = (pilha.pop()).split(",");
			int overall = Integer.parseInt(jogador[7]);
			if(overall >= 80) {
				String nome = jogador[2];
				System.out.println("Jogador: " + nome + " - Overall: " + overall);
			}
		}
	}

	@Override
	public List<String> listaRevelacoes(String caminho, String nome) throws IOException {
		int idade_maxima = 20;
		List<String> list = new LinkedList<String>();
		File file = new File(caminho, nome);
		if(!file.exists() || !file.isFile())
			throw new IOException("Arquivo Inexistente");
		
		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);
		
		String line = buffer.readLine();
	
		line = buffer.readLine();
		while(line != null) {
			String line_array[] = line.split(",");
			int idade = Integer.parseInt(line_array[3]);
			if(idade <= idade_maxima) {
				list.add(line);
			}
			line = buffer.readLine();
		}
		buffer.close();
		reader.close();
		stream.close();
		return list;
	}

	@Override
	public void buscaListaBonsJovens(List<String> lista) throws IOException {
		int overall_minimo = 80;
		System.out.println("[Lista : Revelações Jovens]");
		for(int i=lista.size()-1; i >= 0; i--) {
			String jogador[] = (lista.get(i)).split(",");
			int overall = Integer.parseInt(jogador[7]);
			if(overall >= overall_minimo) {
				String nome = jogador[2];
				int idade = Integer.parseInt(jogador[3]);
				System.out.println("Jogador: " + nome + " - Idade: " + idade + " - Overall: " + overall);
			}
		}
	}

}