package ensta;

import java.util.NoSuchElementException;

public enum Hit {
    MISS(-1, "manque"),
    STIKE(-2, "touche"),
    DESTROYER(2, "Fregate"),
    SUBMARINE(3, "Sous-marin"),
    BATTLESHIP(4, "Croiseur"),
    CARRIER(5, "Porte-avion");

    /* ***
     * Attributes
     */
    private int value;
    private String label;

    /* ***
     * Constructor
     */
    Hit(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /* ***
     * Methods
     */
    public static Hit fromInt(int value) {
        for (Hit hit : Hit.values()) {
            if (hit.value == value) {
                return hit;
            }
        }
        throw new NoSuchElementException("no enum for value " + value);
    }

    public String toString() {
        return this.label;
    }
};
