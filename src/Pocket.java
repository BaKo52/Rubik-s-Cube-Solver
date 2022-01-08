/**
 * Classe définisant un pocket cube
 *  * Définit par trois face sur le modèle Right, Up and Front tel que
 *  * Up : White
 *  * Orange : Right
 *  * Blue : Front
 */
public class Pocket {
    private Face[][][] faces = new Face[2][2][2];

    /**
     * Permet d'initialiser un pocket cube
     */
    public Pocket(){
        // Initialisation face jaune
        this.faces[0][0][0] = new Face(new Coordonne(0,0,0), new Coordonne(1,0,1), Colour.Jaune);
        // Initialisation face blanche
        this.faces[0][1][0] = new Face(new Coordonne(0,1,0), new Coordonne(1,1,1), Colour.Blanc);
        // Initialisation face bleue
        this.faces[0][0][1] = new Face(new Coordonne(0,0,1), new Coordonne(1,1,1), Colour.Bleu);
        // Initialisation face orange
        this.faces[1][0][1] = new Face(new Coordonne(1,0,0), new Coordonne(1,1,1), Colour.Orange);
        // Initialisation face verte
        this.faces[1][1][0] = new Face(new Coordonne(0,0,0), new Coordonne(1,1,0), Colour.Vert);
        // Initialisation face rouge
        this.faces[1][1][1] = new Face(new Coordonne(0,0,0), new Coordonne(0,1,1), Colour.Rouge);
    }

    /**
     * @return Retourne un pocket cube sous forme de String
     */
    public String toString(){
        String res = "";

        res += "Right :\n" + this.faces[1][0][1].toString();
        res += "___________________________________________ \n";
        res += "Front :\n" + this.faces[0][0][1].toString();
        res += "___________________________________________ \n";
        res += "Up ' (Under) :\n" + this.faces[0][0][0].toString();
        res += "___________________________________________ \n";
        res += "Front ' (Back) :\n" + this.faces[1][1][0].toString();
        res += "___________________________________________ \n";
        res += "Up :\n" + this.faces[0][1][0].toString();
        res += "___________________________________________ \n";
        res += "Right ' (Left) :\n" + this.faces[1][1][1].toString();

        return res;
    }
}
