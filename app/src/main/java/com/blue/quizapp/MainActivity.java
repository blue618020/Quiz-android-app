package com.blue.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blue.quizapp.model.Quiz;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 화면 관련 멤버변수
    TextView txtQuiz;
    ProgressBar progressBar;
    TextView txtResult;
    Button btnTrue;
    Button btnFalse;

    // 퀴즈 저장용 멤버변수 (어레이리스트 사용)
    // new 로 객체생성을 꼭 해줘야함!
    // Quiz.java 클래스파일에서 만든 함수를 가져옴
    ArrayList<Quiz> quizArrayList = new ArrayList<Quiz>();

    // 위에서 ArrayList<Quiz> 를 사용해서 퀴즈를 담은 리스트를 만들었으니, Quiz 선언
    // quiz 의 타입은 Quiz
    Quiz quiz;

    // 퀴즈 문제번호 증가용 멤버변수
    // 문제번호 증가와 진행바 증가에 사용됨
    int currentQuizIndex = 0;

    // 정답 카운트용 멤버변수
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 얘는 왜 new 를 사용하지 않았는가
        // => 화면 뷰인 xml 파일에서 만든걸 가져온거니까..라고 생각하면 될듯
        txtQuiz = findViewById(R.id.txtQuiz);
        progressBar = findViewById(R.id.progressBar);
        txtResult = findViewById(R.id.txtResult);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);


        // 퀴즈 출제하기
        // 퀴즈를 먼저 메모리에 변수로 만들어야 한다.
        // 퀴즈가 여러개니까, 여러개를 하나의 변수로 처리할 수 있는 데이터 스트럭쳐가 필요하고
        // 자바의 가장 유용한 데이터 스트럭쳐, 어레이리스트를 사용한다 (멤버변수 생성)

        // res -> string.xml 파일에 문제 리스트 있음
        // 문제와 정답을 같이 쌍으로 묶음처리해야함 (클래스 새로 만들기)
        // java 파일 -> model 패키지 생성 -> Quiz 클래스 만들기 -> 문제와 답 가져오는 함수 만들기

        setQuiz(); // 얘는 맨 밑에서 만든 함수
        progressBar.setProgress(currentQuizIndex + 1, true);

        // 문제 출제하기
        // cpu 관점으로, 문제가 들어있는곳은 quizArrayList
        quiz = quizArrayList.get(currentQuizIndex);
        txtQuiz.setText(quiz.question); // 퀴즈의 문제를 화면에 표시

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 문제 다 내면 끝내기
                if (!getQuiz()) {
                    return;
                }

                if (quiz.answer == true) {
                    txtResult.setText("정답입니다");
                    count++;  // 맞추면 정답 카운트 증가
                } else {
                    txtResult.setText("오답입니다");
                }
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 문제 다 내면 끝내기
                if (!getQuiz()) {
                    return;
                }

                if (quiz.answer == false) {
                    txtResult.setText("정답입니다");
                    count++;  // 맞추면 정답 카운트 증가
                } else {
                    txtResult.setText("오답입니다");
                }
            }
        });
 }

    private Boolean getQuiz() {

        Log.i("Quiz MAIN", ""+currentQuizIndex);

        // 10문제 다 내면 끝내기
        if (currentQuizIndex >= quizArrayList.size()){

            txtResult.setText("맞춘 정답 개수는 "+ count +"개 입니다.");

            // 팝업띄우기
            // 맨 하단에 함수 새로 만들어서 여기에 넣기
            showAlertDialog();

            return false;
        }

        // 다음문제 출제와 진행바 증가
        quiz = quizArrayList.get(currentQuizIndex);
        txtQuiz.setText(quiz.question);
        progressBar.setProgress(currentQuizIndex + 1, true);

        currentQuizIndex++;
        return true;
    }


        // 문제와 답을 모아놓은 함수 생성
        // 퍼블릭 사용하지 않음(어차피 이 파일 안에서 사용하니깐)
        void setQuiz() {
            Quiz q1 = new Quiz(R.string.q1, true);
            quizArrayList.add(q1);

            Quiz q2 = new Quiz(R.string.q2, false);
            quizArrayList.add(q2);

            Quiz q3 = new Quiz(R.string.q3, true);
            quizArrayList.add(q3);

            Quiz q4 = new Quiz(R.string.q4, false);
            quizArrayList.add(q4);

            Quiz q5 = new Quiz(R.string.q5, true);
            quizArrayList.add(q5);

            Quiz q6 = new Quiz(R.string.q6, false);
            quizArrayList.add(q6);

            Quiz q7 = new Quiz(R.string.q7, true);
            quizArrayList.add(q7);

            Quiz q8 = new Quiz(R.string.q8, false);
            quizArrayList.add(q8);

            Quiz q9 = new Quiz(R.string.q9, true);
            quizArrayList.add(q9);

            Quiz q10 = new Quiz(R.string.q10, false);
            quizArrayList.add(q10);
        }


    // AlertDialog 알러트 다이얼로그 만들기
    // 앱 화면에 팝업으로 뜸
    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("퀴즈 끝!");
        builder.setMessage("맞춘 정답 개수는 "+ count +"개 입니다.");

        builder.setCancelable(false); // 아래 둘 중 한개의 버튼을 꼭 누르게 해줌(외각을 클릭해도 안사라지게)

        // 긍정버튼
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 확인버튼을 누르면 퀴즈를 다시 처음부터 시작하게 하기 (초기화)

                currentQuizIndex = 0;
                Quiz quiz = quizArrayList.get(currentQuizIndex);
                txtQuiz.setText(quiz.question);
                txtResult.setText("결과");
                progressBar.setProgress(currentQuizIndex+1, true);
                count = 0;

            }
        });

        // 부정버튼
        builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 종료버튼을 누르면 현재 엑티비티(MainActivity)를 종료시키기
                finish();
            }
        });

        builder.show(); // 화면띄우기
    }


 }