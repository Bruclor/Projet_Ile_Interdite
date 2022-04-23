package Modeles;

import java.util.Random;

/* =============================================
 * =                                           =
 * =             CLASS PAQUET                  =
 * =                                           =
 * =============================================
 */
public class Paquet<T> {

    private int nbCartes;        //Le nombre de cartes contenues dans le paquet(pioche + defausse)
    private Carte nextPioche;    //La premiere carte de la pioche
    private Carte nextDefausse;  //La premiere carte de la defausse

    class Carte {                //Sous classe pour representer une carte du paquet

        private final T elt;           //Un élément du type T
        private Carte nextCarte;       //La carte suivante

        /** -- Construit une carte contenant une element de type T
         *
         * @param elt de type T
         */
        Carte(T elt) {
            this.elt = elt;
            this.nextCarte = null;
        }

    }

    /*
      ===========================================
      =              CONSTRUCTEUR               =
      ===========================================
     */
    public Paquet() {
        this.nbCartes = 0;           //Pas de cartes au début
        this.nextPioche = null;      //Pioche vide
        this.nextDefausse = null;    //Defausse vide
    }
    /*
      ===========================================
      =                 SETTER                  =
      ===========================================
     */

    /** -- Ajoute une carte de type T au paquet
     *
     * @param elt
     */
    public void addCarte(T elt) {
        Carte tmp = this.nextPioche;
        this.nextPioche = new Carte(elt);
        this.nextPioche.nextCarte = tmp;
        this.nbCartes++;
    }

    /** -- Supprime la dernier carte piochee du paquet (suppose que la carte a déjà été piochee)
     */
    public void removeCartePiochee(){         //DOIT S'APPLIQUER APRES QUE LA CARTE AIT ETE PIOCHEE
        Carte remove = this.nextDefausse;
        this.nextDefausse = this.nextDefausse.nextCarte;
        remove.nextCarte = null;
        this.nbCartes--;
    }

    /** -- Pioche une carte et la defausse
     *
     * @return l'element present sur la carte piochée
     */
    public T piocheUneCarte(){
        if (this.nextPioche == null) {
            this.reformePaquet();
        }
        this.affichePaquet();
        Carte cartePiochee = this.nextPioche;
        this.nextPioche = cartePiochee.nextCarte;
        cartePiochee.nextCarte = this.nextDefausse;
        this.nextDefausse = cartePiochee;
        return cartePiochee.elt;
    }

    /** -- Vide le paquet, le melange et reforme le paquet
     */
    public void melangePaquet(){
        while (this.nextPioche != null){
            this.piocheUneCarte();
        }
        this.reformePaquet();
    }

    /** -- Reforme le paquet aleatoirement lorsque le paquet est vide
     *
     */
    public void reformePaquet() {
        Random random = new Random();
        int alea;
        for (int k=this.nbCartes; k>0; k--){
            alea = random.nextInt(k);
            Carte preCarte = null;
            Carte tireeAuSort = this.nextDefausse;
            for (int i=0; i<alea; i++){
                preCarte = tireeAuSort;
                tireeAuSort = tireeAuSort.nextCarte;
            }
            if (preCarte==null){
                this.nextDefausse = tireeAuSort.nextCarte;
            } else {
                preCarte.nextCarte = tireeAuSort.nextCarte;
            }
            Carte tmp = this.nextPioche;
            this.nextPioche = tireeAuSort;
            tireeAuSort.nextCarte = tmp;
        }
    }

    /** -- Methode pour afficher le paquet
     */
    public void affichePaquet(){
        System.out.print(" Pioche : [");
        Carte c = this.nextPioche;
        while (c != null){
            System.out.print(c.elt + ", ");
            c = c.nextCarte;
        }
        System.out.print("]   Defausse : [");
        c = this.nextDefausse;
        while (c != null){
            System.out.print(c.elt + ", ");
            c = c.nextCarte;
        }
        System.out.println(" ]");
    }

}
