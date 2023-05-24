import java.io.Serializable;

public class Weapon implements Serializable{

    /* Class Variables */


    /* Instance Variables */
    private final String name;
    private final int damage;

    /* Constructors */
    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    /* Getters & Setters */
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}