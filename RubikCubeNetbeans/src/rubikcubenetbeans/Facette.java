package rubikcubenetbeans;

/**
 * Classe permettant de créer un objet facette, est contenu dans une face
 * @author valentinmarguerie
 */
public class Facette {
    private Colour colour;

    /**
     * Permet d'initialiser un objet de type facette
     * @param colour : Couleur de la facette cf. Colour.java
     */
    public Facette(Colour colour) {
        this.colour = colour;
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
     * @return Permet de retourner la couleur sous forme de string
     */
    @Override
    public String toString() {
        return this.colour.toString();
    }
}
