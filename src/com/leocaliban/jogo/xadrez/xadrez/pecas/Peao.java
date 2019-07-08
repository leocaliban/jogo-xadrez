package com.leocaliban.jogo.xadrez.xadrez.pecas;

import com.leocaliban.jogo.xadrez.tabuleiro.Posicao;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.Partida;
import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;

public class Peao extends PecaDeXadrez {

	private Partida partida;

	public Peao(Tabuleiro tabuleiro, Cor cor, Partida partida) {
		super(tabuleiro, cor);
		this.partida = partida;
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

			// en passant Branco
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && isPecaDoAdversario(esquerda)
						&& getTabuleiro().buscarPecaPorPosicao(esquerda) == partida.getEnPassant()) {
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}

				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && isPecaDoAdversario(direita)
						&& getTabuleiro().buscarPecaPorPosicao(direita) == partida.getEnPassant()) {
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
		} else {
			posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(posicaoAux) && !getTabuleiro().existePeca(posicaoAux)) {
				matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
			}

			posicaoAux.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao posicao2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
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

			// en passant Preto
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && isPecaDoAdversario(esquerda)
						&& getTabuleiro().buscarPecaPorPosicao(esquerda) == partida.getEnPassant()) {
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}

				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && isPecaDoAdversario(direita)
						&& getTabuleiro().buscarPecaPorPosicao(direita) == partida.getEnPassant()) {
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return matriz;
	}

	@Override
	public String toString() {
		return "P";
	}

}
