# CD2_KakaoTTS

STT 기반 카카오톡의 다양한 이벤트를 블루투스 제품을 통해 TTS 음성으로 안내하는 IOT 앱개발
2017100845 정세호
2017104038 한봉훈

## 요 약
 카카오톡 메신저 앱은 국내에서 최대 이용률을 보이고 있다. 최근 음성 AI기술이 발전하면서 음성합성기술인 TTS(Text-to-speech)의 활용 분야도 점차 넓어져 가고 있다. 본 연구에서는 카카오톡 서비스와 TTS 및 STT를 활용하여 카카오톡을 통해 수신되는 메세지를 음성을 통해 사용자에게 알려주어 사용자가 직접 핸드폰을 다루지 못하는 상황에서도 메세지의 내용을 확인하고 음성으로 메세지를 송신까지 할 수 있는 어플리케이션을 개발한다.  
 

## 1. 서론
### 1.1. 연구배경
  메신저앱 카카오톡은 국내에서 제일 많은 이용자가 가장 자주 사용하는 메신저 앱으로 지난 해 8월 기준으로 국내 점유율은 87%에 달한다. 카카오톡 이용자는 알림을 통해 앱에 직접 접속하여 메세지를 확인하는 방식으로 앱을 이용한다. 하지만 사용자가 스마트폰을 작동할 수 없는 운전 중 또는 휴식 중에 카카오톡 메세지를 받을 경우 사용자는 이를 눈으로 직접 확인하기 어렵다는 불편함이 있다. 따라서 본 연구에서는 국내 이용률이 가장 높은 메신저 앱 카카오톡과 음성 합성 기술인 TTS(Text-to-Speech)를 이용하여 카카오톡으로 수신되는 메세지의 내용을 사용자가 직접 확인할 필요 없이 블루투스 제품을 통해 재생하여 확인할 수 있게 하고, 나아가 STT(Speech-to-Text)를와 kakao API를 활용하여 음성으로 메세지를 전송할 수 있는 어플리케이션을 구현하고자 한다. 

### 1.2. 연구목표
본 연구에서는 카카오톡 API와 음성 합성 기술 TTS및 STT를 활용하여 앱 어플리케이션을 개발한다. 사용자가 직접 스마트폰을 사용할 수 없는 환경에서 앱을 통해 카카오톡 메신저로 수신되는 메세지를 다양한 블루투스 기기로 수신하는 것을 목표로 한다. 이 때 수신 불륨, 속도, 재생 텍스트 내용 등을 사용자가 직접 설정할 수 있게 함으로써 각 사용자의 편의를 고려한 앱을 개발한다. 

## 2. 관련연구
### 2.1. 블루투스
블루투스(Bluetooth)는 10M 정도의 짧은 거리에서 사용이 가능한 근거리 무선 통신망으로, 다른 기술에 비해 상대적으로 저렴하고 전력 소모가 적어 대부분의 스마트폰 기기에 기본 장착되어 있다. 헤드폰, 키보드 등 무선 기기를 연결하거나 데이터를 전송하는 기능을 수행한다. 안드로이드 플랫폼에서 애플리케이션 프레임워크는 Android Bluetooth API를 통해 블루투스 기능에 대한 액세스를 제공하고, 이러한 API를 통해 블루투스 스캔, 기기 간 데이터 전송 및 수신, 검색을 통한 타 기기 연결 등의 작업을 수행할 수 있다. 

### 2.2. TTS(Text-to-Speech)
TTS는 입력된 디지털 텍스트를 음성으로 변환하여 읽어주는 기술이다. TTS 서비스는 입력받은 문자열을 단어로 나누고, 다시 음소로 나눈 후 이를 조합하여 음성으로 변환하여 출력한다. 기존 문자를 단순히 목소리로 바꾸던 개념에서 빅데이터와 인공지능 기술이 결합되면서 사용자 목소리를 직접 학습하고 따라하는 방식으로 발전하고 있다. 

### 2.3. STT(Speech-to-Text) 
  음성을 문자로 전환하여 처리하는 기술이다. 키보드 대신 문자를 입력하는 방식으로 주목을 받고 있으며 로봇, 텔레매틱스 등 음성으로 기기제어, 정보검색이 필요한 경우에 응용된다. 대표적인 알고리즘은 HMM(Hidden Markov Model)으로서, 다양한 화자들이 발성한 음성들을 통계적으로 모델링하여 음향모델을 구성하며 말뭉치 수집을 통하여 언어모델을 구성한다.

### 2.4. 기존 음성 알림에 관련된 어플리케이션 연구
  카카오톡 메신저 알림을 음성으로 확인해주는 기능은 아이폰의 경우 siri 알림 읽어주기와 안드로이드의 경우 삼성의 빅스비 루틴을 활용한 방법이 있다.  

