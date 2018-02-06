// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

//
//test
package org.firebears;

import org.firebears.commands.auto.ChangePriority;
import org.firebears.commands.auto.ChangeShouldCross;
import org.firebears.commands.auto.ChangeSide;
import org.firebears.commands.auto.DriveToDistanceCommand;
import org.firebears.commands.auto.DriveToTapeCommand;
import org.firebears.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.firebears.subsystems.*;

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

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public Joystick joystick1;
	public Joystick joystick2;
	public JoystickButton testPID;
	public JoystickButton Driveinches;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public OI() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		joystick1 = new Joystick(0);
		joystick2 = new Joystick(1);
		
		Driveinches = new JoystickButton(joystick1,1);
		Driveinches.whileHeld(new DriveToDistanceCommand(18.8));

		// testPID = new JoystickButton(joystick1, 1);
		// testPID.whenPressed(new SwitchDrivingType());

		// SmartDashboard Buttons
		SmartDashboard.putData("Start Recording", new StartRecordingCommand());
		SmartDashboard.putData("Stop Recording", new StopRecordingCommand());
		SmartDashboard.putData("Play Recording", new PlayRecordingCommand());
		// Vision Commands
		SmartDashboard.putData("Vision Turn", new VisionRotateCommand());
		SmartDashboard.putData("Drive to Cube", new VisionForwardCommand());
		SmartDashboard.putData("Vision Get Cube", new VisionGetCubeCommandGroup());
		// Auto commands
		SmartDashboard.putData("Left side", new ChangeSide("Left"));
		SmartDashboard.putData("Middle", new ChangeSide("Middle"));
		SmartDashboard.putData("Right side", new ChangeSide("Right"));
		SmartDashboard.putData("Scale priority", new ChangePriority("Scale"));
		SmartDashboard.putData("Switch priority", new ChangePriority("Switch"));
		SmartDashboard.putData("Should cross", new ChangeShouldCross(true));
		SmartDashboard.putData("Shouldn't cross", new ChangeShouldCross(false));
		SmartDashboard.putData("DriveToTapeCommand", new DriveToTapeCommand(.4));
		SmartDashboard.putData("DriveInches", new DriveToDistanceCommand(18.8));
		
	}

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
	public Joystick getJoystick1() {
		return joystick1;
	}

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
