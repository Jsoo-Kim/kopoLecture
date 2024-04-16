package p32;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
//		//---- 1. String 클래스로 "안녕하세요 자바입니다" 문자열 생성 후 거꾸로 만들어서 출력
//		
//		StringBuffer str = new StringBuffer("안녕하세요 자바입니다");
//		str.reverse();
//		String str2 = str.toString();
//		System.out.println(str2);
//	
//		Date nowDate = new Date();
//		System.out.println(nowDate);
//		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String strNowDate = simpleDateFormat.format(nowDate);
//		System.out.println(strNowDate); // 2024-04-16 16:11:41
//		
//		
//		Date sDate = null;
//		try {
//			sDate = simpleDateFormat.parse("2020-12-30 13:00:00");
//			System.out.println(sDate); // Wed Dec 30 13:00:00 KST 2020
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
////		Calendar cal = Calendar.getInstance();
////		cal.setTime(sDate);
////		System.out.println(cal.get(Calendar.MONTH));
//		
//		String monthString = (new SimpleDateFormat("MM")).format(sDate);
//		System.out.println(monthString); // 12
//		int monthInt = Integer.parseInt(monthString);
//		System.out.println(monthInt); // 12
//		
//		
//		long date1 = nowDate.getTime();
//		System.out.println(date1); // 1713251991847 현재 date를 miliSeond로 나타냄
//		long date2 = sDate.getTime();
//		long diffTime = date1 = date2;
////		diffTime / 1000 (몇 초 차이 확인)
////		diffTime / 1000 / 60 (몇 분 차이 확인)
////		diffTime / 1000 / 60 / 60 (몇 시간 차이 확인)
////		diffTime / 1000 / 60 / 60 / 60 (몇 일 차이 확인)
		
		

		
		//---- 2. 강의자료 하단의 문제 4개 진행
		
		
		//---- 2-1. 특정 날짜의 년, 월, 일, 요일 확인
		//---- 2-1-1) LocaleDate 이용
        LocalDate date = LocalDate.of(2024, 4, 16); // 원하는 날짜 직접 지정 
//        LocalDate date = LocalDate.now(); // 현재 닐짜 가져오기

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        
        // 요일을 문자열로 변환
        String dayOfWeekString = dayOfWeek.toString();

        // 출력
        System.out.println("년: " + year);
        System.out.println("월: " + month);
        System.out.println("일: " + day);
        System.out.println("요일: " + dayOfWeekString);

        
        
		//---- 2-1-2) Calendar 이용
        Calendar calendar = Calendar.getInstance(); // 현재 날짜와 시간 가지고 있는 Calendar 객체

        calendar.set(2024, Calendar.APRIL, 16);
		int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 1을 더함
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek2 = calendar.get(Calendar.DAY_OF_WEEK); // 요일을 숫자로 반환
        
        // 요일을 문자열로 변환
        String[] daysOfWeek = {"일", "월", "화", "수", "목", "금", "토"};
        String dayOfWeekString2 = daysOfWeek[dayOfWeek2 - 1];

        // 출력
        System.out.println("년: " + year);
        System.out.println("월: " + month);
        System.out.println("일: " + day);
        System.out.println("요일: " + dayOfWeekString);
		
        
        
        
		//---- 2-2. 특정 월의 마지막 날짜 구하기
        //---- 2-2-1) 자바8 이전
        // 특정 년월 지정
        int year3 = 2024; 
        int month3 = Calendar.FEBRUARY;
        
        // Calendar 객체를 생성하고 지정된 년월을 설정
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(year, month3, 1); // 해당 월의 1일로 설정
        
        // 해당 월의 마지막 날짜 가져오기
        int lastDay = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        // 출력
        System.out.println(year3 + "년 " + (month3 + 1) + "월의 마지막 날짜는 " + lastDay + "일입니다.");
        
        
        
        //---- 2-2-2) 자바8 이후
        // 특정 년월
        int year31 = 2024;
        int month31 = 2; // 월은 1부터 시작합니다. (1: 1월, 2: 2월, ..., 12: 12월)

        // 해당 년월의 마지막 날짜를 가져오기
        YearMonth yearMonth = YearMonth.of(year31, month31);
        int lastDay2 = yearMonth.lengthOfMonth();

        // 출력
        System.out.println(year31 + "년 " + month31 + "월의 마지막 날짜는 " + lastDay2 + "일입니다.");
		
        
        
        
		//---- 2-3. 문자열을 Date/Calendar로 변환하기
        //---- 2-3-1) 문자열을 Date로 변환
        String dateString = "2024-04-16";
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
        	Date date2 = dateFormat.parse(dateString); // 여기에서 포맷에 맞게 문자열이 Date로 변환됨
        	System.out.println("Date 객체: " + date2);
        } catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
        //---- 2-3-2) 문자열을 Calendar로 변환
//        String dateString = "2024-04-16";
//        
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            Date date3 = dateFormat.parse(dateString); // 여기에서 포맷에 맞게 문자열이 Date로 변환됨

            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date3); // 여기에서 Date가 Calendar로 변환됨! (문자열 -> Date -> Calendar)
            
            System.out.println("Calendar 객체: " + calendar3);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        
        
        
		//---- 2-4. 날짜들 간의 시간 차이 구하기
		//---- 2-4-1) 자바8 이전
        // 두 날짜 생성
        Date date4 = new Date();
        Date date5 = new Date(System.currentTimeMillis() + 3600 * 1000); // 현재 시간에서 1시간 뒤의 시간

        // 두 날짜 사이의 시간 차이 계산
        long timeDifference = date4.getTime() - date5.getTime(); // getTime() 메소드는 1970년 1월 1일부터 현재까지의 시간을 밀리초로 반환합니다.

        // 시간 차이 출력
        System.out.println("두 날짜 간의 시간 차이: " + timeDifference + "밀리초");
        
        
        //---- 2-4-2) 자바8 이후
        // 두 날짜 생성
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.now().plusHours(1); // 현재 시간에서 1시간 뒤의 시간

        // 두 날짜 사이의 시간 차이 계산
        Duration duration = Duration.between(dateTime1, dateTime2);

        // 시간 차이 출력
        System.out.println("두 날짜 간의 시간 차이: " + duration.toMillis() + "밀리초");
		
	}
}
