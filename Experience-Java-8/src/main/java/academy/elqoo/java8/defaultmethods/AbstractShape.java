package academy.elqoo.java8.defaultmethods;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AbstractShape implements Shape, NamedObject {

    private int xPos = 0;

    private int yPos = 0;

    @Override
    public int getXPos() {
        return xPos;
    }

    @Override
    public int getYPos() {
        return yPos;
    }

    @Override
    public void setXPos(int xPOs) {
        this.xPos = xPOs;
    }

    @Override
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    @Override
    public String getName() {
        return "Abstract Shape";
    }

    @Override
    public void move(int xPOs, int yPOs) {
        setXPos(10);
        setYPos(10);

    }

    @Override
    public void notImplementedMethod() {
        throw new NotImplementedException();
    }
}
