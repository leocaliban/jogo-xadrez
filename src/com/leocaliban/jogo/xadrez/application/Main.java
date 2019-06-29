package com.leocaliban.jogo.xadrez.application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.leocaliban.jogo.xadrez.xadrez.Partida;
import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;
import com.leocaliban.jogo.xadrez.xadrez.PosicaoXadrez;
import com.leocaliban.jogo.xadrez.xadrez.exceptions.XadrezException;

public class Main {

	public static void main(String[] args) {
		System.out.println("JOGO DE XADREZ");

		Scanner scan = new Scanner(System.in);

		Partida partida = new Partida();
		List<PecaDeXadrez> pecasCapturadas = new ArrayList<>();

		while (true) {

			try {
				UI.limparTerminal();

				UI.exibirPartida(partida, pecasCapturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez posicaoOrigem = UI.informarPosicaoDaPeca(scan);

				boolean[][] movimentosPermitidos = partida.movimencoesPermitidas(posicaoOrigem);
				UI.limparTerminal();
				UI.exibirTabuleiro(partida.getPecas(), movimentosPermitidos);

				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez posicaoDestino = UI.informarPosicaoDaPeca(scan);

				PecaDeXadrez pecaCapturada = partida.movimentarPeca(posicaoOrigem, posicaoDestino);

				if (pecaCapturada != null) {
					pecasCapturadas.add(pecaCapturada);
				}
			} catch (XadrezException e) {
				System.out.println(e.getMessage());
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				scan.nextLine();
			}
		}
	}

}
