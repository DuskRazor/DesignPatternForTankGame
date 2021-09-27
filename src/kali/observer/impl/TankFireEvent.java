package kali.observer.impl;

import kali.observer.Event;
import kali.tank.Tank;

public class TankFireEvent extends Event<Tank> {
    private Tank tank;

    public TankFireEvent(Tank tank){
        this.tank = tank;
    }

    @Override
    public Tank getSource() {
        return tank;
    }
}
