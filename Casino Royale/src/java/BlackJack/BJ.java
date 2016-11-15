package BlackJack;

/**
 *
 * @author Abhishek Karan
 */
public class BJ {

    public int nums;
    public char suite = 'C';

    public void generateNum() {
        double num1 = Math.floor(Math.random() * 13) + 1;
        double num2 = Math.floor(Math.random() * 4) + 1;
        nums = (int) num1;
        suite = 'C';
        if (num2 == 2) {
            suite = 'D';
        } else if (num2 == 3) {
            suite = 'H';
        } else if (num2 == 4) {
            suite = 'S';
        }
    }//generateNum()
}//class
