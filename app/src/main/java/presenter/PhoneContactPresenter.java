package presenter;

import java.util.ArrayList;

import javax.inject.Inject;

import business.manger.ContactManger;
import business.model.PhoneContact;
import concrete.IPhoneContactConcrete;

/**
 * Created by m7md mimo on 11/23/2017.
 */

public class PhoneContactPresenter implements IPhoneContactConcrete.IPhoneContactPresenter {
    private ContactManger contactManger;
    private IPhoneContactConcrete.IPhoneContactView iPhoneContactView;

    @Inject
    public PhoneContactPresenter(ContactManger contactManger) {
        this.contactManger = contactManger;
        contactManger.setPresenter(this);
    }


    @Override
    public void setUp(IPhoneContactConcrete.IPhoneContactView iPhoneContactView) {
        this.iPhoneContactView = iPhoneContactView;
    }

    @Override
    public void getContacts() {
        iPhoneContactView.waiting();
        contactManger.getContactAsync();
    }

    @Override
    public void takeContacts(ArrayList<PhoneContact> contactVOList) {
        iPhoneContactView.stopWaiting();
        iPhoneContactView.showContacts(contactVOList);
    }
}
