package kali.strategy;

import kali.tank.Bullet;
import kali.tank.Tank;

public class DefaultFireStrategy implements FireStrategy {
    /*@Override
    public void fire(Tank tank) {
        switch (tank.getDir()){
            case UP     : GameModel.getInstance().add(new RecDecorator(new TailDecorator(new Bullet(tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2,tank.getY() - Bullet.HEIGHT,tank.getDir(),tank.getGrp()))));break;
            case DOWN   : GameModel.getInstance().add(new RecDecorator(new TailDecorator(new Bullet(tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2,tank.getY() + Tank.HEIGHT,tank.getDir(),tank.getGrp()))));break;
            case LEFT   : GameModel.getInstance().add(new RecDecorator(new TailDecorator(new Bullet(tank.getX() - Bullet.WIDTH,tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2,tank.getDir(),tank.getGrp()))));break;
            case RIGHT  : GameModel.getInstance().add(new RecDecorator(new TailDecorator(new Bullet(tank.getX() + Tank.WIDTH,tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2,tank.getDir(),tank.getGrp()))));break;
            default:break;
        }
    }*/

    @Override
    public void fire(Tank tank) {
        switch (tank.getDir()){
            case UP     : new Bullet(tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2,tank.getY() - Bullet.HEIGHT,tank.getDir(),tank.getGrp());break;
            case DOWN   : new Bullet(tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2,tank.getY() + Tank.HEIGHT,tank.getDir(),tank.getGrp());break;
            case LEFT   : new Bullet(tank.getX() - Bullet.WIDTH,tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2,tank.getDir(),tank.getGrp());break;
            case RIGHT  : new Bullet(tank.getX() + Tank.WIDTH,tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2,tank.getDir(),tank.getGrp());break;
            default:break;
        }
    }


}
