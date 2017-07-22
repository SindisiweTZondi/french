package jesusm.com.fingerprintmanager.sample

import android.content.Intent
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.jesusm.kfingerprintmanager.KFingerprintManager
import com.jesusm.kfingerprintmanager.utils.bind

class MainActivity : AppCompatActivity() {
    private val KEY = "my_key"

    private var messageTextView = bind<TextView>(R.id.message)
    private var authenticateButton = bind<Button>(R.id.buttonAuthenticate)

    @StyleRes
    private var dialogTheme = R.style.DialogThemeDark
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectView(findViewById(R.id.buttonDialogThemeLight))
        initClickListeners()
    }

    private fun initClickListeners() {

        findViewById(R.id.buttonDialogThemeLight).setOnClickListener { v ->
            selectView(v)

            deselectView(findViewById(R.id.buttonDialogThemeDark))

            dialogTheme = R.style.DialogThemeLight
        }

        findViewById(R.id.buttonDialogThemeDark).setOnClickListener { v ->
            selectView(v)

            deselectView(findViewById(R.id.buttonDialogThemeLight))

            dialogTheme = R.style.DialogThemeDark
        }


        authenticateButton.value.setOnClickListener {
            createFingerprintManagerInstance().authenticate(object : KFingerprintManager.AuthenticationCallback {
                override fun onAuthenticationFailedWithHelp(help: String?) {
                    messageTextView.value.text = help
                }

                override fun onAuthenticationSuccess() {
                    messageTextView.value.text = "Successfully authenticated"


                }

                override fun onSuccessWithManualPassword(password: String) {
                    messageTextView.value.text = "Manual password: " + password
                }

                override fun onFingerprintNotRecognized() {
                    messageTextView.value.text = "Fingerprint not recognized"
                }

                override fun onFingerprintNotAvailable() {
                    messageTextView.value.text = "Fingerprint not available"
                }

                override fun onCancelled() {
                    messageTextView.value.text = "Operation cancelled by user"
                }
            }, supportFragmentManager)
        }




    }

    private fun createFingerprintManagerInstance(): KFingerprintManager {
        val fingerprintManager = KFingerprintManager(this, KEY)
        fingerprintManager.setAuthenticationDialogStyle(dialogTheme)
        return fingerprintManager
    }
    private fun selectView(view: View) {
        view.apply {
            isSelected = true
            elevation = 32f
        }
    }

    private fun deselectView(view: View) {
        view.apply {
            isSelected = false
            elevation = 0f
        }
    }
}
