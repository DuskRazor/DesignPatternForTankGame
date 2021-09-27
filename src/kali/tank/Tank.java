package kali.tank;

import kali.observer.impl.TankFireEvent;
import kali.observer.TankFireListener;
import kali.observer.impl.TankFireListenerHandler;
import kali.strategy.DefaultFireStrategy;
import kali.strategy.FireStrategy;
import kali.strategy.FourDirFireStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Tank extends GameObject{
    private Grp grp;
    private Direction dir;
    private Random random = new Random();
    //观察者
    private List<TankFireListener> listeners = new ArrayList<>();

    private int oldX;
    private int oldY;

    private boolean moving = true;
    private boolean living = true;

    private static final int SPEED = 2;

    //主战坦克与敌方坦克开火采用不同的策略
    private FireStrategy singleFire = new DefaultFireStrategy();
    private FireStrategy allSidesFire = new FourDirFireStrategy();

    public static final int WIDTH = ResourceMgr.goodTankR.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankR.getHeight();

    public Tank(int x, int y, Direction dir, Grp grp) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.grp = grp;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        listeners.add(new TankFireListenerHandler());

        GameModel.getInstance().add(this);
    }

    //添加观察者
    /*public void addTankListener(TankFireListener l){
        this.listeners.add(l);
    }*/

    //观察者执行相应操作
    public void handleFireKey(){
        TankFireEvent event = new TankFireEvent(this);

        for(TankFireListener l : listeners){
            l.fireAction(event);
        }
    }

    @Override
    public void paint(Graphics g){
        if(!living){
            GameModel.getInstance().remove(this);
        }

        switch (dir){
            case UP     : g.drawImage(this.grp == Grp.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);break;
            case DOWN   : g.drawImage(this.grp == Grp.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);break;
            case LEFT   : g.drawImage(this.grp == Grp.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);break;
            case RIGHT  : g.drawImage(this.grp == Grp.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);break;
        }

        move();

        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    //可以写到比较器中  解耦
    @Override
    public void colliedWith(GameObject object) {
        if(this.rectangle.intersects(object.rectangle)){
            this.back();
            object.back();
        }
    }

    public void back(){
        x = oldX;
        y = oldY;
    }

    private void move() {
        oldX = x;
        oldY = y;

        if(!moving){
            return;
        }
        switch (this.dir){
            case LEFT   :   x-=SPEED;break;
            case RIGHT  :   x+=SPEED;break;
            case UP     :   y-=SPEED;break;
            case DOWN   :   y+=SPEED;break;
            default:break;
        }

        if(random.nextInt(100) > 97 && this.grp == Grp.BAD){
            this.handleFireKey();
        }

        //每移动一下改变方向
        if(this.grp == Grp.BAD && random.nextInt(100) > 97){
            randomDirection();
        }

        //移动之后进行边界检测
        boundsCheck();
    }

    public void boundsCheck(){
        if(this.x < 0) this.x = 0;
        if(this.x > TankFrame.GAME_WIDTH - WIDTH) this.x = TankFrame.GAME_WIDTH - WIDTH;
        if(this.y < 40) this.y = 40;
        if(this.y > TankFrame.GAME_HEIGHT - HEIGHT) this.y = TankFrame.GAME_HEIGHT - HEIGHT;
    }

    public void randomDirection(){
        this.dir = Direction.values()[random.nextInt(4)];
    }

    public void fire() {
        if(grp == Grp.GOOD){
            allSidesFire.fire(this);
        }else{
            singleFire.fire(this);
        }
    }

    public void die() {
        this.living = false;
        //GameModel.productionOneTank();
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Grp getGrp() {
        return grp;
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
