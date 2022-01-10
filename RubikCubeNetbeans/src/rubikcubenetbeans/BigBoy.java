/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubenetbeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 *
 * @author bkott
 */
public class BigBoy {
    private final HashMap<String, Face> cube = new HashMap<>();
    private ArrayList<Rotation> soluce = new ArrayList<>();

    public BigBoy() {
        // Initialisation face jaune UP'
        this.cube.put("DOWN", new Face(Colour.Jaune, false));
        // Initialisation face blanche UP
        this.cube.put("UP", new Face(Colour.Blanc, false));
        // Initialisation face bleue FRONT
        this.cube.put("FRONT", new Face(Colour.Bleu, false));
        // Initialisation face orange RIGHT
        this.cube.put("RIGHT", new Face(Colour.Orange, false));
        // Initialisation face verte FRONT'
        this.cube.put("BACK", new Face(Colour.Vert, false));
        // Initialisation face rouge RIGHT'
        this.cube.put("LEFT", new Face(Colour.Rouge, false));
    }
    
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
     * @throws java.lang.Exception si le générateur de nombre aléatoire donne un nombre inférieur à 0 ou supérieur à 5
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
            //faceRotation = geneAleatoire.nextInt(6);
            //isClockwise = geneAleatoire.nextBoolean();
            isClockwise = true;
            faceRotation = 5;
            
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
    
