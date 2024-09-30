package exercise;

// BEGIN
class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat (double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }
    public double getArea() {
        double totalArea = area + balconyArea;
        return totalArea;
    }

    @Override
    public int compareTo() {
        return 0;
    }

    public String  toString() {
        return "Квартира площадью " + this.getArea() + " метров на " + floor + " этаже";
    }

    public int compareTo(Home another) {
        if (this.area > another.getArea()) {
            return 1;
        } else if (this.area < another.getArea()) {
            return -1;
        } else {
            return 0;
        }
    }
}
// END