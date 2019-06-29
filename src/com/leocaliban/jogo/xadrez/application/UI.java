package com.leocaliban.jogo.xadrez.application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.leocaliban.jogo.xadrez.xadrez.Partida;
import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;
import com.leocaliban.jogo.xadrez.xadrez.PosicaoXadrez;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void limparTerminal() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static PosicaoXadrez informarPosicaoDaPeca(Scanner scan) {
		try {
			String movimento = scan.nextLine();

			char coluna = movimento.charAt(0);
			int linha = Integer.parseInt(movimento.substring(1));

			return new PosicaoXadrez(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posição no tabuleiro. Valores válidos: A1 ao H8");
		}
	}

	public static void exibirPartida(Partida partida, List<PecaDeXadrez> pecasCapturadas) {
		exibirTabuleiro(partida.getPecas());
		System.out.println();
		exibirPecasCapturadas(pecasCapturadas);
		System.out.println("Turno: " + partida.getTurno());
		System.out.println("Aguardando jogador: " + partida.getJogadorAtual());
	}

	public static void exibirTabuleiro(PecaDeXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				exibirPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}

	public static void exibirTabuleiro(PecaDeXadrez[][] pecas, boolean[][] movimentosPermitidos) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				exibirPeca(pecas[i][j], movimentosPermitidos[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");

	}

	private static void exibirPeca(PecaDeXadrez peca, boolean colorirCasaPermitida) {
		if (colorirCasaPermitida) {
			System.out.print(ANSI_PURPLE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.BRANCO) {
				System.out.print(ANSI_CYAN + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	private static void exibirPecasCapturadas(List<PecaDeXadrez> pecasCapturadas) {
		List<PecaDeXadrez> pecasCapturadasBrancas = pecasCapturadas.stream().filter(p -> p.getCor() == Cor.BRANCO)
				.collect(Collectors.toList());

		List<PecaDeXadrez> pecasCapturadasPretas = pecasCapturadas.stream().filter(p -> p.getCor() == Cor.PRETO)
				.collect(Collectors.toList());

		System.out.println("Peças capturadas: ");
		System.out.print("BRANCA: ");
		System.out.print(ANSI_CYAN);
		System.out.println(Arrays.toString(pecasCapturadasBrancas.toArray()));
		System.out.print(ANSI_RESET);

		System.out.print("PRETA: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(pecasCapturadasPretas.toArray()));
		System.out.print(ANSI_RESET);
	}

}
