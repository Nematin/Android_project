//@author Дегтяникова, Баландин, Золоторёв, Балышев
package ru.psu.studyit.view.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.LoaderManager.LoaderCallbacks

import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.Intent


import ru.psu.studyit.R

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

import java.util.ArrayList

import androidx.appcompat.app.AppCompatActivity

/**
 * A login screen that offers login via email/password.
 */
class CActivityLogin : AppCompatActivity(), LoaderCallbacks<Cursor>
{

    // UI references.
    private var mEmailView: TextInputEditText? = null
    private var mPasswordView: EditText? = null
    private val mProgressView: View? = null
    private var mLoginFormView: View? = null
    private var mTextInputLayoutEmail: TextInputLayout? = null
    private var mTextInputLayoutPassword: TextInputLayout? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.
        mEmailView = findViewById(R.id.email)

        mPasswordView = findViewById<View>(R.id.password) as EditText

        mTextInputLayoutEmail = findViewById(R.id.TextInputLayoutEmail)
        mTextInputLayoutPassword = findViewById(R.id.TextInputLayoutPassword)

        mPasswordView!!
            .setOnEditorActionListener(TextView.OnEditorActionListener { textView, id, keyEvent ->
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL)
                {
                    attemptLogin()
                    return@OnEditorActionListener true
                }
                false
            })

        val mEmailSignInButton = findViewById<View>(R.id.email_sign_in_button) as Button
        //        mEmailSignInButton.setOnClickListener(new OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                attemptLogin();
        //                Intent intent = new Intent(this, CActivityMain.class);
        //                startActivity(intent);
        //                finish();
        //            }
        //        });

        mLoginFormView = findViewById(R.id.login_form)
        //        mProgressView = findViewById(R.id.login_progress);


        mEmailView!!.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            var len = 0
            if (mEmailView!!.text != null)
                len = mEmailView!!.text!!.length
            if (!hasFocus && len == 0)
            {

                mTextInputLayoutEmail!!.hint = "Введите логин"
            }
            else
            {
                mTextInputLayoutEmail!!.hint = "Email"
            }
        }

        mPasswordView!!.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            var len = 0
            if (mPasswordView!!.text != null)
                len = mPasswordView!!.text.length
            if (!hasFocus && len == 0)
            {

                mTextInputLayoutPassword!!.hint = "Введите пароль"
            }
            else
            {
                mTextInputLayoutPassword!!.hint = "Пароль"
            }
        }
    }

    fun onLoginClick(view: View)
    {
        attemptLogin()
        val intent = Intent(this, CActivityMain::class.java)
        startActivity(intent)
        finish()
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin()
    {
        //   if (mAuthTask != null) {
        //       return;
        //    }

        // Reset errors.0
        mEmailView!!.error = null
        mPasswordView!!.error = null

        // Store values at the time of the login attempt.
        val email = mEmailView!!.text!!.toString()
        val password = mPasswordView!!.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password))
        {
            mPasswordView!!.error = getString(R.string.error_invalid_password)
            focusView = mPasswordView
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email))
        {
            mEmailView!!.error = getString(R.string.error_field_required)
            focusView = mEmailView
            cancel = true
        }
        else if (!isEmailValid(email))
        {
            mEmailView!!.error = getString(R.string.error_invalid_email)
            focusView = mEmailView
            cancel = true
        }

        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        }
        else
        {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            //        mAuthTask = new UserLoginTask(email, password);
            //          mAuthTask.execute((Void) null);
        }
    }

    private fun isEmailValid(email: String): Boolean
    {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean
    {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean)
    {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
            mLoginFormView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                (if (show) 0 else 1).toFloat()
            ).setListener(object : AnimatorListenerAdapter()
            {
                override fun onAnimationEnd(animation: Animator)
                {
                    mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mProgressView.animate().setDuration(shortAnimTime.toLong()).alpha(
                (if (show) 1 else 0).toFloat()
            ).setListener(object : AnimatorListenerAdapter()
            {
                override fun onAnimationEnd(animation: Animator)
                {
                    mProgressView.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        }
        else
        {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateLoader(i: Int, bundle: Bundle): Loader<Cursor>
    {
        return CursorLoader(
            this,
            // Retrieve data rows for the device user's 'profile' contact.
            Uri.withAppendedPath(
                ContactsContract.Profile.CONTENT_URI,
                ContactsContract.Contacts.Data.CONTENT_DIRECTORY
            ), ProfileQuery.PROJECTION,

            // Select only email addresses.
            ContactsContract.Contacts.Data.MIMETYPE + " = ?", arrayOf(
            ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE
        ),

            // Show primary email addresses first. Note that there won't be
            // a primary email address if the user hasn't specified one.
            ContactsContract.Contacts.Data.IS_PRIMARY + " DESC"
        )
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor)
    {
        val emails = ArrayList<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast)
        {
            emails.add(cursor.getString(ProfileQuery.ADDRESS))
            cursor.moveToNext()
        }

        addEmailsToAutoComplete(emails)
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>)
    {

    }

    private fun addEmailsToAutoComplete(emailAddressCollection: List<String>)
    {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        val adapter = ArrayAdapter(
            this@CActivityLogin,
            android.R.layout.simple_dropdown_item_1line, emailAddressCollection
        )

        //mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery
    {
        companion object
        {
            val PROJECTION = arrayOf(
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY
            )

            val ADDRESS = 0
            val IS_PRIMARY = 1
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class UserLoginTask internal constructor(
        private val mEmail: String, private val mPassword: String
    ) : AsyncTask<Void, Void, Boolean>()
    {

        override fun doInBackground(vararg params: Void): Boolean?
        {
            // TODO: attempt authentication against a network service.

            try
            {
                // Simulate network access.
                Thread.sleep(2000)
            }
            catch (e: InterruptedException)
            {
                return false
            }

            //            for (String credential : DUMMY_CREDENTIALS) {
            //                String[] pieces = credential.split(":");
            //                if (pieces[0].equals(mEmail)) {
            //                    // Account exists, return true if the password matches.
            //                    return pieces[1].equals(mPassword);
            //                }
            //            }

            // TODO: register the new account here.
            return true
        }

        override fun onPostExecute(success: Boolean?)
        {
            //         mAuthTask = null;
            showProgress(false)

            if (success!!)
            {
                finish()
            }
            else
            {
                mPasswordView!!.error = getString(R.string.error_incorrect_password)
                mPasswordView!!.requestFocus()
            }
        }

        override fun onCancelled()
        {
            //         mAuthTask = null;
            showProgress(false)
        }
    }
}
