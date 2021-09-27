package kali.decorator;

import kali.tank.GameObject;
import kali.tank.Grp;

import java.awt.*;

/**
 * 装饰器：暂且给子弹添加装饰，装饰器中聚合GameObject
 *
 * 目的就是在原有逻辑上下添加另外的逻辑
 */
public abstract class GoDecorator extends GameObject {
    GameObject go;

    public GoDecorator(GameObject go){
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g);

    @Override
    public void colliedWith(GameObject object) {

    }

    @Override
    public int getX() {
        return go.getX();
    }

    @Override
    public int getY() {
        return go.getY();
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

}
