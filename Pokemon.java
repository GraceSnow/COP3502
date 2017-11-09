public class Pokemon {
    // Initialize private variables
    private String species;
    private int attack;
    private int defense;
    private int speed;

    // Define constructors
    public Pokemon(String newSpecies) {
        species = newSpecies;
        attack = species.length() * 4 + 2;
        defense = species.length() * 2 + 7;
        speed = species.length() * 3 + 5;
    }

    // Define methods

    /*
    * When called, the evolve method will double the speed stat,
    * triple the attack stat, and multiply the defense stat by 5.
    */
    public void evolve(){
        speed *= 2;
        attack *= 3;
        defense *= 5;
    }

    public void setSpecies(String newSpecies) {
        species = newSpecies;
    }

    public void setAttack(int newAttack) {
        attack = newAttack;
    }

    public void setDefense(int newDefense) {
        defense = newDefense;
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public String getSpecies() {
        return species;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }
}
