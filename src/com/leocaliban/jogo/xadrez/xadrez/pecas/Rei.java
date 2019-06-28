package com.leocaliban.jogo.xadrez.xadrez.pecas;

import com.leocaliban.jogo.xadrez.tabuleiro.Posicao;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;

public class Rei extends PecaDeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] movimentosPermitidos() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao posicaoAux = new Posicao(0, 0);

		// Marcando posições acima
		posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições à esquerda
		posicaoAux.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições à direita
		posicaoAux.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições abaixo
		posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições à esquerda/acima
		posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições à direita/abaixo
		posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições à esquerda/abaixo
		posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posições à direita/acima
		posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}
		return matriz;
	}

	private boolean podeMoverPara(Posicao posicao) {
		PecaDeXadrez peca = (PecaDeXadrez) getTabuleiro().buscarPecaPorPosicao(posicao);
		return peca == null || peca.getCor() != getCor();
	}
}
