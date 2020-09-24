package khatwa.zilalalrahmaapp.ui.news_details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import khatwa.zilalalrahmaapp.data.repositories.NewsDetailsRepository

class NewsDetailsViewModel @ViewModelInject constructor(private val repository: NewsDetailsRepository): ViewModel() {

   private var id: MutableLiveData<Int> = MutableLiveData()

   val newsResponse = id.switchMap {
      repository.getNewsDetails(it)
   }

   fun getNewsDetails(newsId: Int){
      id .value= newsId
   }

}