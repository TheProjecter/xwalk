package structure.matter;

import java.io.IOException;
import java.util.Hashtable;

import structure.constants.Constants;
import structure.io.pdb.PDBwriter;
import structure.math.Point3d;
import structure.matter.parameter.Element;
import structure.matter.parameter.ParameterReader;


/**
 * Class handling all atom fields of a standard PDB file.
 * Functions are provided to set and get every entry individually.
 * The function toString returns the entry of one atom correctly
 * formatted for output. PDB reading and writing is handled in PDBreader and
 * PDBwriter. The following lines give an overview of the PDB format for ATOM
 * and HETATM entries.
 * ATOM
 *
 * Overview
 *
 * The ATOM records present the atomic coordinates for standard residues. They
 * also present the occupancy and temperature factor for each atom. Heterogen
 * coordinates use the HETATM record type. The element symbol is always present
 * on each ATOM record; segment identifier and charge are optional.
 *
 * Record Format
 *
 * COLUMNS        DATA TYPE       FIELD         DEFINITION
 * ----------------------------------------------------------------------------
 * 1 -  6        Record name     "ATOM"
 *
 * 7 - 11        Integer         serial        AtomRadius serial number.
 *
 * 13 - 16       AtomName      name          AtomName name.
 *
 * 17            Character       proteinAltLoc    Alternate location indicator.
 *
 * 18 - 20       Residue name    resName       Residue name.
 *
 * 22            Character       chainID       Chain identifier.
 *
 * 23 - 26       Integer         resSeq        Residue sequence number.
 *
 * 27            AChar           iCode         Code for insertion of residues.
 *
 * 31 - 38       Real(8.3)       x             Orthogonal coordinates for X in
 * Angstroms.
 *
 * 39 - 46        Real(8.3)       y             Orthogonal coordinates for Y in
 * Angstroms.
 *
 * 47 - 54        Real(8.3)       z             Orthogonal coordinates for Z in
 * Angstroms.
 *
 * 55 - 60        Real(6.2)       occupancy     Occupancy.
 *
 * 61 - 66        Real(6.2)       tempFactor    Temperature factor.
 *
 * 73 - 76        LString(4)      segID      Segment identifier, left-justified.
 *
 * 77 - 78        LString(2)      element       Element symbol, right-justified.
 *
 * 79 - 80        LString(2)      charge        Charge on the atom.
 *
 *
 * Example
 *
 * 1         2         3         4         5         6         7         8
* 123456789012345678901234567890123456789012345678901234567890123456789012345678
* ATOM    145  N   VAL A  25      32.433  16.336  57.540  1.00 11.92      A1   N
* ATOM    146  CA  VAL A  25      31.132  16.439  58.160  1.00 11.85      A1   C
* ATOM    147  C   VAL A  25      30.447  15.105  58.363  1.00 12.34      A1   C
* ATOM    148  O   VAL A  25      29.520  15.059  59.174  1.00 15.65      A1   O
* ATOM    149  CB AVAL A  25      30.385  17.437  57.230  0.28 13.88      A1   C
* ATOM    150  CB BVAL A  25      30.166  17.399  57.373  0.72 15.41      A1   C
* ATOM    151  CG1AVAL A  25      28.870  17.401  57.336  0.28 12.64      A1   C
* ATOM    152  CG1BVAL A  25      30.805  18.788  57.449  0.72 15.11      A1   C
* ATOM    153  CG2AVAL A  25      30.835  18.826  57.661  0.28 13.58      A1   C
* ATOM    154  CG2BVAL A  25      29.909  16.996  55.922  0.72 13.25      A1   C
 *
 *
 * HETATM
 *
 * Overview
 *
 * The HETATM records present the atomic coordinate records for atoms within
 * "non-standard" groups. These records are used for water molecules and atoms
 * presented in HET groups.
 *
 * Record Format
 *
 * COLUMNS        DATA TYPE       FIELD          DEFINITION
 * ----------------------------------------------------------------------------
 * 1 -  6        Record name     "HETATM"
 *
 * 7 - 11        Integer         serial         AtomRadius serial number.
 *
 * 13 - 16        AtomRadius            name           AtomRadius name.
 *
 * 17             Character       proteinAltLo     Alternate location indicator.
 *
 * 18 - 20        Residue name    resName        Residue name.
 *
 * 22             Character       chainID        Chain identifier.
 *
 * 23 - 26        Integer         resSeq         Residue sequence number.
 *
 * 27             AChar           iCode          Code for insertion of residues.
 *
 * 31 - 38        Real(8.3)       x              Orthogonal coordinates for X.
 *
 * 39 - 46        Real(8.3)       y              Orthogonal coordinates for Y.
 *
 * 47 - 54        Real(8.3)       z              Orthogonal coordinates for Z.
 *
 * 55 - 60        Real(6.2)       occupancy      Occupancy.
 *
 * 61 - 66        Real(6.2)       tempFactor     Temperature factor.
 *
 * 73 - 76        LString(4)      segID          Segment identifier;
 * left-justified.
 *
 * 77 - 78        LString(2)      element       Element symbol; right-justified.
 *
 * 79 - 80        LString(2)      charge         Charge on the atom.
 *
 *
 * Amino Acids
 *
 * RESIDUE                     ABBREVIATION                SYNONYM
 * -----------------------------------------------------------------------------
 * Alanine                     ALA                         A
 * Arginine                    ARG                         R
 * Asparagine                  ASN                         N
 * Aspartic acid               ASP                         D
 * ASP/ASN ambiguous           ASX                         B
 * Cysteine                    CYS                         C
 * Glutamine                   GLN                         Q
 * Glutamic acid               GLU                         E
 * GLU/GLN ambiguous           GLX                         Z
 * Glycine                     GLY                         G
 * Histidine                   HIS                         H
 * Isoleucine                  ILE                         I
 * Leucine                     LEU                         L
 * Lysine                      LYS                         K
 * Methionine                  MET                         M
 * Phenylalanine               PHE                         F
 * Proline                     PRO                         P
 * Serine                      SER                         S
 * Threonine                   THR                         T
 * Tryptophan                  TRP                         W
 * Tyrosine                    TYR                         Y
 * Unknown                     UNK
 * Valine                      VAL                         V
 *
 *
 * Amino Acids
 *
 * NAME                    CODE           FORMULA                 MOL. WT.
 * -----------------------------------------------------------------------------
 * Alanine                 ALA            C3 H7 N1 O2             89.09
 * Arginine                ARG            C6 H14 N4 O2            174.20
 * Asparagine              ASN            C4 H8 N2 O3             132.12
 * Aspartic acid           ASP            C4 H7 N1 O4             133.10
 * ASP/ASN ambiguous       ASX            C4 H71/2 N11/2 O31/2    132.61
 * Cysteine                CYS            C3 H7 N1 O2 S1          121.15
 * Glutamine               GLN            C5 H10 N2 O3            146.15
 * Glutamic acid           GLU            C5 H9 N1 O4             147.13
 * GLU/GLN ambiguous       GLX            C5 H91/2 N11/2 O31/2    146.64
 * Glycine                 GLY            C2 H5 N1 O2             75.07
 * Histidine               HIS            C6 H9 N3 O2             155.16
 * Isoleucine              ILE            C6 H13 N1 O2            131.17
 * Leucine                 LEU            C6 H13 N1 O2            131.17
 * Lysine                  LYS            C6 H14 N2 O2            146.19
 * Methionine              MET            C5 H11 N1 O2 S1         149.21
 * Phenylalanine           PHE            C9 H11 N1 O2            165.19
 * Proline                 PRO            C5 H9 N1 O2             115.13
 * Serine                  SER            C3 H7 N1 O3             105.09
 * Threonine               THR            C4 H9 N1 O3             119.12
 * Tryptophan              TRP            C11 H12 N2 O2           204.23
 * Tyrosine                TYR            C9 H11 N1 O3            181.19
 * Valine                  VAL            C5 H11 N1 O2            117.15
 * Undetermined            UNK            C5 H6 N1 O3             128.16
 *
 *
 * SOURCE: ftp://ftp.rcsb.org/pub/pdb/doc/format_descriptions/
 *               Contents_Guide_21.txt
 *
 * COLUMNS DATA TYPE FIELD DEFINITION
 * -----------------------------------------------------------------------------
 * 1 - 6 Record name "CONECT"
 * 7 - 11 Integer serial AtomRadius serial number
 * 12 - 16 Integer serial Serial number of bonded atom
 * 17 - 21 Integer serial Serial number of bonded atom
 * 22 - 26 Integer serial Serial number of bonded atom
 * 27 - 31 Integer serial Serial number of bonded atom
 * 32 - 36 Integer serial Serial number of hydrogen bonded atom
 * 37 - 41 Integer serial Serial number of hydrogen bonded atom
 * 42 - 46 Integer serial Serial number of salt bridged atom
 * 47 - 51 Integer serial Serial number of hydrogen bonded atom
 * 52 - 56 Integer serial Serial number of hydrogen bonded atom
 * 57 - 61 Integer serial Serial number of salt bridge atom
 *
 * @author Abdullah Kahraman
 * @version 3.0
 * @since 3.0
 */
