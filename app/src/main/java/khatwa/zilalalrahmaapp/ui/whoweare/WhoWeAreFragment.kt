package khatwa.zilalalrahmaapp.ui.whoweare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import khatwa.zilalalrahmaapp.R

class WhoWeAreFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.who_we_are_fragment, container, false)
    }

}