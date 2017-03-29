package bclurms;


import android.content.Context;
import android.util.Log;

import com.sonydadc.urms.android.Urms;
import com.sonydadc.urms.android.UrmsError;
import com.sonydadc.urms.android.api.CreateProfileTask;
import com.sonydadc.urms.android.task.EmptyResponse;
import com.sonydadc.urms.android.task.IFailedCallback;
import com.sonydadc.urms.android.task.ISucceededCallback;
import com.sonydadc.urms.android.task.IUrmsTask;
import com.sonydadc.urms.android.task.UrmsTaskStatus;
import com.sonydadc.urms.android.type.UrmsConfig;

import static android.content.ContentValues.TAG;


public class UrmsCreateProfileRequest {

    public void initialize(String authToken, String profileName) {


        if (profileName == "") {
            profileName = "default";
        }

        UrmsConfig config = new UrmsConfig(
                "https://urms-sdk.codefusion.technology/sdk/",			// cgp.api
                "https://urms-marlin-us.codefusion.technology/bks/",	// marlin.api
                "urn:marlin:organization:sne:service-provider:2",		// marlin.service_id
                true 													// marlin.use_ssl
        );

        CreateProfileTask createProfile = Urms.createCreateProfileTask(authToken, profileName, null, config);
        Log.d(TAG, "!@!@! Token: " + authToken);

        createProfile.setSucceededCallback(new ISucceededCallback<EmptyResponse>() {
            @Override
            public void onSucceeded(IUrmsTask task, EmptyResponse result) {
                Log.d(TAG, "Success creating profile.");
            }
        });

        createProfile.setFailedCallback(new IFailedCallback() {
            @Override
            public void onFailed(IUrmsTask task, UrmsTaskStatus status, UrmsError error) {
                if (error.getErrorType() == UrmsError.RegisterUserDeviceCapacityReached) {
                    Log.e(TAG, "RegisterUserDeviceCapacityReached");
                } else if ( error.getErrorType() == UrmsError.NetworkError ||
                        error.getErrorType() == UrmsError.NetworkTimeout) {
                    Log.e(TAG, "Network error or network timeout.");
                } else if(error.getErrorType() == UrmsError.UrmsNotInitialized){
                    Log.e(TAG, "URMS not initialized.");
                } else if (error.getErrorType() == UrmsError.LoseTime) {
                    Log.e(TAG, "Please ensure the time on your device is correct.");
                } else if (error.getErrorType() == UrmsError.OutdatedVersion) {
                    Log.e(TAG, "Outdated version");
                } else if (error.getErrorCode().endsWith("04")) {
                    Log.e(TAG, "Potential server/client configuration mismatch.");
                } else {
                    Log.e(TAG, "Other error: " + error.getErrorCode());
                    Log.e(TAG, "Error type: " + new Integer(error.getErrorType()).toString());
                }
                Log.e(TAG, "Error creating profile.");
            }
        });
        Urms.executeAsync(createProfile);
    }


    /**
     * Interface for handling post-user registration calls
     * @author Bluefire
     *
     */
    public interface OnCreateProfile {
        public void onCreateProfileComplete(boolean success);
    }


    /**
     * Method for registering a URMS User
     * @param context
     * @param urmsToken
     * @param onProfileCreated
     */
    public static void createProfile(Context context, String urmsToken, final OnCreateProfile onProfileCreated) {
        Log.e(TAG, "[registerURMSUser] About to register URMS User. ");
        Log.e(TAG, "[registerURMSUser] urmsToken: " + urmsToken);
        UrmsCreateProfileRequest createProfileRequest = new UrmsCreateProfileRequest();
        createProfileRequest.initialize(urmsToken, "default");
    }



}
