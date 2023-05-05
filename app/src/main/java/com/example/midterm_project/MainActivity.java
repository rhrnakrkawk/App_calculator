package com.example.midterm_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ContentInfoCompat;

import java.util.Stack; //import

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Float mem = null;
    Float n1, n2;
    TextView tv_down, tv_up;
    Button btn_MC, btn_MR;
    Integer input_flag = 0; // 처음 0이 있을 시 맨 앞 0을 지우고 입력
    Integer isEntered = 0; // 숫자 입력 안했을 시 연속으로 계산하는 것을 방지
    Integer isPointEntered = 0;
    Integer isMemoryCalculated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         tv_down= (TextView) findViewById(R.id.tv_downtext);
         tv_up = (TextView) findViewById(R.id.tv_uptext);
         btn_MC = (Button) findViewById(R.id.btn_MC);
         btn_MR = (Button) findViewById(R.id.btn_MR);
    }
    public void clickNumber(View v)
    {
        Button b= (Button)findViewById(v.getId());
        String text = tv_down.getText().toString();
        if(input_flag == 1) // 처음 입력 시 0을 지우고 숫자 출력
        {
            tv_down.setText(b.getText().toString());
            input_flag = 0;
        }
        else if(isMemoryCalculated == 1)
        {
            tv_down.setText(b.getText().toString());
            isMemoryCalculated = 0;
        }
        else
        {
            if(text.charAt(0) == '0' && text.length() == 1)
            {
                tv_down.setText(b.getText().toString());
            }
            else {
                tv_down.setText(tv_down.getText().toString() + b.getText().toString());

            }
        }
        isEntered = 1;
    }
    public void allClear(View v)
    {
        tv_down.setText("0");
        tv_up.setText(null);
        input_flag = 0;
    }
    public void tv_upClear(View v)
    {
        tv_down.setText("0");
    }
    public void BackSpace(View v)
    {
        String text = tv_down.getText().toString();
        String subText;
        if(text.isEmpty())
        {
            tv_down.setText("0");
        }
        else if(text.charAt(0) == '0' && text.length() == 1)
        {
            return;
        }
        else
        {
            subText = text.substring(0, text.length()-1);
            if(subText.isEmpty())
                tv_down.setText("0");
            else
                tv_down.setText(text.substring(0, text.length()-1));
        }
    }
    public void operator(View v)
    {
        Button b= (Button)findViewById(v.getId());
        String text_up = tv_up.getText().toString();
        String text_down = tv_down.getText().toString();
        String str;
        String oper = b.getText().toString();
        Float result_Float;
        Integer result_Int;
        if(text_up.isEmpty())
        {
            tv_up.setText(tv_down.getText().toString() +oper);
        }
        else if(isEntered == 0) // 연산자 연속 클릭 시 계산 방지
        {
            str = text_up.substring(0, text_up.length()-1);
            tv_up.setText(str + oper);
        }
        else if(text_up.contains("="))
        {
            tv_up.setText(tv_down.getText().toString() + oper);
        }
        else
        {
            n1 = Float.parseFloat(text_up.substring(0, text_up.length()-1));
            n2 = Float.parseFloat(tv_down.getText().toString());
            if(text_up.charAt(text_up.length()-1) == '+')
            {
                result_Float = n1 + n2;
                str = result_Float.toString();

                str = str.substring(str.indexOf(".")+1, str.length());
                if(str.equals("0"))
                {
                    result_Int = Math.round(result_Float);
                    tv_up.setText(Integer.toString(result_Int) + oper);
                    tv_down.setText(Integer.toString(result_Int));
                }
                else
                {
                    tv_up.setText(Float.toString(result_Float) + oper);
                    tv_down.setText(Float.toString(result_Float));
                }
            }
            else if(text_up.charAt(text_up.length()-1) == '-')
            {
                result_Float = n1 - n2;
                str = result_Float.toString();

                str = str.substring(str.indexOf(".")+1, str.length());
                if(str.equals("0"))
                {
                    result_Int = Math.round(result_Float);
                    tv_up.setText(Integer.toString(result_Int) + oper);
                    tv_down.setText(Integer.toString(result_Int));
                }
                else
                {
                    tv_up.setText(Float.toString(result_Float) + oper);
                    tv_down.setText(Float.toString(result_Float));
                }
            }
            else if(text_up.charAt(text_up.length()-1) == 'x')
            {
                result_Float = n1 * n2;
                str = result_Float.toString();

                str = str.substring(str.indexOf(".")+1, str.length());
                if(str.equals("0"))
                {
                    result_Int = Math.round(result_Float);
                    tv_up.setText(Integer.toString(result_Int) + oper);
                    tv_down.setText(Integer.toString(result_Int));
                }
                else
                {
                    tv_up.setText(Float.toString(result_Float) + oper);
                    tv_down.setText(Float.toString(result_Float));
                }
            }
            else if(text_up.charAt(text_up.length()-1) == '÷')
            {
                result_Float = n1 / n2;
                str = result_Float.toString();

                str = str.substring(str.indexOf(".")+1, str.length());
                if(str.equals("0"))
                {
                    result_Int = Math.round(result_Float);
                    tv_up.setText(Integer.toString(result_Int) + oper);
                    tv_down.setText(Integer.toString(result_Int));
                }
                else
                {
                    tv_up.setText(Float.toString(result_Float) + oper);
                    tv_down.setText(Float.toString(result_Float));
                }
            }
            else if(text_up.charAt(text_up.length()-1) == '=')
            {
                android.util.Log.i("=테스트 ", text_down + oper);
                tv_up.setText(text_down + oper);
            }
        }
        input_flag = 1;
        isEntered = 0;
        isPointEntered = 0;
    }
    public void equal(View v)
    {
        android.util.Log.i("이퀄 테스트", "bb");
        String text_up = tv_up.getText().toString();
        String text_down = tv_down.getText().toString();
        String str;
        Float result_Float;
        Integer result_Int;
        android.util.Log.i("이퀄 테스트", "cc");
        android.util.Log.i("이퀄 테스트", "dd");


        android.util.Log.i("이퀄 테스트", "aa");
        if(text_up.charAt(text_up.length()-1) == '+')
        {
            n1 = Float.parseFloat(text_up.substring(0, text_up.length()-1));
            n2 = Float.parseFloat(tv_down.getText().toString());
            tv_up.setText(text_up + text_down +"=");
            result_Float = n1 + n2;
            str = Float.toString(result_Float);
            str = str.substring(str.indexOf(".") + 1, str.length());
            if(str.equals("0"))
            {
                result_Int = Math.round(result_Float);
                tv_down.setText(Integer.toString(result_Int));
            }
            else
            {
                tv_down.setText(Float.toString(result_Float));
            }
        }
        else if(text_up.charAt(text_up.length()-1) == '-')
        {
            n1 = Float.parseFloat(text_up.substring(0, text_up.length()-1));
            n2 = Float.parseFloat(tv_down.getText().toString());
            tv_up.setText(text_up + text_down +"=");
            result_Float = n1 - n2;
            str = Float.toString(result_Float);
            str = str.substring(str.indexOf(".") + 1, str.length());
            if(str.equals("0"))
            {
                result_Int = Math.round(result_Float);
                tv_down.setText(Integer.toString(result_Int));
            }
            else
            {
                tv_down.setText(Float.toString(result_Float));
            }
        }
        else if(text_up.charAt(text_up.length()-1) == 'x')
        {
            n1 = Float.parseFloat(text_up.substring(0, text_up.length()-1));
            n2 = Float.parseFloat(tv_down.getText().toString());
            tv_up.setText(text_up + text_down +"=");
            result_Float = n1 * n2;
            str = Float.toString(result_Float);
            str = str.substring(str.indexOf(".") + 1, str.length());
            if(str.equals("0"))
            {
                result_Int = Math.round(result_Float);
                tv_down.setText(Integer.toString(result_Int));
            }
            else
            {
                tv_down.setText(Float.toString(result_Float));
            }
        }
        else if(text_up.charAt(text_up.length()-1) == '÷')
        {
            n1 = Float.parseFloat(text_up.substring(0, text_up.length()-1));
            n2 = Float.parseFloat(tv_down.getText().toString());
            tv_up.setText(text_up + text_down +"=");
            result_Float = n1 / n2;
            str = Float.toString(result_Float);
            str = str.substring(str.indexOf(".") + 1, str.length());
            if(str.equals("0"))
            {
                result_Int = Math.round(result_Float);
                tv_down.setText(Integer.toString(result_Int));
            }
            else
            {
                tv_down.setText(Float.toString(result_Float));
            }
        }
        else if(text_up.charAt(text_up.length()-1) == '=')
        {
            android.util.Log.i("이퀄 테스트", "aa");
            if(text_up.indexOf('+') != -1)
            {
                android.util.Log.i("이퀄 테스트", "aa");
                String n = text_up.substring(text_up.indexOf('+')+1, text_up.length()-1);
                n1 = Float.parseFloat(n);
                n2 = Float.parseFloat(text_down);
                result_Float = n1 + n2;

                str = Float.toString(result_Float);
                str = str.substring(str.indexOf(".") + 1, str.length());
                if(str.equals("0"))
                {
                    tv_up.setText(text_down + "+"+n +"=");
                    result_Int = Math.round(result_Float);
                    tv_down.setText(Integer.toString(result_Int));
                }
                else
                {
                    tv_down.setText(Float.toString(result_Float));
                }
            }
            else if(text_up.lastIndexOf('-') != -1)
            {
                android.util.Log.i("이퀄 테스트", "aa");
                String n = text_up.substring(text_up.lastIndexOf('-')+1, text_up.length()-1);
                n2 = Float.parseFloat(n);
                n1 = Float.parseFloat(text_down);
                result_Float = n1 - n2;

                str = Float.toString(result_Float);
                str = str.substring(str.indexOf(".") + 1, str.length());
                if(str.equals("0"))
                {
                    tv_up.setText(text_down + "-"+n +"=");
                    result_Int = Math.round(result_Float);
                    tv_down.setText(Integer.toString(result_Int));
                }
                else
                {
                    tv_down.setText(Float.toString(result_Float));
                }
            }
            else if(text_up.indexOf('x') != -1)
            {
                android.util.Log.i("이퀄 테스트", "aa");
                String n = text_up.substring(text_up.indexOf('x')+1, text_up.length()-1);
                n1 = Float.parseFloat(n);
                n2 = Float.parseFloat(text_down);
                result_Float = n1 * n2;

                str = Float.toString(result_Float);
                str = str.substring(str.indexOf(".") + 1, str.length());
                if(str.equals("0"))
                {
                    tv_up.setText(text_down + "x"+n +"=");
                    result_Int = Math.round(result_Float);
                    tv_down.setText(Integer.toString(result_Int));
                }
                else
                {
                    tv_down.setText(Float.toString(result_Float));
                }
            }
            else if(text_up.indexOf('÷') != -1)
            {

                String n = text_up.substring(text_up.indexOf('÷')+1, text_up.length()-1);
                n2 = Float.parseFloat(n);
                n1 = Float.parseFloat(text_down);
                result_Float = n1 / n2;
                android.util.Log.i("이퀄 테스트", Float.toString(n1)+", " +Float.toString(n2)+ ", " +Float.toString(result_Float));
                str = Float.toString(result_Float);
                str = str.substring(str.indexOf(".") + 1, str.length());
                if(str.equals("0"))
                {
                    tv_up.setText(text_down + "÷"+n +"=");
                    result_Int = Math.round(result_Float);
                    tv_down.setText(Integer.toString(result_Int));
                }
                else
                {
                    tv_down.setText(Float.toString(result_Float));
                }
            }
        }
        isEntered = 1;
    }
    public void signChange(View v)
    {
        String text_down = tv_down.getText().toString();
        if(text_down.charAt(0) == '-')
        {
            tv_down.setText(text_down.substring(1, text_down.length()));
        }else
        {
            tv_down.setText(String.format("%c%s", '-', text_down));
        }

    }
    public void point(View v)
    {
        String text_down = tv_down.getText().toString();

        if(isPointEntered == 0)
        {
            tv_down.setText(text_down+".");
            isPointEntered = 1;
        }
    }
    public void memoryPlus(View v)
    {
        if(mem == null)
        {
            mem = Float.parseFloat(tv_down.getText().toString());

        }
        else
        {
            mem += Float.parseFloat(tv_down.getText().toString());
        }
        btn_MC.setEnabled(true);
        btn_MR.setEnabled(true);
        isMemoryCalculated = 1;
    }
    public void memoryMinus(View v)
    {
        if(mem == null)
        {
            mem = Float.parseFloat(tv_down.getText().toString());
        }
        else
        {
            mem -= Float.parseFloat(tv_down.getText().toString());
        }
        btn_MC.setEnabled(true);
        btn_MR.setEnabled(true);
        isMemoryCalculated = 1;
    }
    public void memoryRead(View v)
    {
        String m = Float.toString(mem);
        String subm = m.substring(m.indexOf(".")+1, m.length());
        Integer i = Math.round(mem);
        android.util.Log.i("MR테스트", subm);
        if(subm.equals("0"))
        {
            android.util.Log.i("MR0테스트", subm);
            tv_up.setText(null);
            tv_down.setText(i.toString());
        }
        else {
            android.util.Log.i("MR1 테스트", subm);
            tv_up.setText(null);
            tv_down.setText(mem.toString());
        }
        isMemoryCalculated = 1;
    }
    public void memoryClear(View v)
    {
        mem = null;
        btn_MC.setEnabled(false);
        btn_MR.setEnabled(false);
        isMemoryCalculated = 1;
    }
    public void memorySet(View v)
    {
        mem = Float.parseFloat(tv_down.getText().toString());
        btn_MC.setEnabled(true);
        btn_MR.setEnabled(true);
        isMemoryCalculated = 1;
    }
}