## 2.5. 기존 연구의 문제점 및 해결 방안
### 2.5.1. 연구의 문제점 
아이폰에서 siri를 이용하는 경우 기기가 지원하는 헤드폰에 연결된 상태에서만 읽어주기가 가능하며 일부 기기에서는 읽어주기 기능을 사용할 수 없다. 또한 알림 내용, 속도, 볼륨 등의 세부적인 설정이 불가능하다.
삼성 빅스비 루틴을 활용한 알림 읽어주기의 경우 해당 기능을 별도로 빅스비 루틴에 적용해야 사용할 수 있으며 세부 설정은 빅스비 루틴이 아닌 별도의 설정 창에서 가능하다.
두 기능 모두 카카오톡 알림에 대해 음성 답장 기능은 제공은 제공하지 않는다. 
 
### 2.5.2. 해결 방안
#### 2.5.2.1. BlueTooth
기기의 종류나 버전에 관계 없이 앱의 알림을 음성으로 출력할 기기를 BlueTooth로 사용자가 선택할 수 있게 한다. 무선으로 연결되기 때문에 자동차와 불편함 없이 연결될 수 있다. 또한 자동차 뿐만이 아니라 손이 불편한 다양한 상황에서 사용할 수 있을 것이다. 블루투스는 기기 또한 호환 되는 경우가 많기 때문에 유연하게 사용가능 할 것으로 예상된다.

#### 2.5.2.2. 간편한 인터페이스
사용자가 기능을 사용하기 위해 별도로 등록하거나 다른 화면에서 추가적인 설정을 할 필요가 없게끔 한 화면에서 간편하게 설정이 가능하게 한다. 기능 사용 여부, 알림 여부, 연결 상태, 사운드, 읽기에 관한 설정을 사용자가 쉽게 접근하여 수정할 수 있도록 한다.  


#### 2.5.2.3. 카카오톡 메시지 API
카카오톡 메세지 API는 사용자가 친구에게 카카오톡 메세지를 보내는 기능을 제공한다. 서비스 정보를 간편하게 공유하는 공유 API, 사용자간 메세지를 보내는 API 두 종류의 API로 구성되며 모바일, 데스크톱, 태블릿 등 다양한 플랫폼에서 활용이 가능하다. STT와 카카오톡 메시지 API를 함께 활용하면 음성으로 카카오톡 메시지 답장을 하는 것이 가능하다.

#### 2.5.2.4. NotificationListener
NotificationListenerService는 Notification에 대한 정보를 받을 수 있는 객체다. 안드로이드 SDK는 NotificationManager에 직접 listener를 등록하는 코드는 제공하고 있지 않으므로 정보를 받고 싶다면 NotificationListenerService를 상속받은 새로운 서비스를 구현해야 한다. 본 프로젝트에서는 onNotificationPosted() 메서드를 구현하여 알림에 관한 정보를 받고, 알림이 수신되었을 때 TTS및 STT기능이 수행되게끔 한다.  

## 3. 프로젝트 내용
###  3.1. 시나리오
어플리케이션이 실행되면 인트로 액티비티가 가장 먼저 실행되며 앱 아이콘이 표시된다. 이후 알림 접근 허용 창으로 넘어가서 사용자에게 해당 앱이 알림에 접근할 수 있도록 설정하게 한다. 이 때 알림 접근 허용을 거부하고 다음 화면으로 넘어가지 못하게 한다. 
알림 접근 권한이 허용되면 앱이 제대로 동작하기 위해 필요한 권한을 요청한다. 마이크와 근처기기 권한을 요청하게 되며 알림 접근 권한 창에서와 마찬가지로 필요한 권한을 얻을 때 까지 다음화면으로 넘어가지 못한다.
필요한 권한이 모두 허용되면 메인 액티비티로 전환이 되면서 카카오 로그인 상태를 확인한다. 만약 카카오 로그인이 되지 않은 상태라면 카카오 로그인 화면으로 넘어가서 로그인을 진행하게 된다. 
로그인이 완료되면 메인 화면으로 넘어가게 되며 좌측 사진은 메인 화면 구성이다. 기능 사용 칸에서는 on/off를 통해 사용자의 사용여부를 결정할 수 있다. 만약 사용하기로 한다면 아래의 탭들이 활성화 된다. 블루투스 탭의 경우 현재 앱과 블루투스기기의 연결이 되어있는지 확인 할 수 있고, 연결된 기기목록을 통해 블루투스 기기와 의 연결을 관리한다. 블루투스의 경우 연결되어 있지 않다면 스마트폰에서 음성이 나오고 연결된 블루투스 기기가 활성화 되어있다면 기기에서 음성이 출력된다. 사운드, TTS설정 탭의 경우 볼륨과 읽기속도의 정도를 조절할 수 있는 설정을 통해 음성을 사용자의 선호에 맞게 변경할 수 있다.
카카오톡 메시지를 수신하면 NotificationLister를 통해 알림에 관한 정보를 읽을 수 있다. 정보 중 필요한 정보만을 필터링하여 설정했던 사운드 및 TTS를 바탕으로 메시지 내용을 음성으로 출력한다. 음성 출력이 끝나면 사용자에게 음성 입력을 받는다. 그리고 입력 받은 내용을 Kakao API를 활용해 해당 내용을 전송한다.  

