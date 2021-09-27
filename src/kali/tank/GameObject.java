package kali.tank;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {

    int x;
    int y;

    Rectangle rectangle = new Rectangle();

    protected abstract void back();

    public abstract void paint(Graphics g);

    public abstract void colliedWith(GameObject object);

    public abstract int getX();

    public abstract int getY();

    protected abstract void die();

    protected abstract Grp getGrp();

    public abstract int getWIDTH();

    public abstract int getHEIGHT();
}
