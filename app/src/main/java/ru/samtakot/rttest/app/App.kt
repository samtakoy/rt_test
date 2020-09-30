package ru.samtakot.rttest.app

import android.os.Build
import androidx.multidex.MultiDexApplication
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import ru.samtakot.rttest.app.di.DaggerAppComponent
import ru.samtakot.rttest.app.di.Di


class App : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()

        Di.appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build()

        //enable tls v1.2 for android < 21
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            updateAndroidSecurityProvider()
        }
    }

    /**
     * Это должно фиксить SSLException
     *
     * @see {https://stackoverflow.com/a/42471738/3212712}
     */
    private fun updateAndroidSecurityProvider() {
        try {
            ProviderInstaller.installIfNeeded(this)
        } catch (e: GooglePlayServicesRepairableException) {
            // Thrown when Google Play Services is not installed, up-to-date, or enabled
            // Show dialog to allow users to install, update, or otherwise enable Google Play services.
            println("Google Play Services not available.")
        } catch (e: GooglePlayServicesNotAvailableException) {
            println("Google Play Services not available.")
        }
    }
}