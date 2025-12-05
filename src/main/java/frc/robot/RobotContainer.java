package frc.robot;

import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private final CommandXboxController driver = new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);
    public SwerveSubsystem drivebase = new SwerveSubsystem();

    public RobotContainer() {
        configureBindings();
        //drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);
    }
    SwerveInputStream driveAngularVelo = SwerveInputStream.of(drivebase.getSwerveDrive(),
                                                              ()-> driver.getLeftY(),
                                                              ()-> driver.getLeftX())
                                                              .withControllerRotationAxis(driver::getRightX)
                                                              .deadband(Constants.stickDeadband)
                                                              .scaleTranslation(0.4)
                                                              .allianceRelativeControl(true);
                                                               
    SwerveInputStream driveHeading = driveAngularVelo.copy().withControllerHeadingAxis(driver::getRightX,
                                                                                       driver::getRightY)
                                                                                       .headingWhile(true);


    Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveHeading);
    Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelo);
    private void configureBindings() {
    }
    public Command getAutonomousCommand() {
        return null; // fill in later
    }
}
