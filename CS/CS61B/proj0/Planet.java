public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final private double gFactor = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass  = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass  = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double temp = Math.pow((p.xxPos - xxPos), 2) + Math.pow((p.yyPos - yyPos), 2);
        return Math.sqrt(temp);
    }

    public double calcForceExertedBy(Planet p) {
        double force = (gFactor * mass * p.mass) / Math.pow(calcDistance(p), 2);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double totalForce = calcForceExertedBy(p);
        return (totalForce * (p.xxPos - xxPos)) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double totalForce = calcForceExertedBy(p);
        return (totalForce * (p.yyPos - yyPos)) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double force = 0.0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i]))
                continue;
            force += calcForceExertedByX(allPlanets[i]);
        }
        return force;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double force = 0.0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i]))
                continue;
            force += calcForceExertedByY(allPlanets[i]);
        }
        return force;
    }

    public void update(double t, double fx, double fy) {
        double aX = fx / mass;
        double aY = fy / mass;

        xxVel += t * aX;
        yyVel += t * aY;

        xxPos += t * xxVel;
        yyPos += t * yyVel;
    }
}
