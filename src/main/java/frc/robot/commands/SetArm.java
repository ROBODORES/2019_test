/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class SetArm extends Command {
  public SetArm() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_arm.setArm(OI.sideStick.getY()*0.3);
    if (OI.sideStick.getRawButton(1)) {
      Robot.m_arm.setIntake(1.0);
    } else if (OI.sideStick.getRawButton(2)) {
      Robot.m_arm.setIntake(-0.8);
    } else {
      Robot.m_arm.setIntake(0.0);
    }
    if (OI.sideStick.getRawButton(4)) {
      Robot.m_arm.setWrist(0.2);
    } else if (OI.sideStick.getRawButton(6)) {
      Robot.m_arm.setWrist(-0.2);
    } else {
      Robot.m_arm.setWrist(0.0);
    }

    Robot.m_arm.setHatchGrabber(OI.sideStick.getRawButton(3));
    //System.out.println(Robot.m_arm.getHESensor());
    Robot.m_arm.print();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_arm.setArm(0.0);
    Robot.m_arm.setWrist(0.0);
    Robot.m_arm.setIntake(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
