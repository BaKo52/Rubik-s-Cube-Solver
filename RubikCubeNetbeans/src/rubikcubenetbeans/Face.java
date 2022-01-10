package rubikcubenetbeans;

/**
 * Classe gérant une face de pocket cube
 */
public class Face {
    private Facette[][] listeFacettes;
    private final Colour initialColour;
    private final boolean isCubie;

    /**
     * Permet d'initialiser une face
     * @param initialColour couleur initiale de la face
     * @param isCubie est-ce que le cube est un cube 2*2 sinon 3*3
     */
    public Face(Colour initialColour, boolean isCubie){
        this.initialColour = initialColour;
        this.isCubie = isCubie;

        if(isCubie){
            listeFacettes = new Facette[2][2];
        }else{
            listeFacettes = new Facette[3][3];
        }
        
        this.init();
    }

    /**
     * Permet d'initialiser une face
     */
    private void init(){
        int max;
        
        if(isCubie){
            max = 2;
        }else{
            max = 3;
        }
        
        for(int i = 0; i < max; i++){
            for(int j = 0; j < max; j++){
                this.listeFacettes[i][j] = new Facette(initialColour);
            }
        }
    }
    
    public void rotateClockwise(){
        Facette celleEnTrop = listeFacettes[0][0];
        
        if(isCubie){
            listeFacettes[0][0] = listeFacettes[0][1];
            listeFacettes[0][1] = listeFacettes[1][1];
            listeFacettes[1][1] = listeFacettes[1][0];
            listeFacettes[1][0] = celleEnTrop;
        }else{
            listeFacettes[0][0] = listeFacettes[0][1];
            listeFacettes[0][1] = listeFacettes[0][2];
            listeFacettes[0][2] = listeFacettes[1][2];
            listeFacettes[1][2] = listeFacettes[2][2];
            listeFacettes[2][2] = listeFacettes[2][1];
            listeFacettes[2][1] = listeFacettes[2][0];
            listeFacettes[2][0] = listeFacettes[1][0];
            listeFacettes[1][0] = celleEnTrop;
        }
    }
    
    public void rotateCounterClockwise(){
        //un counter clockwise = 3 clockwise donc :
        this.rotateClockwise();
        this.rotateClockwise();
        this.rotateClockwise();
    }

    /**
     * @return : Retourne une face sous forme de string
     */
    @Override
    public String toString(){
        String res = "";
        int max = isCubie ? 2 : 3;

        for(int i = 0; i < max; i++){
            for(int j = 0; j < max; j++){
                res += this.listeFacettes[j][i].toString();
            }
            res += "\n";
        }

        return res;
    }
    
    public Facette getFacette(int colonne, int ligne){
        return this.listeFacettes[colonne][ligne];
    }
    
    public void setFacette(int colonne, int ligne, Facette facette){
        this.listeFacettes[colonne][ligne] = facette;
    }
}
