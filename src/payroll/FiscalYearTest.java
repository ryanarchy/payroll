/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll;

import appdomain.FiscalYear;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ryanarchy
 */
public class FiscalYearTest
{
    public static void main(String[] args)
    {
        Calendar cal = Calendar.getInstance();
        FiscalYear fy1 = new FiscalYear(cal.getTime());
        FiscalYear fy2 = new FiscalYear(cal.getTime());
        for (LinkedHashMap<Date, Double> payPeriod: fy1.getPayPeriods())
        {
            for (Map.Entry<Date,Double> date : payPeriod.entrySet())
            {
                date.setValue(10.0);
            }
        }
        for (LinkedHashMap<Date, Double> payPeriod: fy2.getPayPeriods())
        {
            for (Map.Entry<Date,Double> date : payPeriod.entrySet())
            {
                date.setValue(13.1);
            }
        }
        System.out.println("FY1.toString()");
        System.out.println(fy1);
        for (int i = 0; i < fy1.getPayPeriods().size(); i++)
        {
            System.out.println("Period " + (i + 1) + ":");
            System.out.println(fy1.getPeriodDoubletime(i));
            System.out.println(fy1.getPeriodHours(i));
            System.out.println(fy1.getPeriodOvertime(i));
        }
        System.out.println("FY2.toString()");
        System.out.println(fy2);
        for (int i = 0; i < fy1.getPayPeriods().size(); i++)
        {
            System.out.println("Period " + (i + 1) + ":");
            System.out.println(fy2.getPeriodDoubletime(i));
            System.out.println(fy2.getPeriodHours(i));
            System.out.println(fy2.getPeriodOvertime(i));
        }
             
    }
}
