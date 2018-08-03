package JPractise;

public class Circular implements Pattern {
    private double number1;


    @Override
    public double getLength() {
        return 2 * (this.number1 )*3.14;
    }

    @Override
    public double getArea() {
        return 3.14* this.number1 * this.number1;
    }

    @Override
    public void serNumber(double... number) {
        this.number1 = number[0];
    }
}
