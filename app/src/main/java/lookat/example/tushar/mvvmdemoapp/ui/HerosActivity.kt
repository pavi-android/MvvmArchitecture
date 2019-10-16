package lookat.example.tushar.mvvmdemoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import lookat.example.tushar.allstatemobile.model.HerosModel
import lookat.example.tushar.allstatemobile.ui.adaptors.HerosAdapter
import lookat.example.tushar.allstatemobile.viewmodel.HeroViewModel
import lookat.example.tushar.mvvmdemoapp.R

class HerosActivity : AppCompatActivity() {

    private var heroRecycler: RecyclerView? = null

    var heroViewModel: HeroViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)

        heroRecycler = findViewById(R.id.heroRecyclerId)
        heroViewModel = ViewModelProviders.of(this).get(HeroViewModel::class.java)

        heroViewModel?.herosResult?.observe(this, Observer { heroList ->
            setListToAdaptor(heroList)
        })

    }

    private fun setListToAdaptor(heroList: List<HerosModel>?) {
        val herosAdapter = HerosAdapter(heroList)
        heroRecycler?.setAdapter(herosAdapter)

    }

}

