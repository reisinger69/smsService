package reisinger.privat.smsservice;

public class SMSDTO {
    private String phoneNumber;
    private String message;
    private String action;
    private String actionDate;
    private String _id;
    private String _rev;
    private String table;

    public SMSDTO(String phoneNumber, String message, String action, String actionDate, String _id, String _rev, String table) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.action = action;
        this.actionDate = actionDate;
        this._id = _id;
        this._rev = _rev;
        this.table = table;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
