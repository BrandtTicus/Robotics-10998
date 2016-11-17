/*
Copyright (c) 2016 Robert Atkinson


All rights reserved.


Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:


Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.


Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.


Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.


NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */


@TeleOp(name="JJ", group="Linear Opmode")  // @Autonomous(...) is the other common choice


public class TeleOPLeagueMatch extends LinearOpMode {


    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public DcMotor feedMotor;
    public DcMotor shootMotor;
    //public DcMotor liftMotor;
    //public DcMotor liftMotor2;
    int startposition;
    int targetposition = 3096;
    //one full revolution = 1140
    //gear ratio is 1 to 2, 1140x2
    //add 855 to get the full 2 rotation
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        feedMotor = hardwareMap.dcMotor.get("feed_motor");
        shootMotor = hardwareMap.dcMotor.get("shoot_motor");
        //liftMotor = hardwareMap.dcMotor.get("lift_motor");
        //liftMotor2 = hardwareMap.dcMotor.get("lift_motor2");


        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        feedMotor.setDirection(DcMotor.Direction.FORWARD);
        shootMotor.setDirection(DcMotor.Direction.FORWARD);
        //liftMotor.setDirection(DcMotor.Direction.FORWARD);
        // liftMotor2.setDirection(DcMotor.Direction.REVERSE);


        //shootMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //shootMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION
        //shootMotor.setMode(DcMotor.RunMode.


        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();




            leftMotor.setPower(gamepad1.left_stick_y);
            rightMotor.setPower(gamepad1.right_stick_y);
            feedMotor.setPower(gamepad2.left_stick_y);
            shootMotor.setPower(gamepad2.right_stick_y);
            // liftMotor.setPower(gamepad2.left_trigger);
            // liftMotor2.setPower(gamepad2.left_trigger);
            //liftMotor.setPower(-gamepad2.right_trigger);
            // liftMotor2.setPower(-gamepad2.right_trigger);


            if (gamepad2.y)
            {
                shootMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // set mode
                startposition = shootMotor.getCurrentPosition(); // get a value for where you are at
                shootMotor.setTargetPosition(targetposition + startposition); // how much to add to my current value
                shootMotor.setPower(1); // set the power
                shootMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION); // go to this position
                while(shootMotor.isBusy())
                {


                }
                shootMotor.setPower(0);


            }






        }
        idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
    }
}


