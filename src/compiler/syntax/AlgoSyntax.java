/*
 * AlgoSyntax.java v1.00 31/08/08
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

package compiler.syntax;

import compiler.AlgoSymbolTable;
import compiler.lexical.AlgoLexer;
import compiler.lexical.TokenAlgo;
import compiler.lexical.TokenAlgo.TokenAlgoType;
import java.text.ParseException;

/**
 * Parse and create an AST.
 *
 * @author Damien Rigoni
 * @version 1.00 31/08/08
 */
public class AlgoSyntax {

    private AlgoLexer lexer;

    private AlgoSymbolTable symbolTable;

    private TokenAlgo currentToken;

    private AlgoNode tree;

    @Deprecated
    public AlgoSyntax(AlgoLexer lex) {
        lexer = lex;
    }

    public void parse(String algoFileName) throws Exception {
        lexer.setAlgo(algoFileName);
        program();
    }

    private void nextToken() throws Exception {
        currentToken = lexer.nextToken();
    }

    private void program() throws Exception {
        match(TokenAlgoType.ALGORITHM);
        nextToken();
        match(TokenAlgoType.ID);
        IdAlgoNode idNode = new IdAlgoNode(currentToken);

        nextToken();
        match(TokenAlgoType.LPAREN);

        nextToken();
        ParameterAlgoNode params = parameter();

        nextToken();
        match(TokenAlgoType.RPAREN);

        nextToken();
        match(TokenAlgoType.RETURN);

        nextToken();
        TypeAlgoNode returnType = returnType();
        createNode(currentToken);

        nextToken();
        match(TokenAlgoType.VAR);

        nextToken();
        VariableAlgoNode vars = variable_opt();
        nextToken();

        match(TokenAlgoType.BEGIN);
        createNode(currentToken);
        nextToken();

        statements();
        createNode(currentToken);
        nextToken();

        match(TokenAlgoType.END);
        createNode(currentToken);
    }

    private void createNode(TokenAlgo token) {
    }

    private void match(TokenAlgoType tokenAlgoType) throws Exception {
        if (!currentToken.equals(tokenAlgoType)) {
            throw new ParseException("error:" + lexer.getRowNumber() + ", "
                    + lexer.getColumnNumber() + ": excepted " + tokenAlgoType
                    + " but encoutered " + currentToken.toString(), lexer.getRowNumber());
        }
    }

    private boolean matchToken(TokenAlgoType tokenAlgoType) {
        return currentToken.equals(tokenAlgoType);
    }

    private ParameterAlgoNode parameter() throws Exception {
        nextToken();
        match(TokenAlgoType.COLON);
        nextToken();
        nextToken();
        TokenAlgo type = null;
        symbolTable.addSymbol(id(), type);

        try {
            match(TokenAlgoType.COMMA);
            parameter();
        } catch (ParseException e) {
            // No other parameter
        }
        return null;
    }

    private void statements() throws Exception {
        switch (currentToken.getTokenType()) {
        case ID:
            id();
            switch (currentToken.getTokenType()) {

            }
        case IF:
        case WHILE:
        case DO:
        case BREAK:
        }

        try {
            id();
        } catch (Exception ex) {

        }
    }

    private VariableAlgoNode variable_opt() throws Exception {
        id();
        match(TokenAlgoType.COLON);
        returnType();
        match(TokenAlgoType.SEMICOLON);

        return null;
    }

    private TypeAlgoNode returnType() throws Exception {
        match(TokenAlgoType.TYPE);

        return null;
    }

    private TokenAlgo id() throws Exception {
        match(TokenAlgoType.ID);
        
        return currentToken;
    }
}
