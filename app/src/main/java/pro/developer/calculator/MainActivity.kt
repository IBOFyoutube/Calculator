package pro.developer.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
import javax.xml.xpath.XPathExpression

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        // chaqiruv
        val txt_result = findViewById<TextView>(R.id.txt_result)
        val working_result = findViewById<TextView>(R.id.working_result)
        // Numbers
        var btn_0 = findViewById<Button>(R.id.btn_0)
        var btn_1 = findViewById<Button>(R.id.btn_1)
        var btn_2 = findViewById<Button>(R.id.btn_2)
        var btn_3 = findViewById<Button>(R.id.btn_3)
        var btn_4 = findViewById<Button>(R.id.btn_4)
        var btn_5 = findViewById<Button>(R.id.btn_5)
        var btn_6 = findViewById<Button>(R.id.btn_6)
        var btn_7 = findViewById<Button>(R.id.btn_7)
        var btn_8 = findViewById<Button>(R.id.btn_8)
        var btn_9 = findViewById<Button>(R.id.btn_9)
        // Buttons first
        var btn_clear = findViewById<Button>(R.id.btn_clear)
        var btn_qavs1 = findViewById<Button>(R.id.btn_qavs1)
        var btn_qavs2 = findViewById<Button>(R.id.btn_qavs2)
        var btn_divide = findViewById<Button>(R.id.btn_divide)
        // Buttons second
        var btn_multiply = findViewById<Button>(R.id.btn_multiply)
        var btn_minus = findViewById<Button>(R.id.btn_minus)
        var btn_plus = findViewById<Button>(R.id.btn_plus)
        // Buttons third
        var btn_dot = findViewById<Button>(R.id.btn_dot)
        var btn_back = findViewById<Button>(R.id.btn_back)
        var btn_equals = findViewById<Button>(R.id.btn_equals)




        // clear result
        btn_clear.setOnClickListener {
            working_result.setText("")
            txt_result.setText("")
            true
        }

        // symbols
        btn_qavs1.setOnClickListener {
            working_result.text=addInputText("(")
        }
        btn_qavs2.setOnClickListener {
            working_result.text=addInputText(")")
        }
        btn_dot.setOnClickListener {
            working_result.text=addInputText(".")
        }
        btn_plus.setOnClickListener {
            working_result.text=addInputText("+")
        }
        btn_minus.setOnClickListener {
            working_result.text=addInputText("-")
        }
        btn_divide.setOnClickListener {
            working_result.text=addInputText("÷")
        }
        btn_multiply.setOnClickListener {
            working_result.text=addInputText("x")
        }

        // numbers
        btn_0.setOnClickListener {
            working_result.text=addInputText("0")
        }
        btn_1.setOnClickListener {
            working_result.text=addInputText("1")
        }
        btn_2.setOnClickListener {
            working_result.text=addInputText("2")
        }
        btn_3.setOnClickListener {
            working_result.text=addInputText("3")
        }
        btn_4.setOnClickListener {
            working_result.text=addInputText("4")
        }
        btn_5.setOnClickListener {
            working_result.text=addInputText("5")
        }
        btn_6.setOnClickListener {
            working_result.text=addInputText("6")
        }
        btn_7.setOnClickListener {
            working_result.text=addInputText("7")
        }
        btn_8.setOnClickListener {
            working_result.text=addInputText("8")
        }
        btn_9.setOnClickListener {
            working_result.text=addInputText("9")
        }
        /// press equals result
        btn_equals.setOnClickListener {
            showResult()
        }

        //press back button
        btn_back.setOnClickListener {
         backSpaceAction()
        }
    }

    // functions
    private fun addInputText(buttonValue:String):String{

        val working_result = findViewById<TextView>(R.id.working_result)

        return "${working_result.text} $buttonValue"

    }


    private fun getInputExpression():String{
        val working_result = findViewById<TextView>(R.id.working_result)

        var expression=working_result.text.replace(Regex("÷"),"/")
        expression=expression.replace(Regex("x"),"*")
        return expression
    }

    private fun showResult() {

        val txt_result = findViewById<TextView>(R.id.txt_result)
        val working_result = findViewById<TextView>(R.id.working_result)

        try {

            val expression=getInputExpression()
            val result = Expression(expression).calculate()

            if (result.isNaN()){
                // Show Error Message
                txt_result.text="Error"
                txt_result.setTextColor(ContextCompat.getColor(this,R.color.red))

            }else{
                // Show Result
                txt_result.text=DecimalFormat("0.######").format(result).toString()
                txt_result.setTextColor(ContextCompat.getColor(this,R.color.green))



            }

        }catch (e:Exception){
            // Show Error Message
            txt_result.text="Error"
            txt_result.setTextColor(ContextCompat.getColor(this,R.color.red))

        }
    }

    private fun backSpaceAction(){
        val working_result = findViewById<TextView>(R.id.working_result)
        val length= working_result.length()
        if (length>0){
            working_result.text=working_result.text.subSequence(0,length-1)
        }
    }
}