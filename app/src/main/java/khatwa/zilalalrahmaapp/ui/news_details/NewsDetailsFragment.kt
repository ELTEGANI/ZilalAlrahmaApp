package khatwa.zilalalrahmaapp.ui.news_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import khatwa.zilalalrahmaapp.R
import khatwa.zilalalrahmaapp.databinding.FragmentNewsDetailsBinding
import khatwa.zilalalrahmaapp.others.ApiRequestResult
import kotlinx.android.synthetic.main.news_load_state_footer_view_item.view.*

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private val args: NewsDetailsFragmentArgs by navArgs()
    private val viewModel: NewsDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)

        val id = args.newsId

        viewModel.getNewsDetails(id)

        viewModel.newsResponse.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ApiRequestResult.Status.SUCCESS -> {
                    binding.progressBarLoading.visibility = View.INVISIBLE
                    binding.buttonRetry.visibility= View.INVISIBLE
                    binding.textViewDetails.text = it.data?.details
                }

                ApiRequestResult.Status.LOADING -> {
                    binding.progressBarLoading.visibility = View.VISIBLE
                    binding.buttonRetry.visibility= View.INVISIBLE
                }
                ApiRequestResult.Status.ERROR -> {
                    binding.progressBarLoading.visibility = View.INVISIBLE
                    binding.buttonRetry.visibility= View.VISIBLE
                   Toast.makeText(requireContext(),getString(R.string.error_msg),Toast.LENGTH_SHORT).show()
                }
            }

        })

        binding.buttonRetry.setOnClickListener{
            viewModel.getNewsDetails(id)
        }

        return binding.root

    }

}