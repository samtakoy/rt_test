package ru.samtakot.rttest.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.samtakot.rttest.R
import ru.samtakot.rttest.app.di.Di
import ru.samtakot.rttest.domain.entity.User
import java.text.MessageFormat
import javax.inject.Inject
import javax.inject.Provider

class DetailsFragment : MvpAppCompatFragment(), DetailsView{



    @Inject
    lateinit var factoryProvider: Provider<DetailsPresenter.Factory>
    private val presenter by moxyPresenter {
        val userId = requireArguments().getInt("userId")
        factoryProvider.get().create(userId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        Di.appComponent.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)
        return view
    }

    override fun showUser(user: User) {

        firstName.text = user.firstName
        lastName.text = user.lastName

        val emailString = requireContext().resources.getString(R.string.card_email_text)
        email.text = MessageFormat.format(emailString, user.email)

        Glide.with(requireContext())
            .load(user.avatar)
            .centerCrop()
            .placeholder(R.drawable.ic_person_gray_24dp)
            .error(R.drawable.ic_err_person_gray_24dp)
            .into(avatar)
    }

    override fun showError(errorId: Int) {
        Toast.makeText(requireContext().applicationContext, errorId, Toast.LENGTH_SHORT).show()
    }

    override fun updateToolbarTitle(title: String) {
        val activity: AppCompatActivity = requireActivity() as AppCompatActivity;
        activity.supportActionBar!!.title = title
    }

}