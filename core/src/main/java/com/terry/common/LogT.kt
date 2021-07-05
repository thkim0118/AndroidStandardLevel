package com.terry.common

import android.util.Log

/*
 * Created by Taehyung Kim on 2021-06-26
 */
class LogT {
    companion object {
        private const val TAG = "thkim_debug"
        private val DEBUG_MODE = BuildConfig.DEBUG

        fun v(msg: String) {
            if (DEBUG_MODE) {
                Log.v(TAG, buildLogMsg(msg))
            }
        }

        fun d(msg: String) {
            if (DEBUG_MODE) {
                Log.d(TAG, buildLogMsg(msg))
            }
        }

        fun i(msg: String) {
            if (DEBUG_MODE) {
                Log.i(TAG, buildLogMsg(msg))
            }
        }

        fun w(msg: String) {
            if (DEBUG_MODE) {
                Log.w(TAG, buildLogMsg(msg))
            }
        }

        fun e(msg: String) {
            if (DEBUG_MODE) {
                Log.w(TAG, buildLogMsg(msg))
            }
        }

        fun start() {
            if (DEBUG_MODE) {
                Log.d(TAG, buildLogMsg("---> S"))
            }
        }

        fun end() {
            if (DEBUG_MODE) {
                Log.d(TAG, buildLogMsg("E <---"))
            }
        }

        private fun buildLogMsg(message: String): String {
            val ste = Thread.currentThread().stackTrace[4]

            return StringBuilder().append("[ ")
                .append(ste.fileName.replace(".java", ""))
                .append("::")
                .append(ste.methodName)
                .append(" ] ")
                .append(message)
                .toString()
        }
    }
}