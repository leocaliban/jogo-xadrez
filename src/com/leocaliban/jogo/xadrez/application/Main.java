package com.leocaliban.jogo.xadrez.application;

import java.util.Scanner;

import com.leocaliban.jogo.xadrez.xadrez.Partida;
import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;
import com.leocaliban.jogo.xadrez.xadrez.PosicaoXadrez;

public class Main {

	public static void main(String[] args) {
		System.out.println("JOGO DE XADREZ");

		Scanner scan = new Scanner(System.in);

		Partida partida = new Partida();

		while (true) {

			UI.exibirTabuleiro(partida.getPecas());
			System.out.println();
			System.out.println("Origem: ");
			PosicaoXadrez posicaoOrigem = UI.informarPosicaoDaPeca(scan);

			System.out.println();
			System.out.println("Destino: ");
			PosicaoXadrez posicaoDestino = UI.informarPosicaoDaPeca(scan);

			PecaDeXadrez pecaCapturada = partida.movimentarPeca(posicaoOrigem, posicaoDestino);
		}
	}

}
