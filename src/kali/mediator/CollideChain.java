package kali.mediator;

import kali.mediator.impl.BulletTankImpacter;
import kali.mediator.impl.BulletWallImpacter;
import kali.mediator.impl.TankTankImpacter;
import kali.mediator.impl.TankWallImpacter;
import kali.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用责任链模式贯穿各个碰撞器
 */
public class CollideChain {
    private List<Collide> collides = new LinkedList<>();

    public CollideChain(){
        add(new TankTankImpacter());
        add(new BulletTankImpacter());
        add(new BulletWallImpacter());
        add(new TankWallImpacter());
    }

    public void add(Collide c){
        collides.add(c);
    }

    public void collide(GameObject o1, GameObject o2) {
        for(int i = 0;i < collides.size();i++){
            collides.get(i).collide(o1,o2);
        }
    }
}
