package khatwa.zilalalrahmaapp.ui.donation

import khatwa.zilalalrahmaapp.others.Event
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import khatwa.zilalalrahmaapp.others.Result
import khatwa.zilalalrahmaapp.others.Status

class DonationViewModel @ViewModelInject constructor() : ViewModel() {

    private var _sendUSSDStatus = MutableLiveData<Event<Result<String>>>()
    val sendUSSDStatus: LiveData<Event<Result<String>>> = _sendUSSDStatus

    fun sendUSSD(companyName: String, amount: String, code: String) {

        if (companyName.isEmpty() || amount.isEmpty() || code.isEmpty()) {
            _sendUSSDStatus.value = Event(Result(Status.ERROR, null, "fields must not be empty"))
            return
        }
        val amountInt = amount.toInt()
        if (amountInt <= 0) {
            _sendUSSDStatus.value = Event(Result(Status.ERROR, null, "The lowest transfer value is 1 SDG"))
            return
        }

        var ussd = ""

        if (companyName == "Sudani")
            ussd = "*303*$amount*0000000000*0000#"   // TODO("replace 0000000000 with real phone number")

        if (companyName == "MTN")
            ussd = "*121*090000000*$amount*00000#"

        if (companyName == "Zain")
            ussd = "*200*$code*$amount*090000000*090000000#"

        _sendUSSDStatus.value = Event(Result(Status.SUCCESS, ussd, null))

    }
}