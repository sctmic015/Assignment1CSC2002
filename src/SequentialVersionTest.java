import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class SequentialVersionTest {
    public static void main (String[] args) throws Exception {
        File file = new File(args[0] + ".txt");
        Scanner fileScan = new Scanner(file);
        float[][] testArray = readArray(file);
        Terrain Test = new Terrain(testArray);
        //boolean wudUp = Test.isBasin(154, 212);
        //System.out.println(wudUp);
        for (int b = 0; b < 20; b++) {
            double time1 = System.currentTimeMillis();
            for (int i = 0; i < Test.getRows(); i++) {
                for (int j = 0; j < Test.getColumns(); j++) {
                    if (Test.isBasin(i, j) == true) {
                        System.out.print(i + "  " + j);
                        System.out.println();
                    }
                }
            }
            double time2 = System.currentTimeMillis() - time1;
            System.out.println(time2);
        }
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
        public static void writeOperationsToCSV(String information, int opCount, String filename) throws IOException{
            FileWriter fw = null;
            BufferedWriter bw = null;
            PrintWriter pw = null;


            try {
                fw = new FileWriter((filename + ".csv"), true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);

                //pw.println("Data Structure: Array" + '\n' + "Stage, date and start time tested: "
                //  + information + '\n' + "Operations counted: "
                //+ Integer.toString(opCount) + '\n');

                pw.println(information + " , " + Integer.toString(opCount));
                pw.flush();

            } finally {
                try {
                    pw.close();
                    bw.close();
                    fw.close();
                } catch (IOException io) {// can't do anything }
                }

            }
        }

        public static int lineCounter(String inputFile) throws IOException{
            File file = new File(inputFile);
            Scanner scan = new Scanner(file);
            int count = 0;
            while (scan.hasNextLine()){
                count++;
                System.out.println(scan.nextLine());
            }
            System.out.print(count);
            return count;
        }
}