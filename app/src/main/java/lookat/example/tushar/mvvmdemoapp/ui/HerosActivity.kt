package lookat.example.tushar.mvvmdemoapp.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import lookat.example.tushar.allstatemobile.model.HerosModel
import lookat.example.tushar.allstatemobile.ui.adaptors.HerosAdapter
import lookat.example.tushar.allstatemobile.viewmodel.HeroViewModel
import lookat.example.tushar.mvvmdemoapp.R

class HerosActivity : AppCompatActivity() {

    private var heroRecycler: RecyclerView? = null
    private var heroViewModel: HeroViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)

        heroRecycler = findViewById(R.id.heroRecyclerId)

        //init viewmodel
        heroViewModel = ViewModelProviders.of(this).get(HeroViewModel::class.java)

        //observe the data from viewmodel
        heroViewModel?.herosResult?.observe(this, Observer { heroList ->
            setListToAdaptor(heroList)
        })


    }

    private fun setListToAdaptor(heroList: List<HerosModel>?) {
        val herosAdapter = HerosAdapter(heroList)

        //As per orientation change the layout manager
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            heroRecycler?.setLayoutManager(LinearLayoutManager(this))
        } else {
            heroRecycler?.setLayoutManager(GridLayoutManager(this, 2))
        }
        heroRecycler?.setAdapter(herosAdapter)

    }

}

