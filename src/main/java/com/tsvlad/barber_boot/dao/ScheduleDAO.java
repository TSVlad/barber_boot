package com.tsvlad.barber_boot.dao;



import com.tsvlad.barber_boot.entity.ScheduleItem;

import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleDAO {
    ScheduleItem getScheduleItem(int barber_id, DayOfWeek weekDay);
    List<ScheduleItem> getScheduleItemsForDay(DayOfWeek weekDay);
}
