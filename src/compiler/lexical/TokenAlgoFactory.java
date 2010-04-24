/*
 * TokenAlgoFactory.java v1.00 02/09/08
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
import java.util.HashMap;

/**
 * TokenFactory is an implementation of the flyweight pattern. TokenFactoy
 * creates a new token if none exists or supplies an existing instance.
 *
 * @author Damien Rigoni
 * @version 1.00 02/09/08
 */
public class TokenAlgoFactory {

    private static HashMap<String, TokenAlgo> tokens =
            new HashMap<String, TokenAlgo>();

    private TokenAlgoFactory() {
    }
    
    static {
        tokens.put("algorithm", new TokenAlgo(TokenAlgoType.ALGORITHM, "algorithm"));
        tokens.put("begin", new TokenAlgo(TokenAlgoType.BEGIN, "begin"));
        tokens.put("end", new TokenAlgo(TokenAlgoType.END, "end"));
        tokens.put("return", new TokenAlgo(TokenAlgoType.RETURN, "return"));
        tokens.put("var", new TokenAlgo(TokenAlgoType.VAR, "var"));
        tokens.put("if", new TokenAlgo(TokenAlgoType.IF, "if"));
        tokens.put("then", new TokenAlgo(TokenAlgoType.THEN, "then"));
        tokens.put("else", new TokenAlgo(TokenAlgoType.ELSE, "else"));
        tokens.put("do", new TokenAlgo(TokenAlgoType.DO, "do"));
        tokens.put("while", new TokenAlgo(TokenAlgoType.WHILE, "while"));
        tokens.put("or", new TokenAlgo(TokenAlgoType.OR, "or"));
        tokens.put("and", new TokenAlgo(TokenAlgoType.AND, "and"));
        tokens.put("true", new TokenAlgo(TokenAlgoType.TRUE, "true"));
        tokens.put("false", new TokenAlgo(TokenAlgoType.FALSE, "false"));
    }

    /**
     * Check if the token is in the hash map and return a new token if none
     * exists or return the existing instance.
     * 
     * @param type the token algo type needed to create an instance of token
     * @return the existing token or the created token if none exists
     */
    public static TokenAlgo getToken(TokenAlgoType type, String lexeme) {
        TokenAlgo token = tokens.get(lexeme);
        assert(token.equals(TokenAlgo.NULL) || token.getTokenType() == type);

        if (token.equals(TokenAlgo.NULL)) {
            switch (type) {
            case INT:
                token = new IntegerTokenAlgo(lexeme);
                break;
            case REAL:
                token = new RealTokenAlgo(lexeme);
                break;
            case ID:
            case LPAREN:
            case RPAREN:
            case SEMICOLON:
            case COLON:
            case COMMA:
            case EQ:
            case NEQ:
            case OP:
            case ASSIGN:
                token = new TokenAlgo(type, lexeme);
                break;
            default:
            }
            tokens.put(lexeme, token);
        }
        
        return token;
    }
}
