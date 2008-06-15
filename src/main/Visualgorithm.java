package main;

import javax.swing.SwingUtilities;
import controller.PrincipalController;
import model.Model;


public class Visualgorithm {

    public static void main(String[] args) {
        final Model model = new Model();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PrincipalController(model);
            }
        });
    }
}