/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.SetArm;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  VictorSPX armMotor = null;
  TalonSRX wristMotor = null;
  VictorSPX intakeMotor = null;
  Solenoid hatchSolenoid = null;
  DigitalInput hallEffect = null;
  Encoder armEncoder = null;

  public Arm() {
    armMotor = new VictorSPX(RobotMap.armMotor);
    wristMotor = new TalonSRX(RobotMap.wristMotor);
    intakeMotor = new VictorSPX(RobotMap.armIntakeMotor);
    hatchSolenoid = new Solenoid(RobotMap.pcm_id, RobotMap.hatchSolenoid);
    hallEffect = new DigitalInput(RobotMap.armHallEffect);
    armEncoder = new Encoder(RobotMap.armSourceA, RobotMap.armSourceB);
    armEncoder.setReverseDirection(true);
    armEncoder.setDistancePerPulse(90.0/595.75);
  }

  public void setIntake(double speed) {
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setArm(double speed) {
    armMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setWrist(double speed) {
    wristMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setHatchGrabber(boolean direction) {
    hatchSolenoid.set(direction);
  }

  public boolean getHESensor() {
    return hallEffect.get();
  }

  public void print() {
    double armEncoderVal = armEncoder.getDistance();
    System.out.println(armEncoderVal);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new SetArm());
  }
}
