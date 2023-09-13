import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/*
1. Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
2. Доработайте класс Tree и метод print который мы разработали на семинаре.
Ваш метод должен распечатать полноценное дерево директорий и файлов относительно корневой директории.
 */

public class Main {
    /**
     * To demonstrate tasks.
     * <p>
     * Firs run demonstrate task №1 with backup (no need to delete backup folder).
     * <p>
     * Second run demonstrate full project tree.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            if (backup(".\\src")) {
                File file = new File(".\\backup");
                Tree.print(file);
            } else Tree.print(new File("."));
        } catch (IOException e) {
            System.out.println("Error while copying");
        } catch (SecurityException e) {
            System.out.println("Access is denied!");
        }
    }

    /**
     * Backup files method.
     * <p>
     * Reserves your files from "sourcePath" directory, to new "backup" in root project folder
     *
     * @param sourcePath specify here the path to the project folder that needs to be backed up.
     * @return boolean True - if "backup" folder is absent and created.
     * Trow exceptions if folder exist or access denied
     * @throws IOException       when there are problems finding a folder paths
     * @throws SecurityException Access troubles
     */
    public static boolean backup(String sourcePath) throws IOException, SecurityException {
        File sp = new File(sourcePath);
        File backup = new File(".\\backup\\");
        if (!backup.exists()) {
            backup.mkdir();
            copyFolder(sp.getCanonicalPath(), backup.getCanonicalPath());
            return true;
        } else {
            System.out.println("Folder \"backup\" exists already!");
        }
        return false;
    }

    /**
     * Recursive Method to copy folders and files.
     * <p>
     * Specify here the paths from(sourceFolderPath) and where(toPath) parameters.
     *
     * @param sourceFolderPath from path
     * @param toPath           where to copy
     * @throws IOException       when there are problems finding a folder paths
     * @throws SecurityException Access troubles
     */
    private static void copyFolder(String sourceFolderPath, String toPath) throws IOException, SecurityException {
        File sfp = new File(sourceFolderPath);
        File target = new File(toPath);
        File[] files = sfp.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.isDirectory()) {
                    Files.copy(Path.of(f.getCanonicalPath()),
                            Path.of(target + "\\" + f.getName()),
                            StandardCopyOption.REPLACE_EXISTING);
                } else {
                    File newFolder = new File(target + "\\" + f.getName());
                    newFolder.mkdir();
                    copyFolder(f.getCanonicalPath(),
                            newFolder.getCanonicalPath());
                }
            }
        }
    }
}
