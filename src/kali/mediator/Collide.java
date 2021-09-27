package kali.mediator;

import kali.tank.GameObject;

/**
 * 可以理解为mediator，加入的所有实体物打交道只需要和Collide就可以
 */
public interface Collide {
    void collide(GameObject o1, GameObject o2);
}
