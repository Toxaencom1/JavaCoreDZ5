import java.io.File;


/**
 * This class needs to print folders and files tree from directory
 */
public class Tree {
    /**
     * Short form to print folders and files tree
     *
     * @param file object with String path
     */
    public static void print(File file) {
        print(file, "", true);
    }

    /**
     * Form with additional parameters to print folders and files tree.
     * <p>
     * Printing a tree of folders and files with pseudo-graphics,
     * uses counters and recursion
     *
     * @param file   file object with String path
     * @param indent choose your initial indent
     * @param isLast Is the final folder the path you specified?
     */
    public static void print(File file, String indent, boolean isLast) {
        // Setting indentation, depending on type
        System.out.print(indent);
        if (file.isDirectory()) {
            if (isLast) {
                System.out.print("└─▀ ");
                indent += "  ";
            } else {
                System.out.print("├─▀ ");
                indent += "│ ";
            }
        } else {
            if (isLast) {
                System.out.print("└─ ");
                indent += "  ";
            } else {
                System.out.print("├─ ");
                indent += "│ ";
            }
        }
        System.out.println(file.getName());
        File[] files = file.listFiles();
        if (files == null)
            return;
        // Counting total files and sub folders
        int subDirTotal = 0, filesTotal = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                subDirTotal++;
            else
                filesTotal++;
        }
        // Counting folders amount in directory and recursion method calling
        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirCounter++;
                print(files[i], indent, (subDirCounter == subDirTotal) && (filesTotal == 0));
            }
        }
        // Counting files amount in directory and recursion method calling
        int filesCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                filesCounter++;
                print(files[i], indent, filesCounter == filesTotal);
            }
        }
    }
}
