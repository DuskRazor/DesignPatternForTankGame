attention：zdata是实现存档和读盘功能时将对象序列化后存放数据的文件。以下内容是写代码时的一些思考和想要添加的功能（目的：加入设计模式）

操作：上下左右控制主坦克方向，空格开火，S键存档，L键读档。

让游戏具有存盘功能
对象序列化过程需要实现serializable接口
transient修饰的在序列化过程中透明化

//当然在序列化过程中可以直接把GameModel序列化，再把GameModel都进来，绕一圈
的目的是为了演示，先写哪个就先读哪个
1.采用facade模式提取GameModel，将所有模型内聚起来，TankFrame只处理
显示窗口
2.对于没有加入Mediator模式时，所有加入的物体都需要互相之间做碰撞检测，
采用调停者模式之后，所有物体之间的各种碰撞动作都归于Impacter中处理
3.采用责任链模式处理各个Impacter，返回类型为boolean，我没有做返回值处理
简单的归为void了。
4.问题：new GameModel需要new Tank，new Tank，静态语句块解决
5.学会在构造方法中添加自己，即add(this)

版本5添加装饰器，满足需求：
    1.坦克加一个外壳
    2.想加一个血条
    3.加一个尾巴
    4.子弹加尾巴
    5.子弹加外壳
    ......
Think? 如果继承BloodTank/TailTank/RectTank/BloodTailTank/BloodRecTank/TailBullet
    不灵活，装饰和被装饰者之间耦合度太高
装饰器：暂且给子弹添加装饰，装饰器中聚合GameObject,目的就是在原有逻辑上下添加另外的逻辑

在版本5之上添加观察者模式：observer/listener/callback/hook function，对坦克开火这个
行为视为一个事件。
