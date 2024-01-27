package frc.robot;

import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;

import edu.wpi.first.wpilibj2.command.*;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
    /* Subsystems */
    public final Swerve s_Swerve = new Swerve();
    public final IntakeSubsystem s_IntakeSubsystem = new IntakeSubsystem();    

    private String pPlan = null;
    public double intakeVec = 0;

    public Command autoCode = Commands.sequence(new PrintCommand("no auto selected"));
    


    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final CommandXboxController operator = new CommandXboxController(1);



    private final IntakeCommand m_IntakeCommand = new IntakeCommand(s_IntakeSubsystem, operator);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    //op controls
    private final int wristAxis = XboxController.Axis.kLeftY.value;
    private final int elevatorAxis = XboxController.Axis.kRightY.value;





    private final JoystickButton 
    limelighButton = new JoystickButton(driver, XboxController.Button.kA.value);




    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, 7);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton driver_stowButton = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
    private final JoystickButton driver_AutoBalance = new JoystickButton(driver, XboxController.Button.kB.value);


    // private final JoystickButton xModeButton = new JoystickButton(driver, XboxController.Button.kX.value);

    /* LED Initialization Code */
    private DigitalOutput LEDzero = new DigitalOutput(8);
    private DigitalOutput LEDone = new DigitalOutput(9);

    /* Variables */
    boolean driveStatus = false;

    
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      s_Swerve.setDefaultCommand(
        new TeleopSwerve(
          s_Swerve, 
          () -> -driver.getRawAxis(translationAxis), 
          () -> -driver.getRawAxis(strafeAxis), 
          () -> -driver.getRawAxis(rotationAxis), 
          () -> robotCentric.getAsBoolean()
        )
      );

      // Configure the button bindings
      configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */

    private void configureButtonBindings() {
       zeroGyro.whileTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

            new Trigger(operator.rightTrigger(0.05))
        .onTrue(m_IntakeCommand);
      new Trigger(operator.leftTrigger(0.1))
        .onTrue(m_IntakeCommand);

      

      new Trigger(operator.rightBumper())
        .onTrue(m_IntakeCommand);
        
      new Trigger(operator.rightBumper())
      .onFalse(m_IntakeCommand);

      
    }
    
    public void printValues(){
        //SmartDashboard.putNumber("balanceP", 0.03);
        // SmartDashboard.getNumber("balanceI", elevatorAxis);
        // SmartDashboard.getNumber("balanceD", elevatorAxis);


        

        //SmartDashboard.putNumber("Pid off", chooser.getPIDController().getPositionError());

        // SmartDashboard.putNumber("RX", s_Limelight.getRX());
        // SmartDashboard.putNumber("RY", s_Limelight.getRY());
        // SmartDashboard.putNumber("RZ", s_Limelight.getRZ());
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public Command getAutonomousCommand() {

      Constants.gyroOffset = s_Swerve.gyro.getPitch();
      //s_Swerve.zeroGyro();
      return null;
    }
}
