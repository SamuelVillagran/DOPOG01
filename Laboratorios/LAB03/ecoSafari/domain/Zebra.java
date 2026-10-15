package domain;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Zebra here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Zebra implements Entity {
    private EcoSafari habitat;
    private int row, column;
    private int energy;
    private Random rand;
    private boolean isDead;
    private Grass targetGrass;
    private Land targetLand;
    private Land reproductionLand;

    /**
     * Creates a new Zebra and places it in the habitat.
     */
    public Zebra(EcoSafari habitat, int row, int column) {
        this.habitat = habitat;
        this.row = row;
        this.column = column;
        this.energy = 100;
        this.rand = new Random();
        habitat.set(this, row, column);
    }

    /**
     * Decides what the Zebra will do: eat, move, reproduce, or die.
     */
    @Override
    public void tic() {
        isDead = false;
        targetGrass = null;
        targetLand = null;
        reproductionLand = null;
        if (energy <= 0) {
            isDead = true;
            return;
        }
        targetGrass = findAdjacentGrass();

        if (targetGrass != null) {
            energy += 25;
            if (energy > 100) {
                energy = 100;
            }
        } else {
            targetLand = findLandToMove();
            if (targetLand != null) {
                energy -= 10;
            }
        }
        reproductionLand = findReproductionLand();
    }

    /**
     * Executes the actions decided in the tic() phase.
     */
    @Override
    public void tac() {

        if (isDead || energy <= 0) {
            new Land(habitat, row, column);
            return;
        }

        if (targetGrass != null) {

            int[] grassPos = habitat.find(targetGrass);
            if (grassPos != null) {
                new Land(habitat, grassPos[0], grassPos[1]);
            }
        }

        if (targetLand != null) {
            int[] nextPos = habitat.find(targetLand);
            if (nextPos != null) {
                int nextRow = nextPos[0];
                int nextCol = nextPos[1];

                habitat.set(targetLand, row, column);
                habitat.set(this, nextRow, nextCol);

                this.row = nextRow;
                this.column = nextCol;
            }
        }

        if (reproductionLand != null) {
            int[] repPos = habitat.find(reproductionLand);
            if (repPos != null) {
                new Zebra(habitat, repPos[0], repPos[1]);
            }
        }
    }
    
    
    @Override
    public Color getColor() {
        return Color.WHITE; 
    }

    @Override
    public EcoSafari getHabitat() {
        return habitat;
    }
    private Grass findAdjacentGrass() {
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (isValidPosition(r, c) && habitat.get(r, c) instanceof Grass) {
                    return (Grass) habitat.get(r, c);
                }
            }
        }
        return null;
    }

    private Land findLandToMove() {
        ArrayList<Land> availableLand = new ArrayList<>();
        for (int r = row - 2; r <= row + 2; r++) {
            for (int c = column - 2; c <= column + 2; c++) {
                if (isValidPosition(r, c) && habitat.get(r, c) instanceof Land) {
                    availableLand.add((Land) habitat.get(r, c));
                }
            }
        }
        if (!availableLand.isEmpty()) {
            return availableLand.get(rand.nextInt(availableLand.size()));
        }
        return null;
    }

    private Land findReproductionLand() {
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (isValidPosition(r, c) && habitat.get(r, c) instanceof Land) {
                    if (hasOtherZebraNeighbor(r, c)) {
                        return (Land) habitat.get(r, c);
                    }
                }
            }
        }
        return null;
    }

    private boolean hasOtherZebraNeighbor(int landRow, int landCol) {
        for (int r = landRow - 1; r <= landRow + 1; r++) {
            for (int c = landCol - 1; c <= landCol + 1; c++) {
                if (isValidPosition(r, c)) {
                    Entity e = habitat.get(r, c);
                    if (e instanceof Zebra && e != this) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isValidPosition(int r, int c) {
        return r >= 0 && r < habitat.getSize() && c >= 0 && c < habitat.getSize();
    }
}