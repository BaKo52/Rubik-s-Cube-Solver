package rubikcubenetbeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bkott
 */
public class Rotation {
    boolean isClockwise;
    String face;

    public Rotation(String face, boolean isClockwise) {
        this.isClockwise = isClockwise;
        this.face = face;
    }

    public boolean isClockwise() {
        return isClockwise;
    }

    public void reverseOrder(){
        isClockwise = !isClockwise;
    }

    public String getFace() {
        return face;
    }

    @Override
    public String toString() {
        String temp = "[";
        temp += face;
        if(!isClockwise){
            temp += "'";
        }
        temp += "]";
        
        return temp;
    }
    
    
}
