package org.oobootcamp.ParkingLot.Model;

public class Result<T> {
    public final boolean isSuccess;
    public final String info;
    public final T value;
    public Result(boolean isSuccess, String info, T value)
    {
        this.isSuccess = isSuccess;
        this.info = info;
        this.value = value;
    }
}
