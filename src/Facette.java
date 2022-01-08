/**
 * Classe permettant de créer un objet facette, est contenu dans une face
 * @author valentinmarguerie
 */
public class Facette {
    private Colour colour;
    private Coordonne coordonne;

    /**
     * Permet d'initialiser un objet de type facette
     * @param colour : Couleur de la facette cf. Colour.java
     * @param coordonne : Coordonnes de la facette cf. Coordonne.java
     */
    public Facette(Colour colour, Coordonne coordonne) {
        this.colour = colour;
        this.coordonne = coordonne;
    }

    /*
    Liste des ascesseurs
     */

    /**
     * @return : La couleur de la facette
     */
    public Colour getColour(){
        return this.colour;
    }

    /**
     * Permet de changer la couleur d'une facette
     * @param colour : Couleur à affecter
     */
    public void setColour(Colour colour){
        this.colour = colour;
    }

    /**
     * @return : Retroune les coordonnees de la facette
     */
    public Coordonne getCoordonne(){
        return this.coordonne;
    }

    /**
     * Permet de changer les coordonnees d'une facette
     * @param coordonne : Coordonnees à attribuer
     */
    public void setCoordonne(Coordonne coordonne){
        this.coordonne = coordonne;
    }

    /**
     * @return Permet de retourner la couleur sous forme de string
     */
    @Override
    public String toString() {
        return this.colour.toString();
    }
}
