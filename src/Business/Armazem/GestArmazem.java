package Business.Armazem;

import Data.RobotDAO;

import java.util.Map;

public class GestArmazem {
    private Map<String, Robot> robots;

    public GestArmazem() {
        this.robots = RobotDAO.getInstance();
    }

    public void addRobot(String robotID) {
        robots.put(robotID,new Robot(robotID));
    }

    public void remRobot(String robotID) {
        robots.remove(robotID);
    }

    public void getRobots(){
        System.out.println(robots.values().toString());
    }
}
