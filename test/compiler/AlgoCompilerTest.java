/*
 * AlgoCompilerTest.java v0.10 01/09/08
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

package compiler;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

/**
 * Test of the algorithm compiler.
 *
 * @author Damien Rigoni
 * @version 0.10 01/09/08
 */
public class AlgoCompilerTest {

    private static final String ALGO_FILE_NAME = "data" + File.separator + "InsertBT.al";

    private ICompiler compiler;

    private ICompilerFactory factory;

    @Before
    public void setUp() {
        factory = AlgoCompilerFactory.getInstance();
        compiler = factory.newCompiler();
    }

    @Test
    public void testCompileFile() {
    	fail("Not implemented");
//        try {
//            compiler.compile(ALGO_FILE_NAME);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    @Test
    public void testCompileInput() {
    	fail("Not implemented");
    }
}
