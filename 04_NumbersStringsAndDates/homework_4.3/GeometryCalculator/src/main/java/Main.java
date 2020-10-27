public class Main {
    public static void main(String[] args) {

        double squareCircle = GeometryCalculator.getCircleSquare(10.0);
        System.out.println(squareCircle);

        double volumeSphere = GeometryCalculator.getSphereVolume(27.0);
        System.out.println(volumeSphere);

        double squareTriangle = GeometryCalculator.getTriangleSquare(12.0, 13.0, 14.0);
        System.out.println(squareTriangle);
    }
}
