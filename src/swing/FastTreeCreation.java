package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.IFastTreeCreationView;
import model.Model;


public class FastTreeCreation extends JPanel implements IFastTreeCreationView {

    private static final long serialVersionUID = 1L;
    
    private JPanel controls;
    
    //private PhysicModel pm
    
    public FastTreeCreation(Model model){   
        controls = createControls();        
        setLayout(new BorderLayout());
        add(controls, BorderLayout.SOUTH);
        //add(pm,BorderLayout.CENTER);
    }
    
    private JPanel createControls() {
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2, 2, 4, 4));
        JButton insert = new JButton("Insert");
        JButton delete = new JButton("Delete");
        JTextField insertvalue = new JTextField();
        JTextField deletevalue = new JTextField();
        controls.add(insertvalue);
        controls.add(insert);
        controls.add(deletevalue);
        controls.add(delete);
        return controls;
    }
}