package exercise;

// BEGIN
class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage (double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo() {
        return 0;
    }

    public String  toString() {
        return floorCount + " этажный коттедж площадью " + this.area + " метров";
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
