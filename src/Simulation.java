import java.util.ArrayList;

public class Simulation {

//    public static void main(String[] args) {
//        new Simulation().run();
//    }

    private void adjustVelocities(ArrayList<Particle> particles, double timeFrame) {
        for (int j = 0; j < particles.size() - 1; j++) {
            Particle particle1 = particles.get(j);
            for (int k = j + 1; k < particles.size(); k++) {
                Particle particle2 = particles.get(k);
                adjustPairVelocities(particle1, particle2, timeFrame);
            }
        }
    }

    private void adjustPairVelocities(Particle particle1, Particle particle2, double timeFrame) {
        double xDistance;
        double yDistance;
        double distance;

        double force;
        double xForce;
        double yForce;

        final double G = 6.6738480e-11; // gravity constant

        // calculate distance
        xDistance = particle2.xPosition - particle1.xPosition;
        yDistance = particle2.yPosition - particle1.yPosition;
        distance = Math.pow(xDistance * xDistance + yDistance * yDistance, 0.5);

        // calculate attraction force
        force = (G * particle1.mass * particle2.mass) / (distance * distance);

        // projections of the force
        xForce = force * xDistance / distance; // cosine
        yForce = force * yDistance / distance; // sine

        // adjust velocity
        particle1.xVelocity += xForce * timeFrame / particle1.mass;
        particle1.yVelocity += yForce * timeFrame / particle1.mass;
        particle2.xVelocity -= xForce * timeFrame / particle2.mass;
        particle2.yVelocity -= yForce * timeFrame / particle2.mass;
    }

     private void adjustPositions(ArrayList<Particle> particles, double timeFrame) {
         for (Particle particle : particles) {
             particle.xPosition = particle.xPosition + particle.xVelocity * timeFrame;
             particle.yPosition = particle.yPosition + particle.yVelocity * timeFrame;
         }
     }

    public void run(int[][] xCoord, int[][] yCoord) {
        final ArrayList<Particle> particles = new ArrayList<>();
        final double timeFrame = 50000.0; // sec
        final int steps = xCoord.length;
        final int numberOfParticles = xCoord[0].length;

        // initialize set of particles
        for (int i = 0; i < numberOfParticles - 1; i++) {
            particles.add(new Particle(i, 200.0,1 * 0.000005, 1 * 0.00001, 1 * -250.0, i * 5.0 - 10));
        }
        particles.add(new Particle(numberOfParticles, 1000.0,0 * -0.000002, 1 * -0.000002, 1 * -50.0, 100 * 1.0));

        // simulation
        for (int i = 0; i < steps; i++) {
            adjustVelocities(particles, timeFrame);
            adjustPositions(particles, timeFrame);

            // output
            for (int j = 0; j < numberOfParticles; j++) {
                xCoord[i][j] = particles.get(j).xPosition.intValue();
                yCoord[i][j] = particles.get(j).yPosition.intValue();
            }
        }
    }
}