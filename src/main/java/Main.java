import model.Championship;

import java.io.*;

public class Main {

    static FileInputStream is;
    static DataInputStream data;
    static BufferedReader reader;
    private static Championship c;

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

    private static void handleInputFile() throws IOException {
        int teamNumber = Integer.parseInt(reader.readLine());
        c = new Championship(teamNumber);
        for (int i = 0; i < c.getTotalMatches(); i++) {
            c.processMatch(reader.readLine());
        }
    }

    private static void initStreams(String[] args) throws FileNotFoundException {
        String fileName = "Teste.txt";
        if (args != null && args.length == 1 && !args[0].trim().isEmpty()) {
            fileName = args[0];
        }
        is = new FileInputStream(fileName);
        data = new DataInputStream(is);
        reader = new BufferedReader(new InputStreamReader(data));
    }

    private static void closeStreams() throws IOException {
        reader.close();
        data.close();
        is.close();
    }
}
