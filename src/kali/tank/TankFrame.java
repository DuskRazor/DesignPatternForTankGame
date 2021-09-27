package kali.tank;

import java.awt.*;
import java.awt.event.*;

/**
 * 单纯的作为VIEW
 *
 * 根据摁键的状态改变坦克的方向，根据坦克的方向进行坦克的移动
 */
public class TankFrame extends Frame {
    private Image offScreenImage;
    private static final int L_X = 500;
    private static final int L_Y = 120;
    static final int GAME_WIDTH = 1200;
    static final int GAME_HEIGHT = 800;

    private static GameModel model = GameModel.getInstance();

    public TankFrame(){
        //this.myTank.setMoving(false);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Tank");
        this.setLocation(L_X,L_Y);
        this.addKeyListener(new MyKeyListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void update(Graphics g) {//update方法在paint之前调用    双缓存解决闪烁问题
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        model.paint(g);
    }

    static class MyKeyListener extends KeyAdapter {
        boolean bU = false;
        boolean bD = false;
        boolean bL = false;
        boolean bR = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP     : bU = true;break;
                case KeyEvent.VK_DOWN   : bD = true;break;
                case KeyEvent.VK_LEFT   : bL = true;break;
                case KeyEvent.VK_RIGHT  : bR = true;break;
                case KeyEvent.VK_SPACE  : model.getMainTank().handleFireKey();break;
                //存盘
                case KeyEvent.VK_S      : model.save();break;
                //加载
                case KeyEvent.VK_L      : model.load();break;
                default:break;
            }
            setMainTankDirection();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP     : bU = false;break;
                case KeyEvent.VK_DOWN   : bD = false;break;
                case KeyEvent.VK_LEFT   : bL = false;break;
                case KeyEvent.VK_RIGHT  : bR = false;break;
                default:break;
            }
            setMainTankDirection();
        }

        public void setMainTankDirection(){
            model.getMainTank().setMoving(true);
            if(!bU && !bD && !bL && !bR){
                model.getMainTank().setMoving(false);
            }else{
                if(bU){
                    model.getMainTank().setDir(Direction.UP);
                }
                if(bD){
                    model.getMainTank().setDir(Direction.DOWN);
                }
                if(bL){
                    model.getMainTank().setDir(Direction.LEFT);
                }
                if(bR){
                    model.getMainTank().setDir(Direction.RIGHT);
                }
            }
        }
    }
}
