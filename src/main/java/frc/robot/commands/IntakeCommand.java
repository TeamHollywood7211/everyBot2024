// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/** An example command that uses an example subsystem. */
public class IntakeCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_subsystem;
  private final CommandXboxController m_controller;

  public IntakeCommand(IntakeSubsystem subsystem, CommandXboxController controller) {
    m_subsystem = subsystem;
    this.m_controller = controller;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_controller.getRightTriggerAxis() > 0.05){ //if right trigger > 0.05
      m_subsystem.runGripSpeed(-1, -0.5); //set max outtake
      
    } else if(m_controller.getLeftTriggerAxis() > 0.05){
      m_subsystem.runGripSpeed(1, 1);
    } else if(m_controller.leftBumper().getAsBoolean())
    {
      m_subsystem.runGripSpeed(1, 1); //max speed
    }
    else{
      //m_gripSubsystem.setGripOut();
      m_subsystem.runGripSpeed(0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void outTake() {

   // m_subsystem.runGripSpeed(Constants.outakeSpeed);
  }
  public void inTake() {

   // m_subsystem.runGripSpeed(Constants.intakeSpeed);
  }
}
