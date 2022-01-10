package rubikcubenetbeans;

public class Main {
    public static void main(String[] args) throws Exception {
        Pocket pocket = new Pocket();
        BigBoy bigboy = new BigBoy();
        int scramble;
        boolean isBigboy = false;
        
        String mot = "";
        
        while(!mot.equalsIgnoreCase("pocket") && !mot.equalsIgnoreCase("bigboy")){
            System.out.println("Quel cube voulez vous utilisez :\n- 'bigboy' : cube 3x3;\n- 'pocket' : cube 2x2;");
            mot = Clavier.lireString();
            
            
            if(mot.equalsIgnoreCase("pocket")){
                isBigboy = false;
            }else if(mot.equalsIgnoreCase("bigboy")){
                isBigboy = true;
            }else{
                System.out.println("Réessayez");
            }
        }
        
        
        while (!mot.equalsIgnoreCase("end")) {
            System.out.println("Que voulez-vous faire :\n- 'print' : afficher le cube;\n- 'scramble' : mélanger le cube;\n- 'solve' : afficher la solution du rubik's cube;\n- 'end' : quitter le programme");
            mot = Clavier.lireString();
            
            if(isBigboy){
                switch(mot){
                    case "print" -> {
                        System.out.println(bigboy.toString());
                    }
                    case "scramble" -> {
                        System.out.println("Combien de fois voulez mélanger le cube ? :\n");
                        scramble = Clavier.lireInt();

                        bigboy.scramble(scramble);
                    }
                    case "solve" -> {
                        pocket.solve();
                    }
                }
            }else{
                switch(mot){
                    case "print" -> {
                        System.out.println(pocket.toString());
                    }
                    case "scramble" -> {
                        System.out.println("Combien de fois voulez mélanger le cube ? :\n");
                        scramble = Clavier.lireInt();

                        pocket.scramble(scramble);
                    }
                    case "solve" -> {
                        pocket.solve();
                    }
                }
            }
        }
    }
}