### 3.2. 요구사항
  블루투스 연결은 Android Blutooth API를 사용한다. 블루투스가 연결이 된 경우 음성이 블루투스 기기에서 출력되도록 하고 아닌경우는 스마트폰에서 음성이 출력되도록 한다. 또한 그 설정을 앱에서 관리할 수 있도록 한다. 
카카오톡 메시지 알림을 받고 메시지의 내용과 발신자, 수신자의 정보를 얻어와 이벤트로 받는다. 그리고 구글에서 내장한 RRS API를 사용한 음성합성 기술을 통해 카카오톡의 내용, 수신자, 발신자 등을 음성으로 출력한다. 이후, 답장을 원하면 사용자가 음성으로 내용을 말하면 이를 STT와 Kakao API를 활용하여 카카오톡 메시지로 보낸다. 

## 3.3. 시스템 설계 
### 3.3.1. Use Case Diagram 
 
 [그림 3] Use Case Diagram 
어플리케이션을 use case diagram으로 나타내면 위 그림과 같다. 사용자는 어플리케이션에 접속하여 필요한 권한을 허용하고 블루투스를 연결한 뒤, 기능 사용 여부, 블루투스 on/off, 음량, tts에 관한 설정을 할 수 있다. 

### 3.3.2. Class Diagram
[그림 4]  Class Diagram 
클래스는 크게 Intro Activity, Main Activity, NotificationListener로 나눌 수 있다. IntroActivity에서는 간단하게 인트로 화면을 보여주고 Main Acitivity에서 여러 설정을 할 수 있다. 그리고 알림이 올 때마다 Notification Listener를 통해 TTS 및 STT기능을 활용하여 사용자에게 음성 출력과 음성 입력 기능을 제공한다. 

## 3.4. 구현
소스코드 경로 및 폴더 구성은 아래와 같다. (https://github.com/hanbonghun/CD2_KakaoTTS.git)
 
## 4. 프로젝트 결과 
  앱을 실행하면 맨 처음 권한 설정을 하게된다. 가장 먼저 사용자에게 앱이 필요한 권한이 부여되어있는지를 확인한다. 처음으로 설치한 앱의 경우는 당연히 아무런 권한이 부여되어 있지 않기 때문에 앱에서 사용자에게 권한을 요청하게 된다. 만약 거부하게 된다면 앱은 그 권한이 필요한 기능은 쓸 수 없다는 안내를 하게된다. 그리고 거부한 경우 앱에서 다시 설정을 통해 권한을 받을 수 있다. 
이렇게 권한을 부여받게 된다면 처음으로 보이는 화면은 기능의 온오프를 조절하는 시작화면이다. 시작화면에서는 2가지 기능만이 존재한다. 기능을 끄고 킬 스위치와 옵션으로 가는 버튼만이 있다. 버튼을 통해 간편하게 기능을 조작하고, 그 기능에 대한 사용자 설정은 옵션화면에서 진행한다.
시작화면에서 옵션화면으로 넘어가게 되면 사용자의 설정만을 담당하는 화면이 시작된다. 사용자 설정에서는 권한설정, 어떤 내용만을 TTS로 실행할 것인지 등의 원하는 옵션을 선택한 뒤 적용할 수 있다.

## 5. 결론 및 보완점
  기존에 내장 되어 있는 구글TTS를 통해 많은 사람들이 사용하고 있는 카카오톡 채팅과 메시지를 읽어 줄 수 있는 서비스가 될 것이다. 따라서 도로 운전 시나 당장 핸드폰을 만져 메시지를 확인하기 곤란할 때 쓸 수 있는 앱이 될 것이라고 기대된다. 핸드폰과 멀리떨어진 곳에서도 메시지를 받을 수 있기 때문에 무선이어폰이나 스피커를 통해서 메시지를 열지 않고도 내용을 알 수 있게 되어 편의성이 증가 할 것이다. 음성으로 답장도 가능하게 되어 단순히 메시지를 받는 것 뿐만이 아니라 음성으로 답장도 보낼 수 있어 사용자의 편의성이 더욱 향상 될 것이다. 하지만 현재 개발된 어플리케이션의 경우 kakao API를 사용하는데 여러 제약이 있다. 친구목록에 접근하기 위해 별도의 승인 과정을 거쳐야 하며, 사용자가 설정한 프로필 이름과 상대방이 카카오톡 가입시 설정한 프로필 이름이 다르다면 메세지 전송이 불가능하다. 
  이를 보완하려면 앱을 비즈앱으로 전환한 뒤 kakao 메세지 API검수를 거쳐 친구목록에 접근하는데 유연성을 부여하는 방법이 있고, 상대방과 내가 설정한 프로필 이름이 다른 경우 이름이 유사한 목록을 사용자에게 제공하는 방법으로 보완이 가능할 것으로 예측한다. 

## 6. 참고문헌
[1] 장재곤 저, 안드로이드 앱 프로그래밍
[2] Android Reference: https://devevloper.android.com/

## 7. 시연영상

