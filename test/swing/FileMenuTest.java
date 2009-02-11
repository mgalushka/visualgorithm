/*
 * FileMenuTest.java v1.00 19/01/09
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

import java.io.File;
import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFileChooserOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

/**
 * Functional test of the file menu of the graphic user interface. This test
 * uses the Jemmy library. The tested actions are Open, Save and Exit.
 * 
 * @author Julien Hannier
 * @version 1.00 19/01/09
 */
public class FileMenuTest implements Scenario {

    @Override
    public int runIt(Object param) {
        try {
            new ClassReference("main.Visualgorithm").startApplication();
            JFrameOperator mainFrame = new JFrameOperator("Visualgorithm");

            new JMenuBarOperator(mainFrame).pushMenuNoBlock("File|Open", "|");
            JFileChooserOperator openChooser1 = new JFileChooserOperator(
                    mainFrame);
            openChooser1.setCurrentDirectory(new File("."));
            openChooser1.clickOnFile("BigBST.bt", 2);

            new JMenuBarOperator(mainFrame).pushMenuNoBlock("File|Open", "|");
            JFileChooserOperator openChooser2 = new JFileChooserOperator(
                    mainFrame);
            openChooser2.setCurrentDirectory(new File("."));
            new JTextFieldOperator(openChooser2).setText("BigAVLT.bt");
            new JButtonOperator(openChooser2, "Cancel").push();

            new JMenuBarOperator(mainFrame).pushMenuNoBlock("File|Open", "|");
            JFileChooserOperator openChooser3 = new JFileChooserOperator(
                    mainFrame);
            openChooser3.setCurrentDirectory(new File("."));
            new JTextFieldOperator(openChooser3).setText("BigRBT.bt");
            new JButtonOperator(openChooser3, "Open").push();

            new JMenuBarOperator(mainFrame).pushMenuNoBlock("File|Exit", "|");
            JDialogOperator closeDialog = new JDialogOperator(mainFrame,
                    "Exit Operation");
            new JButtonOperator(closeDialog, "No").push();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] params = { "swing.FileMenuTest" };
        org.netbeans.jemmy.Test.main(params);
    }
}
