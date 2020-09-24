package khatwa.zilalalrahmaapp.ui.donation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import khatwa.zilalalrahmaapp.R
import khatwa.zilalalrahmaapp.others.Status
import khatwa.zilalalrahmaapp.databinding.FragmentDonationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DonationFragment : Fragment(){
    private val viewModel: DonationViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentDonationBinding.inflate(inflater,container,false)

        binding.buttonDonation.setOnClickListener {
            val companyName: String = when(binding.radioGroupSIMType.checkedRadioButtonId){
                R.id.radioButtonSudani -> "Sudani"
                R.id.radioButtonMTN -> "MTN"
                R.id.radioButtonZain -> "Zain"
                else -> ""
            }

            viewModel.sendUSSD(companyName, binding.editTextAmount.text.toString(), "0000") // todo code parameter for zain users
        }

        viewModel.sendUSSDStatus.observe(viewLifecycleOwner,{
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = ussdToCallableUri(result.data.toString())
                        startActivity(intent)
                    }
                    Status.ERROR -> {
                        result.message?.let { message -> Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show() }
                    }
                }

            }
        })

        return binding.root
    }

    private fun ussdToCallableUri(ussd: String): Uri {
        var uriString: String? = ""
        if (!ussd.startsWith("tel:")) uriString += "tel:"
        for (c in ussd.toCharArray()) {
            if (c == '#') uriString += Uri.encode("#") else uriString += c
        }
        return Uri.parse(uriString)
    }

}