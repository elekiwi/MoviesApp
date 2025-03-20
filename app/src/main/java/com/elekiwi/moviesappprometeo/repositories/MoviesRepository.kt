package com.elekiwi.moviesappprometeo.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elekiwi.moviesappprometeo.domain.MovieItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MoviesRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadMovies(): LiveData<MutableList<MovieItemModel>> {
        val moviesLiveData = MutableLiveData<MutableList<MovieItemModel>>()
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<MovieItemModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(MovieItemModel::class.java)
                    item?.let {
                        lists.add(it)
                    }
                    moviesLiveData.value = lists
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return moviesLiveData
    }
}