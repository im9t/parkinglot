package org.oobootcamp.ParkingLot.Model;

public class Result<T> {
    public final boolean isSucceed;
    public final String errorMessage;
    public final T value;
    public Result(boolean isSucceed, String errorMessage, T value)
    {
        this.isSucceed = isSucceed;
        this.errorMessage = errorMessage;
        this.value = value;
    }
}
