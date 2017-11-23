package business.manger;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;

import javax.inject.Inject;

import business.model.PhoneContact;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import presenter.PhoneContactPresenter;

/**
 * Created by m7md mimo on 11/2/2017.
 */

public class ContactManger {
    private ArrayList<PhoneContact> contactArrayList = new ArrayList<>();
    private Realm realm;
    private PhoneContactPresenter presenter;
    Context context;
    private PhoneContact phoneContact;

    public ContactManger() {
    }

    @Inject
    public ContactManger(Context context) {
        this.context = context;
    }

    public void saveContacts(ArrayList<PhoneContact> contactVOList) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(contactVOList);
        realm.commitTransaction();
    }

    public ArrayList<PhoneContact> getContacts() {
            realm = Realm.getDefaultInstance();
            RealmQuery<PhoneContact> query = realm.where(PhoneContact.class);
        RealmResults<PhoneContact> result1 = query.findAll();
        for (int i = 0; i < result1.size() - 1; i++) {
            contactArrayList.add(result1.get(i));
        }

        return contactArrayList;
    }


    public void setPresenter(PhoneContactPresenter presenter) {
        this.presenter = presenter;
    }

    private ArrayList<PhoneContact> getContactsFromProvider() {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    phoneContact = new PhoneContact();
                    phoneContact.setContactName(name);

                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);
                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String phone_name = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
                        phoneContact.setContactNumber(phoneNumber);
                    }

                    phoneCursor.close();

                    contactArrayList.add(phoneContact);
                }
                saveContacts(contactArrayList);
            }
        }
return  contactArrayList;
    }

    public void getPhoneContacts() {

        new AsyncTask<Void, Void, ArrayList<PhoneContact>>() {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected ArrayList<PhoneContact> doInBackground(Void... params) {
                contactArrayList = getContacts();
                return contactArrayList;
            }

            @Override
            protected void onPostExecute(ArrayList<PhoneContact> result) {
                int length = result.size();
                if (length > 0)
                    presenter.takeContacts(result);
                else
                    getContactAsync();
            }
        }.execute((Void[]) null);


    }

    public void getContactAsync() {
        new AsyncTask<Void, Void, ArrayList<PhoneContact>>() {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected ArrayList<PhoneContact> doInBackground(Void... params) {
                return  getContactsFromProvider();
            }

            @Override
            protected void onPostExecute(ArrayList<PhoneContact> result) {
                   presenter.takeContacts(result);

            }
        }.execute((Void[]) null);
    }

}
