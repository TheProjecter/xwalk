/*
 * (C) 2010 Abdullah Kahraman
 *
 * This software is part of the open-source project "Xwalk". You can use this
 * software under the terms of the
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/).
 * This means that you
 * 1.) can copy, modify, distribute the software
 * 2.) must give credit to the author
 * 3.) must not use this work for commercial purposes
 * 4.) must license derivative works under the same or a similar license.
 *
 */

package structure.matter.protein;

import java.io.IOException;
import java.util.ArrayList;

import structure.constants.Constants;
import structure.constants.Constants.ParameterSets;
import structure.matter.Atom;
import structure.matter.AtomList;


/**
 * Class representing protein complexes.
 * @author Abdullah Kahraman
 * @version 0.1
 * @since 0.1
 */
public class PolyPeptideList extends ArrayList < PolyPeptide > {

    //--------------------------------------------------------------------------
    /**
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    //--------------------------------------------------------------------------
    /**
     * Name of complex.
     */
    private String name = "";

    //--------------------------------------------------------------------------
    /**
     * Returns protein atoms found in this complex.
     * @return AtomList object holding all atom coordinates.
     */
    public final AtomList getAllAtoms() {
        AtomList complexCoordinates = new AtomList();
        for (PolyPeptide protein : this) {
             for (AminoAcid aminoacid : protein) {
                  complexCoordinates.addAll(aminoacid.getAllAtoms());
             }
        }
        return complexCoordinates;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns all amino acids in this complex.
     * @return List of all amino acids found in this complex.
     */
    public final ArrayList<AminoAcid> getAllAminoAcids() {
        ArrayList<AminoAcid> complexAminoAcids = new ArrayList<AminoAcid>();
        for (PolyPeptide protein : this) {
             for (AminoAcid aminoAcid : protein) {
                  complexAminoAcids.add(aminoAcid);
             }
        }
        return complexAminoAcids;
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the atom radii according to a ParameterSet objectReturns protein
     * atoms found in this complex.
     * @param parameter
     *        - ParameterSet object holding atom radii.
     * @throws IOException if an error occurs while reading the parameter file.
     */
    public final void setAtomRadii(final ParameterSets parameter)
                                                            throws IOException {
        for (PolyPeptide protein : this) {
             for (AminoAcid aminoacid : protein) {
                 for (Atom atom : aminoacid.getAllAtoms()) {
                      atom.setVanDerWaalsRadius(parameter);
                 }
             }
        }
    }
    //--------------------------------------------------------------------------
    /**
     * Sets the name of this complex.
     * @param complexName
     *        - String object holding the name of this complex.
     */
    public final void setName(final String complexName) {
        this.name = complexName;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns the name of this complex.
     * @return String object holding the name of this complex.
     */
    public final String getName() {
        return name;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns the first PolyPeptide object that holds the atom. The search for
     * the atom will be done based on reference and not by the .equal method.
     * @param atom
     *        - Atom object to be searched in the PolyPeptide list members.
     * @return PolyPeptide object holding the atom. If the atom could not be
     *         found in one of the PolyPeptide list members, than {@code NULL}
     *         is returned.
     */
    public final PolyPeptide get(final Atom atom) {
        for (PolyPeptide peptide : this) {
             for (AminoAcid aa : peptide) {
                  for (Atom atom1 : aa.getAllAtoms()) {
                      if (atom == atom1) {
                          return peptide;
                      }
                  }
             }
        }
        return null;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns all PDB related information of all atoms in PDB format.
     * @return String object holding the text information of all atoms in PDB
     *         format.
     */
    public final String toString() {
        StringBuffer output = new StringBuffer();
        for (PolyPeptide protein : this) {
            output.append(protein.toString());
            output.append("TER" + Constants.LINE_SEPERATOR);
        }
    return output.toString();
    }

}
