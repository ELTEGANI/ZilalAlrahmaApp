package khatwa.zilalalrahmaapp.ui.socialserviceshub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import khatwa.zilalalrahmaapp.R

class SocialServicesHubFragment : Fragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.social_services_hub_fragment, container, false)
    }

}