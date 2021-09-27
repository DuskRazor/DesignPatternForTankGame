package kali.observer;

import kali.observer.impl.TankFireEvent;

import java.io.Serializable;

public interface TankFireListener extends Serializable {
    void fireAction(TankFireEvent event);
}
