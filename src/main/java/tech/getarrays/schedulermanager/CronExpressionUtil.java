package tech.getarrays.schedulermanager;

public class CronExpressionUtil {

    public static String getCronExpression(int days) {
    // This cron expression triggers at midnight every 'days' days
    return String.format("0 0 0 1/%d * ?", days);
}



}
