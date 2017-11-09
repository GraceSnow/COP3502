public class Pokedex {
    // Initialize private variables
    private Pokemon[] pokemonList;

    public Pokedex(int arraySize) {
        pokemonList = new Pokemon[arraySize];
    }

    // Define methods

    /*
    * Return all the names of the Pokemon species in the
    * Pokedex
    */
    public String[] listPokemon(){
        String[] listOfPokemon = new String[pokemonList.length];

        for(int i = 0; i < pokemonList.length; i++) {
            if(pokemonList[i] != null) {
                listOfPokemon[i] = pokemonList[i].getSpecies();
            }
        }
        return listOfPokemon;
    }

    /*
    * Add a Pokemon to the Pokedex and return true if it can
    * actually be added to the Pokedex. If not, return false.
    */
    public boolean addPokemon(String species) {
        boolean y = false;
        //counters
        int x = 0;
        int i = 0;

        while (x == i) {
            if (pokemonList[i] == null) {
                // Check that pokedex isn't full. If there is a space for the pokemon,
                // add pokemon to pokedex
                pokemonList[i] = new Pokemon(species);
                y = true;
                i++;
            }
            else {
                if (pokemonList[pokemonList.length - 1] != null) {
                    // If last index isn't empty, the array is full
                    // Don't add another pokemon
                    System.out.println("Max");  // Pokedex full
                    y = false;
                    x--;
                }
                if (species.equalsIgnoreCase(pokemonList[i].getSpecies())) {
                    // If the pokemon being added is already in pokedex, don't add it again
                    System.out.println("Duplicate");    // Pokemon already in pokedex
                    y = false;
                    i++;
                }
                // If last index isn't empty and current index is full, move on to next index
                i++;
                x++;
            }
        }
        return y;
    }

    /*
    * Return the stats of a certain Pokemon that you are
    * searching for.
    */
    public int[] checkStats(String species){
        int[] pokemonStats = new int[3];
        int pokemonIndex = 0; // index of pokemon species
        boolean y = false;
        // counters
        int x = 0;
        int i = 0;

        while (x == i) {
            if(pokemonList[i] != null) {
                if (species.equalsIgnoreCase(pokemonList[i].getSpecies())) {
                    y = true;
                    pokemonIndex = i;
                    i++;
                }
                else {
                    // check the next index
                    i++;
                    x++;
                }
            }
            else {
                y = false;
                i++;
            }
        }
        if (!y) {
            System.out.println("Missing");
        } else {
            pokemonStats[0] = pokemonList[pokemonIndex].getAttack();
            pokemonStats[1] = pokemonList[pokemonIndex].getDefense();
            pokemonStats[2] = pokemonList[pokemonIndex].getSpeed();
        }
        return pokemonStats;
    }

    /*
    * Sort Pokedex in alphabetical order.
    */
    public void sortPokedex(){
        for (int y = 0; y < pokemonList.length; y++) {
            for (int z = 0; z < pokemonList.length; z++) {
                try {
                    if (pokemonList[y].getSpecies().compareToIgnoreCase(pokemonList[z].getSpecies()) < 0) {
                        String temp = pokemonList[y].getSpecies();
                        pokemonList[y].setSpecies(pokemonList[z].getSpecies());
                        pokemonList[z].setSpecies(temp);
                    }
                }
                catch(NullPointerException except) {
                    break;
                }
            }
        }
    }

    /*
    * Evolve a certain Pokemon that you are searching for in the
    * Pokedex and return true if the Pokemon is actually in the
    * the Pokedex. If not, return false.
    */
    public boolean evolvePokemon(String species){
        boolean y = false;
        int x = 0;
        int i = 0;

        while (x == i) {
            if(pokemonList[i] != null) {
                if(species.equalsIgnoreCase(pokemonList[i].getSpecies())) {
                    // If pokemon is not in pokedex, evolve pokemon and set y to true
                    pokemonList[i].evolve();
                    y = true;
                    i++;
                }
                else {
                    i++;
                    x++;
                }
            }
            else {
                y = false;
                i++;
            }
        }
        return y;
    }
}