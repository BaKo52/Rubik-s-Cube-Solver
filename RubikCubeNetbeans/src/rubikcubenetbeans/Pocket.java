package rubikcubenetbeans;

import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<Rotation> soluce = new ArrayList<>();

    /**
     * Permet d'initialiser un pocket cube
     */
    public Pocket(){
        // Initialisation face jaune UP'
        this.cube.put("DOWN", new Face(Colour.Jaune, true));
        // Initialisation face blanche UP
        this.cube.put("UP", new Face(Colour.Blanc, true));
        // Initialisation face bleue FRONT
        this.cube.put("FRONT", new Face(Colour.Bleu, true));
        // Initialisation face orange RIGHT
        this.cube.put("RIGHT", new Face(Colour.Orange, true));
        // Initialisation face verte FRONT'
        this.cube.put("BACK", new Face(Colour.Vert, true));
        // Initialisation face rouge RIGHT'
        this.cube.put("LEFT", new Face(Colour.Rouge, true));
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
        final ArrayList<Rotation> rotations = new ArrayList<>();
        
        Random geneAleatoire = new Random();
        Boolean isClockwise;
        int faceRotation;
        
        //remplit la liste des rotations avec des rotations générées aléatoirement
        //si typeRotation == 0 => UP
        //si typeRotation == 1 => DOWN
        //si typeRotation == 2 => FRONT
        //si typeRotation == 3 => BACK
        //si typeRotation == 4 => RIGHT
        //si typeRotation == 5 => LEFT
        for(int i = 0; i < numberOfSteps; i++){
            faceRotation = geneAleatoire.nextInt(6);
            isClockwise = geneAleatoire.nextBoolean();
            
            switch(faceRotation){
                case 0 -> rotations.add(new Rotation("UP", isClockwise));
                case 1 -> rotations.add(new Rotation("DOWN", isClockwise));
                case 2 -> rotations.add(new Rotation("FRONT", isClockwise));
                case 3 -> rotations.add(new Rotation("BACK", isClockwise));
                case 4 -> rotations.add(new Rotation("RIGHT", isClockwise));
                case 5 -> rotations.add(new Rotation("LEFT", isClockwise));
                default -> throw(new Exception("scramble : faceRotation < 0 ou faceRotation > 5"));
            }
        }
        
        System.out.println(rotations.toString());
        
        for(Rotation infoRotation : rotations){
            if(infoRotation.isClockwise()){
                this.rotateClockwise(infoRotation.getFace());
            }else{
                this.rotateCounterClockwise(infoRotation.getFace());
            }
        }
        
        Collections.reverse(rotations);
        
        for(int i = 0; i < rotations.size(); i++){
            rotations.get(i).reverseOrder();
        }    
        
        soluce = rotations;   
    }
    
    public void solve(){
        System.out.println("La solution est la combinaison de mouvements suivantes : " + soluce.toString());
        
        for(Rotation infoRotation : soluce){
            if(infoRotation.isClockwise()){
                this.rotateClockwise(infoRotation.getFace());
            }else{
                this.rotateCounterClockwise(infoRotation.getFace());
            }
        }
    }
    
    /**
     * fait une rotation dans le sens des aiguilles d'un montre sur la face
     * séparé en deux étapes :
     * rotation des facettes de la face
     * rotation des facettes extérieures (les facettes collées à la face effectuant une rotation)
     */
    private void rotateClockwise(String face){
        //rotation dans le sens des aiguilles d'une montre des facetes
        this.cube.get(face).rotateClockwise();
        
        //rotation des facettes extérieures
        this.rotateOtherFacette(face, true);
    }
    
    /**
     * fait une rotation dans le sens inverse des aiguilles d'un montre sur la face
     * séparé en deux étapes :
     * rotation des facettes de la face
     * rotation des facettes extérieures (les facettes collées à la face effectuant une rotation)
     */
    private void rotateCounterClockwise(String face){
        //rotation dans le sens inverse des aiguilles d'une montre des facetes
        this.cube.get(face).rotateCounterClockwise();
        
        //rotation des facettes extérieures
        this.rotateOtherFacette(face, false);
    }

    private void rotateOtherFacette(String face, boolean isClockwise) {
        Facette celleEnTrop;
        int max = isClockwise ? 2 : 6;
                    
        switch(face){
            //WORK !
            case "UP" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("FRONT").getFacette(0, 0);
                    this.cube.get("FRONT").setFacette(0, 0, this.cube.get("FRONT").getFacette(1, 0));
                    this.cube.get("FRONT").setFacette(1, 0, this.cube.get("RIGHT").getFacette(0, 0));
                    this.cube.get("RIGHT").setFacette(0, 0, this.cube.get("RIGHT").getFacette(1, 0));
                    this.cube.get("RIGHT").setFacette(1, 0, this.cube.get("BACK").getFacette(0, 0));
                    this.cube.get("BACK").setFacette(0, 0, this.cube.get("BACK").getFacette(1, 0));
                    this.cube.get("BACK").setFacette(1, 0, this.cube.get("LEFT").getFacette(0, 0));
                    this.cube.get("LEFT").setFacette(0, 0, this.cube.get("LEFT").getFacette(1, 0));
                    this.cube.get("LEFT").setFacette(1, 0, celleEnTrop);
                }
            }
            //WORK !
            case "FRONT" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("DOWN").getFacette(0, 0);
                    this.cube.get("DOWN").setFacette(0, 0, this.cube.get("DOWN").getFacette(1, 0));
                    this.cube.get("DOWN").setFacette(1, 0, this.cube.get("RIGHT").getFacette(0, 1));
                    this.cube.get("RIGHT").setFacette(0, 1, this.cube.get("RIGHT").getFacette(0, 0));
                    this.cube.get("RIGHT").setFacette(0, 0, this.cube.get("UP").getFacette(1, 1));
                    this.cube.get("UP").setFacette(1, 1, this.cube.get("UP").getFacette(0, 1));
                    this.cube.get("UP").setFacette(0, 1, this.cube.get("LEFT").getFacette(1, 0));
                    this.cube.get("LEFT").setFacette(1, 0, this.cube.get("LEFT").getFacette(1, 1));
                    this.cube.get("LEFT").setFacette(1, 1, celleEnTrop);
                }
            }
            //WORK !
            case "RIGHT" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("FRONT").getFacette(1, 0);
                    this.cube.get("FRONT").setFacette(1, 0, this.cube.get("FRONT").getFacette(1, 1));
                    this.cube.get("FRONT").setFacette(1, 1, this.cube.get("DOWN").getFacette(1, 0));
                    this.cube.get("DOWN").setFacette(1, 0, this.cube.get("DOWN").getFacette(1, 1));
                    this.cube.get("DOWN").setFacette(1, 1, this.cube.get("BACK").getFacette(0, 1));
                    this.cube.get("BACK").setFacette(0, 1, this.cube.get("BACK").getFacette(0, 0));
                    this.cube.get("BACK").setFacette(0, 0, this.cube.get("UP").getFacette(1, 0));
                    this.cube.get("UP").setFacette(1, 0, this.cube.get("UP").getFacette(1, 1));
                    this.cube.get("UP").setFacette(1, 1, celleEnTrop);
                }
            }
            //WORK !
            case "BACK" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("UP").getFacette(0, 0);
                    this.cube.get("UP").setFacette(0, 0, this.cube.get("UP").getFacette(1, 0));
                    this.cube.get("UP").setFacette(1, 0, this.cube.get("RIGHT").getFacette(1, 0));
                    this.cube.get("RIGHT").setFacette(1, 0, this.cube.get("RIGHT").getFacette(1, 1));
                    this.cube.get("RIGHT").setFacette(1, 1, this.cube.get("DOWN").getFacette(1, 1));
                    this.cube.get("DOWN").setFacette(1, 1, this.cube.get("DOWN").getFacette(0, 1));
                    this.cube.get("DOWN").setFacette(0, 1, this.cube.get("LEFT").getFacette(0, 1));
                    this.cube.get("LEFT").setFacette(0, 1, this.cube.get("LEFT").getFacette(0, 0));
                    this.cube.get("LEFT").setFacette(0, 0, celleEnTrop);
                }
            }
            //WORK !
            case "LEFT" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("FRONT").getFacette(0, 1);
                    this.cube.get("FRONT").setFacette(0, 1, this.cube.get("FRONT").getFacette(0, 0));
                    this.cube.get("FRONT").setFacette(0, 0, this.cube.get("UP").getFacette(0, 1));
                    this.cube.get("UP").setFacette(0, 1, this.cube.get("UP").getFacette(0, 0));
                    this.cube.get("UP").setFacette(0, 0, this.cube.get("BACK").getFacette(1, 0));
                    this.cube.get("BACK").setFacette(1, 0, this.cube.get("BACK").getFacette(1, 1));
                    this.cube.get("BACK").setFacette(1, 1, this.cube.get("DOWN").getFacette(0, 1));
                    this.cube.get("DOWN").setFacette(0, 1, this.cube.get("DOWN").getFacette(0, 0));
                    this.cube.get("DOWN").setFacette(0, 0, celleEnTrop);
                }
            }
            //WORK !
            case "DOWN" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("FRONT").getFacette(1, 1);
                    this.cube.get("FRONT").setFacette(1, 1, this.cube.get("FRONT").getFacette(0, 1));
                    this.cube.get("FRONT").setFacette(0, 1, this.cube.get("LEFT").getFacette(1, 1));
                    this.cube.get("LEFT").setFacette(1, 1, this.cube.get("LEFT").getFacette(0, 1));
                    this.cube.get("LEFT").setFacette(0, 1, this.cube.get("BACK").getFacette(1, 1));
                    this.cube.get("BACK").setFacette(1, 1, this.cube.get("BACK").getFacette(0, 1));
                    this.cube.get("BACK").setFacette(0, 1, this.cube.get("RIGHT").getFacette(1, 1));
                    this.cube.get("RIGHT").setFacette(1, 1, this.cube.get("RIGHT").getFacette(0, 1));
                    this.cube.get("RIGHT").setFacette(0, 1, celleEnTrop);
                }
            }
        }
    }
}
