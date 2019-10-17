package lookat.example.tushar.allstatemobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import lookat.example.tushar.allstatemobile.model.HerosModel
import lookat.example.tushar.allstatemobile.repository.HerosRepository

class HeroViewModel : ViewModel() {

    var herosResult: LiveData<List<HerosModel>> = HerosRepository().getHerosList()

}