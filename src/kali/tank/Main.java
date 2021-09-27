package kali.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        while(true) {
            Thread.sleep(10);
            frame.repaint();
        }
    }
}