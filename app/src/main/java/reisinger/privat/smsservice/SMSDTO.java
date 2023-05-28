package reisinger.privat.smsservice;

public class SMSDTO {
    private long deviceId;
    private String number;
    private String message;
    private String action;

    public SMSDTO(long deviceId, String number, String message, String action) {
        this.deviceId = deviceId;
        this.number = number;
        this.message = message;
        this.action = action;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
