package kali.observer.impl;

import kali.observer.TankFireListener;
import kali.tank.Tank;

public class TankFireListenerHandler implements TankFireListener {
    @Override
    public void fireAction(TankFireEvent event) {
        Tank t = event.getSource();
        t.fire();
    }
}
