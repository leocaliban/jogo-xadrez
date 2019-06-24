package com.leocaliban.jogo.xadrez.application;

import com.leocaliban.jogo.xadrez.xadrez.Partida;

public class Main {

	public static void main(String[] args) {
		System.out.println("JOGO DE XADREZ");

		Partida partida = new Partida();

		UI.exibirTabuleiro(partida.getPecas());
	}

}
