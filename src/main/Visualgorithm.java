/*
 * Visualgorithm.java v1.00 16/06/08
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

package main;

import javax.swing.SwingUtilities;
import swing.SwingViewFactory;
import view.AbstractViewFactory;
import controller.SoftwareController;
import model.ISoftwareModel;
import model.SoftwareModel;

/**
 * This class is the main class of the software. It only contains the main
 * method that initializes and launches the software. It is not designed for
 * inheritance.
 * 
 * @author Julien Hannier
 * @version 1.00 16/06/08
 */
public final class Visualgorithm {

    /**
     * This is the main method of the software that creates the model and the
     * controller with the wanted view factory. The string array {@code args} is
     * not used.
     * 
     * @param args possible arguments
     */
    public static void main(String[] args) {
        final ISoftwareModel model = new SoftwareModel();
        final AbstractViewFactory viewFactory = SwingViewFactory.getFactory();

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.indexOf("mac") >= 0) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Visualgorithm");
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new SoftwareController(model, viewFactory);
            }
        });
    }
}
