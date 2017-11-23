package business.manger;

import android.content.SharedPreferences;

import javax.inject.Inject;

import static business.GmdxUtil.KEY_REMEMBER;

/**
 * Created by m7md mimo on 11/22/2017.
 */
public class SharedManger {
    private SharedPreferences mSharedPreferences;

    @Inject
    public SharedManger(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public void putData(String key, int data) {
        mSharedPreferences.edit().putInt(key, data).apply();
    }

    public void putData(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public void removeRememberData() {
        mSharedPreferences.edit().putInt(KEY_REMEMBER, 0).apply();
    }

    public int getData(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
    public String getLocations(String key) {
        return mSharedPreferences.getString(key, "");
    }
}