public class Atom {
    //--------------------------------------------------------------------------
    // PDB related data field---------------------------------------------------
    //--------------------------------------------------------------------------
    /**
     * column 0-5.
     */
    private String flag;
    /**
     * column 6-10.
     */
    private int serialNumber;
    /**
     * column 12-15.
     */
    private String name;
    /**
     * column 16.
     */
    private char alternativeLocation;
    /**
     * column 17-19.
     */
    private String residueName;
    /**
     * column 21.
     */
    private char chainId;
    /**
     * column 22-25.
     */
    private int residueNumber;
    /**
     * column 26.
     */
    private char iCode;
    /**
     * column 30-37, 38-45, 46-53.
     */
    private Point3d xyz;
    /**
     * column 54-59.
     */
    private double occupancy;
    /**
     * column 60-65.
     */
    private double temperaturFactor;
    /**
     * column 76-77.
     */
    private Element element;
    /**
     * column 78-79.
     */
    private int chargeState;
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    /**
     * Rank of the amino acid within the PDB sequence.
     */
    private int rank;
    /**
     * van der Waals radius of atom.
     */
    private double vanDerWaalsRadius;
    /**
     * atomic weight of atom.
     */
    private double weight;
    /**
     * aromatic state of atom.
     */
    private boolean isAromatic;
    /**
     * metal state of atom.
     */
    private boolean isMetalic;

