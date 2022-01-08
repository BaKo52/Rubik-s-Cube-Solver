/**
 * Classe gérant une face de pocket cube
 */
public class Face {
    private Facette[][][] listeFacettes = new Facette[2][2][2];
    private Coordonne start;
    private Coordonne end;
    private Colour initialColour;

    /**
     * Permet d'initialiser un face
     * @param start
     * @param end
     * @param initialColour
     */
    public Face(Coordonne start, Coordonne end, Colour initialColour){
        this.end = end;
        this.start = start;
        this.initialColour = initialColour;

        this.initializer();
    }

    /**
     * Permet d'initialiser une face
     */
    private void initializer(){
        int y = this.start.getY();
        int x = this.start.getX();
        int z = this.start.getZ();

        if(this.end.getX() == x){

            for(y = this.start.getY(); y<=this.end.getY(); y++){
                for(z = this.start.getZ(); z<=this.end.getZ(); z++){
                    this.listeFacettes[x][y][z] = new Facette(this.initialColour, new Coordonne(x, y, z));
                }
            }

        } else if(this.end.getY()==y){

            for(x = this.start.getX(); x<=this.end.getX(); x++){
                for(z = this.start.getZ(); z<=this.end.getZ(); z++){
                    this.listeFacettes[x][y][z] = new Facette(this.initialColour, new Coordonne(x, y, z));
                }
            }

        } else if(this.end.getZ()==z){

            for(y = this.start.getY(); y<=this.end.getY(); y++){
                for(x = this.start.getX(); x<=this.end.getX(); x++){
                    this.listeFacettes[x][y][z] = new Facette(this.initialColour, new Coordonne(x, y, z));
                }
            }

        }

    }

    /**
     * @return : Retourne une face sous forme de string
     */
    @Override
    public String toString(){
        String res = "";

        int y = this.start.getY();
        int x = this.start.getX();
        int z = this.start.getZ();

        if(this.end.getX() == x){

            for(y = this.start.getY(); y<=this.end.getY(); y++){
                for(z = this.start.getZ(); z<=this.end.getZ(); z++){
                    res += this.listeFacettes[x][y][z].toString();
                }
                res += "\n";
            }

        } else if(this.end.getY()==y){

            for(x = this.start.getX(); x<=this.end.getX(); x++){
                for(z = this.start.getZ(); z<=this.end.getZ(); z++){
                    res += this.listeFacettes[x][y][z].toString();
                }
                res += "\n";
            }

        } else if(this.end.getZ()==z){

            for(y = this.start.getY(); y<=this.end.getY(); y++){
                for(x = this.start.getX(); x<=this.end.getX(); x++){
                    res += this.listeFacettes[x][y][z].toString();
                }
                res += "\n";
            }

        }

        return res;
    }
}
