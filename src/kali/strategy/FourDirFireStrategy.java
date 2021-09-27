package kali.strategy;

import kali.tank.Bullet;
import kali.tank.Direction;
import kali.tank.Tank;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        Direction[] dirs = Direction.values();
        for(Direction dir : dirs){
            switch (dir){
                case UP     : new Bullet(tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2,tank.getY() - Bullet.HEIGHT,dir,tank.getGrp());break;
                case DOWN   : new Bullet(tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2,tank.getY() + Tank.HEIGHT,dir,tank.getGrp());break;
                case LEFT   : new Bullet(tank.getX() - Bullet.WIDTH,tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2,dir,tank.getGrp());break;
                case RIGHT  : new Bullet(tank.getX() + Tank.WIDTH,tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2,dir,tank.getGrp());break;
                default:break;
            }
        }
    }
}
