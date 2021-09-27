package kali.tank;

import java.awt.*;

public class Wall extends GameObject {
    private int width, height;

    public Wall(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = this.width;
        rectangle.height = this.height;
    }
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y, width, height);
        g.setColor(c);
    }

    //可以写到比较器中  解耦
    @Override
    public void colliedWith(GameObject bullet) {
        if(this.rectangle.intersects(bullet.rectangle)){
            bullet.die();
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    protected void die() {
        //DO NOTHING
    }

    @Override
    protected Grp getGrp() {
        return null;
    }

    @Override
    protected void back() {
        //DO NOTHING
    }

    @Override
    public int getHEIGHT() {
        return height;
    }

    @Override
    public int getWIDTH(){
        return width;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
