package academy.elqoo.java8.defaultmethods;

import java.util.List;

public interface Shape  {




    static void moveXPosWith10(List<AbstractShape> shapes) {
        shapes.forEach(s -> s.setXPos(10));
    }

    int getXPos();

    int getYPos();

    void setXPos(int xPOs);

    void setYPos(int yPos);

    default String getName() {
        return "";
    }


    default void move(int xPOs, int yPOs) {
        setXPos(xPOs);
        setYPos(yPOs);
    }

    void notImplementedMethod();
}
