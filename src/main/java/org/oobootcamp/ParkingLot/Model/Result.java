package org.oobootcamp.ParkingLot.Model;

public class Result<T> {
    public final boolean isSuccess;
    public final String errorInfo;
    public final T value;
    public Result(boolean isSuccess, String errorInfo, T value)
    {
        this.isSuccess = isSuccess;
        this.errorInfo = errorInfo;
        this.value = value;
    }
}
