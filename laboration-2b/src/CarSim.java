public class CarSim {
    public static final int frameWidth = 800;
    public static final int frameHeight = 800;

    public static void main(String[] args) {
        CarModel cm = new CarModel();
        DrawPanel dp = new DrawPanel(frameWidth, frameHeight - 280);
        SpeedPanel sp = new SpeedPanel(150, 240);
        CarController cc = new CarController("CarSim 1.0", cm, dp, sp);
        cm.addObserver(sp);
        cm.addObserver(dp);
    }
}
