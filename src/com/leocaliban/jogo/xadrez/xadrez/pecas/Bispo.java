package com.leocaliban.jogo.xadrez.xadrez.pecas;

import com.leocaliban.jogo.xadrez.tabuleiro.Posicao;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;

public class Bispo extends PecaDeXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPermitidos() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao posicaoAux = new Posicao(0, 0);

		// Marcando posições acima/esquerda
		posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			posicaoAux.setValores(posicaoAux.getLinha() - 1, posicaoAux.getColuna() - 1);
		}

		if (getTabuleiro().posicaoExiste(posicaoAux) && isPecaDoAdversario(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições à acima/direita
		posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			posicaoAux.setValores(posicaoAux.getLinha() - 1, posicaoAux.getColuna() + 1);
		}

		if (getTabuleiro().posicaoExiste(posicaoAux) && isPecaDoAdversario(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições à abaixo/esquerda
		posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			posicaoAux.setValores(posicaoAux.getLinha() + 1, posicaoAux.getColuna() + 1);
		}

		if (getTabuleiro().posicaoExiste(posicaoAux) && isPecaDoAdversario(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições abaixo/direita
		posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			posicaoAux.setValores(posicaoAux.getLinha() + 1, posicaoAux.getColuna() - 1);
		}

		if (getTabuleiro().posicaoExiste(posicaoAux) && isPecaDoAdversario(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		return matriz;
	}

}
