package JSON;

public class User{
    private String Login;
    private String Timestamp;
    private String Message;

    public User(String Login, String Timestamp, String Message) {
        this.Login = Login;
        this.Timestamp = Timestamp;
        this.Message = Message;
    }

    public String getLogin() {
        return Login;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public String getMessage() {
        return Message;
    }
}
