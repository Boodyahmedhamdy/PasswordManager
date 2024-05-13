package com.example.passwordmanager.biometric_auth.domain.models

import android.app.Activity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.AuthenticationResult
import androidx.fragment.app.FragmentActivity

class Authenticator(
    private val activity: Activity
) {
    var authResult: String = ""



    fun auth(): String {
        val authenticators = DEVICE_CREDENTIAL or BIOMETRIC_WEAK


        val biometricAuthManager = activity.baseContext?.let {
            BiometricManager.from(it)
        }
        val canAuthenticate = biometricAuthManager?.canAuthenticate(
            authenticators
        )

        when(canAuthenticate) {

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                TODO()
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                TODO()
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                TODO()
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                TODO()
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                TODO()
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                TODO()
            }

            BiometricManager.BIOMETRIC_SUCCESS -> {

                val biometricPrompt = BiometricPrompt(
                    activity as FragmentActivity, object : AuthenticationCallback() {
                        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                            super.onAuthenticationError(errorCode, errString)
                            authResult = errString.toString()
                        }

                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)
                            authResult = result.toString()
                        }

                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            authResult = "auth failed"
                        }
                    }
                )

                val biometricPromptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("log in with your password")
                    .setDescription("to continue you must enter your password for better security")
                    .setAllowedAuthenticators(
                        DEVICE_CREDENTIAL
                    )
                    .build()
                biometricPrompt.authenticate(biometricPromptInfo)
            }
        }
        return "something went wrong"
    }


    fun showBiometricDialog(
        title: String,
        description: String,
        subtitle: String,
        onAuthenticationDialogError: (errorCode: Int, errorString: String) -> Unit = {
            int, string ->
        },
        onAuthenticationDialogSuccess: (result: AuthenticationResult) -> Unit = {},
        onAuthenticationDialogFailed: () -> Unit = {},
        onExceptionCaught: (Exception) -> Unit = {}
    ) {
        val authenticators = BIOMETRIC_WEAK or DEVICE_CREDENTIAL
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setSubtitle(subtitle)
            .setAllowedAuthenticators(authenticators)
            .build()

//        val authenticationManager = BiometricManager.from(activity)

        val prompt = BiometricPrompt(
            activity as FragmentActivity, object : AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onAuthenticationDialogError(errorCode, errString.toString())
                }

                override fun onAuthenticationSucceeded(result: AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onAuthenticationDialogSuccess(result)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onAuthenticationDialogFailed()
                }
            }
        )

        try {
            prompt.authenticate(promptInfo)
        } catch (e: Exception) {
            onExceptionCaught(e)
        }
    }

}