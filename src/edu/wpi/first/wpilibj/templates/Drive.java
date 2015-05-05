//This 
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class Drive {

    //drive motors
    public static Victor leftFront = new Victor(4);
    public static Victor rightFront = new Victor(2);
    public static Victor leftBack = new Victor(3);
    public static Victor rightBack = new Victor(1);
    
    public static Victor deck = new Victor(8);
    public static Victor shooter = new Victor(5);
    
    private static RobotDrive robotDrive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
    
    public static void doDrive(){
        
        boolean slow = Joysticks.driver.getRawButton(5) || Joysticks.driver.getRawButton(6);
        double rotation = Joysticks.driver.getRawAxis(4); //rotation angle
        double magnitude = Joysticks.driver.getMagnitude(); //rotation magnitude
        double yDirection = Joysticks.driver.getY();
        magnitude = magnitude * yDirection;
        
        //check if slow button is pressed
        if(slow){
            magnitude *= 0.5;
        }
        
        //joystick dead zone
        if (magnitude < .1) {
            magnitude = 0.;
        }
        
        
        
    }
    
}
