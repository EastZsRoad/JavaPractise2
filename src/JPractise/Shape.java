package JPractise;

public class Shape {
    private  int id ;
    private String shapetype;
    private double number1;
    private double number2;
    private double length;
    private double area;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShapetype() {
        return shapetype;
    }

    public void setShapetype(String shapetype) {
        this.shapetype = shapetype;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Shape [id=" + id + ", shapetype=" + shapetype + ", number1=" + number1 + ", number2=" + number2 + "]";
    }

}