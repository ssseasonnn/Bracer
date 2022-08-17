package zlc.season.bracerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import zlc.season.bracer.params

class TestAdapter(
    val activity: Activity,
    val fragment: Fragment,
    val intent: Intent,
    val bundle: Bundle
) {
    val paramFromActivity by activity.params<String>()
    val paramFromFragment by fragment.params<String>()
    val paramFromIntent by intent.params<String>()
    val paramFromBundle by bundle.params<String>()

    fun test(){
        val string by activity.params<String>()
    }
}