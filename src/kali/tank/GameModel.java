package kali.tank;

import kali.mediator.CollideChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 单纯的作为MODEL
 *
 * 门面模式：facade
 * 抽象出GameModel，将Model和view分离，同时，GameModel作为Facade，负责与Frame
 * 打交道，同时负责内部事务
 */
public class GameModel {
    private static final int ENEMIES = 5;
    private static Tank myTank;
    //二话不说 加载后INSTANCE就已经初始化了
    private static final GameModel INSTANCE = new GameModel();
    private List<GameObject> objects = new ArrayList<>();
    private CollideChain chain = new CollideChain();

    //private Collide bulletTankCollide = new BulletTankImpacter();
    //private Collide tankTankCollide = new TankTankImpacter();

    /*解决闭环问题：创建GameModel与myTank的死循环，利用静态语句块*/
    static {
        INSTANCE.init();
    }

    private GameModel(){
        add(new Wall(150,150,200,60));
        add(new Wall(550,150,200,60));
        add(new Wall(300,300,60,200));
        add(new Wall(550,300,60,200));
    }

    private void init(){
        initTank();
        myTank = new Tank(200,200,Direction.RIGHT,Grp.GOOD);
        myTank.setMoving(false);
    }

    public void paint(Graphics g){
        myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //进行碰撞检测
        for(int i = 0; i < objects.size(); i++){
            for(int j = i+1; j < objects.size(); j++){
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1,o2);
                //bulletTankCollide.collide(objects.get(i),objects.get(j));
                //tankTankCollide.collide(objects.get(i),objects.get(j));
            }
        }
    }

    private static void initTank(){
        for (int i = 0; i < ENEMIES; i++) {
            productionOneTank();
        }
    }

    private static void productionOneTank(){
        //坦克产生的随即坐标
        Random random = new Random();
        int x = random.nextInt(TankFrame.GAME_WIDTH/2 - Tank.WIDTH) + TankFrame.GAME_WIDTH/2;
        int y = random.nextInt(TankFrame.GAME_HEIGHT - Tank.HEIGHT);
        new Tank(x,y,Direction.DOWN,Grp.BAD);
    }

    //存盘、快照、解析模式：记录快照(瞬时状态) 存盘。记录对象的某个瞬间
    public void save() {
        File file = new File("D:/IDEA_workspace/TankGame/V7TankAddSnap/src/zdata/snap.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //读盘
    public void load() {
        File file = new File("D:/IDEA_workspace/TankGame/V7TankAddSnap/src/zdata/snap.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            myTank = (Tank) ois.readObject();
            objects = (List<GameObject>)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static GameModel getInstance(){
        return INSTANCE;
    }

    public Tank getMainTank() {
        return myTank;
    }

    public void add(GameObject object){
        objects.add(object);
    }

    public void remove(GameObject object){
        objects.remove(object);
    }
}
