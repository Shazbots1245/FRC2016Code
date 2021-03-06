package org.usfirst.frc.team1245.robot;

import org.usfirst.frc.team1245.robot.commands.Elevate;
import org.usfirst.frc.team1245.robot.commands.MoveRelayArm;
import org.usfirst.frc.team1245.robot.commands.StopElevate;
import org.usfirst.frc.team1245.robot.commands.StopRelayArm;
import org.usfirst.frc.team1245.robot.commands.ToggleExtendArm;
import org.usfirst.frc.team1245.robot.commands.ToggleLeverArmHeight;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public static Joystick driverJoystick;
	public static Joystick secondaryJoystick;
	
	public Button moveLeverArmUpButton;
	public Button moveLeverArmDownButton;
	public Button elevateLeverArmButton;
	
	public Button movePulleyArmUpButton;
	public Button movePulleyArmDownButton;
	
	public Button elevateButton;
	public Button descendButton;
	public Button toggleExtendArmButton;
	
	public OI(){
		driverJoystick = new Joystick(0);
		secondaryJoystick = new Joystick(1);

		moveLeverArmUpButton = new JoystickButton(secondaryJoystick, RobotMap.moveLeverArmUpButton);
		moveLeverArmUpButton.whenPressed(new MoveRelayArm(Value.kForward, Robot.leverArm));
		moveLeverArmUpButton.whenReleased(new StopRelayArm(Robot.leverArm));
		
		moveLeverArmDownButton = new JoystickButton(secondaryJoystick, RobotMap.moveLeverArmDownButton);
		moveLeverArmDownButton.whenPressed(new MoveRelayArm(Value.kReverse, Robot.leverArm));
		moveLeverArmDownButton.whenReleased(new StopRelayArm(Robot.leverArm));
		
		elevateLeverArmButton = new JoystickButton(secondaryJoystick, RobotMap.elevateLeverArmButton);
		elevateLeverArmButton.whenReleased(new ToggleLeverArmHeight());
		
		movePulleyArmUpButton = new JoystickButton(secondaryJoystick, RobotMap.movePulleyArmUpButton);
		movePulleyArmUpButton.whenPressed(new MoveRelayArm(Value.kForward, Robot.pulleyArm));
		movePulleyArmUpButton.whenReleased(new StopRelayArm(Robot.pulleyArm));
		
		movePulleyArmDownButton = new JoystickButton(secondaryJoystick, RobotMap.movePulleyArmDownButton);
		movePulleyArmDownButton.whenPressed(new MoveRelayArm(Value.kReverse, Robot.pulleyArm));
		movePulleyArmDownButton.whenReleased(new StopRelayArm(Robot.pulleyArm));
		
		toggleExtendArmButton = new JoystickButton(secondaryJoystick, RobotMap.toggleExtendArmButton);
		toggleExtendArmButton.whenReleased(new ToggleExtendArm());
		
		elevateButton = new JoystickButton(secondaryJoystick, RobotMap.toggleExtendArmButton);
		elevateButton.whenPressed(new Elevate(RobotMap.elevateMagnitude));
		elevateButton.whenReleased(new StopElevate());
		
		descendButton = new JoystickButton(secondaryJoystick, RobotMap.descendButton);
		descendButton.whenPressed(new Elevate(-RobotMap.elevateMagnitude));
		descendButton.whenReleased(new StopElevate());
	}
	
	//Deadzone
	public static double deadZone(double val, double deadZone) {
		// Return a new percentage based on the living zone
	    if(Math.abs(val) > deadZone) {
			if(val > 0) {
				return (val - deadZone) / (1 - deadZone);
			} else {
				return -(-val - deadZone) / (1 - deadZone);
			}
		}
		return 0;
	}
}

