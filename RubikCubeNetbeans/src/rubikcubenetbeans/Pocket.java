
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Classe définisant un pocket cube
 *  * Définit par trois face sur le modèle Right, Up and Front tel que
 *  * Up : White
 *  * Orange : Right
 *  * Blue : Front
 */
public class Pocket {
    private HashMap<String, Face> cube = new HashMap<>();

    /**
     * Permet d'initialiser un pocket cube
     */
    public Pocket(){
        // Initialisation face jaune UP'
        this.cube.put("UP'", new Face(Colour.Jaune, true));
        // Initialisation face blanche UP
        this.cube.put("UP", new Face(Colour.Blanc, true));
        // Initialisation face bleue FRONT
        this.cube.put("FRONT", new Face(Colour.Bleu, true));
        // Initialisation face orange RIGHT
        this.cube.put("RIGHT", new Face(Colour.Orange, true));
        // Initialisation face verte FRONT'
        this.cube.put("FRONT'", new Face(Colour.Vert, true));
        // Initialisation face rouge RIGHT'
        this.cube.put("RIGHT'", new Face(Colour.Rouge, true));
    }

    /**
     * @return Retourne un pocket cube sous forme de String
     */
    public String toString(){
        String res = "";

        for (Entry<String, Face> face : this.cube.entrySet()) {
            res += face.getKey() + ":\n";
            res += face.getValue().toString();
            res += "--------------------------------------------\n";
        }

        return res;
    }
    
    /**
     * Mélange le cube avec "numberOfSteps" rotations
     * @param numberOfSteps nombre de rotations effectuées
     */
    public void scramble(int numberOfSteps) throws Exception{
        final ArrayList<String> rotations = new ArrayList<>();
        
        Random geneAleatoire = new Random();
        int typeRotation;
        
        //remplit la liste des rotations avec des rotations générées aléatoirement
        //si typeRotation == 0 => UP
        //si typeRotation == 1 => UP'
        //si typeRotation == 2 => FRONT
        //si typeRotation == 3 => FRONT'
        //si typeRotation == 4 => RIGHT
        //si typeRotation == 5 => RIGHT'
        for(int i = 0; i < numberOfSteps; i++){
            typeRotation = geneAleatoire.nextInt(6);
            
            switch(typeRotation){
                case 0 -> rotations.add("UP");
                case 1 -> rotations.add("UP'");
                case 2 -> rotations.add("FRONT");
                case 3 -> rotations.add("FRONT'");
                case 4 -> rotations.add("RIGHT");
                case 5 -> rotations.add("RIGHT'");
                default -> throw(new Exception("scramble : génération < 0 ou génération > 5"));
            }
        }
        
        this.rotateSwitch(rotations);
    }
    
    private void rotateSwitch(ArrayList<String> listRotation) throws Exception{
        for(String typeRotation : listRotation){
            switch(typeRotation){
                case "UP" -> this.rotateUP();
                case "UP'" -> this.rotateUPPrime();
                case "FRONT" -> this.rotateFront();
                case "FRONT'" -> this.rotateFrontPrime();
                case "RIGHT" -> this.rotateRight();
                case "RIGHT'" -> this.rotateRightPrime();
                default -> throw(new Exception("rotateSwitch : clé inconnue"));
            }
        }
    }
    
    /**
     * fait une rotation UP
     * séparé en deux étapes :
     * rotation des facettes UP
     * rotation des facettes extérieures (les facettes collées à la face effectuant une rotation)
     */
    private void rotateUP(){
        //rotation dans le sens des aiguilles d'un montre des facetes de UP
        this.cube.get("UP").rotateClockwise();
    }
    
    /**
     * fait une rotation UP'
     * séparé en deux étapes :
     * rotation des facettes UP
     * rotation des facettes extérieures (les facettes collées à la face effectuant une rotation)
     */
    private void rotateUPPrime(){
        //rotation UP' des facetes de UP
        this.cube.get("UP'").rotateCounterClockwise();
    }
    
    /**
     * fait une rotation Front
     * séparé en deux étapes :
     * rotation des facettes FRONT
     * rotation des facettes extérieures (les facettes collées à la face effectuant une rotation)
     */
    private void rotateFront(){
        this.cube.get("FRONT").rotateCounterClockwise();
    }
    
    /**
     * fait une rotation Front'
     * séparé en deux étapes :
     * rotation des facettes FRONT
     * rotation des facettes extérieures (les facettes collées à la face effectuant une rotation)
     */
    private void rotateFrontPrime(){
        
    }
    
    /**
     * fait une rotation Right
     * séparé en deux étapes :
     * rotation des facettes RIGHT
     * rotation des facettes extérieures (les facettes collées à la face effectuant une rotation)
     */
    private void rotateRight(){
        
    }
    
    /**
     * fait une rotation Right'
     * séparé en deux étapes :
     * rotation des facettes RIGHT
     * rotation des facettes extérieures (les facettes collées à la face effectuant une rotation)
     */
    private void rotateRightPrime(){
        
    }
}
