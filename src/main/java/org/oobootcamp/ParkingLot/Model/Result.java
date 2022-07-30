package org.oobootcamp.ParkingLot.Model;

public class Result<T> {
    public final boolean isSuccessed;
    public final String errorMessage;
    public final T value;
    public Result(boolean isSuccessed, String errorMessage, T value)
    {
        this.isSuccessed = isSuccessed;
        this.errorMessage = errorMessage;
        this.value = value;
    }
}
