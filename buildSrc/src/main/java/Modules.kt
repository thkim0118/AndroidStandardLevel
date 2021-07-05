/*
 * Created by Taehyung Kim on 2021-07-04
 */
object Modules {
    const val APP = ":app"

    const val CORE = ":core"

    const val LOCAL = ":local"

    const val REPOSITORY = ":repository"

    object DynamicFeature {
        const val BMI = ":features:bmi"
        const val LOTTO = ":features:lotto"
        const val DIARY = ":features:diary"
        const val CALCULATOR = ":features:calculator"
        const val FRAME = ":features:frame"
        const val POMODORO = ":features:pomodoro"
    }
}