/*
 * PedagogicalBinaryTreeCreationPanel.java v0.10 07/07/08
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

package swing.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import controller.IBinaryTreeController;

/**
 * This class defines the pedagogical binary tree creation panel. It is composed
 * by a binary tree visualization, an insert text field and an insert button, a
 * delete text field and a delete button. All of these are defined in the
 * abstract class <tt>AbstractBinaryTreeCreationPanel</tt>. There is also two
 * other buttons to display a tip or a solution to the insertion or the
 * deletion action. This kind of information is displayed in a text area. This
 * class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 0.10 07/07/08
 * @see AbstractBinaryTreeCreationPanel
 */
final class PedagogicalBinaryTreeCreationPanel extends AbstractBinaryTreeCreationPanel {

    private static final long serialVersionUID = 1L;

    private JTextArea informationArea;

    /**
     * Builds the pedagogical binary tree creation panel and places the
     * components of the abstract class on the panel. It also contains two other
     * buttons to display a tip or a solution that are displayed in a text area.
     * 
     * @param c the binary tree controller
     */
    PedagogicalBinaryTreeCreationPanel(IBinaryTreeController c) {
        super(c);
        binaryTreeVisualization = new PedagogicalBinaryTreeVisualization(binaryTreeController);
        informationArea = createInformationArea();
        
        setLayout(new BorderLayout(4, 4));
        add(binaryTreeVisualization.buildVisualizationPanelWithZoom(), BorderLayout.CENTER);
        add(composeControlsAndInformationArea(), BorderLayout.SOUTH);
    }

    private JTextArea createInformationArea() {
        JTextArea textArea = new JTextArea("Panel not implemented yet!", 2, 1);

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return textArea;
    }

    private JPanel createControls() {
        JPanel controls = new JPanel();
        JButton tipButton = new JButton("Help");
        JButton solutionButton = new JButton("Solution");

        tipButton.setToolTipText("A Tip For The Action");
        tipButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Display a tip
            }
        });
        solutionButton.setToolTipText("The Solution Of The Action");
        solutionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Display the solution
            }
        });
        controls.setLayout(new GridLayout(3, 2, 4, 4));
        controls.add(tipButton);
        controls.add(solutionButton);
        controls.add(insertNodeText);
        controls.add(insertNodeButton);
        controls.add(deleteNodeText);
        controls.add(deleteNodeButton);
        
        return controls;
    }

    private JPanel composeControlsAndInformationArea() {
        JPanel components = new JPanel();

        components.setLayout(new BoxLayout(components, BoxLayout.PAGE_AXIS));
        components.add(informationArea);
        components.add(Box.createRigidArea(new Dimension(0, 4)));
        components.add(createControls());

        return components;
    }

    @Override
    protected void insertNodeActionWithText(String nodeValue) {
        // TODO Insertion
    }

    @Override
    protected void deleteNodeActionWithText(String nodeValue) {
        // TODO Deletion
    }

    @Override
    protected void insertNodeActionWithButtonOnly() {
    }

    @Override
    protected void deleteNodeActionWithButtonOnly() {
    }
}
