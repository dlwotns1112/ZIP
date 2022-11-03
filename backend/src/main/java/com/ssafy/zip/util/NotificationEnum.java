package com.ssafy.zip.util;

import com.ssafy.zip.entity.Family;
import com.ssafy.zip.entity.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public enum NotificationEnum {

    QnaAnswered("%s님이 오늘의 백문백답을 등록했습니다. 확인해보세요!",""),
    BoardUploaded("%s님이 게시글을 등록했습니다. 확인해보세요!",""),
    PictureUploaded("%s님이 사진을 올렸습니다. 확인해보세요!",""),
    FamilyEnrolled("%s님이 가족이 되었습니다. 확인해보세요!",""),
    ScheduleRegistered("%s님이 일정을 등록했습니다. 확인해보세요!",""),
    QnaMissionAccomplished("가족과 함께하는 ‘오늘의 백문백답’ 미션을 성공했습니다!",""),
    TodayLetterMissionAccomplished("가족과 함께하는 ‘오늘의 편지’ 미션을 성공했습니다!",""),
    ScheduleRegistedForMe("%s님이 함께하는 일정을 등록했습니다. 확인해보세요!",""),
    TodayLetterSentTome("%s님에게 오늘의 편지가 도착했습니다. 확인해보세요!",""),
    TodaySchedule("오늘은 %s 일정이 있습니다.","");

    private final String message;
    private final String link;

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }
}