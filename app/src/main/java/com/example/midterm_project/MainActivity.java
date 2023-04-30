package com.example.midterm_project;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Stack; //import

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Stack<Integer> stack = new Stack<>(); //int형 스택 선언
    Integer num1, num2;
    Integer sum;
    TextView tv_down;
    TextView tv_up;
    Integer input_flag = 0; // 처음 0이 있을 시 맨 앞 0을 지우고 입력
    Integer isEntered = 0; // 숫자 입력 안했을 시 연속으로 계산하는 것을 방지
    Integer isPointEntered = 0;
    String Log = "111111111111111111111111   ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         tv_down= (TextView) findViewById(R.id.tv_downtext);
         tv_up = (TextView) findViewById(R.id.tv_uptext);
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
//        android.util.Log.i("버튼 테스트", tv_down.getText().toString());
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
//            android.util.Log.i("백스페이스 테스트", "bb");
            tv_down.setText("0");
        }
        else if(text.charAt(0) == '0' && text.length() == 1)
        {
//            android.util.Log.i("백스페이스 테스트", "aa");
            return;
        }
        else
        {
            subText = text.substring(0, text.length()-1);
            if(subText.isEmpty())
                tv_down.setText("0");
            else
                tv_down.setText(text.substring(0, text.length()-1));
//            android.util.Log.i("백스페이스 테스트", "cc");
        }
    }
    public void operator(View v)
    {
        Button b= (Button)findViewById(v.getId());
        String text = tv_up.getText().toString();
        String text_down = tv_down.getText().toString();
        String str;
        String oper = b.getText().toString();
        if(text.isEmpty())
        {
            tv_up.setText(tv_down.getText().toString() + " "+oper);
        }
        else if(isEntered == 0) // 연산자 연속 클릭 시 계산 방지
        {
            //android.util.Log.i("오페레이터 테스트", "aa");
            str = text.substring(0, text.length()-1);
            tv_up.setText(str + oper);
        }
        else
        {
            if(text.charAt(0) == '-') // 음수 저장
            {
                num1 = Integer.parseInt(text.replaceAll("[^\\d]", ""));
                num1 *= -1;
            }
            else // 양수 저장
            {
                num1 = Integer.parseInt(text.replaceAll("[^\\d]", ""));
            }
            num2 = Integer.parseInt(tv_down.getText().toString());
            if(text.charAt(text.length()-1) == '+')
            {
                tv_up.setText(Integer.toString(num1 + num2) + " "+oper);
                tv_down.setText(Integer.toString(num1 + num2));
            }
            else if(text.charAt(text.length()-1) == '-')
            {
                tv_up.setText(Integer.toString(num1 - num2) + " "+oper);
                tv_down.setText(Integer.toString(num1 - num2));
            }
            else if(text.charAt(text.length()-1) == 'x')
            {
                android.util.Log.i("오페레이터 테스트", "aa");
                tv_up.setText(Integer.toString(num1 * num2) + " "+oper);
                tv_down.setText(Integer.toString(num1 * num2));
            }
            else if(text.charAt(text.length()-1) == '÷')
            {
                tv_up.setText(Integer.toString(num1 / num2) + " "+oper);
                tv_down.setText(Integer.toString(num1 / num2));
            }
            else if(text.charAt(text.length()-1) == '=')
            {
                android.util.Log.i("=테스트 ", text_down + " " + oper);
                tv_up.setText(text_down + " " + oper);
            }
        }
        input_flag = 1;
        isEntered = 0;
    }
    public void equal(View v)
    {
        String text_up = tv_up.getText().toString();
        num2 = Integer.parseInt(tv_down.getText().toString());
        Integer num1;

        if(text_up.charAt(0) == '-') // 음수 저장
        {
            num1 = Integer.parseInt(text_up.replaceAll("[^\\d]", ""));
            num1 *= -1;
        }
        else // 양수 저장
        {
            num1 = Integer.parseInt(text_up.replaceAll("[^\\d]", ""));
        }

        if(text_up.charAt(text_up.length()-1) == '+')
        {
            tv_up.setText(text_up + " " + Integer.toString(num2) +" =");
            tv_down.setText(Integer.toString(num1 + num2));
        }
        else if(text_up.charAt(text_up.length()-1) == '-')
        {
            tv_up.setText(text_up + " " + Integer.toString(num2) +" =");
            tv_down.setText(Integer.toString(num1 - num2));
        }
        else if(text_up.charAt(text_up.length()-1) == 'x')
        {
            tv_up.setText(text_up + " " + Integer.toString(num2) +" =");
            tv_down.setText(Integer.toString(num1 * num2));
        }
        else if(text_up.charAt(text_up.length()-1) == '÷')
        {
            tv_up.setText(text_up + " " + Integer.toString(num2) +" =");
            tv_down.setText(Integer.toString(num1 / num2));
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



//    public void plus(View v)
//    {
//        String text = tv_up.getText().toString();
//        if(text.isEmpty())
//        {
//            tv_up.setText(tv_down.getText().toString() + " +");
//        }
//        else
//        {
//            num1 = Integer.parseInt(text.replaceAll("[^\\d]", ""));
//            num2 = Integer.parseInt(tv_down.getText().toString());
//            tv_up.setText(Integer.toString(num1 + num2) + " +");
//            tv_down.setText(Integer.toString(num1 + num2));
//        }
//        input_flag = 1;
//    }
//    public void subtract(View v)
//    {
//        String text = tv_up.getText().toString();
//        if(text.isEmpty())
//        {
//            tv_up.setText(tv_down.getText().toString() + " -");
//        }
//        else
//        {
//            num1 = Integer.parseInt(text.replaceAll("[^\\d]", ""));
//            num2 = Integer.parseInt(tv_down.getText().toString());
//            tv_up.setText(Integer.toString(num1 - num2) + " -");
//            tv_down.setText(Integer.toString(num1 - num2));
//        }
//        input_flag = 1;
//    }
//    public void multiply(View v)
//    {
//        String text = tv_up.getText().toString();
//        if(text.isEmpty())
//        {
//            tv_up.setText(tv_down.getText().toString() + " *");
//        }
//        else
//        {
//
//            num1 = Integer.parseInt(text.replaceAll("[^\\d]", ""));
//            num2 = Integer.parseInt(tv_down.getText().toString());
//            tv_up.setText(Integer.toString(num1 * num2) + " *");
//            tv_down.setText(Integer.toString(num1 * num2));
//        }
//        input_flag = 1;
//    }
//    public void divide(View v)
//    {
//        String text = tv_up.getText().toString();
//        if(text.isEmpty())
//        {
//            tv_up.setText(tv_down.getText().toString() + " ÷");
//        }
//        else
//        {
//
//            num1 = Integer.parseInt(text.replaceAll("[^\\d]", ""));
//            num2 = Integer.parseInt(tv_down.getText().toString());
//            tv_up.setText(Integer.toString(num1 / num2) + " ÷");
//            tv_down.setText(Integer.toString(num1 / num2));
//        }
//        input_flag = 1;
//    }


}