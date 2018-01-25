package Utils;
public final class globals {
    public enum MessageType { Info, Warn, Danger}
    public enum ECUCommands {
        ApplyBreakToStop,
        DescreaseSpeed,
        IncreaseSpeed,
        Descreasedistance,
        Increasedistance,
        StartAlarm,
        StartLeftIndicator,
        StartRightIndicator,
        TurnOffAllIndicators,
        TurnOnParkingLight,
        TurnOffParkingLight
    }
    public enum PlatoonModes {
        Expand,
        Shrink,
        Disengage,
        GSolo,
        Failsafe
    }
    public enum GPSDataModes{
        Red,
        Yellow,
        Blue
    }

    public enum BreakModes{
        NoBreak,
        SoftBreak,
        HardBreak
    }

}
