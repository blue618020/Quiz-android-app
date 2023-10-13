# 퀴즈 앱

📝 <b> tistory : </b> https://blue618020.tistory.com/80

### 🔍 학습 내용
- AlertDialog를 사용하여 팝업 메시지를 띄우기
- progressBar를 사용하여 현재 진행상황을 나타내기
- ArrayList를 사용하여 문제들을 리스트에 담기

### 💻 실습

#### 1. 문제 목록을 저장
- valuse -> strings.xml 파일에 문제와 name을 지정하여 문제 목록 만들어두기

      <string name="q1">A honey bee can fly at 15mph.</string> ...

- java 파일 -> model 패키지 생성 -> Quiz 클래스 만들기 -> 문제와 답을 저장하는 함수를 생성하기(Quiz)
  
- MainAcitvity에서 퀴즈를 담는 ArrayList를 생성

      ArrayList<Quiz> quizArrayList = new ArrayList<Quiz>();

- setQuiz() 함수를 만들어서 quizArrayList에 문제와 답을 저장

      void setQuiz() {
              Quiz q1 = new Quiz(R.string.q1, true);
              quizArrayList.add(q1);
              ...}


#### 2. 문제를 화면에 출력하고 진행바 증가
- 퀴즈 문제번호 증가와 진행바 증가에 사용하는 멤버변수 선언
  
      int currentQuizIndex = 0;
  
- getQuiz() 함수를 만들어서 다음문제 출제와 진행바 증가 코드를 작성

        private Boolean getQuiz() {
  
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


#### 3. 문제가 끝나면 AlertDialog 팝업 띄우기
- 
  
- 
