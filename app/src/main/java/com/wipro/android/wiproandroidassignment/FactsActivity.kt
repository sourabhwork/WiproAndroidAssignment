package com.wipro.android.wiproandroidassignment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.infosys.telstra.android.telstraandroidassignment.view.FactsFragment

/**
 * Activity class containing fragment
 */
class FactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_container, FactsFragment()).commit()
        }
    }
}
