package kali.mediator.impl;

import kali.mediator.Collide;
import kali.tank.Bullet;
import kali.tank.GameObject;
import kali.tank.Tank;

public class BulletTankImpacter implements Collide {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank)o2;
            bullet.colliedWith(tank);
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2,o1);
        }
    }
}
