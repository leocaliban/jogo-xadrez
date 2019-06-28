package com.leocaliban.jogo.xadrez.tabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;

	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] movimentosPermitidos();

	public boolean movimentosPermitidos(Posicao posicao) {
		return movimentosPermitidos()[posicao.getLinha()][posicao.getColuna()];
	}

	public boolean isMovimentoPermitido() {
		boolean[][] matriz = movimentosPermitidos();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
