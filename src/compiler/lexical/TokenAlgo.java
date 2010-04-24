/*
 * TokenAlgo.java v0.10 28/08/08
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

/**
 * This class represents the tokens. The tokens are created by the
 * TokenAlgoFactory.
 *
 * @author Damien Rigoni
 * @version 0.10 28/08/08
 */
public class TokenAlgo {

    private final TokenAlgoType type;

    private final String lexeme;

    /**
     * For each terminal in grammar we need constants and this enum provide
     * these constants.
     */
    public static enum TokenAlgoType {
        WHILE, ELSE, ALGORITHM, RETURN, VAR, BEGIN, END, TRUE, FALSE, IF, THEN,
        DO, ID, TYPE, AND, OR, REAL, INT, OP, EQ, NEQ, COLON, LPAREN, RPAREN,
        SEMICOLON, COMMA, ASSIGN, GEQ, // Greater or Equal
        GREATER, LEQ, // Less or equal
        LESS, DOT, NOT, BREAK, NULL
    }

    public static TokenAlgo NULL = new TokenAlgo(TokenAlgoType.NULL, "");

    TokenAlgo(TokenAlgoType t, String lex) {
        type = t;
        lexeme = lex;
    }

    @Override
    public String toString() {
        return lexeme;
    }

    public TokenAlgoType getTokenType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TokenAlgo)) {
            return false;
        } else {
            return lexeme.equals(((TokenAlgo) o).lexeme)
                    && type == ((TokenAlgo) o).type;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + lexeme.hashCode();
        
        return result;
    }
}
