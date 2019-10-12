## 국민대학교 2019년도 2학기 모바일 프로그래밍 20150803 법학과 육심현 과제

## 구성 환경

- **Kotlin 버전: 1.3.x**
  - 메뉴의 Tools -> Kotlin -> Configure Kotlin Plug Updates -> Update Channel을 Early Access Preview 1.3x으로 설정해야 실행됨.

- 외부 라이브러리 (build.gradle에 포함되어 있음)
  - com.google.code.gson:gson:2.8.6
  - net.objecthunter:exp4j:0.4.8
  
<GNU/Linux>

- 운영체제: POP!_OS (Ubuntu based distribution with GNOME desktop manager) [OS Link](https://system76.com/pop)

- SDK 플랫폼: Android 10.0(Q)
- Andorid SDK bulid tools: 29.0.2
- Android Emulator: 29.2.1
- Android SDK platform tools: 29.0.4
- Android SDK Tools: 26.1.1

- JDK
  - openjdk version "11.0.4" 2019-07-16
  - OpenJDK Runtime Environment (build 11.0.4+11-post-Ubuntu-1ubuntu219.04)
  - OpenJDK 64-Bit Server VM (build 11.0.4+11-post-Ubuntu-1ubuntu219.04, mixed mode, sharing)
  
<\Microsoft/Windows>

- 운영체제: Microsft Windows 10 Pro, 10.0.18362 빌드 18362
  
- SDK 플랫폼: Android 10.0(Q), 9.0(Pie)
- Andorid SDK bulid tools: 29.0.2
- Android Emulator: 29.2.1
- Android SDK platform tools: 29.0.4
- Android SDK Tools: 26.1.1
- Intel x86 Emulator Accelerator: 7.5.2

- JDK
  - java version "13" 2019-09-17
  - Java(TM) SE Runtime Environment (build 13+33)
  - Java HotSpot(TM) 64-Bit Server VM (build 13+33, mixed mode, sharing)

## 프로그램 요약

- 사용자는 회원가입을 할 수 있고 회원가입한 정보를 통해서 로그인 하여 계산기를 사용할 수 있음.
- 프로그램은 사용자 정보를 저장하고, 저장된 정보와 입력을 대조하여 계산기를 사용할 수 있게 함.

## 프로그램 구성

- **첫 번째 화면** ( Content_main.xml + MainActivity.kt )
  - 개요: 사용자로부터 아이디, 비밀번호를 입력받고 미리 읽어둔 로컬 파일과 정보를 대조하여 다음 액티비티로 전환할지 결정함. 또는 회원가입 액티비티로 화면을 전환.

  - 레이아웃: Root ViewGroup은 RelativeLayout, 아이디와 패스워드에 각각 EditView와 TextView 하나씩으로 구성.  
  로그인과 회원가입은 Button으로 구성함.

  - 스크립트( MainActivity.kt )
    - onCreate
      - loginBtn onClickListener : 로컬 파일 정보와 대조하여 아이디와 패스워드가 일치시 calculator 액티비티를 호출함.
      - signUpBtn OnClickLister :  회원정보를 입력하는 액티비티 호출함.
  
    - onPause : 아이디와 비밀번호 TextView에 입력된 Text를 지움.

- **두 번째 화면** ( sign_up_screen.xml + SignUpActivity.kt )
  - 개요: 사용자로부터 희망하는 아이디, 패스워드와 이름, 주소, 전화번호를 입력받고 정보를 로컬 파일에 저장함.  
  아이디와 비밀번호는 일정 조건을 달성해야 함. 마지막으로 개인 정보 이용 약관에 동의해야 회원가입을 완료할 수 있음.
  
  - 레이아웃: Root ViewGruoup은 LinearLayout, 그 하위로 다수의 LinearLayout에 TextView와 EditView가 한줄로 정렬되는 구조로 구성.  
  아이디, 비밀번호의 조건을 표시하는 TextView와 회원가입을 완료하는 Button은 root바로 아래에 구성함.

  - 스크립트 ( SignUpActivity.kt ) :
    - onCreate
      - id_inputfield TextChangedListener : 텍스트가 바뀐 후에는 정규표현식을 통해서 조건을 검사하고 부족한 조건에 맞추어 TextView로 표현함.
      - password_inputfield TextChangedListener : 아이디 입력칸과 동일함,
      - sign_up_complete OnCLickListener : 정보 입력, 개인정보 이용 동의 여부에 따라서 Toast로 문자열 표시함.  
      조건이 모두 만족된 경우에는 정보를 로컬파일에 저장함.
  
    -checkFinishable : 모든 정보들이 입력되었는지, 개인정보 이용에는 동의하는 가를 체크하고 Boolean값을 리턴함.
  
- **세 번째 화면** ( calculator.xml + calculatorActivity.kt )
  
  - 개요: 버튼 입력을 통해서 숫자를 수식창에 표시하고 표시된 수식값을 계산하여 다시 수식창에 출력하는 간단한 계산기.

  - 레이아웃: Root ViwGroup은 ConstraintLayout, Table Layout을 통해서 숫자, 괄호, 사칙연산 버튼등을 정렬함.

  - 스크립트( calculator.kt ) :
    - onClick : "버튼 클릭 이벤트에서 버튼 아이디에 따라서, 해당하는 문자열을 수식에 추가함
  
    - calculate : "수식창의 문자열을 계산하고 다시 수식창에 표시함, 예외발생시 에러 메시지를 표시  
    ( 외부 라이브러리 exp4j 사용 )
  
    - initListener : 각각의 버튼에 onCllickListener를 등록하는 메서드
  
- **UserInformation.kt** :
  - 개요 : UserInformation이라는 데이터 클래스. id, password, name, address, phonenumber의 5가지 String 데이터가 정의되어있음.
  
- **UserDataSets.kt** :
  - 개요 : UserInformation 인스턴스 리스트를 프로퍼티로 가지는 클래스.

  - 메서드
    - findDuplicate : 인자로 받은 ID가 이미 등록된 아이디인가를 확인하고 Boolean값을 리턴하는 메서드
  
    - validate : 로그인 정보를 입력받고 해당 아이디와 비밀번호가 등록된 정보인가를 비교하고 Boolean값을 리턴하는 메서드
  
    - addData : UserInformation인스턴스를 리스트에 더하는 메서드
  
- **UserDataManager.kt** :
  - 개요 : Nested UserDataSets 인스턴스를 통해서 회원정보를 관리하는 클래스

  - 메서드
    - updateData : UserDataSets에 UserInformation 인스턴스를 더하고 OutputStream을 통해서 로컬 파일에 저장
  
    - initialFileCheck : 프로그램이 시작될 때 로컬파일이 이미 있는지를 확인하고 없으면 파일을 생성하고, 있으면 데이터를 저장함
  
    - loadData : Inputstream을 통해서 로컬 파일로부터 회원 정보를 불러옴
  
    - fileExists : 회원 정보를 저장한 로컬 파일이 존재하는가를 확인하는 메서드.

- Troubleshooting
  - failIfKotlinPluginAppliedAndKotlinModelIsMissing 에러  
  코틀린 버전이 Stable로 되어 있기 때문에 발생하는 문제로 메뉴의 Tools -> Kotlin -> Configure Kotlin Plug Updates -> Update Channel을 Early Access Preview 1.3x으로 설정하면 해결됨. 
