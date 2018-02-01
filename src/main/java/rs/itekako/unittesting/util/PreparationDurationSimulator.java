package rs.itekako.unittesting.util;

public class PreparationDurationSimulator {

    public void simulateDuration(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
