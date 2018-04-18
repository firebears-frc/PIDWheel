// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.firebears;

import java.io.File;

import org.firebears.commands.shooter.SpinShooterWheelsCommand;
import org.firebears.subsystems.AutoSelection;
import org.firebears.subsystems.Chassis;
import org.firebears.subsystems.Grabber;
import org.firebears.subsystems.Lights;
import org.firebears.subsystems.Shooter;
import org.firebears.subsystems.Vision;
import org.firebears.util.RobotReport;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.UsbCameraInfo;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.cscore.VideoEvent;
import edu.wpi.cscore.VideoException;
import edu.wpi.cscore.VideoProperty;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	Command selectedAuto;

	public static OI oi;
	public static Vision vision;
	public static AutoSelection autoSelection;
	public static Chassis chassis;
	public static Lights lights;
	public static Shooter shooter;
	public static Grabber grabber;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotReport report = new RobotReport("FB2018");
		RobotMap.init(report);
		chassis = new Chassis();
		vision = new Vision();
		lights = new Lights();
		shooter = new Shooter();
		grabber = new Grabber();
		autoSelection = new AutoSelection();
		lights.reset();

//		CameraServer.getInstance().startAutomaticCapture(0);

		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI(report);

		report.write(new File(System.getProperty("user.home"), "robotReport.md"));
	}

	/**
	 * This function is called when the disabled button is hit. You can use it to
	 * reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {
	    lights.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("NavX Angle", RobotMap.getNavXAngle());
	}

	@Override
	public void autonomousInit() {
	    lights.reset();
//		// System.out.println("joytickbutton1: " + oi.joystick1.getRawButton(1));
//		// System.out.println("joytickYAxis: "+ oi.joystick1.getRawAxis(1));
//		// System.out.println("joytickXAxis: "+ oi.joystick2.getX());
//		boolean leftSide = oi.joystick2.getRawButton(18);
//		boolean rightSide = oi.joystick2.getRawButton(17);
//		boolean scaleBool = oi.joystick2.getRawButton(15);
//		boolean crossBool = oi.joystick2.getRawButton(16);
//
//		if (leftSide == true && rightSide == false) {
//			RobotMap.side = "Left";
//		} else if (leftSide == false && rightSide == true) {
//			RobotMap.side = "Right";
//		} else if (leftSide == false && rightSide == false) {
//			RobotMap.side = "Middle";
//		}
//
//		if (scaleBool == false) {
//			RobotMap.priority = "Switch";
//		} else if (scaleBool == true) {
//			RobotMap.priority = "Scale";
//		}
//		
//		if (crossBool == true) {
//			RobotMap.shouldCross = true;
//		} else if (crossBool == false) {
//			RobotMap.shouldCross = false;
//		}
//
//		SmartDashboard.putString("Side", RobotMap.side);
//		SmartDashboard.putString("Priority", RobotMap.priority);
//		SmartDashboard.putBoolean("Cross", RobotMap.shouldCross);
		
		Robot.grabber.grabberRaise();
		Robot.grabber.grabberClose();
		RobotMap.compressor.stop();
		
		selectedAuto = autoSelection.getAuto();

		System.out.println("selectedAuto = " + selectedAuto);
		if (selectedAuto != null) {
			selectedAuto.start();
		}

		// String gameData;
		// gameData = DriverStation.getInstance().getGameSpecificMessage();
		// System.out.println(gameData);

		// autonomousCommand = chooser.getSelected();
		// // schedule the autonomous command (example)
		// if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

	}

	@Override
	public void teleopInit() {
	    lights.reset();
		RobotMap.DisableShooter = false;
		RobotMap.DisableDrive = false;
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (selectedAuto != null) {
			selectedAuto.cancel();
		}
		
		RobotMap.compressor.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("NavX Angle", RobotMap.boundAngle(RobotMap.getNavXAngle()));
		SmartDashboard.putBoolean("GrabberUp", !RobotMap.grabberUpPositionSensor.get());
		SmartDashboard.putBoolean("GrabberDown", !RobotMap.grabberDownPositionSensor.get());
		
		if (RobotMap.DEBUG) {
			// Put Encoder values
			SmartDashboard.putNumber("Left Master Encoder Distance",
					RobotMap.chassisLeftMaster.getSelectedSensorPosition(RobotMap.PID_IDX));
			SmartDashboard.putNumber("Left Master Encoder Rate",
					RobotMap.chassisLeftMaster.getSelectedSensorVelocity(RobotMap.PID_IDX));

			double inchesPerSecond = RobotMap.chassisLeftMaster.getSelectedSensorVelocity(RobotMap.PID_IDX) * 10 / 52.6;
			double metersPerSecond = inchesPerSecond / 39.37;

			SmartDashboard.putNumber("LeftMaster Inches per Second", inchesPerSecond);
			SmartDashboard.putNumber("LeftMaster Meters per Second", metersPerSecond);

			SmartDashboard.putNumber("Right Master Encoder Distance",
					RobotMap.chassisRightMaster.getSelectedSensorPosition(RobotMap.PID_IDX));
			SmartDashboard.putNumber("Right Master Encoder Rate",
					RobotMap.chassisRightMaster.getSelectedSensorVelocity(RobotMap.PID_IDX));
			//
			// if (RobotMap.CLOSED_LOOP_DRIVING) {
			// SmartDashboard.putNumber("Left Master PID Error",
			// RobotMap.chassisLeftMaster.getClosedLoopError(RobotMap.PID_IDX));
			// SmartDashboard.putNumber("Right Master PID Error",
			// RobotMap.chassisRightMaster.getClosedLoopError(RobotMap.PID_IDX));
			// }

			SmartDashboard.putNumber("DistanceInInches", Robot.chassis.getChassisRangeFinderDistance());
			SmartDashboard.putNumber("Grabber Distance", Robot.grabber.getGrabberRangeFinderDistance());

			SmartDashboard.putBoolean("TapeSensor", Robot.chassis.isTapeBright());
			// System.out.println("Tape Sensor: " + RobotMap.tape.get());
			SmartDashboard.putBoolean("Cube in Grabber", Robot.grabber.hasCube());

			SmartDashboard.putNumber("Amps", RobotController.getInputCurrent());

			SmartDashboard.putNumber("Left Shooter Rate",
					RobotMap.leftLaunchSpinner.getSelectedSensorVelocity(RobotMap.PID_IDX));
			SmartDashboard.putNumber("Right Shooter Rate",
					RobotMap.rightLaunchSpinner.getSelectedSensorVelocity(RobotMap.PID_IDX));
			// SmartDashboard.putNumber("Left Shooter Rate",
			// Robot.shooter.leftSpinnerEncoder.pidGet());
			// SmartDashboard.putNumber("Right Shooter Rate",
			// Robot.shooter.rightSpinnerEncoder.pidGet());

			SmartDashboard.putNumber("Left Shooter Error", Robot.shooter.leftSpinner.getError());
			SmartDashboard.putNumber("Right Shooter Error", Robot.shooter.rightSpinner.getError());

			SmartDashboard.putBoolean("Closed_LOOP", RobotMap.CLOSED_LOOP_DRIVING);
			SmartDashboard.putString("ControlMode", RobotMap.chassisLeftMaster.getControlMode().toString());
			
			SmartDashboard.putString("Solenoid Pos", RobotMap.leftUpDown.get().toString());
			
			
//			SmartDashboard.putNumber("Camera FPS", CameraServer.getInstance().startAutomaticCapture(0).getActualFPS());
			SmartDashboard.putNumber("DegsPerSec", Robot.chassis.getDegreesPerSecond());


		}
	}
}