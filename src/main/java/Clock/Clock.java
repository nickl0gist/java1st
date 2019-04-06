package Clock;

public class Clock {

    private int hours;
    private int minutes;
    private int seconds;

    public Clock(String clock) throws WrongTimeException{
        setHours(Integer.parseInt(clock.split(":")[0]));
        setMinutes(Integer.parseInt(clock.split(":")[1]));
        setSeconds(Integer.parseInt(clock.split(":")[2]));
    }

    public void setSeconds(int parseInt) throws WrongTimeException{
        if ( 0 > parseInt || parseInt > 59)
            throw new WrongTimeException("The seconds are out of range!");
        else
            this.seconds = parseInt;
    }

    public void setHours(int parseInt) throws WrongTimeException {
        if ( 0 > parseInt || parseInt > 23)
            throw new WrongTimeException("The hours are out of range!");
        else
            this.hours = parseInt;
    }

    public void setMinutes(int parseInt) throws WrongTimeException {
        if ( 0 > parseInt || parseInt > 59)
            throw new WrongTimeException("The minutes are out of range!");
        else
            this.minutes = parseInt;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void addSeconds(int sec) throws WrongTimeException{
        if (sec < 0)
            throw new WrongTimeException("Use backTimeSeconds method");
        if (getSeconds() + sec >= 60){
            int secSum = (getSeconds() + sec);
            setSeconds(secSum % 60);
            addMinutes(secSum / 60);
        } else {
            setSeconds(getSeconds() + sec);
        }
    }

    public void addMinutes(int min) throws WrongTimeException{
        if (min < 0)
            throw new WrongTimeException("Use backTimeMinutes method");
        if (getMinutes() + min >= 60){
            int minSum = (getMinutes() + min);
            setMinutes(minSum % 60);
            addHours(minSum / 60);
        } else {
            setMinutes(getMinutes() + min);
        }
    }

    public void addHours(int hour) throws WrongTimeException{
        if (hour < 0)
            throw new WrongTimeException("Use backTimeHours method");
        if (getHours() + hour >= 24){
            setHours((getHours() + hour) % 24);
        } else {
            setHours(getHours() + hour);
        }
    }

    public void addClock(Clock cl2) throws WrongTimeException {
        if (cl2 == null)
            throw new WrongTimeException("Parameter passed to method shouldn't be NULL");
        addHours(cl2.getHours());
        addMinutes(cl2.getMinutes());
        addSeconds(cl2.getSeconds());
    }

    public void backClock(Clock cl2) throws WrongTimeException {
        if (cl2 == null)
            throw new WrongTimeException("Parameter passed to method shouldn't be NULL");
        backTimeHours(cl2.getHours());
        backTimeMinutes(cl2.getMinutes());
        backTimeSeconds(cl2.getSeconds());
    }

    @Override
    public String toString() {
        String hour = (hours < 10) ? "0" + hours : Integer.toString(hours);
        String minute = (minutes < 10) ? "0" + minutes : Integer.toString(minutes);
        String second = (seconds < 10) ? "0" + seconds : Integer.toString(seconds);
        return "Current Time[" + hour +
                ":" + minute + ":" + second + ']';
    }

    public void backTimeSeconds(int sec) throws WrongTimeException { // 02:03:53 - 174 -> 02:00:59
        if (sec < 0)
            throw new WrongTimeException("The argument should be positive");
        if (sec >= 60 ){
            backTimeSeconds(sec % 60);
            backTimeMinutes(sec / 60);
        } else if (getSeconds() < sec){
            int i = 60 + getSeconds() - sec;
            setSeconds(i);
            backTimeMinutes(1);
        } else {
            setSeconds(getSeconds() - sec);
        }
    }

    public void backTimeMinutes(int min) throws WrongTimeException{
        if (min < 0)
            throw new WrongTimeException("The argument should be positive");
        if (min >= 60 ){
            backTimeMinutes(min % 60);
            backTimeHours(min / 60);
        } else if (getMinutes() < min){
            setMinutes(60 + getMinutes() - min);
            backTimeHours(1);
        } else {
            setMinutes(getMinutes() - min);
        }
    }

    public void backTimeHours(int hour) throws WrongTimeException{
        if (hour < 0)
            throw new WrongTimeException("The argument should be positive");
        if (hour > 24) {
            backTimeHours(hour % 24);
        } else if (hour > getHours()){
            setHours(24 + getHours() - hour);
        } else {
            setHours(getHours() - hour);
        }
    }
}
