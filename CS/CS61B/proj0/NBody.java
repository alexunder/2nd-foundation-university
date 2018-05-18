public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int numberOfPlanets = in.readInt();
        return in.readDouble();
    }

    public static Planet [] readPlanets(String filename) {
        In in = new In(filename);
        int numberOfPlanets = in.readInt();
        double r = in.readDouble();
        System.out.println("numberOfPlanets =" + numberOfPlanets);
        System.out.println("r =" + r);
        Planet [] planets = new Planet[numberOfPlanets];
        int i = 0;
        while(!in.isEmpty()) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m  = in.readDouble();
            String image = in.readString();
            planets[i++] = new Planet(xPos, yPos, xV, yV, m, image);

            if (i == numberOfPlanets)
                break;
        }

        return planets;
    }

    public static void drawBackground(double radius) {
        int scale = (int)radius + 1;
        System.out.println("scale =" + scale);
        StdDraw.setScale(-scale, scale);
        StdDraw.picture(-scale, -scale, "images/starfield.jpg");
    }

    public static void main(String[] args) {
        int n = args.length;
        if (n != 3) {
            System.out.println("Please run it in right way.");
            return;
        }

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double r = readRadius(filename);
        Planet [] planets = readPlanets(filename);
        drawBackground(r);
    }
}
