package org.firstinspires.ftc.teamcode.Robot.Constants;

public enum BallsColour {
    NECTAR_RED(2),
    NECTAR_BLUE(3),
    POLLEN(1);
    // 1 = Pollen(Yellow)
    // 2 = Nectar(Red)
    // 3 = Nectar(Blue)
    private final int colorNum;

    BallsColour(int colorNum) {
        this.colorNum = colorNum;
    }

    public int getColorNum() {
        return colorNum;
    }
}
