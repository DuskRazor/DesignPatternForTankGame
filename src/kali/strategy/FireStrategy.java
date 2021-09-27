package kali.strategy;

import kali.tank.Tank;

import java.io.Serializable;

/**
 * 开火使用策略模式
 * 让发射子弹采取不同的策略
 */
public interface FireStrategy extends Serializable {
    void fire(Tank tank);
}
