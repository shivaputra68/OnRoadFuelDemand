package operations;

public class AdminBunkVerify {

    String bunkName,ownerName,bunkContact, bunkAddress, status;

    public AdminBunkVerify(String bunkName, String ownerName, String bunkContact, String bunkAddress, String status) {
        this.bunkName = bunkName;
        this.ownerName = ownerName;
        this.bunkContact = bunkContact;
        this.bunkAddress = bunkAddress;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBunkName() {
        return bunkName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getBunkContact() {
        return bunkContact;
    }

    public String getBunkAddress() {
        return bunkAddress;
    }

    public String getStatus() {
        return status;
    }
}
