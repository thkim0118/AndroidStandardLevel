/*
 * Created by Taehyung Kim on 2021-07-04
 */
object Modules {
    const val APP = ":app"

    const val CORE = ":core"

    const val LOCAL = ":local"
    const val REMOTE = ":remote"

    const val REPOSITORY = ":repository"

    object DynamicFeature {
        const val BMI = ":features:Bmi"
        const val LOTTO = ":features:Lotto"
        const val DIARY = ":features:Diary"
        const val CALCULATOR = ":features:Calculator"
        const val FRAME = ":features:Frame"
        const val POMODORO = ":features:Pomodoro"
        const val AUDIO_RECORDER = ":features:AudioRecorder"
        const val WEB_VIEWER = ":features:WebViewer"
        const val NOTIFICATION = ":features:Notification"
        const val REMOTE_CONFIG = ":features:RemoteConfig"
        const val ALARM = ":features:Alarm"
        const val BOOKS = ":features:books"
        const val TINDER = ":features:Tinder"
        const val TRANSACTION = ":features:Transaction"
        const val AIRBNB = ":features:Airbnb"
        const val VIDEO_PLAYER = ":features:VideoPlayer"
        const val MUSIC_PLAYER = ":features:MusicPlayer"
        const val LOCATION = ":features:Location"
    }
}