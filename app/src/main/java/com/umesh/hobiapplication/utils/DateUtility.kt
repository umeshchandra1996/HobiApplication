package com.umesh.hobiapplication.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtility {
    /**
     * function converts the string date in given input format to required output format
     *
     * @param inputFormat
     * @param outputFormat
     * @param input
     * @return
     */
    fun formatStringDate(inputFormat: String, outputFormat: String, input: String):String{
        val inputDateFormat = SimpleDateFormat(inputFormat)
        val outputDateFormat = SimpleDateFormat(outputFormat)
        return outputDateFormat.format(inputDateFormat.parse(input))
    }

    /**
     * function converts the date object in required output format string
     *
     * @param outputFormat
     * @param input
     * @return
     */
    fun formatDate(outputFormat: String, input: Date):String{
        val simpleDateFormat = SimpleDateFormat(outputFormat)
        return simpleDateFormat.format(input)
    }

    /**
     * function convert milliseconds dateTime in required output format string
     *
     * @param outputFormat
     * @param milliSeconds
     * @return
     */
    fun getTimeFromMilliseconds(outputFormat: String, milliSeconds: Long):String{
        val simpleDateFormat = SimpleDateFormat(outputFormat)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return simpleDateFormat.format(calendar.time)
    }

    /**
     * function calculate the age from milliseconds dateTime
     *
     * @param time
     * @return
     */
    fun calculateAgeYear(time: Long) :String {
        val today = Calendar.getInstance()
        val timeDiff=today.time.time-time
        val numYears = (timeDiff/ 31536000000).toInt()
        val numMonth = ((timeDiff % 31536000000) / 2592000000).toInt()
        return if(numYears>0 || numMonth>0){
            (if(numYears>0) "$numYears yr" else "")+if(numMonth>0) " $numMonth mo" else ""
        }else{
            " < 1 mo"
        }
    }

    /**
     * function return DOB with age by calculating from the milliseconds dateTime
     *
     * @param time
     * @return
     */
    fun showAgeFormat(time: Long):String{
        val date= getTimeFromMilliseconds("dd-MMM-yyyy",time)
        val age= calculateAgeYear(time)
        return "$date ($age)"
    }

    /**
     * function returns the current dateTime milliseconds in string
     *
     * @return
     */
    fun getTimestamp(): String
    {
        return Date().time.toString()
    }
}