/*
 * AlgoLexer.java v1.00 28/08/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (visualgo@googlegroups.com)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package compiler.lexical;

import compiler.lexical.TokenAlgo.TokenAlgoType;

/**
 * This is the lexer for the algo compiler.
 *
 * @author Damien Rigoni
 * @version 1.00 28/08/08
 */
public class AlgoLexer implements ILexer {

    private IPeeker peeker;

    private boolean mustRead;

    private int peek;

    @Deprecated
    public AlgoLexer(IPeeker p) {
        peeker = p;
        mustRead = true;
        peek = ' ';
    }

    @Override
    public TokenAlgo nextToken() throws Exception {
        if (mustRead == true) {
            peek = peeker.nextChar();
        } else {
            mustRead = true;
        }

        if (Character.isLetter(peek)) {
            // Id
            StringBuffer word = new StringBuffer();
            do {
                word.append((char) peek);
                peek = peeker.nextChar();
            } while (Character.isLetterOrDigit(peek));
            
            mustRead = false;
            return Character.isUpperCase(word.charAt(0))
                    ? TokenAlgoFactory.getToken(TokenAlgoType.TYPE, word.toString())
                    : TokenAlgoFactory.getToken(TokenAlgoType.ID, word.toString());
        } else if (Character.isDigit(peek)) {
            // Number
            StringBuffer number = new StringBuffer();
            do {
                number.append((char) peek);
                peek = peeker.nextChar();
            } while (Character.isDigit(peek));
            
            if (peek != '.') {
                mustRead = false;
                return TokenAlgoFactory.getToken(TokenAlgoType.INT, number.toString());
            } else {
                number.append((char) peek);
                do {
                    number.append((char) peek);
                    peek = peeker.nextChar();
                } while (Character.isDigit(peek));

                mustRead = false;
                return TokenAlgoFactory.getToken(TokenAlgoType.REAL, number.toString());
            }
        } else {
            switch (peek) {
            case '>':
                peek = peeker.nextChar();
                if (peek == '=') {
                    return TokenAlgoFactory.getToken(TokenAlgoType.GEQ, ">=");
                } else {
                    mustRead = false;
                    return TokenAlgoFactory.getToken(TokenAlgoType.GREATER, ">");
                }
            case '<':
                peek = peeker.nextChar();
                if (peek == '=') {
                    return TokenAlgoFactory.getToken(TokenAlgoType.LEQ, "<=");
                } else if (peek == '-') {
                    return TokenAlgoFactory.getToken(TokenAlgoType.ASSIGN, "<-");
                } else {
                    mustRead = false;
                    return TokenAlgoFactory.getToken(TokenAlgoType.LESS, "<");
                }
            case '=':
                return TokenAlgoFactory.getToken(TokenAlgoType.EQ, "=");
            case '!':
                peek = peeker.nextChar();
                if (peek == '=') {
                    return TokenAlgoFactory.getToken(TokenAlgoType.NEQ, "!=");
                } else {
                    mustRead = false;
                    return TokenAlgoFactory.getToken(TokenAlgoType.NOT, "!");
                }
            case ':':
                return TokenAlgoFactory.getToken(TokenAlgoType.COLON, ":");
            case '(':
                return TokenAlgoFactory.getToken(TokenAlgoType.LPAREN, "(");
            case ')':
                return TokenAlgoFactory.getToken(TokenAlgoType.RPAREN, ")");
            case ',':
                return TokenAlgoFactory.getToken(TokenAlgoType.COMMA, ",");
            case ';':
                return TokenAlgoFactory.getToken(TokenAlgoType.SEMICOLON, ";");
            case '.':
                return TokenAlgoFactory.getToken(TokenAlgoType.DOT, ".");
            case '+':
            case '-':
            case '*':
            case '/':
                return TokenAlgoFactory.getToken(TokenAlgoType.OP,
                        String.valueOf(peek));
            case '\n':
            case ' ':
            case '\t':
                return nextToken();
            case -1:
                return TokenAlgo.NULL;
            default:
                throw new LexicalException((char) peek, peeker.getRowNumber(),
                        peeker.getColumnNumber());
            }
        }
    }

    /**
     * Return the current row number of the input.
     * 
     * @return the current row number
     */
    public int getRowNumber() {
        return peeker.getRowNumber();
    }

    /**
     * Return the current column number of the input.
     * 
     * @return the current column number
     */
    public int getColumnNumber() {
        return peeker.getColumnNumber();
    }

    public void setAlgo(String algoFileName) throws Exception {
        peeker.setAlgoFileName(algoFileName);
    }
}
