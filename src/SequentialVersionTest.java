import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class SequentialVersionTest {
    public static void main (String[] args) throws Exception {
        //File file = new File(args[0] + ".txt");
        //Scanner systemScan = new Scanner(System.in);
        //String scanIn = systemScan.next();
        String scanIn = args[0] + ".txt";
        String fileName = "/home/michael/Documents/" + scanIn;
        File file = new File(fileName);
        Scanner fileScan = new Scanner(file);
        float[][] testArray = readArray(file);
        Terrain Test = new Terrain(testArray);
        boolean[][] classification = new boolean[Test.getRows()][Test.getColumns()];
        for (int b = 0; b < 20; b++) {
            double time1 = System.currentTimeMillis();
            for (int i = 0; i < Test.getRows(); i++) {
                for (int j = 0; j < Test.getColumns(); j++) {
                    classification[i][j] = Test.isBasin(i, j);
                }
            }
            double time2 = System.currentTimeMillis() - time1;
            writeOperationsToCSV("trial" + Integer.toString(b) , time2, scanIn + "Run");

        }
        writeOutputToTxt(classification, Test, "large_out_2");
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
    public static void writeOperationsToCSV(String information, double opCount, String filename) throws IOException{
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

                pw.println(information + " , " + Double.toString(opCount));
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
    public static int countTrue(boolean[][] classification) {
        int count = 0;
        for (int i = 0; i < classification.length; i ++) {
            for (int j = 0; j < classification[i].length; j ++){
                if (classification[i][j]){
                    count ++;
                }
            }
        }
        return count;
    }
    public static void writeOutputToTxt(boolean[][] classification, Terrain Test, String filename) throws IOException{
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(filename + ".txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(countTrue(classification));
            for (int i = 0; i < classification.length; i ++){
                for (int j = 0; j < classification[i].length; j ++){
                    if (classification[i][j]){
                        pw.println(i + " " + j);
                    }
                }
            }
            pw.flush();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {}
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