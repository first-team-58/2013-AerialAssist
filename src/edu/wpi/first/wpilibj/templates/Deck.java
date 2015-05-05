//This class controls everything involving the deck of Sam.
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStation;

public class Deck 
{
    //driverstation initialization
    public static DriverStation driverStation = DriverStation.getInstance();
    
    //names all of the motors and relays on the robot, globaly
    public static Victor shooterMotor = new Victor(5); //The shooter motor is connected to PWM 5
    public static Victor deckMotor = new Victor(8); //The deck angle motor is connected to PWM 8
    public static Relay regularFire = new Relay(5); //The relay to fire the piston for rightside up frisbees is 5
    public static Relay upsideDownFire = new Relay(6); //The relay to fire the piston for upside down frisbees is 6
    public static AnalogChannel deckAngle = new AnalogChannel(1); //The analog channel that tells us what the deck angle is is 1

    //presets for the deck limits
    private static double lowerDeckLimit = 1;
    private static double upperDeckLimit = 4;
    private static double shooterSpeed = .5;
    private static double analogInitial;
    //run once boolean
    private static boolean runOnce = true;
            
    //reports the angle of the deck as a double
    public static double getAngle()
    {
        double bridgeAngle = deckAngle.getAverageVoltage();
        return bridgeAngle;
    }
    //This is run in ShowBot2013 under the teleopPeriodic function
    public static void runDeck()
    {
        //This statement is run the first time runDeck is called
        if(runOnce)
        {
           analogInitial = driverStation.getAnalogIn(1);
           runOnce = false; 
        }
        
        //This sets the adjustable speed selection
        if(driverStation.getAnalogIn(1) != analogInitial)
        {
            shooterSpeed = driverStation.getAnalogIn(1)/5;
        }
        
        //Fires the regular piston
        if(Joysticks.operator.getRawButton(7) && (Joysticks.operator.getRawButton(2) || Joysticks.operator.getRawButton(3)))
        {
            regularFire.set(Relay.Value.kOn);
        } else {
            regularFire.set(Relay.Value.kOff);
        }
        //Fires the upside down piston
        if(Joysticks.operator.getRawButton(8) && (Joysticks.operator.getRawButton(2) || Joysticks.operator.getRawButton(3)))
        {
            upsideDownFire.set(Relay.Value.kOn);
        } else {
            upsideDownFire.set(Relay.Value.kOff);
        }
        //spins the shooter motor at full power
        if(Joysticks.operator.getRawButton(6))
        {
            shooterMotor.set(1);
        }
        //spins the shooter motor at half power
        if(Joysticks.operator.getRawButton(3))
        {
             shooterMotor.set(.5);
        }
        //spins the shooter motor at the adjustable power
        if(Joysticks.operator.getRawButton(2))
        {
            shooterMotor.set(shooterSpeed);
        }
        //controls the deck angle in the lower direction
        if(Joysticks.operator.getRawAxis(6) < -.75 || getAngle()>lowerDeckLimit)
        {
            deckMotor.set(-.5);
        }
        //controls the deck angle in the upper direction
        if(Joysticks.operator.getRawAxis(6) > .75 || getAngle()<upperDeckLimit)
        {
            deckMotor.set(.5);
        }
            
    }

}
