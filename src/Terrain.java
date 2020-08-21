import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Terrain {
    float[][] mountainArray;
    int rows;
    int columns;

    public Terrain(float[][] mountainArray){
        this.mountainArray = mountainArray;
        this.rows = mountainArray.length;
        this.columns = mountainArray[0].length;
    }

    // Maybe try get this to work tomorrow or leave out
    public Terrain(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        String info = scan.nextLine();
        //System.out.println(info);
        String[] infoSplit = info.split(" ");
        int dim1 = Integer.parseInt(infoSplit[0]);
        int dim2 = Integer.parseInt(infoSplit[1]);
        float[][] array = new float[dim1][dim2];
        //this.mountainArray = new float[dim1][dim2];
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
        System.out.println(Arrays.toString(array));
        this.mountainArray = array;
    }

   public boolean isBasin(int rowNum, int columnNum){
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

    public int getRows(){
        return this.rows;
    }
    public int getColumns() {
        return this.columns;
    }
    public float getItem(int i, int j) {
        return mountainArray[i][j];
    }
}
