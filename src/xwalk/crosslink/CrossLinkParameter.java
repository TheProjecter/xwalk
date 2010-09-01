package xwalk.crosslink;

import java.util.Hashtable;

import xwalk.io.CommandlineArguments;

/**
 * Class that stores and handles parameter for calculating cross-links with
 * Xwalk.
 * @author Abdullah Kahraman
 * @version 3.0
 * @since 3.0
 */
public class CrossLinkParameter {

    /**
     * Hashtable that stores all parameters necessary to calculate cross-links.
     */
    private Hashtable < Parameter, String > parameter =
                                          new Hashtable < Parameter, String >();
    //--------------------------------------------------------------------------
    /**
     * Supported cross-link parameters, that effect the behavior of the Xwalk
     * application.
     */
    public enum Parameter { ALTERNATIVE_LOCATION1, ALTERNATIVE_LOCATION2,
                            AMINO_ACID_RESIDUE_NUMBER1,
                            AMINO_ACID_RESIDUE_NUMBER2,
                            AMINO_ACID_RESIDUE_NAME1,
                            AMINO_ACID_RESIDUE_NAME2,
                            ATOM_TYPE1,
                            ATOM_TYPE2,
                            CHAIN_ID1,
                            CHAIN_ID2,
                            GRID_CELL_SIZE,
                            MAXIMUM_DISTANCE,
                            DO_INTRAMOLECULAR_DISTANCE,
                            DO_INTERMOLECULAR_DISTANCE,
                            DO_SOLVENT_PATH_DISTANCE,
                            DO_SAS,
                            SOLVENT_RADIUS,
                            MINIMUM_SOLVENT_ACCESSIBILITY_RATIO,
                            DO_LOCAL_GRID,
                            IS_HOMOMERIC,
                            DO_PYMOL_OUTPUT,
                            INFILE_PATH,
                            DISTANCE_FILE_PATH,
                            OUTFILE_PATH,
                            DO_FORCE_OUTPUT,
                            DO_VERBOSE_OUTPUT,
                            DO_GRID_OUTPUT,
                            DO_TRYPSIN_DIGEST,
                            DO_EXPASY_RULE
    };
    //--------------------------------------------------------------------------
    /**
     * Constructor that sets all relevant Xwalk parameters from the user given
     * commandline arguments.
     * @param arg
     *        - CommandlineArguments object holding all arguments that were
     *          un/set by the user.
     */
    public CrossLinkParameter(final CommandlineArguments arg) {
        this.setParameter(Parameter.ALTERNATIVE_LOCATION1,
                          arg.getAlternativeLocation1Argument());
        this.setParameter(Parameter.ALTERNATIVE_LOCATION2,
                          arg.getAlternativeLocation2Argument());
        this.setParameter(Parameter.AMINO_ACID_RESIDUE_NAME1,
                          arg.getAminoAcidName1Argument());
        this.setParameter(Parameter.AMINO_ACID_RESIDUE_NAME2,
                          arg.getAminoAcidName2Argument());
        this.setParameter(Parameter.AMINO_ACID_RESIDUE_NUMBER1,
                          arg.getAminoAcidNumber1Argument());
        this.setParameter(Parameter.AMINO_ACID_RESIDUE_NUMBER2,
                          arg.getAminoAcidNumber2Argument());
        this.setParameter(Parameter.ATOM_TYPE1, arg.getAtomType1Argument());
        this.setParameter(Parameter.ATOM_TYPE2, arg.getAtomType2Argument());
        this.setParameter(Parameter.CHAIN_ID1, arg.getChainIds1Argument());
        this.setParameter(Parameter.CHAIN_ID2, arg.getChainIds2Argument());
        this.setParameter(Parameter.DO_INTERMOLECULAR_DISTANCE,
                          Boolean.toString(arg.isInterMolecularDistanceSet()));
        this.setParameter(Parameter.DO_INTRAMOLECULAR_DISTANCE,
                          Boolean.toString(arg.isIntraMolecularDistanceSet()));
        this.setParameter(Parameter.DO_PYMOL_OUTPUT,
                          Boolean.toString(arg.isPymolOutputSet()));
        this.setParameter(Parameter.DO_SOLVENT_PATH_DISTANCE,
                          Boolean.toString(
                                      arg.isSolventPathDistanceCalculationSet())
                                          );
        this.setParameter(Parameter.GRID_CELL_SIZE,
                          Double.toString(arg.getGridCellSizeArgument()));
        this.setParameter(Parameter.INFILE_PATH,
                          arg.getInfileArgument());
        this.setParameter(Parameter.DISTANCE_FILE_PATH,
                          arg.getDistanceInfileArgument());
        this.setParameter(Parameter.OUTFILE_PATH,
                          arg.getOutfileArgument());
        this.setParameter(Parameter.IS_HOMOMERIC,
                          Boolean.toString(arg.isHomomericSet()));
        this.setParameter(Parameter.MAXIMUM_DISTANCE,
                          Double.toString(arg.getMaximumDistanceArgument()));
        this.setParameter(Parameter.SOLVENT_RADIUS,
                          Double.toString(arg.getSolventRadiusArgument()));
        this.setParameter(Parameter.DO_SAS,
                          Boolean.toString(
                                            arg.getSolventAccessibiltyArgument()
                                          )
                          );
        this.setParameter(Parameter.DO_LOCAL_GRID,
                          Boolean.toString(arg.isLocalGridSet()));
        this.setParameter(Parameter.DO_FORCE_OUTPUT,
                          Boolean.toString(arg.isForceOutputSet()));
        this.setParameter(Parameter.DO_VERBOSE_OUTPUT,
                          Boolean.toString(arg.isVerboseOutputSet()));
        this.setParameter(Parameter.DO_GRID_OUTPUT,
                          Boolean.toString(arg.isGridOutputSet()));
        this.setParameter(Parameter.DO_TRYPSIN_DIGEST, Boolean.toString(
                                                  arg.isTrypsinateArgumentSet())
                                                               );
        this.setParameter(Parameter.DO_EXPASY_RULE, Boolean.toString(
                                                      arg.isExpasyArgumentSet())
                                                               );
    }
    //--------------------------------------------------------------------------
    /**
     * Sets a cross-link parameter.
     * @param xlParameter
     *        Parameter object that holds a list of all relevant cross-link
     *        parameters.
     * @param xlValue
     *           String object that holds the value of the cross-link parameter
     */
    public final void setParameter(final Parameter xlParameter,
                                   final String xlValue) {
        this.parameter.put(xlParameter, xlValue);
    }
    //--------------------------------------------------------------------------
    /**
     * Returns the value of a cross-link parameter.
     * @param xlParameter
     *        Parameter object that holds a list of all relevant cross-link
     *        parameters.
     * @return String object that holds the value of the cross-link parameter
     */
    public final String getParameter(final Parameter xlParameter) {
        return this.parameter.get(xlParameter);
    }
}
