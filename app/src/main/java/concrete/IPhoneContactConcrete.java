package concrete;

import java.util.ArrayList;

import business.model.PhoneContact;

/**
 * Created by m7md mimo on 11/21/2017.
 */

public interface IPhoneContactConcrete {
    interface IPhoneContactPresenter {
        void setUp(IPhoneContactView iPhoneContactView);

        void getContacts();

        void takeContacts(ArrayList<PhoneContact> contactVOList);
    }

    interface IPhoneContactView {
        void waiting();

        void stopWaiting();

        void showContacts(ArrayList<PhoneContact> contactVOList);
    }
}
