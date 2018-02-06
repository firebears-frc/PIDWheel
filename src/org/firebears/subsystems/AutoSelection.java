// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.firebears.subsystems;

import org.firebears.RobotMap;
import org.firebears.commands.*;
import org.firebears.commands.auto.DriveToDistanceCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class AutoSelection extends Subsystem {
	
	final String LEFT = "Left";
	final String RIGHT = "Right";
	final String MIDDLE = "Middle";
	final String SWITCH = "Switch";
	final String SCALE = "Scale";
	final char R = 'R';
	final char L = 'L';

	String side;
	String priority;
	Boolean shouldCross;
	
	Command leftSideLeftScale = new DriveToDistanceCommand(1); // leftSideLeftScaleCommand
	Command leftSideRightScale = new DriveToDistanceCommand(1);; // leftSideRightScaleCommand
	Command leftSideLeftSwitch = new DriveToDistanceCommand(1);; // leftSideLeftSwitchCommand
	Command leftSideRightSwitch = new DriveToDistanceCommand(1);; // leftSideRightSwitchCommand
	Command rightSideLeftScale = new DriveToDistanceCommand(1);; // rightSideLeftScaleCommand
	Command rightSideRightScale = new DriveToDistanceCommand(1);; // rightSideRightScaleCommand
	Command rightSideLeftSwitch = new DriveToDistanceCommand(1);; // rightSideLeftSwitchCommand
	Command rightSideRightSwitch = new DriveToDistanceCommand(1);; // rightSideRightSwitchCommand
	Command middleSideLeftSwitch = new DriveToDistanceCommand(1);; // middleSideLeftSwitchCommand
	Command middleSideRightSwitch = new DriveToDistanceCommand(1);; // middleSideRightSwitchCommand
	Command bothSideCrossAuto = new DriveToDistanceCommand(1);; //bothSideCrossAutoCommand
	
	

	public Command getAuto() {
		
		Command selectedAuto = null;
		String side = RobotMap.side;
		System.out.println("Side: " + side);
		String priority = RobotMap.priority;
		System.out.println("Priority: " + priority);
		Boolean shouldCross = RobotMap.shouldCross;
		System.out.println("ShouldCross: " + shouldCross);
		String gameData = DriverStation.getInstance().getGameSpecificMessage();

		// to-do = replace println's with auto commands
		switch (side) {

		// Robot on left side
		case LEFT:
			switch (priority) {

			// Scale as priority
			case SCALE:
				switch (gameData.charAt(1)) {

				// Scale on left
				case L:
					// Runs when robot on left, scale on left (works)
					System.out.println("Select auto: left side, left scale");
					selectedAuto = leftSideLeftScale;
					break;

				// Scale on right
				case R:
					if (shouldCross) {
						// Runs when robot on left, scale on right and allowed to cross (works)
						System.out.println("Select auto: left side, right scale");
						selectedAuto = leftSideRightScale;
					} else {
						if (gameData.charAt(0) == L) {
							// Runs when robot on left, scale on right and cannot cross
							System.out.println("Select auto: left side, left switch");
							selectedAuto = leftSideLeftSwitch;
						} else {
							// Runs when robot on left, switch & scale on right and cannot cross
							System.out.println("Select auto: cross auto line");
							selectedAuto = bothSideCrossAuto;
						}
					}
					break;
				}
				break;

			// Switch as priority
			case SWITCH:
				switch (gameData.charAt(0)) {

				// Switch on left
				case L:
					// Runs when robot on left, switch on left
					System.out.println("Select auto: left side, left switch");
					selectedAuto = leftSideLeftSwitch;
					break;

				// Switch on right
				case R:
					if (shouldCross) {
						// Runs when robot on left, switch on right and allowed to cross
						System.out.println("Select auto: left side, right switch");
						selectedAuto = leftSideRightSwitch;
					} else {
						// Runs when robot on left, switch on right and cannot cross
						System.out.println("Select auto: cross auto line");
						selectedAuto = bothSideCrossAuto;
					}
					break;
				}
				break;
			}
			break;

		// Robot is middle
		case MIDDLE:
			switch (gameData.charAt(0)) {

			// Switch on left
			case L:
				// Runs when robot is middle, switch on left
				System.out.println("Select auto: middle, left switch");
				selectedAuto = middleSideLeftSwitch;
				break;

			// Switch on right
			case R:
				// Runs when robot is middle, switch on right
				System.out.println("Select auto: middle, right switch");
				selectedAuto = middleSideRightSwitch;
				break;
			}
			break;

		// Robot on right side
		case RIGHT:
			switch (priority) {

			// Scale as priority
			case SCALE:
				switch (gameData.charAt(1)) {

				// Scale on right
				case R:
					// Runs when robot on right, scale on right
					System.out.println("Select auto: right side, right scale");
					selectedAuto = rightSideRightScale;
					break;
					
				// Scale on left
				case L:
					if (shouldCross) {
						// Runs when robot on right, scale on left and allowed to cross
						System.out.println("Select auto: right side, left scale");
						selectedAuto = rightSideLeftScale;
					} else {
						if (gameData.charAt(0) == R) {
							// Runs when robot on right, scale on left and cannot cross
							System.out.println("Select auto: right side, right switch");
							selectedAuto = rightSideRightSwitch;
						} else {
							// Runs when robot on right, switch & scale on left and cannot cross
							System.out.println("Select auto: cross auto line");
							selectedAuto = bothSideCrossAuto;
						}
						
					}
					break;
				}
				break;

			// Switch as priority
			case SWITCH:
				
				switch (gameData.charAt(0)) {
				
				// Switch on right
				case R:
					// Runs when robot on right, switch on right
					System.out.println("Select auto: right side, right switch");
					selectedAuto = rightSideRightSwitch;
					break;

				// Switch on left
				case L:
					if (shouldCross) {
						// Runs when robot on right, switch on left and allowed to cross
						System.out.println("Select auto: right side, left switch");
						selectedAuto = rightSideLeftSwitch;
					} else {
						// Runs when robot on right, switch on left and not allowed to cross
						System.out.println("Select auto: cross auto line");
						selectedAuto = bothSideCrossAuto;
					}
					break;
				}
				break;
			}
			break;
		}
		return selectedAuto;
	}

	@Override
	public void initDefaultCommand() {
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop
	}
}
