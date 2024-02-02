// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax IntakeMotor1 = new CANSparkMax(Constants.intakeMotor1ID, MotorType.kBrushless); //assigns the motors and stuff
  CANSparkMax IntakeMotor2 = new CANSparkMax(Constants.intakeMotor2ID, MotorType.kBrushless);



  /** Creates a new ExampleSubsystem. */
  public IntakeSubsystem() {
    
    IntakeMotor1.restoreFactoryDefaults();
    IntakeMotor2.restoreFactoryDefaults();

    IntakeMotor1.setInverted(false);
    IntakeMotor2.setInverted(false); 

    IntakeMotor1.setVoltage(35);
    IntakeMotor2.setVoltage(35);
    
   IntakeMotor1.set(0);
   IntakeMotor2.set(0);
  }

  public Command exampleMethodCommand() {

    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public boolean exampleCondition() {
    return false;
  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }

  public void runGripSpeed(double speed1, double speed2)
  {
    IntakeMotor1.set(speed1); //sets the leader motor to the designated speed
    IntakeMotor2.set(speed2);
  }

  public Command shootOut(){
    double power = 1.00;
    runGripSpeed(power, power);

    return null;

  }
  public Command shootStop(){
    runGripSpeed(0, 0);
    return null;
  }

}
