package org.oobootcamp.ParkingLot.Model;

public class Result<T> {
    public final String errorMessage;
    public final T value;

    public Result(String errorMessage)
    {
        this(null, errorMessage);
    }
    public Result(T value)
    {
        this(value, "");
    }
    private Result(T value, String errorMessage)
    {
        this.errorMessage = errorMessage;
        this.value = value;
    }
    public boolean isSucceed()
    {
        return value != null;
    }
}
