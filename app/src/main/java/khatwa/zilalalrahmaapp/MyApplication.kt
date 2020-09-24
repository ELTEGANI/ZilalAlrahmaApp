package khatwa.zilalalrahmaapp

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import khatwa.zilalalrahmaapp.others.MyWorker
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {

        super.onCreate()
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
        var dayDiff = 6 - dayOfWeek   //different (in Days) between curr day and Friday

        if (dayDiff < 0) dayDiff += 7

        val currHour = calendar[Calendar.HOUR_OF_DAY]
        val hourDiff = 15 - currHour //different between curr Hour and 15:00

        val timeDiff = dayDiff * 24 + hourDiff //different (in Hours) between curr time and Friday at 15:00
        Timber.e(timeDiff.toString())

        val notifyRequest = PeriodicWorkRequest.Builder(MyWorker::class.java, 7, TimeUnit.DAYS)
                .setInitialDelay(timeDiff.toLong(), TimeUnit.HOURS) // remind user every friday at 15:00 to 16:00
                .build()

        WorkManager.getInstance(this)
                .enqueueUniquePeriodicWork(
                        "notify_worker",
                        ExistingPeriodicWorkPolicy.KEEP,  // todo  use KEEP instead of REPLACE
                        notifyRequest)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }


}