package com.leocaliban.jogo.xadrez.xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.leocaliban.jogo.xadrez.tabuleiro.Peca;
import com.leocaliban.jogo.xadrez.tabuleiro.Posicao;
import com.leocaliban.jogo.xadrez.tabuleiro.Tabuleiro;
import com.leocaliban.jogo.xadrez.xadrez.enums.Cor;
import com.leocaliban.jogo.xadrez.xadrez.exceptions.XadrezException;
import com.leocaliban.jogo.xadrez.xadrez.pecas.Bispo;
import com.leocaliban.jogo.xadrez.xadrez.pecas.Cavalo;
import com.leocaliban.jogo.xadrez.xadrez.pecas.Peao;
import com.leocaliban.jogo.xadrez.xadrez.pecas.Rainha;
import com.leocaliban.jogo.xadrez.xadrez.pecas.Rei;
import com.leocaliban.jogo.xadrez.xadrez.pecas.Torre;

public class Partida {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public Partida() {
		this.tabuleiro = new Tabuleiro(8, 8);
		this.turno = 1;
		this.jogadorAtual = Cor.BRANCO;
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

	public boolean[][] movimencoesPermitidas(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoDeOrigem(posicao);
		return tabuleiro.buscarPecaPorPosicao(posicao).movimentosPermitidos();
	}

	public PecaDeXadrez movimentarPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();

		validarPosicaoDeOrigem(origem);
		validarPosicaoDeDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);

		if (testarCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("Você não pode se colocar em CHECK!");
		}

		check = (testarCheck(obterOponente(jogadorAtual))) ? true : false;

		if (testarCheckMate(obterOponente(jogadorAtual))) {
			checkMate = true;
		} else {
			passarVez();
		}
		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca realizarMovimento(Posicao origem, Posicao destino) {
		PecaDeXadrez peca = (PecaDeXadrez) tabuleiro.removerPeca(origem);
		peca.adicionarContagem();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);

		tabuleiro.posicionarPeca(peca, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaDeXadrez peca = (PecaDeXadrez) tabuleiro.removerPeca(destino);
		peca.removerContagem();
		tabuleiro.posicionarPeca(peca, origem);

		if (pecaCapturada != null) {
			tabuleiro.posicionarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}

	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem.");
		}

		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.buscarPecaPorPosicao(posicao)).getCor()) {
			throw new XadrezException("A peça escolhida não é sua.");
		}

		if (!tabuleiro.buscarPecaPorPosicao(posicao).isMovimentoPermitido()) {
			throw new XadrezException("Não existe movimentos permitidos para a peça selecionada.");
		}
	}

	private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.buscarPecaPorPosicao(origem).movimentosPermitidos(destino)) {
			throw new XadrezException("A peça selecionada não pode ser movida para o destino escolhido.");
		}
	}

	private void passarVez() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor obterOponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PecaDeXadrez buscarRei(Cor cor) {
		List<Peca> pecas = pecasNoTabuleiro.stream().filter(p -> ((PecaDeXadrez) p).getCor() == cor)
				.collect(Collectors.toList());

		for (Peca peca : pecas) {
			if (peca instanceof Rei) {
				return (PecaDeXadrez) peca;
			}
		}
		throw new IllegalStateException("Não existe REI de cor " + cor + " no tabuleiro.");
	}

	private boolean testarCheck(Cor cor) {
		Posicao posicaoRei = buscarRei(cor).getPocicaoXadrez().toPosicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream()
				.filter(p -> ((PecaDeXadrez) p).getCor() == obterOponente(cor)).collect(Collectors.toList());

		for (Peca peca : pecasOponente) {
			boolean[][] matriz = peca.movimentosPermitidos();
			if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testarCheckMate(Cor cor) {
		if (!testarCheck(cor)) {
			return false;
		}

		List<Peca> pecas = pecasNoTabuleiro.stream().filter(p -> ((PecaDeXadrez) p).getCor() == cor)
				.collect(Collectors.toList());

		for (Peca peca : pecas) {
			boolean[][] matriz = peca.movimentosPermitidos();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (matriz[i][j]) {
						Posicao origem = ((PecaDeXadrez) peca).getPocicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = realizarMovimento(origem, destino);
						boolean testeCheck = testarCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void posicionarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void inicializar() {
		posicionarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

		posicionarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));

	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean isCheck() {
		return check;
	}

	public boolean isCheckMate() {
		return checkMate;
	}

}
