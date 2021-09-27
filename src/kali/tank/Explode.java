package kali.tank;

import java.awt.*;

public class Explode extends GameObject{
    private int step;
    static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        new Thread(() -> {
            (new Audio("audio/explode.wav")).play();
        }).start();

        GameModel.getInstance().add(this);
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],this.x,this.y,null);
        //爆炸结束直接移除
        if(step >= ResourceMgr.explodes.length){
            GameModel.getInstance().remove(this);
        }
    }

    @Override
    public void colliedWith(GameObject object) {
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

    @Override
    protected void die() {

    }

    @Override
    protected Grp getGrp() {
        return null;
    }

    @Override
    protected void back() {

    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
