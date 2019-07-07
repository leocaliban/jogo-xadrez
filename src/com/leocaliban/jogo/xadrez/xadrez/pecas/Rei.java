package com.leocaliban.jogo.xadrez.xadrez.pecas;

import com.leocaliban.jogo.xadrez.tabuleiro.Posicao;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.Partida;
import com.leocaliban.jogo.xadrez.xadrez.PecaDeXadrez;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;

public class Rei extends PecaDeXadrez {

	private Partida partida;

	public Rei(Tabuleiro tabuleiro, Cor cor, Partida partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean testarRoque(Posicao posicao) {
		PecaDeXadrez peca = (PecaDeXadrez) getTabuleiro().buscarPecaPorPosicao(posicao);
		return peca != null && peca instanceof Torre && peca.getCor() == getCor() && peca.getContagemMovimentos() == 0;
	}

	@Override
	public boolean[][] movimentosPermitidos() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao posicaoAux = new Posicao(0, 0);

		// Marcando posi��es acima
		posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posi��es � esquerda
		posicaoAux.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posi��es � direita
		posicaoAux.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posi��es abaixo
		posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posi��es � esquerda/acima
		posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posi��es � direita/abaixo
		posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posi��es � esquerda/abaixo
		posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Marcando posi��es � direita/acima
		posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(posicaoAux) && podeMoverPara(posicaoAux)) {
			matriz[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
		}

		// Movimento de ROQUE
		if (getContagemMovimentos() == 0 && !partida.isCheck()) {
			// Teste roque lado rei
			Posicao posicaoTorre1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testarRoque(posicaoTorre1)) {
				Posicao posicao1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao posicao2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().buscarPecaPorPosicao(posicao1) == null
						&& getTabuleiro().buscarPecaPorPosicao(posicao2) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			// Teste roque lado rainha
			Posicao posicaoTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if (testarRoque(posicaoTorre2)) {
				Posicao posicao1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao posicao2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao posicao3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().buscarPecaPorPosicao(posicao1) == null
						&& getTabuleiro().buscarPecaPorPosicao(posicao2) == null
						&& getTabuleiro().buscarPecaPorPosicao(posicao3) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		return matriz;
	}

	private boolean podeMoverPara(Posicao posicao) {
		PecaDeXadrez peca = (PecaDeXadrez) getTabuleiro().buscarPecaPorPosicao(posicao);
		return peca == null || peca.getCor() != getCor();
	}
}
