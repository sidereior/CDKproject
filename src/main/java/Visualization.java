import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.smiles.SmilesParser;
import javax.swing.*;

import java.awt.Color;

public class Visualization {

    public static void main(String[] args) throws Exception {
        IChemObjectBuilder bldr   = SilentChemObjectBuilder.getInstance();
        SmilesParser smipar = new SmilesParser(bldr);
        IAtomContainer mol = smipar.parseSmiles("CN1C=NC2=C1C(=O)N(C(=O)N2C)C");
        mol.setProperty(CDKConstants.TITLE, "caffeine");
        DepictionGenerator dptgen = new DepictionGenerator();
        dptgen.withSize(200, 250)
                .withMolTitle()
                .withTitleColor(Color.DARK_GRAY);
        dptgen.depict(mol)
                .writeTo("D:\\Users\\alexa/caffeine.png");

    }
}