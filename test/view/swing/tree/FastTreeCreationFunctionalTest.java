/*
 * FastTreeCreationFunctionalTest.java v0.10 20/04/10
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

package view.swing.tree;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.operators.JFrameOperator;

/**
 * Functional test of the fast tree creation panel.
 *
 * @author Julien Hannier
 * @version 0.10 20/04/10
 */
public class FastTreeCreationFunctionalTest implements Scenario {

    @Override
    public int runIt(Object param) {
        try {
            new ClassReference("main.Visualgorithm").startApplication();
            JFrameOperator mainFrame = new JFrameOperator("Visualgorithm");

            // TODO Test of the fast tree creation panel
        } catch (Exception ex) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] params = { "swing.tree.FastTreeCreationFunctionalTest" };

        org.netbeans.jemmy.Test.main(params);
    }
}
