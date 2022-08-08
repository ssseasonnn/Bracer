package zlc.season.bracer

import android.app.Activity
import android.content.Intent

object ActivityIntentHolder {
    private val intentsMap = mutableMapOf<Activity, Intent>()

    @Synchronized
    fun createIntent(activity: Activity): Intent {
        var intent = intentsMap[activity]
        if (intent == null) {
            intent = Intent()
            intentsMap[activity] = intent
        }
        return intent
    }

    @Synchronized
    fun deleteIntent(activity: Activity) {
        intentsMap.remove(activity)
    }

    fun getIntent(activity: Activity): Intent? {
        return intentsMap[activity]
    }
}