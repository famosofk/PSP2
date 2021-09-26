import model.Championship;

import java.io.*;

public class Main {

    /*
      Todos os métodos começam com complexidade valendo 1.
      Para cada fluxo, condicional (exceção, else e default) e operador lógico adicionaremos 1.
      Métodos sort não serão considerados, pois, eles não geram um fluxo.
      **/

    private static FileInputStream is;
    private static DataInputStream data;
    private static BufferedReader reader;
    private static Championship c;

    /**
     * Devido a presença do catch, temos uma complexidade ciclomática de 2.
     **/
    public static void main(String[] args) throws IOException {
        try {
            initStreams(args);
            handleInputFile();
            System.out.println(c.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStreams();
        }
    }

    /**
     * Complexidade: 2. Apresenta um for, lendo uma linha para cada partida.
     **/
    private static void handleInputFile() throws IOException {
        int teamNumber = Integer.parseInt(reader.readLine());
        c = new Championship(teamNumber);
        for (int i = 0; i < c.getTotalMatches(); i++) {
            String[] data = reader.readLine().trim().split(" ");
            int firstTeamScore = Integer.parseInt(data[1]);
            int secondTeamScore = Integer.parseInt(data[3]);
            int firstTeamNumber = Integer.parseInt(data[0]);
            int secondTeamNumber = Integer.parseInt(data[2]);
            c.processMatch(firstTeamScore, secondTeamScore, firstTeamNumber, secondTeamNumber);
        }
    }

    /**
     * Complexidade: 4.
     * Apresenta um if +1
     * apresenta dois equals +2
     **/
    private static void initStreams(String[] args) throws FileNotFoundException {
        String fileName = "Teste.txt";
        if (args != null && args.length == 1 && !args[0].trim().isEmpty()) {
            fileName = args[0];
        }
        is = new FileInputStream(fileName);
        data = new DataInputStream(is);
        reader = new BufferedReader(new InputStreamReader(data));
    }

    /**
     * Complexidade: 1. Código sequencial.
     */
    private static void closeStreams() throws IOException {
        reader.close();
        data.close();
        is.close();
    }
}