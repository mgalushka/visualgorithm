/*
 * TokenTest.java 1 sept. 2008
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

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import compiler.lexical.IntegerTokenAlgo;
import compiler.lexical.TokenAlgo;
import compiler.lexical.TokenAlgo.TokenAlgoType;

/**
 * @author Damien R.
 * 
 */
public class TokenTest {

    private TokenAlgo tokenId1, tokenId2, tokenId3, tokenId4;

    private TokenAlgo tokenDo;

    private TokenAlgo tokenIdWeak;

    private TokenAlgo tokenInt1, tokenInt2;

    @BeforeClass
    public void setUp() {
        tokenId1 = new TokenAlgo(TokenAlgoType.ID, "tata");
        tokenId2 = new TokenAlgo(TokenAlgoType.ID, "toto");
        tokenId3 = new TokenAlgo(TokenAlgoType.ID, "tata");
        tokenId4 = new TokenAlgo(TokenAlgoType.ID, "tata");

        tokenDo = new TokenAlgo(TokenAlgoType.DO, "do");
        tokenIdWeak = new TokenAlgo(TokenAlgoType.ID, "do");

        tokenInt1 = new IntegerTokenAlgo("2");
        tokenInt2 = new IntegerTokenAlgo("2");
    }

    @Test
    public void equalsTest() {
        // reflexivity
        assertTrue(tokenId1.equals(tokenId1));

        // symmetry
        assertTrue(tokenId3.equals(tokenId1));
        assertEquals(tokenId1.equals(tokenId3), tokenId3
                .equals(tokenId1));

        // transitivity
        assertEquals(tokenId1.equals(tokenId3), tokenId3
                .equals(tokenId4));
        assertTrue(tokenId1.equals(tokenId4));

        assertFalse(tokenId1.equals(tokenId2));
        assertFalse(tokenId2.equals(tokenId1));
        assertFalse(tokenId3.equals(tokenId2));
        assertFalse(tokenId1.equals(tokenDo));
        assertFalse(tokenDo.equals(tokenIdWeak));
        assertFalse(tokenIdWeak.equals(tokenDo));
        assertFalse(tokenId1.equals(tokenInt1));
        assertTrue(tokenInt1.equals(tokenInt2));
    }

    @Test
    public void hasCodeTest() {
        assertEquals(tokenId1.hashCode(), tokenId1.hashCode());
        assertEquals(tokenId1.hashCode(), tokenId3.hashCode());
        assertFalse(tokenId1.hashCode() == tokenId2.hashCode());
    }
}
