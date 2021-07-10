/*
 * Created by Taehyung Kim on 2021-07-04
 */
object Modules {
    const val APP = ":app"

    const val CORE = ":core"

    const val LOCAL = ":local"

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
    }
}