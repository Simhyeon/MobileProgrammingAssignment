package com.example.assignment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.calculator.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class CalculatorActivity: AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)
        initListener()
    }

    override fun onClick(v: View?) {
        //Toast.makeText(applicationContext, "Clicked ${v?.id}", Toast.LENGTH_SHORT).show()
        when(v?.id) {
            R.id.cal_0 -> if ( formula_view.text.toString() == "0" ) return else formula_view.append("0")
            R.id.cal_1 -> formula_view.append("1")
            R.id.cal_2 -> formula_view.append("2")
            R.id.cal_3 -> formula_view.append("3")
            R.id.cal_4 -> formula_view.append("4")
            R.id.cal_5 -> formula_view.append("5")
            R.id.cal_6 -> formula_view.append("6")
            R.id.cal_7 -> formula_view.append("7")
            R.id.cal_8 -> formula_view.append("8")
            R.id.cal_9 -> formula_view.append("9")
            R.id.cal_dot -> if ( formula_view.text.isEmpty() ) return else formula_view.append(".")
            R.id.cal_parenthesis_left -> formula_view.append("(")
            R.id.cal_parenthesis_right -> formula_view.append(")")
            R.id.cal_plus -> formula_view.append("+")
            R.id.cal_minus -> formula_view.append("-")
            R.id.cal_multiply -> if ( formula_view.text.isEmpty() ) return else formula_view.append("*")
            R.id.cal_divide -> if ( formula_view.text.isEmpty() ) return else formula_view.append("/")
            R.id.cal_clear -> formula_view.text = ""
            R.id.cal_modulo -> if ( formula_view.text.isEmpty() ) return else formula_view.append("%")
            R.id.cal_enter -> if ( formula_view.text.isEmpty() ) return else calculate()
        }
    }

    private fun calculate() {
        val txt = formula_view.text.toString()
        // Create an Expression (A class from exp4j library)
        val expression = ExpressionBuilder(txt).build()
        try {
            // Calculate the result and display
            val result = expression.evaluate()
            formula_view.text = result.toString()
        } catch (ex: ArithmeticException) {
            // Display an error message
            formula_view.text = "ERR"
        } catch (err: Exception) {
            formula_view.text = "ERR"
        }
    }

    private fun initListener() {
        cal_0.setOnClickListener(this)
        cal_1.setOnClickListener(this)
        cal_2.setOnClickListener(this)
        cal_3.setOnClickListener(this)
        cal_4.setOnClickListener(this)
        cal_5.setOnClickListener(this)
        cal_6.setOnClickListener(this)
        cal_7.setOnClickListener(this)
        cal_8.setOnClickListener(this)
        cal_9.setOnClickListener(this)
        cal_dot.setOnClickListener(this)
        cal_parenthesis_left.setOnClickListener(this)
        cal_parenthesis_right.setOnClickListener(this)
        cal_plus.setOnClickListener(this)
        cal_minus.setOnClickListener(this)
        cal_multiply.setOnClickListener(this)
        cal_divide.setOnClickListener(this)
        cal_clear.setOnClickListener(this)
        cal_modulo.setOnClickListener(this)
        cal_enter.setOnClickListener(this)
    }
}