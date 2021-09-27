package kali.decorator;

import kali.tank.GameObject;

import java.awt.*;

public class RecDecorator extends GoDecorator {

    public RecDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(go.getX(),go.getY(),go.getWIDTH(),go.getHEIGHT());
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
