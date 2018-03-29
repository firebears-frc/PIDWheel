package org.firebears.commands.auto.movement;

import org.firebears.commands.DriveToDistanceStraightCommand;
import org.firebears.commands.RelativeAngleCommand;
import org.firebears.commands.VisionGetCubeCommandGroup;
import org.firebears.commands.driver.FireCubeCommand;
import org.firebears.commands.grabber.OpenGrabberCommand;
import org.firebears.commands.shooter.SpinShooterWheelsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftSideTwoCubeSplitCommand extends CommandGroup {

    public LeftSideTwoCubeSplitCommand() {
    	addSequential(new LeftSideLeftScaleCommand());
		addSequential(new SpinShooterWheelsCommand(0));
		addSequential(new RelativeAngleCommand(-80));
		addSequential(new VisionGetCubeCommandGroup());
		addSequential(new RelativeAngleCommand(180));
		addSequential(new SpinShooterWheelsCommand(.4));
		addSequential(new WaitCommand(.5));
		addSequential(new DriveToDistanceStraightCommand(36, -.5));
		
		// Remove if not necessary on competition bot
		addSequential(new OpenGrabberCommand(true));
		addSequential(new WaitCommand(.25));
		addSequential(new OpenGrabberCommand(false));
		addSequential(new WaitCommand(.25));
		
		addSequential(new FireCubeCommand());
    }
}