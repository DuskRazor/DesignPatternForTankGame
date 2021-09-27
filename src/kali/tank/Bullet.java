package kali.tank;

import java.awt.*;

public class Bullet extends GameObject{
    private Grp grp;
    private Direction dir;
    private boolean living = true;

    private static final int SPEED = 5;
    public static final int WIDTH = ResourceMgr.bulletR.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletR.getHeight();
    //private GameModel model = GameModel.getInstance();

    public Bullet(int x, int y, Direction dir,Grp grp) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.grp = grp;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g){
        if(!living){
            GameModel.getInstance().remove(this);
        }

        switch (dir){
            case UP     : g.drawImage(ResourceMgr.bulletU,x,y,null);break;
            case DOWN   : g.drawImage(ResourceMgr.bulletD,x,y,null);break;
            case LEFT   : g.drawImage(ResourceMgr.bulletL,x,y,null);break;
            case RIGHT  : g.drawImage(ResourceMgr.bulletR,x,y,null);break;
        }

        move();

        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void move() {
        switch (dir){
            case LEFT   :   x-=SPEED;break;
            case RIGHT  :   x+=SPEED;break;
            case UP     :   y-=SPEED;break;
            case DOWN   :   y+=SPEED;break;
            default:break;
        }

        if(x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    //可以写到比较器中  解耦
    public void colliedWith(GameObject tank) {
        //如果同属于同一组不进行碰撞检测
        if(this.grp == tank.getGrp()){
            return;
        }

        if(this.rectangle.intersects(tank.rectangle)){
            this.die();
            tank.die();
            new Explode(tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2,
                    tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2);
        }
    }

    public void die() {
        this.living = false;
    }

    @Override
    protected Grp getGrp() {
        return this.grp;
    }

    @Override
    protected void back() {
        //DO NOTHING
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public void setGrp(Grp grp) {
        this.grp = grp;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
