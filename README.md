# AndroidStandardLevel
- 안드로이드 앱 개발의 기본기를 스터디한 기록

* TODO
  * Feature 이동 UI(MainActivity)
    * Carousel with MotionLayout
  * 각 기능 리팩토링
  * 세부 기능 추가 및 고도화
  * Migrate to Jetpack Compose UI
  * UI 고도화

## Tech Stack
* Room
* Retrofit
* Hilt DI
* MVVM
* LiveData
* Coroutine
* Clean Architecture
  * Repository Pattern(Domain Layer)
  * Use Case pattern(Domain Layer)
* Multi Module
* Kotlin DSL

## Module
* features
  * 기능 별로 분류
* core
  * DI, Util, Base 등 공통 사용 클래스
* local
  * Internal Database(Room), Model, Dao, Local DI(Database, Dao)
* remote
  * Remote Rest API(Retrofit), Model, Dto, API Interface, Remote DI(Retrofit, Api class)
* repository
  * Data Source, Repository, Use Case


## features
### Alarm
- BroadcastReceiver
- AlarmManager
- PendingIntent
- TiemPickerDialog

### AudioRecorder
- Custom View([RecordButton.kt](https://github.com/thkim0118/AndroidStandardLevel/blob/master/features/AudioRecorder/src/main/java/com/terry/recorder/RecordButton.kt), [SoundVisualizerView.kt](https://github.com/thkim0118/AndroidStandardLevel/blob/master/features/AudioRecorder/src/main/java/com/terry/recorder/SoundVisualizerView.kt), [CountUpView.kt](https://github.com/thkim0118/AndroidStandardLevel/blob/master/features/AudioRecorder/src/main/java/com/terry/recorder/CountUpView.kt))
- MediaPlayer
- MediaRecorder
- Audio Record Permission

### Books
- Retrofit
- Room
  - Migration
  - Insert, Query
- MVVM
- Hilt DI

### Notification
- Firebase Messaging
- Notification Channel
- Notification Style
  - Normal
  - Expandable
  - Custom
- PendingIntent

### RemoteConfig
- Firebase RemoteConfig
  - Debug -> Fetch 주기를 0으로 설정 가능
  - Release -> Fetch 를 최소 12시간으로 설정해야한다.
  - Remote Config 의 Default 값은 setDefaultsAsync() 로 설정한다.
- TextView
  - maxLines
  - ellipsize
- ViewPager2
  - infinite scroll pager

### WebViewer
- WebView
  - WebViewClient
  - WebChromeClient
- EditText
  - imeOptions
  - inputType
  - selectAllOnFocus
- SwipeRefreshLayout
- ContentLoadingProgressBar

### Tinder
- Firebase Login
  - Email
  - Facebook
- Firebase Realtime Database

### Transaction(Used transaction)
- Firebase Realtime Database
- Firebase Storage

### Airbnb
- Mock Retrofit
- MVVM
- Hilt DI
- ViewPager2
- BottomSheet
- Naver Map api
- Glide
  - transform(crop, corner)

### VideoPlayer
- ExoPlayer
- MotionLayout
- Mocky Retrofit
- MVVM
- Hilt DI

### Music Player
- ExoPlayer
- Mocky Retrofit
- data class
  - 클래스 내부 다양한 처리 방식(Player 상태 관리 위임)
- MVVM
- Hilt DI

### Location
- Google Map
- Retrofit
- MVVM
- Hilt DI
