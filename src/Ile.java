public class Ile extends Zone {

    private final int taille;
    private Zone[][] grille = new Zone[this.taille][this.taille];

    /**
     * Constructeurs
     *  @param x
     * @param y
     * @param etat
     * @param artefact
     * @param taille
     **/
    public Ile(int x, int y, Etat etat, Artefact artefact, int taille) {
        super(x, y, etat, artefact);
        this.taille = taille;
    }



}


