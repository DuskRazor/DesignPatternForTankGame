package kali.mediator.impl;

import kali.mediator.Collide;
import kali.tank.GameObject;
import kali.tank.Tank;
import kali.tank.Wall;

public class TankWallImpacter implements Collide {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank tank = (Tank) o1;
            Wall wall = (Wall)o2;
            tank.colliedWith(wall);
        }else if(o1 instanceof Wall && o2 instanceof Tank) {
            Wall wall = (Wall) o1;
            Tank tank = (Tank) o2;
            collide(tank, wall);
        }
    }
}
