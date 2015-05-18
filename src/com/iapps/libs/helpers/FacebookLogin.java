package com.iapps.libs.helpers;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.iapps.common_library.R;

public class FacebookLogin {
	private String			fbAppId			= "";

	/**
	 * Don't forget to override onActivityResult on the target fragment, and call this class'
	 * onActivityResult
	 */
	Session.StatusCallback	statusCallback	= new SessionStatusCallback();
	public Bundle			facebookBundle;
	public boolean			isFBAsyncRunning;
	Fragment				frag;
	ListenerFacebook		callback;

	public FacebookLogin(String fbAppId, ListenerFacebook callback, Fragment frag) {
		this.fbAppId = fbAppId;
		this.frag = frag;
		this.callback = callback;
	}

	public void signUpByFacebook() {
		Session session = new Session.Builder(frag.getActivity()).setApplicationId(
				fbAppId).build();
		Session.setActiveSession(session);
		newLoginWithFB();
	}

	private void newLoginWithFB() {
		Session session = Session.getActiveSession();

		if (!session.isOpened() && !session.isClosed()) {
			session.openForRead(new Session.OpenRequest(frag)
					.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK)
					.setCallback(statusCallback)
					.setPermissions(Arrays.asList("email", "user_birthday")));
		}
		else {
			Session.openActiveSession(frag.getActivity(), true, statusCallback);
		}
	}

	private void onSessionStateChange(SessionState state, Exception exception) {
		Session session = Session.getActiveSession();
		// Check if the session is open
		if (state.isOpened()) {
			if (isFBAsyncRunning == false) {
				Request req = new Request(session, "me");
				FacebookRequestAsyncTask fb = new FacebookRequestAsyncTask(req);
				fb.execute();
			}
			else {
				// it is already running
			}

		}
		else if (session.getState() == SessionState.OPENING) {
			session.addCallback(statusCallback);

		}
		else if (state == SessionState.CLOSED_LOGIN_FAILED) {
			showFBError();
		}
	}

	private void showFBError() {
		// Helper.showAlert(getActivity(), "Error", "Facebook connection error");
		Session.getActiveSession().closeAndClearTokenInformation();
		Session session = new Session.Builder(frag.getActivity()).setApplicationId(fbAppId).build();
		Session.setActiveSession(session);
	}

	private class SessionStatusCallback implements Session.StatusCallback {

		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChange(state, exception);
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK
				&& requestCode == Session.DEFAULT_AUTHORIZE_ACTIVITY_CODE && data != null) {
			Session.getActiveSession().addCallback(statusCallback);
			Session.getActiveSession().onActivityResult(frag.getActivity(), requestCode,
					resultCode,
					data);
		}
		else if (Session.getActiveSession().getState() == SessionState.OPENING && data != null) {
			Session.getActiveSession().addCallback(statusCallback);
			Session.getActiveSession().onActivityResult(frag.getActivity(), requestCode,
					resultCode,
					data);
		}
	}

	// Facebook Request
	private class FacebookRequestAsyncTask
			extends RequestAsyncTask {

		final ProgressDialog	mDialog	= new ProgressDialog(frag.getActivity());

		public FacebookRequestAsyncTask(Request request) {
			super(request);
		}

		@Override
		public void onPreExecute() {
			super.onPreExecute();
			isFBAsyncRunning = true;
			mDialog.setTitle(null);
			mDialog.setMessage(frag.getActivity().getString(R.string.iapps__loading));
			mDialog.setCancelable(false);
			mDialog.show();
		}

		@Override
		public void onPostExecute(List<Response> result) {
			mDialog.dismiss();
			isFBAsyncRunning = false;
			if (result != null && result.size() > 0) {
				try {
					Response m = result.get(0);
					callback.onFbLoginSuccess(m);
				}
				catch (NullPointerException e) {
					callback.onFbLoginFail(e.getMessage());
					showFBError();
					e.printStackTrace();
				}
				catch (IndexOutOfBoundsException e) {
					callback.onFbLoginFail(e.getMessage());
					showFBError();
					e.printStackTrace();
				}

			}
			else {
				callback.onFbLoginFail(frag.getActivity().getString(
						R.string.iapps__unknown_response));
			}

		}
	}

	public interface ListenerFacebook {
		public void onFbLoginSuccess(Response response);

		public void onFbLoginFail(String message);
	}
}