    //--------------------------------------------------------------------------
    /**
     * Constructor sets all the fields to defaults (mainly 0 and "").
     */
    public Atom() {
        this.flag = "ATOM  ";
        this.serialNumber = 0;
        this.name = "";
        this.alternativeLocation = ' ';
        this.residueName = "";
        this.chainId = ' ';
        this.residueNumber = 0;
        this.iCode = ' ';
        this.xyz = new Point3d(0.0, 0.0, 0.0);
        this.occupancy = 0.0;
        this.temperaturFactor = 0.0;
        this.element = Element.CARBON;
        this.chargeState = 0;
        this.rank = 0;
        this.vanDerWaalsRadius = Constants.DEFAULT_ATOM_RADIUS;
        this.weight = 1;
        this.isAromatic = false;
        this.isMetalic = false;
    }
    //--------------------------------------------------------------------------
    /**
     * Creates a copy of this AtomRadius object.
     * @return Atom object being a copy of this atom object.
     */
    public final Atom copy() {
        Atom atom = new Atom();
        atom.setFlag(new String(this.getFlag()));
        atom.setSerialNumber((new Integer(this.getSerialNumber())).intValue());
        atom.setName(new String(this.getName()));
        atom.setAlternativeLocation(new Character(
                                      this.getAlternativeLocation()).charValue()
                                   );
        atom.setResidueName(new String(this.getResidueName()));
        atom.setChainId(new Character(this.getChainId()).charValue());
        atom.setResidueNumber(
                             (new Integer(this.getResidueNumber())).intValue()
                             );
        atom.setICode(new Character(this.getICode()).charValue());
        atom.setPoint3d(new Point3d(this.xyz.getX(),
                                    this.xyz.getY(),
                                    this.xyz.getZ()
                                   )
                       );
        atom.setOccupancy((new Double(this.getOccupancy())).doubleValue());
        atom.setTemperatureFactor(
                         (new Double(this.getTemperatureFactor())).doubleValue()
                                 );
        atom.setElement(this.getElement());
        atom.setChargeState((new Integer(this.getChargeState())).intValue());
        atom.setRank(new Integer(this.getRank()).intValue());
        atom.setVanDerWaalsRadius(
                           new Double(this.getVanDerWaalsRadius()).doubleValue()
                                 );
        atom.setWeight(new Double(this.getWeight()).doubleValue());
    return atom;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the flag of the atom.
     * @param  flagName
     *         - String object, which must be either "ATOM" or "HETATM".
     * @return {@code TRUE} if flag correspond to ATOM or HETATM, {@code FALSE}
     *         otherwise.
     * @see #getFlag()
     */
    public final boolean setFlag(final String flagName) {
        if (!flagName.equals("ATOM  ") && !flagName.equals("HETATM")) {
            System.err.println("Warning PDB flag \"" + flagName + "\" neither "
                             + "ATOM nor HETATM!");
            return false;
        } else {
            this.flag = flagName;
        }
    return true;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the flag of the atom.
     * @return String object holding the atom flag.
     * @see #setFlag(String)
     */
    public final String getFlag() {
        return flag;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the serialNumber number of an atom.
     * @param  serialNo
     *            - integer between -9999 and 99999
     * @return {@code TRUE} if serialNumber is within limits, {@code FALSE}
     *         otherwise.
     * @see #getSerialNumber()
     */
    public final boolean setSerialNumber(final int serialNo) {
        if ((serialNo > Constants.MAX_SERIAL)) {
            System.err.println("WARNING PDB serialNumber \"" + serialNo
                              + "\" number out of range!");
            return false;
        }
        this.serialNumber = serialNo;
    return true;
    }
    //--------------------------------------------------------------------------
    /**
     * Get the serialNumber number of an atom.
     * @return integer value representing the serialNumber number.
     * @see #setSerialNumber(int)
     */
    public final int getSerialNumber() {
        return serialNumber;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the name of the atom.
     * @param name
     *        - String object holding the name of the atom. If the name is
     *          longer than 4 characters, only the first four characters will be
     *          used.
     * @return {@code TRUE} if the atom name is within limits, {@code FALSE}
     *         otherwise.
     * @see #getName()
     */
    public final boolean setName(final String name) {
        if (name.length() > 4) {
            this.name = name.substring(0, 4);
            return false;
        } else {
            this.name = name;
        }
    return true;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the name of the atom.
     * @return String object holding the name of the atom.
     * @see #setName(String)
     */
    public final String getName() {
        return name;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the alternative location ID of the atom.
     * @param alternativeLocation
     *           - character representing the alternative location ID.
     * @see #getAlternativeLocation()
     */
    public final void setAlternativeLocation(final char alternativeLocation) {
        this.alternativeLocation = alternativeLocation;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the alternative location ID of the atom.
     * @return character representing the alternative location ID.
     * @see #setAlternativeLocation(char)
     */
    public final char getAlternativeLocation() {
        return this.alternativeLocation;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the residue name of the atom.
     * @param residueName
     *        - String object holding the name of the residue type to which the
     *          atom belongs.
     *          If the name is longer than 3 characters, only the first three
     *          characters will be used.
     * @return {@code TRUE} if the residue name is within limits, {@code FALSE}
     *          otherwise.
     * @see #getResidueName()
     */
    public final boolean setResidueName(final String residueName) {
        if (residueName.length() > 3) {
            this.residueName = residueName.substring(0, 3);
            return false;
        }
        else { this.residueName = residueName;
        }
    return true;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the name of the residue to which the atom belongs.
     * @return String object holding the name of the residue.
     * @see #setResidueName(String)
     */
    public final String getResidueName() {
        return this.residueName;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the chain ID to which the atom belongs.
     * @param chainId
     *           - character representing the chain ID.
     * @see #getChainId()
     */
    public final void setChainId(final char chainId) {
        this.chainId = chainId;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the chain ID to which the atom belongs.
     * @return character representing the chain ID of the atom.
     * @see #setChainId(char)
     */
    public final char getChainId() {
        return chainId;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the number of the amino acid to which the atom belongs.
     * @param  residueNumber
     *            - integer between -999 and 9999
     * @return {@code TRUE} if residue number is within limits, {@code FALSE}
     *         otherwise.
     * @see #getResidueNumber()
     */
    public final boolean setResidueNumber(final int residueNumber) {
        if ((residueNumber > 9999) || (residueNumber < -999)) {
            System.err.println("WARNING PDB residue number \"" + residueNumber
                              + "\" is out of range!");
            return false;
        }
        this.residueNumber = residueNumber;
    return true;
    };
    //--------------------------------------------------------------------------
    /**
     * Gets the number of the amino acid to which the atom belongs.
     * @return integer representing the residue number of the atom.
     * @see #setResidueNumber(int)
     */
    public final int getResidueNumber() {
        return residueNumber;
    };
    //--------------------------------------------------------------------------
    /**
     * Sets the rank of the amino acid within the PDB sequence to which this
     * atom belongs.
     * @param rankValue
     *        - integer number
     * @see #getRank()
     */
    public final void setRank(final int rankValue) {
        this.rank = rankValue;
    };
    //--------------------------------------------------------------------------
    /**
     * Gets the rank of the amino acid in the PDB sequence to which this atom
     * belongs.
     * @return integer representing the rank of the amino acid.
     * @see #setRank(int)
     */
    public final int getRank() {
        return this.rank;
    };
    //--------------------------------------------------------------------------
    /**
     * Sets the insertion code ID of residue to which the atom belongs.
     * @param iCode
     *           - character representing the insertion code ID.
     * @see #getICode()
     */
    public final void setICode(final char iCode) {
        this.iCode = iCode;
    }
    //--------------------------------------------------------------------------
    /**
     * Get the insertion code code ID of a residue to which the atom belongs.
     * @return character holding the insertion code ID.
     * @see #setICode(char)
     */
    public final char getICode() {
        return iCode;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the atom's XYZ coordinates.
     * @param xyz
     *        - Point3d object holding the Cartesian coordinates of the atom.
     *          Each coordinate is allowed to vary between -999.999 and 9999.999
     * @return {@code TRUE} if the coordinates are within limits, {@code FALSE}
     *          otherwise.
     * @see #getPoint3d()
     */
    public final boolean setPoint3d(final Point3d xyz) {
        this.xyz = xyz;
        if (xyz.getX() > Constants.MAX_XYZ || xyz.getX() < Constants.MIN_XYZ) {
            System.err.println("WARNING: X-coordinate is out of bounds!: "
                             + xyz.getX()
                              );
            return false;
        }
        if (xyz.getX() > Constants.MAX_XYZ || xyz.getX() < Constants.MIN_XYZ) {
            System.err.println("WARNING: Y-coordinate is out of bounds!: "
                              + xyz.getY()
                              );
            return false;
        }
        if (xyz.getX() > Constants.MAX_XYZ || xyz.getX() < Constants.MIN_XYZ) {
            System.err.println("WARNING: Z-coordinate is out of bounds!: "
                              + xyz.getZ()
                              );
            return false;
        }
    return true;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the atom's XYZ coordinates.
     * @return Point3d object holding the Cartesian coordinates of the atom.
     * @see #setPoint3d(Point3d)
     */
    public final Point3d getPoint3d() {
        return this.xyz;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the atom's occupancy value. If the value is out of limits, the limit
     * value will be set.
     * @param occupancy
     *        - double value between -99.99 and 999.99.
     * @return {@code TRUE} if the occupancy value is within limits,
     *         {@code FALSE} otherwise.
     * @see #getOccupancy()
     */
    public final boolean setOccupancy(final double occupancy) {
        if (occupancy > Constants.MAX_OCCUPANCY_TEMPERATURE_VALUE) {
            this.occupancy = Constants.MAX_OCCUPANCY_TEMPERATURE_VALUE;
            System.err.println("WARNING: Occupancy value (" + occupancy + ") "
                              + "is too large. Setting it to "
                              + Constants.MAX_OCCUPANCY_TEMPERATURE_VALUE
                              + "!");
            return false;
        } else if (occupancy < Constants.MIN_OCCUPANCY_TEMPERATURE_VALUE) {
            this.occupancy = Constants.MIN_OCCUPANCY_TEMPERATURE_VALUE;
            System.err.println("WARNING: Occupancy value (" + occupancy + ") "
                              + "is too small. Setting it to "
                              + Constants.MIN_OCCUPANCY_TEMPERATURE_VALUE
                              + "!");
            return false;
        } else {
            this.occupancy = occupancy;
        }
    return true;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the atom's occupancy value.
     * @return double value representing the occupancy value.
     * @see #setOccupancy(double)
     */
    public final double getOccupancy() {
        return this.occupancy;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the atom's temperature factor value. If the value is out of limits,
     * the limit value will be set.
     * @param temperaturFactor
     *        - double value between -99.99 and 999.99.
     * @return {@code TRUE} if the temperature factor value is within limits,
     * {@code FALSE} otherwise.
     * @see #getTemperatureFactor()
     */
    public final boolean setTemperatureFactor(final double temperaturFactor) {
        if (temperaturFactor > Constants.MAX_OCCUPANCY_TEMPERATURE_VALUE) {
            this.temperaturFactor = Constants.MAX_OCCUPANCY_TEMPERATURE_VALUE;
            System.err.println("WARNING: Temperature factor value ("
                              + temperaturFactor + ") is too large. Setting "
                              + "it to "
                              + Constants.MAX_OCCUPANCY_TEMPERATURE_VALUE
                              + "!");
            return false;
        } else if (temperaturFactor
                   <
                   Constants.MIN_OCCUPANCY_TEMPERATURE_VALUE) {
            this.temperaturFactor = -Constants.MIN_OCCUPANCY_TEMPERATURE_VALUE;
            System.err.println("WARNING: Temperature factor value("
                              + temperaturFactor + ") is too small. Setting it "
                              + "to "
                              + Constants.MIN_OCCUPANCY_TEMPERATURE_VALUE
                              + "!");
            return false;
        } else {
            this.temperaturFactor = temperaturFactor;
        }
    return true;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the atom's temperature factor value.
     * @return double value representing the temperature factor
     * @see #setTemperatureFactor(double)
     */
    public final double getTemperatureFactor() {
        return this.temperaturFactor;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the atom's average dislocation, calculated from the temperature
     * factor value by
     * Bj= 8  * Math.pow(Math.PI,2) * Math.pow(avgDis,2)
     * Bj= 79 * Math.pow(avgDis,2)
     * @return double value representing the average dislocation of the atoms 
     *         position.
     */
    public final double getAverageDislocation() {
        return Math.sqrt(this.temperaturFactor/79);
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the element name of the atom.
     * @param element
     *        - Element object
     * @see #getElement()
     */
    public final void setElement(final Element element) {
        this.element = element;
    }
    //--------------------------------------------------------------------------
       /**
     * Gets the periodic table name of the atom's element.
     * @return Element object holding the element information of the atom.
     * @see #setElement(Element)
     */
    public final Element getElement() {
        return this.element;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the charge state of the atom.
     * @param chargeState
     *        - integer between -9 and 9
     * @return {@code TRUE} if the charge state is within limits, {@code FALSE}
     *         otherwise.
     * @see #getChargeState()
     */
    public final boolean setChargeState(final int chargeState) {
        if ((chargeState > 9) || (chargeState < -9)) {
            System.err.println("WARNING: Charge state is out of bounds!: "
                              + chargeState);
            return false;
        }
        this.chargeState = chargeState;
    return true;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the charge state of the atom.
     * @return integer value representing the charge state of the atom
     * @see #setChargeState(int)
     */
    public final int getChargeState() {
        return this.chargeState;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the van der Waals radius of the atom.
     * @param vanDerWaalsRadius
     *        - double value representing a vdW radius of the atom.
     * @see #setVanDerWaalsRadius(structure.constants.Constants.ParameterSets)
     * @see #getVanDerWaalsRadius()
     */
    public final void setVanDerWaalsRadius(final double vanDerWaalsRadius) {
        this.vanDerWaalsRadius = vanDerWaalsRadius;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the van der Waals radius of the atom according to a parameterSet.
     * This method should not be used to assign radii to atoms in a loop.
     * In such cases use the method {@code AtomList.setVanDerWaalsRadii}.
     * @param parameterSet
     *        - One of the existing ParameterSets of atom radii.
     * @throws IOException if an error occurs while reading the parameter file.
     * @see #setVanDerWaalsRadius(double)
     * @see #getVanDerWaalsRadius()
     */
    public final void setVanDerWaalsRadius(
                                   final Constants.ParameterSets parameterSet)
                                                            throws IOException {
        ParameterReader params = new ParameterReader(parameterSet);
        Hashtable < Element, Double > radii = params.getVdwRadiusParameterSet();
        this.setVanDerWaalsRadius(radii.get(this.getElement()));
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the van der Waals radius of the atom.
     * @return double value representing the vdW radius the atom.
     * @see #setVanDerWaalsRadius(double)
     * @see #setVanDerWaalsRadius(structure.constants.Constants.ParameterSets)
     */
    public final double getVanDerWaalsRadius() {
        return this.vanDerWaalsRadius;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the atomic weight of the atom.
     * @param weight
     *        - double value representing a atomic weight of the atom.
     * @see #getWeight()
     */
    public final void setWeight(final double weight) {
        this.weight = weight;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the atomic weight of the atom.
     * @return double value representing the atomic weight of the atom.
     * @see #setWeight(double)
     */
    public final double getWeight() {
        return this.weight;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the aromatic state of the atom to {@code TRUE}.
     * @see #isAromatic()
     * @see #unsetAromaticity()
     */
    public final void setAromaticity() {
        this.isAromatic = true;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the aromatic state of the atom to {@code FALSE}.
     * @see #isAromatic()
     * @see #setAromaticity()
     */
    public final void unsetAromaticity() {
        this.isAromatic = false;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the aromatic state of the atom.
     * @return {@code TRUE} if atom belongs to an aromatic ring, {@code FALSE}
     *         otherwise.
     * @see #setAromaticity()
     * @see #unsetAromaticity()
     */
    public final boolean isAromatic() {
        return this.isAromatic;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the metallic state of the atom to {@code TRUE}.
     * @see #isMetalic()
     * @see #unsetMetallicity()
     */
    public final void setMetallicity() {
        this.isMetalic = true;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the metallic state of the atom to {@code FALSE}.
     * @see #isMetalic()
     * @see #setMetallicity()
     */
    public final void unsetMetallicity() {
        this.isMetalic = false;
    }
    //--------------------------------------------------------------------------
    /**
     * Gets the metallic state of the atom.
     * @return {@code TRUE} if atom has no belongs to an aromatic ring,
     *         {code @FALSE}
     *         otherwise.
     * @see #setMetallicity
     * @see #unsetMetallicity()
     */
    public final boolean isMetalic() {
        return this.isMetalic;
    }
    //--------------------------------------------------------------------------
    /**
     * Overrides the equal method of the Object class.
     * @param atom
     *        - Second Atom object to assess the equivalence with.
     * @return {@code TRUE} only if both atoms have the same name, chain Id,
     *         alternative location, residue name, residue number and
     *            Cartesian coordinates, {@code FALSE} otherwise.
     */
    public final boolean equals(final Atom atom) {
        boolean hasSameAtomAttributes =
                            this.getName().trim().equals(atom.getName().trim())
                            &&
                            this.getChainId() == atom.getChainId()
                            &&
                            this.getAlternativeLocation()
                                ==
                                atom.getAlternativeLocation();

        boolean hasSameResidueAttributes =
                            this.getResidueName().trim().equals(
                                                    atom.getResidueName().trim()
                                                               )
                            &&
                            this.getResidueNumber() == atom.getResidueNumber();

        final double errorMargin = 0.0001;
        boolean hasSameCoordinates =
                      Math.abs(this.xyz.getX() - atom.xyz.getX()) < errorMargin
                      &&
                      Math.abs(this.xyz.getY() - atom.xyz.getY()) < errorMargin
                      &&
                      Math.abs(this.xyz.getZ() - atom.xyz.getZ()) < errorMargin;
        if (hasSameAtomAttributes
            &&
            hasSameResidueAttributes
            &&
            hasSameCoordinates) {
          return true;
        }
    return false;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns all PDB related information of this atom in PDB format.
     * @return String object holding the text information of this atom in PDB
     *         format.
     */
    public final String toString() {
        return PDBwriter.pdbFormat(this) + Constants.LINE_SEPERATOR;
    }
}
