package com.leocaliban.jogo.xadrez.xadrez;

import com.leocaliban.jogo.xadrez.tabuleiro.Peca;
import com.leocaliban.jogo.xadrez.tabuleiro.Posicao;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;

public abstract class PecaDeXadrez extends Peca {

	private Cor cor;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	public PosicaoXadrez getPocicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}

	protected boolean isPecaDoAdversario(Posicao posicao) {
		PecaDeXadrez peca = (PecaDeXadrez) getTabuleiro().buscarPecaPorPosicao(posicao);
		return peca != null && peca.getCor() != cor;
	}

}
