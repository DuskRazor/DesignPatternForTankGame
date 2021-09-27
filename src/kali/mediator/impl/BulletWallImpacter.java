package kali.mediator.impl;

import kali.mediator.Collide;
import kali.tank.Bullet;
import kali.tank.GameObject;
import kali.tank.Wall;

public class BulletWallImpacter implements Collide {
    @Override
        public void collide(GameObject o1, GameObject o2) {
            if(o1 instanceof Bullet && o2 instanceof Wall){
                Bullet bullet = (Bullet)o1;
                Wall wall = (Wall)o2;
                wall.colliedWith(bullet);
            }else if(o1 instanceof Wall && o2 instanceof Bullet){
            Wall wall = (Wall)o1;
            Bullet bullet = (Bullet)o2;
            collide(bullet,wall);
        }
    }
}
