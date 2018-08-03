package JPractise;

public class Rectangle implements Pattern {
    private double number1 ;
    private double number2;

    @Override
    public double getLength() {
        return 2 * (this.number1 + this.number2);
    }

    @Override
    public double getArea() {
        return this.number1 * this.number2;
    }

    @Override
    public void serNumber(double... number) {
        this.number1 = number[0];
        this.number2 = number[1];
    }

}


