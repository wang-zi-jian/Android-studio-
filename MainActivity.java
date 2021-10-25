package ch.neet.wzj.counter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ch.neet.wzj.counter.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //创建Button对象   也就是activity_main.xml里所设置的ID
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_pt;
    Button btn_mul, btn_div, btn_add, btn_sub;
    Button btn_clr, btn_sqrt, btn_eq, bth_re;
    EditText et_input;
    boolean clr_flag;    //判断et编辑文本框中是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化对象
        setContentView(R.layout.activity_main);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_pt = (Button) findViewById(R.id.btn_pt);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_clr = (Button) findViewById(R.id.btn_clr);
        btn_sqrt = (Button) findViewById(R.id.btn_sqrt);
        btn_eq = (Button) findViewById(R.id.btn_eq);
        bth_re = (Button) findViewById(R.id.btn_re);
        et_input = (EditText) findViewById(R.id.et_input);

        //给按钮设置的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_pt.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_sqrt.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
        bth_re.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        double a = 0;
        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_pt:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) v).getText());
                break;
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                }
                if (str.contains("+") || str.contains("-") || str.contains("×") || str.contains("÷")) {
                    str = str.substring(0, str.indexOf(" "));
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.btn_clr:
                if (clr_flag)
                    clr_flag = false;
                str = "";
                et_input.setText("");
                break;

            case R.id.btn_re:
                a = Double.parseDouble(str);
                a=0-a;
                et_input.setText((int)a+"");
                break;

            case R.id.btn_eq: //单独运算最后结果
                getResult();//调用下面的方法
                break;
            case R.id.btn_sqrt:
                a = Double.parseDouble(str);
                if (a < 0) {
                    //root can't be negative
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                } else {
                    et_input.setText(Math.sqrt(a) + "");
                }
                break;
        }
    }

    private void getResult() {
        String exp = et_input.getText().toString();
        if (exp == null || exp.equals("")) return;
        //因为没有运算符所以不用运算
        if (!exp.contains(" ")) {
            return;
        }
        if (clr_flag) {
            clr_flag = false;
            return;
        }
        clr_flag = true;
        //截取运算符前面的字符串
        String s1 = exp.substring(0, exp.indexOf(" "));
        //截取的运算符
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        //截取运算符后面的字符串
        String s2 = exp.substring(exp.indexOf(" ") + 3);
        double cnt = 0;
        if (!s1.equals("") && !s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                cnt = d1 + d2;
            }
            if (op.equals("-")) {
                cnt = d1 - d2;
            }
            if (op.equals("×")) {
                cnt = d1 * d2;
            }
            if (op.equals("÷")) {
                if (d2 == 0)
                    Toast.makeText(MainActivity.this, "错误！", Toast.LENGTH_LONG).show();
                else cnt = d1 / d2;
            }
            if (op.equals("+-")) {
                cnt = d1 * (-1);
            }
            if (!s1.contains(".") && !s2.contains(".") && !op.equals("÷")) {
                int res = (int) cnt;
                et_input.setText(res + "");
            } else {
                et_input.setText(cnt + "");
            }
        }
        //如果s1不是是空    s2是空  就执行下一步
        else if (!s1.equals("") && s2.equals("")) {
            Toast.makeText(MainActivity.this, "0不能作为除数！", Toast.LENGTH_LONG).show();
            double d1 = Double.parseDouble(s1);
            if (op.equals("+")) {
                cnt = d1;
            }
            if (op.equals("-")) {
                cnt = d1;
            }
            if (op.equals("×")) {
                cnt = 0;
            }
            if (op.equals("÷")) {
                cnt = 0;
            }
            if (op.equals("+-")) {
                cnt = 0;
            }
            if (!s1.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res + "");
            } else {
                et_input.setText(cnt + "");
            }
        }
        //如果s1是空    s2不是空  就执行下一步
        else if (s1.equals("") && !s2.equals("")) {
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                cnt = d2;
            }
            if (op.equals("-")) {
                cnt = 0 - d2;
            }
            if (op.equals("×")) {
                cnt = 0;
            }
            if (op.equals("÷")) {
                cnt = 0;
            }
            if (op.equals("+-")) {
                cnt = 0 - d2;
            }
            if (!s2.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res + "");
            } else {
                et_input.setText(cnt + "");
            }
        } else {
            et_input.setText("");
            Toast.makeText(MainActivity.this, "错误！", Toast.LENGTH_LONG).show();
        }
    }

    public int turn(int a) {
        a = ~a + 1;
        return a;

    }
}
