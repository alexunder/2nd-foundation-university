public class NBody {
    public static final double SCIENTIFIC_RATIO = 1.0e+9;

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
        int scale = (int)(radius / SCIENTIFIC_RATIO);
        StdDraw.setScale(-scale, scale);
        StdDraw.picture(0, 0 , "images/starfield.jpg");
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

        StdDraw.enableDoubleBuffering();

        int t = 0;
        int i = 0;

        double [] xForces = new double[planets.length];
        double [] yForces = new double[planets.length];

        for (t = 0; t < T; t += dt) {
            for (i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (i = 0; i < planets.length; i++) {
                planets[i].update(t, xForces[i], yForces[i]);
            }

            drawBackground(r);

            for (i = 0; i < planets.length; i++) {
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
