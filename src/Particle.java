public class Particle {
    public Double mass;
    public Double xVelocity;
    public Double yVelocity;
    public Double xPosition;
    public Double yPosition;

    @Override
    public String toString() {
        return "particle{" +
                ", mass=" + mass +
                ", xVelocity=" + xVelocity +
                ", yVelocity=" + yVelocity +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }

    public Particle() {
        this.mass = 1.0;
        this.xVelocity = 0.0;
        this.yVelocity = 0.0;
        this.xPosition = 0.0;
        this.yPosition = 0.0;
    }

    public Particle(Double mass, Double xVelocity, Double yVelocity, Double xPosition, Double yPosition) {
        this.mass = mass;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

}
