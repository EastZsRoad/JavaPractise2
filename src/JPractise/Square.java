package JPractise;

public class Square implements Pattern {
    private double number1;

    @Override
    public double getLength() {
        return 4 * (this.number1 );
    }

    @Override
    public double getArea() {
        return this.number1 * this.number1;
    }

    @Override
    public void serNumber(double... number) {
        this.number1 = number[0];
    }

}
