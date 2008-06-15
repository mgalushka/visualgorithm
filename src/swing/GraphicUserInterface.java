package swing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import view.IPrincipalView;
import controller.PrincipalController;


public class GraphicUserInterface implements IPrincipalView {

    private static final long serialVersionUID = 1L;

    private JFrame frame;
    
    private JMenuBar menuBar;
    
    private JTabbedPane tabbedPane;

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
    
    public GraphicUserInterface(PrincipalController controller) {
        frame = new JFrame("Visualgorithm");
        menuBar = createMenuBar(controller);
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        frame.setJMenuBar(menuBar);
        frame.add(tabbedPane);
    }

    private JMenuBar createMenuBar(PrincipalController controller) {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = createMenuFile(controller);
        JMenu trees = createMenuTrees(controller);
        JMenu algorithms = createMenuAlgorithms(controller);
        JMenu tools = createMenuTools(controller);

        menuBar.add(file);
        menuBar.add(trees);
        menuBar.add(algorithms);
        menuBar.add(tools);
        return menuBar;
    }
    
    private JMenu createMenuFile(PrincipalController controller) {
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        
        exit.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        file.add(exit);
        return file;
    }
    
    private JComponent getFastTreeCreationView(PrincipalController controller) {
        return (JComponent)controller.getFastTreeCreationController().getView();
    }
    
    private JMenu createMenuTrees(final PrincipalController controller) {
        JMenu trees = new JMenu("Trees");
        
        //test
        JMenuItem newTree = new JMenuItem("New Tree");
        newTree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.fastCreationTree();
                tabbedPane.addTab("Test", getFastTreeCreationView(controller));
            }
        });
        trees.add(newTree);
        //
        
        return trees;
    }
    
    private JMenu createMenuAlgorithms(PrincipalController controller) {
        JMenu algorithms = new JMenu("Algorithms");
        
        return algorithms;
    }
    
    private JMenu createMenuTools(PrincipalController controller) {
        JMenu tools = new JMenu("Tools");
        
        return tools;
    }
    
    public void displayView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize( screenSize.width * 9/10, screenSize.height * 9/10);
    }
}