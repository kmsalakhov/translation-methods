import Dot.TreeDotGenerator;
import Parser.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("You need to give exactly 2 arguments: input filename and output filename");
            return;
        }

        String inputFilename = args[0], outputFilename = args[1];

        try (Reader reader = new BufferedReader(new FileReader(inputFilename))) {
            KotlinParser parser = new KotlinParser();
            Tree tree = parser.parse(reader);

            try (Writer writer = new BufferedWriter(new FileWriter(outputFilename))) {
                TreeDotGenerator dotGenerator = new TreeDotGenerator(writer);
                dotGenerator.generateDot(tree);
            } catch (IOException e) {
                System.out.printf("Can't create writer: %s", e);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Can't find file: %s", e);
        } catch (IOException e) {
            System.out.printf("Can't create reader: %s", e);
        } catch (ParseException e) {
            System.out.printf("Cannot parse: %s", e);
        }
    }
}
