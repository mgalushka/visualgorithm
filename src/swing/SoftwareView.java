/*
 * SoftwareView.java v0.10 16/06/08
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import model.SoftwareModelEvent;
import model.SoftwareModelEvent.SoftwareModelEventType;
import view.ISoftwareView;
import controller.ISoftwareController;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import swing.tree.BinaryTreeMenu;
import util.ImageLoadingUtility;

/**
 * This class defines the view of the software. The software view is composed by
 * the software controller to modify the software model and a software view IO
 * operation which manages IO operations between users and the software. It also
 * contains a tabbed pane which has tabs representing data structure views. This
 * class is not designed for inheritance. If you would like to add other data
 * structure menus, do not forget to register your data structure menus in the
 * data structure {@code dataStructureMenus} in this class.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 * @see ISoftwareView
 */
public final class SoftwareView extends JFrame implements ISoftwareView {

    private static final long serialVersionUID = 1L;

    private static final double SOFTWARE_VIEW_DISPLAY_RATIO = 0.8;

    private ISoftwareController softwareController;

    private SoftwareViewIOOperation softwareViewIOOperation;

    private JTabbedPane softwareTabbedPane;

    /**
     * The data structure menus contains all the data structure menus of the software.
     */
    private static List<IDataStructureMenu> dataStructureMenus =
            new ArrayList<IDataStructureMenu>();

    /**
     * Registration of the data structure menus according to the types of data
     * structures. You have to register new data structure menus here.
     */
    static {
        dataStructureMenus.add(new BinaryTreeMenu());
    }

    /**
     * Builds the software view. The software view is composed by the software
     * controller and a tabbed pane. It also contains a software view IO
     * operation.
     * 
     * @param c the software controller
     */
    public SoftwareView(ISoftwareController c) {
        super("Visualgorithm");
        
        softwareController = c;
        softwareTabbedPane = new JTabbedPane(SwingConstants.TOP,
                JTabbedPane.SCROLL_TAB_LAYOUT);
        softwareViewIOOperation = new SoftwareViewIOOperation(softwareController);
        
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                if (softwareViewIOOperation.exitSoftwareOperation(
                        softwareTabbedPane.getTabCount())) {
                    closeView();
                }
            }
        });
        setJMenuBar(createMenuBar());
        getContentPane().add(softwareTabbedPane, BorderLayout.CENTER);
        
        shapeSoftwareViewFrame();
    }

    private void shapeSoftwareViewFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int softwareViewWidth = (int) (screenWidth * SOFTWARE_VIEW_DISPLAY_RATIO);
        int softwareViewHeight = (int) (screenHeight * SOFTWARE_VIEW_DISPLAY_RATIO);

        setSize(softwareViewWidth, softwareViewHeight);
        setLocation((screenWidth - softwareViewWidth) / 2,
                (screenHeight - softwareViewHeight) / 2);
    }

    private JMenuBar createMenuBar() {
        JMenuBar softwareMenuBar = new JMenuBar();

        softwareMenuBar.add(createFileMenu());
        for (IDataStructureMenu dataStructureMenu: dataStructureMenus) {
            dataStructureMenu.initializeDataStructureMenu(softwareController);
            softwareMenuBar.add((JMenu) dataStructureMenu);
        }
        softwareMenuBar.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

        return softwareMenuBar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        fileMenu.setMnemonic('F');
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        openMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                softwareViewIOOperation.openFileOperation();
            }
        });
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        saveMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                int selectedIndex = softwareTabbedPane.getSelectedIndex();

                if (selectedIndex >= 0) {
                    String fileName =
                            softwareViewIOOperation.saveDataStructureOperation(selectedIndex);
                    if (!fileName.isEmpty()) {
                        try {
                            softwareTabbedPane.setTitleAt(selectedIndex, fileName);
                            ((JComponent) softwareTabbedPane.getTabComponentAt(
                                    selectedIndex)).revalidate();
                        } catch (IndexOutOfBoundsException ex) {
                            softwareViewIOOperation.showErrorMessage("An irrecoverable" +
                                    " error occurs and the software is about\nto shut" +
                                    " down. Sorry for the inconvenience.", "Software Error");
                            System.exit(1);
                        }
                    }
                }
            }
        });
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.indexOf("mac") >= 0) {
            try {
                Class macApplicationClass = Class.forName("com.apple.eawt.Application");
                Object macApplication = macApplicationClass.newInstance();

                Method setDockIconImage = macApplicationClass.getMethod("setDockIconImage",
                        Image.class);
                setDockIconImage.invoke(macApplication,
                        ImageLoadingUtility.loadImageFromVisualgorithmJar("img/icon.png"));

                Class applicationListenerClass = Class.forName("com.apple.eawt.ApplicationListener");
                Object macListener = Proxy.newProxyInstance(getClass().getClassLoader(),
                        new Class[] {applicationListenerClass}, new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) {
                        if (method.getName().equals("handleQuit")) {
                            if (softwareViewIOOperation.exitSoftwareOperation(
                                    softwareTabbedPane.getTabCount())) {
                                closeView();
                            }
                        }
                        return null;
                    }
                });
                Method addApplicationListener = macApplicationClass.getMethod("addApplicationListener",
                        applicationListenerClass);
                addApplicationListener.invoke(macApplication, macListener);
            } catch (Exception ex) {
                softwareViewIOOperation.showErrorMessage("An irrecoverable error" +
                        " occurs and the software is about\nto shut down. Sorry" +
                        " for the inconvenience.", "Software Error");
                System.exit(1);
            }
        } else {
            JMenuItem exitMenuItem = new JMenuItem("Exit");

            exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            exitMenuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {
                    if (softwareViewIOOperation.exitSoftwareOperation(
                            softwareTabbedPane.getTabCount())) {
                        closeView();
                    }
                }
            });
            fileMenu.add(exitMenuItem);
        }
        
        return fileMenu;
    }

    @Override
    public void displayView() {
        setVisible(true);
    }

    @Override
    public void closeView() {
        setVisible(false);
        dispose();
    }

    @Override
    public void modelHasChanged(SoftwareModelEvent event) {
        if (event.getEventType() == SoftwareModelEventType.INSERT) {
            int tabCount = softwareTabbedPane.getTabCount();

            try {
                softwareTabbedPane.addTab(event.getDataStructureModelName(),
                        (JComponent) softwareController.getDataStructureController(
                        tabCount).getView());
                softwareTabbedPane.setTabComponentAt(tabCount, new TabCloseButton(
                        softwareTabbedPane, softwareViewIOOperation));
                softwareTabbedPane.setSelectedIndex(tabCount);
            } catch (IndexOutOfBoundsException ex) {
                softwareViewIOOperation.showErrorMessage("An irrecoverable error" +
                        " occurs and the software is about\nto shut down. Sorry" +
                        " for the inconvenience.", "Software Error");
                System.exit(1);
            }
        } else if (event.getEventType() == SoftwareModelEventType.CLEAR) {
            System.exit(0);
        } else if (event.getEventType() == SoftwareModelEventType.DELETE) {
            int index = event.getDataStructureModelIndex();
            
            try {
                softwareTabbedPane.remove(index);
            } catch (IndexOutOfBoundsException ex) {
                softwareViewIOOperation.showErrorMessage("An irrecoverable error" +
                        " occurs and the software is about\nto shut down. Sorry" +
                        " for the inconvenience.", "Software Error");
                System.exit(1);
            }
        }
    }
}
