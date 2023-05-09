package com.example.midterm_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ContentInfoCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack; //import

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Float mem = null;
    BigDecimal bmem = null;
    BigDecimal b1, b2;
    TextView tv_down, tv_up;
    Button btn_MC, btn_MR;
    Integer isFirst = 0; // 처음 0이 있을 시 맨 앞 0을 지우고 입력
    Integer isEntered = 0; // 숫자 입력 안했을 시 연속으로 계산하는 것을 방지
    Integer isPointEntered = 0;
    Integer isMemoryCalculated = 0;
    Integer isCalculated = 0;

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
        if(isFirst == 1) // 처음 입력 시 0을 지우고 숫자 출력
        {
            tv_down.setText(b.getText().toString());
            isFirst = 0;
        }
        else if(isMemoryCalculated == 1)
        {
            tv_down.setText(b.getText().toString());
            isMemoryCalculated = 0;
        }
        else if(isCalculated == 1)
        {
            android.util.Log.i("ccc", "ccc");
            tv_up.setText(null);
            tv_down.setText(b.getText().toString());
            isCalculated = 0;
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
        isFirst = 0;
        isPointEntered = 0;

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

            if(!subText.contains(".")){
                isPointEntered = 0;
            }
        }
    }
    public void operator(View v)
    {
        Button b= (Button)findViewById(v.getId());
        String text_up = tv_up.getText().toString();
        String text_down = tv_down.getText().toString();
        String bstr;
        String oper = b.getText().toString();

        BigDecimal result_Big = null;
        if(text_up.isEmpty())
        {
            tv_up.setText(tv_down.getText().toString() +oper);
        }
        else if(isEntered == 0) // 연산자 연속 클릭 시 계산 방지
        {
            bstr = text_up.substring(0, text_up.length()-1);
            tv_up.setText(bstr + oper);
        }
        else if(text_up.contains("="))
        {
            tv_up.setText(tv_down.getText().toString() + oper);
        }
        else
        {
            if(text_up.charAt(text_up.length()-1) == '=')
            {
                tv_up.setText(text_down + oper);
            }
            else
            {
                b1 = new BigDecimal(text_up.substring(0, text_up.length()-1));
                b2 = new BigDecimal(tv_down.getText().toString());

                if(text_up.charAt(text_up.length()-1) == '+')
                {
                    result_Big = b1.add(b2);
                }
                else if(text_up.charAt(text_up.length()-1) == '-')
                {
                    result_Big = b1.subtract(b2);
                }
                else if(text_up.charAt(text_up.length()-1) == 'x')
                {
                    result_Big = b1.multiply(b2);
                }
                else if(text_up.charAt(text_up.length()-1) == '÷')
                {
                    result_Big = b1.divide(b2, 10, RoundingMode.HALF_UP);
                }
                String num2 = (result_Big.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros()).toPlainString();
                tv_up.setText(num2+oper);
                tv_down.setText(num2);
            }
        }
        isFirst = 1;
        isEntered = 0;
        isPointEntered = 0;
        isCalculated = 0;
    }
    public void equal(View v)
    {
        String text_up = tv_up.getText().toString();
        String text_down = tv_down.getText().toString();
        BigDecimal result_Big = null;
        if(text_up.isEmpty())
        {
            tv_up.setText(tv_down.getText().toString()+"=");
        }
        else if(text_up.charAt(text_up.length()-1) == '=')
        {
            b2 = new BigDecimal(text_down);
            if(text_up.indexOf('+') == -1 && text_up.indexOf('-') == -1 && text_up.indexOf('x') == -1 && text_up.indexOf('÷') == -1)
            {
                return;
            }
            else if(text_up.indexOf('+') != -1)
            {
                String n = text_up.substring(text_up.indexOf('+')+1, text_up.length()-1);
                b1 = new BigDecimal(n);
                result_Big = b1.add(b2);
                tv_up.setText(text_down + "+"+n +"=");
            }
            else if(text_up.lastIndexOf('-') != -1)
            {
                String n = text_up.substring(text_up.lastIndexOf('-')+1, text_up.length()-1);
                b1 = new BigDecimal(n);
                result_Big = b2.subtract(b1);
                tv_up.setText(text_down + "-"+n +"=");
            }
            else if(text_up.indexOf('x') != -1)
            {
                String n = text_up.substring(text_up.lastIndexOf('x')+1, text_up.length()-1);
                b1 = new BigDecimal(n);
                result_Big = b1.multiply(b2);
                tv_up.setText(text_down + "x"+n +"=");
            }
            else if(text_up.indexOf('÷') != -1)
            {
                String n = text_up.substring(text_up.lastIndexOf('÷')+1, text_up.length()-1);
                b1 = new BigDecimal(n);
                result_Big = b2.divide(b1, 10, RoundingMode.HALF_UP);
                tv_up.setText(text_down + "÷"+n +"=");
            }
            String num2 = (result_Big.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros()).toPlainString();
            tv_down.setText(num2);
        }
        else
        {
            b1 = new BigDecimal(text_up.substring(0, text_up.length()-1));
            b2 = new BigDecimal(tv_down.getText().toString());
            tv_up.setText(text_up + text_down +"=");
            if(text_up.charAt(text_up.length()-1) == '+')
            {
                result_Big = b1.add(b2);
            }
            else if(text_up.charAt(text_up.length()-1) == '-')
            {
                result_Big = b1.subtract(b2);
            }
            else if(text_up.charAt(text_up.length()-1) == 'x')
            {
                result_Big = b1.multiply(b2);
            }
            else if(text_up.charAt(text_up.length()-1) == '÷')
            {
                result_Big = b1.divide(b2, 10, RoundingMode.HALF_UP);
            }
            String num2 = (result_Big.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros()).toPlainString();
            tv_down.setText(num2);
        }
        isEntered = 1;
        isCalculated = 1;
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

        if(isFirst == 1)
        {
            tv_down.setText("0.");
        }
        else if(isMemoryCalculated == 1)
        {
            tv_down.setText("0.");
            isMemoryCalculated = 0;
        }
        else if(isCalculated == 1)
        {
            tv_up.setText(null);
            tv_down.setText("0.");
            isCalculated = 0;
        }
        else if(isPointEntered == 0)
        {
            tv_down.setText(text_down+".");
            isPointEntered = 1;
        }

        isFirst = 0;
        isCalculated = 0;
        isEntered = 1;
    }
    public void memoryPlus(View v)
    {
        BigDecimal temp;
        if(bmem == null)
        {
            bmem = new BigDecimal(tv_down.getText().toString());
        }
        else
        {
            temp = new BigDecimal(tv_down.getText().toString());
            bmem = bmem.add(temp);
        }
        btn_MC.setEnabled(true);
        btn_MR.setEnabled(true);
        isMemoryCalculated = 1;
    }
    public void memoryMinus(View v)
    {
        BigDecimal temp;
        if(bmem == null)
        {
            bmem = new BigDecimal(tv_down.getText().toString());
        }
        else
        {
            temp = new BigDecimal(tv_down.getText().toString());
            bmem = bmem.subtract(temp);
        }
        btn_MC.setEnabled(true);
        btn_MR.setEnabled(true);
        isMemoryCalculated = 1;
    }
    public void memoryRead(View v)
    {
        //String m = bmem.toString();
        String num2 = (bmem.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros()).toPlainString();
        tv_up.setText(null);
        tv_down.setText(num2.toString());
        isMemoryCalculated = 1;
    }
    public void memoryClear(View v)
    {
        bmem = null;
        btn_MC.setEnabled(false);
        btn_MR.setEnabled(false);
        isMemoryCalculated = 1;
    }
    public void memorySet(View v)
    {
        bmem = new BigDecimal(tv_down.getText().toString());
        btn_MC.setEnabled(true);
        btn_MR.setEnabled(true);
        isMemoryCalculated = 1;
    }
}