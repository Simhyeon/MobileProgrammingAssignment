package com.example.assignment

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import java.io.FileNotFoundException

class UserDataManager{

    companion object {
        val gson: Gson =  Gson()
        var dataSets: UserDataSets? = null

        fun updateData(context: Context, data: UserInformation) {
            dataSets?.addData(data)
            context.openFileOutput(context.getString(R.string.defaultUserDataFile), Context.MODE_PRIVATE).use {
                it.write(gson.toJson(dataSets).toByteArray())
            }
        }
    }

    fun initialFileCheck(context: Context) {
        if (!fileExists(context)) {
            val sets = mutableListOf(UserInformation("tst","tst","tst","tst", "tst"))
            dataSets = UserDataSets(sets)
            context.openFileOutput(context.getString(R.string.defaultUserDataFile), Context.MODE_PRIVATE).use {
                it.write(gson.toJson(dataSets).toByteArray())
            }
        } else {
            dataSets = loadData(context)
            if(dataSets == null) {
                throw Exception("Failed to read a file somehow... ¯\\_(ツ)_/¯")
            }
        }

        //Toast.makeText(context, "$dataSets", Toast.LENGTH_LONG).show()
    }


    private fun loadData(context: Context): UserDataSets? =
        context.openFileInput(context.getString(R.string.defaultUserDataFile)).use {
            return@use gson.fromJson(it.readBytes().toString(Charsets.UTF_8), UserDataSets::class.java)
        }

    private fun fileExists(context: Context): Boolean {
        val file = context.getFileStreamPath(context.getString(R.string.defaultUserDataFile))
        return !(file == null || !file.exists())
    }
}
