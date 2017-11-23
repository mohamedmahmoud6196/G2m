package com.example.m7mdmimo.g2mdx;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import adapter.MyContactRecyclerViewAdapter;
import business.model.PhoneContact;
import butterknife.BindView;
import butterknife.ButterKnife;
import concrete.IPhoneContactConcrete;
import dagger.android.support.AndroidSupportInjection;
import presenter.PhoneContactPresenter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ContactFragment extends Fragment implements IPhoneContactConcrete.IPhoneContactView {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private ArrayList<String> PhoneContactList;
    private Cursor cursor;
    private int counter;
    private Handler updateBarHandler;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @BindView(R.id.recycler_contact)
    RecyclerView recyclerView_contacts;
    @BindView(R.id.progress_bar_contact_loading)
    ProgressBar progressBar;
    @Inject
    PhoneContactPresenter phoneContactPresenter;

    public ContactFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Since reading PhoneContacts takes more time, let's run it on a separate thread.


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);
        phoneContactPresenter.setUp(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            recyclerView_contacts.setLayoutManager(new LinearLayoutManager(getActivity()));
            phoneContactPresenter.getContacts();
        }


        return view;
    }


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                phoneContactPresenter.getContacts();
            } else {
                Toast.makeText(getActivity(), "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void waiting() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopWaiting() {
       progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showContacts(ArrayList<PhoneContact> contactVOList) {
        recyclerView_contacts.setAdapter(new MyContactRecyclerViewAdapter(contactVOList));

    }
}
