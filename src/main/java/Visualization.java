import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.smiles.SmilesParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import java.awt.Color;

public class Visualization {

    public static void main(String[] args) throws Exception {
        //Simple example visualization of a manually entered compound
        IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();
        SmilesParser smipar = new SmilesParser(bldr);
        IAtomContainer mol = smipar.parseSmiles("Fc1ccc(cc1)[C@@]3(OCc2cc(C#N)ccc23)CCCN(C)C");
        mol.setProperty(CDKConstants.TITLE, "C#C#C#C#N=N=N");
        DepictionGenerator dptgen = new DepictionGenerator();
        dptgen.withSize(200, 250).withMolTitle().withTitleColor(Color.DARK_GRAY);
        BufferedImage visual = dptgen.depict(mol).toImg();
        File outputfile = new File("CN1C=NC2=C1C(=O)N(C(=O)N2C)C" + ".png");
        ImageIO.write(visual, "png", outputfile);
    }
}