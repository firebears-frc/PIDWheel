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

	public Joystick joystick1;
	public Joystick joystick2;
	public JoystickButton testPID;

	public OI() {
		// Intialize the joysticks
		joystick1 = new Joystick(0);
		joystick2 = new Joystick(1);

		// Joystick Buttons
		
		// Switch between Open and Closed Loop Driving
		// testPID = new JoystickButton(joystick1, 1);
		// testPID.whenPressed(new SwitchDrivingType());

		// SmartDashboard Buttons
		SmartDashboard.putData("Start Recording", new StartRecordingCommand());
		SmartDashboard.putData("Stop Recording", new StopRecordingCommand());
		SmartDashboard.putData("Play Recording", new PlayRecordingCommand());
		SmartDashboard.putData("Play Recording Mirrored", new PlayMirroredRecording());
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
		
	}

	public Joystick getJoystick1() {
		return joystick1;
	}

}