    private void rotateOtherFacette(String face, boolean isClockwise) {
        Facette celleEnTrop;
        int max = isClockwise ? 3 : 9;
        
        switch(face){
            //WORK !
            case "UP" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("FRONT").getFacette(0, 0);
                    this.cube.get("FRONT").setFacette(0, 0, this.cube.get("FRONT").getFacette(1, 0));
                    this.cube.get("FRONT").setFacette(1, 0, this.cube.get("FRONT").getFacette(2, 0));
                    this.cube.get("FRONT").setFacette(2, 0, this.cube.get("RIGHT").getFacette(0, 0));
                    this.cube.get("RIGHT").setFacette(0, 0, this.cube.get("RIGHT").getFacette(1, 0));
                    this.cube.get("RIGHT").setFacette(1, 0, this.cube.get("RIGHT").getFacette(2, 0));
                    this.cube.get("RIGHT").setFacette(2, 0, this.cube.get("BACK").getFacette(0, 0));
                    this.cube.get("BACK").setFacette(0, 0, this.cube.get("BACK").getFacette(1, 0));
                    this.cube.get("BACK").setFacette(1, 0, this.cube.get("BACK").getFacette(2, 0));
                    this.cube.get("BACK").setFacette(2, 0, this.cube.get("LEFT").getFacette(0, 0));
                    this.cube.get("LEFT").setFacette(0, 0, this.cube.get("LEFT").getFacette(1, 0));
                    this.cube.get("LEFT").setFacette(1, 0, this.cube.get("LEFT").getFacette(2, 0));
                    this.cube.get("LEFT").setFacette(2, 0, celleEnTrop);
                }
            }
            //WORK !
            case "FRONT" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("DOWN").getFacette(0, 0);
                    this.cube.get("DOWN").setFacette(0, 0, this.cube.get("DOWN").getFacette(1, 0));
                    this.cube.get("DOWN").setFacette(1, 0, this.cube.get("DOWN").getFacette(2, 0));
                    this.cube.get("DOWN").setFacette(2, 0, this.cube.get("RIGHT").getFacette(0, 2));
                    this.cube.get("RIGHT").setFacette(0, 2, this.cube.get("RIGHT").getFacette(0, 1));
                    this.cube.get("RIGHT").setFacette(0, 1, this.cube.get("RIGHT").getFacette(0, 0));
                    this.cube.get("RIGHT").setFacette(0, 0, this.cube.get("UP").getFacette(2, 2));
                    this.cube.get("UP").setFacette(2, 2, this.cube.get("UP").getFacette(1, 2));
                    this.cube.get("UP").setFacette(1, 2, this.cube.get("UP").getFacette(0, 2));
                    this.cube.get("UP").setFacette(0, 2, this.cube.get("LEFT").getFacette(2, 0));
                    this.cube.get("LEFT").setFacette(2, 0, this.cube.get("LEFT").getFacette(2, 1));
                    this.cube.get("LEFT").setFacette(2, 1, this.cube.get("LEFT").getFacette(2, 2));
                    this.cube.get("LEFT").setFacette(2, 2, celleEnTrop);
                }
            }
            //WORK !
            case "RIGHT" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("FRONT").getFacette(2, 0);
                    this.cube.get("FRONT").setFacette(2, 0, this.cube.get("FRONT").getFacette(2, 1));
                    this.cube.get("FRONT").setFacette(2, 1, this.cube.get("FRONT").getFacette(2, 2));
                    this.cube.get("FRONT").setFacette(2, 2, this.cube.get("DOWN").getFacette(2, 2));
                    this.cube.get("DOWN").setFacette(2, 2, this.cube.get("DOWN").getFacette(2, 1));
                    this.cube.get("DOWN").setFacette(2, 1, this.cube.get("DOWN").getFacette(2, 0));
                    this.cube.get("DOWN").setFacette(2, 0, this.cube.get("BACK").getFacette(0, 2));
                    this.cube.get("BACK").setFacette(0, 2, this.cube.get("BACK").getFacette(0, 1));
                    this.cube.get("BACK").setFacette(0, 1, this.cube.get("BACK").getFacette(0, 0));
                    this.cube.get("BACK").setFacette(0, 0, this.cube.get("UP").getFacette(2, 0));
                    this.cube.get("UP").setFacette(2, 0, this.cube.get("UP").getFacette(2, 1));
                    this.cube.get("UP").setFacette(2, 1, this.cube.get("UP").getFacette(2, 2));
                    this.cube.get("UP").setFacette(2, 2, celleEnTrop);
                }
            }
            //WORK !
            case "BACK" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("UP").getFacette(0, 0);
                    this.cube.get("UP").setFacette(0, 0, this.cube.get("UP").getFacette(1, 0));
                    this.cube.get("UP").setFacette(1, 0, this.cube.get("UP").getFacette(2, 0));
                    this.cube.get("UP").setFacette(2, 0, this.cube.get("RIGHT").getFacette(2, 0));
                    this.cube.get("RIGHT").setFacette(2, 0, this.cube.get("RIGHT").getFacette(2, 1));
                    this.cube.get("RIGHT").setFacette(2, 1, this.cube.get("RIGHT").getFacette(2, 2));
                    this.cube.get("RIGHT").setFacette(2, 2, this.cube.get("DOWN").getFacette(2, 2));
                    this.cube.get("DOWN").setFacette(2, 2, this.cube.get("DOWN").getFacette(1, 2));
                    this.cube.get("DOWN").setFacette(1, 2, this.cube.get("DOWN").getFacette(0, 2));
                    this.cube.get("DOWN").setFacette(0, 2, this.cube.get("LEFT").getFacette(2, 2));
                    this.cube.get("LEFT").setFacette(0, 2, this.cube.get("LEFT").getFacette(0, 1));
                    this.cube.get("LEFT").setFacette(0, 1, this.cube.get("LEFT").getFacette(0, 0));
                    this.cube.get("LEFT").setFacette(0, 0, celleEnTrop);
                }
            }
            case "LEFT" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("FRONT").getFacette(0, 2);
                    this.cube.get("FRONT").setFacette(0, 2, this.cube.get("FRONT").getFacette(0, 1));
                    this.cube.get("FRONT").setFacette(0, 1, this.cube.get("FRONT").getFacette(0, 0));
                    this.cube.get("FRONT").setFacette(0, 0, this.cube.get("UP").getFacette(2, 2));
                    this.cube.get("UP").setFacette(0, 2, this.cube.get("UP").getFacette(0, 1));
                    this.cube.get("UP").setFacette(0, 1, this.cube.get("UP").getFacette(0, 0));
                    this.cube.get("UP").setFacette(0, 0, this.cube.get("BACK").getFacette(2, 2));
                    this.cube.get("BACK").setFacette(2, 2, this.cube.get("BACK").getFacette(2, 1));
                    this.cube.get("BACK").setFacette(2, 1, this.cube.get("BACK").getFacette(2, 0));
                    this.cube.get("BACK").setFacette(2, 0, this.cube.get("DOWN").getFacette(0, 2));
                    this.cube.get("DOWN").setFacette(0, 2, this.cube.get("DOWN").getFacette(0, 1));
                    this.cube.get("DOWN").setFacette(0, 1, this.cube.get("DOWN").getFacette(0, 0));
                    this.cube.get("DOWN").setFacette(0, 0, celleEnTrop);
                }
            }
            //WORK !
            case "DOWN" -> {
                for(int i = 0; i < max; i++){
                    celleEnTrop = this.cube.get("FRONT").getFacette(2, 2);
                    this.cube.get("FRONT").setFacette(2, 2, this.cube.get("FRONT").getFacette(1, 2));
                    this.cube.get("FRONT").setFacette(1, 2, this.cube.get("FRONT").getFacette(0, 2));
                    this.cube.get("FRONT").setFacette(0, 2, this.cube.get("LEFT").getFacette(2, 2));
                    this.cube.get("LEFT").setFacette(2, 2, this.cube.get("LEFT").getFacette(1, 2));
                    this.cube.get("LEFT").setFacette(1, 2, this.cube.get("LEFT").getFacette(0, 2));
                    this.cube.get("LEFT").setFacette(0, 2, this.cube.get("BACK").getFacette(2, 2));
                    this.cube.get("BACK").setFacette(2, 2, this.cube.get("BACK").getFacette(1, 2));
                    this.cube.get("BACK").setFacette(1, 2, this.cube.get("BACK").getFacette(0, 2));
                    this.cube.get("BACK").setFacette(0, 2, this.cube.get("RIGHT").getFacette(2, 2));
                    this.cube.get("RIGHT").setFacette(2, 2, this.cube.get("RIGHT").getFacette(1, 2));
                    this.cube.get("RIGHT").setFacette(1, 2, this.cube.get("RIGHT").getFacette(0, 2));
                    this.cube.get("RIGHT").setFacette(0, 2, celleEnTrop);
                }
            }
        }
    }
}
