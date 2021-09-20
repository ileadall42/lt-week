package week6;

public class MT1 {

    public static void main(String[] args) {
        MT1 mt1 = new MT1();
        int i = mt1.numberOfRounds("01:45", "15:19");
        System.out.println(i);
    }

    public int numberOfRounds(String startTime, String finishTime) {

        Integer startHourRound = getNextRoundStart(Integer.valueOf(getMinute(startTime)));
        Integer endHourRound = getPreRoundEnd(Integer.valueOf(getMinute(finishTime)));
        Integer startHour = Integer.valueOf(getHour(startTime));
        Integer endHour = Integer.valueOf(getHour(finishTime));
        boolean overNight = roundOverNight(startTime, finishTime);


        if (overNight) {
            endHour += 24;
        }
        if (endHour - startHour < 0) {
            endHour = startHour;
        }
        if (startHour.equals(endHour)) {
            return minRound(startTime, finishTime);
        }

        startHour += 1;

        int round = startHourRound + endHourRound + (endHour - startHour) * 4;
        return round;

    }

    public boolean roundOverNight(String startTime, String endTime) {
        String st = startTime.replace(":", "");
        String et = endTime.replace(":", "");
        return Integer.valueOf(st) > Integer.valueOf(et);
    }

    public Integer getNextRoundStart(Integer mm) {
        int mmMod = mm / 15;
        if (mm % 15 == 0) {
            return 4 - mmMod;
        }
        return 4 - mmMod - 1;
    }

    public Integer getPreRoundEnd(Integer mm) {
        int mmMod = mm / 15;
        return mmMod;
    }

    public Integer minRound(String sh, String fh) {
        Integer shM = ((Integer.valueOf(getMinute(sh)) / 15) + 1) * 15;
        Integer fhM = (Integer.valueOf(getMinute(fh)) / 15) * 15;
        ;
        return fhM - shM <= 0 ? 0 : (fhM - shM) / 15;

    }

    public String getMinute(String formatTime) {
        return splitGetHoutAndMinute(formatTime)[1];
    }

    public String getHour(String formatTime) {
        return splitGetHoutAndMinute(formatTime)[0];
    }

    public String[] splitGetHoutAndMinute(String fortmatTime) {
        String[] timeComposition = fortmatTime.split(":");
        return timeComposition;
    }
}
