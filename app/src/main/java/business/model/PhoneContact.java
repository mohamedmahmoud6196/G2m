package business.model;

import io.realm.RealmObject;

/**
 * Created by m7md mimo on 11/21/2017.
 */

    public class PhoneContact extends RealmObject {
    private String contactName;
    private String contactNumber;

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
