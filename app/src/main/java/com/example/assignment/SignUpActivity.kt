package com.example.assignment

import android.os.Bundle
import android.os.PersistableBundle // 어디다 써먹는 건지 한 번 찾아보자.
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.sign_up_screen.*

class SignUpActivity: AppCompatActivity() {

    var isIdValid: Boolean = false
    var isPwdValid: Boolean = false
    val idRegex = Regex("^(?=.*[a-z])(?=\\S+$).{4,12}$")
    val pwdRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[!?@#$%*^&+=])(?=\\S+$).{6,15}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_screen)

        // 아이디를 입력하는 텍스트필드의 조건을 검사하는 함수를 할당함.
        id_inputfield.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                id_input_noti_field.text =
                    when(UserDataManager.dataSets?.findDuplicate(id_inputfield.text.toString())) {
                        null -> {
                            when(idRegex.matches(id_inputfield.text)) {
                                true -> {
                                    isIdValid = true
                                    "사용 가능한 아이디"
                                }
                                false -> {
                                    isIdValid = false
                                    "아이디는 영어 소문자 4~12자로 작성해야 합니다."
                                }
                            }
                        }
                        else -> {
                            isIdValid = false
                            "사용 불가능한 아이디"
                        }
                    }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 비밀번호를 입력하는 텍스트필드의 조건을 검사하는 함수를 할당함.
        password_inputfield.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                pwd_input_noti_field.text =
                    when (pwdRegex.matches(password_inputfield.text)) {
                        true -> {
                            isPwdValid = true
                            "사용 가능한 비밀번호"
                        }
                        false -> {
                            isPwdValid = false
                            "비밀번호는 숫자, 소문자, 특수문자 포함 6~15자로 작성해야 합니다."
                        }
                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        sign_up_complete.setOnClickListener {
            if( !checkFinishable() ) {
                Toast.makeText(applicationContext, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if(!privacy_agree.isChecked) {
                Toast.makeText(applicationContext, "개인정보 이용에 동의해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                UserDataManager.updateData(
                    applicationContext,
                    UserInformation(
                        id_inputfield.text.toString(),
                        password_inputfield.text.toString(),
                        name_inputfield.text.toString(),
                        address_inputfield.text.toString(),
                        phone_number_inputfield.text.toString()
                    )
                )
                finish()
            }
        }
    }
//    private fun checkIdValidation()
//    private fun checkPwdValidation()
    private fun checkFinishable() =
        isIdValid && isPwdValid && name_inputfield.text.isNotEmpty() && address_inputfield.text.isNotEmpty() && phone_number_inputfield.text.isNotEmpty()
}