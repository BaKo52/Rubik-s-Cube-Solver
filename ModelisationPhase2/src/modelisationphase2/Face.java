/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelisationphase2;

/**
 * Classe définissant une face d'un rubik's contenant n*n facettes
 * @author bk296745
 */
public class Face {

    private Facette[][][] listFacettes;
    private int size;
    // Permet définir la partie constante des coordonnées de la face
    private String constante;

    public Face(int size, Colour colour){
        this.size = size;
        this.listFacettes = new Facette[size+1][size+1][size+1];
        /**
         * Permet d'initialiser une face en fonction de la couleur passée en paramètre
         */
        switch(colour){

            /**
             * Pour la face bleue, z est la valeur constante avec z = size
             */
            case Bleu : {
                this.constante = "z";
                int z = size;
                for(int x = 0; x < size + 1; x++){
                    for(int y = 0; y < size + 1; y++){
                        listFacettes[x][y][z] = new Facette(Colour.Bleu, new Coordonne(x, y, z));
                    }
                }
                break;
            }

            case Orange: {
                this.constante = "x";
                int x = size;
                for (int y = 0; y < size + 1; y++){
                    for (int z = 0; z < size + 1; z++){
                        listFacettes[x][y][z] = new Facette(Colour.Orange, new Coordonne(x, y, z));
                    }
                }
                break;
            }

            case Blanc: {
                this.constante = "y";
                int y = size;
                for (int x = 0; x < size + 1; x++){
                    for (int z = 0; z < size + 1; z++){
                        listFacettes[x][y][z] = new Facette(Colour.Blanc, new Coordonne(x, y, z));
                    }
                }
                break;
            }
        }
    }

    /**
     * @return : Un string représentant la face
     */
    @Override
    public String toString(){
        String res = "";

        switch (this.constante){
            case "z":{
                int z = this.size;

                for (int x = size; x != 0; x --){
                    for (int y = size; y != 0; y--){
                        res += listFacettes[x][y][z].getColour().toString();
                    }
                    res += "\n";
                }
                break;
            }

            case "y":{
                int y = this.size;

                for (int x = size; x != 0; x --){
                    for (int z = size; z != 0; z--){
                        res += listFacettes[x][y][z].getColour().toString();
                    }
                    res += "\n";
                }
                break;
            }

            case "x":{
                int x = this.size;

                for (int z = size; z != 0; z --){
                    for (int y = size; y != 0; y--){
                        res += listFacettes[x][y][z].getColour().toString();
                    }
                    res += "\n";
                }
                break;
            }
        }

        return res;
    }
}
