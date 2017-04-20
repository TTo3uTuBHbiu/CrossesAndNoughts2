
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by z on 19.04.2017.
 */
public class BoardTest {
    Noughts.Board demo = new Noughts.Board(4, 4);
    @Test

    public void getTest() {
        demo.addCross(3,2);
        demo.addCross(3,1);
        demo.addNought(3,3);
        demo.addNought(0,3);
        assertEquals(demo.get(3,2), Noughts.Sign.Cross);
        assertEquals(demo.get(3,1), Noughts.Sign.Cross);
        assertEquals(demo.get(3,3), Noughts.Sign.Nought);
        assertEquals(demo.get(0,3), Noughts.Sign.Nought);

    }

    @Test

    public void clearCellTest(){
        demo.addCross(3,2);
        demo.addNought(3,3);
        demo.clearCell(3,2);
        demo.clearCell(3,3);


    }

    @Test

    public void MaxInRowTest(){
        demo.addCross(0, 0);
        demo.addCross(0, 1);
        demo.addNought(0, 2);
        demo.addNought(0, 3);
        demo.addCross(1, 0);
        demo.addNought(1, 1);
        demo.addCross(1, 2);
        demo.addNought(1, 3);
        demo.addNought(2, 0);
        demo.addCross(2, 1);
        demo.addNought(2, 2);
        demo.addCross(2, 3);
        demo.addNought(3, 0);
        demo.addCross(3, 1);
        demo.addNought(3, 2);
        demo.addNought(3, 3);
        assertEquals(demo.maxInRow(),3);

    }

    @Test

    public void MaxInRowTest2() {
        Noughts.Board demo1 = new Noughts.Board(1, 1);
        demo1.addNought(0, 0);
        assertEquals(demo1.maxInRow(), 1);

    }

    @Test

    public void MaxInRow3(){
        Noughts.Board demo2 = new Noughts.Board(1, 4);
        demo2.addNought(0, 0);
        demo2.addCross(0, 1);
        demo2.addNought(0, 2);
        demo2.addNought(0, 3);
        assertEquals(demo2.maxInRow(),2);

    }






}