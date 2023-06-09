package com.ssafy.zip.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ssafy.zip.android.data.Calendar
import com.ssafy.zip.android.databinding.AlbumItemBinding
import com.ssafy.zip.android.databinding.CalendarItemBinding
import com.ssafy.zip.android.databinding.DialogAddCalendarBinding
import kotlin.math.absoluteValue

class CalendarAdapter(
    private val calendarList: List<Calendar>,
    var link:CalendarFragment.MemberSelectAdapter,
    var activity: MainActivity
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>(){
    private val viewPool = RecyclerView.RecycledViewPool()
    private lateinit var calendarMemberAdapter : CalendarMemberAdapter
    private lateinit var calendarItemBinding : CalendarItemBinding

    inner class CalendarViewHolder(val binding: CalendarItemBinding) : RecyclerView.ViewHolder(binding.root){
        val calendarStart: TextView = itemView.findViewById(R.id.calendar_start_time)
        val calendarEnd: TextView = itemView.findViewById(R.id.calendar_end_time)
        val calendarTitle : TextView = itemView.findViewById(R.id.calendar_title)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.calendar_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        calendarItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.calendar_item, parent, false)
        return CalendarViewHolder(calendarItemBinding)

    }
    override fun getItemCount(): Int {
        return calendarList.size
    }

    override fun onBindViewHolder(holder: CalendarAdapter.CalendarViewHolder, position: Int) {
        if (holder is CalendarViewHolder) {
            val calendar = calendarList[position]
            // holder.calendarImage.setImageResource(calendar.user)
            holder.calendarTitle.text = calendar.content
            // holder.calendarStart.text = calendar.startDate.toString()
            // 여기서 0
            var startHours = if(calendar.startDate.hours < 10) ("0" + calendar.startDate.hours) else calendar.startDate.hours.toString()
            var startMinute = if(calendar.startDate.minutes < 10) ("0" + calendar.startDate.minutes)else calendar.startDate.minutes.toString()
            var endHours = if(calendar.endDate.hours < 10) ("0" + calendar.endDate.hours) else calendar.endDate.hours.toString()
            var endMinute = if(calendar.endDate.minutes < 10) ("0" + calendar.endDate.minutes) else calendar.endDate.minutes.toString()

            holder.calendarStart.text =
                startHours + ":" + startMinute
            holder.calendarEnd.text =
                endHours + ":" + endMinute
            calendarMemberAdapter = CalendarMemberAdapter(calendar.users)
            val cnt2 = when (calendarMemberAdapter.itemCount) {
                in 1..4 -> calendarMemberAdapter.itemCount
                // in 5..6 -> 4
                else -> 4
            }
            holder.recyclerView.apply {
                adapter = calendarMemberAdapter
                layoutManager = GridLayoutManager(holder.recyclerView.context, cnt2)
            }

            holder.itemView.setOnLongClickListener {
                // calendar item을 길게 클릭했을 때 실행
                MaterialAlertDialogBuilder(activity)
                    .setMessage("해당 일정을 삭제하시겠습니까?")
                    .setPositiveButton("확인") { dialog, which ->
                        // calendar 삭체
                        link.deleteCalendar(calendar)
                        dialog.dismiss()
                    }
                    .setNegativeButton("취소") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()

                return@setOnLongClickListener true
            }
        }
    }



//    fun countRowNum(adapter: CalendarMemberAdapter) : Int{
//        val cnt2 = when (adapter.itemCount) {
//            in 1..4 -> adapter.itemCount
//            in 5..6 -> 3
//            else -> 4
//        }
//        return cnt2
//    }

}




