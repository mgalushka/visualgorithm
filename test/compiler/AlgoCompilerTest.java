
package compiler;

import org.junit.BeforeClass;
import org.junit.Test;
import compiler.AlgoCompilerFactory;
import compiler.ICompiler;
import compiler.ICompilerFactory;

/**
 * Test of the AlgoCompiler.
 * 
 */
public class AlgoCompilerTest {

    private static final String algoFileName = "./algo/test.al";

    private static ICompiler compiler;

    private static ICompilerFactory factory;

    @BeforeClass
    public static void setUp() {
        factory = AlgoCompilerFactory.getInstance();
        compiler = factory.newCompiler();
    }

    @Test
    public void testCompileFile() {
        try {
            compiler.compile(algoFileName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();                                
        }
    }

    @Test
    public void testCompileInput() {
        // TODO Test compile input
    }
}
