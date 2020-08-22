import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class FindBasin extends RecursiveTask<boolean[][]> {
    static int THRESHOLD = 10000;
    int lo;
    int hi;
    public float[][] array;
    boolean[][] outArray;

    public FindBasin(float[][] array, int lo, int hi, boolean[][] outArray) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
        this.outArray = outArray;
    }

    protected boolean[][] compute() {
        List<ForkJoinTask> children = new ArrayList();
        for (int i = 0; i < array[0].length; i++) {
            children.add(new FindBasinHelper(0, array.length, i, array, outArray));
        }
        invokeAll(children);

        return outArray;
    }
}
