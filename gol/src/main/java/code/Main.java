package code;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Game gol = new Game();
        String file = args[0];
        gol.Read(file);
        
    }
}
