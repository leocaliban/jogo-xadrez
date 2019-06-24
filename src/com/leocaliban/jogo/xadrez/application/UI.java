package com.leocaliban.jogo.xadrez.application;

import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;

public class UI {

	public static void exibirTabuleiro(PecaDeXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				exibirPeca(pecas[i][j]);
			}

			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}

	private static void exibirPeca(PecaDeXadrez peca) {
		if (peca == null) {
			System.out.print("- ");
		} else {
			System.out.print("  ");
		}
	}

}
