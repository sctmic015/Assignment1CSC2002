import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class FindBasinHelper extends RecursiveAction {
    public float[][] array;
    boolean[][] outArray;
    int lo;
    int hi;
    int rowNum;
    static int THRESHOLD = 100000;

    FindBasinHelper(int lo, int high, int rowNum, float[][] array, boolean[][] outArray) {
        this.lo = lo;
        this.hi = high;
        this.rowNum = rowNum;
        this.array = array;
        this.outArray = outArray;
    }

    protected void compute() {
        if (hi-lo < THRESHOLD) {
            computeDirectly();
        }
        else {
            FindBasinHelper left = new FindBasinHelper(lo, (hi+lo)/2, rowNum, array, outArray);
            FindBasinHelper right = new FindBasinHelper((hi+lo)/2, hi, rowNum, array, outArray);
            //left.fork();
            //right.compute();
            //left.join();
            invokeAll(left, right);
        }
    }

    protected void computeDirectly() {
        for (int i = 0; i < hi;i ++){
            if (isBasin(rowNum, i, array)) {
                outArray[rowNum][i]=true;
            }
            else {
                outArray[rowNum][i]=false;
            }
            //outArray[rowNum][i] = true;
            //array[rowNum][i] = array[rowNum][i] * 5;
        }
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
