/*
 * Visualgorithm.java v1.00 16/06/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (bx1gl@googlegroups.com)
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

package main;

import javax.swing.SwingUtilities;
import controller.PrincipalController;
import model.PrincipalModel;

/**
 * Main class of the software.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class Visualgorithm {

    /**
     * Launchs the software.
     * 
     * @param args possible arguments
     */
    public static void main(String[] args) {
        final PrincipalModel model = new PrincipalModel();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PrincipalController(model);
            }
        });
    }
}