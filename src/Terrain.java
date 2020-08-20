public class Terrain {
    float[][] mountainArray;
    int rows;
    int columns;

    public Terrain(float[][] mountainArray){
        this.mountainArray = mountainArray;
        this.rows = mountainArray.length;
        this.columns = mountainArray[0].length;
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
            if (mountainArray[rowNum + 1][columnNum] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum+1][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum + 1][columnNum -1] - mountainArray[rowNum][columnNum] >= 0.01 &&
                    mountainArray[rowNum][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum][columnNum -1] - mountainArray[rowNum][columnNum] >= 0 &&
                    mountainArray[rowNum - 1][columnNum] - mountainArray[rowNum][columnNum] >= 0 && mountainArray[rowNum - 1][columnNum + 1] - mountainArray[rowNum][columnNum] >= 0.01 && mountainArray[rowNum - 1][columnNum-1] - mountainArray[rowNum][columnNum] >= 0)
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
}
