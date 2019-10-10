국민대학교 2019년도 2학기 모바일 프로그래밍 20150803 법학과 육심현 과제입니다. 

<실행환경>
운영체제: GNU/Linux x86_64, POP!_OS (Ubuntu based distribution with GNOME desktop manager) https://system76.com/pop

SDK 플랫폼: Android 10.0(Q)
Andorid SDK bulid tools: 29.0.2
Android Emulator: 29.2.1
Android SDK platform tools: 29.0.4
Android SDK Tools: 26.1.1

JDK: {
  openjdk version "11.0.4" 2019-07-16
  OpenJDK Runtime Environment (build 11.0.4+11-post-Ubuntu-1ubuntu219.04)
  OpenJDK 64-Bit Server VM (build 11.0.4+11-post-Ubuntu-1ubuntu219.04, mixed mode, sharing)
}

<구현 내용>

1. Content.xml + MainActivity.kt
 - 개요: 첫번째 화면, 사용자로부터 아이디, 비밀번호를 입력받고 미리 읽어둔 로컬 파일과 정보를 대조하여 다음 액티비티로 전환할지 결정함. 또는 회원가입 액티비티로 화면을 전환.
 
 - 레이아웃: RelativeLayout - 아이디와 패스워드에 각각 EditView와 TextView 하나씩으로 구성. 로그인과 회원가입은 Button으로 구성함. 
 
 - 스크립트: MainActivity.kt {
     onCreate - 로그인과 회원가입 버튼에 OnClickLister를 설정함. 로그인시에는 로컬 파일 정보와 대조하여 아이디와 패스워드가 일치시 Intent로 calculator 액티비티를 호출함.
     onPause  - 아이디와 비밀번호 TextView에 입력된 Text를 지움.
 }
 
 2. sign_up_screen.xml + SignUpActivity.kt
  - 개요: 두번쨰 화면, 사용자로부터 희망하는 아이디, 패스워드와 이름, 주소, 전화번호를 입력받고 정보를 로컬 파일에 저장함. 아이디와 비밀번호는 일정 조건을 달성해야 함. 마지막으로 개인 정보 이용 약관에 동의해야 회원가입을 완료할 수 있음. 
  
  - 레이아웃: LinearLayout - Root 레이아웃으로 LinearLayout이 있고 그 하위로 다수의 LinearLayout에 TextView와 EditView가 한줄로 정렬되는 구조로 구성. 아이디, 비밀번호의 조건을 표시하는 TextView와 회원가입을 완료하는 Button은 root바로 아래에 구성함. 
  
  - 스크립트: SignUpActivity.kt {
     onCreate - { 
         아이디 입력칸에 TextChangedListener를 등록함. 텍스트가 바뀐 후에는 코틀린 정규표현식을 통해서 조건을 검사하고 부족한 조건에 맞추어 TextView로 표현함. 비밀번호 입력칸에도 TextChangedListener를 등록하여 정규표현식으로 조건을 검사함. 회원가입 버튼에는 OnCLickListener를 등록하여 정보 입력, 개인정보 이용 동의 여부에 따라서 Toast로 문자열 표시함. 조건이 모두 만족된 경우에는 정보를 로컬파일에 저장함. 
     }
     checkFinishable(): Boolean - 
  }
  
  3. calculator.xml + calculator.kt {
   - 개요:
   
   - 레이아웃:
   
   - 스크립트: calculator.kt 
  }
  
  4. UserInformation.kt
  
  5. UserDataSets.kt
  
  6. UserDataManager.kt
