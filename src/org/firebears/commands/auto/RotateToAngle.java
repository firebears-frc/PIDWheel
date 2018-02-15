// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.firebears.commands.auto;

import edu.wpi.first.wpilibj.command.PIDCommand;

import static org.firebears.RobotMap.boundAngle;
import static org.firebears.RobotMap.getNavXAngle;

import org.firebears.Robot;

/**
 * Turns to angle based on previous angle
 */
public class RotateToAngle extends PIDCommand {

	private double m_angle;
	double targetAngle;
	private static final double ANGLE_TOLERANCE = 1.0;

	public RotateToAngle(double angle) {
		super("RotateCommand", 0.2, 0.0, 0.0);
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(ANGLE_TOLERANCE);
		m_angle = angle;
		requires(Robot.chassis);
	}

	private double getAngleDifference() {
		return boundAngle(getNavXAngle() - targetAngle);
	}

	@Override
	protected double returnPIDInput() {
		return getAngleDifference();
	}

	protected void usePIDOutput(double output) {
		Robot.chassis.drive(0.0, output, false);
	}

	protected void initialize() {
		targetAngle = boundAngle(getNavXAngle() + m_angle);
		getPIDController().setSetpoint(0); 
		System.out.println("Begin RotateToAngle(" +m_angle + ")");
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Math.abs(getAngleDifference()) < ANGLE_TOLERANCE;
	}

	protected void end() {
		Robot.chassis.stop();
	}

	protected void interrupted() {
		end();
	}
}
