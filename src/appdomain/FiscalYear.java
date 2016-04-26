/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdomain;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ryanarchy
 */
public class FiscalYear
{
    List<LinkedHashMap<Date, Double>> payPeriods = 
            new ArrayList<LinkedHashMap<Date, Double>>();
    short year;
    
    Calendar cal = Calendar.getInstance();
    
    // RYAN
    public FiscalYear(short year)
    {
        this.year = year;
        cal.set(Calendar.YEAR, (int) this.year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        this.buildYear(cal.getTime());
    }
    
    public FiscalYear(String year)
    {
        this.year = Short.parseShort(year);
        cal.set(Calendar.YEAR, (int) this.year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        this.buildYear(cal.getTime());
    }
    
    public FiscalYear(Date year)
    {
        cal.setTime(year);
        this.year = (short) cal.get(Calendar.YEAR);
        cal.set(Calendar.YEAR, (int) this.year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        this.buildYear(cal.getTime());
    }
    
    /**
     * This method is used by the costructors to fill the dates of the year and
     * divide them up in to pay periods.  For simplicity, wer are sticking with 
     * 26 pay periods, any extra days beyond 14 will be put in the last pay 
     * period.
     * @param year This is a Date that has the first day of the year that we 
     * wish to fill out.
     */
    private void buildYear(Date year)
    {
        Calendar firstDay = Calendar.getInstance();
        firstDay.setTime(year);
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(year);
        lastDay.set(Calendar.MONTH, Calendar.DECEMBER);
        lastDay.set(Calendar.DAY_OF_MONTH, 31);
        
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(firstDay.getTime());
        
        while (gcal.getTime().before(lastDay.getTime()))
        {
            for (int x = 0; x < 26; x++)
            {
                LinkedHashMap<Date, Double> pp = new LinkedHashMap<>();
                if (x == 25)
                {
                    while (true)
                    {
                        gcal.add(Calendar.DAY_OF_YEAR, 1);
                        pp.put(gcal.getTime(), 0.0);
                    }
                }
                else
                {
                    for (int i = 0; i < 14; i++)
                    {

                        gcal.add(Calendar.DAY_OF_YEAR, 1);
                        pp.put(gcal.getTime(), 0.0);
                    }
                }
                payPeriods.add(x, pp);
            }
        }
    }
            
    public LinkedHashMap<Date,Double> getPeriod(int period)
    {
        return payPeriods.get(period - 1);
    }
    
    /**
     * This function will fill the requested pay period with hours in the order
     * that they are provided.  We assume that we are not provided more hours
     * than the pay period contains.  If we receive more hours, they are
     * truncated
     * @param period The pay period we are updating
     * @param hours an array of doubles that we will use to fill the pay period
     */
    public void setPeriodHours(int period, double[] hours)
    {
        LinkedHashMap<Date,Double> pp = payPeriods.get(period - 1);
        Date[] keys = pp.keySet().toArray(new Date[pp.size()]);
        int max = Math.min(payPeriods.get(period-1).size(), hours.length);
             
        for (int i = 0;i < max; i++)
        {
            payPeriods.get(period - 1).put(keys[i], hours[i]);
        }
    }
    
    public void setDateHours(Date date, Double hours)
    {
        double h = hours;
        if (h < 0)
        {
            throw new java.lang.IllegalArgumentException("Hours provided must" +
                    " be greater than 0.");
        }
        else if (h > 24)
        {
            throw new java.lang.IllegalArgumentException("Hours provided must" +
                    " be less than 24.");
        }
        
        for (LinkedHashMap<Date,Double> pp: this.payPeriods)
        {
            pp.computeIfPresent(date, (k,v) -> hours);
        }
    }
    public double getDateHours(Date date)
    {
        double hours = 0.0;
        boolean containsDate = false;
        for (LinkedHashMap<Date,Double> pp : this.payPeriods)
        {
            if (pp.containsKey(date))
            {
                containsDate = true;
                hours = pp.get(date);
            }
        }
        if (!containsDate)
        {
            throw new Error("No hours found for date");
        }
        return hours;
            
    }
    /**
     * this function will return the current year of this fiscal calendar as a 
     * date object
     * @return java.util.Date type
     */
    public Date getYear()
    {
        return new Date(year, 1, 1);
    }
    
    /**
     * This method will get the sum of all of the hours worked for a specified
     * period
     * @param period This is the period that we wish to sum.
     * @return We will return a double value that contains all of the hours
     * worked for the specified period.
     */
    public double getPeriodHours(int period)
    {
        LinkedHashMap<Date,Double> pp = this.getPeriod(period);
        double total = 0.0;
        for (double hours : pp.values())
        {
            total += hours;
        }
        return total;
    }
    
    public double getPeriodOvertime(int period)
    {
        double overtime = this.getPeriodHours(period)- (TaxRates.OVERTIME_HOURS + this.getPeriodDoubletime(period));
        
        if (overtime < 0)
        {
            overtime = 0;
        }
        
        return overtime;
    }
    
    public double getPeriodDoubletime(int period)
    {
        LinkedHashMap<Date,Double> pp = this.getPeriod(period);
        double doublePayHours = 0;
        
        for (double h : pp.values())
        {
            if (h > 12)
            {
                doublePayHours += h - 12;
            }
        }
        
        return doublePayHours;
    }
    
    /**
     * this function will return the total amount of hours worked for this
     * fiscal year object
     * @return 
     */
    public double getTotalHours()
    {
        double total = 0.0;
        for (int i = 0; i < 26; i++)
        {
            total += this.getPeriodHours(i);
        }
        return total;
    }
    
    public double getDoubletimeHours()
    {
        double total = 0.0;
        for (int i = 0; i < 26; i++)
        {
            total += this.getPeriodDoubletime(i);
        }
        return total;
    }
    
    public double getOvertimeHours()
    {
        double total = 0.0;
        for (int i = 0; i > 26; i++)
        {
            total += this.getPeriodHours(i);
        }
        return total;
    }
}
