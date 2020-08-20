import java.util.*;
import java.io.*;

public class SequentialVersion {
    public static void main (String[] args) throws Exception {
        Scanner systemScan = new Scanner(System.in);
        File file;
        file = new File("/home/michael/Documents/large_in.txt");
        Scanner fileScan = new Scanner(file);
        float[][] testArray = readArray(file);
        Terrain Test = new Terrain(testArray);
        //boolean wudUp = Test.isBasin(154, 212);
        //System.out.println(wudUp);
        double time1 = System.currentTimeMillis();
        for (int i = 0; i < Test.getRows(); i ++) {
            for (int j = 0; j < Test.getColumns(); j ++){
                if (Test.isBasin(i, j) == true) {
                    System.out.print(i + "  " + j);
                    System.out.println();
                }
            }
        }
        double time2 =System.currentTimeMillis() - time1;
        System.out.println(time2);
    }
    public static float[][] readArray(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        String info = scan.nextLine();
        System.out.println(info);
        String[] infoSplit = info.split(" ");
        int dim1 = Integer.parseInt(infoSplit[0]);
        int dim2 = Integer.parseInt(infoSplit[1]);
        float[][] array = new float[dim1][dim2];
        while (scan.hasNextLine()){
            String[] line = scan.nextLine().trim().split(" ");
            int i = 0;
            int count = 0;
            while (i < dim1) {
                for (int j = 0; j < dim2 ; j ++){
                    array[i][j] = Float.parseFloat(line[count]);
                    count ++;
                }
                i ++;
            }
        }
        return array;
    }
}