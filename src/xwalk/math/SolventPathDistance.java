package xwalk.math;

import java.util.ArrayList;
import java.util.Vector;

import structure.constants.Constants.Value;
import structure.grid.AtomGrid;
import structure.grid.Grid;
import structure.grid.GridCell;
import structure.grid.GridUtilities;
import structure.grid.Path;
import structure.math.algorithms.BreadthFirstSearch;
import structure.matter.Atom;
import structure.matter.AtomList;


/**
 * Master class for calculating and storing Solvent-Path distances.
 * @author Abdullah Kahraman
 * @version 3.0
 * @since 3.0
 */
public class SolventPathDistance {
    /**
     * Source grid cell.
     */
    private GridCell sourceCell;
    /**
     * All candidate target grid cells.
     */
    private ArrayList < GridCell > targetCells;
    /**
     * Grid object which will be used to calculate the Solvent-Path distance.
     */
    private Grid grid;
    /**
     * The linker moiety of a cross-linker is about 1,5 carbon atom thick.
     */
    private static final double DISTANCE_PATH_RADIUS = 3.0;

    /**
     * GridCells that are active and for which distances are calculated.
     *
     */
    private Vector < GridCell > activeCells = new Vector < GridCell > ();
    //--------------------------------------------------------------------------
    /**
     * Constructor.
     * @param atom1cell
     *        - GridCell object of first protein atom to be connected by the
     *          virtual cross-linker.
     * @param atom2cells
     *        - List of GridCell objects of second protein atoms to be connected
     *          by the virtual cross-linker.
     * @param grid
     *        - AtomGrid object build on protein structure.
     */
    public SolventPathDistance(final GridCell atom1cell,
                               final ArrayList < GridCell > atom2cells,
                               final Grid grid
                              ) {
        this.sourceCell = atom1cell;
        this.targetCells = atom2cells;
        this.grid = grid;
    }
    //--------------------------------------------------------------------------
    /**
     * Constructor.
     * @param atom1
     *        - First protein atom to be connected by the virtual cross-linker.
     * @param atoms2
     *        - List of second protein atoms to be connected by the virtual
     *          cross-linker.
     * @param atomGrid
     *        - Grid build on protein structure.
     */
    public SolventPathDistance(final Atom atom1,
                               final AtomList atoms2,
                               final AtomGrid atomGrid) {
        ArrayList < GridCell > atomCells = atomGrid.getAllGridCells(atom1);
        for (GridCell cell : atomCells) {
            // set all cells that are occupied by this atom to unoccupied and
            // unvisited.
             cell.reset();
        }
        ArrayList < GridCell > atom2cells = new ArrayList < GridCell > ();
        for (Atom atom2 : atoms2) {
             GridCell atom2cell = atomGrid.get(atom2);
             atom2cell.reset();
             atom2cells.add(atom2cell);
             atomCells = atomGrid.getAllGridCells(atom2);
             for (GridCell cell : atomCells) {
                 // set all cells that are occupied by this atom to unoccupied 
                 // and unvisited.
                 cell.reset();
            }
        }
        this.sourceCell = atomGrid.get(atom1);
        this.targetCells = atom2cells;
        this.grid = atomGrid;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns a list of Path objects, where each path corresponds to a single
     * source-target distance measure.
     * @param maxDist
     *        - double value representing the maximum allowed distance between
     *          source and target.
     * @return List of Path objects, one for each solvent path distance
     *         calculation
     */
    public final ArrayList < Path > getShortestPath(final double maxDist) {
        // initialize distance calculation
        this.grid.resetSoft();
        BreadthFirstSearch shortestPathAlgo = new BreadthFirstSearch();
        ArrayList < Path > paths = shortestPathAlgo.findShortestPath(
                                                                    sourceCell,
                                                                    targetCells,
                                                                    grid,
                                                                    maxDist
                                                                    );
        return paths;
    }
    //--------------------------------------------------------------------------
    /**
     * Extract the distance of a target cell to its source cell from a Path
     * object.
     * @param path
     *        - Path object holding the source and target grid cells and all
     *          grid cells inbetween.
     * @return double value representing the distance extracted from the target
     *         grid cell.
     */
    public static double extractTargetDistances(final Path path) {
        return Double.parseDouble(path.get(
                                     BreadthFirstSearch.CELL_NO_OF_TARGET_CELL_IN_PATH
                                          ).getValue(Value.DISTANCE)
                                 );
    }
    //--------------------------------------------------------------------------
}