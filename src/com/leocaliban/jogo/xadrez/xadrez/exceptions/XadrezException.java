package com.leocaliban.jogo.xadrez.xadrez.exceptions;

import com.leocaliban.jogo.xadrez.tabuleiro.exceptions.TabuleiroException;

public class XadrezException extends TabuleiroException {

	private static final long serialVersionUID = 1L;

	public XadrezException(String mensagem) {
		super(mensagem);
	}
}
