public class MessageWithName extends Message {

    private String newName;
    MessageWithName(String username, String password, String deviceType, String deviceName, String whichFunction, String newName) {
        super(username, password, deviceType, deviceName, whichFunction, -1);
        this.newName = newName;
    }

    public String getNewName() {
        return getNewName();
    }
}
