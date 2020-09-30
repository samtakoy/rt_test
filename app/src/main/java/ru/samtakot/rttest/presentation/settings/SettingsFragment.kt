package ru.samtakot.rttest.presentation.settings

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.preference.Preference
import com.bumptech.glide.Glide
import io.reactivex.Completable
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.samtakot.rttest.R
import ru.samtakot.rttest.app.di.Di
import ru.samtakot.rttest.extensions.ioToMain
import javax.inject.Inject
import javax.inject.Provider

class SettingsFragment : MvpPreferenceFragmentCompat(), SettingsView{

    @Inject
    lateinit var presenterProvider: Provider<SettingsPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Di.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)

        findPreference("pref_key_invalidate_db_cache").onPreferenceClickListener =
            Preference.OnPreferenceClickListener { preference: Preference? ->
                presenter.onUiInvalidateDbCache()
                true
            }

        findPreference("pref_key_clear_db_cache").onPreferenceClickListener =
            Preference.OnPreferenceClickListener { preference: Preference? ->
                presenter.onUiClearDbCache()
                true
            }

        findPreference("pref_key_clear_glide_cache").onPreferenceClickListener =
            Preference.OnPreferenceClickListener { preference: Preference? ->
                presenter.onUiClearGlideCaches()
                true
            }
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(requireContext().applicationContext, messageId, Toast.LENGTH_SHORT).show()
    }

    override fun clearGlideCaches() {

        val appContext = requireContext().applicationContext

        Glide.get(appContext).clearMemory();

        Completable.fromCallable {
            Glide
                .get(appContext)
                .clearDiskCache()
        }
            .ioToMain()
            .subscribe(
                {showMessage(R.string.msg_settings_apply_success)},
                {throwable -> showMessage(R.string.msg_settings_apply_error)}
            )


    }
}