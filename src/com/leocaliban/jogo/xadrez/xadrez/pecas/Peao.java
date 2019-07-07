package com.leocaliban.jogo.xadrez.xadrez.pecas;

import com.leocaliban.jogo.xadrez.tabuleiro.Posicao;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;

public class Peao extends PecaDeXadrez {

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPermitidos() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao posicaoAux = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {
			posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}

			posicaoAux.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao posicao2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)
					&& getTabuleiro().posicaoExiste(posicao2) && !getTabuleiro().existePeca(posicao2)
					&& getContagemMovimentos() == 0) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}

			posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(posicaoAux) && isPecaDoAdversario(posicaoAux)) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}

			posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(posicaoAux) && isPecaDoAdversario(posicaoAux)) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}
		} else {
			posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}

			posicaoAux.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao posicao2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)
					&& getTabuleiro().posicaoExiste(posicao2) && !getTabuleiro().existePeca(posicao2)
					&& getContagemMovimentos() == 0) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}

			posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(posicaoAux) && isPecaDoAdversario(posicaoAux)) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}

			posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(posicaoAux) && isPecaDoAdversario(posicaoAux)) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}
		}
		return matriz;
	}

	@Override
	public String toString() {
		return "P";
	}

}
