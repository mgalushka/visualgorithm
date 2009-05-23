/*
 * SoftwareView.java v1.00 16/06/08
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

package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import model.SoftwareModelEvent;
import model.SoftwareModelEvent.SoftwareModelEventType;
import swing.SoftwareIO.SaveEventType;
import view.ISoftwareView;
import controller.SoftwareController;
import swing.tree.TreeMenu;

/**
 * Definition of the software view.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see ISoftwareView
 */
public class SoftwareView extends JFrame implements ISoftwareView {

    private static final long serialVersionUID = 1L;

    private SoftwareController softwareController;

    private JMenuBar menuBar;

    private JMenu algorithms;

    private SoftwareIO ioOperation;

    private JTabbedPane tabbedPane;

    /**
     * Builds the software view.
     * 
     * @param c the software controller
     */
    public SoftwareView(SoftwareController c) {
        super("Visualgorithm");
        softwareController = c;
        tabbedPane = new JTabbedPane(SwingConstants.TOP,
                JTabbedPane.SCROLL_TAB_LAYOUT);
        ioOperation = new SoftwareIO(this, tabbedPane, softwareController);
        createMenuBar();

        menuBar.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        setJMenuBar(menuBar);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                ioOperation.exitSoftwareOperation();
            }
        });
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 8 / 10, screenSize.height * 8 / 10);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        createAlgorithmsMenu();

        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        addDataStructureMenus(menuBar);
        menuBar.add(algorithms);
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        file.setMnemonic('F');
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
            ActionEvent.CTRL_MASK));
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                ioOperation.openOperation();

            }
        });
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
            ActionEvent.CTRL_MASK));
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                ioOperation.saveOperation(SaveEventType.SAVE);
            }
        });
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
            ActionEvent.CTRL_MASK));
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                ioOperation.exitSoftwareOperation();
            }
        });
        file.add(open);
        file.add(save);
        file.add(exit);
        return file;
    }

    private JMenu createEditMenu() {
        JMenu edit = new JMenu("Edit");
        JMenu language = new JMenu("Language");
        JMenuItem preferences = new JMenuItem("Preferences");

        edit.setMnemonic('E');
        language.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO language
            }
        });
        preferences.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO preferences
            }
        });
        edit.add(language);
        edit.add(preferences);
        return edit;
    }

    protected void addDataStructureMenus(JMenuBar menuBar) {
        JMenu newMenu = new TreeMenu(this, tabbedPane, softwareController);
        menuBar.add(newMenu);
    }

    private void createAlgorithmsMenu() {
        algorithms = new JMenu("Algorithms");
        JMenu apply = new JMenu("Apply");
        JMenu notes = new JMenu("Notes");

        algorithms.setMnemonic('A');
        // TODO apply
        // TODO notes
        algorithms.add(apply);
        algorithms.add(notes);
    }

    private JComponent getTabView(int index) {
        return (JComponent) softwareController.getTabController(index)
                .getView();
    }

    @Override
    public void showView() {
        setVisible(true);
    }

    @Override
    public void hideView() {
        setVisible(false);
        dispose();
    }

    @Override
    public void modelHasChanged(SoftwareModelEvent event) {
        if (event.getEventType() == SoftwareModelEventType.INSERT) {
            int numTab = tabbedPane.getTabCount();
            tabbedPane.addTab(event.getDataStructureModelName(), getTabView(numTab));
            tabbedPane.setTabComponentAt(numTab, new TabCloseButton(tabbedPane,
                    ioOperation));
            tabbedPane.setSelectedIndex(numTab);
        } else if (event.getEventType() == SoftwareModelEventType.CLEAR) {
            System.exit(0);
        } else if (event.getEventType() == SoftwareModelEventType.DELETE) {
            int i = event.getDataStructureModelIndex();
            if (i != -1) {
                tabbedPane.remove(i);
            }
        }
    }
}
