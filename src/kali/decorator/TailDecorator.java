package kali.decorator;

import kali.tank.GameObject;

import java.awt.*;

public class TailDecorator extends GoDecorator {

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(go.getX(),go.getY(),go.getX() + getWIDTH(),go.getY() + getHEIGHT());
        g.setColor(color);
    }

    @Override
    public int getWIDTH() {
        return go.getWIDTH();
    }

    @Override
    public int getHEIGHT() {
        return go.getHEIGHT();
    }
}
