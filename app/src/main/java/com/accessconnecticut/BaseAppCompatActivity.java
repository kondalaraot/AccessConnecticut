package com.accessconnecticut;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**Common Activity class for default functionalties in all activities
 * Created by KTirumalsetty on 10/12/2016.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
//    private PreferenceManager mPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        mPreferenceManager = new PreferenceManager(this);

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.menu_common, menu);

        // Get the SearchView and set the searchable configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(true);
        }
*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    protected void showProgress(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();

        mProgressDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name), msg);
        mProgressDialog.setCancelable(false);
//        mProgressDialog.setProgressStyle(Theme_Material_Light);
    }

    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /*protected PreferenceManager getPrefManager(){
        if(mPreferenceManager !=null){
            return mPreferenceManager;
        }else{
            mPreferenceManager = new PreferenceManager(this);
        }
        return mPreferenceManager;
    }*/

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void showAlert(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }


/*protected boolean hasText(EditText editText) {

        if(editText.getVisibility() == View.VISIBLE){

            String text = editText.getText().toString().trim();
            editText.setError(null);
            // length 0 means there is no text

            if (TextUtils.isEmpty(text)) {
                editText.setError(getString(R.string.error_field_required));
//                focusView = mAboutShop;
//                cancel = true;
                return true;
            }

        }

        return false;
    }*/
    protected boolean hasSpinnerSelected(Spinner spinner) {

        if(spinner.getVisibility() == View.VISIBLE){

            int pos = spinner.getSelectedItemPosition();
            ((TextView)spinner.getSelectedView()).setError(null);
            // length 0 means there is no text

            if (pos == 0) {
                ((TextView)spinner.getSelectedView()).setError("");
//                focusView = mAboutShop;
//                cancel = true;
                return true;
            }

        }

        return false;
    }
   /* protected void showSnackBarMessage(String message) {

        if (getView() != null) {

            Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
        }
    }*/

}
