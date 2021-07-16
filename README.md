# AndroidStandardLevel

## features
### AudioRecorder
- Custom View([RecordButton.kt](https://github.com/thkim0118/AndroidStandardLevel/blob/master/features/AudioRecorder/src/main/java/com/terry/recorder/RecordButton.kt), [SoundVisualizerView.kt](https://github.com/thkim0118/AndroidStandardLevel/blob/master/features/AudioRecorder/src/main/java/com/terry/recorder/SoundVisualizerView.kt), [CountUpView.kt](https://github.com/thkim0118/AndroidStandardLevel/blob/master/features/AudioRecorder/src/main/java/com/terry/recorder/CountUpView.kt))
- MediaPlayer
- MediaRecorder
- Audio Record Permission

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

### Alarm
- BroadcastReceiver
- AlarmManager
- PendingIntent
- TiemPickerDialog
