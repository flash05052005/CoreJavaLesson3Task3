package CoreJavaLesson3Task3;

import CoreJavaLesson3Task2.GameProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.*;

public class Main {

    public static void openZip(String zipUri, String uriDir) throws IOException {
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipUri));
        ZipEntry entry;
        String name;

        while ((entry = zipIn.getNextEntry()) != null) {
            name = entry.getName();
            FileOutputStream fileOut = new FileOutputStream(uriDir + "//" + name);
            for (int c = zipIn.read(); c != -1; c = zipIn.read()) {
                fileOut.write(c);
            }
            fileOut.flush();
            zipIn.closeEntry();
            fileOut.close();
        }
    }

    public static GameProgress openProgress(String uri) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(uri);
        ObjectInputStream object = new ObjectInputStream(fileIn);

        return (GameProgress) object.readObject();
    }

    public static void main(String[] args) {
        String zipUri = "D://GamesJava/savegames//save.zip";
        String uriDir = "D://GamesJava/savegames";
        String uri = "D://GamesJava/savegames//save3.dat";

        try {
            openZip(zipUri, uriDir);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(openProgress(uri));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
