package com.leocaliban.jogo.xadrez.xadrez;

import com.leocaliban.jogo.xadrez.tabuleiro.Peca;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;

public class PecaDeXadrez extends Peca {

	private Cor cor;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

}
