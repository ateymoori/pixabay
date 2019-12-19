package com.pixabay.utils.tools

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import com.pixabay.utils.R


class AppUtils {
    companion object {
        fun appVersionName(ctx: Context): String {
            return try {
                val pInfo = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
                val version = pInfo.versionName
                version;
            } catch (e: Exception) {
                e.printStackTrace()
                "000"
            }
        }

        fun openURL(mActivity: Activity?, url: String?) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                mActivity?.startActivity(intent)
            } catch (e: Exception) {
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun unixTimeToDate(unixTime: Long, outPutFormat: String = "yyyy-MM-dd"): String {
            val sdf = java.text.SimpleDateFormat(outPutFormat)
            val date = java.util.Date(unixTime)
            return sdf.format(date)
        }


        fun openInstagramUser(activity: Activity, IGUserName: String) {
            val uri = Uri.parse("http://instagram.com/_u/$IGUserName")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.instagram.android")
            try {
                activity.startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/$IGUserName")
                    )
                )
            }
        }

        fun openFaceBookUser(activity: Activity, fbUser: String) {
            val uri = Uri.parse("fb://page/$fbUser")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.facebook.katana")
            try {
                activity.startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/$fbUser")
                    )
                )
            }

        }

        fun openDialer(activity: Activity, number: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            activity.startActivity(intent)
        }

        fun openEmailSender(
            activity: Activity,
            receiver: String,
            subject: String? = "",
            bodyText: String? = ""
        ) {
            val mailto = "mailto:$receiver?" +
                    "&subject=" + Uri.encode(subject) +
                    "&body=" + Uri.encode(bodyText)

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)

            try {
                activity.startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
            }

        }

        @SuppressLint("HardwareIds")
        fun getSystemUniqueCode(context: Context): String {
//            val code = "35" +
//                    Build.BOARD.length % 10 + Build.BRAND.length % 10 +
//                    Build.CPU_ABI.length % 10 + Build.DEVICE.length % 10 +
//                    Build.DISPLAY.length % 10 + Build.HOST.length % 10 +
//                    Build.ID.length % 10 + Build.MANUFACTURER.length % 10 +
//                    Build.MODEL.length % 10 + Build.PRODUCT.length % 10 +
//                    Build.TAGS.length % 10 + Build.TYPE.length % 10 +
//                    Build.USER.length % 10
//            return code + Settings.Secure.getString(
//                context.contentResolver,
//                Settings.Secure.ANDROID_ID
//            )
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }


        fun setClipboard(context: Context, text: String) {
            val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText("Copied Text", text)
            clipboard.setPrimaryClip(clip)
        }

        fun isPackageInstalled(
            packageName: String,
            context: Context?
        ): Boolean {
            if (context == null) return false
            val pm: PackageManager = context.packageManager
            return try {
                pm.getPackageInfo(packageName, 0)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }

        fun openGooglePlayAppByPKG(mActivity: Activity?, pkg: String) {

            try {
                mActivity?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$pkg")
                    )
                )
            } catch (error: ActivityNotFoundException) {
                mActivity?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$pkg")
                    )
                )
            }
        }


    }
}