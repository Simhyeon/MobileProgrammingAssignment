package com.example.assignment

data class UserDataSets(var list: MutableList<UserInformation>) {

    fun findDuplicate(inputId: String): UserInformation? =
        list.find { return@find it.id == inputId }

    fun validate(inputId: String, inputPwd: String): UserInformation? =
        list.find { return@find it.id == inputId && it.password == inputPwd }

    fun addData(info:UserInformation) { list.add(info)}
}