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
        //Terrain Test = new Terrain(testArray);
        boolean[][] classification = new boolean[testArray.length][testArray[0].length];
        for (int b = 0; b < 20; b++) {
            double time1 = System.currentTimeMillis();
            for (int i = 0; i < testArray.length; i++) {
                for (int j = 0; j < testArray[0].length; j++) {
                    classification[i][j] = isBasin(i, j, testArray);
                }
            }
            double time2 = System.currentTimeMillis() - time1;
            writeOperationsToCSV("trial" + Integer.toString(b) , time2, scanIn + "Run");

        }
        writeOutputToTxt(classification, testArray, "large_out_2");
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
    public static void writeOutputToTxt(boolean[][] classification, float[][] Test, String filename) throws IOException{
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

    public static boolean isBasin(int rowNum, int columnNum, float[][] mountainArray){
        if (rowNum == 0 && columnNum == 0){    // Top left
            //if (mountainArray[0][1] - mountainArray[0][0] >= 0.01 && mountainArray[1][1] - mountainArray[0][0] >= 0.01 &&
            //      mountainArray[1][0] - mountainArray[0][0] >= 0.01)
            return false;
        }
        else if (rowNum == 0 && columnNum == mountainArray.length -1 ) { // Top Right
            //if (mountainArray[0][mountainArray.length - 2] - mountainArray[0][mountainArray.length - 1] >= 0.01 && mountainArray[1][mountainArray.length - 2] - mountainArray[0][mountainArray.length - 1] >= 0.01 &&
            //      mountainArray[1][mountainArray.length-1] - mountainArray[0][mountainArray.length - 1] >= 0.01)
            return false;
        }
        else if(rowNum == mountainArray[0].length -1  && columnNum == 0){ // Bottom left
            //if (mountainArray[mountainArray[0].length - 2][0] - mountainArray[mountainArray[0].length-1][0] >= 0.01 && mountainArray[mountainArray[0].length - 2][1] - mountainArray[mountainArray[0].length-1][0] >= 0.01 &&
            //      mountainArray[mountainArray[0].length -1][1] - mountainArray[mountainArray[0].length - 1][0] >= 0.01)
            //return true;
        }
        else if(rowNum == mountainArray[0].length - 1 && columnNum == mountainArray.length - 1){   // Bottom right
            //if (mountainArray[mountainArray[0].length - 2][mountainArray.length - 1] - mountainArray[mountainArray[0].length-1][mountainArray.length - 1] >= 0.01 &&
            //      mountainArray[mountainArray[0].length - 2][mountainArray.length - 2] - mountainArray[mountainArray[0].length-1][mountainArray.length - 1] >= 0.01 &&
            //    mountainArray[mountainArray[0].length -1][mountainArray.length -2] - mountainArray[mountainArray[0].length - 1][mountainArray.length - 1] >= 0.01)
            return false;
        }
        else if (columnNum == 0){   // Left of box
            //if (mountainArray[rowNum - 1][columnNum] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum-1][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0 &&
            //      mountainArray[rowNum][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0 &&
            //    mountainArray[rowNum + 1][columnNum] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum + 1][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0.01)
            return false;
        }
        else if (columnNum == mountainArray.length - 1) {   // Right of box
            //if (mountainArray[rowNum - 1][columnNum] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum - 1][columnNum -1] - mountainArray[rowNum][columnNum] >= 0.01 &&
            //      mountainArray[rowNum][columnNum -1] - mountainArray[rowNum][columnNum] >= 0 &&
            //    mountainArray[rowNum + 1][columnNum] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum + 1][columnNum-1] - mountainArray[rowNum][columnNum] >= 0)
            return false;
        }
        else if (rowNum == 0){   // Top of box
            //if (mountainArray[rowNum][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum][columnNum -1] - mountainArray[rowNum][columnNum] >= 0 &&
            //      mountainArray[rowNum + 1][columnNum] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum + 1][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum - 1][columnNum-1] - mountainArray[rowNum][columnNum] >= 0)
            return false;
        }
        else if (rowNum == mountainArray[0].length - 1) { //Bottom of box
            //if (mountainArray[rowNum - 1][columnNum] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum-1][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum + 1][columnNum -1] - mountainArray[rowNum][columnNum] >= 0.01 &&
            //      mountainArray[rowNum][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum][columnNum -1] - mountainArray[rowNum][columnNum] >= 0 )
            return false;
        }
        else {  // General case
            if (mountainArray[rowNum + 1][columnNum] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum+1][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum + 1][columnNum -1] - mountainArray[rowNum][columnNum] >= 0.01 &&
                    mountainArray[rowNum][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum][columnNum -1] - mountainArray[rowNum][columnNum] >= 0.01 &&
                    mountainArray[rowNum - 1][columnNum] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum - 1][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum - 1][columnNum-1] - mountainArray[rowNum][columnNum] >= 0.01)
                return true;
        }
        return false;
    }
}