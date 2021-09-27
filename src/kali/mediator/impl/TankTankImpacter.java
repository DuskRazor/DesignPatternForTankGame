package kali.mediator.impl;

import kali.mediator.Collide;
import kali.tank.GameObject;
import kali.tank.Tank;

public class TankTankImpacter implements Collide {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;
            t1.colliedWith(t2);
        }
    }
}
