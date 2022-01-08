/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelisationphase2;

import java.sql.SQLOutput;

/**
 * Classe définissant la structure d'un cube de taille n*n
 * Définit par trois face sur le modèle Right, Up and Front tel que
 * Up : White
 * Orange : Right
 * Blue : Front
 *
 * @author bk296745
 */
public class Cube {
    private Face[] cube = new Face[3];
    private int size;

    /**
     * Permet de définir la taille du cube
     * @param size
     */
    public Cube(int size) {
        this.size = size;

        this.cube[0] = new Face(this.size, Colour.Bleu);
        this.cube[1] = new Face(this.size, Colour.Blanc);
        this.cube[2] = new Face(this.size, Colour.Orange);

        System.out.println("Face");
        System.out.println(this.cube[0].toString());

        System.out.println("Top");
        System.out.println(this.cube[1].toString());

        System.out.println("Right");
        System.out.println(this.cube[2].toString());
    }
}
