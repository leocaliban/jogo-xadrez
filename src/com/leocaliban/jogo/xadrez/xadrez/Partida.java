package com.leocaliban.jogo.xadrez.xadrez;

import com.leocaliban.jogo.xadrez.tabuleiro.Peca;
import com.leocaliban.jogo.xadrez.tabuleiro.Posicao;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;
import com.leocaliban.jogo.xadrez.xadrez.exceptions.XadrezException;
import com.leocaliban.jogo.xadrez.xadrez.pecas.Rei;
import com.leocaliban.jogo.xadrez.xadrez.pecas.Torre;

public class Partida {

	private Tabuleiro tabuleiro;

	public Partida() {
		this.tabuleiro = new Tabuleiro(8, 8);
		this.inicializar();
	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] matriz = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaDeXadrez) tabuleiro.buscarPecaPorCoordenadas(i, j);
			}
		}
		return matriz;
	}

	public PecaDeXadrez movimentarPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();

		validarPosicaoDeOrigem(origem);
		validarPosicaoDeDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);
		return (PecaDeXadrez) pecaCapturada;
	}

	private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.buscarPecaPorPosicao(origem).movimentosPermitidos(destino)) {
			throw new XadrezException("A peça selecionada não pode ser movida para o destino escolhido.");
		}
		
	}

	private Peca realizarMovimento(Posicao origem, Posicao destino) {
		Peca peca = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);

		tabuleiro.posicionarPeca(peca, destino);

		return pecaCapturada;
	}

	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem.");
		}
		
		if (!tabuleiro.buscarPecaPorPosicao(posicao).isMovimentoPermitido()) {
			throw new XadrezException("Não existe movimentos permitidos para a peça selecionada.");
		}
	}

	private void posicionarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}

	private void inicializar() {
		posicionarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		posicionarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
